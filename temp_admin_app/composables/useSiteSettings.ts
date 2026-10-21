import { useStorage } from '@vueuse/core'

export const initialSettings = {
  siteName: 'KavoraX',
  tagline: 'Empowering Businesses Digitally',
  email: 'hello@kavorax.com',
  phone: '+91 96295 79358',
  social: {
    linkedin: 'https://linkedin.com/company/kavorax',
    instagram: 'https://instagram.com/kavorax',
    facebook: 'https://facebook.com/kavorax',
    whatsapp: '919629579358'
  },
  seo: {
    title: 'KavoraX | Software Ecosystem',
    description: 'Transform your retail, hospitality, and service business with the ultimate software ecosystem.'
  }
}

export function useSiteSettings() {
  const settings = useStorage('kavorax-settings', initialSettings)
  return { settings }
}
