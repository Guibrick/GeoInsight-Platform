import { createApp } from 'vue'
import App from './App.vue'
import { createPinia } from 'pinia'
import 'leaflet/dist/leaflet.css'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-icons/font/bootstrap-icons.css'
import './assets/styles.css'

const app = createApp(App)
app.use(createPinia())
app.mount('#app')