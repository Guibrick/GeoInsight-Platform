<template>
  <div class="d-flex flex-column" style="height: 90%;">
    <ul class="list-group flex-grow-1 overflow-auto">
      <li v-for="station in stations" :key="station.name" class="list-group-item d-flex justify-content-between align-items-center">
        <div>
          <strong>{{ station.name }}</strong>
          <div class="d-flex align-items-center gap-2 mt-1">
            <span class="badge" :class="typeBadgeClass(station.type)">{{ station.type }}</span>
            <span class="badge" :class="valueBadgeClass(station.value)">{{ station.value }}</span>
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script lang="ts" setup>
import { onMounted } from 'vue'
import { useStationStore } from '../store/stationStore'
import { storeToRefs } from 'pinia'

const store = useStationStore()
const { stations } = storeToRefs(store)

const stationTypes = ['AIR', 'WATER', 'NOISE']

const typeBadgeClass = (type: string) => {
  switch(type) {
    case 'AIR': return 'badge-air'
    case 'WATER': return 'badge-water'
    case 'NOISE': return 'badge-noise'
    default: return 'bg-secondary'
  }
}

const valueBadgeClass = (val: number) => {
  if (val < 4) return 'badge-value-low'
  if (val < 7) return 'badge-value-medium'
  return 'badge-value-high'
}

onMounted(() => {
  store.fetchStations()
})
</script>