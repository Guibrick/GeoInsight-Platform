import { createApp } from 'vue'
import App from './App.vue'
import { createPinia } from 'pinia'
import 'leaflet/dist/leaflet.css'

const app = createApp(App)
app.use(createPinia())
app.mount('#app')