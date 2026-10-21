<template>
  <div class="space-y-[24px]">
    <div class="flex items-center justify-between">
      <div>
        <h1 class="text-[28px] font-bold text-on-surface tracking-tight" style="font-family: 'Plus Jakarta Sans', sans-serif;">Demo Bookings</h1>
        <p class="text-[14px] text-outline mt-1">Manage all demo booking requests.</p>
      </div>
      <div class="flex gap-2">
        <button class="px-4 py-2 bg-surface border border-outline-variant rounded-lg text-sm text-on-surface hover:bg-surface-container flex items-center gap-2 transition">
          <span class="material-symbols-outlined text-[18px]">filter_list</span>
          Filter
        </button>
        <button @click="fetchLeads" class="px-4 py-2 bg-surface border border-outline-variant rounded-lg text-sm text-on-surface hover:bg-surface-container flex items-center gap-2 transition">
          <span class="material-symbols-outlined text-[18px]">refresh</span>
          Refresh
        </button>
      </div>
    </div>

    <!-- Search -->
    <div class="bg-surface p-4 rounded-xl border border-outline-variant flex gap-4">
      <div class="relative flex-1">
        <span class="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-outline">search</span>
        <input v-model="searchQuery" type="text" placeholder="Search demo bookings..." class="w-full bg-background border border-outline-variant rounded-lg pl-10 pr-4 py-2 text-sm text-on-surface focus:border-primary outline-none" />
      </div>
    </div>

    <!-- Table -->
    <div class="bg-surface border border-outline-variant rounded-xl overflow-hidden">
      <table class="w-full text-left">
        <thead>
          <tr class="bg-surface-container-low border-b border-outline-variant">
            <th class="py-3 px-4 text-xs font-semibold text-outline uppercase tracking-wider">Name</th>
            <th class="py-3 px-4 text-xs font-semibold text-outline uppercase tracking-wider">Email</th>
            <th class="py-3 px-4 text-xs font-semibold text-outline uppercase tracking-wider">Company</th>
            <th class="py-3 px-4 text-xs font-semibold text-outline uppercase tracking-wider">Phone</th>
            <th class="py-3 px-4 text-xs font-semibold text-outline uppercase tracking-wider">Requirements</th>
            <th class="py-3 px-4 text-xs font-semibold text-outline uppercase tracking-wider">Created</th>
            <th class="py-3 px-4 text-xs font-semibold text-outline uppercase tracking-wider">Status</th>
          </tr>
        </thead>
        <tbody class="divide-y divide-outline-variant/50">
          <tr v-if="loading"><td colspan="7" class="py-8 text-center text-outline">Loading...</td></tr>
          <tr v-else-if="error"><td colspan="7" class="py-8 text-center text-error">{{ error }}</td></tr>
          <tr v-else-if="filteredLeads.length === 0"><td colspan="7" class="py-8 text-center text-outline">No demo bookings yet.</td></tr>
          <tr v-for="lead in filteredLeads" :key="lead.id" class="hover:bg-surface-container transition">
            <td class="py-3 px-4 text-sm text-on-surface">{{ lead.name }}</td>
            <td class="py-3 px-4 text-sm text-on-surface">{{ lead.email }}</td>
            <td class="py-3 px-4 text-sm text-on-surface">{{ lead.company }}</td>
            <td class="py-3 px-4 text-sm text-on-surface">{{ lead.phone || 'N/A' }}</td>
            <td class="py-3 px-4 text-sm text-on-surface max-w-[200px] truncate">{{ lead.requirements || 'N/A' }}</td>
            <td class="py-3 px-4 text-sm text-on-surface">{{ new Date(lead.createdAt).toLocaleString() }}</td>
            <td class="py-3 px-4 text-sm text-on-surface">{{ lead.status }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import { useApi } from '~/composables/useApi'

definePageMeta({ layout: 'admin', middleware: ['admin-auth'] })

const { apiFetch } = useApi()
const leads = ref<any[]>([])
const loading = ref(true)
const error = ref<string | null>(null)
const searchQuery = ref('')

const fetchLeads = async () => {
  loading.value = true
  error.value = null
  try {
    const data = await apiFetch('/v1/admin/demo-requests')
    leads.value = Array.isArray(data) ? data : []
  } catch (err: any) {
    error.value = err.message || 'Unable to connect to the server. Please try again.'
  } finally {
    loading.value = false
  }
}

const filteredLeads = computed(() => {
  if (!searchQuery.value) return leads.value
  const q = searchQuery.value.toLowerCase()
  return leads.value.filter(l => 
    (l.name && l.name.toLowerCase().includes(q)) ||
    (l.email && l.email.toLowerCase().includes(q)) ||
    (l.company && l.company.toLowerCase().includes(q))
  )
})

onMounted(fetchLeads)
</script>
