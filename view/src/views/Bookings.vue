<template>
  <div>
    <h1>Bookings</h1>
    <form @submit.prevent="createBooking">
      <label for="userId">User ID:</label>
      <input v-model="booking.userId" id="userId" type="number" required />

      <label for="roomId">Room ID:</label>
      <input v-model="booking.roomId" id="roomId" type="number" required />

      <label for="inDate">Check-in Date:</label>
      <input v-model="booking.inDate" id="inDate" type="date" required />

      <label for="outDate">Check-out Date:</label>
      <input v-model="booking.outDate" id="outDate" type="date" required />

      <button type="submit">Create Booking</button>
    </form>

    <div v-if="bookings.length">
      <h2>Existing Bookings</h2>
      <ul>
        <li v-for="booking in bookings" :key="booking.id">
          <p><strong>User ID:</strong> {{ booking.userId }}</p>
          <p><strong>Room ID:</strong> {{ booking.roomId }}</p>
          <p><strong>Check-in Date:</strong> {{ booking.inDate }}</p>
          <p><strong>Check-out Date:</strong> {{ booking.outDate }}</p>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Bookings',
  data() {
    return {
      booking: {
        userId: '',
        roomId: '',
        inDate: '',
        outDate: ''
      },
      bookings: []
    };
  },
  async created() {
    this.fetchBookings();
  },
  methods: {
    async createBooking() {
      try {
        if (!this.booking.userId || !this.booking.roomId || !this.booking.inDate || !this.booking.outDate) {
          alert("All fields are required");
          return;
        }

        // Format dates as 'YYYY-MM-DDTHH:MM:SS' to match LocalDateTime format
        const formattedBooking = {
          ...this.booking,
          inDate: new Date(this.booking.inDate).toISOString().slice(0, 19),
          outDate: new Date(this.booking.outDate).toISOString().slice(0, 19)
        };

        await axios.post('http://localhost:8080/api/bookings/create', formattedBooking);
        alert('Booking created successfully');
        this.booking = { userId: '', roomId: '', inDate: '', outDate: '' };
        this.fetchBookings();
      } catch (error) {
        console.error('Error creating booking:', error);
      }
    }
  }

};
</script>

<style scoped>
/* Add styles here */
</style>
