<template>
  <div :class="['pointer-events-auto clip-chamfer-chip bg-surface-container-low/90 backdrop-blur-md border border-outline-variant/50 p-4 text-left hover:border-primary-container hover:bg-primary-container/10 transition-all duration-200', extraClass]">
    <div class="w-8 h-8 rounded bg-brand-emerald-dark text-primary flex items-center justify-center mb-2">
      <span class="material-symbols-outlined text-[18px]">{{ icon }}</span>
    </div>
    <div class="text-title-sm font-title-sm text-on-surface">{{ title }}</div>
    <div class="text-body-sm font-body-sm text-[#A1A1AA]">{{ description }}</div>
  </div>
</template>

<script setup lang="ts">
defineProps<{
  icon: string
  title: string
  description: string
  extraClass?: string
}>()
</script>