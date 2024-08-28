<template>
  <div class="card-container card-container-admin">
    <div class="form-container-admin">
      <form @submit.prevent="addUser">
        <h2 class="form-header">Add User</h2>
        <div class="form-group">
          <label for="firstName">First Name:</label>
          <input id="firstName" v-model="user.firstName" placeholder="Enter first name" required type="text">
        </div>
        <div class="form-group">
          <label for="lastName">Last Name:</label>
          <input id="lastName" v-model="user.lastName" placeholder="Enter last name" required type="text">
        </div>
        <div class="form-group">
          <label for="email">Email:</label>
          <input id="email" v-model="user.email" placeholder="Enter email" required type="email">
        </div>
        <div class="form-group">
          <label for="password">Password:</label>
          <input id="password" v-model="user.password" placeholder="Enter password" required type="password">
        </div>
        <div class="form-group">
          <label for="role">Role:</label>
          <select id="role" v-model="user.roles[0].roleName" required>
            <option value="USER">User</option>
            <option value="ADMIN">Admin</option>
            <option value="SUPERADMIN">Super Admin</option>
          </select>
        </div>
        <div class="button-container">
          <button class="confirm-button button" type="submit"><i class="fas fa-check"></i> Confirm</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";

// Add this line to set a default base URL for your API
axios.defaults.baseURL = 'http://localhost:8080';

// Add an interceptor for every request
axios.interceptors.request.use(
    config => {
      const token = localStorage.getItem('token');

      if (token) {
        config.headers['Authorization'] = `Bearer ${token}`;
      }

      return config;
    },
    error => {
      return Promise.reject(error);
    }
);

export default {
  data() {
    return {
      user: {
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        roles: [{ roleName: "USER" }], // Updated to match the backend structure
      },
      errorMessage: ''
    };
  },
  methods: {
    addUser() {
      console.log("Adding user:", this.user);
      // Send the user data to the backend API or perform any other necessary actions
      const token = localStorage.getItem('token');
      axios
          .post("/api/admin/users/create", this.user, {
            headers: {
              Authorization: `Bearer ${token}`
            }
          })
          .then((response) => {
            // Handle success
            console.log(response);
            alert("User added successfully");
          })
          .catch((error) => {
            // Handle error
            console.log(error);
            alert("Failed to add user. Please try again.");
          });

      // You can access the user object using 'this.user' in this method
      console.log("Adding user:", this.user);

      // Reset the form after adding the user
      this.resetForm();
    },
    resetForm() {
      // Reset the form fields
      this.user = {
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        roles: [{ roleName: "USER" }]
      };
    },
  },
};
</script>

<style scoped>

</style>
