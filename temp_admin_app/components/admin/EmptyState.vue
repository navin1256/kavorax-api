<template>
  <div class="empty-state">
    <span class="material-symbols-outlined icon">{{ icon }}</span>
    <h3 class="title">{{ title }}</h3>
    <p class="desc">{{ description }}</p>
    <div class="action" v-if="$slots.action">
      <slot name="action" />
    </div>
  </div>
</template>

<script setup lang="ts">
defineProps({
  icon: { type: String, default: 'inbox' },
  title: { type: String, default: 'No data found' },
  description: { type: String, default: 'There is nothing to display here right now.' }
})
</script>

<style scoped>
.empty-state {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 64px 24px;
  text-align: center;
  background: rgba(16, 20, 25, 0.5);
  border: 1px dashed var(--admin-border);
  border-radius: 12px;
}

.icon {
  font-size: 48px;
  color: #666;
  margin-bottom: 16px;
}

.title {
  font-size: 1.25rem;
  font-weight: 600;
  color: var(--admin-text-primary);
  margin: 0 0 8px;
}

.desc {
  color: var(--admin-text-secondary);
  margin: 0 0 24px;
  max-width: 400px;
}

.action {
  margin-top: 16px;
}
</style>
