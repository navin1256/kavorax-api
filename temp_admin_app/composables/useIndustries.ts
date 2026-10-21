import { useStorage } from '@vueuse/core'

export const initialIndustries = [
  { id: 'restaurants', slug: 'restaurants', name: 'Restaurants & Fine Dining', headline: 'Elevate the dining experience.', subline: 'Seamlessly manage orders and staff.', image: '/images/fine-dining.jpg' },
  { id: 'cafes', slug: 'cafes', name: 'Cafes & Bakeries', headline: 'Brew success.', subline: 'Fast ordering for quick turnarounds.', image: '/images/cafes.jpg' },
  { id: 'retail', slug: 'retail', name: 'Retail Stores', headline: 'Modernize your retail shop.', subline: 'Track inventory and sales easily.', image: '/images/retail.jpg' },
  { id: 'hospitality', slug: 'hospitality', name: 'Hotels & Resorts', headline: 'Deliver 5-star service.', subline: 'Integrated management for hospitality.', image: '/images/hotel-concierge.jpg' },
  { id: 'salons', slug: 'salons', name: 'Salons & Spas', headline: 'Pamper your clients.', subline: 'Appointments and POS combined.', image: '/images/salon.jpg' },
  { id: 'convenience', slug: 'convenience', name: 'Convenience Stores', headline: 'Fast moving, fast billing.', subline: 'Handle high volumes with ease.', image: '/images/minimart.jpg' },
  { id: 'ecommerce', slug: 'ecommerce', name: 'E-commerce', headline: 'Sell everywhere.', subline: 'Omnichannel inventory in sync.', image: '/images/ecommerce.jpg' },
  { id: 'sme', slug: 'sme', name: 'Growing SMEs', headline: 'Scale without limits.', subline: 'Enterprise tools for small businesses.', image: '/images/growing-smes.jpg' },
]

export function useIndustries() {
  const industries = useStorage('kavorax-industries', initialIndustries)
  return { industries }
}
