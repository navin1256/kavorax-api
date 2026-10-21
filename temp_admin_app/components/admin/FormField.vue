<template>
  <div class="form-field">
    <label v-if="label" class="label">{{ label }}</label>
    <div class="input-wrapper">
      <slot>
        <input 
          v-if="type !== 'textarea' && type !== 'select'" 
          :type="type" 
          :value="modelValue" 
          @input="$emit('update:modelValue', ($event.target as HTMLInputElement).value)" 
          class="input" 
          :placeholder="placeholder"
        />
        <textarea 
          v-else-if="type === 'textarea'"
          :value="modelValue" 
          @input="$emit('update:modelValue', ($event.target as HTMLTextAreaElement).value)" 
          class="input textarea" 
          :placeholder="placeholder"
          rows="4"
        ></textarea>
        <select 
          v-else-if="type === 'select'"
          :value="modelValue"
          @change="$emit('update:modelValue', ($event.target as HTMLSelectElement).value)"
          class="input select"
        >
          <option v-for="opt in options" :key="opt.value" :value="opt.value">{{ opt.label }}</option>
        </select>
      </slot>
    </div>
    <span v-if="error" class="error-msg">{{ error }}</span>
  </div>
</template>

<script setup lang="ts">
defineProps<{
  label?: string
  modelValue?: string | number
  type?: 'text' | 'email' | 'password' | 'textarea' | 'select' | 'number'
  placeholder?: string
  error?: string
  options?: { label: string, value: string | number }[]
}>()
defineEmits(['update:modelValue'])
</script>

<style scoped>
.form-field {
  display: flex;
  flex-direction: column;
  gap: 8px;
  margin-bottom: 16px;
}
.label {
  font-size: 0.85rem;
  font-weight: 600;
  color: #e0e2ea;
}
.input {
  width: 100%;
  background: var(--admin-tier-1);
  border: 1px solid var(--admin-border);
  border-radius: 8px;
  padding: 12px 16px;
  color: var(--admin-text-primary);
  font-family: inherit;
  font-size: 0.95rem;
  transition: all 0.2s;
}
.input:focus {
  outline: none;
  border-color: var(--admin-success);
}
.textarea {
  resize: vertical;
}
.select {
  appearance: none;
  background-image: url('data:image/svg+xml;charset=US-ASCII,<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="%23999999" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><polyline points="6 9 12 15 18 9"></polyline></svg>');
  background-repeat: no-repeat;
  background-position: right 12px center;
  background-size: 16px;
  padding-right: 40px;
}
.error-msg {
  color: var(--admin-danger);
  font-size: 0.8rem;
  margin-top: 4px;
}
</style>
