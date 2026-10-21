<template>
  <div class="status-strip">
    <div v-for="(item, index) in items" :key="index" class="status-item">
      <span class="dot" :style="{ backgroundColor: item.color || 'var(--admin-success)' }"></span>
      <span class="label">{{ item.label }}</span>
      <span v-if="index < items.length - 1" class="separator">&middot;</span>
    </div>
  </div>
</template>

<script setup lang="ts">
defineProps<{
  items: Array<{ color?: string, label: string }>
}>()
</script>

<style scoped>
.status-strip {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 8px 32px;
  background-color: var(--admin-tier-1);
  border-bottom: 1px solid var(--admin-border);
  flex-wrap: wrap;
}
.status-item {
  display: flex;
  align-items: center;
  gap: 6px;
}
.dot {
  width: 8px;
  height: 8px;
  border-radius: 50%;
}
.label {
  font-size: 0.75rem;
  color: var(--admin-text-secondary);
  font-weight: 500;
}
.separator {
  color: var(--admin-border);
  margin-left: 6px;
}
</style>
