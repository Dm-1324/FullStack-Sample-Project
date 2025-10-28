

# 🧑‍🎓 Full-Stack Student Database CRUD Project

This is a simple full-stack mini-project demonstrating basic CRUD (Create, Read, Update, Delete) operations using a **Java Spring Boot** backend, a **Vue.js/Vuetify** frontend, and **MySQL** as the database.

## 📁 Project Structure

The project is split into two main directories:

1.  **`Complete-Project`**: The backend API, built with Spring Boot and Java 17/JPA/MySQL.
2.  **`APIproject`**: The frontend UI, built with Vue 3, Composition API, and Vuetify.

-----

## 🚀 1. Setup and Installation

### Prerequisites

| Tool | Version | Notes |
| :--- | :--- | :--- |
| **Java** | 17+ | Required for the backend. |
| **Maven** | 3.x | Used for Spring Boot dependency management. |
| **MySQL** | 8.0+ | Database server. |
| **Node.js** | 20.19.0+ | Required for the frontend (as per `package.json`). |
| **npm** | Latest | Package manager. |

### 1.1. Backend Setup (`Complete-Project`) ☕

1.  **Database Configuration:**
      * Ensure your MySQL server is running.
      * Create a database named `student`.
      * Update the `src/main/resources/application.properties` file with your correct database credentials:
        ```properties
        spring.datasource.url=jdbc:mysql://localhost:3306/student
        spring.datasource.username=root  // CHANGE THIS
        spring.datasource.password=root  // CHANGE THIS
        # ... other settings ...
        ```
2.  **Open in IntelliJ IDEA (or preferred IDE):**
      * Open IntelliJ IDEA and choose **Open**.
      * Navigate to the **`Complete-Project`** directory and open the project. IntelliJ will automatically detect the `pom.xml` and configure the dependencies.
3.  **Run the Application:**
      * Locate the main class: `src/main/java/com/example/Complete_Project/CompleteProjectApplication.java`.
      * Run the application from your IDE.
      * The backend should start on **`http://localhost:8080`**.

### 1.2. Frontend Setup (`APIproject`) 🖼️

1.  **Open in VS Code (or preferred editor):**
      * Open VS Code and choose **Open Folder**.
      * Select the **`APIproject`** directory.
2.  **Install Dependencies:**
      * Open the terminal inside the `APIproject` directory.
      * Install the required Node packages (Vue, Vuetify, Axios, etc.):
        ```bash
        npm install
        ```
3.  **Run the Application:**
      * Start the development server:
        ```bash
        npm run dev
        ```
      * The frontend should now be running and accessible at **`http://localhost:5173/`** (as configured by Vite).

-----

## 2\. API Endpoints (Postman Testing) 🧪

Once the Spring Boot backend is running, use Postman to test the CRUD endpoints on **`http://localhost:8080/api/students`**.

| Operation | Method | Endpoint | Body/Params | Success Status | Description |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Create** | `POST` | `/api/students` | **JSON Body:** `{"name": "...", "email": "...", "age": 20}` | `201 Created` | Creates a new Student record. Returns the created `Student` entity. |
| **Read All** | `GET` | `/api/students` | None | `200 OK` | Retrieves all students. |
| **Read By ID** | `GET` | `/api/students/{id}` | Path Variable `{id}` | `200 OK` or `404 Not Found` | Retrieves a single student. |
| **Update** | `PUT` | `/api/students/{id}` | **JSON Body** (Full Student object) | `200 OK` | Updates an existing student. Returns a **String** message on success. |
| **Delete** | `DELETE` | `/api/students/{id}` | Path Variable `{id}` | `200 OK` or `404 Not Found` | Deletes a student by ID. Returns a **String** message on success. |

### Example Postman Test: Updating a Student

1.  **Method:** `PUT`
2.  **URL:** `http://localhost:8080/api/students/1` (Replace `1` with an actual student ID)
3.  **Body (raw, JSON):**
    ```json
    {
        "id": 1,
        "name": "Updated Name",
        "email": "new.email@test.com",
        "age": 25
    }
    ```
4.  **Expected Response:** Status `200 OK` and Body `"Student Details Updated Successfully!"`.

-----

## 3\. Frontend Logic (`StudentDashboard.vue`)

The Vue component automatically handles the connection, form validation, and data presentation.

| Feature | Implementation | Compatibility |
| :--- | :--- | :--- |
| **Framework** | Vue 3 Composition API (`<script setup>`) | Modern Vue standard. |
| **UI Library** | Vuetify 3 | Provides responsive, clean components. |
| **Data Fetching** | `axios.get(API_URL)` | Fetches the full list of `Student` entities. |
| **Form Validation**| `<v-form ref="formRef" v-model="formValid" :rules="...">` | Native Vuetify validation ensures data quality before API calls. |
| **CRUD Mapping** | `POST` for Create, `PUT` for Update, `DELETE` for Delete | Directly maps to the Spring Boot Controller endpoints. |
