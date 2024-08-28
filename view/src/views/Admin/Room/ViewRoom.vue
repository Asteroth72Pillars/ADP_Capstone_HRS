<template>
  <div class="card-container card-container-admin ">
    <div class="form-container">
    <div class="room-profile">
      <h1><i class="fas fa-room"></i> Room Profile: </h1>
      <div class="profile-details" v-if="room">
        <div>
          <p><label>Make:</label><span>{{ room.make }}</span></p>

          <p><label>Model:</label><span>{{ room.model }}</span></p>

          <p><label>Year:</label><span>{{ room.year }}</span></p>


          <p><label>Category:</label><span>{{ room.category }}</span></p>

          <p><label>Price Group:</label><span>{{ room.priceGroup }}</span></p>


         <p><label>License Plate: </label>{{ room.licensePlate }}</p>
          <p> <label>Available: </label>{{ room.available }}</p>


          </div>
      </div>
      <div v-else>
        <p>Loading room profile...</p>
      </div>
    </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
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
  name: 'ViewRoom',
  data() {
    return {
      room: null,
    };
  },
  mounted() {
    this.fetchRoomProfile();
  },
  methods: {
    fetchRoomProfile() {
      // Assuming you have the room ID or any other identifier to fetch the room's profile
      const carId = this.$route.params.id// Get the category from the route parameter
      const token = localStorage.getItem('token');
      axios
          .get(`/api/admin/rooms/read/${carId}`, {
            headers: {
              Authorization: `Bearer ${token}`
            }
          })
          .then((response) => {
            this.room = response.data;
          })
          .catch((error) => {
            console.log(error);
          });
    },
  },
};
</script>

<style scoped>
.card-container {

//padding: 50px;
  justify-content: center;
}
.room-profile {
  margin-top: 20px;
}

.room-profile h1 {
  margin-bottom: 10px;
}

.profile-details div {
  margin-bottom: 10px;
}

label {
  font-weight: bold;
}

span {
  margin-left: 10px;
}
</style>


