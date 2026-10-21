<template>
  <div class="animate-in fade-in zoom-in-95 duration-200 pb-28 min-h-[calc(100vh-64px)]">
    
    <!-- MAIN INTERACTIVE CONTENT AREA -->
    <main class="flex flex-col gap-6 max-w-[1600px] w-full mx-auto mt-2">
      <!-- SECTION 1: PAGE HEADER -->
      <section class="flex flex-col md:flex-row md:items-center justify-between gap-4">
        <div class="flex flex-col">
          <nav class="flex items-center gap-2 text-[11px] font-semibold text-outline mb-1.5">
            <span>Content</span>
            <span class="text-outline-variant">/</span>
            <span class="text-on-surface">Pricing</span>
          </nav>
          <h1 class="text-[28px] font-bold text-on-surface tracking-tight" style="font-family: 'Plus Jakarta Sans', sans-serif;">Pricing Editor</h1>
          <p class="text-[14px] text-on-surface-variant mt-1">Manage the plans and module matrices shown on your public Pricing page.</p>
        </div>
        <div class="flex items-center gap-3">
          <button class="flex items-center gap-2 px-4 py-2 rounded-full border border-primary/40 bg-surface-container text-primary text-[14px] font-semibold hover:bg-primary/10 transition-all duration-150 active:scale-98">
            <span>Preview Public Page</span>
            <span class="material-symbols-outlined text-[18px]">open_in_new</span>
          </button>
        </div>
      </section>

      <!-- SECTION 2: MARKET & BILLING TOGGLES -->
      <section class="bg-surface-container-low border border-outline-variant/65 rounded-xl p-3 flex flex-wrap items-center justify-between gap-4 shadow-sm">
        <!-- Market Selector -->
        <div class="flex items-center gap-3">
          <span class="text-[11px] font-semibold text-outline uppercase tracking-wider">Market:</span>
          <div class="flex items-center gap-2">
            <button class="px-3.5 py-1.5 rounded-full bg-primary-container/20 border border-primary text-primary text-[13px] font-semibold shadow-[0_0_12px_rgba(16,185,129,0.2)]">
              India (₹)
            </button>
            <div class="flex items-center gap-1.5 px-3.5 py-1.5 rounded-full bg-surface-container border border-outline-variant/40 text-outline opacity-60 cursor-not-allowed">
              <span class="text-[13px] font-medium">Oman - GCC</span>
              <span class="text-[9px] uppercase tracking-wider font-bold px-1.5 py-0.5 rounded-full bg-surface-container-highest text-on-surface-variant">Coming soon</span>
            </div>
          </div>
        </div>
        <!-- Billing Cycle Segmented Control -->
        <div class="flex items-center gap-3">
          <span class="text-[11px] font-semibold text-outline uppercase tracking-wider">Billing Cycle:</span>
          <div class="flex items-center bg-surface-container p-1 rounded-full border border-outline-variant/65">
            <button class="px-4 py-1 rounded-full bg-primary-container text-on-primary-container text-[13px] font-semibold shadow-sm transition-all">
              Monthly
            </button>
            <button class="px-4 py-1 rounded-full text-on-surface-variant hover:text-on-surface text-[13px] font-medium transition-all flex items-center gap-1.5">
              <span>Annual</span>
              <span class="px-1.5 py-0.5 rounded-full bg-primary/20 text-primary text-[10px] font-bold tracking-tight">Save 20%</span>
            </button>
          </div>
        </div>
      </section>

      <!-- SECTION 3: THREE PLAN CARDS (Bento / Side-by-Side 3-Col Grid) -->
      <section class="grid grid-cols-1 lg:grid-cols-3 gap-6 items-stretch">
        
        <!-- PLAN CARD 1: STARTER -->
        <div class="bg-surface-container-low border border-outline-variant/65 rounded-2xl p-6 flex flex-col justify-between gap-6 transition-all duration-200 hover:border-outline shadow-lg">
          <div class="flex flex-col gap-5">
            <!-- Header & Badge -->
            <div class="flex items-center justify-between h-7">
              <span class="text-[11px] font-semibold text-outline tracking-wider uppercase">Tier 01</span>
              <span class="text-[11px] text-outline font-mono">SKU: KVX-STR-01</span>
            </div>
            <!-- Editable Plan Name -->
            <div class="flex flex-col gap-1.5">
              <label class="text-[11px] font-semibold text-outline">Plan Display Name</label>
              <input v-model="starterPlan.name" @input="markUnsaved" class="bg-surface-container-lowest border border-outline-variant/65 rounded-lg px-3.5 py-2 text-[18px] font-bold text-on-surface focus:outline-none focus:border-primary-container focus:ring-1 focus:ring-primary-container transition-all" style="font-family: 'Plus Jakarta Sans', sans-serif;" type="text"/>
            </div>
            <!-- Short Tagline -->
            <div class="flex flex-col gap-1.5">
              <label class="text-[11px] font-semibold text-outline">Short Tagline</label>
              <textarea v-model="starterPlan.tagline" @input="markUnsaved" class="bg-surface-container-lowest border border-outline-variant/65 rounded-lg px-3 py-2 text-on-surface-variant text-[13px] resize-none focus:outline-none focus:border-primary-container transition-all" rows="2"></textarea>
            </div>
            <!-- Price Specification Field -->
            <div class="flex flex-col gap-1.5">
              <label class="text-[11px] font-semibold text-outline">Base Recurring Price</label>
              <div class="flex items-center bg-surface-container-lowest border border-outline-variant/65 rounded-lg px-3 py-1.5 focus-within:border-primary-container focus-within:ring-1 focus-within:ring-primary-container transition-all">
                <span class="text-primary text-[22px] font-bold mr-1">₹</span>
                <input v-model="starterPlan.price" @input="markUnsaved" class="bg-transparent border-0 p-0 text-on-surface text-[22px] font-bold focus:ring-0 w-full" type="text"/>
                <span class="text-outline text-[13px] ml-2">/month</span>
              </div>
            </div>
            <!-- Switch Parameters -->
            <div class="flex flex-col gap-3 py-3 border-y border-outline-variant/40">
              <div class="flex items-center justify-between">
                <span class="text-[13px] text-on-surface">Custom Pricing Mode</span>
                <button @click="starterPlan.isCustom = !starterPlan.isCustom; markUnsaved()" :class="starterPlan.isCustom ? 'bg-primary-container justify-end' : 'bg-surface-container-highest justify-start'" class="w-9 h-5 rounded-full p-0.5 transition-colors relative flex items-center" type="button">
                  <span :class="starterPlan.isCustom ? 'bg-on-primary-container' : 'bg-outline'" class="w-4 h-4 rounded-full shadow-sm"></span>
                </button>
              </div>
              <div class="flex items-center justify-between">
                <span class="text-[13px] text-on-surface" :class="{ 'font-semibold': starterPlan.isPopular }">Flag as "Most Popular"</span>
                <button @click="starterPlan.isPopular = !starterPlan.isPopular; markUnsaved()" :class="starterPlan.isPopular ? 'bg-primary-container justify-end' : 'bg-surface-container-highest justify-start'" class="w-9 h-5 rounded-full p-0.5 transition-colors relative flex items-center" type="button">
                  <span :class="starterPlan.isPopular ? 'bg-on-primary-container' : 'bg-outline'" class="w-4 h-4 rounded-full shadow-sm"></span>
                </button>
              </div>
            </div>
            <!-- Included Modules Checklist -->
            <div class="flex flex-col gap-2.5">
              <span class="text-[11px] font-semibold text-outline uppercase tracking-wider">Included Modules ({{ modules.filter(m => m.starter).length }})</span>
              <div class="flex flex-col gap-2 bg-surface-container-lowest/60 p-3 rounded-xl border border-outline-variant/40">
                <label v-for="(mod, idx) in modules" :key="idx" class="flex items-center justify-between cursor-pointer group">
                  <span class="text-[13px] transition-colors" :class="mod.starter ? 'text-on-surface group-hover:text-primary' : 'text-outline group-hover:text-on-surface'">{{ mod.name }}</span>
                  <input v-model="mod.starter" @change="markUnsaved" class="rounded border-outline-variant/65 bg-surface-container text-primary-container focus:ring-0 focus:ring-offset-0 transition-colors" type="checkbox"/>
                </label>
              </div>
            </div>
            <!-- Support Level -->
            <div class="flex flex-col gap-1.5">
              <label class="text-[11px] font-semibold text-outline">Service Level Agreement (SLA)</label>
              <input v-model="starterPlan.sla" @input="markUnsaved" class="bg-surface-container-lowest border border-outline-variant/65 rounded-lg px-3 py-2 text-on-surface-variant text-[13px] focus:outline-none focus:border-primary-container transition-all" type="text"/>
            </div>
          </div>
          <!-- Card Footer Save -->
          <button @click="saveEdits" class="w-full py-2.5 rounded-full border border-primary/50 text-primary text-[14px] font-semibold hover:bg-primary/10 transition-all duration-150 active:scale-98">
            Save Starter Plan
          </button>
        </div>

        <!-- PLAN CARD 2: GROWTH (Highlighted) -->
        <div class="relative bg-surface-container-low border-2 border-primary/60 shadow-[0_0_24px_rgba(16,185,129,0.18)] rounded-2xl p-6 flex flex-col justify-between gap-6">
          <div class="flex flex-col gap-5">
            <!-- Header & Badge -->
            <div class="flex items-center justify-between h-7">
              <span class="inline-flex items-center gap-1.5 px-3 py-0.5 rounded-full bg-primary-container text-on-primary-container text-[11px] font-bold shadow-[0_0_10px_#10b981]">
                <span class="material-symbols-outlined text-[14px]" style="font-variation-settings: 'FILL' 1;">star</span>
                MOST POPULAR
              </span>
              <span class="text-[11px] text-primary font-mono">SKU: KVX-GRW-02</span>
            </div>
            <!-- Editable Plan Name -->
            <div class="flex flex-col gap-1.5">
              <label class="text-[11px] font-semibold text-primary">Plan Display Name</label>
              <input v-model="growthPlan.name" @input="markUnsaved" class="bg-surface-container-lowest border border-primary/40 rounded-lg px-3.5 py-2 text-[18px] font-bold text-on-surface focus:outline-none focus:border-primary focus:ring-1 focus:ring-primary transition-all" style="font-family: 'Plus Jakarta Sans', sans-serif;" type="text"/>
            </div>
            <!-- Short Tagline -->
            <div class="flex flex-col gap-1.5">
              <label class="text-[11px] font-semibold text-outline">Short Tagline</label>
              <textarea v-model="growthPlan.tagline" @input="markUnsaved" class="bg-surface-container-lowest border border-outline-variant/65 rounded-lg px-3 py-2 text-on-surface-variant text-[13px] resize-none focus:outline-none focus:border-primary transition-all" rows="2"></textarea>
            </div>
            <!-- Price Specification Field -->
            <div class="flex flex-col gap-1.5">
              <label class="text-[11px] font-semibold text-outline">Base Recurring Price</label>
              <div class="flex items-center bg-surface-container-lowest border border-primary/40 rounded-lg px-3 py-1.5 focus-within:border-primary focus-within:ring-1 focus-within:ring-primary transition-all">
                <span class="text-primary text-[22px] font-bold mr-1">₹</span>
                <input v-model="growthPlan.price" @input="markUnsaved" class="bg-transparent border-0 p-0 text-on-surface text-[22px] font-bold focus:ring-0 w-full" type="text"/>
                <span class="text-outline text-[13px] ml-2">/month</span>
              </div>
            </div>
            <!-- Switch Parameters -->
            <div class="flex flex-col gap-3 py-3 border-y border-outline-variant/40">
              <div class="flex items-center justify-between">
                <span class="text-[13px] text-on-surface">Custom Pricing Mode</span>
                <button @click="growthPlan.isCustom = !growthPlan.isCustom; markUnsaved()" :class="growthPlan.isCustom ? 'bg-primary-container justify-end' : 'bg-surface-container-highest justify-start'" class="w-9 h-5 rounded-full p-0.5 transition-colors relative flex items-center" type="button">
                  <span :class="growthPlan.isCustom ? 'bg-on-primary-container' : 'bg-outline'" class="w-4 h-4 rounded-full shadow-sm"></span>
                </button>
              </div>
              <div class="flex items-center justify-between">
                <span class="text-[13px] text-on-surface font-semibold">Flag as "Most Popular"</span>
                <button @click="growthPlan.isPopular = !growthPlan.isPopular; markUnsaved()" :class="growthPlan.isPopular ? 'bg-primary-container justify-end' : 'bg-surface-container-highest justify-start'" class="w-9 h-5 rounded-full p-0.5 transition-colors relative flex items-center" type="button">
                  <span :class="growthPlan.isPopular ? 'bg-on-primary-container' : 'bg-outline'" class="w-4 h-4 rounded-full shadow-sm"></span>
                </button>
              </div>
            </div>
            <!-- Included Modules Checklist -->
            <div class="flex flex-col gap-2.5">
              <span class="text-[11px] font-semibold text-primary uppercase tracking-wider">Included Modules ({{ modules.filter(m => m.growth).length }})</span>
              <div class="flex flex-col gap-2 bg-surface-container-lowest/60 p-3 rounded-xl border border-outline-variant/40">
                <label v-for="(mod, idx) in modules" :key="idx" class="flex items-center justify-between cursor-pointer group">
                  <span class="text-[13px] transition-colors" :class="mod.growth ? 'text-on-surface group-hover:text-primary' : 'text-outline group-hover:text-on-surface'">{{ mod.name }}</span>
                  <input v-model="mod.growth" @change="markUnsaved" class="rounded border-outline-variant/65 bg-surface-container text-primary-container focus:ring-0 focus:ring-offset-0 transition-colors" type="checkbox"/>
                </label>
              </div>
            </div>
            <!-- Support Level -->
            <div class="flex flex-col gap-1.5">
              <label class="text-[11px] font-semibold text-outline">Service Level Agreement (SLA)</label>
              <input v-model="growthPlan.sla" @input="markUnsaved" class="bg-surface-container-lowest border border-outline-variant/65 rounded-lg px-3 py-2 text-on-surface-variant text-[13px] focus:outline-none focus:border-primary transition-all" type="text"/>
            </div>
          </div>
          <!-- Card Footer Save -->
          <button @click="saveEdits" class="w-full py-2.5 rounded-full border border-primary bg-primary/10 text-primary text-[14px] font-semibold hover:bg-primary/20 transition-all duration-150 active:scale-98 shadow-[0_0_12px_rgba(16,185,129,0.2)]">
            Save Growth Plan
          </button>
        </div>

        <!-- PLAN CARD 3: ENTERPRISE -->
        <div class="bg-surface-container-low border border-outline-variant/65 rounded-2xl p-6 flex flex-col justify-between gap-6 transition-all duration-200 hover:border-outline shadow-lg">
          <div class="flex flex-col gap-5">
            <!-- Header & Badge -->
            <div class="flex items-center justify-between h-7">
              <span class="text-[11px] font-semibold text-outline tracking-wider uppercase">Tier 03</span>
              <span class="text-[11px] text-outline font-mono">SKU: KVX-ENT-03</span>
            </div>
            <!-- Editable Plan Name -->
            <div class="flex flex-col gap-1.5">
              <label class="text-[11px] font-semibold text-outline">Plan Display Name</label>
              <input v-model="enterprisePlan.name" @input="markUnsaved" class="bg-surface-container-lowest border border-outline-variant/65 rounded-lg px-3.5 py-2 text-[18px] font-bold text-on-surface focus:outline-none focus:border-primary-container focus:ring-1 focus:ring-primary-container transition-all" style="font-family: 'Plus Jakarta Sans', sans-serif;" type="text"/>
            </div>
            <!-- Short Tagline -->
            <div class="flex flex-col gap-1.5">
              <label class="text-[11px] font-semibold text-outline">Short Tagline</label>
              <textarea v-model="enterprisePlan.tagline" @input="markUnsaved" class="bg-surface-container-lowest border border-outline-variant/65 rounded-lg px-3 py-2 text-on-surface-variant text-[13px] resize-none focus:outline-none focus:border-primary-container transition-all" rows="2"></textarea>
            </div>
            <!-- Price Specification Field (Custom) -->
            <div class="flex flex-col gap-1.5">
              <label class="text-[11px] font-semibold text-outline">Base Recurring Price</label>
              <div class="flex items-center bg-surface-container-lowest border border-outline-variant/65 rounded-lg px-3 py-2 focus-within:border-primary-container transition-all">
                <span class="material-symbols-outlined text-primary text-[18px] mr-2">lock</span>
                <input v-model="enterprisePlan.price" @input="markUnsaved" class="bg-transparent border-0 p-0 text-primary text-[16px] font-semibold focus:ring-0 w-full" type="text"/>
                <span class="px-2 py-0.5 rounded text-[10px] uppercase font-bold bg-surface-container-high text-outline ml-2 whitespace-nowrap">Tiered SLA</span>
              </div>
            </div>
            <!-- Switch Parameters -->
            <div class="flex flex-col gap-3 py-3 border-y border-outline-variant/40">
              <div class="flex items-center justify-between">
                <span class="text-[13px] text-on-surface font-semibold">Custom Pricing Mode</span>
                <button @click="enterprisePlan.isCustom = !enterprisePlan.isCustom; markUnsaved()" :class="enterprisePlan.isCustom ? 'bg-primary-container justify-end' : 'bg-surface-container-highest justify-start'" class="w-9 h-5 rounded-full p-0.5 transition-colors relative flex items-center" type="button">
                  <span :class="enterprisePlan.isCustom ? 'bg-on-primary-container' : 'bg-outline'" class="w-4 h-4 rounded-full shadow-sm"></span>
                </button>
              </div>
              <div class="flex items-center justify-between">
                <span class="text-[13px] text-on-surface">Flag as "Most Popular"</span>
                <button @click="enterprisePlan.isPopular = !enterprisePlan.isPopular; markUnsaved()" :class="enterprisePlan.isPopular ? 'bg-primary-container justify-end' : 'bg-surface-container-highest justify-start'" class="w-9 h-5 rounded-full p-0.5 transition-colors relative flex items-center" type="button">
                  <span :class="enterprisePlan.isPopular ? 'bg-on-primary-container' : 'bg-outline'" class="w-4 h-4 rounded-full shadow-sm"></span>
                </button>
              </div>
            </div>
            <!-- Included Modules Checklist -->
            <div class="flex flex-col gap-2.5">
              <div class="flex items-center justify-between">
                <span class="text-[11px] font-semibold text-outline uppercase tracking-wider">Included Modules ({{ modules.filter(m => m.enterprise).length }})</span>
                <span v-if="modules.every(m => m.enterprise)" class="text-[11px] font-semibold text-primary">All Modules Active</span>
              </div>
              <div class="flex flex-col gap-2 bg-surface-container-lowest/60 p-3 rounded-xl border border-outline-variant/40">
                <label v-for="(mod, idx) in modules" :key="idx" class="flex items-center justify-between cursor-pointer group">
                  <span class="text-[13px] transition-colors" :class="mod.enterprise ? 'text-on-surface group-hover:text-primary' : 'text-outline group-hover:text-on-surface'">{{ mod.name }}</span>
                  <input v-model="mod.enterprise" @change="markUnsaved" class="rounded border-outline-variant/65 bg-surface-container text-primary-container focus:ring-0 focus:ring-offset-0 transition-colors" type="checkbox"/>
                </label>
              </div>
            </div>
            <!-- Support Level -->
            <div class="flex flex-col gap-1.5">
              <label class="text-[11px] font-semibold text-outline">Service Level Agreement (SLA)</label>
              <input v-model="enterprisePlan.sla" @input="markUnsaved" class="bg-surface-container-lowest border border-outline-variant/65 rounded-lg px-3 py-2 text-on-surface-variant text-[13px] focus:outline-none focus:border-primary-container transition-all" type="text"/>
            </div>
          </div>
          <!-- Card Footer Save -->
          <button @click="saveEdits" class="w-full py-2.5 rounded-full border border-primary/50 text-primary text-[14px] font-semibold hover:bg-primary/10 transition-all duration-150 active:scale-98">
            Save Enterprise Plan
          </button>
        </div>

      </section>

      <!-- SECTION 4: MODULE ADD-ON MATRIX TABLE -->
      <section class="bg-surface-container-low border border-outline-variant/40 rounded-2xl p-6 flex flex-col gap-4 shadow-lg">
        <div class="flex flex-col gap-1 pb-4 border-b border-outline-variant/40">
          <h2 class="text-[18px] font-semibold text-on-surface" style="font-family: 'Plus Jakarta Sans', sans-serif;">Consolidated Module Add-on Matrix</h2>
          <p class="text-[13px] text-on-surface-variant">Configure cross-plan module inclusions and individual standalone add-on access.</p>
        </div>
        <div class="overflow-x-auto custom-scroll">
          <table class="w-full text-left border-collapse">
            <thead>
              <tr class="bg-surface-dim border-b border-outline-variant/65">
                <th class="py-3.5 px-4 text-[11px] font-semibold text-outline uppercase tracking-wider">Module Name</th>
                <th class="py-3.5 px-4 text-[11px] font-semibold text-outline uppercase tracking-wider text-center">{{ starterPlan.name }} (₹{{ starterPlan.price }})</th>
                <th class="py-3.5 px-4 text-[11px] font-semibold text-primary uppercase tracking-wider text-center">{{ growthPlan.name }} (₹{{ growthPlan.price }})</th>
                <th class="py-3.5 px-4 text-[11px] font-semibold text-outline uppercase tracking-wider text-center">{{ enterprisePlan.name }} ({{ enterprisePlan.price.split('—')[0].trim() }})</th>
              </tr>
            </thead>
            <tbody class="divide-y divide-outline-variant/30 text-[14px]">
              <tr v-if="modules.length === 0">
                <td colspan="4" class="py-8 px-4 text-center text-[13px] text-outline italic">No modules configured.</td>
              </tr>
              <tr v-for="mod in modules" :key="mod.name" class="hover:bg-surface-container transition-colors">
                <td class="py-4 px-4 font-semibold text-on-surface flex items-center gap-2.5">
                  <span class="w-2 h-2 rounded-full" :class="(mod.starter && mod.growth && mod.enterprise) ? 'bg-primary' : (!mod.starter && !mod.growth && !mod.enterprise) ? 'bg-error' : 'bg-outline'"></span>
                  {{ mod.name }}
                </td>
                
                <!-- Starter Col -->
                <td class="py-4 px-4 text-center">
                  <span v-if="mod.starter" class="inline-flex items-center gap-1.5 text-primary text-[13px] font-medium">
                    <span class="material-symbols-outlined text-[18px]">check_circle</span>
                    Included
                  </span>
                  <button v-else class="inline-flex items-center gap-1.5 px-3 py-1 rounded-full bg-surface-container-high border border-outline-variant/65 text-on-surface text-[13px] hover:border-primary transition-colors">
                    <span class="text-primary font-bold">+</span> Add-on (₹999/mo)
                  </button>
                </td>
                
                <!-- Growth Col -->
                <td class="py-4 px-4 text-center" :class="{ 'bg-primary/5': mod.growth }">
                  <span v-if="mod.growth" class="inline-flex items-center gap-1.5 text-primary text-[13px] font-medium">
                    <span class="material-symbols-outlined text-[18px]">check_circle</span>
                    Included
                  </span>
                  <button v-else class="inline-flex items-center gap-1.5 px-3 py-1 rounded-full bg-surface-container-high border border-primary/50 text-primary text-[13px] hover:bg-primary/10 transition-colors">
                    <span class="font-bold">+</span> Add-on (₹1,499/mo)
                  </button>
                </td>
                
                <!-- Enterprise Col -->
                <td class="py-4 px-4 text-center">
                  <span v-if="mod.enterprise" class="inline-flex items-center gap-1.5 text-primary text-[13px] font-medium">
                    <span class="material-symbols-outlined text-[18px]">check_circle</span>
                    Included
                  </span>
                  <span v-else class="inline-flex items-center gap-1 text-outline text-[12px] font-semibold">
                    <span class="material-symbols-outlined text-[16px] text-error">block</span>
                    Not Available
                  </span>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </section>

    </main>

    <!-- GLOBAL SAVE BAR (Sticky bottom command anchor) -->
    <aside v-if="hasUnsavedChanges" class="fixed bottom-0 right-0 lg:left-[260px] left-0 bg-surface-container border-t border-primary/40 px-8 py-3.5 flex flex-col sm:flex-row items-center justify-between gap-4 shadow-[0_-8px_32px_rgba(0,0,0,0.8)] z-50 animate-in slide-in-from-bottom-5 duration-300">
      <div class="flex items-center gap-3">
        <span class="relative flex h-3 w-3">
          <span class="animate-ping absolute inline-flex h-full w-full rounded-full bg-primary opacity-75"></span>
          <span class="relative inline-flex rounded-full h-3 w-3 bg-primary-container"></span>
        </span>
        <div class="flex items-center gap-2">
          <span class="font-semibold text-[16px] text-on-surface">You have unsaved pricing changes</span>
          <span class="text-outline hidden sm:inline">•</span>
          <span class="text-[13px] text-on-surface-variant hidden sm:inline">Modifications pending synchronization</span>
        </div>
      </div>
      <div class="flex items-center gap-3 w-full sm:w-auto">
        <button @click="discardEdits" class="flex-1 sm:flex-none px-5 py-2 rounded-full border border-outline-variant/80 text-on-surface-variant hover:text-on-surface hover:bg-surface-container-high text-[16px] font-semibold transition-all active:scale-98">
          Discard
        </button>
        <button @click="saveEdits" class="flex-1 sm:flex-none px-7 py-2 rounded-full bg-gradient-to-r from-emerald-500 to-emerald-600 hover:from-emerald-400 hover:to-emerald-500 text-white text-[16px] font-semibold shadow-[0_0_16px_rgba(16,185,129,0.35)] transition-all duration-150 active:scale-98 whitespace-nowrap">
          Save All Changes
        </button>
      </div>
    </aside>

    <AdminToast ref="toastRef" />
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'

definePageMeta({ layout: 'admin', middleware: ['admin-auth'] })

const toastRef = ref()
const hasUnsavedChanges = ref(false)

const markUnsaved = () => {
  hasUnsavedChanges.value = true
}

const saveEdits = () => {
  hasUnsavedChanges.value = false
  toastRef.value?.addToast('Pricing configuration synchronized successfully.')
}

const discardEdits = () => {
  hasUnsavedChanges.value = false
  toastRef.value?.addToast('Changes discarded.')
}

const starterPlan = ref({
  name: '',
  tagline: '',
  price: '',
  isCustom: false,
  isPopular: false,
  sla: ''
})
const growthPlan = ref({
  name: '',
  tagline: '',
  price: '',
  isCustom: false,
  isPopular: false,
  sla: ''
})
const enterprisePlan = ref({
  name: '',
  tagline: '',
  price: '',
  isCustom: false,
  isPopular: false,
  sla: ''
})

const modules = ref<any[]>([])
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
  width: 5px;
  height: 5px;
}
.custom-scroll::-webkit-scrollbar-track {
  background: #101419;
}
.custom-scroll::-webkit-scrollbar-thumb {
  background: #2b3734;
  border-radius: 4px;
}

/* Base animations */
.fade-in {
  animation: fadeIn 0.3s ease-out forwards;
}
.zoom-in-95 {
  animation: zoomIn 0.3s cubic-bezier(0.16, 1, 0.3, 1) forwards;
}
.slide-in-from-bottom-5 {
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
</style>
