<template>
  <div class="task-bar">
    <button class="management-button" @click="navigateTo('/admin/user-management')">
      User Management
    </button>
    <button class="management-button" @click="navigateTo('/admin/booking-management')">
      Booking Management
    </button>
    <button class="management-button" @click="navigateTo('/admin/room-management')">
      Room Management
    </button>
  </div>
</template>

<script>
export default {
  name: 'AdminManagementButtons',
  methods: {
    navigateTo(path) {
      // Ensure the path matches the routes defined in your router configuration
      this.$router.push(path).catch(err => {
        // Handle any potential navigation errors (e.g., route not found)
        console.error(err);
      });
    }
  }
}
</script>

<style scoped>
.task-bar {
  display: flex;
  background-color: #333; /* Dark background for the task bar */
  padding: 10px;
}

.management-button {
  all: unset;
  background-color: rgba(4, 241, 116, 1);
  color: white;
  border-radius: 4px;
  text-align: center;
  cursor: pointer;
  font-family: "Alice", serif;
  padding: 15px 25px;
  margin-right: 20px; /* Space between buttons */
  flex: 1;
  text-align: center;
}

.management-button:last-child {
  margin-right: 0; /* Remove margin on the last button */
}
</style>
