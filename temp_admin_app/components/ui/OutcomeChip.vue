<template>
  <NuxtLink :to="href" class="inline-block bg-[#080c10] border border-[rgba(43,55,52,0.65)] rounded-full px-4 py-2 hover:border-[#10b981] transition-colors">
    <div class="flex items-center gap-2 font-sans-ui">
      <span class="text-xs text-[#999999] font-medium">{{ step }} / {{ category }}</span>
      <span class="text-sm text-white font-semibold">{{ title }}</span>
    </div>
  </NuxtLink>
</template>

<script setup lang="ts">
defineProps<{
  href: string
  step: string
  category: string
  title: string
}>()
</script>
