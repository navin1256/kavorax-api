<template>
  <footer class="relative w-full bg-[#080c10] border-t border-[rgba(43,55,52,0.65)] py-16 font-sans-ui text-xs">
    <div class="max-w-7xl mx-auto px-6 sm:px-12">
      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-5 gap-10 mb-14">
        <!-- Left: Brand Info -->
        <div class="lg:col-span-2">
          <img :alt="settings.siteName" class="h-10 w-auto object-contain mb-4" src="/images/logo.jpg"/>
          <p class="text-neutral-400 font-serif-custom text-lg italic mb-6">
            {{ settings.tagline }}
          </p>
          <div class="flex items-center gap-3">
            <UiButtonCTA :to="settings.social.linkedin" variant="secondary" icon="arrow_forward" label="in" />
            <UiButtonCTA :to="settings.social.instagram" variant="secondary" icon="arrow_forward" label="ig" />
            <UiButtonCTA :to="settings.social.facebook" variant="secondary" icon="arrow_forward" label="fb" />
            <UiButtonCTA to="https://youtube.com/kavorax" variant="secondary" icon="arrow_forward" label="yt" />
            <UiButtonCTA :to="`https://wa.me/${settings.social.whatsapp}`" variant="secondary" icon="arrow_forward" label="" />
          </div>
          <div class="mt-8 text-neutral-500 space-y-1">
            <p class="font-semibold text-neutral-400">{{ settings.siteName }} Global Private Limited</p>
            <p>Chennai &amp; Bengaluru, India</p>
          </div>
        </div>
        
        <!-- Col 2: Solutions -->
        <div>
          <p class="text-white uppercase font-bold tracking-wider mb-4">Solutions</p>
          <ul class="space-y-2.5 text-neutral-400">
            <li v-for="mod in modules" :key="mod.slug">
              <NuxtLink class="hover:text-[#10b981] transition-colors" :to="`/products/${mod.slug}`">{{ mod.name }}</NuxtLink>
            </li>
          </ul>
        </div>
        
        <!-- Col 3: Industries -->
        <div>
          <p class="text-white uppercase font-bold tracking-wider mb-4">Industries</p>
          <ul class="space-y-2.5 text-neutral-400">
            <li v-for="ind in industries" :key="ind.slug">
              <NuxtLink class="hover:text-[#10b981] transition-colors" :to="`/industries/${ind.slug}`">{{ ind.name }}</NuxtLink>
            </li>
          </ul>
        </div>
        
        <!-- Col 4: Company & Contact -->
        <div>
          <p class="text-white uppercase font-bold tracking-wider mb-4">Company</p>
          <ul class="space-y-2.5 text-neutral-400 mb-6">
            <li><NuxtLink class="hover:text-[#10b981] transition-colors" to="/about">About KavoraX</NuxtLink></li>
            <li><NuxtLink class="hover:text-[#10b981] transition-colors" to="/careers">Careers</NuxtLink></li>
            <li><NuxtLink class="hover:text-[#10b981] transition-colors" to="/contact">Contact Us</NuxtLink></li>
            <li><NuxtLink class="hover:text-[#10b981] transition-colors" to="/experience-center">Experience Center / Demo</NuxtLink></li>
            <li><NuxtLink class="hover:text-[#10b981] transition-colors" to="/resources">Insights &amp; Guides</NuxtLink></li>
          </ul>
          <p class="text-white uppercase font-bold tracking-wider mb-2">Direct Contact</p>
          <div class="space-y-1.5 text-neutral-400">
            <p><a class="hover:text-[#10b981] transition-colors" :href="`mailto:${settings.email}`">{{ settings.email }}</a></p>
            <p><a class="hover:text-[#10b981] transition-colors" :href="`tel:${settings.phone}`">{{ settings.phone }}</a></p>
          </div>
        </div>
      </div>
      
      <!-- Bottom Bar -->
      <div class="border-t border-[rgba(43,55,52,0.65)]/60 pt-8 flex flex-col sm:flex-row items-center justify-between gap-4 text-neutral-500">
        <p>&copy; 2026 {{ settings.siteName }} Global Private Limited. All rights reserved.</p>
        <div class="flex items-center gap-6">
          <NuxtLink class="hover:text-neutral-300 transition-colors" to="/legal">Privacy Policy</NuxtLink>
          <NuxtLink class="hover:text-neutral-300 transition-colors" to="/legal">Terms of Use</NuxtLink>
          <NuxtLink class="hover:text-neutral-300 transition-colors" to="/legal">Cookie Settings</NuxtLink>
        </div>
      </div>
    </div>
  </footer>
</template>

<script setup lang="ts">
import { useModules } from '~/composables/useModules'
import { useIndustries } from '~/composables/useIndustries'
import { useSiteSettings } from '~/composables/useSiteSettings'

const { modules } = useModules()
const { industries } = useIndustries()
const { settings } = useSiteSettings()
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Cormorant+Garamond:ital,wght@0,400;0,500;0,600;0,700;1,400;1,600&display=swap');

.font-sans-ui {
  font-family: "Plus Jakarta Sans", sans-serif;
}
.font-serif-custom {
  font-family: "Cormorant Garamond", Georgia, serif;
}
</style>
