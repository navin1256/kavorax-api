<template>
  <div class="space-y-[24px]">
    <!-- SECTION 1 — PAGE HEADER -->
    <section class="flex flex-col md:flex-row md:items-center justify-between gap-4">
      <div>
        <h1 class="text-[28px] font-bold text-on-surface tracking-tight" style="font-family: 'Plus Jakarta Sans', sans-serif;">Careers</h1>
        <p class="text-[14px] text-on-surface-variant mt-1">Manage open roles shown on your public Careers page.</p>
      </div>
      <div class="flex items-center gap-3">
        <button class="px-5 py-2.5 rounded-full bg-gradient-to-r from-[#10b981] to-[#059669] text-white font-semibold text-[13px] flex items-center gap-2 emerald-glow-pill active:scale-[0.98] transition-all duration-150 shadow-[0_0_16px_rgba(16,185,129,0.25)]">
          <span class="material-symbols-outlined text-[20px]">add</span>
          <span>New Role</span>
        </button>
      </div>
    </section>

    <!-- SECTION 2 — FILTER BAR -->
    <section class="bg-surface-container-low border border-outline-variant/65 rounded-xl p-4 flex flex-wrap gap-4 items-center justify-between shadow-sm">
      <div class="flex flex-wrap items-center gap-3 flex-1 min-w-[280px]">
        <!-- Role Search Input -->
        <div class="relative flex-1 min-w-[220px] max-w-sm">
          <span class="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-outline text-[18px]">search</span>
          <input class="w-full h-[40px] pl-9 pr-4 bg-surface-container-lowest border border-outline-variant/65 rounded-lg text-[13px] text-on-surface placeholder:text-outline focus:outline-none focus:border-primary-container focus:ring-1 focus:ring-primary-container transition-all" placeholder="Search roles..." type="text"/>
        </div>
        <!-- Status Dropdown Filter -->
        <div class="relative">
          <select class="appearance-none h-[40px] pl-3.5 pr-9 bg-surface-container-lowest border border-outline-variant/65 rounded-lg text-[13px] text-on-surface focus:outline-none focus:border-primary-container transition-all cursor-pointer">
            <option value="">All Statuses</option>
            <option value="open">Open</option>
            <option value="closed">Closed</option>
          </select>
          <span class="material-symbols-outlined absolute right-2.5 top-1/2 -translate-y-1/2 text-outline pointer-events-none text-[18px]">expand_more</span>
        </div>
        <!-- Department Dropdown Filter -->
        <div class="relative">
          <select class="appearance-none h-[40px] pl-3.5 pr-9 bg-surface-container-lowest border border-outline-variant/65 rounded-lg text-[13px] text-on-surface focus:outline-none focus:border-primary-container transition-all cursor-pointer">
            <option value="">All Departments</option>
            <option value="engineering">Engineering</option>
            <option value="sales">Sales</option>
            <option value="cs">Customer Success</option>
            <option value="operations">Operations</option>
            <option value="product">Product</option>
          </select>
          <span class="material-symbols-outlined absolute right-2.5 top-1/2 -translate-y-1/2 text-outline pointer-events-none text-[18px]">expand_more</span>
        </div>
      </div>
      <!-- Reset Button -->
      <button class="text-[13px] text-on-surface-variant hover:text-primary transition-colors flex items-center gap-1.5 px-2 py-1">
        <span class="material-symbols-outlined text-[16px]">filter_alt_off</span>
        <span>Clear filters</span>
      </button>
    </section>

    <!-- SECTION 3 — ROLES TABLE -->
    <section class="bg-surface-container-low border border-outline-variant/65 rounded-2xl overflow-hidden shadow-lg">
      <div class="overflow-x-auto">
        <table class="w-full text-left border-collapse">
          <thead>
            <tr class="bg-surface border-b border-outline-variant/65">
              <th class="py-3.5 px-6 font-semibold text-[11px] text-on-surface-variant uppercase tracking-wider">Role Title</th>
              <th class="py-3.5 px-6 font-semibold text-[11px] text-on-surface-variant uppercase tracking-wider">Department</th>
              <th class="py-3.5 px-6 font-semibold text-[11px] text-on-surface-variant uppercase tracking-wider">Location</th>
              <th class="py-3.5 px-6 font-semibold text-[11px] text-on-surface-variant uppercase tracking-wider">Type</th>
              <th class="py-3.5 px-6 font-semibold text-[11px] text-on-surface-variant uppercase tracking-wider">Status</th>
              <th class="py-3.5 px-6 font-semibold text-[11px] text-on-surface-variant uppercase tracking-wider">Applicants</th>
              <th class="py-3.5 px-6 font-semibold text-[11px] text-on-surface-variant uppercase tracking-wider text-right">Actions</th>
            </tr>
          </thead>
          <tbody class="divide-y divide-outline-variant/40">
            <tr v-if="roles.length === 0">
              <td colspan="7" class="py-8 px-4 text-center text-[13px] text-outline italic">No open roles currently available.</td>
            </tr>
            <tr v-for="(role, idx) in roles" :key="idx" class="hover:bg-surface-container transition-colors group">
              <td class="py-4 px-6">
                <div class="flex items-center gap-3">
                  <span class="w-2 h-2 rounded-full" :class="role.status === 'Open' ? 'bg-primary-container' : 'bg-outline'"></span>
                  <span class="text-[14px] text-on-surface font-semibold group-hover:text-primary transition-colors">{{ role.title }}</span>
                </div>
              </td>
              <td class="py-4 px-6">
                <span class="inline-flex items-center px-2.5 py-0.5 rounded-full text-[11px] font-semibold bg-surface-container-high border border-outline-variant/65 text-on-surface">{{ role.department }}</span>
              </td>
              <td class="py-4 px-6">
                <div class="flex items-center gap-1.5 text-[13px] text-on-surface-variant">
                  <span class="material-symbols-outlined text-[16px]" :class="role.status === 'Open' ? 'text-primary' : 'text-outline'">location_on</span>
                  <span>{{ role.location }}</span>
                </div>
              </td>
              <td class="py-4 px-6">
                <span class="inline-flex items-center px-2 py-0.5 rounded-md text-[11px] font-semibold bg-surface-variant/40 text-on-surface-variant">{{ role.type }}</span>
              </td>
              <td class="py-4 px-6">
                <span v-if="role.status === 'Open'" class="inline-flex items-center gap-1.5 px-2.5 py-1 rounded-full text-[11px] font-semibold bg-primary-container/15 text-primary border border-primary-container/30">
                  <span class="w-1.5 h-1.5 rounded-full bg-primary-container animate-pulse"></span>
                  Open
                </span>
                <span v-else class="inline-flex items-center gap-1.5 px-2.5 py-1 rounded-full text-[11px] font-semibold bg-surface-variant/40 text-on-surface-variant border border-outline-variant/65">
                  <span class="w-1.5 h-1.5 rounded-full bg-outline"></span>
                  Closed
                </span>
              </td>
              <td class="py-4 px-6">
                <div class="flex flex-col">
                  <span class="font-bold text-on-surface text-[14px]">{{ role.applicants }}</span>
                  <button class="text-[11px] font-semibold text-primary-container hover:text-primary underline decoration-primary-container/40 underline-offset-2 flex items-center gap-1 text-left" @click="openModal">
                    <span>view applicants</span>
                    <span class="text-[12px]">→</span>
                  </button>
                </div>
              </td>
              <td class="py-4 px-6 text-right">
                <div class="flex items-center justify-end gap-1">
                  <button class="w-8 h-8 rounded-lg flex items-center justify-center text-outline hover:text-on-surface hover:bg-surface-variant/50 transition-all">
                    <span class="material-symbols-outlined text-[18px]">edit</span>
                  </button>
                  <button class="w-8 h-8 rounded-lg flex items-center justify-center text-outline hover:text-error hover:bg-surface-variant/50 transition-all">
                    <span class="material-symbols-outlined text-[18px]">delete</span>
                  </button>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </section>
    
    <!-- SECTION 5 — PAGINATION -->
    <section class="flex flex-col sm:flex-row items-center justify-between gap-4 py-2">
      <span class="text-[13px] text-on-surface-variant">
        Showing <span class="text-on-surface font-medium">0-0</span> of <span class="text-on-surface font-medium">0</span> roles
      </span>
      <div class="flex items-center gap-1.5">
        <button aria-label="Previous Page" class="w-9 h-9 rounded-full bg-surface-container-low border border-outline-variant/65 flex items-center justify-center text-outline hover:text-on-surface hover:bg-surface-container transition-all">
          <span class="material-symbols-outlined text-[18px]">chevron_left</span>
        </button>
        <button class="w-9 h-9 rounded-full bg-primary-container text-white font-semibold text-[13px] flex items-center justify-center shadow-[0_0_12px_rgba(16,185,129,0.3)]">1</button>
        <button class="w-9 h-9 rounded-full bg-surface-container-low border border-outline-variant/65 text-on-surface-variant hover:text-on-surface hover:bg-surface-container font-semibold text-[13px] flex items-center justify-center transition-all">2</button>
        <button class="w-9 h-9 rounded-full bg-surface-container-low border border-outline-variant/65 text-on-surface-variant hover:text-on-surface hover:bg-surface-container font-semibold text-[13px] flex items-center justify-center transition-all">3</button>
        <button aria-label="Next Page" class="w-9 h-9 rounded-full bg-surface-container-low border border-outline-variant/65 flex items-center justify-center text-outline hover:text-on-surface hover:bg-surface-container transition-all">
          <span class="material-symbols-outlined text-[18px]">chevron_right</span>
        </button>
      </div>
    </section>

    <!-- SECTION 6 — APPLICANTS MODAL OVERLAY -->
    <transition name="fade">
      <div v-if="isModalOpen" class="fixed inset-0 z-50 flex items-center justify-center p-4 modal-backdrop-blur transition-opacity duration-200" @click.self="closeModal">
        <div class="w-full max-w-2xl bg-surface-container-low border border-outline-variant/90 rounded-2xl shadow-[0_8px_32px_rgba(0,0,0,0.8),0_0_1px_rgba(16,185,129,0.3)] overflow-hidden flex flex-col max-h-[90vh]">
          <!-- Modal Header -->
          <div class="p-6 border-b border-outline-variant/65 flex items-center justify-between bg-surface">
            <div class="flex items-center gap-3">
              <div class="w-3 h-3 rounded-full bg-primary-container"></div>
              <div>
                <h2 class="text-[18px] font-bold text-on-surface" style="font-family: 'Plus Jakarta Sans', sans-serif;">Applicants — Staff Distributed Systems Engineer</h2>
                <p class="text-[13px] text-on-surface-variant">Reviewing candidate submissions and credentials.</p>
              </div>
            </div>
            <button class="w-8 h-8 rounded-lg flex items-center justify-center text-outline hover:text-on-surface hover:bg-surface-variant transition-all" @click="closeModal">
              <span class="material-symbols-outlined text-[20px]">close</span>
            </button>
          </div>
          <!-- Applicant List Body -->
          <div class="p-6 overflow-y-auto space-y-3 custom-scroll flex-1">
            <div v-if="applicants.length === 0" class="py-8 px-4 text-center text-[13px] text-outline italic">
              No applicants for this role yet.
            </div>
            <div v-for="(applicant, idx) in applicants" :key="idx" class="bg-surface border border-outline-variant/65 rounded-xl p-4 flex flex-col sm:flex-row sm:items-center justify-between gap-4 hover:border-primary-container/40 transition-colors">
              <div class="flex items-start gap-3">
                <div class="w-10 h-10 rounded-full bg-surface-variant flex items-center justify-center text-on-surface font-bold text-[14px] border border-outline-variant/65">
                  {{ applicant.initials }}
                </div>
                <div>
                  <h4 class="text-[16px] font-semibold text-on-surface" style="font-family: 'Plus Jakarta Sans', sans-serif;">{{ applicant.name }}</h4>
                  <p class="text-[12px] font-medium text-outline font-mono">{{ applicant.email }}</p>
                  <span class="text-[11px] font-semibold text-on-surface-variant mt-1 inline-block">Applied on {{ applicant.date }}</span>
                </div>
              </div>
              <div class="flex items-center gap-3 self-end sm:self-center">
                <a class="text-[13px] font-semibold text-primary-container hover:text-primary flex items-center gap-1 transition-colors" href="#">
                  <span>View Resume</span>
                  <span class="material-symbols-outlined text-[15px]">open_in_new</span>
                </a>
                <div class="relative">
                  <select v-model="applicant.status" :class="`appearance-none h-[34px] pl-3 pr-8 bg-surface-container border border-outline-variant/65 rounded-full text-[11px] font-semibold focus:outline-none focus:border-primary-container cursor-pointer ${getStatusColor(applicant.status)}`">
                    <option value="New">New</option>
                    <option value="Reviewed">Reviewed</option>
                    <option value="Rejected">Rejected</option>
                  </select>
                  <span class="material-symbols-outlined absolute right-2.5 top-1/2 -translate-y-1/2 text-outline pointer-events-none text-[16px]">expand_more</span>
                </div>
              </div>
            </div>
          </div>
          <!-- Modal Footer -->
          <div class="p-4 px-6 border-t border-outline-variant/65 bg-surface flex items-center justify-between">
            <span class="text-[13px] text-on-surface-variant">Showing 0 of 0 applicants</span>
            <button class="px-5 py-2 rounded-full border border-outline-variant/65 hover:bg-surface-variant text-on-surface text-[13px] font-semibold transition-all" @click="closeModal">
              Close
            </button>
          </div>
        </div>
      </div>
    </transition>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'

definePageMeta({ layout: 'admin', middleware: ['admin-auth'] })

const isModalOpen = ref(false)

const openModal = () => isModalOpen.value = true
const closeModal = () => isModalOpen.value = false

const roles = ref<any[]>([])

const applicants = ref<any[]>([])

const getStatusColor = (status: string) => {
  if (status === 'New') return 'text-primary-container'
  if (status === 'Rejected') return 'text-error'
  return 'text-on-surface'
}
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
  font-variation-settings: 'wght' 300, 'GRAD' 0, 'opsz' 20;
  vertical-align: middle;
}

.emerald-glow-pill:hover {
  box-shadow: 0 0 16px rgba(16, 185, 129, 0.35);
}

.modal-backdrop-blur {
  backdrop-filter: blur(8px);
  background-color: rgba(11, 15, 20, 0.85);
}

.custom-scroll::-webkit-scrollbar {
  width: 6px;
}
.custom-scroll::-webkit-scrollbar-track {
  background: #101419;
}
.custom-scroll::-webkit-scrollbar-thumb {
  background: #2b3734;
  border-radius: 9999px;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.2s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
