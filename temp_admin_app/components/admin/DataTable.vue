<template>
  <div class="data-table-wrapper">
    <div class="data-table-toolbar">
      <div class="search-box">
        <span class="material-symbols-outlined icon">search</span>
        <input type="text" v-model="searchQuery" :placeholder="searchPlaceholder" class="search-input" />
      </div>
      <div class="actions">
        <slot name="toolbar-actions" />
      </div>
    </div>

    <div class="table-responsive">
      <table class="data-table">
        <thead>
          <tr>
            <th v-for="col in columns" :key="col.key" @click="handleSort(col.key)" :class="{ sortable: col.sortable }">
              <div class="th-content">
                {{ col.label }}
                <span v-if="col.sortable" class="material-symbols-outlined sort-icon" :class="{ active: sortKey === col.key }">
                  {{ sortKey === col.key && sortOrder === 'asc' ? 'arrow_upward' : 'arrow_downward' }}
                </span>
              </div>
            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="row in paginatedData" :key="row.id">
            <td v-for="col in columns" :key="col.key">
              <slot :name="`cell-${col.key}`" :row="row">
                {{ row[col.key] }}
              </slot>
            </td>
          </tr>
          <tr v-if="paginatedData.length === 0">
            <td :colspan="columns.length" class="empty-cell">
              <AdminEmptyState :title="emptyTitle" />
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <div class="pagination" v-if="totalPages > 1">
      <button class="page-btn" :disabled="currentPage === 1" @click="currentPage--">
        <span class="material-symbols-outlined">chevron_left</span>
      </button>
      <span class="page-info">Page {{ currentPage }} of {{ totalPages }}</span>
      <button class="page-btn" :disabled="currentPage === totalPages" @click="currentPage++">
        <span class="material-symbols-outlined">chevron_right</span>
      </button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'

const props = defineProps<{
  data: any[]
  columns: { key: string, label: string, sortable?: boolean }[]
  searchPlaceholder?: string
  searchKeys?: string[]
  itemsPerPage?: number
  emptyTitle?: string
}>()

const searchQuery = ref('')
const sortKey = ref('')
const sortOrder = ref<'asc' | 'desc'>('asc')
const currentPage = ref(1)
const itemsPerPage = props.itemsPerPage || 10

const filteredData = computed(() => {
  let result = [...props.data]

  if (searchQuery.value && props.searchKeys) {
    const q = searchQuery.value.toLowerCase()
    result = result.filter(item => {
      return props.searchKeys!.some(key => String(item[key]).toLowerCase().includes(q))
    })
  }

  if (sortKey.value) {
    result.sort((a, b) => {
      const valA = a[sortKey.value]
      const valB = b[sortKey.value]
      if (valA < valB) return sortOrder.value === 'asc' ? -1 : 1
      if (valA > valB) return sortOrder.value === 'asc' ? 1 : -1
      return 0
    })
  }

  return result
})

const totalPages = computed(() => Math.ceil(filteredData.value.length / itemsPerPage))

const paginatedData = computed(() => {
  const start = (currentPage.value - 1) * itemsPerPage
  const end = start + itemsPerPage
  return filteredData.value.slice(start, end)
})

function handleSort(key: string) {
  const col = props.columns.find(c => c.key === key)
  if (!col?.sortable) return

  if (sortKey.value === key) {
    sortOrder.value = sortOrder.value === 'asc' ? 'desc' : 'asc'
  } else {
    sortKey.value = key
    sortOrder.value = 'asc'
  }
}

// Reset page when searching
watch(searchQuery, () => {
  currentPage.value = 1
})
</script>

<style scoped>
.data-table-wrapper {
  background: var(--admin-tier-1);
  border: 1px solid var(--admin-border);
  border-radius: 12px;
  overflow: hidden;
}

.data-table-toolbar {
  padding: 16px 24px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid var(--admin-border);
  gap: 16px;
  flex-wrap: wrap;
}

.search-box {
  position: relative;
  display: flex;
  align-items: center;
  flex: 1;
  max-width: 320px;
}
.search-box .icon {
  position: absolute;
  left: 12px;
  color: #666;
}
.search-input {
  width: 100%;
  background: var(--admin-tier-0);
  border: 1px solid var(--admin-border);
  border-radius: 8px;
  padding: 8px 16px 8px 40px;
  color: var(--admin-text-primary);
}
.search-input:focus {
  outline: none;
  border-color: var(--admin-success);
}

.table-responsive {
  overflow-x: auto;
}

.data-table {
  width: 100%;
  border-collapse: collapse;
}

.data-table th {
  padding: 16px 24px;
  text-align: left;
  font-size: 0.85rem;
  font-weight: 600;
  color: var(--admin-text-secondary);
  text-transform: uppercase;
  letter-spacing: 0.05em;
  background: rgba(11, 15, 20, 0.5);
  border-bottom: 1px solid var(--admin-border);
}

.data-table th.sortable {
  cursor: pointer;
}
.data-table th.sortable:hover {
  color: var(--admin-text-primary);
}

.th-content {
  display: flex;
  align-items: center;
  gap: 8px;
}
.sort-icon {
  font-size: 1rem;
  opacity: 0.3;
}
.sort-icon.active {
  opacity: 1;
  color: var(--admin-success);
}

.data-table td {
  padding: 16px 24px;
  border-bottom: 1px solid rgba(43,55,52,0.3);
  font-size: 0.95rem;
}

.data-table tbody tr:hover td {
  background: rgba(255, 255, 255, 0.02);
}

.data-table tbody tr:last-child td {
  border-bottom: none;
}

.empty-cell {
  padding: 0 !important;
}

.pagination {
  padding: 16px 24px;
  display: flex;
  align-items: center;
  justify-content: flex-end;
  gap: 16px;
  border-top: 1px solid var(--admin-border);
}

.page-btn {
  background: var(--admin-tier-0);
  border: 1px solid var(--admin-border);
  color: var(--admin-text-primary);
  border-radius: 8px;
  width: 32px;
  height: 32px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
}
.page-btn:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}
.page-btn:not(:disabled):hover {
  border-color: var(--admin-success);
  color: var(--admin-success);
}

.page-info {
  font-size: 0.9rem;
  color: var(--admin-text-secondary);
}
</style>
