<template>
  <form @submit.prevent="submitStation">
    <input v-model="name" placeholder="Name" required />
    <input v-model.number="latitude" placeholder="Latitude" required />
    <input v-model.number="longitude" placeholder="Longitude" required />
    <input v-model="type" placeholder="Type" required />
    <input v-model="value" placeholder="Value" required />
    <button type="submit">Create Station</button>
  </form>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { createStation } from '../api/stations'
import type { Station } from '../types/station'
import { useStationStore } from '../store/stationStore'

const store = useStationStore()

const name = ref('')
const latitude = ref(0)
const longitude = ref(0)
const type = ref('')
const value = ref(0)

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
  alert('Station created!')
  await store.fetchStations()
}
</script>