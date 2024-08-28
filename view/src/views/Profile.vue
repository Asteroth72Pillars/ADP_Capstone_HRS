<template>
  <div>
    <h1>User Profile</h1>
    <div v-if="user">
      <p><strong>Username:</strong> {{ user.username }}</p>
      <p><strong>Email:</strong> {{ user.email }}</p>
    </div>
    <div v-else-if="error">
      <p>Error loading user profile: {{ error }}</p>
    </div>
    <div v-else>
      <p>Loading...</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Profile',
  props: ['userId'],
  data() {
    return {
      user: null,
      error: null
    };
  },
  async created() {
    try {
      const response = await axios.get(`/api/user/profile/${this.userId}`);
      this.user = response.data;
    } catch (error) {
      this.error = 'Failed to load user profile. Please try again later.';
      console.error('Error fetching user profile:', error);
    }
  }
};
</script>

<style scoped>
/* Add styles here */
</style>
