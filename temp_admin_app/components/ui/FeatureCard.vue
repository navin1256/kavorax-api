<template>
  <div class="bg-brand-surface border border-white/[0.08] p-8 rounded-2xl relative overflow-hidden group hover:border-brand-green/40 transition-all">
    <div class="w-12 h-12 rounded-xl bg-brand-green/10 border border-brand-green/30 flex items-center justify-center text-brand-green mb-6">
      <span class="material-symbols-outlined text-2xl">{{ icon }}</span>
    </div>
    <h3 class="text-xl font-display font-semibold text-white mb-3">{{ title }}</h3>
    <p class="text-sm text-brand-muted-light">
      {{ description }}
    </p>
  </div>
</template>

<script setup lang="ts">
defineProps<{
  icon: string
  title: string
  description: string
}>()
</script>
