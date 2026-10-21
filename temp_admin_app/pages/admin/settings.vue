<template>
  <div class="animate-in fade-in zoom-in-95 duration-200 min-h-[calc(100vh-64px)] pb-12">
    <!-- CONTENT CANVAS -->
    <main class="flex-1 max-w-[1580px] w-full mx-auto flex flex-col gap-6 mt-2">
      <!-- SECTION 1 — PAGE HEADER -->
      <section class="flex flex-col md:flex-row md:items-center justify-between gap-4 pb-2 border-b border-outline-variant/40">
        <div>
          <h1 class="text-[36px] font-bold text-white tracking-tight" style="font-family: 'Plus Jakarta Sans', sans-serif;">Settings</h1>
          <p class="text-[16px] text-on-surface-variant mt-1">Manage site-wide information shown across your public pages.</p>
        </div>
        <div class="flex items-center gap-3">
          <div class="h-8 px-3 rounded-full bg-surface-container border border-outline-variant/65 flex items-center gap-2">
            <span class="w-2 h-2 rounded-full bg-primary animate-pulse"></span>
            <span class="text-[11px] font-semibold text-on-surface-variant tracking-wide">System v2.4 • Production Cluster live</span>
          </div>
          <div class="h-8 px-3 rounded-full bg-surface-container-low border border-outline-variant/65 flex items-center gap-1.5 text-on-surface-variant text-[11px] font-semibold">
            <span class="material-symbols-outlined text-[15px] text-primary">sync</span>
            <span>Synced 2m ago</span>
          </div>
        </div>
      </section>

      <!-- SECTION 2 — TWO-COLUMN LAYOUT -->
      <div class="grid grid-cols-1 lg:grid-cols-12 gap-6 items-start mt-2">
        <!-- LEFT COLUMN: Vertical In-Page Tab Navigation -->
        <div class="lg:col-span-3 bg-surface-container-low rounded-2xl p-3 border border-outline-variant/65 flex flex-col gap-1.5 sticky top-24">
          <div class="px-3 py-2 text-[11px] font-semibold text-outline uppercase tracking-wider">
            Configurations
          </div>
          
          <a @click.prevent="scrollTo('general')" :class="activeSection === 'general' ? 'bg-surface-container border-l-4 border-primary text-on-surface' : 'text-on-surface-variant hover:text-on-surface hover:bg-surface-container'" class="w-full flex items-center justify-between p-3 rounded-xl transition-all cursor-pointer">
            <div class="flex items-center gap-3">
              <span class="material-symbols-outlined text-[20px]" :class="activeSection === 'general' ? 'text-primary' : ''">tune</span>
              <span class="font-semibold text-[16px]" style="font-family: 'Plus Jakarta Sans', sans-serif;">General</span>
            </div>
            <span v-if="activeSection === 'general'" class="material-symbols-outlined text-[18px] text-primary">chevron_right</span>
          </a>
          
          <a @click.prevent="scrollTo('social')" :class="activeSection === 'social' ? 'bg-surface-container border-l-4 border-primary text-on-surface' : 'text-on-surface-variant hover:text-on-surface hover:bg-surface-container'" class="w-full flex items-center justify-between p-3 rounded-xl transition-all cursor-pointer">
            <div class="flex items-center gap-3">
              <span class="material-symbols-outlined text-[20px]" :class="activeSection === 'social' ? 'text-primary' : ''">share</span>
              <span class="font-semibold text-[16px]" style="font-family: 'Plus Jakarta Sans', sans-serif;">Social Links</span>
            </div>
            <span class="text-[11px] font-semibold text-outline">5 links</span>
          </a>
          
          <a @click.prevent="scrollTo('seo')" :class="activeSection === 'seo' ? 'bg-surface-container border-l-4 border-primary text-on-surface' : 'text-on-surface-variant hover:text-on-surface hover:bg-surface-container'" class="w-full flex items-center justify-between p-3 rounded-xl transition-all cursor-pointer">
            <div class="flex items-center gap-3">
              <span class="material-symbols-outlined text-[20px]" :class="activeSection === 'seo' ? 'text-primary' : ''">language</span>
              <span class="font-semibold text-[16px]" style="font-family: 'Plus Jakarta Sans', sans-serif;">SEO Defaults</span>
            </div>
            <span class="text-[11px] font-semibold px-2 py-0.5 rounded-full bg-surface-container-high text-on-surface-variant">Meta</span>
          </a>
          
          <div class="my-2 border-t border-outline-variant/65"></div>
          
          <a @click.prevent="scrollTo('danger')" :class="activeSection === 'danger' ? 'bg-error-container/20 text-error' : 'text-error hover:bg-error-container/20'" class="w-full flex items-center justify-between p-3 rounded-xl transition-all cursor-pointer">
            <div class="flex items-center gap-3">
              <span class="material-symbols-outlined text-[20px] text-error">warning</span>
              <span class="font-semibold text-[16px]" style="font-family: 'Plus Jakarta Sans', sans-serif;">Danger Zone</span>
            </div>
            <span class="material-symbols-outlined text-[16px] text-error">lock</span>
          </a>
        </div>

        <!-- RIGHT COLUMN: Tabbed Forms & Configuration Cards -->
        <div class="lg:col-span-9 flex flex-col gap-6">
          
          <!-- 1. GENERAL CONFIGURATION -->
          <div id="general" class="scroll-mt-24 bg-surface-container-low rounded-2xl p-6 lg:p-8 border border-outline-variant/65 relative overflow-hidden">
            <div class="absolute top-0 left-0 right-0 h-[2px] bg-gradient-to-r from-transparent via-primary/50 to-transparent"></div>
            <div class="pb-4 mb-6 border-b border-outline-variant/40 flex items-center justify-between">
              <div>
                <h2 class="text-[22px] font-bold text-white tracking-tight" style="font-family: 'Plus Jakarta Sans', sans-serif;">General Site Configuration</h2>
                <p class="text-[14px] text-on-surface-variant mt-0.5">Core brand identity and global contact endpoints.</p>
              </div>
              <div class="flex items-center gap-2">
                <span class="px-2.5 py-1 rounded-full bg-primary/10 text-primary border border-primary/20 text-[11px] font-semibold">Primary Profile</span>
              </div>
            </div>
            
            <form @submit.prevent="saveSettings" class="space-y-6">
              <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                <div>
                  <label class="block text-[13px] font-medium text-white mb-2" style="font-family: 'Plus Jakarta Sans', sans-serif;">Site Name</label>
                  <div class="relative">
                    <input v-model="general.siteName" class="w-full h-11 bg-surface-container-lowest border border-outline-variant/65 rounded-xl px-4 text-white text-[14px] focus:outline-none focus:border-primary focus:ring-1 focus:ring-primary/20 transition-all" type="text" />
                    <span class="material-symbols-outlined absolute right-3 top-1/2 -translate-y-1/2 text-primary text-[18px]">check_circle</span>
                  </div>
                  <p class="text-[11px] font-semibold text-on-surface-variant mt-1.5">Official legal registered company entity name.</p>
                </div>
                <div>
                  <label class="block text-[13px] font-medium text-white mb-2" style="font-family: 'Plus Jakarta Sans', sans-serif;">Tagline</label>
                  <input v-model="general.tagline" class="w-full h-11 bg-surface-container-lowest border border-outline-variant/65 rounded-xl px-4 text-white text-[14px] focus:outline-none focus:border-primary focus:ring-1 focus:ring-primary/20 transition-all" type="text" />
                  <p class="text-[11px] font-semibold text-on-surface-variant mt-1.5">Displayed on primary hero banners and metadata headlines.</p>
                </div>
              </div>
              
              <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                <div>
                  <label class="block text-[13px] font-medium text-white mb-2" style="font-family: 'Plus Jakarta Sans', sans-serif;">Contact Email</label>
                  <div class="relative">
                    <span class="material-symbols-outlined absolute left-3.5 top-1/2 -translate-y-1/2 text-outline text-[18px]">mail</span>
                    <input v-model="general.email" class="w-full h-11 bg-surface-container-lowest border border-outline-variant/65 rounded-xl pl-11 pr-4 text-white text-[14px] focus:outline-none focus:border-primary focus:ring-1 focus:ring-primary/20 transition-all" type="email" />
                  </div>
                  <p class="text-[11px] font-semibold text-on-surface-variant mt-1.5">Receives general customer inquiries and automated portal receipts.</p>
                </div>
                <div>
                  <label class="block text-[13px] font-medium text-white mb-2" style="font-family: 'Plus Jakarta Sans', sans-serif;">Sales Email</label>
                  <div class="relative">
                    <span class="material-symbols-outlined absolute left-3.5 top-1/2 -translate-y-1/2 text-outline text-[18px]">mark_email_unread</span>
                    <input class="w-full h-11 bg-surface-container-lowest border border-outline-variant/65 rounded-xl pl-11 pr-4 text-white text-[14px] focus:outline-none focus:border-primary focus:ring-1 focus:ring-primary/20 transition-all" type="email" value="sales@kavorax.com" />
                  </div>
                  <p class="text-[11px] font-semibold text-on-surface-variant mt-1.5">Dispatches enterprise lead capture requests and RFPs.</p>
                </div>
              </div>
              
              <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                <div>
                  <label class="block text-[13px] font-medium text-white mb-2" style="font-family: 'Plus Jakarta Sans', sans-serif;">Phone Number</label>
                  <div class="relative flex items-center">
                    <div class="absolute left-3.5 flex items-center gap-1.5 text-on-surface-variant">
                      <span class="text-sm">🇮🇳</span>
                      <span class="text-[12px] text-outline font-semibold">+91</span>
                      <span class="h-4 w-px bg-outline-variant/65 ml-1"></span>
                    </div>
                    <input v-model="general.phone" class="w-full h-11 bg-surface-container-lowest border border-outline-variant/65 rounded-xl pl-20 pr-4 text-white text-[14px] focus:outline-none focus:border-primary focus:ring-1 focus:ring-primary/20 transition-all" type="text" />
                  </div>
                  <p class="text-[11px] font-semibold text-on-surface-variant mt-1.5">Direct hotline for corporate concierge and urgent operations.</p>
                </div>
                <div class="flex flex-col justify-end">
                  <div class="p-3 bg-surface-container rounded-xl border border-outline-variant/65 flex items-center gap-3">
                    <span class="material-symbols-outlined text-primary text-[22px]">verified_user</span>
                    <div class="flex flex-col">
                      <span class="text-[12px] text-white font-semibold">Domain Ownership Verified</span>
                      <span class="text-[11px] font-semibold text-on-surface-variant">DNS records mapped to cluster endpoint routing</span>
                    </div>
                  </div>
                </div>
              </div>
              
              <div class="pt-6 border-t border-outline-variant/40 flex items-center justify-between">
                <div class="flex items-center gap-2 text-on-surface-variant text-[11px] font-semibold">
                  <span class="material-symbols-outlined text-[16px] text-primary">cloud_done</span>
                  <span>All endpoints operational</span>
                </div>
                <div class="flex items-center gap-3">
                  <button type="button" class="px-5 py-2.5 rounded-full border border-outline-variant/65 text-on-surface-variant hover:text-white hover:bg-surface-container transition-all text-[13px] font-medium">
                    Reset
                  </button>
                  <button type="submit" class="px-7 py-2.5 rounded-full bg-gradient-to-r from-primary-container to-primary text-[#00422b] text-[13px] font-bold shadow-[0_0_15px_rgba(16,185,129,0.25)] hover:shadow-[0_0_20px_rgba(16,185,129,0.4)] active:scale-[0.98] transition-all">
                    Save Changes
                  </button>
                </div>
              </div>
            </form>
          </div>

          <!-- 2. SOCIAL LINKS -->
          <div id="social" class="scroll-mt-24 bg-surface-container-low rounded-2xl p-6 lg:p-8 border border-outline-variant/65">
            <div class="pb-4 mb-6 border-b border-outline-variant/40 flex items-center justify-between">
              <div>
                <h2 class="text-[22px] font-bold text-white tracking-tight" style="font-family: 'Plus Jakarta Sans', sans-serif;">Social Links</h2>
                <p class="text-[14px] text-on-surface-variant mt-0.5">Public social endpoints displayed in footer and communication channels.</p>
              </div>
              <span class="px-2.5 py-1 rounded-full bg-surface-container text-on-surface-variant border border-outline-variant/65 text-[11px] font-semibold">5 Channels Active</span>
            </div>
            
            <form @submit.prevent="saveSettings" class="space-y-4">
              <div>
                <label class="block text-[13px] font-medium text-white mb-1.5" style="font-family: 'Plus Jakarta Sans', sans-serif;">LinkedIn URL</label>
                <div class="relative">
                  <div class="absolute left-3.5 top-1/2 -translate-y-1/2 flex items-center gap-2 pointer-events-none">
                    <span class="material-symbols-outlined text-primary text-[18px]">link</span>
                    <span class="text-[12px] font-semibold text-outline">linkedin.com/</span>
                  </div>
                  <input v-model="social.linkedin" class="w-full h-11 bg-surface-container-lowest border border-outline-variant/65 rounded-xl pl-32 pr-4 text-white text-[14px] focus:outline-none focus:border-primary focus:ring-1 focus:ring-primary/20 transition-all" type="text" />
                </div>
              </div>
              
              <div>
                <label class="block text-[13px] font-medium text-white mb-1.5" style="font-family: 'Plus Jakarta Sans', sans-serif;">Instagram URL</label>
                <div class="relative">
                  <div class="absolute left-3.5 top-1/2 -translate-y-1/2 flex items-center gap-2 pointer-events-none">
                    <span class="material-symbols-outlined text-primary text-[18px]">photo_camera</span>
                    <span class="text-[12px] font-semibold text-outline">instagram.com/</span>
                  </div>
                  <input v-model="social.instagram" class="w-full h-11 bg-surface-container-lowest border border-outline-variant/65 rounded-xl pl-32 pr-4 text-white text-[14px] focus:outline-none focus:border-primary focus:ring-1 focus:ring-primary/20 transition-all" type="text" />
                </div>
              </div>
              
              <div>
                <label class="block text-[13px] font-medium text-white mb-1.5" style="font-family: 'Plus Jakarta Sans', sans-serif;">Facebook URL</label>
                <div class="relative">
                  <div class="absolute left-3.5 top-1/2 -translate-y-1/2 flex items-center gap-2 pointer-events-none">
                    <span class="material-symbols-outlined text-primary text-[18px]">public</span>
                    <span class="text-[12px] font-semibold text-outline">facebook.com/</span>
                  </div>
                  <input v-model="social.facebook" class="w-full h-11 bg-surface-container-lowest border border-outline-variant/65 rounded-xl pl-32 pr-4 text-white text-[14px] focus:outline-none focus:border-primary focus:ring-1 focus:ring-primary/20 transition-all" type="text" />
                </div>
              </div>
              
              <div>
                <label class="block text-[13px] font-medium text-white mb-1.5" style="font-family: 'Plus Jakarta Sans', sans-serif;">YouTube URL</label>
                <div class="relative">
                  <div class="absolute left-3.5 top-1/2 -translate-y-1/2 flex items-center gap-2 pointer-events-none">
                    <span class="material-symbols-outlined text-primary text-[18px]">smart_display</span>
                    <span class="text-[12px] font-semibold text-outline">youtube.com/</span>
                  </div>
                  <input class="w-full h-11 bg-surface-container-lowest border border-outline-variant/65 rounded-xl pl-32 pr-4 text-white text-[14px] focus:outline-none focus:border-primary focus:ring-1 focus:ring-primary/20 transition-all" type="text" value="https://youtube.com/@kavorax" />
                </div>
              </div>
              
              <div>
                <label class="block text-[13px] font-medium text-white mb-1.5" style="font-family: 'Plus Jakarta Sans', sans-serif;">WhatsApp Number / Link</label>
                <div class="relative">
                  <div class="absolute left-3.5 top-1/2 -translate-y-1/2 flex items-center gap-2 pointer-events-none">
                    <span class="material-symbols-outlined text-primary text-[18px]">chat</span>
                    <span class="text-[12px] font-semibold text-outline">wa.me/</span>
                  </div>
                  <input v-model="social.whatsapp" class="w-full h-11 bg-surface-container-lowest border border-outline-variant/65 rounded-xl pl-24 pr-4 text-white text-[14px] focus:outline-none focus:border-primary focus:ring-1 focus:ring-primary/20 transition-all" type="text" />
                </div>
              </div>
              
              <div class="pt-6 mt-6 border-t border-outline-variant/40 flex justify-end">
                <button type="submit" class="px-7 py-2.5 rounded-full bg-gradient-to-r from-primary-container to-primary text-[#00422b] text-[13px] font-bold shadow-[0_0_15px_rgba(16,185,129,0.20)] hover:shadow-[0_0_20px_rgba(16,185,129,0.30)] transition-all">
                  Save Changes
                </button>
              </div>
            </form>
          </div>

          <!-- 3. SEO DEFAULTS -->
          <div id="seo" class="scroll-mt-24 bg-surface-container-low rounded-2xl p-6 lg:p-8 border border-outline-variant/65">
            <div class="pb-4 mb-6 border-b border-outline-variant/40 flex items-center justify-between">
              <div>
                <h2 class="text-[22px] font-bold text-white tracking-tight" style="font-family: 'Plus Jakarta Sans', sans-serif;">SEO Defaults</h2>
                <p class="text-[14px] text-on-surface-variant mt-0.5">Control search engine indexing, OpenGraph card summaries, and social share representations.</p>
              </div>
              <div class="flex items-center gap-2">
                <span class="material-symbols-outlined text-primary text-[18px]">speed</span>
                <span class="text-[12px] text-primary font-semibold">100/100 Health</span>
              </div>
            </div>
            
            <form @submit.prevent="saveSettings" class="space-y-6">
              <div>
                <div class="flex justify-between items-center mb-2">
                  <label class="text-[13px] font-medium text-white" style="font-family: 'Plus Jakarta Sans', sans-serif;">Default Meta Title</label>
                  <span class="text-[12px] text-outline">61/70 characters recommended</span>
                </div>
                <input v-model="seo.title" class="w-full h-11 bg-surface-container-lowest border border-outline-variant/65 rounded-xl px-4 text-white text-[14px] focus:outline-none focus:border-primary focus:ring-1 focus:ring-primary/20 transition-all" type="text" />
              </div>
              
              <div>
                <div class="flex justify-between items-center mb-2">
                  <label class="text-[13px] font-medium text-white" style="font-family: 'Plus Jakarta Sans', sans-serif;">Default Meta Description</label>
                  <span class="text-[12px] text-primary font-semibold">142/160 characters</span>
                </div>
                <textarea v-model="seo.description" class="w-full bg-surface-container-lowest border border-outline-variant/65 rounded-xl p-4 text-white text-[14px] focus:outline-none focus:border-primary focus:ring-1 focus:ring-primary/20 transition-all resize-none" rows="3"></textarea>
                <div class="w-full bg-surface-container h-1.5 rounded-full mt-2 overflow-hidden">
                  <div class="bg-primary h-full rounded-full" style="width: 88.75%"></div>
                </div>
              </div>
              
              <div>
                <label class="block text-[13px] font-medium text-white mb-2" style="font-family: 'Plus Jakarta Sans', sans-serif;">Default Social Share Image (OG Image)</label>
                <div class="p-4 bg-surface-container rounded-xl border border-outline-variant/65 flex flex-col sm:flex-row items-center gap-5">
                  <div class="relative w-full sm:w-56 h-32 rounded-lg bg-surface-container-lowest border border-outline-variant/65 overflow-hidden flex flex-col justify-between p-3 shrink-0">
                    <div class="flex items-center justify-between">
                      <div class="w-6 h-6 rounded bg-primary/20 flex items-center justify-center text-primary font-bold text-[10px]">K</div>
                      <span class="text-[10px] font-code-sm text-outline">1200 × 630</span>
                    </div>
                    <div>
                      <div class="text-[11px] font-bold text-white truncate">KavoraX Global Systems</div>
                      <div class="text-[9px] text-on-surface-variant truncate">Next-Gen Enterprise Engine</div>
                    </div>
                  </div>
                  <div class="flex-1 flex flex-col justify-between h-full gap-3">
                    <div>
                      <div class="text-[13px] font-semibold text-white" style="font-family: 'Plus Jakarta Sans', sans-serif;">og-banner-enterprise-dark-v2.png</div>
                      <p class="text-[11px] font-semibold text-on-surface-variant mt-1">Rendered across Twitter/X cards, LinkedIn feed posts, and WhatsApp preview snippets.</p>
                      <div class="flex items-center gap-3 mt-2 text-[11px] font-semibold text-outline">
                        <span>Format: PNG</span>
                        <span>•</span>
                        <span>Size: 420 KB</span>
                        <span>•</span>
                        <span class="text-primary font-medium">Optimized for retina</span>
                      </div>
                    </div>
                    <div>
                      <button type="button" class="inline-flex items-center gap-2 px-4 py-2 rounded-full border border-outline-variant/65 bg-surface-container-high text-on-surface hover:text-primary hover:border-primary transition-all text-[13px] font-medium">
                        <span class="material-symbols-outlined text-[18px]">perm_media</span>
                        <span>Replace Image</span>
                      </button>
                    </div>
                  </div>
                </div>
              </div>
              
              <div class="pt-6 mt-6 border-t border-outline-variant/40 flex justify-end">
                <button type="submit" class="px-7 py-2.5 rounded-full bg-gradient-to-r from-primary-container to-primary text-[#00422b] text-[13px] font-bold shadow-[0_0_15px_rgba(16,185,129,0.20)] hover:shadow-[0_0_20px_rgba(16,185,129,0.30)] transition-all">
                  Save Changes
                </button>
              </div>
            </form>
          </div>

          <!-- 4. DANGER ZONE -->
          <div id="danger" class="scroll-mt-24 bg-surface-container-low rounded-2xl p-6 lg:p-8 border border-error/50 bg-gradient-to-b from-error/5 via-transparent to-transparent mb-12">
            <div class="pb-4 mb-6 border-b border-outline-variant/40 flex items-center justify-between">
              <div class="flex items-center gap-3">
                <div class="w-9 h-9 rounded-xl bg-error-container/40 border border-error/40 flex items-center justify-center text-error">
                  <span class="material-symbols-outlined text-[20px]">warning</span>
                </div>
                <div>
                  <h2 class="text-[22px] font-bold text-error tracking-tight" style="font-family: 'Plus Jakarta Sans', sans-serif;">Danger Zone</h2>
                  <p class="text-[14px] text-on-surface-variant mt-0.5">Destructive and irreversible operational actions.</p>
                </div>
              </div>
              <span class="px-3 py-1 rounded-full bg-error-container/30 border border-error/40 text-error text-[11px] font-semibold uppercase tracking-wider">
                Restricted Access
              </span>
            </div>
            
            <div class="space-y-6">
              <!-- Export Data -->
              <div class="flex flex-col sm:flex-row sm:items-center justify-between gap-4 p-4 rounded-xl bg-surface-container border border-outline-variant/40">
                <div>
                  <div class="text-[16px] font-semibold text-white" style="font-family: 'Plus Jakarta Sans', sans-serif;">Export All Content Data</div>
                  <p class="text-[13px] text-on-surface-variant mt-0.5">Download a JSON backup of all products, industries, pricing, and content.</p>
                </div>
                <button @click="exportData" class="inline-flex items-center justify-center gap-2 px-5 py-2.5 rounded-full border border-outline-variant/80 text-on-surface hover:text-white hover:border-on-surface transition-all text-[13px] font-medium shrink-0">
                  <span class="material-symbols-outlined text-[18px]">download</span>
                  <span>Export JSON</span>
                </button>
              </div>
            </div>
          </div>
          
        </div>
      </div>
    </main>

    <!-- SAVE CONFIRMATION TOAST (Custom HTML logic implemented via Vue ref) -->
    <transition name="slide-up">
      <aside v-if="showCustomToast" class="fixed bottom-6 right-6 z-[100] bg-surface-container-low border-l-4 border-primary rounded-xl p-4 shadow-2xl border-y border-r border-outline-variant/65 flex items-center gap-3 max-w-sm">
        <div class="w-8 h-8 rounded-full bg-primary/20 flex items-center justify-center text-primary shrink-0">
          <span class="material-symbols-outlined text-[18px]">check_circle</span>
        </div>
        <div class="flex flex-col flex-1">
          <span class="text-[13px] text-white font-semibold" style="font-family: 'Plus Jakarta Sans', sans-serif;">Settings saved successfully</span>
          <span class="text-[11px] text-outline">Just now</span>
        </div>
        <button @click="showCustomToast = false" class="text-outline hover:text-white transition-colors p-1">
          <span class="material-symbols-outlined text-[16px]">close</span>
        </button>
      </aside>
    </transition>

    <!-- CONFIRMATION MODAL -->
    <transition name="fade">
      <div v-if="isConfirmOpen" class="fixed inset-0 z-[60] flex items-center justify-center p-4 bg-surface-container-lowest/80 backdrop-blur-sm transition-all duration-300" @click.self="isConfirmOpen = false">
        <div class="relative w-full max-w-md bg-surface-container-low rounded-2xl border-2 border-error p-6 shadow-2xl shadow-black/80 flex flex-col gap-4 animate-in zoom-in-95 duration-200">
          <div class="flex items-center justify-between">
            <div class="w-12 h-12 rounded-2xl bg-error-container/30 border border-error/50 flex items-center justify-center text-error">
              <span class="material-symbols-outlined text-[28px]">warning</span>
            </div>
            <button @click="isConfirmOpen = false" class="w-8 h-8 rounded-full bg-surface-container flex items-center justify-center text-on-surface-variant hover:text-white transition-colors">
              <span class="material-symbols-outlined text-[18px]">close</span>
            </button>
          </div>
          <div>
            <h3 class="text-[22px] font-bold text-white" style="font-family: 'Plus Jakarta Sans', sans-serif;">Are you sure?</h3>
            <p class="text-[14px] text-on-surface-variant mt-2">
              Are you sure you want to proceed?
            </p>
          </div>
          <div class="flex items-center justify-end gap-3 mt-4 pt-4 border-t border-outline-variant/40">
            <button @click="isConfirmOpen = false" class="px-5 py-2.5 rounded-full border border-outline-variant/65 text-on-surface-variant hover:text-white hover:bg-surface-container transition-all text-[13px] font-medium">
              Cancel
            </button>
          </div>
        </div>
      </div>
    </transition>

  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'

definePageMeta({ layout: 'admin', middleware: ['admin-auth'] })

const isConfirmOpen = ref(false)
const showCustomToast = ref(false)
const activeSection = ref('general')

// Handle Scroll Spy behavior manually for the left nav
const scrollTo = (id: string) => {
  activeSection.value = id
  const el = document.getElementById(id)
  if (el) {
    el.scrollIntoView({ behavior: 'smooth' })
  }
}

import { useSiteSettings } from '~/composables/useSiteSettings'
const { settings } = useSiteSettings()

const general = ref({ siteName: settings.value.siteName, tagline: settings.value.tagline, email: settings.value.email, phone: settings.value.phone })
const social = ref(settings.value.social)
const seo = ref(settings.value.seo)

function saveSettings() {
  settings.value = { ...settings.value, ...general.value, social: social.value, seo: seo.value }
  
  // Show custom toast designed in the HTML
  showCustomToast.value = true
  setTimeout(() => {
    showCustomToast.value = false
  }, 4000)
}

function exportData() {
  const data = JSON.stringify({ settings: settings.value })
  const blob = new Blob([data], { type: 'application/json' })
  const url = URL.createObjectURL(blob)
  const a = document.createElement('a')
  a.href = url
  a.download = 'kavorax_backup.json'
  a.click()
  URL.revokeObjectURL(url)
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

/* Base animations */
.fade-in {
  animation: fadeIn 0.2s ease-out forwards;
}
.zoom-in-95 {
  animation: zoomIn 0.2s cubic-bezier(0.16, 1, 0.3, 1) forwards;
}

@keyframes fadeIn {
  from { opacity: 0; }
  to { opacity: 1; }
}

@keyframes zoomIn {
  from { transform: scale(0.97); }
  to { transform: scale(1); }
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.2s ease;
}
.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}

.slide-up-enter-active,
.slide-up-leave-active {
  transition: all 0.3s cubic-bezier(0.16, 1, 0.3, 1);
}
.slide-up-enter-from,
.slide-up-leave-to {
  transform: translateY(20px);
  opacity: 0;
}
</style>
