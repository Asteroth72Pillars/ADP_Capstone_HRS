<template>
  <div id="app">
    <header>
      <nav>
        <ul>
          <li><router-link to="/">Home</router-link></li>
          <li><router-link to="/register">Register</router-link></li>
          <li><router-link to="/login">Login</router-link></li>
          <li><router-link to="/rooms">Rooms</router-link></li>
          <li><router-link to="/bookings">Bookings</router-link></li>
        </ul>
      </nav>
    </header>
    <main>
      <router-view></router-view>
    </main>
    <footer>
      <p>&copy; 2024 My Application</p>
    </footer>
  </div>
</template>

<script>
export default {
  name: 'App'
};
</script>

<style>
/* Add global styles here */
</style>
