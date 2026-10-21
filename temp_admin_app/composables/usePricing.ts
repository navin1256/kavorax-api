import { useStorage } from '@vueuse/core'

export const initialPricing = [
  { id: 'starter', name: 'Starter', price: '$49', isPopular: false, features: ['POS & Billing module', 'Basic Inventory', 'Up to 3 users', 'Standard email support'] },
  { id: 'pro', name: 'Professional', price: '$99', isPopular: true, features: ['Everything in Starter', 'Advanced Inventory tracking', 'CRM & Loyalty', 'Unlimited users', 'Priority phone support'] },
  { id: 'enterprise', name: 'Enterprise', price: 'Custom', isPopular: false, features: ['Everything in Professional', 'Custom integrations', 'Dedicated account manager', 'SLA guarantees'] },
]

export function usePricing() {
  const pricing = useStorage('kavorax-pricing', initialPricing)
  return { pricing }
}
