import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/Home.vue';
import AboutUs from '@/views/AboutUs.vue';
import Bookings from '@/views/Bookings.vue';
import ContactUs from '@/views/ContactUs.vue';
import Faqs from '@/views/Faqs.vue';
import HelpDesk from '@/views/HelpDesk.vue';
import Login from '@/views/Login.vue';
import Profile from '@/views/Profile.vue';
import Register from '@/views/Register.vue';
import Signout from '@/views/Signout.vue';
import UserProfile from '@/views/UserProfile.vue';
import AdminHome from '@/views/Admin/AdminHome.vue';
import UserManagement from '@/views/Admin/User/UserManagement.vue';
import BookingManagement from '@/views/Admin/Booking/BookingManagement.vue';
import RoomManagement from '@/views/Admin/Room/RoomManagment.vue';
import CreateUser from '@/views/Admin/User/CreateUser.vue';
import CreateRoom from '@/views/Admin/Room/CreateRoom.vue';
import CreateBooking from '@/views/Admin/Booking/CreateBooking.vue';
import UpdateUser from '@/views/Admin/User/UpdateUser.vue';
const routes = [
    { path: '/', component: Home },
    { path: '/about-us', component: AboutUs },
    { path: '/bookings', component: Bookings },
    { path: '/contact-us', component: ContactUs },
    { path: '/faqs', component: Faqs },
    { path: '/help-desk', component: HelpDesk },
    { path: '/login', component: Login },
    { path: '/profile', component: Profile },
    { path: '/register', component: Register },
    { path: '/signout', component: Signout },
    { path: '/user-profile', component: UserProfile },
    {
        path: '/profile/:userId',
        name: 'UserProfile',
        component: UserProfile,
        props: true
    },
    {
        path: '/admin-home',
        name: 'AdminHome',
        component: AdminHome
    },
    {
        path: '/admin/user-management',
        name: 'UserManagement',
        component: UserManagement
    },
    {
        path: '/admin/booking-management',
        name: 'BookingManagement',
        component: BookingManagement
    },
    {
        path: '/admin/room-management',
        name: 'RoomManagement',
        component: RoomManagement
    },
    {
        path: '/admin/users/create',
        name: 'CreateUser',
        component: CreateUser, // The component for adding a user
    },
    {
        path: '/admin/rooms/create',
        name: 'CreateRoom',
        component: CreateRoom, // The component for adding a room
    },
    {
        path: '/admin/bookings/create',
        name: 'CreateBooking',
        component: CreateBooking, // The component for adding a booking
    },
    {
        path: '/rooms',
        name: 'RoomManagement',
        component: RoomManagement
    },
    {
        path: '/admin/users/update/:id',
        name: 'updateUser',
        component: UpdateUser,
        props: true
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
