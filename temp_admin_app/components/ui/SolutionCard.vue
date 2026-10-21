<template>
  <div class="emerald-glow-card border border-white/10 hover:border-brand-green/50 rounded-2xl p-6 transition-all duration-300 flex flex-col justify-between group hover:shadow-[0_8px_32px_rgba(8,232,136,0.14)]">
    <div>
      <div class="w-10 h-10 rounded-lg bg-brand-green/10 border border-brand-green/30 flex items-center justify-center text-brand-green mb-5 group-hover:scale-110 transition-transform">
        <span class="material-symbols-outlined text-[22px]">{{ icon }}</span>
      </div>
      <h3 class="text-xl font-display font-semibold text-brand-text mb-2">{{ title }}</h3>
      <p class="text-sm text-brand-muted-light">{{ description }}</p>
    </div>
    <NuxtLink :to="href" class="text-brand-green font-label font-semibold text-sm mt-6 inline-flex items-center gap-1 group-hover:gap-2 transition-all">
      <span>{{ linkText }}</span>
      <span>→</span>
    </NuxtLink>
  </div>
</template>

<script setup lang="ts">
withDefaults(
  defineProps<{
    icon: string
    title: string
    description: string
    href?: string
    linkText?: string
  }>(),
  {
    href: '#product-showcase',
    linkText: 'Learn more'
  }
)
</script>
