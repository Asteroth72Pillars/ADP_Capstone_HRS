<template>
  <div class="card-container">
    <div class="help-center">
      <h1>Help Center</h1>
      <div class="filter-container">
        <select v-model="selectedCategory" class="filter-dropdown">
          <option value="All">Show All</option>
          <option v-for="category in categories" :key="category" :value="category">{{ category }}</option>
        </select>
      </div>
      <hr>
      <div v-if="filteredHelpCenters && filteredHelpCenters.length > 0">
        <div v-for="helpCenter in filteredHelpCenters" :key="helpCenter.id">
          <h3>{{ helpCenter.title }}</h3>
          <p>{{ helpCenter.content }}</p>
          <hr>
        </div>
      </div>
      <div v-else>
        <p>Loading help center content...</p>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'HelpCenter',
  data() {
    return {
      helpCenters: null,
      originalHelpCenters: null,
      selectedCategory: 'All',
      categories: []
    };
  },
  computed: {
    filteredHelpCenters() {
      if (this.selectedCategory === 'All') {
        return this.helpCenters;
      } else {
        return this.helpCenters.filter(entry => entry.category === this.selectedCategory);
      }
    }
  },
  mounted() {
    this.fetchHelpCenters();
  },
  methods: {
    fetchHelpCenters() {
      axios
          .get('http://localhost:8080/api/help-center/get-all')
          .then((response) => {
            this.helpCenters = response.data;
            this.originalHelpCenters = response.data;
            this.categories = [...new Set(response.data.map(entry => entry.category))];
          })
          .catch((error) => {
            console.error(error);
          });
    },
  }
};
</script>

<style scoped>
.card-container {
  width: 700px;
  margin: 0 auto;
  padding: 30px;
  background-color: #fdfefe; /* Soft White */
  border-radius: 10px;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
}

.help-center {
  width: 100%;
  height: 100%;
  background-color: #fdfefe; /* Soft White */
  padding: 30px;
  border-radius: 10px;
}

.help-center h1 {
  margin-bottom: 20px;
  text-align: center;
  color: #2c3e50; /* Navy Blue */
  font-size: 2em;
  font-weight: bold;
}

.filter-container {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 20px;
}

.filter-dropdown {
  font-size: 16px;
  padding: 8px;
  border-radius: 5px;
  border: 1px solid #2c3e50; /* Navy Blue */
  background-color: #fdfefe; /* Soft White */
  color: #2c3e50; /* Navy Blue */
}

.filter-dropdown:focus {
  outline: none;
  border-color: #f39c12; /* Gold */
}

.filter-dropdown option {
  background-color: #fdfefe; /* Soft White */
  color: #2c3e50; /* Navy Blue */
}

.filter-dropdown option:hover {
  background-color: #f39c12; /* Gold */
  color: #fdfefe; /* Soft White */
}

.help-center hr {
  border: none;
  border-top: 2px solid #f39c12; /* Gold */
  margin: 20px 0;
}

.help-center p, .help-center h3 {
  text-align: start;
  margin: 0;
}

.help-center h3 {
  font-size: 1.5em;
  margin-bottom: 10px;
  color: #2c3e50; /* Navy Blue */
}

.help-center p {
  font-size: 1.2em;
  color: #555; /* Dark Gray for text */
}
</style>
