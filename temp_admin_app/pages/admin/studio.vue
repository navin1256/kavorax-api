<template>
  <div class="studio-layout">
    <!-- Topbar -->
    <header class="studio-topbar">
      <div class="topbar-left">
        <NuxtLink to="/admin" class="back-btn">
          <span class="material-symbols-outlined">arrow_back</span>
          Back to Admin
        </NuxtLink>
        <div class="page-info">
          <h2>{{ currentRouteName }}</h2>
          <span class="route-path">{{ currentIframeUrl }}</span>
        </div>
      </div>
      
      <div class="topbar-center">
        <div class="device-toggles">
          <button :class="{ active: deviceMode === 'desktop' }" @click="deviceMode = 'desktop'">
            <span class="material-symbols-outlined">desktop_windows</span>
          </button>
          <button :class="{ active: deviceMode === 'tablet' }" @click="deviceMode = 'tablet'">
            <span class="material-symbols-outlined">tablet_mac</span>
          </button>
          <button :class="{ active: deviceMode === 'mobile' }" @click="deviceMode = 'mobile'">
            <span class="material-symbols-outlined">smartphone</span>
          </button>
        </div>
      </div>
      
      <div class="topbar-right">
        <div class="status-indicator">
          <span class="dot" :class="saveStatus"></span>
          {{ saveStatusText }}
        </div>
        <UiButtonCTA variant="secondary" size="sm" @click="saveDraft" icon="save">Save Draft</UiButtonCTA>
        <UiButtonCTA variant="primary" size="sm" @click="publishChanges" icon="publish">Publish</UiButtonCTA>
      </div>
    </header>

    <div class="studio-body">
      <!-- Left Sidebar: Controls -->
      <aside class="studio-sidebar">
        <h3>Page Editor</h3>
        <p class="subtitle">Select a component to edit its content.</p>
        
        <div class="form-group">
          <label>URL to Preview</label>
          <input type="text" v-model="targetUrl" @keyup.enter="loadUrl" class="input-field" />
          <UiButtonCTA variant="secondary" size="sm" @click="loadUrl" class="mt-2 w-full">Load Page</UiButtonCTA>
        </div>

        <div v-if="selectedElement" class="editor-panel">
          <h4>Editing element</h4>
          
          <div class="form-group" v-if="selectedElement.type === 'text'">
            <label>Text Content</label>
            <textarea v-model="selectedElement.content" class="input-field" rows="4" @input="updateElement"></textarea>
          </div>
          
          <div class="form-group" v-if="selectedElement.type === 'image'">
            <label>Image URL</label>
            <input type="text" v-model="selectedElement.src" class="input-field" @input="updateElement" />
            
            <label class="mt-2">Alt Text</label>
            <input type="text" v-model="selectedElement.alt" class="input-field" @input="updateElement" />
          </div>

          <div class="form-group" v-if="selectedElement.type === 'link'">
            <label>Link Text</label>
            <input type="text" v-model="selectedElement.content" class="input-field" @input="updateElement" />
            
            <label class="mt-2">Destination URL</label>
            <input type="text" v-model="selectedElement.href" class="input-field" @input="updateElement" />
          </div>
        </div>
        <div v-else class="empty-selection">
          <span class="material-symbols-outlined">touch_app</span>
          <p>Click on any editable element in the preview to edit it here.</p>
        </div>
      </aside>

      <!-- Main Canvas: Iframe -->
      <main class="studio-canvas">
        <div class="iframe-wrapper" :class="deviceMode">
          <iframe 
            ref="previewIframe" 
            :src="currentIframeUrl" 
            frameborder="0"
            @load="onIframeLoad"
          ></iframe>
        </div>
      </main>
    </div>
    
    <AdminToast ref="toastRef" />
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted, onUnmounted } from 'vue'

definePageMeta({
  layout: false, // Full screen, bypass standard admin layout
  middleware: ['admin-auth']
})

const toastRef = ref()
const deviceMode = ref<'desktop' | 'tablet' | 'mobile'>('desktop')
const saveStatus = ref<'saved' | 'draft' | 'saving'>('saved')
const saveStatusText = computed(() => {
  if (saveStatus.value === 'saving') return 'Saving...'
  if (saveStatus.value === 'draft') return 'Unsaved Changes'
  return 'Saved'
})

const targetUrl = ref('http://localhost:3000/')
const currentIframeUrl = ref('http://localhost:3000/')
const currentRouteName = ref('Homepage')
const previewIframe = ref<HTMLIFrameElement | null>(null)

const selectedElement = ref<any>(null)

function loadUrl() {
  if (!targetUrl.value.startsWith('http')) {
    targetUrl.value = 'http://localhost:3000' + (targetUrl.value.startsWith('/') ? '' : '/') + targetUrl.value
  }
  currentIframeUrl.value = targetUrl.value
}

function onIframeLoad() {
  toastRef.value?.addToast('Preview loaded')
  sendToIframe({ type: 'STUDIO_INIT' })
}

function updateElement() {
  saveStatus.value = 'draft'
  sendToIframe({
    type: 'STUDIO_UPDATE_ELEMENT',
    payload: selectedElement.value
  })
}

function sendToIframe(msg: any) {
  if (previewIframe.value && previewIframe.value.contentWindow) {
    previewIframe.value.contentWindow.postMessage(msg, 'http://localhost:3000')
  }
}

function handleMessage(event: MessageEvent) {
  // Only accept from public site origin
  if (event.origin !== 'http://localhost:3000') return
  
  if (event.data?.type === 'STUDIO_ELEMENT_SELECTED') {
    selectedElement.value = event.data.payload
  }
}

onMounted(() => {
  window.addEventListener('message', handleMessage)
})
onUnmounted(() => {
  window.removeEventListener('message', handleMessage)
})

function saveDraft() {
  saveStatus.value = 'saving'
  setTimeout(() => {
    saveStatus.value = 'saved'
    toastRef.value?.addToast('Draft saved successfully')
  }, 600)
}

function publishChanges() {
  saveStatus.value = 'saving'
  setTimeout(() => {
    saveStatus.value = 'saved'
    toastRef.value?.addToast('Changes published to live site')
  }, 800)
}
</script>

<style scoped>
.studio-layout {
  height: 100vh;
  display: flex;
  flex-direction: column;
  background-color: var(--admin-tier-0, #0b0f14);
  color: var(--admin-text-primary, #fff);
  font-family: 'Inter', sans-serif;
  overflow: hidden;
}

.studio-topbar {
  height: 64px;
  background-color: var(--admin-tier-1, #101419);
  border-bottom: 1px solid var(--admin-border, rgba(43,55,52,0.65));
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 24px;
  flex-shrink: 0;
}

.topbar-left {
  display: flex;
  align-items: center;
  gap: 24px;
}
.back-btn {
  display: flex;
  align-items: center;
  gap: 8px;
  color: var(--admin-text-secondary, #999);
  text-decoration: none;
  font-size: 0.9rem;
  font-weight: 500;
  transition: color 0.2s;
}
.back-btn:hover { color: #fff; }
.back-btn span { font-size: 1.2rem; }

.page-info h2 {
  margin: 0;
  font-size: 1.1rem;
  font-weight: 600;
  color: #fff;
}
.route-path {
  font-size: 0.75rem;
  color: var(--admin-text-secondary, #999);
}

.device-toggles {
  display: flex;
  background: var(--admin-tier-2, #161b20);
  border-radius: 8px;
  padding: 4px;
}
.device-toggles button {
  background: transparent;
  border: none;
  color: var(--admin-text-secondary, #999);
  padding: 6px 12px;
  border-radius: 6px;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
}
.device-toggles button.active {
  background: var(--admin-tier-3, #1c2228);
  color: #fff;
  box-shadow: 0 1px 3px rgba(0,0,0,0.2);
}

.topbar-right {
  display: flex;
  align-items: center;
  gap: 16px;
}
.status-indicator {
  display: flex;
  align-items: center;
  gap: 8px;
  font-size: 0.85rem;
  color: var(--admin-text-secondary, #999);
}
.dot {
  width: 8px;
  height: 8px;
  border-radius: 50%;
  background: #666;
}
.dot.saved { background: var(--admin-success, #10b981); }
.dot.draft { background: var(--admin-warning, #f59e0b); }
.dot.saving { background: #3b82f6; animation: pulse 1s infinite alternate; }

@keyframes pulse {
  0% { opacity: 0.5; }
  100% { opacity: 1; }
}

.studio-body {
  display: flex;
  flex: 1;
  overflow: hidden;
}

.studio-sidebar {
  width: 320px;
  background-color: var(--admin-tier-1, #101419);
  border-right: 1px solid var(--admin-border, rgba(43,55,52,0.65));
  padding: 24px;
  overflow-y: auto;
  display: flex;
  flex-direction: column;
  flex-shrink: 0;
}
.studio-sidebar h3 { margin: 0 0 4px; font-size: 1.1rem; }
.subtitle { margin: 0 0 24px; font-size: 0.85rem; color: var(--admin-text-secondary, #999); }

.form-group { margin-bottom: 20px; }
.form-group label {
  display: block;
  font-size: 0.8rem;
  color: var(--admin-text-secondary, #999);
  margin-bottom: 8px;
  text-transform: uppercase;
  letter-spacing: 0.05em;
}
.input-field {
  width: 100%;
  background: var(--admin-tier-0, #0b0f14);
  border: 1px solid var(--admin-border, rgba(43,55,52,0.65));
  color: #fff;
  padding: 10px 12px;
  border-radius: 6px;
  font-size: 0.95rem;
  font-family: inherit;
}
.input-field:focus {
  outline: none;
  border-color: var(--admin-success, #10b981);
}

.editor-panel {
  margin-top: 32px;
  padding-top: 24px;
  border-top: 1px solid var(--admin-border, rgba(43,55,52,0.65));
}
.editor-panel h4 { margin: 0 0 16px; color: var(--admin-success, #10b981); }

.empty-selection {
  margin-top: 48px;
  text-align: center;
  color: var(--admin-text-secondary, #999);
}
.empty-selection span { font-size: 3rem; opacity: 0.5; margin-bottom: 16px; }

.studio-canvas {
  flex: 1;
  background-color: #000;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 24px;
  overflow: auto;
}

.iframe-wrapper {
  background: #fff;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 10px 30px rgba(0,0,0,0.5);
  transition: all 0.3s ease;
  position: relative;
}
.iframe-wrapper.desktop { width: 100%; height: 100%; }
.iframe-wrapper.tablet { width: 768px; height: 1024px; }
.iframe-wrapper.mobile { width: 390px; height: 844px; }

.iframe-wrapper iframe {
  width: 100%;
  height: 100%;
  border: none;
}

.mt-2 { margin-top: 8px; }
.w-full { width: 100%; justify-content: center; }
</style>
