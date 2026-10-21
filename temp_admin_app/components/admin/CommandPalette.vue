<template>
  <div v-if="isOpen" class="palette-overlay" @click.self="close">
    <div class="palette-modal">
      <div class="palette-search">
        <span class="material-symbols-outlined icon">search</span>
        <input 
          ref="searchInput" 
          v-model="searchQuery" 
          type="text" 
          placeholder="Search admin panels..." 
          @keydown.down.prevent="navigateDown"
          @keydown.up.prevent="navigateUp"
          @keydown.enter.prevent="selectCurrent"
          @keydown.esc.prevent="close"
        />
        <span class="hint">ESC to close</span>
      </div>
      
      <div class="palette-content">
        <div v-if="filteredItems.length === 0" class="empty-state">
          No results found for "{{ searchQuery }}"
        </div>
        
        <div v-else>
          <div class="section-title">Navigation</div>
          <ul class="item-list">
            <li 
              v-for="(item, index) in filteredItems" 
              :key="item.path"
              :class="['list-item', { 'is-active': activeIndex === index }]"
              @mouseover="activeIndex = index"
              @click="selectItem(item)"
            >
              <span class="material-symbols-outlined item-icon">{{ item.icon }}</span>
              <span class="item-label">{{ item.label }}</span>
              <span class="material-symbols-outlined arrow">keyboard_return</span>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, watch, onMounted, onUnmounted, nextTick } from 'vue'
import { useRouter } from 'vue-router'

const props = defineProps<{
  modelValue: boolean
}>()

const emit = defineEmits(['update:modelValue'])

const router = useRouter()
const searchInput = ref<HTMLInputElement | null>(null)
const searchQuery = ref('')
const activeIndex = ref(0)

const items = [
  { label: 'Dashboard', path: '/admin', icon: 'dashboard' },
  { label: 'Leads & Demo Requests', path: '/admin/leads', icon: 'group' },
  { label: 'Content: Products', path: '/admin/content/products', icon: 'inventory_2' },
  { label: 'Content: Industries', path: '/admin/content/industries', icon: 'domain' },
  { label: 'Content: Pricing', path: '/admin/content/pricing', icon: 'payments' },
  { label: 'Content: Resources', path: '/admin/content/resources', icon: 'article' },
  { label: 'Content: Careers', path: '/admin/content/careers', icon: 'work' },
  { label: 'Media Library', path: '/admin/media', icon: 'perm_media' },
  { label: 'Team', path: '/admin/team', icon: 'badge' },
  { label: 'Settings', path: '/admin/settings', icon: 'settings' }
]

const isOpen = computed({
  get: () => props.modelValue,
  set: (val) => emit('update:modelValue', val)
})

const filteredItems = computed(() => {
  if (!searchQuery.value) return items
  const query = searchQuery.value.toLowerCase()
  return items.filter(i => i.label.toLowerCase().includes(query))
})

watch(isOpen, (val) => {
  if (val) {
    searchQuery.value = ''
    activeIndex.value = 0
    nextTick(() => {
      searchInput.value?.focus()
    })
  }
})

watch(searchQuery, () => {
  activeIndex.value = 0
})

function close() {
  isOpen.value = false
}

function navigateDown() {
  if (activeIndex.value < filteredItems.value.length - 1) {
    activeIndex.value++
  } else {
    activeIndex.value = 0
  }
}

function navigateUp() {
  if (activeIndex.value > 0) {
    activeIndex.value--
  } else {
    activeIndex.value = filteredItems.value.length - 1
  }
}

function selectCurrent() {
  if (filteredItems.value.length > 0) {
    selectItem(filteredItems.value[activeIndex.value])
  }
}

function selectItem(item: any) {
  router.push(item.path)
  close()
}
</script>

<style scoped>
.palette-overlay {
  position: fixed;
  inset: 0;
  background: rgba(11, 15, 20, 0.75);
  backdrop-filter: blur(4px);
  z-index: 100;
  display: flex;
  align-items: flex-start;
  justify-content: center;
  padding-top: 12vh;
}
.palette-modal {
  width: 100%;
  max-width: 600px;
  background: var(--admin-tier-2);
  border: 1px solid var(--admin-border);
  border-radius: 12px;
  overflow: hidden;
  display: flex;
  flex-direction: column;
}
.palette-search {
  display: flex;
  align-items: center;
  padding: 16px 24px;
  border-bottom: 1px solid var(--admin-border);
  gap: 16px;
}
.palette-search .icon {
  color: var(--admin-text-secondary);
  font-size: 1.5rem;
}
.palette-search input {
  flex: 1;
  background: transparent;
  border: none;
  color: var(--admin-text-primary);
  font-size: 1.1rem;
  outline: none;
}
.palette-search input::placeholder {
  color: var(--admin-text-secondary);
  opacity: 0.5;
}
.hint {
  font-size: 0.75rem;
  color: var(--admin-text-secondary);
  background: var(--admin-tier-1);
  padding: 4px 8px;
  border-radius: 4px;
  border: 1px solid var(--admin-border);
}
.palette-content {
  padding: 16px;
  max-height: 400px;
  overflow-y: auto;
}
.section-title {
  font-size: 0.75rem;
  text-transform: uppercase;
  color: var(--admin-text-secondary);
  padding: 8px 16px;
  letter-spacing: 0.05em;
}
.empty-state {
  padding: 32px;
  text-align: center;
  color: var(--admin-text-secondary);
}
.item-list {
  list-style: none;
  padding: 0;
  margin: 0;
}
.list-item {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 12px 16px;
  border-radius: 8px;
  cursor: pointer;
  color: var(--admin-text-secondary);
  transition: all 0.1s ease;
}
.list-item.is-active {
  background: var(--admin-tier-3);
  color: var(--admin-text-primary);
}
.list-item .item-icon {
  font-size: 1.25rem;
}
.list-item .item-label {
  flex: 1;
  font-weight: 500;
}
.list-item .arrow {
  font-size: 1rem;
  opacity: 0;
}
.list-item.is-active .arrow {
  opacity: 1;
  color: var(--admin-success);
}
</style>
