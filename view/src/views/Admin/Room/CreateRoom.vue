<template>
  <div class="card-container card-container-admin">
    <div class="form-container-admin">
      <form @submit.prevent="addRoom">
        <h2 class="form-header">Add Room</h2>

        <div class="form-group">
          <label for="roomType">Room Type:</label>
          <input id="roomType" v-model="room.roomType" required type="text">
        </div>

        <div class="form-group">
          <label for="capacity">Capacity:</label>
          <input id="capacity" v-model="room.capacity" required type="number">
        </div>

        <div class="form-group">
          <label for="priceGroup">Price Group:</label>
          <select id="priceGroup" v-model="room.priceGroup" required>
            <option value="ECONOMY">Economy</option>
            <option value="STANDARD">Standard</option>
            <option value="LUXURY">Luxury</option>
            <option value="PREMIUM">Premium</option>
            <option value="EXOTIC">Exotic</option>
            <option value="SPECIAL">Special</option>
            <option value="OTHER">Other</option>
            <option value="NONE">None</option>
          </select>
        </div>

        <div class="form-group">
          <label for="isAvailable">Is Available:</label>
          <select id="isAvailable" v-model="room.isAvailable" required>
            <option :value="true">Yes</option>
            <option :value="false">No</option>
          </select>
        </div>

        <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
        <div class="button-container">
          <button class="confirm-button button" type="submit"><i class="fas fa-check"></i> Confirm</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { PriceGroup } from "@/enums/PriceGroup";
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
      room: {
        id: null,
        roomType: '',
        capacity: 0,
        priceGroup: PriceGroup.LUXURY,
        isAvailable: true
      },
      errorMessage: '', // Added error message data property
    };
  },
  methods: {
    addRoom() {
      this.errorMessage = ''; // Reset the error message
      const token = localStorage.getItem('token');

      axios.post('/api/admin/rooms/create', this.room, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      })
          .then(response => {
            // Handle success
            console.log('Room added successfully');
            console.log(response.data);
            console.log(response);
          })
          .catch(error => {
            // Handle error
            console.log(error);
            if (error.response && error.response.status === 400) {
              // Display an error message to the user
              this.errorMessage = 'Invalid data. Please check the entered values.';
              console.log(error.response.data); // The response data contains details about the validation errors or data issues
              console.log(error.response.status);
              console.log(error.response);
            } else {
              // Display a generic error message
              this.errorMessage = 'An error occurred while adding the room.';
            }
          });

      // You can access the room object using 'this.room' in this method
      console.log('Adding room:', this.room);

      // Reset the form after adding the room
      this.resetForm();
    },

    resetForm() {
      // Reset the form fields
      this.room = {
        id: null,
        roomType: '',
        capacity: 0,
        priceGroup: '',
        isAvailable: true
      };
    }
  }
};
</script>

<style scoped>
.error-message {
  color: red;
  margin-top: 5px;
}
</style>
