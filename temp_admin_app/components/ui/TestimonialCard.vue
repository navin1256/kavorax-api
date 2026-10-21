<template>
  <div class="bg-brand-surface border border-white/10 rounded-2xl p-8 flex flex-col justify-between hover:border-brand-green/40 transition-all">
    <div class="space-y-4">
      <div class="flex text-brand-green gap-1">
        <span
          v-for="i in rating"
          :key="i"
          class="material-symbols-outlined text-[18px]"
          data-weight="fill"
        >
          star
        </span>
      </div>
      <p class="text-sm text-brand-text leading-relaxed italic">
        "{{ quote }}"
      </p>
    </div>
    <div class="pt-6 border-t border-white/10 mt-6 flex items-center gap-3">
      <div class="w-10 h-10 rounded-full bg-brand-green/20 flex items-center justify-center text-brand-green font-bold">
        {{ authorInitials }}
      </div>
      <div>
        <div class="text-sm font-bold text-white">{{ authorName }}</div>
        <div class="text-xs text-brand-muted">{{ authorRole }}</div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
withDefaults(
  defineProps<{
    quote: string
    authorName: string
    authorRole: string
    authorInitials: string
    rating?: number
  }>(),
  {
    rating: 5
  }
)
</script>
