<template>
  <div class="bg-brand-surface border border-white/[0.08] hover:border-brand-green/40 rounded-2xl p-8 transition-all duration-300 group hover:-translate-y-1">
    <div class="h-12 w-12 rounded-xl bg-white/5 border border-white/10 flex items-center justify-center text-brand-green group-hover:bg-brand-green group-hover:text-brand-black transition-colors mb-6">
      <span class="material-symbols-outlined text-2xl">{{ icon }}</span>
    </div>
    <h3 class="text-xl font-display font-semibold text-brand-text mb-3">{{ title }}</h3>
    <p class="text-sm text-brand-muted-light leading-relaxed">
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
