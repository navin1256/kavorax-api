<template>
  <div v-if="showConsent" class="fixed bottom-0 left-0 w-full z-40 bg-surface-container-low/95 border-t border-outline-variant/40 px-4 py-3 backdrop-blur-lg flex flex-col sm:flex-row items-center justify-between gap-3 text-body-sm font-body-sm text-on-surface-variant">
    <div class="flex items-center gap-2">
      <span class="material-symbols-outlined text-primary text-base">lock</span>
      <span>This platform uses necessary cookies and ensures jurisdictional data residency compliance in accordance with GCC &amp; Indian DPDP laws.</span>
    </div>
    <div class="flex items-center gap-2 shrink-0">
      <button class="px-4 py-1.5 rounded bg-surface-container border border-outline-variant/50 text-on-surface hover:text-primary transition-colors text-label-sm font-label-sm" @click="dismissConsent">
        Preferences
      </button>
      <UiButtonCTA variant="primary" icon="arrow_forward" label="Accept & Proceed" @click="dismissConsent" />
    </div>
  </div>
  </div>
</template>
<script setup lang="ts">
import { ref } from 'vue'

const showConsent = ref(true)
const dismissConsent = () => {
  showConsent.value = false
}
</script>
