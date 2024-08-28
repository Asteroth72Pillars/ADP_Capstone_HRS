<template>
  <div>
    <h1>Welcome to Home</h1>
    <nav>
      <ul>
        <li><router-link to="/about-us">About Us</router-link></li>
        <li><router-link to="/bookings">Book</router-link></li>
        <li><router-link to="/contact-us">Contact Us</router-link></li>
        <li><router-link to="/faqs">FAQs</router-link></li>
        <li><router-link to="/help-desk">Help Desk</router-link></li>
        <li><router-link to="/login">Login</router-link></li>
        <li><router-link to="/profile">Profile</router-link></li>
        <li><router-link to="/register">Register</router-link></li>
        <li><router-link to="/signout">Signout</router-link></li>
        <li><router-link to="/user-profile">User Profile</router-link></li>
      </ul>
    </nav>
  </div>
</template>

<script>
export default {
  name: 'Home',
};
</script>

<style scoped>
/* Add styles for the navigation links if needed */
</style>
