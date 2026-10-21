<template>
  <div class="space-y-[24px]">
    <!-- ======================================================== -->
    <!-- 3. SECTION 1 — WELCOME HERO BANNER                       -->
    <!-- ======================================================== -->
    <section class="relative overflow-hidden rounded-2xl bg-surface-container-low border border-outline-variant/65 p-8 shadow-sm">
      <!-- Architectural Glow lines & Motif Backdrop -->
      <div class="absolute inset-0 pointer-events-none">
        <div class="absolute -right-10 -bottom-16 w-80 h-80 rounded-full bg-primary-container/5 blur-3xl"></div>
        <svg class="absolute right-0 top-0 h-full w-2/5 text-primary-container/20" fill="none" viewBox="0 0 400 200">
          <line opacity="0.4" stroke="currentColor" stroke-dasharray="6 4" stroke-width="1.2" x1="50" x2="380" y1="20" y2="180"></line>
          <line opacity="0.6" stroke="currentColor" stroke-width="1.5" x1="120" x2="390" y1="0" y2="110"></line>
          <line opacity="0.3" stroke="currentColor" stroke-width="1" x1="180" x2="350" y1="0" y2="200"></line>
          <line opacity="0.5" stroke="currentColor" stroke-width="1.5" x1="260" x2="400" y1="0" y2="70"></line>
        </svg>
      </div>
      <div class="relative z-10 flex flex-col lg:flex-row items-start lg:items-center justify-between gap-8">
        <!-- Left side (60%) -->
        <div class="w-full lg:w-3/5 space-y-4">
          <div>
            <h2 class="text-[28px] font-bold text-on-surface tracking-tight" style="font-family: 'Plus Jakarta Sans', sans-serif;">Welcome back, Admin</h2>
            <p class="text-[14px] text-outline mt-1">Here's what's happening with KavoraX today.</p>
          </div>
          <!-- Command-style search bar inside Hero -->
          <div class="relative max-w-xl">
            <span class="material-symbols-outlined absolute left-3.5 top-1/2 -translate-y-1/2 text-outline text-xl" data-icon="search">search</span>
            <input class="w-full h-[46px] pl-11 pr-14 rounded-xl bg-surface-container-lowest border border-outline-variant/65 text-on-surface placeholder:text-outline text-[14px] focus:outline-none focus:border-primary-container focus:ring-1 focus:ring-primary-container shadow-inner" placeholder="Search leads, content, or settings..." type="text"/>
            <div class="absolute right-3.5 top-1/2 -translate-y-1/2 flex items-center gap-1">
              <kbd class="px-2 py-0.5 rounded text-[11px] bg-surface-container border border-outline-variant/65 text-outline">⌘K</kbd>
            </div>
          </div>
        </div>
        <!-- Right side (40% emblem showcase) -->
        <div class="w-full lg:w-2/5 flex items-center justify-end relative pr-6">
          <div class="relative w-36 h-36 flex items-center justify-center">
            <!-- Radiant Ambient Halo -->
            <div class="absolute inset-0 bg-primary-container/20 rounded-full blur-2xl"></div>
            <!-- Monolithic Geometric K Emblem -->
            <svg class="w-32 h-32 text-primary-container opacity-25 filter drop-shadow-[0_0_12px_rgba(16,185,129,0.5)]" fill="none" stroke="currentColor" viewBox="0 0 100 100">
              <polygon fill="currentColor" points="15,10 35,10 35,90 15,90"></polygon>
              <polygon fill="currentColor" points="35,45 65,10 88,10 52,52"></polygon>
              <polygon fill="currentColor" points="48,50 88,90 65,90 35,58"></polygon>
            </svg>
          </div>
        </div>
      </div>
    </section>

    <!-- ======================================================== -->
    <!-- 4. SECTION 2 — STAT CHIPS                                -->
    <!-- ======================================================== -->
    <section class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-[24px]">
      <!-- Card 1 -->
      <div class="bg-surface-container-low border border-outline-variant/65 rounded-2xl p-6 transition-all duration-200">
        <div class="flex items-start justify-between">
          <div class="w-10 h-10 rounded-full bg-surface-container border border-outline-variant/30 flex items-center justify-center text-outline">
            <span class="material-symbols-outlined text-xl" data-icon="person_add">person_add</span>
          </div>
        </div>
        <div class="mt-4">
          <p class="text-[28px] font-bold text-outline leading-none" style="font-family: 'Plus Jakarta Sans', sans-serif;">{{ totalLeads }}</p>
          <p class="text-[12px] font-medium text-outline mt-1">Total Leads</p>
        </div>
      </div>
      <!-- Card 2 -->
      <div class="bg-surface-container-low border border-outline-variant/65 rounded-2xl p-6 transition-all duration-200">
        <div class="flex items-start justify-between">
          <div class="w-10 h-10 rounded-full bg-surface-container border border-outline-variant/30 flex items-center justify-center text-outline">
            <span class="material-symbols-outlined text-xl" data-icon="event_available">event_available</span>
          </div>
        </div>
        <div class="mt-4">
          <p class="text-[28px] font-bold text-outline leading-none" style="font-family: 'Plus Jakarta Sans', sans-serif;">{{ totalDemos }}</p>
          <p class="text-[12px] font-medium text-outline mt-1">Demo Bookings</p>
        </div>
      </div>
      <!-- Card 3 -->
      <div class="bg-surface-container-low border border-outline-variant/65 rounded-2xl p-6 transition-all duration-200">
        <div class="flex items-start justify-between">
          <div class="w-10 h-10 rounded-full bg-surface-container border border-outline-variant/30 flex items-center justify-center text-outline">
            <span class="material-symbols-outlined text-xl" data-icon="mail">mail</span>
          </div>
        </div>
        <div class="mt-4">
          <p class="text-[28px] font-bold text-outline leading-none" style="font-family: 'Plus Jakarta Sans', sans-serif;">{{ totalSessions }}</p>
          <p class="text-[12px] font-medium text-outline mt-1">Schedule Sessions</p>
        </div>
      </div>
      <!-- Card 4 -->
      <div class="bg-surface-container-low border border-outline-variant/65 rounded-2xl p-6 transition-all duration-200">
        <div class="flex items-start justify-between">
          <div class="w-10 h-10 rounded-full bg-surface-container border border-outline-variant/30 flex items-center justify-center text-outline">
            <span class="material-symbols-outlined text-xl" data-icon="work">work</span>
          </div>
        </div>
        <div class="mt-4">
          <p class="text-[28px] font-bold text-outline leading-none" style="font-family: 'Plus Jakarta Sans', sans-serif;">{{ totalEnquiries }}</p>
          <p class="text-[12px] font-medium text-outline mt-1">Enquiries</p>
        </div>
      </div>
    </section>

    <!-- ======================================================== -->
    <!-- 5. SECTION 3 — LIVE STATUS STRIP                         -->
    <!-- ======================================================== -->
    <section class="bg-surface border border-outline-variant/65 rounded-xl py-2.5 px-6 flex flex-wrap items-center justify-center gap-4">
      <div class="flex items-center gap-2">
        <span class="text-[13px] text-outline italic">No live data available yet.</span>
      </div>
    </section>

    <!-- ======================================================== -->
    <!-- 6. SECTION 4 — CHART + RECENT ACTIVITY                   -->
    <!-- ======================================================== -->
    <section class="grid grid-cols-1 lg:grid-cols-12 gap-[24px] items-start">
      <!-- Left Column (55% / 7 cols) -->
      <div class="lg:col-span-7 bg-surface-container-low border border-outline-variant/65 rounded-2xl p-6">
        <div class="flex items-center justify-between pb-4 mb-6 border-b border-outline-variant/45">
          <div>
            <h3 class="text-[18px] font-bold text-on-surface" style="font-family: 'Plus Jakarta Sans', sans-serif;">Lead Volume</h3>
            <p class="text-[11px] font-semibold text-outline mt-0.5">Inbound pipeline progression</p>
          </div>
        </div>
        <!-- Empty Chart Container -->
        <div class="w-full h-64 flex items-center justify-center relative border border-dashed border-outline-variant/30 rounded-xl">
          <p class="text-outline text-[13px] italic">Analytics will appear once real data is available.</p>
        </div>
      </div>

      <!-- Right Column (45% / 5 cols) -->
      <div class="lg:col-span-5 bg-surface-container-low border border-outline-variant/65 rounded-2xl p-6 flex flex-col">
        <div class="pb-4 mb-4 border-b border-outline-variant/45 flex items-center justify-between">
          <h3 class="text-[18px] font-bold text-on-surface" style="font-family: 'Plus Jakarta Sans', sans-serif;">Recent Activity</h3>
        </div>
        <!-- Activity List -->
        <div class="flex-1 flex items-center justify-center min-h-[200px]">
          <p class="text-outline text-[13px] italic">No activity data available yet.</p>
        </div>
      </div>
    </section>

    <!-- ======================================================== -->
    <!-- 7. SECTION 5 — RECENT LEADS TABLE                        -->
    <!-- ======================================================== -->
    <section class="bg-surface-container-low border border-outline-variant/65 rounded-2xl p-6">
      <div class="flex items-center justify-between pb-4 mb-4 border-b border-outline-variant/45">
        <div>
          <h3 class="text-[18px] font-bold text-on-surface" style="font-family: 'Plus Jakarta Sans', sans-serif;">Recent Leads</h3>
          <p class="text-[11px] font-semibold text-outline">Direct pipeline inbound authorizations</p>
        </div>
        <NuxtLink to="/admin/leads" class="text-[13px] text-primary hover:underline font-semibold flex items-center gap-1 group">
          <span>View all</span>
          <span class="transition-transform group-hover:translate-x-1">→</span>
        </NuxtLink>
      </div>
      
      <div class="overflow-x-auto">
        <table class="w-full text-left border-collapse">
          <thead>
            <tr class="bg-surface border-b border-outline-variant/65">
              <th class="py-3 px-4 text-[11px] font-semibold text-outline uppercase tracking-wider">Name</th>
              <th class="py-3 px-4 text-[11px] font-semibold text-outline uppercase tracking-wider">Business Name</th>
              <th class="py-3 px-4 text-[11px] font-semibold text-outline uppercase tracking-wider">Requested Date</th>
              <th class="py-3 px-4 text-[11px] font-semibold text-outline uppercase tracking-wider">Status</th>
              <th class="py-3 px-4 text-[11px] font-semibold text-outline uppercase tracking-wider text-right">Actions</th>
            </tr>
          </thead>
          <tbody class="divide-y divide-outline-variant/40">
            <tr v-if="loading">
              <td colspan="5" class="py-8 px-4 text-center text-[13px] text-white">
                <div class="flex items-center justify-center gap-2">
                  <span class="material-symbols-outlined animate-spin" data-icon="refresh">refresh</span>
                  <span>Loading recent leads...</span>
                </div>
              </td>
            </tr>
            <tr v-else-if="error">
              <td colspan="5" class="py-8 px-4 text-center text-[13px] text-error">{{ error }}</td>
            </tr>
            <tr v-else-if="recentLeads.length === 0">
              <td colspan="5" class="py-8 px-4 text-center text-[13px] text-outline italic">No enquiries or demo requests yet.</td>
            </tr>
            <tr v-for="lead in recentLeads" v-else :key="lead.id" class="hover:bg-surface-container transition-colors">
              <td class="py-3 px-4 text-[13px] font-medium text-white">{{ lead.name }}</td>
              <td class="py-3 px-4 text-[13px] text-outline">{{ lead.bizName }}</td>
              <td class="py-3 px-4 text-[13px] text-outline">{{ lead.date }}</td>
              <td class="py-3 px-4">
                <span v-if="lead.status === 'New'" class="inline-flex items-center gap-1.5 px-2 py-0.5 rounded text-[10px] font-semibold bg-primary-container/20 text-primary-container border border-primary-container/30">
                  <span class="w-1.5 h-1.5 rounded-full bg-primary-container"></span> New
                </span>
                <span v-else class="inline-flex items-center gap-1.5 px-2 py-0.5 rounded text-[10px] font-semibold bg-surface-container-high text-outline border border-outline-variant/50">
                  <span class="w-1.5 h-1.5 rounded-full bg-outline"></span> {{ lead.status }}
                </span>
              </td>
              <td class="py-3 px-4 text-right">
                <NuxtLink to="/admin/leads" class="text-[12px] text-primary hover:underline font-semibold">View</NuxtLink>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </section>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useApi } from '~/composables/useApi'

definePageMeta({
  layout: 'admin',
  middleware: ['admin-auth']
})

const { apiFetch } = useApi()
const recentLeads = ref<any[]>([])
const totalLeads = ref(0)
const totalDemos = ref(0)
const totalSessions = ref(0)
const totalEnquiries = ref(0)
const loading = ref(true)
const error = ref<string | null>(null)

onMounted(async () => {
  loading.value = true
  error.value = null
  try {
    const [enquiriesRes, demoRequestsRes, sessionRequestsRes] = await Promise.allSettled([
      apiFetch('/v1/admin/enquiries'),
      apiFetch('/v1/admin/demo-requests'),
      apiFetch('/v1/admin/session-requests')
    ])

    const enquiries = enquiriesRes.status === 'fulfilled' ? (enquiriesRes.value as any[]) : []
    const demoRequests = demoRequestsRes.status === 'fulfilled' ? (demoRequestsRes.value as any[]) : []
    const sessionRequests = sessionRequestsRes.status === 'fulfilled' ? (sessionRequestsRes.value as any[]) : []

    totalEnquiries.value = enquiries.length
    totalDemos.value = demoRequests.length
    totalSessions.value = sessionRequests.length
    totalLeads.value = enquiries.length + demoRequests.length + sessionRequests.length

    const formattedEnquiries = enquiries.map(e => ({
      id: `enq-${e.id}`,
      name: e.name,
      bizName: e.subject || 'N/A',
      date: new Date(e.createdAt).toLocaleString('en-US', { day: '2-digit', month: 'short', year: 'numeric', hour: '2-digit', minute: '2-digit' }),
      createdAtDate: new Date(e.createdAt),
      status: e.status === 'RECEIVED' ? 'New' : e.status === 'RESOLVED' ? 'Closed' : 'Contacted'
    }))

    const formattedDemos = demoRequests.map(d => ({
      id: `dem-${d.id}`,
      name: d.name,
      bizName: d.company || 'N/A',
      date: new Date(d.createdAt).toLocaleString('en-US', { day: '2-digit', month: 'short', year: 'numeric', hour: '2-digit', minute: '2-digit' }),
      createdAtDate: new Date(d.createdAt),
      status: d.status === 'PENDING' ? 'New' : d.status === 'COMPLETED' ? 'Closed' : 'Contacted'
    }))

    const formattedSessions = sessionRequests.map(s => ({
      id: `sess-${s.id}`,
      name: s.name,
      bizName: s.company || 'N/A',
      date: new Date(s.createdAt).toLocaleString('en-US', { day: '2-digit', month: 'short', year: 'numeric', hour: '2-digit', minute: '2-digit' }),
      createdAtDate: new Date(s.createdAt),
      status: s.status === 'PENDING' ? 'New' : s.status === 'COMPLETED' ? 'Closed' : 'Contacted'
    }))

    // Merge, sort, and take top 5
    recentLeads.value = [...formattedEnquiries, ...formattedDemos, ...formattedSessions]
      .sort((a, b) => b.createdAtDate.getTime() - a.createdAtDate.getTime())
      .slice(0, 5)
  } catch (err: any) {
    error.value = err.message || 'Failed to fetch data'
  } finally {
    loading.value = false
  }
})
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&family=Plus+Jakarta+Sans:wght@600;700;800&display=swap');

.material-symbols-outlined {
  font-family: 'Material Symbols Outlined';
  font-weight: normal;
  font-style: normal;
  display: inline-block;
  line-height: 1;
  text-transform: none;
  letter-spacing: normal;
  word-wrap: normal;
  white-space: nowrap;
  direction: ltr;
  font-variation-settings: 'FILL' 0, 'wght' 300, 'GRAD' 0, 'opsz' 24;
  vertical-align: middle;
}
</style>
