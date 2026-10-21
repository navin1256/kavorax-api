import { useStorage } from '@vueuse/core'

export const initialResources = []

export function useResources() {
  const resources = useStorage('kavorax-resources', initialResources)
  return { resources }
}
