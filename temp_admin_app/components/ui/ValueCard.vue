<template>
  <div class="faceted-corner-tr p-8 rounded-2xl bg-surface-container-lowest border border-white/[0.08] hover:border-primary-container/40 hover:bg-brand-emerald-dark transition-all duration-300 group">
    <div class="w-12 h-12 rounded-lg bg-surface-container flex items-center justify-center text-primary-container mb-6 group-hover:scale-110 transition-transform">
      <span class="material-symbols-outlined text-[28px]">{{ icon }}</span>
    </div>
    <h3 class="font-title-lg text-title-lg text-white font-bold mb-3">{{ title }}</h3>
    <p class="font-body-md text-body-md text-on-surface-variant">{{ description }}</p>
  </div>
</template>

<script setup lang="ts">
defineProps<{
  icon: string
  title: string
  description: string
}>()
</script>