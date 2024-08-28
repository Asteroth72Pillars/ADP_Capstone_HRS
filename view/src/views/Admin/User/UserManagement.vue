<template>
  <div class="content-container">
    <div class="content-header">
      <h1><i class="fas fa-users"></i> User Management</h1>
      <div class="search-bar-container">
        <div class="search-bar">
          <div class="search-input">
            <input v-model="searchQuery" placeholder="Search..." type="text" />
            <button class="read-button button" @click="resetSearch">
              <i class="fas fa-search"></i> Reset
            </button>
          </div>
          <router-link class="add-button button" to="/admin/users/create">
            <i class="fas fa-user"></i> Add User
          </router-link>
        </div>
      </div>
    </div>
    <table>
      <thead>
      <tr>
        <th @click="sortUsers('id')">ID <i class="fas fa-sort"></i></th>
        <th @click="sortUsers('firstName')">First Name <i class="fas fa-sort"></i></th>
        <th @click="sortUsers('lastName')">Last Name <i class="fas fa-sort"></i></th>
        <th @click="sortUsers('email')">Email <i class="fas fa-sort"></i></th>
        <th class="actions-column">Actions</th>
      </tr>
      </thead>
      <tbody v-if="!loading">
      <tr v-for="user in sortedUsers" :key="user.id">
        <td v-if="!user.editing">{{ user.id }}</td>
        <td v-else>
          <input v-model="user.id" disabled type="text" />
        </td>

        <!-- First Name -->
        <td v-if="!user.editing">{{ user.firstName }}</td>
        <td v-else>
          <input v-model="user.firstName" type="text" />
        </td>

        <!-- Last Name -->
        <td v-if="!user.editing">{{ user.lastName }}</td>
        <td v-else>
          <input v-model="user.lastName" type="text" />
        </td>

        <!-- Email -->
        <td v-if="!user.editing">{{ user.email }}</td>
        <td v-else>
          <input v-model="user.email" type="text" />
        </td>

        <!-- Actions -->
        <td>
          <template v-if="!user.editing">
            <button class="delete-button button" @click="deleteUser(user)">
              <i class="fas fa-trash"></i> Delete
            </button>
            <button class="update-button button" @click="editUser(user)">
              <i class="fas fa-edit"></i> Edit
            </button>
            <button class="read-button button" @click="openUserView(user.id)">
              <i class="fas fa-eye"></i> Read
            </button>
          </template>
          <template v-else>
            <button class="update-button button" @click="saveUser(user)">
              <i class="fas fa-save"></i> Save
            </button>
            <button class="delete-button button" @click="cancelEdit(user)">
              <i class="fas fa-times"></i> Cancel
            </button>
          </template>
        </td>
      </tr>
      </tbody>
    </table>
    <div v-if="loading" class="loading">Loading...</div>
  </div>
</template>

<script>
import axios from "axios";

axios.defaults.baseURL = 'http://localhost:8080/api/user';

export default {
  name: "UsersManagement",
  data() {
    return {
      users: [],
      sortedUsers: [],
      searchQuery: "",
      loading: false,
      showConfirmationModal: false,
      userToDeleteId: null,
      successModal: {
        show: false,
        message: "",
      },
      failModal: {
        show: false,
        message: "",
      },
    };
  },
  methods: {
    getUsers() {
      this.loading = true;
      axios.get('/api/admin/users/list/all')
          .then((response) => {
            this.users = response.data;
            this.sortedUsers = response.data;
            this.loading = false;
          })
          .catch((error) => {
            this.loading = false;
            this.showFailureModal("Failed to fetch users. Please try again.");
          });
    },
    sortUsers(sortKey) {
      this.sortedUsers = this.sortedUsers.sort((a, b) => {
        if (a[sortKey] < b[sortKey]) return -1;
        if (a[sortKey] > b[sortKey]) return 1;
        return 0;
      });
    },
    deleteUser(user) {
      this.userToDeleteId = user;
      this.showConfirmationModal = true;
    },
    confirmDeleteUser() {
      if (this.userToDeleteId) {
        const userId = this.userToDeleteId.id;
        this.loading = true;
        axios
            .delete(`http://localhost:8080/api/admin/users/delete/${userId}`)
            .then(() => {
              this.showSuccessModal("User deleted successfully.");
              this.getUsers();
            })
            .catch((error) => {
              this.loading = false;
              this.showFailureModal("Failed to delete user. Please try again.");
            });
      }
      this.userToDeleteId = null;
      this.showConfirmationModal = false;
    },
    cancelDeleteUser() {
      this.userToDeleteId = null;
      this.showConfirmationModal = false;
    },
    editUser(user) {
      this.$router.push({ name: 'updateUser', params: { id: user.id } });
    },
    saveUser(user) {
      user.editing = false;
      this.loading = true;
      axios
          .put(`http://localhost:8080/api/admin/users/update/${user.id}`, user)
          .then(() => {
            this.showSuccessModal("User updated successfully.");
            this.getUsers();
          })
          .catch((error) => {
            this.loading = false;
            this.showFailureModal("Failed to update user. Please try again.");
          });
    },
    cancelEdit(user) {
      user.editing = false;
    },
    openUserView(userId) {
      this.$router.push(`/admin/users/read/${userId}`);
    },
    resetSearch() {
      this.searchQuery = "";
      this.sortedUsers = this.users;
    },
    showSuccessModal(message) {
      this.successModal = {
        show: true,
        message: message,
      };
    },
    showFailureModal(message) {
      this.failModal = {
        show: true,
        message: message,
      };
    },
  },
  computed: {
    filteredUsers() {
      if (!this.searchQuery) {
        return this.sortedUsers;
      }
      return this.sortedUsers.filter((user) => {
        return (
            user.id.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
            user.userName.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
            user.firstName.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
            user.lastName.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
            user.phoneNumber.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
            user.email.toLowerCase().includes(this.searchQuery.toLowerCase())
        );
      });
    }
  },
  created() {
    this.getUsers();
  }
};
</script>

<style scoped>
.content-container {
  width: 100%;
}

.content-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}

.search-bar-container {
  display: flex;
  align-items: center;
}

.search-bar {
  display: flex;
  align-items: center;
}

.search-input {
  display: flex;
  align-items: center;
  margin-right: 20px;
}

.search-input input {
  padding: 5px;
  font-size: 16px;
}

.button {
  padding: 5px 10px;
  margin: 0 5px;
}

.read-button {
  background-color: #4CAF50;
  color: white;
}

.add-button {
  background-color: #2196F3;
  color: white;
}

.update-button {
  background-color: #FFC107;
  color: white;
}

.delete-button {
  background-color: #F44336;
  color: white;
}

.loading {
  text-align: center;
  font-size: 18px;
}

.actions-column {
  width: 150px;
}

table {
  width: 100%;
  border-collapse: collapse;
}

table th, table td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

table th {
  background-color: #f2f2f2;
}

table td input {
  border: 1px solid #ddd;
  padding: 4px;
}

.table-actions {
  display: flex;
  justify-content: space-between;
}

.table-actions button {
  margin: 0 5px;
}
</style>
