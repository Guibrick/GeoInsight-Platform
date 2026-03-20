<template>
  <div>
    <h2>Stations</h2>
    <ul>
      <li v-for="station in stations" :key="station.uniqueId">
        {{ station.name }} - {{ station.type }}
      </li>
    </ul>
    <div v-if="loading">Loading...</div>
    <div v-if="error">{{ error.message }}</div>
  </div>
</template>

<script lang="ts" setup>
import { onMounted } from 'vue'
import { useStationStore } from '../store/stationStore'

const store = useStationStore()

onMounted(async () => {
  await store.fetchStations()
  console.log('Stations loaded:', store.stations)
})

const { stations, loading, error } = store
</script>