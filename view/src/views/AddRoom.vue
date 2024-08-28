<template>
  <div class="content-container">
    <div class="content-header">
      <h1><i class="fas fa-plus"></i> Add New Room</h1>
    </div>
    <form @submit.prevent="addRoom">
      <div class="form-group">
        <label for="roomType">Room Type</label>
        <input v-model="newRoom.roomType" id="roomType" type="text" required />
      </div>
      <div class="form-group">
        <label for="capacity">Capacity</label>
        <input v-model.number="newRoom.capacity" id="capacity" type="number" min="1" required />
      </div>
      <div class="form-group">
        <label for="priceGroup">Price Group</label>
        <select v-model="newRoom.priceGroup" id="priceGroup" required>
          <option value="" disabled>Select Price Group</option>
          <option v-for="group in priceGroups" :key="group" :value="group">{{ group }}</option>
        </select>
      </div>
      <div class="form-group">
        <label for="isAvailable">Available</label>
        <input v-model="newRoom.isAvailable" id="isAvailable" type="checkbox" />
      </div>
      <button type="submit" class="submit-button button">Add Room</button>
      <router-link to="/rooms" class="cancel-button button">Cancel</router-link>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      newRoom: {
        roomType: "",
        capacity: null,
        priceGroup: "",
        isAvailable: false,
      },
      priceGroups: ['Economy', 'Standard', 'Luxury'], // Example price groups, update as needed
    };
  },
  methods: {
    addRoom() {
      axios
          .post("/api/admin/rooms/add", this.newRoom)
          .then((response) => {
            console.log(response);
            this.$router.push("/rooms"); // Redirect to the room management page after successful addition
          })
          .catch((error) => {
            console.log(error);
            alert("Failed to add room. Please try again.");
          });
    },
  },
};
</script>

<style scoped>
.content-header {
  text-align: center;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
}

.form-group input, .form-group select {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
}

.submit-button, .cancel-button {
  margin-right: 10px;
}
</style>
