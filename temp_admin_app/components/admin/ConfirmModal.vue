<template>
  <AdminModal :modelValue="modelValue" @update:modelValue="$emit('update:modelValue', $event)" :title="title" maxWidth="400px">
    <div class="confirm-content">
      <p class="message">{{ message }}</p>
      <div class="modal-actions">
        <UiButtonCTA type="button" variant="secondary" @click="$emit('update:modelValue', false)">Cancel</UiButtonCTA>
        <UiButtonCTA type="button" variant="primary" customClass="btn-danger" @click="handleConfirm">Confirm Delete</UiButtonCTA>
      </div>
    </div>
  </AdminModal>
</template>

<script setup lang="ts">
defineProps<{
  modelValue: boolean
  title?: string
  message?: string
}>()

const emit = defineEmits(['update:modelValue', 'confirm'])

function handleConfirm() {
  emit('confirm')
  emit('update:modelValue', false)
}
</script>

<style scoped>
.confirm-content {
  display: flex;
  flex-direction: column;
}
.message {
  margin-bottom: 24px;
  color: var(--admin-text-secondary);
  font-size: 0.95rem;
  line-height: 1.5;
}
.modal-actions {
  display: flex;
  justify-content: flex-end;
  gap: 12px;
}
.btn-danger {
  background-color: var(--admin-danger) !important;
  color: white !important;
}
.btn-danger:hover {
  filter: brightness(1.1);
}
</style>

