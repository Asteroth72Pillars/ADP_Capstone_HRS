<template>
  <div class="card-container">
    <div class="form-container" @submit.prevent="register">
      <form>
        <h2 class="form-header"><i class="fas fa-user-plus"></i> Signup</h2>

        <div class="form-group">
          <label for="first-name"><i class="fas fa-user"></i> First Name:</label>
          <input type="text" id="firstName" name="firstName" placeholder="Enter your first name" v-model="firstName" required>
        </div>
        <div class="form-group">
          <label for="last-name"><i class="fas fa-user"></i> Last Name:</label>
          <input type="text" id="lastName" name="lastName" placeholder="Enter your last name" v-model="lastName" required>
        </div>
        <div class="form-group">
          <label for="email"><i class="fas fa-envelope"></i> Email:</label>
          <input type="email" id="email" name="email" placeholder="Enter your email" v-model="email" required>
        </div>
        <div class="form-group">
          <label for="phone-number"><i class="fas fa-phone"></i> Phone Number:</label>
          <input type="text" id="phoneNumber" name="phoneNumber" placeholder="Enter your phone number" v-model="phoneNumber">
        </div>
        <div class="form-group">
          <label for="password"><i class="fas fa-lock"></i> Password:</label>
          <input type="password" id="password" name="password" placeholder="Enter your password" v-model="password" required>
        </div>
        <div class="form-group">
          <label for="confirm-password"><i class="fas fa-lock"></i> Confirm Password:</label>
          <input type="password" id="confirm-password" name="confirmPassword" placeholder="Confirm your password" required>
        </div>
        <div class="button-container">
          <button class="add-button button" type="submit"><i class="fas fa-user-plus"></i> Signup</button>
          <button class="read-button button" @click="goToLogin"><i class="fas fa-sign-in-alt"></i> Login</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      firstName: "",
      lastName: "",
      email: "",
      phoneNumber: "",
      password: "",
      loadingModal: false,
      successModal: {
        show: false,
        message: ""
      },
      failureModal: {
        show: false,
        message: ""
      }
    };
  },
  methods: {
    register() {
      //this.loadingModal = true;
      axios
          .post("http://localhost:8080/api/user/register", {
            userName: this.email,
            firstName: this.firstName,
            lastName: this.lastName,
            phoneNumber: this.phoneNumber,
            password: this.password,
            email: this.email
          })
          .then(() => {
            this.loadingModal = false;
            alert("Registration successful");
          })
          .catch(() => {
            this.loadingModal = false;
            alert("Registration failed");
          });
    }
  }


};
</script>

<style scoped>
/* Add your component-specific styles here */
</style>
