<template>
  <Teleport to="body">
    <div class="toast-container">
      <TransitionGroup name="toast">
        <div v-for="toast in toasts" :key="toast.id" :class="['toast', `toast--${toast.type}`]">
          <span class="material-symbols-outlined icon">{{ toast.type === 'success' ? 'check_circle' : 'error' }}</span>
          <span class="message">{{ toast.message }}</span>
          <button class="close-btn" @click="removeToast(toast.id)">
            <span class="material-symbols-outlined">close</span>
          </button>
        </div>
      </TransitionGroup>
    </div>
  </Teleport>
</template>

<script setup lang="ts">
import { ref } from 'vue'

export interface Toast {
  id: number
  message: string
  type: 'success' | 'error'
}

const toasts = ref<Toast[]>([])
let nextId = 0

const addToast = (message: string, type: 'success' | 'error' = 'success') => {
  const id = nextId++
  toasts.value.push({ id, message, type })
  setTimeout(() => {
    removeToast(id)
  }, 3000)
}

const removeToast = (id: number) => {
  const index = toasts.value.findIndex(t => t.id === id)
  if (index > -1) toasts.value.splice(index, 1)
}

defineExpose({ addToast })
</script>

<style scoped>
.toast-container {
  position: fixed;
  bottom: 24px;
  right: 24px;
  display: flex;
  flex-direction: column;
  gap: 12px;
  z-index: 9999;
}

.toast {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 16px 20px;
  border-radius: 8px;
  background: var(--admin-tier-1);
  color: var(--admin-text-primary);
  border-left: 4px solid transparent;
  min-width: 300px;
}

.toast--success {
  border-left-color: var(--admin-success);
}
.toast--success .icon {
  color: var(--admin-success);
}

.toast--error {
  border-left-color: var(--admin-danger);
}
.toast--error .icon {
  color: var(--admin-danger);
}

.message {
  flex: 1;
  font-size: 0.9rem;
  font-weight: 500;
}

.close-btn {
  background: none;
  border: none;
  color: var(--admin-text-secondary);
  cursor: pointer;
  display: flex;
}
.close-btn:hover {
  color: var(--admin-text-primary);
}

.toast-enter-active,
.toast-leave-active {
  transition: all 0.3s ease;
}
.toast-enter-from {
  opacity: 0;
  transform: translateX(30px);
}
.toast-leave-to {
  opacity: 0;
  transform: translateY(30px);
}
</style>
