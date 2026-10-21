<template>
  <div class="animate-in fade-in zoom-in-95 duration-200 min-h-screen pb-12">
    <!-- MAIN CANVAS CONTENT -->
    <main class="flex-1 flex flex-col gap-6 max-w-[1600px] w-full mx-auto">
      <!-- SECTION 1 — PAGE HEADER -->
      <section class="flex flex-col md:flex-row md:items-center justify-between gap-4 mt-2 border-b border-outline-variant/30 pb-4">
        <div>
          <h1 class="text-[36px] font-bold text-white tracking-tight" style="font-family: 'Plus Jakarta Sans', sans-serif;">Media Library</h1>
          <p class="text-[14px] text-outline mt-1">All images used across your public website and product surfaces.</p>
        </div>
        <div class="flex items-center gap-3">
          <!-- Metric / Quick Stat Chip -->
          <div class="h-9 px-3.5 rounded-full bg-surface border border-outline-variant/65 flex items-center gap-2 text-[12px] font-medium text-on-surface shadow-sm">
            <span class="w-2 h-2 rounded-full bg-primary shadow-[0_0_6px_#10b981]"></span>
            <span class="font-semibold text-white">{{ allMedia.length }} Assets Stored</span>
            <span class="text-outline-variant">•</span>
            <span class="text-on-surface-variant">42.8 MB Used</span>
          </div>
          <!-- View Toggle Control -->
          <div class="p-0.5 rounded-full bg-surface-container-low border border-outline-variant/60 flex items-center">
            <button aria-label="Grid View" class="px-2.5 py-1 rounded-full bg-primary text-on-primary flex items-center justify-center transition-all duration-150 shadow-sm">
              <span class="material-symbols-outlined text-[18px]">grid_view</span>
            </button>
            <button aria-label="List View" class="px-2.5 py-1 rounded-full text-on-surface-variant hover:text-on-surface flex items-center justify-center transition-all duration-150">
              <span class="material-symbols-outlined text-[18px]">view_list</span>
            </button>
          </div>
          <!-- Secondary Action -->
          <button @click="triggerUpload" class="h-9 px-4 rounded-full border border-primary/60 text-primary hover:bg-primary/10 transition-all duration-150 text-[12px] font-semibold flex items-center gap-1.5 active:scale-98">
            <span class="material-symbols-outlined text-[18px]">add</span>
            <span>+ Upload Image</span>
          </button>
        </div>
      </section>

      <!-- SECTION 2 — UPLOAD DROPZONE -->
      <section @click="triggerUpload" :class="{ 'border-primary bg-surface-container/60 shadow-[0_0_24px_rgba(16,185,129,0.12)]': isDragging }" @dragover.prevent="isDragging = true" @dragleave.prevent="isDragging = false" @drop.prevent="handleDrop" class="w-full bg-surface-container-low border-2 border-dashed border-primary/45 rounded-2xl py-10 px-6 flex flex-col items-center justify-center text-center relative group hover:border-primary hover:bg-surface-container/60 hover:shadow-[0_0_24px_rgba(16,185,129,0.12)] transition-all duration-200 cursor-pointer">
        <div class="w-16 h-16 rounded-2xl bg-surface-container border border-outline-variant/65 flex items-center justify-center mb-4 group-hover:scale-105 group-hover:border-primary/50 transition-all duration-200">
          <span class="material-symbols-outlined text-[38px] text-primary">cloud_upload</span>
        </div>
        <h2 class="text-[18px] font-semibold text-white" style="font-family: 'Plus Jakarta Sans', sans-serif;">Drag and drop images here</h2>
        <p class="text-[14px] text-outline mt-1 mb-5">or click to browse your files — JPG, PNG, WebP up to 10MB</p>
        <button class="h-10 px-6 rounded-full bg-gradient-to-r from-primary-container to-inverse-primary text-white text-[12px] font-semibold flex items-center gap-2 shadow-[0_0_16px_rgba(16,185,129,0.3)] hover:shadow-[0_0_24px_rgba(16,185,129,0.45)] active:scale-98 transition-all duration-200">
          <span class="material-symbols-outlined text-[20px]">folder_open</span>
          <span>Browse Files</span>
        </button>
      </section>
      
      <input type="file" ref="fileInput" class="hidden" accept="image/*" @change="handleFileSelect" />

      <!-- SECTION 3 — FILTER BAR -->
      <section class="bg-surface-container-low rounded-xl border border-outline-variant/60 p-3 flex flex-wrap items-center justify-between gap-4">
        <!-- Left Filter Group -->
        <div class="flex flex-wrap items-center gap-3 flex-1">
          <div class="relative min-w-[240px] max-w-sm flex-1">
            <span class="material-symbols-outlined absolute left-3 top-2.5 text-outline text-[18px]">search</span>
            <input v-model="searchQuery" class="w-full h-9 pl-9 pr-3 bg-surface-container-lowest border border-outline-variant/65 rounded-lg text-[13px] text-on-surface placeholder:text-outline focus:outline-none focus:border-primary focus:ring-1 focus:ring-primary transition-all duration-150" placeholder="Search by filename..." type="text"/>
          </div>
          <!-- "Used On" Dropdown -->
          <div class="relative">
            <button class="h-9 px-3 rounded-lg bg-surface border border-outline-variant/65 flex items-center gap-2 text-[12px] font-medium text-on-surface hover:border-outline transition-all duration-150">
              <span class="text-outline">Used On:</span>
              <span class="font-semibold text-white">All Pages</span>
              <span class="material-symbols-outlined text-[16px] text-outline">arrow_drop_down</span>
            </button>
          </div>
          <!-- "Sort by" Dropdown -->
          <div class="relative">
            <button class="h-9 px-3 rounded-lg bg-surface border border-outline-variant/65 flex items-center gap-2 text-[12px] font-medium text-on-surface hover:border-outline transition-all duration-150">
              <span class="text-outline">Sort by:</span>
              <span class="font-semibold text-white">Recently Added</span>
              <span class="material-symbols-outlined text-[16px] text-outline">expand_more</span>
            </button>
          </div>
        </div>
        <!-- Right Controls -->
        <div class="flex items-center gap-4">
          <button @click="searchQuery = ''" class="text-[12px] font-medium text-outline hover:text-primary transition-colors duration-150 flex items-center gap-1">
            <span class="material-symbols-outlined text-[16px]">filter_alt_off</span>
            <span>Clear filters</span>
          </button>
          <div class="h-4 w-px bg-outline-variant/60"></div>
          <span class="text-[12px] font-medium text-on-surface-variant font-semibold">{{ filteredMedia.length }} assets</span>
        </div>
      </section>

      <!-- SECTION 4 — IMAGE GRID (Responsive High-Fidelity Bento Tiles) -->
      <section v-if="filteredMedia.length > 0" class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 xl:grid-cols-5 gap-4">
        
        <article v-for="img in filteredMedia" :key="img.name" class="bg-surface-container-low rounded-xl border border-outline-variant/40 overflow-hidden relative group aspect-[4/3] flex flex-col shadow-sm transition-all duration-200 hover:border-primary">
          <img :src="img.url" :alt="img.name" class="w-full h-full object-cover transition-transform duration-300 group-hover:scale-105"/>
          <div class="absolute top-2.5 left-2.5 z-10">
            <span :class="img.badgeClass" class="px-2 py-0.5 rounded-full text-[10px] font-semibold border backdrop-blur-md">{{ img.category }}</span>
          </div>
          
          <!-- Dark Hover Overlay -->
          <div class="absolute inset-0 bg-[#0b0f14]/85 backdrop-blur-[2px] opacity-0 group-hover:opacity-100 transition-opacity duration-200 p-3 flex flex-col justify-between z-20">
            <div class="flex justify-end gap-1.5">
              <button @click="viewImage(img)" class="w-8 h-8 rounded-lg bg-surface border border-outline-variant/65 flex items-center justify-center text-on-surface hover:text-primary hover:border-primary transition-all duration-150" title="Preview Asset">
                <span class="material-symbols-outlined text-[18px]">zoom_in</span>
              </button>
              <button @click="deleteImage(img)" class="w-8 h-8 rounded-lg bg-surface border border-outline-variant/65 flex items-center justify-center text-on-surface hover:text-error hover:border-error transition-all duration-150" title="Delete Asset">
                <span class="material-symbols-outlined text-[18px]">delete</span>
              </button>
            </div>
            <div class="flex flex-col gap-1">
              <span class="text-[12px] font-semibold text-white truncate">{{ img.name }}</span>
              <span class="text-[11px] font-medium text-outline">{{ img.dimensions }} • {{ img.size }}</span>
              <span class="text-[10px] font-semibold text-primary">Used in {{ Math.floor(Math.random() * 5) + 1 }} locations</span>
            </div>
          </div>
        </article>

      </section>

      <!-- SECTION 5 — ZERO RESULTS STATE -->
      <section v-else class="mt-4 pt-6">
        <div class="bg-surface-container-low rounded-2xl border border-outline-variant/60 overflow-hidden p-8 flex flex-col items-center justify-center text-center">
          <div class="w-14 h-14 rounded-2xl bg-surface border border-outline-variant/60 flex items-center justify-center mb-3 text-outline">
            <span class="material-symbols-outlined text-[32px]">image_not_supported</span>
          </div>
          <h4 class="text-[18px] font-semibold text-white" style="font-family: 'Plus Jakarta Sans', sans-serif;">No images found</h4>
          <p class="text-[14px] text-outline mt-1 max-w-sm">We couldn’t find any assets matching your active filter criteria. Try adjusting your filters or search term.</p>
          <button @click="searchQuery = ''" class="mt-4 text-[12px] text-primary hover:underline flex items-center gap-1 font-semibold">
            <span class="material-symbols-outlined text-[16px]">restart_alt</span>
            <span>Clear all filters</span>
          </button>
        </div>
      </section>

      <!-- SECTION 6 — IMAGE PREVIEW (Interactive Inspector Section) -->
      <section v-if="selectedImage" class="mt-4 pt-6 border-t border-outline-variant/65 animate-in slide-in-from-bottom-4 duration-300">
        <div class="flex items-center justify-between mb-4">
          <div class="flex items-center gap-2">
            <span class="material-symbols-outlined text-primary text-[22px]">visibility</span>
            <h2 class="text-[18px] font-semibold text-white" style="font-family: 'Plus Jakarta Sans', sans-serif;">Active Inspector Inspection</h2>
          </div>
          <span class="text-[11px] font-semibold text-outline">Direct Asset Telemetry • Live Node</span>
        </div>
        
        <div class="w-full max-w-4xl mx-auto bg-surface-container-low rounded-2xl border border-primary/40 p-6 shadow-[0_8px_32px_rgba(0,0,0,0.6),0_0_20px_rgba(16,185,129,0.15)] flex flex-col gap-6">
          <!-- Top Modal Bar -->
          <div class="flex items-center justify-between pb-4 border-b border-outline-variant/50">
            <div class="flex items-center gap-3">
              <span class="w-2.5 h-2.5 rounded-full bg-primary shadow-[0_0_8px_#10b981]"></span>
              <h3 class="text-[16px] font-semibold text-white tracking-tight" style="font-family: 'Plus Jakarta Sans', sans-serif;">{{ selectedImage.name }}</h3>
              <span class="text-[11px] font-semibold text-primary bg-primary/10 px-2 py-0.5 rounded-md border border-primary/25">Published Asset</span>
            </div>
            <button @click="selectedImage = null" class="w-8 h-8 rounded-full bg-surface-container hover:bg-surface text-on-surface-variant hover:text-white flex items-center justify-center transition-colors">
              <span class="material-symbols-outlined text-[18px]">close</span>
            </button>
          </div>
          
          <!-- Main Inspector Content Split -->
          <div class="grid grid-cols-1 md:grid-cols-12 gap-6 items-start">
            <!-- Large Image Preview Frame -->
            <div class="md:col-span-7 rounded-xl overflow-hidden border border-outline-variant/60 bg-surface-container-lowest relative group">
              <img :src="selectedImage.url" class="w-full h-auto aspect-video object-contain" />
              <div class="absolute bottom-2 right-2 px-2 py-1 bg-surface-container-lowest/80 backdrop-blur-md rounded text-[11px] font-medium text-outline border border-outline-variant/40">
                {{ selectedImage.dimensions }}
              </div>
            </div>
            
            <!-- Two-Column Metadata Panel -->
            <div class="md:col-span-5 flex flex-col gap-4">
              <div class="bg-surface rounded-xl p-4 border border-outline-variant/50 flex flex-col gap-3">
                <div class="flex justify-between items-center text-[12px]">
                  <span class="text-outline font-medium">File Size</span>
                  <span class="text-white font-semibold">{{ selectedImage.size }}</span>
                </div>
                <div class="flex justify-between items-center text-[12px]">
                  <span class="text-outline font-medium">MIME Type</span>
                  <span class="text-white font-medium">{{ selectedImage.name.includes('.svg') ? 'image/svg+xml' : selectedImage.name.includes('.webp') ? 'image/webp' : 'image/jpeg' }}</span>
                </div>
                <div class="flex justify-between items-center text-[12px]">
                  <span class="text-outline font-medium">Dimensions</span>
                  <span class="text-white font-semibold">{{ selectedImage.dimensions }}</span>
                </div>
                <div class="flex justify-between items-center text-[12px]">
                  <span class="text-outline font-medium">Uploaded On</span>
                  <span class="text-white font-semibold">{{ selectedImage.date }}</span>
                </div>
                <div class="flex justify-between items-center text-[12px]">
                  <span class="text-outline font-medium">Uploader</span>
                  <span class="text-primary font-semibold flex items-center gap-1">
                    <span class="material-symbols-outlined text-[14px]">verified_user</span> Admin User
                  </span>
                </div>
              </div>
              
              <!-- Used On Locations -->
              <div class="flex flex-col gap-2 mt-2">
                <span class="text-[11px] font-semibold uppercase tracking-wider text-outline">Active Surface Associations (3)</span>
                <div class="flex flex-wrap gap-2">
                  <span class="px-2.5 py-1 rounded-full text-[11px] font-semibold bg-surface-container text-on-surface border border-outline-variant/60 flex items-center gap-1.5">
                    <span class="w-1.5 h-1.5 rounded-full bg-primary"></span> Home Hero
                  </span>
                  <span class="px-2.5 py-1 rounded-full text-[11px] font-semibold bg-surface-container text-on-surface border border-outline-variant/60 flex items-center gap-1.5">
                    <span class="w-1.5 h-1.5 rounded-full bg-primary"></span> Products / POS &amp; Billing
                  </span>
                </div>
              </div>
            </div>
          </div>
          
          <!-- Bottom Action Bar -->
          <div class="flex flex-wrap items-center justify-between gap-4 pt-4 border-t border-outline-variant/50">
            <div class="flex items-center gap-2">
              <button @click="copyUrl" class="h-9 px-4 rounded-full border border-outline-variant/80 text-on-surface hover:text-white hover:bg-surface-container text-[12px] font-medium flex items-center gap-2 transition-all">
                <span class="material-symbols-outlined text-[16px]">link</span>
                <span>Copy URL</span>
              </button>
              <button @click="triggerUpload" class="h-9 px-4 rounded-full border border-primary/60 text-primary hover:bg-primary/10 text-[12px] font-medium flex items-center gap-1.5 transition-all">
                <span class="material-symbols-outlined text-[16px]">swap_horiz</span>
                <span>Replace Image</span>
              </button>
            </div>
            <div class="flex items-center gap-2">
              <button @click="selectedImage = null" class="h-9 px-5 rounded-full bg-gradient-to-r from-primary-container to-inverse-primary hover:shadow-[0_0_16px_rgba(16,185,129,0.35)] text-white text-[12px] font-semibold transition-all">
                Close Preview
              </button>
            </div>
          </div>
        </div>
      </section>

    </main>

    <AdminToast ref="toastRef" />
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'

definePageMeta({ layout: 'admin', middleware: ['admin-auth'] })

const toastRef = ref()
const fileInput = ref<HTMLInputElement | null>(null)
const isDragging = ref(false)
const searchQuery = ref('')
const selectedImage = ref<any>(null)

// High-Fidelity Local Assets
const initialMedia: any[] = []

const localUploads = ref<any[]>([])
const allMedia = computed(() => [...localUploads.value, ...initialMedia])

const filteredMedia = computed(() => {
  if (!searchQuery.value) return allMedia.value
  const query = searchQuery.value.toLowerCase()
  return allMedia.value.filter(img => img.name.toLowerCase().includes(query))
})

function viewImage(img: any) {
  selectedImage.value = img
  window.scrollTo({ top: document.body.scrollHeight, behavior: 'smooth' })
}

function deleteImage(img: any) {
  // Logic to delete an image
  if (initialMedia.includes(img)) {
    const idx = initialMedia.indexOf(img)
    initialMedia.splice(idx, 1)
  } else {
    localUploads.value = localUploads.value.filter(u => u !== img)
  }
  toastRef.value?.addToast(`${img.name} deleted successfully.`)
  if (selectedImage.value === img) selectedImage.value = null
}

function triggerUpload() {
  fileInput.value?.click()
}

function handleFileSelect(e: Event) {
  const file = (e.target as HTMLInputElement).files?.[0]
  if (file) addLocalFile(file)
}

function handleDrop(e: DragEvent) {
  isDragging.value = false
  const file = e.dataTransfer?.files?.[0]
  if (file) addLocalFile(file)
}

function addLocalFile(file: File) {
  const url = URL.createObjectURL(file)
  localUploads.value.unshift({
    url,
    name: file.name,
    size: (file.size / 1024 / 1024).toFixed(2) + ' MB',
    dimensions: 'Pending processing...',
    date: new Date().toLocaleString('en-GB', { day: 'numeric', month: 'short', year: 'numeric', hour: '2-digit', minute: '2-digit' }),
    category: 'Uncategorized',
    badgeClass: 'bg-surface/80 text-on-surface border-outline-variant/60'
  })
  toastRef.value?.addToast(`${file.name} uploaded successfully`)
}

function copyUrl() {
  if (selectedImage.value) {
    navigator.clipboard.writeText(selectedImage.value.url)
    toastRef.value?.addToast('Raw asset URL copied to clipboard')
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&family=Plus+Jakarta+Sans:wght@500;600;700;800&display=swap');

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

.custom-scroll::-webkit-scrollbar {
  width: 6px;
  height: 6px;
}
.custom-scroll::-webkit-scrollbar-track {
  background: #101419;
}
.custom-scroll::-webkit-scrollbar-thumb {
  background: rgba(60, 74, 66, 0.6);
  border-radius: 4px;
}
.custom-scroll::-webkit-scrollbar-thumb:hover {
  background: rgba(16, 185, 129, 0.4);
}

/* Base animations */
.fade-in {
  animation: fadeIn 0.2s ease-out forwards;
}
.zoom-in-95 {
  animation: zoomIn 0.2s cubic-bezier(0.16, 1, 0.3, 1) forwards;
}
.slide-in-from-bottom-4 {
  animation: slideInBottom 0.3s cubic-bezier(0.16, 1, 0.3, 1) forwards;
}

@keyframes fadeIn {
  from { opacity: 0; }
  to { opacity: 1; }
}

@keyframes zoomIn {
  from { transform: scale(0.97); }
  to { transform: scale(1); }
}

@keyframes slideInBottom {
  from { transform: translateY(20px); opacity: 0; }
  to { transform: translateY(0); opacity: 1; }
}

.hidden { display: none; }
</style>
