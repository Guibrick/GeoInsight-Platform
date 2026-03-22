<template>
  <div class="d-flex flex-column" style="height: 100%;">
    <h2 class="mb-3">Stations</h2>

    <ul class="list-group flex-grow-1 overflow-auto">
      <li 
        v-for="station in stations" 
        :key="station.name" 
        class="list-group-item d-flex justify-content-between align-items-center"
      >
        {{ station.name }}
        <span class="badge bg-primary rounded-pill">{{ station.type }}</span>
      </li>
    </ul>

    <div v-if="loading" class="mt-2">Loading...</div>
    <div v-if="error" class="mt-2 text-danger">{{ error.message }}</div>
  </div>
</template>

<script lang="ts" setup>
import { onMounted } from 'vue'
import { useStationStore } from '../store/stationStore'
import { storeToRefs } from 'pinia'

const store = useStationStore()
const { stations, loading, error } = storeToRefs(store)

onMounted(async () => {
  await store.fetchStations()
  console.log('Stations loaded:', stations.value)
})
</script>