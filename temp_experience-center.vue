<template>
  <div class="relative flex min-h-screen flex-col bg-background overflow-x-hidden">
    <main class="w-full">
      <!-- 1. HERO -->
      <section class="max-w-7xl mx-auto px-margin py-space-xl grid grid-cols-1 lg:grid-cols-12 gap-12 items-center">
        <div class="lg:col-span-6 flex flex-col gap-6">
          <h1 class="text-headline-lg font-headline-lg text-text-primary">See KavoraX working for your business.</h1>
          <p class="text-body-lg text-text-secondary">Book a live online demo, or visit our Experience Center and try it yourself.</p>
          <div class="grid grid-cols-1 sm:grid-cols-2 gap-4 mt-4">
            <div 
              @click="choice = 'online'"
              :class="['cursor-pointer p-6 rounded-xl border-2 transition-all flex flex-col gap-3', choice === 'online' ? 'bg-background-secondary border-primary' : 'bg-background-secondary border-outline-variant']"
            >
              <div class="w-10 h-10 rounded-full bg-surface-container flex items-center justify-center text-primary">
                <span class="material-symbols-outlined" style="font-variation-settings: 'FILL' 1;">video_camera_front</span>
              </div>
              <h3 class="text-headline-sm font-headline-sm text-text-primary">Online demo</h3>
              <p class="text-body-md text-text-secondary">30-minute live walkthrough from your desk</p>
            </div>
            
            <div 
              @click="choice = 'visit'"
              :class="['cursor-pointer p-6 rounded-xl border-2 transition-all flex flex-col gap-3', choice === 'visit' ? 'bg-background-secondary border-primary' : 'bg-background-secondary border-outline-variant']"
            >
              <div class="w-10 h-10 rounded-full bg-surface-container flex items-center justify-center text-primary">
                <span class="material-symbols-outlined" style="font-variation-settings: 'FILL' 1;">location_on</span>
              </div>
              <h3 class="text-headline-sm font-headline-sm text-text-primary">Visit our Experience Center</h3>
              <p class="text-body-md text-text-secondary">Try the platform and hardware in person</p>
            </div>
          </div>
        </div>
        
        <div class="lg:col-span-6 relative">
          <div class="relative rounded-2xl overflow-hidden shadow-2xl border border-outline-variant h-[400px]">
            <div class="absolute inset-0 bg-cover bg-center" style="background-image: url('/images/smiling-shop-owner.jpg')"></div>
          </div>
          <div class="absolute -bottom-6 -left-6 w-48 h-36 rounded-xl overflow-hidden shadow-2xl border-2 border-primary hidden sm:block">
            <div class="absolute inset-0 bg-cover bg-center" style="background-image: url('/images/hotel-concierge.jpg')"></div>
          </div>
        </div>
      </section>

      <!-- 2. BOOKING FORM -->
      <section class="bg-background-secondary py-space-xl border-t border-outline-variant" id="booking-form">
        <div class="max-w-4xl mx-auto px-margin">
          <div class="bg-surface p-8 sm:p-12 rounded-2xl border border-outline-variant shadow-xl">
            <h2 class="text-headline-md font-headline-md text-text-primary mb-2">Schedule Your Session</h2>
            <p class="text-body-md text-text-secondary mb-8">Complete the details below to secure your personalized walkthrough.</p>
            <form v-if="!formSuccess" class="flex flex-col gap-6" @submit.prevent="onSubmit">
              <!-- Form Error -->
              <div v-if="formError" class="p-4 bg-red-500/10 border border-red-500/20 rounded-xl text-red-400 text-sm">
                {{ formError }}
              </div>

              <div class="grid grid-cols-1 sm:grid-cols-2 gap-6">
                <div class="flex flex-col gap-2">
                  <label class="text-label-md font-label-md text-text-primary">Full name <span class="text-primary">*</span></label>
                  <input v-model="name" v-bind="nameProps" :class="{'border-red-500': errors.name}" class="bg-background border border-outline-variant rounded-xl p-3 text-text-primary focus:border-primary focus:ring-1 focus:ring-primary outline-none transition-colors" type="text"/>
                  <span class="text-xs text-red-500">{{ errors.name }}</span>
                </div>
                <div class="flex flex-col gap-2">
                  <label class="text-label-md font-label-md text-text-primary">Business name <span class="text-primary">*</span></label>
                  <input v-model="company" v-bind="companyProps" :class="{'border-red-500': errors.company}" class="bg-background border border-outline-variant rounded-xl p-3 text-text-primary focus:border-primary focus:ring-1 focus:ring-primary outline-none transition-colors" type="text"/>
                  <span class="text-xs text-red-500">{{ errors.company }}</span>
                </div>
              </div>
              <div class="grid grid-cols-1 sm:grid-cols-2 gap-6">
                <div class="flex flex-col gap-2">
                  <label class="text-label-md font-label-md text-text-primary">Work email <span class="text-primary">*</span></label>
                  <input v-model="email" v-bind="emailProps" :class="{'border-red-500': errors.email}" class="bg-background border border-outline-variant rounded-xl p-3 text-text-primary focus:border-primary focus:ring-1 focus:ring-primary outline-none transition-colors" type="email"/>
                  <span class="text-xs text-red-500">{{ errors.email }}</span>
                </div>
                <div class="flex flex-col gap-2">
                  <label class="text-label-md font-label-md text-text-primary">Phone/WhatsApp</label>
                  <input v-model="phone" v-bind="phoneProps" :class="{'border-red-500': errors.phone}" class="bg-background border border-outline-variant rounded-xl p-3 text-text-primary focus:border-primary focus:ring-1 focus:ring-primary outline-none transition-colors" type="tel"/>
                  <span class="text-xs text-red-500">{{ errors.phone }}</span>
                </div>
              </div>
              <div class="grid grid-cols-1 sm:grid-cols-2 gap-6">
                <div class="flex flex-col gap-2">
                  <label class="text-label-md font-label-md text-text-primary">Business type</label>
                  <select v-model="businessType" v-bind="businessTypeProps" :class="{'border-red-500': errors.businessType}" class="bg-background border border-outline-variant rounded-xl p-3 text-text-primary focus:border-primary focus:ring-1 focus:ring-primary outline-none transition-colors">
                    <option value="Restaurants">Restaurants</option>
                    <option value="Cafes">Cafes</option>
                    <option value="Hospitality">Hospitality</option>
                    <option value="Retail">Retail</option>
                    <option value="Convenience Stores">Convenience Stores</option>
                    <option value="Salons">Salons</option>
                    <option value="E-commerce">E-commerce</option>
                    <option value="Growing SMEs">Growing SMEs</option>
                  </select>
                </div>
                <div class="flex flex-col gap-2">
                  <label class="text-label-md font-label-md text-text-primary">Number of outlets</label>
                  <select v-model="outlets" v-bind="outletsProps" :class="{'border-red-500': errors.outlets}" class="bg-background border border-outline-variant rounded-xl p-3 text-text-primary focus:border-primary focus:ring-1 focus:ring-primary outline-none transition-colors">
                    <option value="1 outlet">1 outlet</option>
                    <option value="2-5 outlets">2-5 outlets</option>
                    <option value="6-15 outlets">6-15 outlets</option>
                    <option value="15+ outlets">15+ outlets</option>
                  </select>
                </div>
              </div>
              <div class="flex flex-col gap-2">
                <label class="text-label-md font-label-md text-text-primary">Modules of interest</label>
                <div class="flex flex-wrap gap-2 pt-2">
                  <label v-for="module in modulesList" :key="module" class="cursor-pointer bg-background border border-outline-variant px-3 py-1.5 rounded-full text-label-md font-label-md hover:border-primary transition-colors flex items-center gap-2">
                    <input v-model="selectedModules" :value="module" class="rounded bg-background border-outline-variant text-primary focus:ring-0" type="checkbox"/> {{ module }}
                  </label>
                </div>
                <span class="text-xs text-red-500">{{ errors.selectedModules }}</span>
              </div>
              <div class="grid grid-cols-1 sm:grid-cols-2 gap-6">
                <div class="flex flex-col gap-2">
                  <label class="text-label-md font-label-md text-text-primary">Preferred date</label>
                  <input v-model="date" v-bind="dateProps" :class="{'border-red-500': errors.date}" class="bg-background border border-outline-variant rounded-xl p-3 text-text-primary focus:border-primary focus:ring-1 focus:ring-primary outline-none transition-colors" type="date"/>
                  <span class="text-xs text-red-500">{{ errors.date }}</span>
                </div>
                <div class="flex flex-col gap-2">
                  <label class="text-label-md font-label-md text-text-primary">Preferred time slot</label>
                  <select v-model="timeSlot" v-bind="timeSlotProps" :class="{'border-red-500': errors.timeSlot}" class="bg-background border border-outline-variant rounded-xl p-3 text-text-primary focus:border-primary focus:ring-1 focus:ring-primary outline-none transition-colors">
                    <option value="09:00 AM - 10:00 AM">09:00 AM - 10:00 AM</option>
                    <option value="11:00 AM - 12:00 PM">11:00 AM - 12:00 PM</option>
                    <option value="02:00 PM - 03:00 PM">02:00 PM - 03:00 PM</option>
                    <option value="04:00 PM - 05:00 PM">04:00 PM - 05:00 PM</option>
                  </select>
                </div>
              </div>
              <div class="flex items-center gap-3">
                <input v-model="privacy" v-bind="privacyProps" class="rounded bg-background border-outline-variant text-primary focus:ring-0" type="checkbox"/>
                <span class="text-label-md font-label-md text-text-secondary">I agree to the <NuxtLink to="/privacy" class="text-primary underline">Privacy Policy</NuxtLink> and consent to communication regarding my booking.</span>
              </div>
              <span class="text-xs text-red-500">{{ errors.privacy }}</span>
              
              <UiButtonCTA :disabled="isSubmitting" size="sm" variant="primary" icon="send" :label="isSubmitting ? 'Confirming...' : 'Confirm my demo'" :isSubmit="true" />
              <p class="text-center text-text-secondary text-label-md font-label-md">You'll get instant confirmation and reminders by email and WhatsApp.</p>
            </form>

            <div v-else class="p-8 text-center space-y-4 border border-primary/30 bg-primary/5 rounded-2xl">
              <span class="material-symbols-outlined text-primary text-5xl">check_circle</span>
              <h3 class="text-2xl font-headline font-bold text-white">Booking Confirmed!</h3>
              <p class="text-text-secondary">Your demo request has been received. Our team will reach out shortly to finalize the schedule.</p>
              <button @click="resetFormState" class="mt-4 text-primary hover:underline text-sm font-medium">Book another session</button>
            </div>
          </div>
        </div>
      </section>

      <!-- 3. PRODUCT TOUR -->
      <section class="bg-background-secondary py-space-xl border-t border-outline-variant">
        <div class="max-w-7xl mx-auto px-margin">
          <h2 class="text-headline-md font-headline-md text-text-primary mb-8 text-center">Explore the platform.</h2>
          <div class="flex justify-center gap-2 mb-12 flex-wrap">
            <UiButtonCTA size="sm" variant="primary" icon="arrow_forward" label="{{ data.label }}" @click="activeTab = key" />
          </div>
          
          <div class="grid grid-cols-1 lg:grid-cols-12 gap-8 items-center bg-surface p-8 rounded-2xl border border-outline-variant">
            <div class="lg:col-span-6 relative rounded-xl overflow-hidden h-[350px]">
              <div class="absolute inset-0 bg-cover bg-center" style="background-image: url('/images/about-ceo.jpg')"></div>
              <div class="absolute inset-0 bg-black/40 flex items-center justify-center">
                <div class="w-16 h-16 rounded-full bg-primary-container/80 flex items-center justify-center text-on-primary-container cursor-pointer hover:scale-105 transition-transform">
                  <span class="material-symbols-outlined text-3xl" style="font-variation-settings: 'FILL' 1;">play_arrow</span>
                </div>
              </div>
            </div>
            <div class="lg:col-span-6 flex flex-col gap-4">
              <h3 class="text-headline-sm font-headline-sm text-text-primary">{{ tabData[activeTab].title }}</h3>
              <p class="text-body-md text-text-secondary">{{ tabData[activeTab].desc }}</p>
              <ul class="flex flex-col gap-2 text-body-md text-on-surface-variant">
                <li v-for="(feature, index) in tabData[activeTab].features" :key="index" class="flex items-center gap-2">
                  <span class="material-symbols-outlined text-primary text-sm">check</span> {{ feature }}
                </li>
              </ul>
            </div>
          </div>
        </div>
      </section>

      <!-- 4. INDUSTRY DEMO PATHS -->
      <section class="max-w-7xl mx-auto px-margin py-space-xl">
        <h2 class="text-headline-md font-headline-md text-text-primary mb-8 text-center">See a demo built for your business.</h2>
        <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-5 gap-6">
          <NuxtLink to="/industries/restaurants" class="bg-background-secondary p-6 rounded-xl border border-outline-variant hover:border-primary transition-all flex flex-col gap-4">
            <span class="material-symbols-outlined text-primary text-3xl" style="font-variation-settings: 'FILL' 1;">restaurant</span>
            <h3 class="text-headline-sm font-headline-sm text-text-primary">Restaurants</h3>
            <p class="text-body-md text-text-secondary">Table mapping, KDS routing, multi-course ordering, and inventory recipe costing.</p>
          </NuxtLink>
          <NuxtLink to="/industries/cafes" class="bg-background-secondary p-6 rounded-xl border border-outline-variant hover:border-primary transition-all flex flex-col gap-4">
            <span class="material-symbols-outlined text-primary text-3xl" style="font-variation-settings: 'FILL' 1;">local_cafe</span>
            <h3 class="text-headline-sm font-headline-sm text-text-primary">Cafes</h3>
            <p class="text-body-md text-text-secondary">Rapid queue busting, loyalty integration, custom drink builders, and mobile ordering.</p>
          </NuxtLink>
          <NuxtLink to="/industries/retail" class="bg-background-secondary p-6 rounded-xl border border-outline-variant hover:border-primary transition-all flex flex-col gap-4">
            <span class="material-symbols-outlined text-primary text-3xl" style="font-variation-settings: 'FILL' 1;">storefront</span>
            <h3 class="text-headline-sm font-headline-sm text-text-primary">Retail</h3>
            <p class="text-body-md text-text-secondary">Barcode scanning, multi-location stock sync, customer profiles, and returns management.</p>
          </NuxtLink>
          <NuxtLink to="/industries/salons" class="bg-background-secondary p-6 rounded-xl border border-outline-variant hover:border-primary transition-all flex flex-col gap-4">
            <span class="material-symbols-outlined text-primary text-3xl" style="font-variation-settings: 'FILL' 1;">content_cut</span>
            <h3 class="text-headline-sm font-headline-sm text-text-primary">Salons</h3>
            <p class="text-body-md text-text-secondary">Staff scheduling, service booking, client preferences history, and retail product sales.</p>
          </NuxtLink>
          <NuxtLink to="/industries/hospitality" class="bg-background-secondary p-6 rounded-xl border border-outline-variant hover:border-primary transition-all flex flex-col gap-4">
            <span class="material-symbols-outlined text-primary text-3xl" style="font-variation-settings: 'FILL' 1;">hotel</span>
            <h3 class="text-headline-sm font-headline-sm text-text-primary">Hospitality</h3>
            <p class="text-body-md text-text-secondary">Room charging, multi-outlet property management, and unified guest billing systems.</p>
          </NuxtLink>
        </div>
      </section>

      <!-- 5. VISIT US -->
      <section class="bg-background-secondary py-space-xl border-t border-outline-variant">
        <div class="max-w-7xl mx-auto px-margin grid grid-cols-1 lg:grid-cols-12 gap-8 items-center">
          <div class="lg:col-span-5 bg-surface p-8 rounded-2xl border border-outline-variant flex flex-col gap-6">
            <span class="text-label-md font-label-md text-primary bg-primary-container/10 px-3 py-1 rounded-full w-max">Address to be confirmed</span>
            <h2 class="text-headline-md font-headline-md text-text-primary">Visit Our Experience Center</h2>
            <div class="flex flex-col gap-4 text-body-md text-text-secondary">
              <div class="flex items-start gap-3">
                <span class="material-symbols-outlined text-primary">schedule</span>
                <div>
                  <p class="text-text-primary font-bold">Opening Hours</p>
                  <p>Monday - Friday: 09:00 - 18:00</p>
                  <p>Saturday: By appointment only</p>
                </div>
              </div>
              <div class="flex items-start gap-3">
                <span class="material-symbols-outlined text-primary">call</span>
                <div>
                  <p class="text-text-primary font-bold">Direct Line</p>
                  <p>+1 (800) 555-KAVORAX</p>
                </div>
              </div>
            </div>
            <UiButtonCTA size="sm" variant="primary" icon="arrow_forward" label="Get directions" to="https://maps.google.com" target="_blank" />
          </div>
          <div class="lg:col-span-7 h-[400px] rounded-2xl overflow-hidden border border-outline-variant relative">
            <div class="absolute inset-0 bg-cover bg-center" style="background-image: url('/images/barista-checkout.jpg')"></div>
            <div class="absolute bottom-4 right-4 bg-surface/90 backdrop-blur px-4 py-2 rounded-xl border border-outline-variant text-label-md font-label-md text-text-primary">
              Global Flagship Showcase Center
            </div>
          </div>
        </div>
      </section>

      <!-- 6. WHAT HAPPENS NEXT -->
      <section class="max-w-7xl mx-auto px-margin py-space-xl">
        <h2 class="text-headline-md font-headline-md text-text-primary mb-12 text-center">What happens next.</h2>
        <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-8 relative">
          <div class="flex flex-col gap-4 bg-background-secondary p-6 rounded-xl border border-outline-variant relative z-10">
            <div class="w-10 h-10 rounded-full bg-primary-container text-on-primary-container flex items-center justify-center font-bold text-label-md">1</div>
            <h3 class="text-headline-sm font-headline-sm text-text-primary">Book</h3>
            <p class="text-body-md text-text-secondary">Select your preferred format, date, and time via our instant scheduler.</p>
          </div>
          <div class="flex flex-col gap-4 bg-background-secondary p-6 rounded-xl border border-outline-variant relative z-10">
            <div class="w-10 h-10 rounded-full bg-primary-container text-on-primary-container flex items-center justify-center font-bold text-label-md">2</div>
            <h3 class="text-headline-sm font-headline-sm text-text-primary">Quick requirements call</h3>
            <p class="text-body-md text-text-secondary">A brief 5-minute chat to tailor the session to your operational workflow.</p>
          </div>
          <div class="flex flex-col gap-4 bg-background-secondary p-6 rounded-xl border border-outline-variant relative z-10">
            <div class="w-10 h-10 rounded-full bg-primary-container text-on-primary-container flex items-center justify-center font-bold text-label-md">3</div>
            <h3 class="text-headline-sm font-headline-sm text-text-primary">Personalised demo</h3>
            <p class="text-body-md text-text-secondary">Live walkthrough focused directly on your modules and business type.</p>
          </div>
          <div class="flex flex-col gap-4 bg-background-secondary p-6 rounded-xl border border-outline-variant relative z-10">
            <div class="w-10 h-10 rounded-full bg-primary-container text-on-primary-container flex items-center justify-center font-bold text-label-md">4</div>
            <h3 class="text-headline-sm font-headline-sm text-text-primary">Proposal and setup plan</h3>
            <p class="text-body-md text-text-secondary">Receive a transparent pricing breakdown and onboarding roadmap.</p>
          </div>
        </div>
      </section>

      <!-- 7. FAQ -->
      <section class="bg-background-secondary py-space-xl border-t border-outline-variant">
        <div class="max-w-4xl mx-auto px-margin">
          <h2 class="text-headline-md font-headline-md text-text-primary mb-12 text-center">Frequently Asked Questions</h2>
          <div class="flex flex-col gap-4">
            <details class="bg-surface p-6 rounded-xl border border-outline-variant group">
              <summary class="cursor-pointer font-bold text-headline-sm text-text-primary flex justify-between items-center">
                What to expect in demo?
                <span class="material-symbols-outlined transition-transform group-open:rotate-180">expand_more</span>
              </summary>
              <p class="text-body-md text-text-secondary mt-4">Our demos are interactive and tailored specifically to your operational workflows. You will see live simulations of transactions, inventory tracking, reporting, and hardware integration.</p>
            </details>
            <details class="bg-surface p-6 rounded-xl border border-outline-variant group">
              <summary class="cursor-pointer font-bold text-headline-sm text-text-primary flex justify-between items-center">
                Whether hardware is shown?
                <span class="material-symbols-outlined transition-transform group-open:rotate-180">expand_more</span>
              </summary>
              <p class="text-body-md text-text-secondary mt-4">Yes! If you visit our Experience Center, you can test our entire range of POS terminals, kitchen display screens, barcode scanners, and receipt printers in person. For online demos, we feature high-definition hardware demonstrations.</p>
            </details>
            <details class="bg-surface p-6 rounded-xl border border-outline-variant group">
              <summary class="cursor-pointer font-bold text-headline-sm text-text-primary flex justify-between items-center">
                Setup time after booking?
                <span class="material-symbols-outlined transition-transform group-open:rotate-180">expand_more</span>
              </summary>
              <p class="text-body-md text-text-secondary mt-4">Standard cloud configurations can be deployed within 24-48 hours. Enterprise multi-outlet deployments with custom hardware integrations typically require 1 to 2 weeks.</p>
            </details>
            <details class="bg-surface p-6 rounded-xl border border-outline-variant group">
              <summary class="cursor-pointer font-bold text-headline-sm text-text-primary flex justify-between items-center">
                Cost to visit?
                <span class="material-symbols-outlined transition-transform group-open:rotate-180">expand_more</span>
              </summary>
              <p class="text-body-md text-text-secondary mt-4">Visiting our Experience Center is entirely free. Sessions are private and booked in advance to ensure dedicated one-on-one time with a KavoraX enterprise specialist.</p>
            </details>
          </div>
        </div>
      </section>

      <!-- 8. PREFER TO CHAT BANNER -->
      <section class="max-w-7xl mx-auto px-margin py-space-lg">
        <div class="bg-surface-container p-8 rounded-2xl border border-outline-variant flex flex-col sm:flex-row justify-between items-center gap-6">
          <div class="flex flex-col gap-2 text-center sm:text-left">
            <h3 class="text-headline-sm font-headline-sm text-text-primary">Prefer to chat?</h3>
            <p class="text-body-md text-text-secondary">Message our team directly on WhatsApp for immediate answers.</p>
          </div>
          <UiButtonCTA size="sm" to="https://whatsapp.com" variant="primary" icon="chat" label="Message us on WhatsApp" />
        </div>
      </section>
    </main>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useForm } from 'vee-validate'
import { toTypedSchema } from '@vee-validate/zod'
import * as z from 'zod'
import { useApi } from '~/composables/useApi'

const { apiFetch, handleApiError } = useApi()

const choice = ref('online')
const activeTab = ref<TabDataKey>('pos')

const modulesList = [
  'POS Core',
  'Inventory',
  'CRM & Loyalty',
  'Delivery Management',
  'Analytics & BI',
  'Kitchen Display (KDS)',
  'Multi-Store Hub',
  'Staff Management',
  'API Integrations'
]

type TabDataKey = 'pos' | 'inventory' | 'crm' | 'delivery' | 'analytics'

const tabData: Record<TabDataKey, { label: string, title: string, desc: string, features: string[] }> = {
  pos: {
    label: 'POS',
    title: 'Lightning-Fast Point of Sale',
    desc: 'Experience sub-second transaction processing designed for high-volume environments. Manage split bills, offline resilience, and intuitive custom modifiers effortlessly.',
    features: ['Offline fallback mode', 'Custom modifier matrices', 'Integrated payment terminals']
  },
  inventory: {
    label: 'Inventory',
    title: 'Real-Time Inventory Control',
    desc: 'Automate stock deductions, receive low-level alerts across multiple warehouses, and execute seamless batch transfers with zero manual discrepancy.',
    features: ['Multi-warehouse tracking', 'Automated purchase orders', 'Waste and variance auditing']
  },
  crm: {
    label: 'CRM',
    title: 'Advanced CRM & Loyalty',
    desc: 'Capture customer preferences, build tiered reward programs, and execute automated targeted campaigns that drive repeat visits and lifetime value.',
    features: ['Tiered loyalty points', 'SMS/Email marketing', 'Detailed customer profiling']
  },
  delivery: {
    label: 'Delivery',
    title: 'Unified Delivery Management',
    desc: 'Consolidate orders from third-party aggregators and your own fleet into a single dispatch screen with automated driver tracking and status updates.',
    features: ['Aggregator integrations', 'Live driver tracking', 'Automated dispatch routing']
  },
  analytics: {
    label: 'Analytics',
    title: 'Executive Analytics & BI',
    desc: 'Gain deep visibility into labor costs, top-selling categories, hourly revenue trends, and predictive forecasting via custom executive dashboards.',
    features: ['Custom reporting', 'Real-time telemetry', 'Multi-location consolidation']
  }
}

// Form state
const formError = ref('')
const formSuccess = ref(false)

const resetFormState = () => {
  formSuccess.value = false
  formError.value = ''
  resetForm()
}

// Zod Schema
const schema = toTypedSchema(z.object({
  name: z.string().min(2, 'Name is required').max(100, 'Name is too long'),
  company: z.string().min(2, 'Business name is required').max(100, 'Business name is too long'),
  email: z.string().email('Valid email is required'),
  phone: z.string().max(20, 'Phone number is too long').optional(),
  businessType: z.string().optional(),
  outlets: z.string().optional(),
  selectedModules: z.array(z.string()).optional(),
  date: z.string().optional(),
  timeSlot: z.string().optional(),
  privacy: z.boolean().refine(val => val === true, {
    message: 'You must agree to the privacy policy'
  })
}))

const { handleSubmit, errors, defineField, isSubmitting, resetForm } = useForm({
  validationSchema: schema,
  initialValues: {
    privacy: false,
    selectedModules: []
  }
})

const [name, nameProps] = defineField('name')
const [company, companyProps] = defineField('company')
const [email, emailProps] = defineField('email')
const [phone, phoneProps] = defineField('phone')
const [businessType, businessTypeProps] = defineField('businessType')
const [outlets, outletsProps] = defineField('outlets')
const [selectedModules] = defineField('selectedModules')
const [date, dateProps] = defineField('date')
const [timeSlot, timeSlotProps] = defineField('timeSlot')
const [privacy, privacyProps] = defineField('privacy')

const onSubmit = handleSubmit(async (values) => {
  formError.value = ''
  
  try {
    const payload = {
      name: values.name,
      email: values.email,
      company: values.company,
      phone: values.phone,
      businessType: values.businessType,
      outlets: values.outlets,
      selectedModules: values.selectedModules,
      date: values.date,
      timeSlot: values.timeSlot
    }
    
    await apiFetch('/v1/session-requests', {
      method: 'POST',
      body: payload
    })
    
    formSuccess.value = true
  } catch (error: any) {
    const apiError = handleApiError(error)
    formError.value = apiError.message
    
    if (apiError.devDetails) {
      // In development, append diagnostics to the UI error to make it visible
      if (process.dev) {
        formError.value += ` [DEV: ${apiError.devDetails}]`
      }
    }
  }
})
</script>

