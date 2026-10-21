<template>
  <NuxtLink 
    v-if="to && !isExternal" 
    :to="to" 
    :class="['button', `button--${variant}`, `button--${size}`, customClass]" 
    :disabled="disabled"
  >
    <span v-if="icon" class="material-symbols-outlined button__icon">{{ icon }}</span>
    <span class="button__text"><slot>{{ label }}</slot></span>
  </NuxtLink>
  
  <a 
    v-else-if="to && isExternal" 
    :href="to" 
    target="_blank" 
    :class="['button', `button--${variant}`, `button--${size}`, customClass]"
    :disabled="disabled"
  >
    <span v-if="icon" class="material-symbols-outlined button__icon">{{ icon }}</span>
    <span class="button__text"><slot>{{ label }}</slot></span>
  </a>
  
  <button 
    v-else 
    :type="isSubmit ? 'submit' : 'button'" 
    :class="['button', `button--${variant}`, `button--${size}`, customClass]"
    :disabled="disabled"
    @click="$emit('click', $event)"
  >
    <span v-if="icon" class="material-symbols-outlined button__icon">{{ icon }}</span>
    <span class="button__text"><slot>{{ label }}</slot></span>
  </button>
</template>

<script setup lang="ts">
import { computed } from 'vue'

const props = defineProps({
  variant: {
    type: String,
    default: 'primary' // 'primary' | 'secondary'
  },
  size: {
    type: String,
    default: 'md' // 'sm' | 'md' | 'lg'
  },
  icon: {
    type: String,
    default: 'arrow_forward'
  },
  label: {
    type: String,
    default: ''
  },
  to: {
    type: String,
    default: ''
  },
  disabled: {
    type: Boolean,
    default: false
  },
  isSubmit: {
    type: Boolean,
    default: false
  },
  customClass: {
    type: String,
    default: ''
  }
})

defineEmits(['click'])

const isExternal = computed(() => {
  return props.to && (props.to.startsWith('http') || props.to.startsWith('mailto:') || props.to.startsWith('tel:'))
})
</script>

<style scoped>
.button {
  line-height: 1;
  cursor: pointer;
  display: inline-flex;
  align-items: center;
  gap: 0.35em;
  font-weight: 700;
  border-radius: 9999px;
  border: 1px solid transparent;
  transition: all 0.25s ease-out;
  text-transform: uppercase;
  letter-spacing: 0.05em;
  font-family: "Plus Jakarta Sans", "Inter", sans-serif;
  white-space: nowrap;
}

.button[disabled] {
  opacity: 0.5;
  cursor: not-allowed;
  pointer-events: none;
  box-shadow: none !important;
}

.button__icon {
  width: 1.5em;
  height: 1.5em;
  display: flex;
  align-items: center;
  justify-content: center;
}

/* Primary Variant */
.button--primary {
  background: linear-gradient(90deg, #0d9488 0%, #10b981 100%);
  color: #ffffff;
  box-shadow: 0 0.7em 1.5em -0.5em rgba(16, 185, 129, 0.45);
}

.button--primary:hover:not([disabled]) {
  border-color: #34d399;
  box-shadow: 0 0.8em 1.8em -0.4em rgba(16,185,129,0.65);
  transform: scale(1.03);
  background: linear-gradient(90deg, #0d9488 0%, #34d399 100%);
}

.button--primary:active:not([disabled]) {
  transform: scale(0.98);
  box-shadow: 0 0.5em 1.5em -0.5em rgba(16, 185, 129, 0.45);
}

/* Secondary Variant */
.button--secondary {
  background: transparent;
  border: 1.5px solid #10b981;
  color: #ffffff;
  box-shadow: 0 0.5em 1.2em -0.6em rgba(16, 185, 129, 0.25);
}

.button--secondary:hover:not([disabled]) {
  border-color: #34d399;
  box-shadow: 0 0.5em 1.2em -0.6em rgba(16,185,129,0.3);
  transform: scale(1.03);
}

.button--secondary:active:not([disabled]) {
  transform: scale(0.98);
}

/* Size Variants */
.button--lg {
  font-size: 1rem;
  padding: 0.7em 1.3em;
  padding-right: 1.5em;
}
.button--lg .button__icon {
  font-size: 1.4em;
}

.button--md {
  font-size: 0.9rem;
  padding: 0.6em 1.1em;
  padding-right: 1.3em;
}
.button--md .button__icon {
  font-size: 1.2em;
}

.button--sm {
  font-size: 0.8rem;
  padding: 0.5em 0.9em;
  padding-right: 1.05em;
}
.button--sm .button__icon {
  font-size: 1em;
}

/* Responsive reduction for mobile */
@media (max-width: 639px) {
  .button--lg { font-size: 0.9rem; }
  .button--md { font-size: 0.8rem; }
  .button--sm { font-size: 0.75rem; }
}
</style>
