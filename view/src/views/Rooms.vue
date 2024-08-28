<template>
  <div>
    <h1>Rooms</h1>
    <ul>
      <li v-for="room in rooms" :key="room.id">
        <p><strong>Room Number:</strong> {{ room.roomNumber }}</p>
        <p><strong>Price Group:</strong> {{ room.priceGroup }}</p>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Rooms',
  data() {
    return {
      rooms: []
    };
  },
  async created() {
    try {
      const response = await axios.get('/api/rooms/list/all');
      this.rooms = response.data;
    } catch (error) {
      console.error('Error fetching rooms:', error);
    }
  }
};
</script>

<style scoped>
/* Add styles here */
</style>
