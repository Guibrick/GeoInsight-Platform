import { defineStore } from 'pinia'
import { ref } from 'vue'
import type { Station } from '../types/station'
import { getAllStations } from '../api/stations'

export const useStationStore = defineStore('stations', () => {
  const stations = ref<Station[]>([])
  const loading = ref(false)
  const error = ref<Error | null>(null)

  const fetchStations = async () => {
    loading.value = true
    error.value = null
    try {
      const response = await getAllStations()
      stations.value = response.data
    } catch (err: any) {
      error.value = err
    } finally {
      loading.value = false
    }
  }

  return { stations, loading, error, fetchStations }
})