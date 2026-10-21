import { useStorage } from '@vueuse/core'

export const initialCareers = [
  { id: '1', role: 'Frontend Developer', department: 'Engineering', location: 'Remote', type: 'Full-time', status: 'Open', applicants: 12, description: 'Vue/Nuxt developer needed.' },
  { id: '2', role: 'Sales Executive', department: 'Sales', location: 'Chennai', type: 'Full-time', status: 'Open', applicants: 5, description: 'Drive B2B sales.' },
]

export function useCareers() {
  const careers = useStorage('kavorax-careers', initialCareers)
  return { careers }
}
