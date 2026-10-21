export default defineNuxtRouteMiddleware((to, from) => {
  if (process.server) return;
  const authStore = useAdminAuthStore()
  authStore.initAuth()
  if (!authStore.isLoggedIn && to.path !== '/admin/login') {
    return navigateTo('/admin/login')
  }
})
