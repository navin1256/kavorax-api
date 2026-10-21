import { useStorage } from '@vueuse/core'

export const initialTeam = [
  { id: '1', name: 'Kavin M', role: 'CEO', email: 'kavin@kavorax.com', bio: 'Visionary leader with 10+ years in retail tech.', lastActive: '2 mins ago', status: 'Active', avatar: '/images/about-ceo.jpg' },
  { id: '2', name: 'Suganya M', role: 'CFO', email: 'suganya@kavorax.com', bio: 'Financial strategist ensuring sustainable growth.', lastActive: '1 hr ago', status: 'Active', avatar: '/images/about-cfo.jpg' },
  { id: '3', name: 'Neha Gupta', role: 'Marketing Manager', email: 'neha@kavorax.com', bio: 'Driving brand awareness and user acquisition.', lastActive: '1 day ago', status: 'Active', avatar: '/images/admin_portrait_1_1790923945481.jpg' },
]

export function useTeam() {
  const team = useStorage('kavorax-team', initialTeam)
  return { team }
}
