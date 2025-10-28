# 🧑‍🎓 Full-Stack Student Database CRUD Project

This is a simple full-stack mini-project demonstrating basic **CRUD** (Create, Read, Update, Delete) operations using a **Java Spring Boot** backend, a **Vue.js/Vuetify** frontend, and **MySQL** as the database.

## 📁 Project Structure

The project is split into two main directories:

1.  **`Complete-Project`**: The backend API, built with Spring Boot, Java 17/JPA, and MySQL.
2.  **`APIproject`**: The frontend UI, built with Vue 3 Composition API, and Vuetify.

-----

## 🚀 1. Setup and Installation

### Prerequisites

| Tool | Version | Notes |
| :--- | :--- | :--- |
| **Java** | 17+ | Required for the backend. |
| **Maven** | 3.x | Used for Spring Boot dependency management. |
| **MySQL** | 8.0+ | Database server. |
| **Node.js** | 20.19.0+ | **Critical** for the frontend (as per `package.json`). |
| **npm** | Latest | Package manager. |

-----

### 1.1. Backend Setup (`Complete-Project`) ☕

**IDE: IntelliJ IDEA**

1.  **Database Configuration:**
      * Ensure your **MySQL** server is running and a database named **`student`** exists.
      * **Update** the `application.properties` file inside `src/main/resources/` with your actual MySQL `username` and `password`.
2.  **Open and Run:**
      * Open **IntelliJ IDEA** and open the **`Complete-Project`** folder.
      * Run the main application class: `com.example.Complete_Project.CompleteProjectApplication`.
      * The server should start on **`http://localhost:8080`**.

-----

### 1.2. Frontend Setup (`APIproject`) 🖼️

**IDE: VS Code**

1.  **App Creation (If starting fresh):**
      * *Skip this if your Vue project structure is already set up in the `APIproject` directory.*
    <!-- end list -->
    ```bash
    # 1. Create a Vue/Vite project structure in the APIproject folder
    npm create vite@latest APIproject -- --template vue
    # 2. Change into the project directory
    cd APIproject
    ```
2.  **Install Dependencies:**
      * Open your terminal inside the **`APIproject`** folder and run the command to install the required packages:
    <!-- end list -->
    ```bash
    # Vue 3, Vuetify 3, Axios, and Material Design Icons
    npm install vue@3 vuetify@3 axios @mdi/font
    ```
      * *Note: The packages installed cover the core requirements: **vue**, **vuetify**, **axios**, and **@mdi/font**.*
3.  **Run the Application:**
      * Start the development server:
        ```bash
        npm run dev
        ```
      * The frontend should now be running and accessible at **`http://localhost:5173/`** (as configured by Vite).

-----

## 2\. API Endpoints (Postman Testing) 🧪

Once the Spring Boot backend is running, use Postman to test the CRUD endpoints against the base URL: **`http://localhost:8080/api/students`**.

| Operation | Method | Endpoint | Expected Status | Description |
| :--- | :--- | :--- | :--- | :--- |
| **Create** | `POST` | `/api/students` | `201 Created` | Creates a new Student (Body: `{name, email, age}`). |
| **Read All**| `GET` | `/api/students` | `200 OK` | Retrieves a list of all students. |
| **Read By ID** | `GET` | `/api/students/{id}` | `200 OK` or `404 Not Found` | Retrieves a single student. |
| **Update** | `PUT` | `/api/students/{id}` | `200 OK` or `404 Not Found` | Updates student details (Returns a **String** message on success). |
| **Delete** | `DELETE`| `/api/students/{id}` | `200 OK` or `404 Not Found` | Deletes a student by ID (Returns a **String** message on success). |

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
