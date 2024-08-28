<template>
  <div class="card-container">
    <div class="form-container">
      <div>
        <div class="form-header">
          <h2>About Us</h2>
        </div>
        <hr />
        <div class="intro">
          <p>Welcome to <strong>[Eleve Hotel]</strong>, where elegance meets comfort. Nestled in the heart of <strong>[Capetown]</strong>, our boutique hotel offers a unique blend of modern luxury and classic charm. Whether you’re here for business or pleasure, we aim to provide an unforgettable experience tailored to your needs.</p>
          <p>At <strong>[Eleve Hotel]</strong>, we pride ourselves on our exceptional service and attention to detail. Our beautifully designed rooms, gourmet dining options, and personalized services are all curated to ensure that your stay with us is nothing short of perfect.</p>
        </div>
        <hr />
        <div class="profile-info" v-if="about">
          <p>
            <strong><i class="fa fa-map-marker"></i> Address:</strong> {{ about.address }}
          </p>
          <p>
            <strong><i class="fa fa-calendar"></i> Office Hours:</strong> {{ about.officeHours }}
          </p>
          <p>
            <strong><i class="fas fa-envelope"></i> Email:</strong> {{ about.email }}
          </p>
          <p>
            <strong><i class="fas fa-phone"></i> Telephone Number:</strong> {{ about.telephone }}
          </p>
          <p>
            <strong><i class="fa fa-mobile" aria-hidden="true"></i> WhatsApp Number:</strong> {{ about.whatsApp }}
          </p>
        </div>
        <div v-else>
          <p>Loading...</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'AboutUs',
  data() {
    return {
      about: {
        address: "123 Luxury Lane, Capetown, South Africa",
        officeHours: "Monday - Sunday: 8:00 AM - 10:00 PM",
        email: "contact@hotelname.com",
        telephone: "+123 456 7890",
        whatsApp: "+123 456 7890",
      },
    };
  },
  mounted() {
    this.fetchAboutUs();
    console.log('About Us component mounted');
  },
  methods: {
    fetchAboutUs() {
      const aboutId = this.$route.params.id || 1; // Default to ID 1 if not provided

      console.log('About ID:', aboutId);

      axios
          .get(`http://localhost:8080/api/aboutUs/read/${aboutId}`)
          .then((response) => {
            console.log(response.data);
            // Uncomment below if the API should replace the static data
            // this.about = response.data;
          })
          .catch((error) => {
            console.error(error);
          });
    },
    navigateToAboutUs() {
      // Use this.$router.push to navigate to About Us with id 1
      this.$router.push({ name: 'AboutUs', params: { id: 2 } });
    },
  },
};
</script>

<style scoped>
.card-container {
  width: 650px;
  margin: 0 auto;
  background-color: #fdfefe; /* Soft White */
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
}

.form-container {
  background-color: #ffffff; /* Soft White */
  padding: 30px;
  border-radius: 10px;
}

.form-header {
  text-align: center;
  margin-bottom: 20px;
  color: #f39c12; /* Gold */
}

.form-container hr {
  border: 1px solid #f39c12; /* Gold */
}

.intro {
  margin-bottom: 20px;
  color: #2c3e50; /* Navy Blue */
}

.profile-info p {
  font-size: 1.2em;
  color: #2c3e50; /* Navy Blue */
}

.profile-info strong {
  color: #f39c12; /* Gold */
}

.profile-info i {
  color: #f39c12; /* Gold */
}
</style>
