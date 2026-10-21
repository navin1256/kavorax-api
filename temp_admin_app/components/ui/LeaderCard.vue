<template>
  <div class="faceted-corner-tr p-8 rounded-2xl bg-surface-container-lowest/80 border border-white/[0.08] hover:border-primary-container/40 transition-all duration-300 relative group overflow-hidden">
    <div class="flex flex-col sm:flex-row items-center sm:items-start gap-6">
      <div class="relative w-32 h-32 rounded-xl overflow-hidden shrink-0 border border-primary-container/30 bg-brand-emerald-dark">
        <NuxtImg :src="imageSrc" :alt="imageAlt" class="w-full h-full object-cover grayscale contrast-125 mix-blend-luminosity" />
        <div class="absolute inset-0 bg-primary-container/15 mix-blend-color"></div>
      </div>
      <div class="space-y-3 text-center sm:text-left flex-1">
        <div class="flex flex-col sm:flex-row sm:items-center justify-between gap-2">
          <div>
            <h3 class="font-headline-sm text-headline-sm text-white font-bold">{{ name }}</h3>
            <p class="font-title-sm text-title-sm text-primary">{{ role }}</p>
          </div>
          <NuxtLink :to="socialLink" target="_blank" rel="noopener" class="w-8 h-8 rounded-lg bg-surface-container flex items-center justify-center text-on-surface-variant hover:text-primary transition-colors self-center sm:self-auto" aria-label="LinkedIn Profile">
            <span class="material-symbols-outlined text-[18px]">share</span>
          </NuxtLink>
        </div>
        <div class="inline-block px-2.5 py-0.5 rounded-full bg-primary-container/10 border border-primary-container/30 text-primary-container font-label-sm text-label-sm">
          {{ badge }}
        </div>
        <p class="font-body-md text-body-md text-on-surface-variant leading-relaxed">
          {{ description }}
        </p>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
defineProps<{
  name: string
  role: string
  badge: string
  description: string
  imageSrc: string
  imageAlt: string
  socialLink: string
}>()
</script>