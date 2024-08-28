<template>
  <div>
    <h1>Login</h1>
    <form @submit.prevent="loginUser">
      <label for="username">Username:</label>
      <input v-model="user.username" id="username" type="text" required />

      <label for="password">Password:</label>
      <input v-model="user.password" id="password" type="password" required />

      <button type="submit">Login</button>
    </form>
    <p v-if="errorMessage" style="color: red;">{{ errorMessage }}</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Login',
  data() {
    return {
      user: {
        username: '',
        password: ''
      },
      errorMessage: ''
    };
  },
  methods: {
    async loginUser() {
      try {
        // Check if the admin is logging in
        if (this.user.username === 'admin' && this.user.password === 'admin') {
          alert('Admin logged in successfully');
          this.$router.push('/admin-home'); // Redirect to AdminHome.vue
        } else {
          // If not admin, proceed with normal user login
          const response = await axios.post('http://localhost:8080/api/user/login', this.user);
          const { userId } = response.data;
          alert('User logged in successfully');
          this.$router.push(`/profile/${userId}`); // Redirecting to user's profile page
        }
      } catch (error) {
        console.error('Error logging in:', error);
        this.errorMessage = 'Invalid username or password'; // Display error message
      }
    }
  }
};
</script>

<style scoped>
form {
  margin-top: 20px;
}

label {
  display: block;
  margin-bottom: 5px;
}

input {
  display: block;
  margin-bottom: 10px;
}

button {
  display: block;
  margin-top: 10px;
}

p {
  margin-top: 10px;
}
</style>
