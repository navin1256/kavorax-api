import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useAdminAuthStore = defineStore('adminAuth', () => {
  const isLoggedIn = ref(false)
  const token = ref<string | null>(null)
  const currentUser = ref<{ id: number; name: string; email: string; role: string; avatar: string } | null>(null)

  function login(apiToken: string, userData: { id: number; email: string; role: string }) {
    isLoggedIn.value = true
    token.value = apiToken
    currentUser.value = {
      id: userData.id,
      name: userData.email.split('@')[0], // Extract a name from email for the avatar
      email: userData.email,
      role: userData.role,
      avatar: `https://ui-avatars.com/api/?name=${userData.email.split('@')[0]}&background=10b981&color=fff`,
    }
    
    if (typeof window !== 'undefined') {
      localStorage.setItem('adminAuth', JSON.stringify({ 
        isLoggedIn: true, 
        token: apiToken, 
        currentUser: currentUser.value 
      }))
    }
  }

  function logout() {
    isLoggedIn.value = false
    token.value = null
    currentUser.value = null
    if (typeof window !== 'undefined') {
      localStorage.removeItem('adminAuth')
    }
  }

  function initAuth() {
    if (typeof window !== 'undefined') {
      const stored = localStorage.getItem('adminAuth')
      if (stored) {
        try {
          const parsed = JSON.parse(stored)
          if (parsed.isLoggedIn) {
            isLoggedIn.value = parsed.isLoggedIn
            token.value = parsed.token || null
            currentUser.value = parsed.currentUser
          }
        } catch (e) {
          // ignore
        }
      }
    }
  }

  return { isLoggedIn, token, currentUser, login, logout, initAuth }
})
