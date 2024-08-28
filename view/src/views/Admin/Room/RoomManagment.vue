<template>
  <div class="content-container">
    <div class="content-header">
      <h1><i class="fas fa-room"> </i>
        Room Management
      </h1>
      <div class="search-bar-container">
        <div class="search-bar">
          <div class="search-input">
            <input v-model="searchQuery" placeholder="Search..." type="text" />
            <button @click="resetSearch" class="read-button button">
              <i class="fas fa-search"> </i> Reset
            </button>
          </div>
          <router-link to="/admin/rooms/create" class="add-button button">
            <i class="fas fa-room"> </i> Add Room
          </router-link>
        </div>
      </div>
    </div>

    <table>
      <thead>
      <tr>
        <th @click="sortRooms('id')">ID <i class="fas fa-sort"></i></th>
        <th @click="sortRooms('roomType')">Room Type <i class="fas fa-sort"></i></th>
        <th @click="sortRooms('capacity')">Capacity <i class="fas fa-sort"></i></th>
        <th @click="sortRooms('priceGroup')">Price Group <i class="fas fa-sort"></i></th>
        <th @click="sortRooms('isAvailable')">Available <i class="fas fa-sort"></i></th>
        <th class="actions-column">Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="room in filteredRooms" :key="room.id">
        <td v-if="!room.editing">{{ room.id }}</td>
        <td v-else>
          <input type="text" v-model="room.id" disabled>
        </td>
        <td v-if="!room.editing">{{ room.roomType }}</td>
        <td v-else>
          <input type="text" v-model="room.roomType">
        </td>
        <td v-if="!room.editing">{{ room.capacity }}</td>
        <td v-else>
          <input type="number" v-model="room.capacity">
        </td>
        <td v-if="!room.editing">{{ room.priceGroup }}</td>
        <td v-else>
          <input type="text" v-model="room.priceGroup">
        </td>
        <td v-if="!room.editing">
          <input type="checkbox" v-model="room.isAvailable" :disabled="room.editing">
        </td>
        <td v-else>
          <input type="checkbox" v-model="room.isAvailable">
        </td>
        <td>
          <template v-if="!room.editing">
            <button @click="deleteRoom(room.id)" class="delete-button button">
              <i class="fas fa-trash"></i> Delete
            </button>
            <button @click="editRoom(room)" class="update-button button">
              <i class="fas fa-edit"></i> Edit
            </button>
            <button @click="openRoomView(room.id)" class="read-button button">
              <i class="fas fa-eye"></i> Read
            </button>
          </template>
          <template v-else>
            <button @click="saveRoom(room)" class="update-button button">Save</button>
            <button @click="cancelEdit(room)" class="delete-button button">Cancel</button>
          </template>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from "axios";
axios.defaults.baseURL = 'http://localhost:8080'; // Update the base URL as needed

export default {
  data() {
    return {
      rooms: [],
      loading: false,
      sortBy: "",
      searchQuery: "",
      showConfirmationModal: false,
      roomToDelete: null,
      successMessage: "",
      failureMessage: "",
    };
  },
  computed: {
    sortedRooms() {
      if (this.sortBy) {
        return [...this.rooms].sort((a, b) => (a[this.sortBy] > b[this.sortBy] ? 1 : -1));
      }
      return this.rooms;
    },
    filteredRooms() {
      const query = this.searchQuery.toLowerCase();
      return this.sortedRooms.filter(room => {
        return Object.values(room).some(val =>
            typeof val === 'string' && val.toLowerCase().includes(query)
        );
      });
    },
  },
  methods: {
    fetchRooms() {
      this.loading = true;
      axios.get("/api/admin/rooms/all")
          .then(response => {
            this.rooms = response.data;
          })
          .catch(error => {
            console.error(error);
            this.failureMessage = "Failed to fetch rooms. Please try again.";
          })
          .finally(() => {
            this.loading = false;
          });
    },
    deleteRoom(roomId) {
      this.showConfirmationModal = true;
      this.roomToDelete = this.rooms.find(room => room.id === roomId);
    },
    cancelDelete() {
      this.showConfirmationModal = false;
      this.roomToDelete = null;
    },
    confirmDelete() {
      if (this.roomToDelete) {
        this.loading = true;
        axios.delete(`/api/admin/rooms/delete/${this.roomToDelete.id}`)
            .then(() => {
              this.fetchRooms();
              this.successMessage = `Room ID: ${this.roomToDelete.id} was deleted successfully.`;
            })
            .catch(error => {
              console.error(error);
              this.failureMessage = "Failed to delete room. Please try again.";
            })
            .finally(() => {
              this.loading = false;
              this.showConfirmationModal = false;
              this.roomToDelete = null;
            });
      }
    },
    openRoomView(roomId) {
      this.$router.push(`/admin/rooms/read/${roomId}`);
    },
    sortRooms(sortBy) {
      this.sortBy = sortBy;
    },
    resetSearch() {
      this.searchQuery = "";
    },
    editRoom(room) {
      room.editing = true;
    },
    saveRoom(room) {
      this.pushUpdatedRoom(room);
      room.editing = false;
      this.loading = true;
    },
    pushUpdatedRoom(room) {
      axios.put(`/api/admin/rooms/update/${room.id}`, room)
          .then(() => {
            this.successMessage = `Room ID: ${room.id} was updated successfully.`;
          })
          .catch(error => {
            console.error(error);
            this.failureMessage = `Room ID: ${room.id} was not updated successfully.`;
          })
          .finally(() => {
            this.loading = false;
          });
    },
    cancelEdit(room) {
      room.editing = false;
    },
  },
  created() {
    this.fetchRooms();
  },
};
</script>

<style scoped>
.content-header {
  text-align: center;
  margin-bottom: 20px;
}

.search-bar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 10px;
}

.search-input {
  display: flex;
  align-items: center;
}

.search-input input {
  margin-right: 10px;
}

.read-button, .add-button, .update-button, .delete-button {
  margin: 5px;
}

button {
  cursor: pointer;
}

.actions-column {
  width: 150px;
}

table {
  width: 100%;
  border-collapse: collapse;
}

table th, table td {
  padding: 10px;
  border: 1px solid #ddd;
  text-align: left;
}

th {
  background-color: #f4f4f4;
}
</style>
