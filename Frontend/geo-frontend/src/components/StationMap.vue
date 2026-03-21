<template>
  <div>
    <h2>Mapa de estaciones</h2>
    <div id="map" style="height: 400px;"></div>

    <div v-if="loading">Cargando estaciones...</div>
    <div v-if="error">{{ error }}</div>
  </div>
</template>

<script lang="ts" setup>
import { onMounted, ref } from 'vue'
import * as L from 'leaflet'
import 'leaflet/dist/leaflet.css'
import axios from 'axios'
import type { Station } from '../types/station'

// estado
const loading = ref(false)
const error = ref<string | null>(null)

onMounted(async () => {
  const map = L.map('map').setView([45.4642, 9.1900], 6)

  L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '© OpenStreetMap contributors'
  }).addTo(map)

  loading.value = true

  try {
    const res = await axios.get<Station[]>('/stations')
    const stations = res.data

    console.log('Stations:', stations)

    stations.forEach((station) => {
      if (station.latitude && station.longitude) {
        L.marker([station.latitude, station.longitude])
          .addTo(map)
          .bindPopup(`${station.name} - ${station.type}`)
      }
    })

  } catch (err: any) {
    console.error(err)
    error.value = 'Error cargando estaciones'
  } finally {
    loading.value = false
  }
})
</script>