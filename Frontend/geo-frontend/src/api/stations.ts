import axios from 'axios'
import type { Station } from '../types/station'

const api = axios.create({ baseURL: '/stations', timeout: 5000 })

export const getAllStations = () => api.get<Station[]>('')
export const getStationById = (id: string) => api.get<Station>(`${id}`)
export const createStation = (station: Station) => api.post<Station>('', station)