<template>
  <div v-if="isVisible" class="fixed bottom-4 left-4 right-4 md:right-auto md:max-w-3xl z-[60] bg-[#0b0f14]/95 backdrop-blur-md border border-[rgba(43,55,52,0.65)] rounded-2xl p-5 flex flex-col md:flex-row items-start md:items-center justify-between gap-5 shadow-2xl">
    <p class="text-sm text-text-secondary leading-relaxed">
      We use cookies to enhance your browsing experience, analyze site traffic, and personalize content. By clicking "Accept All", you consent to our use of cookies.
    </p>
    <div class="flex flex-wrap items-center gap-3 shrink-0">
      <UiButtonCTA size="sm" variant="secondary" icon="" label="Reject All" @click="closeBanner" />
      <NuxtLink @click="closeBanner" to="/legal#cookie-pref" class="text-primary underline text-sm hover:text-[#34d399] transition-colors whitespace-nowrap px-2">Preferences</NuxtLink>
      <UiButtonCTA size="sm" variant="primary" icon="" label="Accept All" @click="acceptAll" />
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'

const isVisible = ref(false)

onMounted(() => {
  const hasConsented = localStorage.getItem('kavorax-cookie-consent')
  if (!hasConsented) {
    isVisible.value = true
  }
})

const acceptAll = () => {
  localStorage.setItem('kavorax-cookie-consent', 'accepted')
  isVisible.value = false
}

const closeBanner = () => {
  localStorage.setItem('kavorax-cookie-consent', 'rejected')
  isVisible.value = false
}
</script>
