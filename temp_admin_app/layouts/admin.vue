<template>
  <div class="bg-surface-container-lowest text-on-surface antialiased min-h-screen flex selection:bg-primary-container selection:text-on-primary">
    <!-- ======================================================== -->
    <!-- 1. FIXED SIDEBAR                                         -->
    <!-- ======================================================== -->
    <aside class="fixed top-0 bottom-0 left-0 w-[260px] h-screen z-40 bg-surface border-r border-outline-variant/65 flex flex-col justify-between p-4">
      <!-- Brand Lockup -->
      <div>
        <div class="px-3 py-1 mb-5">
          <img src="/images/kavorax-logo-full.jpg" alt="Kavorax Global Private Limited" class="w-[180px] object-contain mix-blend-screen -ml-3" />
        </div>

        <!-- Navigation Tabs -->
        <nav class="space-y-1.5">
          <!-- 1. Dashboard -->
          <NuxtLink to="/admin" exact-active-class="bg-surface-container !text-primary border-l-4 !border-primary" class="flex items-center gap-3 px-4 py-3 text-on-surface-variant hover:text-on-surface hover:bg-surface-container text-[14px] font-medium rounded-lg transition-all duration-200 border-l-4 border-transparent outline-none focus-visible:ring-2 focus-visible:ring-primary" style="font-family: 'Plus Jakarta Sans', sans-serif;">
            <span class="material-symbols-outlined text-[20px]" data-icon="dashboard">dashboard</span>
            <span>Dashboard</span>
          </NuxtLink>

          <!-- 2. Leads -->
          <div class="space-y-1">
            <div class="w-full flex items-center justify-between px-4 py-3 text-on-surface-variant text-[14px] font-medium rounded-lg" style="font-family: 'Plus Jakarta Sans', sans-serif;">
              <div class="flex items-center gap-3">
                <span class="material-symbols-outlined text-[20px] transition-colors" data-icon="group">group</span>
                <span>Leads</span>
              </div>
            </div>
            <div class="ml-[26px] py-1 space-y-1 border-l border-outline-variant/40">
              <NuxtLink to="/admin/leads/demo-bookings" exact-active-class="!text-[#10b981] !border-[#10b981]" class="block py-1.5 text-[12px] font-medium text-[#999999] hover:text-white transition-colors border-l border-transparent -ml-[1px] pl-[21px] outline-none focus-visible:ring-2 focus-visible:ring-primary rounded-r-md">Demo Bookings</NuxtLink>
              <NuxtLink to="/admin/leads/session-requests" exact-active-class="!text-[#10b981] !border-[#10b981]" class="block py-1.5 text-[12px] font-medium text-[#999999] hover:text-white transition-colors border-l border-transparent -ml-[1px] pl-[21px] outline-none focus-visible:ring-2 focus-visible:ring-primary rounded-r-md">Schedule Your Session</NuxtLink>
              <NuxtLink to="/admin/leads/enquiries" exact-active-class="!text-[#10b981] !border-[#10b981]" class="block py-1.5 text-[12px] font-medium text-[#999999] hover:text-white transition-colors border-l border-transparent -ml-[1px] pl-[21px] outline-none focus-visible:ring-2 focus-visible:ring-primary rounded-r-md">Enquiries</NuxtLink>
            </div>
          </div>

          <!-- 3. Content (Expandable preview) -->
          <div class="space-y-1">
            <button class="w-full flex items-center justify-between px-4 py-3 text-on-surface-variant hover:text-on-surface hover:bg-surface-container text-[14px] font-medium rounded-lg transition-all duration-200 border-l-4 border-transparent outline-none focus-visible:ring-2 focus-visible:ring-primary" style="font-family: 'Plus Jakarta Sans', sans-serif;">
              <div class="flex items-center gap-3">
                <span class="material-symbols-outlined text-[20px] transition-colors" data-icon="article">article</span>
                <span>Content</span>
              </div>
              <span class="material-symbols-outlined text-[20px]" data-icon="expand_more">expand_more</span>
            </button>
            <div class="ml-[26px] py-1 space-y-1 border-l border-outline-variant/40">
              <NuxtLink to="/admin/content/products" exact-active-class="!text-[#10b981] !border-[#10b981]" class="block py-1.5 text-[12px] font-medium text-[#999999] hover:text-white transition-colors border-l border-transparent -ml-[1px] pl-[21px] outline-none focus-visible:ring-2 focus-visible:ring-primary rounded-r-md">Products</NuxtLink>
              <NuxtLink to="/admin/content/industries" exact-active-class="!text-[#10b981] !border-[#10b981]" class="block py-1.5 text-[12px] font-medium text-[#999999] hover:text-white transition-colors border-l border-transparent -ml-[1px] pl-[21px] outline-none focus-visible:ring-2 focus-visible:ring-primary rounded-r-md">Industries</NuxtLink>
              <NuxtLink to="/admin/content/pricing" exact-active-class="!text-[#10b981] !border-[#10b981]" class="block py-1.5 text-[12px] font-medium text-[#999999] hover:text-white transition-colors border-l border-transparent -ml-[1px] pl-[21px] outline-none focus-visible:ring-2 focus-visible:ring-primary rounded-r-md">Pricing</NuxtLink>
              <NuxtLink to="/admin/content/resources" exact-active-class="!text-[#10b981] !border-[#10b981]" class="block py-1.5 text-[12px] font-medium text-[#999999] hover:text-white transition-colors border-l border-transparent -ml-[1px] pl-[21px] outline-none focus-visible:ring-2 focus-visible:ring-primary rounded-r-md">Resources</NuxtLink>
              <NuxtLink to="/admin/content/careers" exact-active-class="!text-[#10b981] !border-[#10b981]" class="block py-1.5 text-[12px] font-medium text-[#999999] hover:text-white transition-colors border-l border-transparent -ml-[1px] pl-[21px] outline-none focus-visible:ring-2 focus-visible:ring-primary rounded-r-md">Careers</NuxtLink>
            </div>
          </div>

          <!-- 4. Team -->
          <NuxtLink to="/admin/team" exact-active-class="bg-surface-container !text-primary border-l-4 !border-primary" class="flex items-center gap-3 px-4 py-3 text-on-surface-variant hover:text-on-surface hover:bg-surface-container text-[14px] font-medium rounded-lg transition-all duration-200 border-l-4 border-transparent outline-none focus-visible:ring-2 focus-visible:ring-primary" style="font-family: 'Plus Jakarta Sans', sans-serif;">
            <span class="material-symbols-outlined text-[20px] transition-colors" data-icon="badge">badge</span>
            <span>Team</span>
          </NuxtLink>

          <!-- 5. Media Library -->
          <NuxtLink to="/admin/media" exact-active-class="bg-surface-container !text-primary border-l-4 !border-primary" class="flex items-center gap-3 px-4 py-3 text-on-surface-variant hover:text-on-surface hover:bg-surface-container text-[14px] font-medium rounded-lg transition-all duration-200 border-l-4 border-transparent outline-none focus-visible:ring-2 focus-visible:ring-primary" style="font-family: 'Plus Jakarta Sans', sans-serif;">
            <span class="material-symbols-outlined text-[20px] transition-colors" data-icon="perm_media">perm_media</span>
            <span>Media Library</span>
          </NuxtLink>

          <!-- 6. Settings -->
          <NuxtLink to="/admin/settings" exact-active-class="bg-surface-container !text-primary border-l-4 !border-primary" class="flex items-center gap-3 px-4 py-3 text-on-surface-variant hover:text-on-surface hover:bg-surface-container text-[14px] font-medium rounded-lg transition-all duration-200 border-l-4 border-transparent outline-none focus-visible:ring-2 focus-visible:ring-primary" style="font-family: 'Plus Jakarta Sans', sans-serif;">
            <span class="material-symbols-outlined text-[20px] transition-colors" data-icon="settings">settings</span>
            <span>Settings</span>
          </NuxtLink>
        </nav>
      </div>

      <!-- Staff Profile & Logout Drawer -->
      <div class="pt-4 border-t border-outline-variant/65">
        <div class="flex items-center justify-between p-2 rounded-xl bg-surface-container-low border border-outline-variant/40">
          <div class="flex items-center gap-3">
            <div class="w-9 h-9 rounded-full bg-primary-container/20 border border-primary/40 flex items-center justify-center text-primary font-bold text-[12px] overflow-hidden">
              <img v-if="authStore.currentUser?.avatar" :src="authStore.currentUser.avatar" alt="Avatar" class="w-full h-full object-cover" />
              <span v-else>{{ authStore.currentUser?.name?.substring(0, 2).toUpperCase() || 'AD' }}</span>
            </div>
            <div class="flex flex-col overflow-hidden">
              <span class="text-[13px] text-on-surface font-semibold truncate leading-tight">{{ authStore.currentUser?.name || 'Admin User' }}</span>
              <span class="text-[11px] font-semibold text-outline truncate">{{ authStore.currentUser?.role || 'Administrator' }}</span>
            </div>
          </div>
          <button @click="handleLogout" class="p-1.5 rounded-lg text-outline hover:text-primary hover:bg-surface-container transition-colors outline-none focus-visible:ring-2 focus-visible:ring-primary" title="Sign Out">
            <span class="material-symbols-outlined text-[20px]" data-icon="logout">logout</span>
          </button>
        </div>
      </div>
    </aside>

    <!-- ======================================================== -->
    <!-- MAIN WORKSPACE CANVAS                                    -->
    <!-- ======================================================== -->
    <div class="ml-[260px] flex-1 flex flex-col min-w-0 bg-surface-container-lowest">
      <!-- 2. STICKY TOPBAR -->
      <header class="sticky top-0 right-0 z-30 h-16 bg-surface border-b border-outline-variant/65 px-space-xl flex justify-between items-center w-full">
        <div>
          <h1 class="text-[22px] font-bold text-on-surface tracking-tight" style="font-family: 'Plus Jakarta Sans', sans-serif;">{{ route.name?.toString().replace('admin-', '').replace('-', ' ').replace(/\b\w/g, l => l.toUpperCase()) || 'Dashboard' }}</h1>
          <p class="text-[11px] font-semibold text-outline">Real-time enterprise operations &amp; overview</p>
        </div>
        <div class="flex items-center gap-4">
          <!-- Command Search Bar -->
          <div class="relative w-80">
            <span class="material-symbols-outlined absolute left-3.5 top-1/2 -translate-y-1/2 text-outline text-[20px]" data-icon="search">search</span>
            <input class="w-full h-[40px] pl-10 pr-12 rounded-xl bg-surface-container-low border border-outline-variant/65 text-on-surface placeholder:text-outline text-[13px] focus:outline-none focus:border-primary-container focus:ring-1 focus:ring-primary-container transition-all outline-none" placeholder="Search leads, content, or settings..." type="text"/>
            <kbd class="absolute right-3 top-1/2 -translate-y-1/2 px-1.5 py-0.5 rounded text-[10px] bg-surface-container border border-outline-variant/65 text-outline">⌘K</kbd>
          </div>
          <!-- Trailing Actions -->
          <div class="flex items-center gap-2 border-l border-outline-variant/40 pl-4">
            <button class="relative p-2 rounded-full text-outline hover:text-on-surface hover:bg-surface-container transition-colors outline-none focus-visible:ring-2 focus-visible:ring-primary">
              <span class="material-symbols-outlined text-[20px]" data-icon="notifications">notifications</span>
              <span class="absolute top-1.5 right-1.5 w-2 h-2 rounded-full bg-primary-container shadow-[0_0_8px_#10b981]"></span>
            </button>
            <button class="flex items-center gap-2 px-4 py-2 rounded-full bg-gradient-to-r from-primary-container to-secondary-container text-[#00422b] text-[13px] font-bold shadow-[0_0_15px_rgba(16,185,129,0.25)] hover:shadow-[0_0_20px_rgba(16,185,129,0.4)] active:scale-95 transition-all outline-none focus-visible:ring-2 focus-visible:ring-primary">
              <span class="material-symbols-outlined text-[20px]" data-icon="add">add</span>
              <span>New Action</span>
            </button>
            <div class="w-8 h-8 rounded-full bg-surface-container border border-outline-variant/65 flex items-center justify-center text-primary text-[12px] font-medium ml-1 overflow-hidden">
              <img v-if="authStore.currentUser?.avatar" :src="authStore.currentUser.avatar" alt="Avatar" class="w-full h-full object-cover" />
              <span v-else>{{ authStore.currentUser?.name?.substring(0, 2).toUpperCase() || 'AD' }}</span>
            </div>
          </div>
        </div>
      </header>

      <!-- CONTENT BODY -->
      <main class="p-space-xl space-y-space-lg max-w-[1600px] w-full">
        <slot />
      </main>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useAdminAuthStore } from '~/stores/adminAuth'
import { useRouter, useRoute } from 'vue-router'

const authStore = useAdminAuthStore()
const router = useRouter()
const route = useRoute()

function handleLogout() {
  authStore.logout()
  router.push('/admin/login')
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
  font-variation-settings: 'FILL' 0, 'wght' 300, 'GRAD' 0, 'opsz' 24;
  vertical-align: middle;
}
</style>
