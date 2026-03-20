<template>
  <form @submit.prevent="submitStation">
    <input v-model="name" placeholder="Name" required />
    <input v-model="type" placeholder="Type" required />
    <input v-model.number="lat" placeholder="Latitude" required />
    <input v-model.number="lon" placeholder="Longitude" required />
    <button type="submit">Create Station</button>
  </form>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { createStation } from '../api/stations'
import type { Station } from '../types/station'

const name = ref('')
const type = ref('')
const lat = ref(0)
const lon = ref(0)

const submitStation = async () => {
  const newStation: Station = {
    uniqueId: crypto.randomUUID(),
    name: name.value,
    type: type.value,
    lat: lat.value,
    lon: lon.value,
  }
  await createStation(newStation)
  name.value = type.value = ''
  lat.value = lon.value = 0
  alert('Station created!')
}
</script>