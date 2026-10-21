<template>
  <div class="space-y-[24px]">
    <!-- SECTION 1: PAGE HEADER -->
    <section class="flex flex-col sm:flex-row sm:items-center justify-between gap-4">
      <div>
        <h1 class="text-[28px] font-bold text-on-surface tracking-tight" style="font-family: 'Plus Jakarta Sans', sans-serif;">Resources / Insights</h1>
        <p class="text-[14px] text-on-surface-variant mt-1">Manage the articles shown on your public Insights page.</p>
      </div>
      <div>
        <button class="inline-flex items-center gap-2 px-5 py-2.5 rounded-full bg-gradient-to-r from-[#10b981] to-[#059669] text-on-surface font-semibold hover:shadow-[0_0_16px_rgba(16,185,129,0.35)] active:scale-95 transition-all text-[16px]">
          <span class="material-symbols-outlined text-[20px]" data-icon="add">add</span>
          <span>New Article</span>
        </button>
      </div>
    </section>

    <!-- SECTION 2: FILTER BAR -->
    <section class="bg-surface-container-low rounded-xl border border-outline-variant/65 p-4 flex flex-wrap items-center justify-between gap-4">
      <div class="flex flex-wrap items-center gap-4 flex-1">
        <!-- Search input -->
        <div class="relative min-w-[280px] max-w-sm flex-1">
          <span class="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-on-surface-variant text-[18px]" data-icon="search">search</span>
          <input class="w-full h-10 pl-9 pr-4 bg-surface-container-lowest border border-outline-variant/65 rounded-lg text-[13px] text-on-surface placeholder:text-on-surface-variant/60 focus:outline-none focus:border-primary focus:ring-1 focus:ring-primary transition-all" placeholder="Search articles..." type="text"/>
        </div>
        <!-- Status Dropdown Pill -->
        <div class="relative">
          <select class="h-10 pl-3.5 pr-8 bg-surface-container-lowest border border-outline-variant/65 rounded-full text-[13px] text-on-surface focus:outline-none focus:border-primary focus:ring-1 focus:ring-primary appearance-none cursor-pointer transition-all">
            <option value="all">All Statuses</option>
            <option value="draft">Draft</option>
            <option value="published">Published</option>
          </select>
          <span class="material-symbols-outlined absolute right-2.5 top-1/2 -translate-y-1/2 text-on-surface-variant text-[18px] pointer-events-none" data-icon="expand_more">expand_more</span>
        </div>
        <!-- Category Dropdown Pill -->
        <div class="relative">
          <select class="h-10 pl-3.5 pr-8 bg-surface-container-lowest border border-outline-variant/65 rounded-full text-[13px] text-on-surface focus:outline-none focus:border-primary focus:ring-1 focus:ring-primary appearance-none cursor-pointer transition-all">
            <option value="all">All Categories</option>
            <option value="restaurants">Restaurants</option>
            <option value="retail">Retail</option>
            <option value="salons">Salons</option>
            <option value="inventory">Inventory</option>
            <option value="billing">Billing &amp; GST</option>
            <option value="growth">Growth</option>
            <option value="updates">Product Updates</option>
          </select>
          <span class="material-symbols-outlined absolute right-2.5 top-1/2 -translate-y-1/2 text-on-surface-variant text-[18px] pointer-events-none" data-icon="expand_more">expand_more</span>
        </div>
      </div>
      <!-- Clear filters button -->
      <button class="text-[13px] text-on-surface-variant hover:text-on-surface underline underline-offset-4 transition-colors">
        Clear filters
      </button>
    </section>

    <!-- SECTION 3: ARTICLES TABLE -->
    <section class="bg-surface-container-low rounded-2xl border border-outline-variant/65 overflow-hidden shadow-2xl shadow-black/40">
      <div class="overflow-x-auto">
        <table class="w-full text-left border-collapse">
          <thead>
            <tr class="bg-surface border-b border-outline-variant/65">
              <th class="py-3 px-4 text-[11px] font-semibold text-on-surface-variant uppercase tracking-wider w-16">Thumbnail</th>
              <th class="py-3 px-6 text-[11px] font-semibold text-on-surface-variant uppercase tracking-wider min-w-[340px]">Title &amp; Excerpt</th>
              <th class="py-3 px-4 text-[11px] font-semibold text-on-surface-variant uppercase tracking-wider">Category</th>
              <th class="py-3 px-4 text-[11px] font-semibold text-on-surface-variant uppercase tracking-wider">Status</th>
              <th class="py-3 px-4 text-[11px] font-semibold text-on-surface-variant uppercase tracking-wider">Date</th>
              <th class="py-3 px-4 text-[11px] font-semibold text-on-surface-variant uppercase tracking-wider">Author</th>
              <th class="py-3 px-6 text-[11px] font-semibold text-on-surface-variant uppercase tracking-wider text-right">Actions</th>
            </tr>
          </thead>
          <tbody class="divide-y divide-outline-variant/40">
            <tr v-if="articles.length === 0">
              <td colspan="7" class="py-8 px-4 text-center text-[13px] text-outline italic">No content loaded.</td>
            </tr>
            <tr v-for="(article, idx) in articles" :key="idx" :class="['transition-colors group', idx % 2 === 0 ? 'bg-surface-container-low hover:bg-surface-container' : 'bg-surface-container-lowest hover:bg-surface-container']">
              <td class="py-3.5 px-4">
                <img :src="article.image" class="w-12 h-12 rounded-lg object-cover border border-outline-variant/65" :alt="article.title" />
              </td>
              <td class="py-3.5 px-6">
                <div class="text-[14px] font-semibold text-on-surface group-hover:text-primary transition-colors">{{ article.title }}</div>
                <div class="text-[13px] text-on-surface-variant truncate max-w-md">{{ article.excerpt }}</div>
              </td>
              <td class="py-3.5 px-4">
                <span class="px-2.5 py-1 rounded-full text-[11px] font-semibold bg-surface-container-high border border-outline-variant/65 text-on-surface">{{ article.category }}</span>
              </td>
              <td class="py-3.5 px-4">
                <span v-if="article.status === 'Published'" class="inline-flex items-center gap-1.5 px-2.5 py-0.5 rounded-full text-[11px] font-semibold bg-primary-container/10 border border-primary-container/40 text-primary">
                  <span class="w-1.5 h-1.5 rounded-full bg-primary-container"></span>
                  Published
                </span>
                <span v-else class="inline-flex items-center gap-1.5 px-2.5 py-0.5 rounded-full text-[11px] font-semibold bg-surface-container-high border border-outline-variant/65 text-on-surface-variant">
                  <span class="w-1.5 h-1.5 rounded-full bg-outline"></span>
                  Draft
                </span>
              </td>
              <td class="py-3.5 px-4 text-[13px] text-on-surface-variant">{{ article.date }}</td>
              <td class="py-3.5 px-4">
                <div class="flex items-center gap-2">
                  <div class="w-7 h-7 rounded-full bg-surface-variant border border-outline-variant/65 flex items-center justify-center text-xs font-semibold text-primary">{{ article.initials }}</div>
                  <span class="text-[13px] text-on-surface">{{ article.author }}</span>
                </div>
              </td>
              <td class="py-3.5 px-6 text-right">
                <div class="inline-flex items-center gap-2">
                  <button class="p-1.5 text-on-surface-variant hover:text-primary transition-colors rounded" title="Edit Article">
                    <span class="material-symbols-outlined text-[18px]" data-icon="edit">edit</span>
                  </button>
                  <button class="p-1.5 text-on-surface-variant hover:text-error transition-colors rounded" title="Delete Article">
                    <span class="material-symbols-outlined text-[18px]" data-icon="delete">delete</span>
                  </button>
                  <button class="p-1.5 text-on-surface-variant hover:text-on-surface transition-colors rounded" title="Preview Article">
                    <span class="material-symbols-outlined text-[18px]" data-icon="visibility">visibility</span>
                  </button>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <!-- SECTION 4: PAGINATION FOOTER -->
      <div class="p-4 bg-surface border-t border-outline-variant/65 flex flex-col sm:flex-row items-center justify-between gap-4">
        <div class="text-[13px] text-on-surface-variant">
          Showing 0-0 of 0 articles
        </div>
        <div class="flex items-center gap-1.5">
          <button disabled class="w-8 h-8 rounded-lg flex items-center justify-center text-on-surface-variant hover:text-on-surface hover:bg-surface-container disabled:opacity-40 transition-colors" title="Previous Page">
            <span class="material-symbols-outlined text-[18px]" data-icon="chevron_left">chevron_left</span>
          </button>
          <button class="w-8 h-8 rounded-full bg-gradient-to-br from-[#10b981] to-[#059669] text-on-surface font-semibold text-[16px] flex items-center justify-center shadow-[0_0_12px_rgba(16,185,129,0.35)]">1</button>
          <button class="w-8 h-8 rounded-full bg-surface-container-high text-on-surface-variant hover:text-on-surface hover:bg-surface-container border border-outline-variant/65 font-semibold text-[16px] flex items-center justify-center transition-colors">2</button>
          <button class="w-8 h-8 rounded-full bg-surface-container-high text-on-surface-variant hover:text-on-surface hover:bg-surface-container border border-outline-variant/65 font-semibold text-[16px] flex items-center justify-center transition-colors">3</button>
          <button class="w-8 h-8 rounded-full bg-surface-container-high text-on-surface-variant hover:text-on-surface hover:bg-surface-container border border-outline-variant/65 font-semibold text-[16px] flex items-center justify-center transition-colors">4</button>
          <button class="w-8 h-8 rounded-lg flex items-center justify-center text-on-surface-variant hover:text-on-surface hover:bg-surface-container transition-colors" title="Next Page">
            <span class="material-symbols-outlined text-[18px]" data-icon="chevron_right">chevron_right</span>
          </button>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'

definePageMeta({ layout: 'admin', middleware: ['admin-auth'] })

const articles = ref<any[]>([])
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&family=Plus+Jakarta+Sans:wght@600;700;800&display=swap');

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
  font-variation-settings: 'wght' 300, 'GRAD' 0, 'opsz' 20;
  vertical-align: middle;
}
</style>
