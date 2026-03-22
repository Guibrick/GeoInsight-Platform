<template>
  <div class="d-flex flex-column" style="height: 90%;">
    <ul class="list-group flex-grow-1 overflow-auto">
      <li v-for="station in stations" :key="station.name" class="list-group-item d-flex justify-content-between align-items-center">
        <span class="fw-bold">{{ station.name }}</span>
        <div class="d-flex gap-2">
          <span 
            class="badge"
            :class="{
              'badge-air': station.type === 'AIR',
              'badge-water': station.type === 'WATER',
              'badge-noise': station.type === 'NOISE'
            }"
          >
            {{ station.type }}
          </span>

          <span class="badge" :class="valueClass(station.value)">
            {{ station.value }}
          </span>
        </div>
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

const typeClass = (type: string) => {
  switch(type) {
    case 'AIR': return 'badge-air'
    case 'WATER': return 'badge-water'
    case 'NOISE': return 'badge-noise'
    default: return 'bg-secondary'
  }
}

const valueClass = (val: number) => {
  if (val < 4) return 'badge-value-low'
  if (val < 7) return 'badge-value-medium'
  return 'badge-value-high'
}

onMounted(async () => {
  await store.fetchStations()
  console.log('Stations loaded:', stations.value)
})
</script>