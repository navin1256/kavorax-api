<template>
  <div class="relative p-8 rounded-2xl bg-surface-container-lowest border border-outline-variant/30 space-y-4 hover:border-primary-container/50 transition-all">
    <div class="flex items-center justify-between">
      <span :class="['font-label-sm text-label-sm uppercase font-bold', colorClass]">{{ period }}</span>
      <span :class="['w-3 h-3 rounded-full', bgClass, shadowClass]"></span>
    </div>
    <h3 class="font-title-lg text-title-lg text-white font-bold">{{ title }}</h3>
    <p class="font-body-md text-body-md text-on-surface-variant">
      {{ description }}
    </p>
    <div class="pt-4 border-t border-outline-variant/20 flex items-center gap-2 text-outline font-label-sm text-label-sm">
      <span :class="['material-symbols-outlined text-[16px]', iconColorClass]">{{ statusIcon }}</span>
      <span>{{ statusText }}</span>
    </div>
  </div>
</template>

<script setup lang="ts">
import { computed } from 'vue'

const props = defineProps<{
  period: string
  title: string
  description: string
  statusIcon: string
  statusText: string
  themeColor: 'primary' | 'secondary' | 'outline'
}>()

const colorClass = computed(() => {
  if (props.themeColor === 'primary') return 'text-primary-container'
  if (props.themeColor === 'secondary') return 'text-secondary'
  return 'text-outline'
})

const bgClass = computed(() => {
  if (props.themeColor === 'primary') return 'bg-primary-container'
  if (props.themeColor === 'secondary') return 'bg-secondary'
  return 'bg-outline'
})

const shadowClass = computed(() => {
  if (props.themeColor === 'primary') return 'shadow-[0_0_8px_var(--color-brand-green)]'
  return ''
})

const iconColorClass = computed(() => {
  if (props.themeColor === 'primary') return 'text-primary'
  if (props.themeColor === 'secondary') return 'text-secondary'
  return 'text-outline'
})
</script>