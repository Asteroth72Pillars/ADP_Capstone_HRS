<template>
  <div class="content-container">
    <div class="card-container">
      <h1>List of {{ category }} Rooms</h1>
      <table>
        <thead>
        <tr>
          <th @click="sortRooms('id')">ID</th>
          <th @click="sortRooms('make')">Make</th>
          <th @click="sortRooms('model')">Model</th>
          <th @click="sortRooms('year')">Year</th>
          <th @click="sortRooms('category')">Category</th>
          <th @click="sortRooms('priceGroup')">Price Group</th>
          <th @click="sortRooms('licensePlate')">License Plate</th>
          <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="room in sortedRooms" :key="room.id">
          <td>{{ room.id }}</td>
          <td>{{ room.make }}</td>
          <td>{{ room.model }}</td>
          <td>{{ room.year }}</td>
          <td>{{ room.category }}</td>
          <td>{{ room.priceGroup }}</td>
          <td>{{ room.licensePlate }}</td>
          <td><button @click="deleteRoom(room.id)">Delete</button></td>
        </tr>
        </tbody>
      </table>
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
  name: 'DeleteRoom',
  data() {
    return {
      rooms: [],
      category: '',
      sortColumn: '', // Current column to sort by
      sortDirection: '', // Current sort direction
    };
  },
  mounted() {
    this.fetchRooms();
  },
  methods: {
    fetchRooms() {
      const category = 'all';
      const token = localStorage.getItem('token');
      axios
          .get(`/api/rooms/${category}`, {
            headers: {
              Authorization: `Bearer ${token}`
            }
          })
          .then((response) => {
            this.rooms = response.data;
            this.category = category;
          })
          .catch((error) => {
            console.log(error);
          });
    },
    sortRooms(column) {
      if (this.sortColumn === column) {
        this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
      } else {
        this.sortColumn = column;
        this.sortDirection = 'asc';
      }
    },
    deleteRoom(carId) {
      const token = localStorage.getItem('token');
      axios
          .delete(`api/admin/rooms/delete/${carId}`
              , {
                headers: {
                  Authorization: `Bearer ${token}`
                }
              })
          .then((response) => {
            this.fetchRooms();
            console.log(response);
            console.log('Room deleted');
          })
          .catch((error) => {
            console.log(error);
            console.log('Room not deleted');
          });
    },
  },
  computed: {
    sortedRooms() {
      if (this.sortColumn && this.sortDirection) {
        return this.rooms.sort((a, b) => {
          const aValue = a[this.sortColumn];
          const bValue = b[this.sortColumn];

          if (typeof aValue === 'string' && typeof bValue === 'string') {
            return this.sortDirection === 'asc'
                ? aValue.localeCompare(bValue)
                : bValue.localeCompare(aValue);
          } else {
            return this.sortDirection === 'asc' ? aValue - bValue : bValue - aValue;
          }
        });
      }
      return this.rooms;
    },
  },
};
</script>

<style scoped>
/* Add custom styles for the component */
</style>
