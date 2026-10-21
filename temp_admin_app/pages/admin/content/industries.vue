<template>
  <div class="space-y-[24px]">
    <!-- LIST VIEW -->
    <div v-if="!editingItem">
      <div class="flex flex-col md:flex-row md:items-center justify-between gap-4 mb-6">
        <div>
          <h1 class="text-[28px] font-bold text-on-surface tracking-tight" style="font-family: 'Plus Jakarta Sans', sans-serif;">Industries</h1>
          <p class="text-[14px] text-on-surface-variant mt-1">Manage industry pages content.</p>
        </div>
      </div>

      <AdminDataTable :data="industries" :columns="columns" searchPlaceholder="Search industries...">
        <template #cell-image="{ row }">
          <img :src="row.image" class="w-12 h-8 object-cover rounded border border-outline-variant/65" alt="Industry thumbnail" v-if="row.image" />
        </template>
        <template #cell-actions="{ row }">
          <button class="p-1.5 text-on-surface-variant hover:text-primary transition-colors rounded flex items-center gap-1" @click="editIndustry(row)">
            <span class="material-symbols-outlined text-[18px]">edit</span>
            <span class="text-[12px] font-semibold">Edit</span>
          </button>
        </template>
      </AdminDataTable>
    </div>

    <!-- DETAIL / EDIT VIEW -->
    <div v-else class="pb-24 animate-in fade-in zoom-in-95 duration-200">
      <!-- SECTION 1 — PAGE HEADER -->
      <section class="flex flex-col md:flex-row md:items-center justify-between gap-4 pb-2">
        <div>
          <!-- Breadcrumb -->
          <nav class="flex items-center gap-2 text-[11px] font-semibold text-outline mb-1.5">
            <span>Content</span>
            <span class="text-outline-variant">/</span>
            <span>Industries</span>
            <span class="text-outline-variant">/</span>
            <span class="text-on-surface">{{ formData.name || 'Editing' }}</span>
          </nav>
          <h1 class="text-[28px] font-bold text-on-surface tracking-tight" style="font-family: 'Plus Jakarta Sans', sans-serif;">Edit Industry</h1>
        </div>
        <!-- Action Button Pair -->
        <div class="flex items-center gap-3">
          <button @click="cancelEdit" class="px-6 py-2 rounded-full border-[1.5px] border-primary-container text-on-surface text-[16px] font-semibold hover:bg-primary-container/10 transition-all duration-200 active:scale-95" style="font-family: 'Plus Jakarta Sans', sans-serif;">
            Cancel
          </button>
          <button @click="saveIndustry" class="flex items-center gap-2 px-6 py-2 rounded-full bg-gradient-to-r from-primary-container to-[#059669] text-white text-[16px] font-semibold shadow-[0_0_16px_rgba(16,185,129,0.35)] hover:shadow-[0_0_22px_rgba(16,185,129,0.5)] transition-all duration-200 active:scale-98" style="font-family: 'Plus Jakarta Sans', sans-serif;">
            <span class="material-symbols-outlined text-[16px] font-semibold">check</span>
            <span>Save Changes</span>
          </button>
        </div>
      </section>

      <!-- SECTION 2 — TWO-COLUMN LAYOUT -->
      <div class="grid grid-cols-1 lg:grid-cols-12 gap-6 items-start mt-4">
        <!-- LEFT COLUMN: FORM CARD -->
        <div class="lg:col-span-7 bg-surface-container-low border border-outline-variant/65 rounded-2xl p-6 space-y-6">
          
          <!-- 1. Headline -->
          <div class="space-y-1.5">
            <label class="block text-[11px] font-semibold text-outline uppercase tracking-wider">Headline</label>
            <input v-model="formData.headline" class="w-full h-[42px] px-3.5 bg-surface-container-lowest border border-outline-variant/65 rounded-xl text-on-surface text-[14px] focus:border-primary-container focus:ring-1 focus:ring-primary-container focus:outline-none transition-all duration-200" type="text"/>
          </div>

          <!-- 2. Subline -->
          <div class="space-y-1.5">
            <label class="block text-[11px] font-semibold text-outline uppercase tracking-wider">Subline</label>
            <textarea v-model="formData.subline" class="w-full p-3 bg-surface-container-lowest border border-outline-variant/65 rounded-xl text-on-surface text-[14px] focus:border-primary-container focus:ring-1 focus:ring-primary-container focus:outline-none transition-all duration-200 resize-none leading-relaxed" rows="2"></textarea>
          </div>

          <!-- 3. Hero Photo Description -->
          <div class="space-y-1.5">
            <label class="block text-[11px] font-semibold text-outline uppercase tracking-wider">Hero Photo Description</label>
            <input v-model="photoDescription" class="w-full h-[42px] px-3.5 bg-surface-container-lowest border border-outline-variant/65 rounded-xl text-on-surface text-[14px] focus:border-primary-container focus:ring-1 focus:ring-primary-container focus:outline-none transition-all duration-200" type="text"/>
          </div>

          <!-- 4. Key Features Section -->
          <div class="space-y-3 pt-2">
            <div class="flex items-center justify-between pb-1 border-b border-outline-variant/40">
              <span class="text-[18px] font-semibold text-on-surface" style="font-family: 'Plus Jakarta Sans', sans-serif;">Key Features ({{ features.length }})</span>
              <button @click="addFeature" class="text-primary hover:text-primary-fixed-dim text-[13px] font-semibold flex items-center gap-1 transition-colors">
                <span class="material-symbols-outlined text-[14px]">add</span>
                <span>Add Feature</span>
              </button>
            </div>
            <div class="space-y-2">
              <div v-for="(feat, idx) in features" :key="idx" class="flex items-center gap-2 p-1.5 bg-surface-container rounded-xl border border-outline-variant/40 hover:border-outline-variant transition-colors group">
                <span class="material-symbols-outlined text-outline cursor-grab select-none px-1 group-hover:text-primary">drag_indicator</span>
                <input v-model="features[idx]" class="flex-1 h-9 px-3 bg-surface-container-lowest border border-outline-variant/65 rounded-[10px] text-[13px] text-on-surface focus:border-primary focus:ring-1 focus:ring-primary focus:outline-none" type="text"/>
                <button @click="features.splice(idx, 1)" class="p-1.5 text-outline hover:text-error transition-colors rounded-lg hover:bg-surface-container-high" title="Delete">
                  <span class="material-symbols-outlined text-[18px]">delete</span>
                </button>
              </div>
            </div>
          </div>

          <!-- 5. Frequently Asked Questions Section -->
          <div class="space-y-3 pt-2">
            <div class="flex items-center justify-between pb-1 border-b border-outline-variant/40">
              <span class="text-[18px] font-semibold text-on-surface" style="font-family: 'Plus Jakarta Sans', sans-serif;">FAQ ({{ faqs.length }})</span>
              <button @click="addFaq" class="text-primary hover:text-primary-fixed-dim text-[13px] font-semibold flex items-center gap-1 transition-colors">
                <span class="material-symbols-outlined text-[14px]">add</span>
                <span>Add Question</span>
              </button>
            </div>
            <div class="space-y-3">
              <div v-for="(faq, idx) in faqs" :key="idx" class="p-4 bg-surface-container rounded-xl border border-outline-variant/65 space-y-2.5 relative group">
                <button @click="faqs.splice(idx, 1)" class="absolute top-3.5 right-3.5 text-outline hover:text-error transition-colors" title="Delete FAQ">
                  <span class="material-symbols-outlined text-[18px]">delete</span>
                </button>
                <div class="space-y-1 pr-6">
                  <label class="text-[11px] font-semibold text-outline">Question</label>
                  <input v-model="faq.q" class="w-full h-9 px-3 bg-surface-container-lowest border border-outline-variant/65 rounded-lg text-[13px] text-on-surface font-medium focus:border-primary focus:outline-none" type="text"/>
                </div>
                <div class="space-y-1">
                  <label class="text-[11px] font-semibold text-outline">Answer</label>
                  <textarea v-model="faq.a" class="w-full p-2.5 bg-surface-container-lowest border border-outline-variant/65 rounded-lg text-[13px] text-on-surface-variant focus:border-primary focus:outline-none resize-none" rows="2"></textarea>
                </div>
              </div>
            </div>
          </div>

          <!-- 6. Modules Utilized -->
          <div class="space-y-2.5 pt-2">
            <label class="block text-[11px] font-semibold text-outline uppercase tracking-wider">Works With (Interconnected Modules)</label>
            <div class="flex flex-wrap gap-2">
              <button v-for="mod in interconnectedModules" :key="mod.name" @click="mod.selected = !mod.selected" :class="['px-3.5 py-1.5 rounded-full font-medium flex items-center gap-1.5 transition-colors text-[13px]', mod.selected ? 'bg-primary-container/20 border border-primary-container text-primary shadow-[0_0_10px_rgba(16,185,129,0.15)]' : 'bg-surface-container border border-outline-variant/65 text-on-surface-variant hover:border-outline hover:text-on-surface']">
                <span v-if="mod.selected" class="w-1.5 h-1.5 rounded-full bg-primary-container"></span>
                <span>{{ mod.name }}</span>
              </button>
            </div>
          </div>
        </div>

        <!-- RIGHT COLUMN: PREVIEW PANEL (sticky) -->
        <div class="lg:col-span-5 sticky top-24 bg-surface-container-low border border-outline-variant/65 rounded-2xl p-6 space-y-5">
          <!-- 1. Hero Image -->
          <div class="space-y-3">
            <div class="flex items-center justify-between">
              <label class="text-[11px] font-semibold text-outline uppercase tracking-wider">Hero Image</label>
              <span class="text-[11px] font-mono text-outline">1920 × 1080 px</span>
            </div>
            <div class="relative overflow-hidden rounded-xl border border-outline-variant/65 aspect-[16/9] bg-surface-container-lowest group">
              <img :src="formData.image || 'https://lh3.googleusercontent.com/aida-public/AB6AXuDEyctRkTv2BRqiWnBJNKUIXgri_zlZbhVrJVmmQIfzwBY52W2_fVbAG5CysqrhLatf0KuVK-hM103UFk5sVBQSGRDYmHP2Gzt2LbEcCFZUtqBN6Ac8i1dkOLm6IuANm6L1CUEqjxk2ogI2SNwiAnRM4XU3HzscxOFbC25L79E6YeSMYsgBMhuy3CS9vGrLoXdB3j0blTDxTnRby9raDCEZFpxo9y5h_MTluifl4aFrJypfdeIQ3YZN'" class="w-full h-full object-cover group-hover:scale-105 transition-transform duration-500" alt="Preview"/>
              <div class="absolute inset-0 bg-gradient-to-t from-surface-container-lowest/80 via-transparent to-transparent pointer-events-none"></div>
              <div class="absolute bottom-2.5 left-3 flex items-center gap-1.5 px-2 py-0.5 rounded bg-surface/80 backdrop-blur-sm border border-outline-variant/40 text-[11px] font-mono text-on-surface">
                <span class="w-1.5 h-1.5 rounded-full bg-primary"></span>
                <span>hero_image_lux.avif</span>
              </div>
            </div>
            <!-- Replace Image Button -->
            <button class="w-full py-2 px-4 rounded-full border-[1.5px] border-primary-container text-white font-semibold text-[16px] hover:bg-primary-container/10 flex items-center justify-center gap-2 transition-all duration-150" style="font-family: 'Plus Jakarta Sans', sans-serif;">
              <span class="material-symbols-outlined text-[18px] text-primary">perm_media</span>
              <span>Replace Image</span>
            </button>
          </div>

          <div class="border-t border-outline-variant/65"></div>

          <!-- Public Page Preview Meta -->
          <div class="space-y-3">
            <div class="flex items-center justify-between">
              <span class="text-[12px] font-semibold tracking-wider text-outline uppercase">PUBLIC PAGE PREVIEW</span>
              <span class="text-[11px] text-outline font-mono">Viewport: Desktop 1440px</span>
            </div>
            
            <!-- Boxed Live Preview -->
            <div class="bg-surface-container-lowest border border-outline-variant/65 rounded-xl p-6 relative overflow-hidden shadow-inner">
              <div class="flex items-center gap-2 mb-4">
                <span class="inline-flex items-center gap-1.5 px-2 py-0.5 rounded-full bg-primary/10 border border-primary/30 text-primary text-[11px] font-mono font-medium">
                  <span class="w-1.5 h-1.5 rounded-full bg-primary animate-pulse"></span>
                  LIVE PUBLIC PREVIEW
                </span>
              </div>
              <div class="mb-2">
                <span class="inline-block px-2.5 py-0.5 rounded-full bg-surface-container-high border border-outline-variant/50 text-primary text-[11px] font-mono uppercase tracking-wider">
                  {{ formData.name || 'Industry Category' }}
                </span>
              </div>
              
              <h2 class="text-4xl italic text-white tracking-normal leading-tight mb-3" style="font-family: 'Cormorant Garamond', serif;">
                {{ formData.headline || 'Industry Headline' }}
              </h2>
              <p class="text-[14px] text-on-surface-variant leading-relaxed">
                {{ formData.subline || 'Industry Subline Description' }}
              </p>
              
              <div class="mt-6 pt-4 border-t border-outline-variant/30 flex items-center justify-between text-[11px] font-semibold text-outline">
                <span>Modules Connected: {{ interconnectedModules.filter(m => m.selected).length }}</span>
                <span class="text-primary font-mono text-[11px]">99.99% Uptime SLA</span>
              </div>
            </div>
            
            <p class="text-[12px] text-outline text-center">
              This is how visitors will see this page
            </p>
          </div>
        </div>
      </div>
      
      <!-- UNSAVED CHANGES INDICATOR (Sticky Bottom Bar) -->
      <div v-if="hasUnsavedChanges" class="fixed bottom-0 left-[260px] right-0 bg-[#1c2228] border-t-[1.5px] border-primary-container px-6 py-3 flex items-center justify-between shadow-[0_-8px_30px_rgba(0,0,0,0.85)] z-50 backdrop-blur-md">
        <!-- Left side -->
        <div class="flex items-center gap-3">
          <span class="relative flex h-2.5 w-2.5">
            <span class="animate-ping absolute inline-flex h-full w-full rounded-full bg-primary opacity-75"></span>
            <span class="relative inline-flex rounded-full h-2.5 w-2.5 bg-primary-container shadow-[0_0_8px_#10b981]"></span>
          </span>
          <div class="flex items-baseline gap-2">
            <span class="text-white text-[14px] font-medium">You have unsaved changes</span>
            <span class="text-outline text-[12px] hidden sm:inline">• Last draft autosaved just now</span>
          </div>
        </div>
        <!-- Right side -->
        <div class="flex items-center gap-3">
          <button @click="cancelEdit" class="px-5 py-1.5 rounded-full border-[1.5px] border-primary-container text-white text-[14px] font-medium hover:bg-primary-container/10 transition-all duration-150 active:scale-95">
            Cancel
          </button>
          <button @click="saveIndustry" class="flex items-center gap-1.5 px-5 py-1.5 rounded-full bg-gradient-to-r from-primary-container to-[#059669] text-white text-[14px] font-semibold shadow-[0_0_14px_rgba(16,185,129,0.35)] hover:shadow-[0_0_18px_rgba(16,185,129,0.5)] transition-all duration-150 active:scale-98">
            <span class="material-symbols-outlined text-[16px]">save</span>
            <span>Save Changes</span>
          </button>
        </div>
      </div>
      
    </div>

    <AdminToast ref="toastRef" />
  </div>
</template>

<script setup lang="ts">
import { ref, watch } from 'vue'
import { useIndustries } from '~/composables/useIndustries'

definePageMeta({ layout: 'admin', middleware: ['admin-auth'] })

const toastRef = ref()
const { industries } = useIndustries()

const columns = [
  { key: 'image', label: 'Image' },
  { key: 'name', label: 'Industry' },
  { key: 'headline', label: 'Headline' },
  { key: 'actions', label: 'Actions' },
]

const editingItem = ref<any>(null)
const formData = ref<any>({})
const hasUnsavedChanges = ref(false)

const photoDescription = ref('')

const features = ref<string[]>([])

const faqs = ref<{q: string, a: string}[]>([])

const interconnectedModules = ref([
  { name: 'Inventory Engine', selected: false },
  { name: 'Kitchen Display KDS', selected: false },
  { name: 'Analytics & Telemetry', selected: false },
  { name: 'Staff & Payroll', selected: false },
  { name: 'Multi-Outlet Central', selected: false },
  { name: 'Customer Loyalty', selected: false },
  { name: 'Supply Chain EDI', selected: false },
  { name: 'Accounting Sync', selected: false },
])

function editIndustry(item: any) {
  editingItem.value = item
  formData.value = { ...item }
  hasUnsavedChanges.value = false
}

function cancelEdit() {
  editingItem.value = null
  formData.value = {}
  hasUnsavedChanges.value = false
}

function saveIndustry() {
  const idx = industries.value.findIndex((p: any) => p.slug === editingItem.value.slug)
  if (idx > -1) {
    industries.value[idx] = { ...industries.value[idx], ...formData.value }
    toastRef.value?.addToast('Industry content updated successfully')
  }
  editingItem.value = null
  hasUnsavedChanges.value = false
}

function addFeature() {
  features.value.push('New Key Feature')
  hasUnsavedChanges.value = true
}

function addFaq() {
  faqs.value.push({ q: 'New Question?', a: 'Answer...' })
  hasUnsavedChanges.value = true
}

// Watch for changes to show unsaved bar
watch(() => formData.value, () => {
  if (editingItem.value) hasUnsavedChanges.value = true
}, { deep: true })
watch(() => features.value, () => {
  if (editingItem.value) hasUnsavedChanges.value = true
}, { deep: true })
watch(() => faqs.value, () => {
  if (editingItem.value) hasUnsavedChanges.value = true
}, { deep: true })
watch(() => interconnectedModules.value, () => {
  if (editingItem.value) hasUnsavedChanges.value = true
}, { deep: true })

</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Cormorant+Garamond:ital,wght@0,400;0,600;1,400;1,600&family=Inter:wght@400;500;600;700&family=Plus+Jakarta+Sans:wght@500;600;700;800&display=swap');

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

/* Animations for transitioning to edit mode smoothly */
.fade-in {
  animation: fadeIn 0.3s ease-out forwards;
}
.zoom-in-95 {
  animation: zoomIn 0.3s cubic-bezier(0.16, 1, 0.3, 1) forwards;
}

@keyframes fadeIn {
  from { opacity: 0; }
  to { opacity: 1; }
}

@keyframes zoomIn {
  from { transform: scale(0.97); }
  to { transform: scale(1); }
}
</style>
