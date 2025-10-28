<script setup>
import { ref, reactive, computed, onMounted } from 'vue'
import axios from 'axios'

// --- Configuration ---
const API_URL = 'http://localhost:8080/api/students'

// --- State Variables ---
const students = ref([]) // Holds the list of students
const loading = ref(false)
const formValid = ref(false) // Tracks Vuetify form validity

// Student model for form binding, using 'reactive' for objects
const studentForm = reactive({
  id: null,
  name: '',
  email: '',
  age: null,
})

// Computed property to determine if we are editing
const isEditing = computed(() => studentForm.id !== null)

// Reference to the Vuetify form component for validation/reset
const formRef = ref(null)

// --- Vuetify Data/Rules ---
const headers = [
  { title: 'ID', key: 'id' },
  { title: 'Name', key: 'name' },
  { title: 'Email', key: 'email' },
  { title: 'Age', key: 'age' },
  { title: 'Actions', key: 'actions', sortable: false },
]

const rules = {
  required: (v) => !!v || 'This field is required',
  email: (v) => /.+@.+\..+/.test(v) || 'E-mail must be valid',
  age: (v) => (v && v > 0) || 'Age must be positive',
}

// --- CRUD Operations ---

// READ Operation
async function fetchStudents() {
  loading.value = true
  try {
    // API returns List<Student> Entity
    const response = await axios.get(API_URL)
    students.value = response.data
  } catch (error) {
    console.error('Error fetching students:', error)
  } finally {
    loading.value = false
  }
}

// CREATE/UPDATE Operation
async function saveStudent() {
  // Validate the form using the component reference
  const { valid } = await formRef.value.validate()
  if (!valid) return

  try {
    const studentData = { ...studentForm } // Copy the reactive data

    if (isEditing.value) {
      // UPDATE (PUT) - Backend now returns a String message, not the updated object.
      await axios.put(`${API_URL}/${studentData.id}`, studentData)
    } else {
      // CREATE (POST) - Backend returns the new Student Entity (with generated ID).
      await axios.post(API_URL, studentData)
    }

    // Refresh the list and reset the form
    await fetchStudents()
    resetForm()
  } catch (error) {
    console.error('Error saving student:', error)
    // In a real app, you'd show an error message here (e.g., using a v-snackbar)
  }
}

// DELETE Operation
async function deleteStudent(id) {
  if (!confirm('Are you sure you want to delete this student?')) return

  try {
    // DELETE - Backend now returns a String message, not 204 No Content.
    await axios.delete(`${API_URL}/${id}`)
    fetchStudents() // Refresh list after successful deletion
  } catch (error) {
    console.error('Error deleting student:', error)
    // Handle error, especially if student ID wasn't found (404)
  }
}

// --- UI/Utility Methods ---

function editStudent(item) {
  // Populate the reactive form model with the item's data
  // Object.assign safely copies properties from the table row (item) to the form model
  Object.assign(studentForm, item)
}

function resetForm() {
  // Reset Vuetify validation state
  formRef.value.reset()

  // Manually clear the model data and reset id state
  Object.assign(studentForm, {
    id: null,
    name: '',
    email: '',
    age: null,
  })
}

// Fetch students when the component mounts
onMounted(() => {
  fetchStudents()
})
</script>

<template>
  <v-app>
    <v-main>
      <v-container>
        <h1 class="text-h4 mb-6 text-indigo-darken-2">Student Database Manager 🧑‍🎓</h1>

        <v-card class="mb-8" elevation="4">
          <v-card-title class="bg-indigo-lighten-5 py-3">
            {{ isEditing ? 'Edit Student Details' : 'Add New Student' }}
          </v-card-title>
          <v-card-text class="pt-4">
            <v-form ref="formRef" v-model="formValid" @submit.prevent="saveStudent">
              <v-row dense>
                <v-col cols="12" md="4">
                  <v-text-field
                    v-model="studentForm.name"
                    label="Name"
                    :rules="[rules.required]"
                    variant="outlined"
                    density="compact"
                  ></v-text-field>
                </v-col>

                <v-col cols="12" md="4">
                  <v-text-field
                    v-model="studentForm.email"
                    label="Email"
                    :rules="[rules.required, rules.email]"
                    type="email"
                    variant="outlined"
                    density="compact"
                  ></v-text-field>
                </v-col>

                <v-col cols="12" md="4">
                  <v-text-field
                    v-model.number="studentForm.age"
                    label="Age"
                    :rules="[rules.required, rules.age]"
                    type="number"
                    variant="outlined"
                    density="compact"
                  ></v-text-field>
                </v-col>
              </v-row>

              <v-btn color="indigo-darken-1" type="submit" :disabled="!formValid" class="mr-4 mt-2">
                {{ isEditing ? 'Update Student' : 'Save Student' }}
              </v-btn>
              <v-btn v-if="isEditing" @click="resetForm" color="grey" variant="tonal" class="mt-2">
                Cancel Edit
              </v-btn>
            </v-form>
          </v-card-text>
        </v-card>

        <v-card elevation="4">
          <v-card-title class="bg-indigo-lighten-5 py-3"
            >Student List ({{ students.length }})</v-card-title
          >

          <v-data-table
            :headers="headers"
            :items="students"
            class="elevation-0"
            :loading="loading"
            density="comfortable"
          >
            <template v-slot:[`item.actions`]="{ item }">
              <v-icon class="me-2" size="small" color="blue-darken-1" @click="editStudent(item)">
                mdi-pencil
              </v-icon>
              <v-icon size="small" color="red-darken-1" @click="deleteStudent(item.id)">
                mdi-delete
              </v-icon>
            </template>

            <template v-slot:no-data>
              <v-alert type="info" variant="tonal" class="my-4">
                No students found. Please add a new student above.
              </v-alert>
            </template>
          </v-data-table>
        </v-card>
      </v-container>
    </v-main>
  </v-app>
</template>

<style>
/* Import Material Design Icons for Vuetify icons */
@import '@mdi/font/css/materialdesignicons.css';
</style>
