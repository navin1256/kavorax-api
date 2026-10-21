<template>
  <div class="min-h-screen w-full relative flex items-center justify-center p-6 md:p-12 lg:p-16 select-none bg-[#0b0f14] overflow-x-hidden text-white font-sans">
    
    <!-- AMBIENT ABSTRACT FLOWING WAVE (Matches exact position, diagonal swoop, and scale of reference) -->
    <div class="absolute inset-0 pointer-events-none overflow-hidden z-0">
      <!-- Base deep atmospheric mesh -->
      <div class="absolute -top-[15%] -left-[10%] w-[55%] h-[55%] rounded-full bg-emerald-950/20 blur-[130px]"></div>
      <div class="absolute top-[40%] -left-[15%] w-[85%] h-[75%] wave-ribbon"></div>
      
      <!-- Flowing sculptural curved lines mimicking reference organic ribbons -->
      <svg class="absolute bottom-0 left-0 w-full h-[65%] opacity-35" viewBox="0 0 1440 600" fill="none" preserveAspectRatio="none">
        <path d="M-100,520 C320,580 480,380 840,430 C1200,480 1340,320 1600,280" stroke="url(#emeraldGradient)" stroke-width="2.5" stroke-linecap="round"/>
        <path d="M-120,440 C280,480 420,290 760,340 C1100,390 1280,240 1560,190" stroke="url(#emeraldGradientDim)" stroke-width="1.5" stroke-dasharray="8 6"/>
        <defs>
          <linearGradient id="emeraldGradient" x1="0" y1="0" x2="1" y2="0">
            <stop offset="0%" stop-color="#10b981" stop-opacity="0.05" />
            <stop offset="35%" stop-color="#10b981" stop-opacity="0.65" />
            <stop offset="65%" stop-color="#34d399" stop-opacity="0.45" />
            <stop offset="100%" stop-color="#10b981" stop-opacity="0.02" />
          </linearGradient>
          <linearGradient id="emeraldGradientDim" x1="0" y1="0" x2="1" y2="0">
            <stop offset="0%" stop-color="#10b981" stop-opacity="0" />
            <stop offset="45%" stop-color="#34d399" stop-opacity="0.25" />
            <stop offset="100%" stop-color="#10b981" stop-opacity="0" />
          </linearGradient>
        </defs>
      </svg>
    </div>

    <!-- MAIN CANVAS CONTAINER -->
    <div class="relative z-10 w-full max-w-[1340px] mx-auto min-h-[640px] flex flex-col justify-between">
      
      <!-- TOP-LEFT: KAVORAX BRAND LOGO LOCKUP -->
      <header class="w-full flex items-center justify-between mb-8 lg:mb-4">
        <div class="group cursor-pointer">
          <img src="/images/kavorax-logo-full.jpg" alt="Kavorax Global Private Limited" class="h-[75px] object-contain mix-blend-screen -ml-4" />
        </div>

        <!-- Subtle node status indicator -->
        <div class="hidden md:flex items-center gap-2 px-3 py-1 rounded-full bg-[#161b20]/60 border border-[rgba(43,55,52,0.65)] text-xs text-[#999999]">
          <span class="w-2 h-2 rounded-full bg-emerald-400 animate-pulse"></span>
          <span class="text-gray-300 font-mono text-[11px]">Zero-Trust Enclave • Node IN-BLR</span>
        </div>
      </header>

      <!-- CENTER CONTENT: LEFT HERO MOMENT + RIGHT COMPACT LOGIN CARD -->
      <main class="w-full grid grid-cols-1 lg:grid-cols-12 gap-12 lg:gap-8 items-center my-auto py-4">
        
        <!-- LEFT REGION (Col 1-7): FLOATING SYSTEM CHAT BUBBLES + OVERSIZED WATERMARK WORDMARK -->
        <section class="lg:col-span-7 flex flex-col justify-center relative pl-0 lg:pl-6 select-none">
          
          <!-- 4 FLOATING DIALOGUE BUBBLES -->
          <div class="flex flex-col items-start gap-2.5 mb-8 ml-6 md:ml-16 z-20">
            <!-- Bubble 1 -->
            <div class="glass-bubble px-4 py-2 rounded-xl rounded-bl-sm text-xs text-gray-300 flex items-center gap-2 transition hover:border-emerald-500/50 hover:bg-[#161b20]/80">
              <span class="material-symbols-outlined text-[15px] text-emerald-400">waving_hand</span>
              <span>Hi Operations Admin,</span>
            </div>
            <!-- Bubble 2 -->
            <div class="glass-bubble px-4 py-2 rounded-xl rounded-bl-sm text-xs text-gray-200 flex items-center gap-2 transition hover:border-emerald-500/50 hover:bg-[#161b20]/80">
              <span class="material-symbols-outlined text-[15px] text-emerald-400">sync</span>
              <span>Cluster telemetry is online and operational.</span>
            </div>
            <!-- Bubble 3 -->
            <div class="glass-bubble px-4 py-2 rounded-xl rounded-bl-sm text-xs text-gray-300 flex items-center gap-2 transition hover:border-emerald-500/50 hover:bg-[#161b20]/80">
              <span class="material-symbols-outlined text-[15px] text-emerald-400">encrypted</span>
              <span>Establishing hardware-verified tunnel...</span>
            </div>
            <!-- Bubble 4 -->
            <div class="glass-bubble px-4 py-2 rounded-xl rounded-bl-sm text-xs text-emerald-300/90 font-medium flex items-center gap-2 border border-emerald-500/35 transition hover:border-emerald-500/60 hover:bg-[#161b20]/80">
              <span class="material-symbols-outlined text-[15px] text-emerald-400">verified_user</span>
              <span>Please authenticate your enterprise credentials.</span>
            </div>
          </div>

          <!-- OVERSIZED BRAND MOMENT -->
          <div class="relative flex flex-col select-none mt-2">
            <!-- Sub-prefix label "THE" matching reference -->
            <div class="flex items-center gap-3 ml-2 mb-1">
              <span class="text-[11px] font-mono tracking-[0.3em] font-semibold text-emerald-400/80 uppercase">THE</span>
              <div class="h-[1px] w-12 bg-emerald-500/30"></div>
            </div>

            <!-- Giant wordmark 'KAVORAX' with stylized gradient & futuristic dots -->
            <div class="relative">
              <h1 class="font-display font-extrabold text-[58px] sm:text-[76px] md:text-[96px] lg:text-[104px] tracking-tight leading-none bg-gradient-to-r from-white via-emerald-100 to-emerald-400/70 bg-clip-text text-transparent drop-shadow-[0_4px_24px_rgba(0,0,0,0.6)]">
                kavorax
              </h1>
              
              <!-- Futuristic geometric ambient dots mimicking reference -->
              <div class="absolute -left-3 top-1/2 -translate-y-1/2 w-2 h-2 rounded-full bg-emerald-400/60 shadow-[0_0_8px_#10b981]"></div>
              <div class="absolute left-6 bottom-1 w-1.5 h-1.5 rounded-full bg-emerald-400/40"></div>
            </div>

            <!-- Sub-tagline "FUTURE OF BUSINESS OPERATIONS" -->
            <div class="flex items-center gap-3 ml-2 mt-2">
              <span class="text-[10px] md:text-[11px] font-mono tracking-[0.32em] font-semibold text-gray-400/80 uppercase">
                FUTURE OF BUSINESS OPERATIONS
              </span>
            </div>
          </div>
        </section>

        <!-- RIGHT REGION (Col 8-12): COMPACT AUTHENTICATION CARD -->
        <section class="lg:col-span-5 flex justify-center lg:justify-end">
          <div class="w-full max-w-[430px] rounded-2xl bg-[#161b20] border border-[rgba(43,55,52,0.65)] p-8 sm:p-9 login-card-shadow relative">
            
            <!-- Subtle top emerald border highlight -->
            <div class="absolute top-0 left-8 right-8 h-[2px] bg-gradient-to-r from-transparent via-emerald-500/60 to-transparent"></div>

            <!-- CARD HEADER -->
            <div class="mb-6 border-b border-[rgba(43,55,52,0.65)] pb-6">
              <h2 class="font-display font-bold text-2xl text-white tracking-tight">
                Sign in to KavoraX
              </h2>
              <p class="text-xs text-[#999999] mt-1.5">
                Secure enterprise operations management portal
              </p>
            </div>

            <!-- AUTHENTICATION FORM -->
            <form class="space-y-4" @submit.prevent="handleLogin">
              
              <!-- INPUT FIELD 1: IDENTIFIER -->
              <div>
                <div class="relative">
                  <span class="material-symbols-outlined absolute left-3.5 top-1/2 -translate-y-1/2 text-[18px] text-[#999999]">mail</span>
                  <input 
                    v-model="email"
                    type="email" 
                    class="w-full bg-[#0b0f14] text-white text-sm rounded-xl pl-11 py-3.5 pr-4 border focus:outline-none focus:ring-1 transition font-mono tracking-wide" 
                    :class="errorMsg ? 'border-red-500 focus:border-red-500 focus:ring-red-500' : 'border-[rgba(43,55,52,0.65)] focus:border-emerald-500 focus:ring-emerald-500 placeholder-gray-500'"
                    placeholder="Enter staff email address"
                    required
                  />
                </div>
              </div>

              <!-- INPUT FIELD 2: PASSWORD -->
              <div>
                <div class="relative">
                  <input 
                    v-model="password"
                    :type="showPassword ? 'text' : 'password'" 
                    class="w-full bg-[#0b0f14] text-white text-sm rounded-xl px-4 py-3.5 pr-11 border focus:outline-none focus:ring-1 transition font-mono" 
                    :class="errorMsg ? 'border-red-500 focus:border-red-500 focus:ring-red-500' : 'border-[rgba(43,55,52,0.65)] focus:border-emerald-500 focus:ring-emerald-500 placeholder-gray-500'"
                    placeholder="Enter access passcode"
                    required
                  />
                  <button 
                    type="button" 
                    @click="showPassword = !showPassword"
                    class="absolute right-3.5 top-1/2 -translate-y-1/2 text-[#999999] hover:text-emerald-400 transition flex items-center justify-center p-1"
                    aria-label="Toggle password visibility"
                  >
                    <span class="material-symbols-outlined text-[19px]">{{ showPassword ? 'visibility_off' : 'visibility' }}</span>
                  </button>
                </div>
              </div>

              <!-- DYNAMIC ERROR MSG -->
              <div v-if="errorMsg" class="animate-in fade-in zoom-in-95 duration-200">
                <div class="flex items-center gap-2 text-red-400 bg-red-400/10 border border-red-400/30 rounded-lg px-3 py-2 text-[12px]">
                  <span class="material-symbols-outlined text-[16px] text-red-400 select-none">error</span>
                  <span class="font-medium">{{ errorMsg }}</span>
                </div>
              </div>

              <!-- CONSENT ROW -->
              <div class="flex items-start gap-2.5 pt-1.5 pb-2">
                <input 
                  type="checkbox" 
                  id="termsConsent" 
                  checked 
                  class="mt-1 w-4 h-4 rounded border-[rgba(43,55,52,0.8)] bg-[#0b0f14] text-emerald-500 focus:ring-emerald-500 focus:ring-offset-0 focus:ring-1 accent-emerald-500 cursor-pointer"
                />
                <label for="termsConsent" class="text-xs text-[#999999] leading-tight cursor-pointer select-none">
                  I agree to the <a href="#" class="text-emerald-400 hover:text-emerald-300 underline underline-offset-2">Terms of Service</a> and <a href="#" class="text-emerald-400 hover:text-emerald-300 underline underline-offset-2">Privacy Policy</a>
                </label>
              </div>

              <!-- PRIMARY CTA BUTTON -->
              <button 
                type="submit" 
                :disabled="isLoading"
                class="w-full py-3.5 px-6 rounded-xl font-display font-semibold text-sm text-white bg-gradient-to-r from-emerald-500 to-emerald-600 hover:from-emerald-400 hover:to-emerald-500 active:scale-[0.99] transition shadow-[0_6px_20px_rgba(16,185,129,0.35)] flex items-center justify-center gap-2 disabled:opacity-70 disabled:active:scale-100"
              >
                <span>{{ isLoading ? 'Authenticating...' : 'Sign In' }}</span>
                <span class="material-symbols-outlined text-[18px]">arrow_forward</span>
              </button>

              <!-- CARD FOOTER -->
              <div class="pt-3 text-center">
                <a href="#" class="text-xs text-[#999999] hover:text-emerald-400 transition inline-flex items-center gap-1">
                  <span>Don't have access?</span>
                  <span class="text-emerald-400 hover:underline">Contact Administrator</span>
                </a>
              </div>
            </form>

            <!-- COMPLIANCE & SECURITY FOOTER EMBED -->
            <div class="mt-6 pt-4 border-t border-[rgba(43,55,52,0.4)] flex items-center justify-between text-[11px] text-gray-500 font-mono">
              <span class="flex items-center gap-1.5">
                <span class="material-symbols-outlined text-[13px] text-emerald-500/80">lock</span>
                <span>256-bit TLS Enclave</span>
              </span>
              <span>v2.4.8-PROD</span>
            </div>
          </div>
        </section>

      </main>

      <!-- FOOTER BENCHMARK -->
      <footer class="w-full flex items-center justify-between pt-6 text-[11px] text-[#999999] border-t border-[rgba(43,55,52,0.3)]">
        <span>© 2026 KavoraX Global Private Limited. All rights reserved.</span>
        <div class="flex items-center gap-4">
          <a href="#" class="hover:text-emerald-400 transition">System Status: Optimal</a>
          <span>•</span>
          <a href="#" class="hover:text-emerald-400 transition">Security Policy</a>
        </div>
      </footer>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useAdminAuthStore } from '~/stores/adminAuth'

definePageMeta({
  layout: false
})

const email = ref('ops.command@kavorax.com')
const password = ref('')
const showPassword = ref(false)
const errorMsg = ref('')
const isLoading = ref(false)

const router = useRouter()
const authStore = useAdminAuthStore()

const config = useRuntimeConfig()

const handleLogin = async () => {
  errorMsg.value = ''
  
  if (!email.value || !password.value) {
    errorMsg.value = 'Please fill in all fields'
    return
  }
  
  isLoading.value = true
  
  try {
    const response = await $fetch<{ token: string, user: { id: number, email: string, role: string } }>('/v1/auth/login', {
      baseURL: config.public.apiBase,
      method: 'POST',
      body: {
        email: email.value,
        password: password.value
      }
    })
    
    authStore.login(response.token, response.user)
    router.push('/admin')
  } catch (error: any) {
    if (error.response?.status === 401) {
      errorMsg.value = 'Invalid email or password'
    } else if (error.response?.status === 400) {
      errorMsg.value = 'Invalid input format'
    } else {
      errorMsg.value = 'Server unavailable. Please try again later.'
    }
  } finally {
    isLoading.value = false
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600&family=Plus+Jakarta+Sans:wght@500;600;700;800&display=swap');

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
  font-variation-settings: 'FILL' 0, 'wght' 300, 'GRAD' 0, 'opsz' 20;
}
.font-display {
  font-family: 'Plus Jakarta Sans', sans-serif;
}
.font-sans {
  font-family: 'Inter', sans-serif;
}

/* Ambient organic wave styling mirroring the reference diagonal wave */
.wave-ribbon {
  background: radial-gradient(ellipse 90% 50% at 30% 60%, rgba(16, 185, 129, 0.22) 0%, rgba(52, 211, 153, 0.08) 45%, rgba(11, 15, 20, 0) 80%);
  filter: blur(55px);
}
.wave-line {
  background: linear-gradient(90deg, rgba(16, 185, 129, 0.02) 0%, rgba(16, 185, 129, 0.35) 40%, rgba(52, 211, 153, 0.45) 60%, rgba(16, 185, 129, 0.05) 100%);
}
.glass-bubble {
  background: rgba(22, 27, 32, 0.65);
  backdrop-filter: blur(12px);
  -webkit-backdrop-filter: blur(12px);
  border: 1px solid rgba(16, 185, 129, 0.2);
  box-shadow: 0 8px 24px -4px rgba(0, 0, 0, 0.45), 0 0 16px -2px rgba(16, 185, 129, 0.08);
}
.login-card-shadow {
  box-shadow: 0 24px 60px -12px rgba(0, 0, 0, 0.8), 0 0 45px -10px rgba(16, 185, 129, 0.08);
}

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
</style>
