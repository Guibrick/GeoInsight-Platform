<template>
  <form @submit.prevent="submitStation">
    <input 
      v-model="name" 
      placeholder="Name" 
      required 
      class="form-control mb-2" 
    />

    <select 
      v-model="type" 
      required 
      class="form-select mb-2"
    >
      <option value="" disabled>Select Station Type</option>
      <option v-for="option in stationTypes" :key="option" :value="option">
        {{ option }}
      </option>
    </select>

<div class="mb-3">
  <label for="valueSlider" class="form-label">Value: {{ value }}</label>
  <input
    type="range"
    v-model.number="value"
    min="0"
    max="10"
    step="0.5"
    class="form-range custom-slider"
  />

  <div class="d-flex justify-content-between mt-1">
    <span>0</span>
    <span>5</span>
    <span>10</span>
  </div>
</div>

    <button type="submit" class="btn btn-primary w-100">Create Station</button>
  </form>
</template>

<script lang="ts" setup>
import { ref, watch } from 'vue'
import { createStation } from '../api/stations'
import type { Station } from '../types/station'
import { useStationStore } from '../store/stationStore'
import { storeToRefs } from 'pinia'

const store = useStationStore()
const { stations } = storeToRefs(store)

const name = ref('')
const latitude = ref(0)
const longitude = ref(0)
const type = ref('')
const value = ref(0)

const stationTypes = ['AIR', 'WATER', 'NOISE']

const fetchCoordinates = async (city: string) => {
  if (!city) return { lat: 0, lon: 0 }

  try {
    const response = await fetch(
      `https://nominatim.openstreetmap.org/search?format=json&q=${encodeURIComponent(city)}`
    )
    const data = await response.json()
    if (data.length === 0) return { lat: 0, lon: 0 }

    return {
      lat: parseFloat(data[0].lat),
      lon: parseFloat(data[0].lon)
    }
  } catch (error) {
    console.error('Error fetching coordinates:', error)
    return { lat: 0, lon: 0 }
  }
}

watch(name, async (newName) => {
  if (!newName) return
  const coords = await fetchCoordinates(newName)
  latitude.value = coords.lat
  longitude.value = coords.lon
})

const submitStation = async () => {
  const newStation: Station = {
    name: name.value,
    latitude: latitude.value,
    longitude: longitude.value,
    type: type.value,
    value: value.value
  }

  await createStation(newStation)

  name.value = type.value = ''
  latitude.value = longitude.value = value.value = 0

  await store.fetchStations()
}
</script>