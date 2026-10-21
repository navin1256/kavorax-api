<template>
  <header class="fixed top-0 left-0 w-full z-50 transition-all duration-300 bg-[#0b0f14]/85 backdrop-blur-md border-b border-[rgba(43,55,52,0.65)] py-4" id="site-header">
    <div class="max-w-[1920px] mx-auto px-6 lg:px-8 xl:px-10 2xl:px-16 flex items-center justify-between w-full">
      
      <!-- Brand Logo -->
      <NuxtLink class="flex items-center gap-3 group shrink-0 outline-none border-none" to="/">
        <img :alt="settings.siteName" class="h-10 w-auto object-contain transition-transform group-hover:scale-105" src="/images/logo.jpg"/>
      </NuxtLink>
      
      <!-- Desktop Nav & Actions Container -->
      <div class="hidden lg:flex items-center gap-6 xl:gap-10">
        
        <!-- Main Navigation Row -->
        <nav class="flex items-center gap-5 xl:gap-7 2xl:gap-8 font-sans-ui text-[11px] xl:text-xs tracking-wider uppercase text-neutral-300">
          <NuxtLink class="whitespace-nowrap hover:text-[#10b981] transition-colors outline-none border-none" active-class="text-[#10b981]" exact-active-class="text-[#10b981]" to="/">Home</NuxtLink>
          <NuxtLink class="whitespace-nowrap hover:text-[#10b981] transition-colors outline-none border-none" active-class="text-[#10b981]" to="/about">About</NuxtLink>
          <NuxtLink class="whitespace-nowrap hover:text-[#10b981] transition-colors outline-none border-none" active-class="text-[#10b981]" to="/solutions">Solutions</NuxtLink>
          
          <!-- Products Mega Menu Dropdown -->
          <div class="relative group py-2">
            <button class="flex items-center gap-1.5 whitespace-nowrap hover:text-[#10b981] transition-colors focus:outline-none outline-none border-none">
              <span>Products</span>
              <span class="material-symbols-outlined text-[16px]">expand_more</span>
            </button>
            <div class="dropdown-menu absolute top-full -left-20 w-[640px] bg-[#080c10] border border-[rgba(43,55,52,0.65)]/80 rounded-xl p-6 shadow-2xl backdrop-blur-xl grid grid-cols-2 gap-4">
              <NuxtLink v-for="mod in modules" :key="mod.slug" class="flex items-start gap-3 p-2.5 rounded-lg hover:bg-white/5 transition-colors outline-none border-none" :to="`/products/${mod.slug}`">
                <span class="material-symbols-outlined text-[#10b981] text-xl">star</span>
                <div>
                  <p class="text-sm font-semibold text-white capitalize whitespace-normal">{{ mod.name }}</p>
                  <p class="text-xs text-[#999999] normal-case font-light whitespace-normal">{{ mod.subline }}</p>
                </div>
              </NuxtLink>
            </div>
          </div>

          <!-- Industries Mega Menu Dropdown -->
          <div class="relative group py-2">
            <button class="flex items-center gap-1.5 whitespace-nowrap hover:text-[#10b981] transition-colors focus:outline-none outline-none border-none">
              <span>Industries</span>
              <span class="material-symbols-outlined text-[16px]">expand_more</span>
            </button>
            <div class="dropdown-menu absolute top-full -left-20 w-[640px] bg-[#080c10] border border-[rgba(43,55,52,0.65)]/80 rounded-xl p-6 shadow-2xl backdrop-blur-xl grid grid-cols-2 gap-4">
              <NuxtLink v-for="ind in industries" :key="ind.slug" class="flex items-start gap-3 p-2.5 rounded-lg hover:bg-white/5 transition-colors outline-none border-none" :to="`/industries/${ind.slug}`">
                <span class="material-symbols-outlined text-[#10b981] text-xl">domain</span>
                <div>
                  <p class="text-sm font-semibold text-white capitalize whitespace-normal">{{ ind.name }}</p>
                  <p class="text-xs text-[#999999] normal-case font-light whitespace-normal">{{ ind.subline }}</p>
                </div>
              </NuxtLink>
            </div>
          </div>

          <NuxtLink class="whitespace-nowrap hover:text-[#10b981] transition-colors outline-none border-none" active-class="text-[#10b981]" to="/pricing">Pricing</NuxtLink>
          <NuxtLink class="whitespace-nowrap hover:text-[#10b981] transition-colors outline-none border-none" active-class="text-[#10b981]" to="/resources">Resources</NuxtLink>
        </nav>
        
        <div class="flex items-center gap-3 border-l border-[rgba(43,55,52,0.65)] pl-6">
          <a :href="`mailto:${settings.email}`" class="text-neutral-400 hover:text-white transition-colors text-sm font-sans flex items-center gap-1.5 outline-none border-none">
            <span class="material-symbols-outlined text-[18px]">mail</span> <span class="hidden xl:inline">{{ settings.email }}</span>
          </a>
          <a :href="`tel:${settings.phone}`" class="text-neutral-400 hover:text-white transition-colors text-sm font-sans flex items-center gap-1.5 outline-none border-none">
            <span class="material-symbols-outlined text-[18px]">call</span> <span class="hidden xl:inline">{{ settings.phone }}</span>
          </a>
        </div>
        
        <div class="flex items-center gap-3">
          <UiButtonCTA to="/experience-center" variant="secondary" icon="" label="Take a Tour" size="sm" />
          <UiButtonCTA to="/contact" variant="primary" icon="arrow_forward" label="Contact Sales" size="sm" />
        </div>
      </div>

      <!-- Mobile Hamburger Button -->
      <button class="lg:hidden text-white hover:text-[#10b981] transition-colors focus:outline-none outline-none shrink-0" @click="isMobileMenuOpen = !isMobileMenuOpen" aria-label="Toggle Menu">
        <span class="material-symbols-outlined text-3xl">{{ isMobileMenuOpen ? 'close' : 'menu' }}</span>
      </button>

    </div>

    <!-- Full Screen Slide-Over Mobile Menu -->
    <div :class="{'opacity-100 translate-x-0': isMobileMenuOpen, 'opacity-0 translate-x-full': !isMobileMenuOpen, 'pointer-events-auto': isMobileMenuOpen, 'pointer-events-none': !isMobileMenuOpen}" 
         class="fixed inset-0 top-[73px] bg-[#0b0f14] z-40 lg:hidden transition-all duration-300 ease-in-out flex flex-col h-[calc(100vh-73px)] overflow-y-auto">
      <div class="px-6 py-8 flex flex-col gap-6 font-sans-ui text-sm font-semibold tracking-wider uppercase text-neutral-300">
        <NuxtLink class="hover:text-[#10b981] transition-colors py-2 border-b border-[rgba(43,55,52,0.65)] outline-none" to="/" @click="isMobileMenuOpen = false">Home</NuxtLink>
        <NuxtLink class="hover:text-[#10b981] transition-colors py-2 border-b border-[rgba(43,55,52,0.65)] outline-none" to="/about" @click="isMobileMenuOpen = false">About</NuxtLink>
        <NuxtLink class="hover:text-[#10b981] transition-colors py-2 border-b border-[rgba(43,55,52,0.65)] outline-none" to="/solutions" @click="isMobileMenuOpen = false">Solutions</NuxtLink>
        
        <!-- Mobile Products Accordion -->
        <div class="py-2 border-b border-[rgba(43,55,52,0.65)] flex flex-col">
          <button @click="mobileProductsOpen = !mobileProductsOpen" class="flex items-center justify-between hover:text-[#10b981] transition-colors focus:outline-none outline-none w-full text-left uppercase">
            <span>Products</span>
            <span class="material-symbols-outlined transition-transform duration-200" :class="mobileProductsOpen ? 'rotate-180' : ''">expand_more</span>
          </button>
          <div v-show="mobileProductsOpen" class="flex flex-col gap-3 mt-4 pl-4 normal-case text-neutral-400">
            <NuxtLink v-for="mod in modules" :key="mod.slug" class="hover:text-[#10b981] transition-colors outline-none" :to="`/products/${mod.slug}`" @click="isMobileMenuOpen = false">{{ mod.name }}</NuxtLink>
          </div>
        </div>
        
        <!-- Mobile Industries Accordion -->
        <div class="py-2 border-b border-[rgba(43,55,52,0.65)] flex flex-col">
          <button @click="mobileIndustriesOpen = !mobileIndustriesOpen" class="flex items-center justify-between hover:text-[#10b981] transition-colors focus:outline-none outline-none w-full text-left uppercase">
            <span>Industries</span>
            <span class="material-symbols-outlined transition-transform duration-200" :class="mobileIndustriesOpen ? 'rotate-180' : ''">expand_more</span>
          </button>
          <div v-show="mobileIndustriesOpen" class="flex flex-col gap-3 mt-4 pl-4 normal-case text-neutral-400">
            <NuxtLink v-for="ind in industries" :key="ind.slug" class="hover:text-[#10b981] transition-colors outline-none" :to="`/industries/${ind.slug}`" @click="isMobileMenuOpen = false">{{ ind.name }}</NuxtLink>
          </div>
        </div>

        <NuxtLink class="hover:text-[#10b981] transition-colors py-2 border-b border-[rgba(43,55,52,0.65)] outline-none" to="/pricing" @click="isMobileMenuOpen = false">Pricing</NuxtLink>
        <NuxtLink class="hover:text-[#10b981] transition-colors py-2 border-b border-[rgba(43,55,52,0.65)] outline-none" to="/experience-center" @click="isMobileMenuOpen = false">Demo</NuxtLink>
        <NuxtLink class="hover:text-[#10b981] transition-colors py-2 border-b border-[rgba(43,55,52,0.65)] outline-none" to="/resources" @click="isMobileMenuOpen = false">Resource</NuxtLink>
        <NuxtLink class="hover:text-[#10b981] transition-colors py-2 border-b border-[rgba(43,55,52,0.65)] outline-none" to="/careers" @click="isMobileMenuOpen = false">Careers</NuxtLink>
        <NuxtLink class="hover:text-[#10b981] transition-colors py-2 border-b border-[rgba(43,55,52,0.65)] outline-none" to="/contact" @click="isMobileMenuOpen = false">Contact Us</NuxtLink>
        
        <div class="mt-8 flex flex-col gap-4">
          <UiButtonCTA to="/experience-center" variant="primary" icon="arrow_forward" label="Book a Demo" customClass="" @click="isMobileMenuOpen = false" size="sm" />
        </div>
      </div>
    </div>

  </header>
</template>

<script setup lang="ts">
import { onMounted, onUnmounted, ref } from 'vue'
import { useModules } from '~/composables/useModules'
import { useIndustries } from '~/composables/useIndustries'
import { useSiteSettings } from '~/composables/useSiteSettings'

const { modules } = useModules()
const { industries } = useIndustries()
const { settings } = useSiteSettings()

const isMobileMenuOpen = ref(false)
const mobileProductsOpen = ref(false)
const mobileIndustriesOpen = ref(false)

const handleScroll = () => {
  const header = document.getElementById('site-header')
  if (header) {
    if (window.scrollY > 40) {
      header.classList.add('py-2.5', 'bg-[#0b0f14]/95')
      header.classList.remove('py-4', 'bg-[#0b0f14]/85')
    } else {
      header.classList.add('py-4', 'bg-[#0b0f14]/85')
      header.classList.remove('py-2.5', 'bg-[#0b0f14]/95')
    }
  }
}

onMounted(() => {
  window.addEventListener('scroll', handleScroll)
})

onUnmounted(() => {
  window.removeEventListener('scroll', handleScroll)
})
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700&display=swap');

.font-sans-ui {
  font-family:"Plus Jakarta Sans", sans-serif;
}

/* Mega menu transition */
.dropdown-menu {
  opacity: 0;
  visibility: hidden;
  transform: translateY(8px);
  transition: all 0.25s ease-out;
}
.group:hover .dropdown-menu {
  opacity: 1;
  visibility: visible;
  transform: translateY(0);
}

</style>
