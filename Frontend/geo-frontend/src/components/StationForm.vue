<template>
  <form @submit.prevent="submitStation">
    <input v-model="name" placeholder="Name" required />
    <input v-model="type" placeholder="Type" required />
    <input v-model="value" placeholder="Value" required />
    <button type="submit">Create Station</button>
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