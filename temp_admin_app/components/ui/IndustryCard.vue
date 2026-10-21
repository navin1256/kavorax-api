<template>
  <div class="relative group rounded-2xl overflow-hidden border border-white/10 bg-brand-surface h-80 flex flex-col justify-end p-6 hover:border-brand-green/50 transition-all duration-300">
    <div class="absolute inset-0 z-0">
      <NuxtImg
        :src="imageSrc"
        :alt="imageAlt"
        class="w-full h-full object-cover group-hover:scale-105 transition-transform duration-500 opacity-40"
        loading="lazy"
      />
      <div class="absolute inset-0 bg-gradient-to-t from-brand-black via-brand-black/60 to-transparent"></div>
    </div>
    <div class="relative z-10">
      <span class="text-brand-green text-xs font-label uppercase tracking-wider font-bold">{{ tag }}</span>
      <h3 class="text-xl font-display font-bold text-white mt-1">{{ title }}</h3>
      <p class="text-xs text-brand-muted-light mt-2 line-clamp-2">{{ description }}</p>
    </div>
  </div>
</template>

<script setup lang="ts">
defineProps<{
  tag: string
  title: string
  description: string
  imageSrc: string
  imageAlt: string
}>()
</script>
