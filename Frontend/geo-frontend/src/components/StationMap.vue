<template>
  <div id="map" style="height: 400px;"></div>
</template>

<script lang="ts" setup>
import { onMounted } from 'vue'
import * as L from 'leaflet'
import 'leaflet/dist/leaflet.css'

onMounted(() => {
  const map = L.map('map').setView([45.4642, 9.1900], 13)

  L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '© OpenStreetMap contributors'
  }).addTo(map)


  L.marker([45.4642, 9.1900]).addTo(map).bindPopup('Milano')
})
</script>