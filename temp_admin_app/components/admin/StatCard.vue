<template>
  <div class="stat-card">
    <div class="stat-card__header">
      <span class="label">{{ label }}</span>
      <span class="material-symbols-outlined icon">{{ icon }}</span>
    </div>
    <div class="stat-card__value">{{ value }}</div>
    <div v-if="trend" :class="['stat-card__trend', trend > 0 ? 'trend-up' : 'trend-down']">
      <span class="material-symbols-outlined trend-icon">{{ trend > 0 ? 'arrow_upward' : 'arrow_downward' }}</span>
      {{ Math.abs(trend) }}%
      <span class="trend-text">vs last week</span>
    </div>
  </div>
</template>

<script setup lang="ts">
defineProps<{
  label: string
  value: string | number
  icon: string
  trend?: number
}>()
</script>

<style scoped>
.stat-card {
  background: var(--admin-tier-1);
  border: 1px solid var(--admin-border);
  border-radius: 12px;
  padding: 24px;
  display: flex;
  flex-direction: column;
  gap: 12px;
}
.stat-card__header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: var(--admin-text-secondary);
}
.label {
  font-size: 0.9rem;
  font-weight: 500;
}
.icon {
  font-size: 1.5rem;
  color: #34d399;
}
.stat-card__value {
  font-size: 2rem;
  font-weight: 700;
  color: var(--admin-text-primary);
}
.stat-card__trend {
  display: flex;
  align-items: center;
  gap: 4px;
  font-size: 0.85rem;
  font-weight: 600;
}
.trend-up {
  color: var(--admin-success);
}
.trend-down {
  color: var(--admin-danger);
}
.trend-icon {
  font-size: 1rem;
}
.trend-text {
  color: #666;
  font-weight: 400;
  margin-left: 4px;
}
</style>
