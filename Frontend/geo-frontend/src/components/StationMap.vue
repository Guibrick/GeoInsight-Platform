<template>
  <div>
    <div id="map" style="height: 700px;"></div>

    <div v-if="loading">Loading stations...</div>
    <div v-if="error">{{ error }}</div>
  </div>
</template>

<script lang="ts" setup>
import { onMounted, ref, watchEffect } from 'vue'
import * as L from 'leaflet'
import 'leaflet/dist/leaflet.css'
import { useStationStore } from '../store/stationStore'
import { storeToRefs } from 'pinia'

const store = useStationStore()
const { stations, loading, error } = storeToRefs(store)

const map = ref<L.Map>()

onMounted(() => {
  map.value = L.map('map').setView([45.4642, 9.1900], 6)

  L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '© OpenStreetMap contributors'
  }).addTo(map.value)

  store.fetchStations()
})

watchEffect(() => {
  if (!map.value) return

  map.value.eachLayer((layer) => {
    if ((layer as any)._popup) map.value?.removeLayer(layer)
  })

  stations.value.forEach((station) => {
    if (station.latitude && station.longitude) {
      L.marker([station.latitude, station.longitude])
        .addTo(map.value!)
        .bindPopup(`${station.name} - ${station.type}`)
    }
  })
})
</script>