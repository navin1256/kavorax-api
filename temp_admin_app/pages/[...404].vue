<template>
  <div class="relative flex min-h-[70vh] flex-col bg-background overflow-x-hidden">
    <main class="flex-grow flex items-center justify-center w-full">
      <section class="px-space-md py-space-xl max-w-7xl mx-auto text-center w-full">
        <div class="bg-background-secondary border border-outline-variant p-space-xl rounded-xl max-w-3xl mx-auto flex flex-col items-center">
          <h2 class="text-headline-lg font-headline-lg text-transparent bg-clip-text bg-gradient-to-r from-text-primary to-primary mb-space-sm text-7xl md:text-9xl">404</h2>
          <p class="text-body-lg text-text-secondary mb-space-md">This page took a wrong turn into the digital void.</p>
          <div class="flex flex-col sm:flex-row gap-space-sm">
            <UiButtonCTA size="sm" to="/" variant="primary" icon="arrow_back" label="Back to Home" />
            <UiButtonCTA size="lg" to="/experience-center" variant="secondary" icon="arrow_forward" label="Book a Demo" />
          </div>
        </div>
      </section>
    </main>
  </div>
</template>

<script setup lang="ts">
// 404 Catch all page
</script>
