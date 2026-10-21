<template>
  <div class="relative z-10 flex flex-col items-center text-center">
    <div class="w-20 h-20 rounded-full bg-brand-emerald-dark border-2 border-brand-green flex items-center justify-center text-brand-green font-display text-xl font-bold shadow-[0_0_25px_rgba(8,232,136,0.3)] mb-6">
      {{ stepNumber }}
    </div>
    <h3 class="text-xl font-display font-semibold text-brand-text mb-2">{{ title }}</h3>
    <p class="text-sm text-brand-muted-light max-w-xs">
      {{ description }}
    </p>
  </div>
</template>

<script setup lang="ts">
defineProps<{
  stepNumber: string
  title: string
  description: string
}>()
</script>
