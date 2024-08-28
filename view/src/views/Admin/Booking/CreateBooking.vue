<template>
  <div class="card-container card-container-admin">
    <div class="form-container-admin">
      <router-view></router-view>

      <form ref="bookingForm" @submit.prevent="createBooking">
        <div class="form-header">
          <h2>Create Booking</h2>
        </div>

        <div class="form-group">
          <label for="user">User:</label>
          <select id="user" v-model="selectedUser" name="userId">
            <option v-for="user in users" :key="user.id" :value="user.id">
              User ID: {{ user.id }} Email: {{ user.email }}
            </option>
          </select>
        </div>

        <div class="form-group">
          <label for="issuedDate">Issued Date:</label>
          <input id="issuedDate" v-model="selectedIssuedDate" name="issuedDate" type="datetime-local">
        </div>

        <div class="form-group">
          <label for="returnedDate">Returned Date:</label>
          <input id="returnedDate" v-model="selectedReturnedDate" name="returnedDate" type="datetime-local">
        </div>

        <div class="form-group">
          <label for="room">Room:</label>
          <select id="room" v-model="selectedRoom" name="carId">
            <option v-for="room in rooms" :key="room.id" :value="room.id">
              Room ID: {{ room.id }} Make: {{ room.make }} Model: {{ room.model }}
            </option>
          </select>
        </div>

        <div class="form-group">
          <div class="button-container">
            <button class="confirm-button button" type="submit"><i class="fas fa-check"></i> Confirm</button>
          </div>
        </div>
      </form>
    </div>
  </div>

  <div class="modal-body">
    <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      users: [],
      rooms: [],
      selectedUser: "",
      selectedRoom: "",
      selectedIssuedDate: "",
      selectedReturnedDate: "",
      errorMessage: "",
    };
  },
  mounted() {
    this.fetchUsersList();
    this.fetchRoomsList();
  },
  methods: {
    fetchUsersList() {
      axios
          .get("http://localhost:8080/api/admin/users/list/all")
          .then((response) => {
            this.users = response.data;
          })
          .catch((error) => {
            console.log(error);
            this.errorMessage = "Failed to fetch users list";
          });
    },

    fetchRoomsList() {
      axios
          .get("http://localhost:8080/api/admin/rooms/all")
          .then((response) => {
            this.rooms = response.data;
          })
          .catch((error) => {
            console.log(error);
            this.errorMessage = "Failed to fetch rooms list";
          });
    },

    createBooking() {
      this.errorMessage = "";

      const booking = {
        user: {
          id: this.selectedUser,
        },
        room: {
          id: this.selectedRoom,
        },
        issuedDate: this.selectedIssuedDate,
        returnedDate: this.selectedReturnedDate,
      };

      axios
          .post("http://localhost:8080/api/admin/bookings/create", booking)
          .then((response) => {
            if (response && response.data) {
              console.log("Booking created successfully");
            } else {
              console.error("Response or response.data is undefined");
            }
          })
          .catch((error) => {
            console.log(error);
            this.errorMessage = error.response.data;
          });
    },
  },
};
</script>
