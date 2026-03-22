<template>
  <div>
    <div id="map" style="height: 750px;"></div>

    <div v-if="loading">Loading stations...</div>
    <div v-if="error">{{ error }}</div>

    <div
      v-if="modalVisible"
      class="modal-overlay"
      @click.self="modalVisible = false"
    >
      <div
        class="modal-card"
        :style="{
          backgroundColor: selectedStation ? valueColor(selectedStation.value) : '#fff',
          border: selectedStation ? '2px solid ' + typeColor(selectedStation.type) : '2px solid #ccc'
        }"
      >
        <h5>{{ selectedStation?.name }}</h5>
        <p>{{ selectedStation?.type }}</p>
        <p>{{ selectedStation?.value }}</p>
      </div>
    </div>
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

const map = ref<any | null>(null)
const selectedStation = ref<any | null>(null)
const modalVisible = ref(false)

const typeColor = (type: string) => {
  switch(type) {
    case 'AIR': return '#FFA07A'
    case 'WATER': return '#87CEFA'
    case 'NOISE': return '#D8BFD8'
    default: return '#AAA'
  }
}

const valueColor = (val: number) => {
  if (val < 4) return '#A8E6CF'
  if (val < 7) return '#FFD3B6'
  return '#FF8B94'
}

const createIcon = (type: string, value: number) => {
  const border = typeColor(type)
  const fill = valueColor(value)
  const html = `
    <div style="
      width: 30px;
      height: 30px;
      border-radius: 50%;
      background-color: ${fill};
      border: 4px solid ${border};
      cursor: pointer;
      pointer-events: auto;
      box-sizing: border-box;
      transition: transform 0.2s ease;
    "></div>
  `
  return L.divIcon({ html, className: '', iconSize: [30, 30], iconAnchor: [15, 15], popupAnchor: [0, -20] })
}

onMounted(() => {
  map.value = L.map('map').setView([45.4642, 9.1900], 6)

  L.tileLayer('https://{s}.basemaps.cartocdn.com/light_all/{z}/{x}/{y}{r}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors &copy; <a href="https://carto.com/">CARTO</a>',
    subdomains: 'abcd',
    maxZoom: 19
  }).addTo(map.value)
})

watchEffect(() => {
  if (!map.value) return

  map.value.eachLayer((layer: any) => {
    if (layer._popup) map.value.removeLayer(layer)
  })

  stations.value.forEach((station) => {
    if (station.latitude && station.longitude) {
      const marker = L.marker([station.latitude, station.longitude], {
        icon: createIcon(station.type, station.value)
      }).addTo(map.value)

      marker.on('click', () => {
        selectedStation.value = station
        modalVisible.value = true
      })
    }
  })
})
</script>