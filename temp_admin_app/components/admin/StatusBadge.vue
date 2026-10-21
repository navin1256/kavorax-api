<template>
  <span :class="['status-badge', badgeClass]">
    {{ status }}
  </span>
</template>

<script setup lang="ts">
import { computed } from 'vue'

const props = defineProps<{
  status: string
}>()

const badgeClass = computed(() => {
  const s = props.status.toLowerCase()
  if (s === 'new' || s === 'published' || s === 'active' || s === 'open') return 'badge-success'
  if (s === 'contacted' || s === 'pending') return 'badge-warning'
  if (s === 'closed' || s === 'draft') return 'badge-neutral'
  if (s === 'error' || s === 'overdue') return 'badge-danger'
  return 'badge-neutral'
})
</script>

<style scoped>
.status-badge {
  display: inline-flex;
  align-items: center;
  padding: 4px 10px;
  border-radius: 9999px;
  font-size: 0.75rem;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 0.05em;
}

.badge-success {
  background: rgba(16, 185, 129, 0.15);
  color: var(--admin-success);
  border: 1px solid rgba(16, 185, 129, 0.3);
}

.badge-warning {
  background: rgba(245, 158, 11, 0.15);
  color: var(--admin-warning);
  border: 1px solid rgba(245, 158, 11, 0.3);
}

.badge-danger {
  background: rgba(239, 68, 68, 0.15);
  color: var(--admin-danger);
  border: 1px solid rgba(239, 68, 68, 0.3);
}

.badge-neutral {
  background: rgba(153, 153, 153, 0.15);
  color: var(--admin-text-secondary);
  border: 1px solid rgba(153, 153, 153, 0.3);
}
</style>
