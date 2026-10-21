import { useAdminAuthStore } from '~/stores/adminAuth'
import { useRouter } from 'vue-router'

export function useApi() {
  const config = useRuntimeConfig()
  const authStore = useAdminAuthStore()
  const router = useRouter()

  const apiFetch = $fetch.create({
    baseURL: config.public.apiBase as string,
    onRequest({ request, options }) {
      if (authStore.token) {
        options.headers = options.headers || {}
        // @ts-ignore
        options.headers['Authorization'] = `Bearer ${authStore.token}`
      }
    },
    onResponseError({ response }) {
      if (response.status === 401 || response.status === 403) {
        // Token might be expired or invalid
        authStore.logout()
        router.push('/admin/login')
      }
    }
  })

  return { apiFetch }
}
