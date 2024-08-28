<template>
  <div class="card-container">
    <div class="faq">
      <h1>Frequently Asked Questions</h1>
      <hr>
      <div v-if="faqList" class="faq-list">
        <ul>
          <li v-for="faq in faqList" :key="faq.id" class="faq-item">
            <h3>{{ faq.question }}</h3>
            <p>{{ faq.answer }}</p>
            <hr>
          </li>
        </ul>
      </div>
      <div v-else>
        <p>Loading FAQs...</p>
      </div>
      <div class="button-container">
        <router-link class="back-button button" to="/help-center">Back to Help Center</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Faq',
  data() {
    return {
      faqList: null,
    };
  },
  mounted() {
    this.fetchFaqList();
  },
  methods: {
    fetchFaqList() {
      axios
          .get('http://localhost:8080/api/faq/get-all')
          .then((response) => {
            this.faqList = response.data;
          })
          .catch((error) => {
            console.error(error);
          });
    },
  },
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

.faq {
  width: 100%;
  background-color: #fdfefe; /* Soft White */
  padding: 30px;
  border-radius: 10px;
}

.faq h1 {
  margin-bottom: 20px;
  text-align: center;
  color: #2c3e50; /* Navy Blue */
  font-size: 2em;
  font-weight: bold;
}

.faq hr {
  border: none;
  border-top: 2px solid #f39c12; /* Gold */
  margin: 20px 0;
}

.faq-list ul {
  padding: 0;
  list-style: none;
}

.faq-item {
  margin-bottom: 30px;
}

.faq-item h3 {
  font-size: 1.5em;
  margin-bottom: 10px;
  color: #2c3e50; /* Navy Blue */
}

.faq-item p {
  font-size: 1.2em;
  color: #555; /* Dark Gray for text */
}

.back-button {
  display: block;
  text-align: center;
  margin-top: 20px;
  padding: 10px 20px;
  background-color: #f39c12; /* Gold */
  color: #fdfefe; /* Soft White */
  border-radius: 5px;
  text-decoration: none;
  font-weight: bold;
}

.back-button:hover {
  background-color: #d28e0e; /* Darker shade of Gold */
}

.faq-item h3, .faq-item p {
  text-align: left;
  margin: 0;
}
</style>
