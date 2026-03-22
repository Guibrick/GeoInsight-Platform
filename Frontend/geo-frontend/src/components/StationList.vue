<template>
  <div>
    <h2>Stations</h2>
      <ul class="list-group">
        <li v-for="station in stations" :key="station.name" class="list-group-item d-flex justify-content-between align-items-center">
          {{ station.name }} <span class="badge bg-primary rounded-pill">{{ station.type }}</span>
        </li>
      </ul>

    <div v-if="loading">Loading...</div>
    <div v-if="error">{{ error.message }}</div>
  </div>
</template>

<script lang="ts" setup>
import { onMounted } from 'vue'
import { useStationStore } from '../store/stationStore'
import { storeToRefs } from 'pinia'

const store = useStationStore()
const { stations, loading, error } = storeToRefs(store) // <-- esto es clave

onMounted(async () => {
  await store.fetchStations()
  console.log('Stations loaded:', stations.value)
})
</script>