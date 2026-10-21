<template>
  <div class="animate-in fade-in zoom-in-95 duration-200 min-h-screen">
    
    <!-- MAIN WORKSPACE -->
    <main class="flex flex-col gap-6 max-w-[1600px] w-full mx-auto">
      <!-- SECTION 1: PAGE HEADER -->
      <section class="flex flex-col md:flex-row md:items-center justify-between gap-4 pb-2 border-b border-outline-variant/30 mt-2">
        <div>
          <h1 class="text-[36px] font-bold text-white tracking-tight" style="font-family: 'Plus Jakarta Sans', sans-serif;">Team</h1>
          <p class="text-[14px] text-[#999999] mt-1">Manage the leadership and team profiles shown on your public About page.</p>
        </div>
        <div class="flex items-center gap-3">

          <button @click="isInviteModalOpen = true" class="px-5 py-2.5 rounded-full bg-gradient-to-r from-[#10b981] to-[#059669] text-white text-[13px] font-semibold flex items-center gap-2 shadow-[0_0_20px_rgba(16,185,129,0.35)] hover:shadow-[0_0_26px_rgba(16,185,129,0.5)] active:scale-[0.98] transition-all">
            <span class="material-symbols-outlined text-[18px]">person_add</span>
            <span>+ Invite Team Member</span>
          </button>
        </div>
      </section>

      <!-- SECTION 2: FILTER & SEARCH BAR -->
      <section class="w-full bg-[#161b20] rounded-xl border border-outline-variant/40 p-3.5 flex flex-wrap items-center justify-between gap-4 shadow-sm">
        <!-- Quick Search -->
        <div class="relative flex-1 min-w-[280px]">
          <span class="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-[#999999] text-[16px]">search</span>
          <input class="w-full bg-[#0b0f14] text-white placeholder:text-[#999999]/70 text-[13px] pl-9 pr-4 py-2 rounded-lg border border-outline-variant/50 focus:outline-none focus:border-primary focus:ring-1 focus:ring-primary transition-all" placeholder="Search team members by name, email, or role..." type="text"/>
        </div>
        <!-- Filter Dropdowns -->
        <div class="flex items-center flex-wrap gap-2.5">
          <!-- Role Filter -->
          <div class="relative">
            <select class="appearance-none bg-[#0b0f14] text-white text-[12px] font-medium pl-3.5 pr-8 py-2 rounded-lg border border-outline-variant/50 focus:outline-none focus:border-primary cursor-pointer hover:border-outline transition-all">
              <option value="all">Role: All Members (0)</option>
              <option value="leadership">Leadership Only</option>
              <option value="staff">Staff Personnel</option>
              <option value="pending">Pending Invites</option>
            </select>
            <span class="material-symbols-outlined absolute right-2 top-1/2 -translate-y-1/2 text-on-surface-variant pointer-events-none text-[16px]">expand_more</span>
          </div>
          <!-- Status Filter -->
          <div class="relative">
            <select class="appearance-none bg-[#0b0f14] text-white text-[12px] font-medium pl-3.5 pr-8 py-2 rounded-lg border border-outline-variant/50 focus:outline-none focus:border-primary cursor-pointer hover:border-outline transition-all">
              <option value="all">Status: Any State</option>
              <option value="active">Active Now</option>
              <option value="away">Away</option>
              <option value="pending">Pending</option>
            </select>
            <span class="material-symbols-outlined absolute right-2 top-1/2 -translate-y-1/2 text-on-surface-variant pointer-events-none text-[16px]">expand_more</span>
          </div>
          <!-- Department Filter -->
          <div class="relative">
            <select class="appearance-none bg-[#0b0f14] text-white text-[12px] font-medium pl-3.5 pr-8 py-2 rounded-lg border border-outline-variant/50 focus:outline-none focus:border-primary cursor-pointer hover:border-outline transition-all">
              <option value="all">Department: All Departments</option>
              <option value="exec">Executive Governance</option>
              <option value="finance">Finance &amp; Operations</option>
              <option value="eng">Distributed Infrastructure</option>
              <option value="growth">Enterprise Strategy</option>
            </select>
            <span class="material-symbols-outlined absolute right-2 top-1/2 -translate-y-1/2 text-on-surface-variant pointer-events-none text-[16px]">expand_more</span>
          </div>
          <!-- Reset Filter Button -->
          <button class="p-2 text-on-surface-variant hover:text-white rounded-lg hover:bg-surface-container border border-outline-variant/40 transition-colors" title="Reset Filters">
            <span class="material-symbols-outlined text-[16px]">filter_alt_off</span>
          </button>
        </div>
      </section>

      <!-- SECTION 3: TEAM GRID -->
      <section>
        <div class="flex items-center justify-between mb-4 mt-2">
          <div class="flex items-center gap-2">
            <span class="text-[11px] font-semibold tracking-wider text-[#999999] uppercase">Active Personnel</span>
            <span class="px-2 py-0.5 text-[10px] font-bold bg-[#1c2228] text-primary border border-outline-variant/50 rounded-full">{{ activeMembers.length }} Featured</span>
          </div>
          <span class="text-[11px] font-semibold text-on-surface-variant">Displaying public leadership roster</span>
        </div>
        
        <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-5">
          <div v-if="activeMembers.length === 0" class="col-span-1 sm:col-span-2 lg:col-span-4 py-12 text-center text-[13px] text-outline italic bg-[#161b20] border border-outline-variant/50 rounded-2xl">
            No active team members configured.
          </div>
          <div v-for="member in activeMembers" :key="member.email" class="bg-[#161b20] border border-outline-variant/50 rounded-2xl p-5 hover:border-primary/50 transition-all flex flex-col items-center text-center relative group shadow-[0_4px_20px_rgba(0,0,0,0.3)]">
            <!-- Department Badge -->
            <div class="absolute top-4 left-4">
              <span :class="member.badgeClass" class="inline-flex items-center px-2 py-0.5 rounded-full text-[10px] font-semibold border">{{ member.department }}</span>
            </div>
            
            <!-- Avatar -->
            <div class="relative mt-2 mb-3">
              <div :class="member.avatarRingClass" class="w-20 h-20 rounded-full p-0.5 bg-gradient-to-tr overflow-hidden">
                <img :src="member.avatar" class="w-full h-full rounded-full object-cover bg-surface-container border border-[#161b20]" alt="Profile" />
              </div>
              <span v-if="member.isActive" class="absolute bottom-0 right-1 w-4 h-4 bg-primary rounded-full border-2 border-[#161b20] shadow-[0_0_8px_#10b981]" title="Active now"></span>
              <span v-else class="absolute bottom-0 right-1 w-4 h-4 bg-outline-variant/70 rounded-full border-2 border-[#161b20]" title="Offline"></span>
            </div>
            
            <h3 class="text-[16px] font-bold text-white tracking-tight" style="font-family: 'Plus Jakarta Sans', sans-serif;">{{ member.name }}</h3>
            <p class="text-[12px] font-medium text-primary mt-0.5" :class="member.department === 'Executive' ? 'text-primary' : 'text-[#999999]'">{{ member.role }}</p>
            <p class="text-[12px] font-medium text-on-surface-variant/70 mt-1 truncate max-w-[210px]">{{ member.email }}</p>
            
            <div class="w-full h-px bg-outline-variant/40 my-3.5"></div>
            
            <!-- Last Active -->
            <div class="flex items-center gap-1.5 text-on-surface-variant text-[11px] font-semibold">
              <span class="material-symbols-outlined text-[12px]" :class="member.isActive ? 'text-primary' : 'text-on-surface-variant'">schedule</span>
              <span>{{ member.lastActive }}</span>
            </div>
            
            <!-- Hover Action Pills -->
            <div class="mt-4 flex items-center gap-2 w-full pt-1">
              <button @click="openEditModal(member)" class="flex-1 py-1.5 px-3 rounded-full bg-surface-container border border-outline-variant/60 text-[12px] font-medium text-on-surface hover:text-primary hover:border-primary transition-all flex items-center justify-center gap-1.5">
                <span class="material-symbols-outlined text-[12px]">edit</span>
                <span>Edit</span>
              </button>
              <button @click="confirmDelete(member)" class="p-1.5 rounded-full bg-surface-container border border-outline-variant/60 text-on-surface-variant hover:text-error hover:border-error/50 transition-all" title="Remove Member">
                <span class="material-symbols-outlined text-[14px]">delete</span>
              </button>
            </div>
          </div>
        </div>
      </section>

      <!-- SECTION 4: PENDING INVITES -->
      <section class="pt-4">
        <div class="flex items-center justify-between mb-3">
          <div class="flex items-center gap-2">
            <h2 class="text-[11px] font-semibold tracking-wider text-[#999999] uppercase">Pending Invites</h2>
            <span class="px-2 py-0.5 text-[10px] font-bold bg-amber-500/10 text-amber-400 border border-amber-500/20 rounded-full">{{ pendingInvites.length }} Awaiting Response</span>
          </div>
          <button class="text-[11px] font-semibold text-primary hover:underline flex items-center gap-1">
            <span>Invite History Logs</span>
            <span class="material-symbols-outlined text-[12px]">arrow_forward</span>
          </button>
        </div>
        
        <div class="grid grid-cols-1 md:grid-cols-2 gap-5">
          <div v-if="pendingInvites.length === 0" class="col-span-1 md:col-span-2 py-8 text-center text-[13px] text-outline italic bg-[#161b20] border border-outline-variant/50 rounded-2xl">
            No pending invitations.
          </div>
          <div v-for="invite in pendingInvites" :key="invite.email" class="bg-[#161b20] border border-outline-variant/50 rounded-2xl p-5 hover:border-amber-500/40 transition-all flex items-center justify-between gap-4 shadow-sm">
            <div class="flex items-center gap-4">
              <!-- Avatar -->
              <div class="relative">
                <div class="w-14 h-14 rounded-full bg-surface-container border border-outline-variant/80 flex items-center justify-center text-amber-400 text-[18px] font-bold" style="font-family: 'Plus Jakarta Sans', sans-serif;">
                  {{ invite.initials }}
                </div>
                <span class="absolute -top-1 -right-1 px-1.5 py-0.5 bg-amber-500/20 border border-amber-500/60 rounded-full text-[9px] font-medium text-amber-400">
                  {{ invite.status }}
                </span>
              </div>
              <div>
                <h4 class="text-[16px] font-bold text-white" style="font-family: 'Plus Jakarta Sans', sans-serif;">{{ invite.name }}</h4>
                <p class="text-[12px] font-medium text-on-surface-variant">{{ invite.role }}</p>
                <p class="text-[12px] font-medium text-[#999999] mt-0.5">{{ invite.email }}</p>
                <div class="flex items-center gap-1 text-[11px] font-semibold text-on-surface-variant/70 mt-1">
                  <span class="material-symbols-outlined text-[12px]">{{ invite.icon }}</span>
                  <span>{{ invite.meta }}</span>
                </div>
              </div>
            </div>
            
            <div class="flex flex-col items-end gap-2.5">
              <button class="flex items-center gap-1.5 px-3 py-1.5 rounded-full bg-[#10b981]/10 text-[#10b981] border border-[#10b981]/30 hover:bg-[#10b981]/20 transition-all text-[11px] font-semibold">
                <span class="material-symbols-outlined text-[12px]">sync</span>
                <span>Resend Invite</span>
              </button>
              <button @click="pendingInvites = pendingInvites.filter(i => i.email !== invite.email)" class="p-1.5 text-on-surface-variant hover:text-error rounded-lg hover:bg-surface-container transition-colors" title="Revoke invitation">
                <span class="material-symbols-outlined text-[14px]">delete</span>
              </button>
            </div>
          </div>
        </div>
      </section>

      <!-- SECTION 5: ROLES & PERMISSIONS MATRIX -->
      <section class="bg-[#161b20] border border-outline-variant/65 rounded-2xl p-6 mt-6 shadow-lg mb-8">
        <div class="flex items-center justify-between pb-4 border-b border-outline-variant/45 mb-4">
          <div>
            <h2 class="text-[18px] font-semibold text-white" style="font-family: 'Plus Jakarta Sans', sans-serif;">System Role Definitions &amp; Access Tiers</h2>
            <p class="text-[14px] text-[#999999]">Cryptographic RBAC security matrix mapping governance privileges across instances.</p>
          </div>
          <span class="text-[12px] font-medium text-primary px-3 py-1 bg-surface-container rounded-full border border-primary/30">
            RBAC v4.2 Enforced
          </span>
        </div>
        <div class="overflow-x-auto custom-scroll">
          <table class="w-full text-left border-collapse">
            <thead>
              <tr class="bg-[#101419] border-b border-outline-variant/65 text-[#999999] text-[11px] font-semibold uppercase tracking-wider">
                <th class="py-3 px-4">Role Tier</th>
                <th class="py-3 px-4">Members</th>
                <th class="py-3 px-4">Global Orchestration</th>
                <th class="py-3 px-4">Billing &amp; Ledger</th>
                <th class="py-3 px-4">Content Publishing</th>
                <th class="py-3 px-4 text-right">Action</th>
              </tr>
            </thead>
            <tbody class="divide-y divide-outline-variant/40 text-[14px]">
              <tr class="hover:bg-[#1c2228] transition-colors">
                <td class="py-3.5 px-4 font-semibold text-white flex items-center gap-2">
                  <span class="w-2 h-2 rounded-full bg-primary"></span>
                  Founder &amp; CEO (Tier 0)
                </td>
                <td class="py-3.5 px-4 text-on-surface-variant">1 Account</td>
                <td class="py-3.5 px-4"><span class="text-primary text-[12px] font-medium">Unrestricted Root</span></td>
                <td class="py-3.5 px-4"><span class="text-primary text-[12px] font-medium">Authorized</span></td>
                <td class="py-3.5 px-4"><span class="text-primary text-[12px] font-medium">Direct Push</span></td>
                <td class="py-3.5 px-4 text-right">
                  <button class="text-[12px] font-medium text-primary hover:underline">Config Policy</button>
                </td>
              </tr>
              <tr class="hover:bg-[#1c2228] transition-colors">
                <td class="py-3.5 px-4 font-semibold text-white flex items-center gap-2">
                  <span class="w-2 h-2 rounded-full bg-secondary"></span>
                  Director / VP Level (Tier 1)
                </td>
                <td class="py-3.5 px-4 text-on-surface-variant">4 Accounts</td>
                <td class="py-3.5 px-4"><span class="text-primary text-[12px] font-medium">Cluster Scope</span></td>
                <td class="py-3.5 px-4"><span class="text-primary text-[12px] font-medium">Auditor Access</span></td>
                <td class="py-3.5 px-4"><span class="text-primary text-[12px] font-medium">Reviewer &amp; Sign</span></td>
                <td class="py-3.5 px-4 text-right">
                  <button class="text-[12px] font-medium text-primary hover:underline">Config Policy</button>
                </td>
              </tr>
              <tr class="hover:bg-[#1c2228] transition-colors">
                <td class="py-3.5 px-4 font-semibold text-white flex items-center gap-2">
                  <span class="w-2 h-2 rounded-full bg-on-surface-variant"></span>
                  Operations Staff (Tier 2)
                </td>
                <td class="py-3.5 px-4 text-on-surface-variant">11 Accounts</td>
                <td class="py-3.5 px-4"><span class="text-on-surface-variant text-[12px] font-medium">Read Only</span></td>
                <td class="py-3.5 px-4"><span class="text-error text-[12px] font-medium">Restricted</span></td>
                <td class="py-3.5 px-4"><span class="text-primary text-[12px] font-medium">Draft Creator</span></td>
                <td class="py-3.5 px-4 text-right">
                  <button class="text-[12px] font-medium text-primary hover:underline">Config Policy</button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </section>
    </main>

    <!-- MODAL 1: INVITE -->
    <transition name="fade">
      <div v-if="isInviteModalOpen" class="fixed inset-0 z-[60] flex items-center justify-center p-4" style="background-color: rgba(0,0,0,0.8); backdrop-filter: blur(4px);" @click.self="isInviteModalOpen = false">
        <div class="relative w-full max-w-lg bg-[#161b20] border border-primary/40 rounded-2xl p-6 shadow-[0_8px_32px_rgba(0,0,0,0.8),0_0_24px_rgba(16,185,129,0.25)] space-y-5 animate-in zoom-in-95">
          <!-- Header -->
          <div class="flex items-center justify-between border-b border-outline-variant/40 pb-4">
            <div class="flex items-center gap-3">
              <div class="w-9 h-9 rounded-xl bg-primary/10 border border-primary/30 flex items-center justify-center text-primary">
                <span class="material-symbols-outlined text-[18px]">person_add</span>
              </div>
              <div>
                <h3 class="text-[18px] font-semibold text-white" style="font-family: 'Plus Jakarta Sans', sans-serif;">Invite Team Member</h3>
                <p class="text-[12px] font-medium text-[#999999]">Send credential link to new operations staff</p>
              </div>
            </div>
            <button class="text-on-surface-variant hover:text-white p-1 rounded-lg hover:bg-surface-container transition-colors" @click="isInviteModalOpen = false">
              <span class="material-symbols-outlined text-[20px]">close</span>
            </button>
          </div>
          
          <form @submit.prevent="submitInvite" class="space-y-4">
            <div>
              <label class="block text-[12px] font-medium text-on-surface-variant mb-1.5">Full Legal Name</label>
              <input v-model="inviteForm.name" class="w-full bg-[#101419] text-white placeholder:text-[#999999]/60 text-[14px] px-3.5 py-2.5 rounded-xl border border-outline-variant/65 focus:outline-none focus:border-primary focus:ring-1 focus:ring-primary transition-all" placeholder="e.g. David Sterling" required type="text"/>
            </div>
            <div>
              <label class="block text-[12px] font-medium text-on-surface-variant mb-1.5">Enterprise Email Address</label>
              <input v-model="inviteForm.email" class="w-full bg-[#101419] text-white placeholder:text-[#999999]/60 text-[14px] px-3.5 py-2.5 rounded-xl border border-outline-variant/65 focus:outline-none focus:border-primary focus:ring-1 focus:ring-primary transition-all" placeholder="d.sterling@kavorax.com" required type="email"/>
            </div>
            <div class="grid grid-cols-2 gap-4">
              <div>
                <label class="block text-[12px] font-medium text-on-surface-variant mb-1.5">Assigned Role</label>
                <select class="w-full bg-[#101419] text-white text-[14px] px-3.5 py-2.5 rounded-xl border border-outline-variant/65 focus:outline-none focus:border-primary appearance-none cursor-pointer">
                  <option>Staff Personnel</option>
                  <option>Director / Lead</option>
                  <option>VP of Infrastructure</option>
                  <option>Founder & CEO</option>
                </select>
              </div>
              <div>
                <label class="block text-[12px] font-medium text-on-surface-variant mb-1.5">Department Unit</label>
                <select class="w-full bg-[#101419] text-white text-[14px] px-3.5 py-2.5 rounded-xl border border-outline-variant/65 focus:outline-none focus:border-primary appearance-none cursor-pointer">
                  <option>Engineering & Ops</option>
                  <option>Finance & Audit</option>
                  <option>Enterprise Growth</option>
                  <option>Security & Compliance</option>
                </select>
              </div>
            </div>
            <div class="pt-2">
              <label class="flex items-center gap-2 cursor-pointer">
                <input type="checkbox" class="w-4 h-4 rounded bg-[#101419] border-outline-variant/65 text-primary focus:ring-primary focus:ring-offset-0"/>
                <span class="text-[12px] font-medium text-on-surface">Publish profile immediately to public About portal</span>
              </label>
            </div>
            <div class="flex items-center justify-end gap-3 pt-3 border-t border-outline-variant/40">
              <button type="button" @click="isInviteModalOpen = false" class="px-5 py-2 rounded-full border border-outline-variant/65 text-on-surface-variant hover:text-white hover:bg-surface-container text-[13px] font-medium transition-all">
                Cancel
              </button>
              <button type="submit" class="px-6 py-2 rounded-full bg-gradient-to-r from-[#10b981] to-[#059669] text-white text-[13px] font-semibold shadow-[0_0_16px_rgba(16,185,129,0.35)] hover:shadow-[0_0_24px_rgba(16,185,129,0.5)] active:scale-[0.98] transition-all flex items-center gap-1.5">
                <span class="material-symbols-outlined text-[16px]">send</span>
                <span>Send Invite</span>
              </button>
            </div>
          </form>
        </div>
      </div>
    </transition>

    <!-- MODAL 2: EDIT MEMBER -->
    <transition name="fade">
      <div v-if="isEditModalOpen" class="fixed inset-0 z-[60] flex items-center justify-center p-4" style="background-color: rgba(0,0,0,0.8); backdrop-filter: blur(4px);" @click.self="isEditModalOpen = false">
        <div class="relative w-full max-w-xl bg-[#161b20] border border-primary/40 rounded-2xl p-6 shadow-[0_8px_32px_rgba(0,0,0,0.8),0_0_24px_rgba(16,185,129,0.25)] space-y-5 animate-in zoom-in-95">
          <!-- Header -->
          <div class="flex items-center justify-between border-b border-outline-variant/40 pb-4">
            <div class="flex items-center gap-3">
              <div class="w-9 h-9 rounded-xl bg-primary/10 border border-primary/30 flex items-center justify-center text-primary">
                <span class="material-symbols-outlined text-[18px]">badge</span>
              </div>
              <div>
                <h3 class="text-[18px] font-semibold text-white" style="font-family: 'Plus Jakarta Sans', sans-serif;">Edit Team Member</h3>
                <p class="text-[12px] font-medium text-[#999999]">Update profile parameters and public credentials</p>
              </div>
            </div>
            <button class="text-on-surface-variant hover:text-white p-1 rounded-lg hover:bg-surface-container transition-colors" @click="isEditModalOpen = false">
              <span class="material-symbols-outlined text-[20px]">close</span>
            </button>
          </div>
          
          <!-- Avatar Replacement -->
          <div class="p-3.5 rounded-xl bg-[#101419] border border-outline-variant/50 flex items-center justify-between gap-4">
            <div class="flex items-center gap-3">
              <div class="w-14 h-14 rounded-full p-0.5 bg-gradient-to-tr from-primary to-surface-variant overflow-hidden">
                <img :src="editingMember?.avatar" class="w-full h-full rounded-full object-cover" />
              </div>
              <div>
                <p class="text-[13px] font-semibold text-white" style="font-family: 'Plus Jakarta Sans', sans-serif;">Profile Photo</p>
                <p class="text-[11px] font-semibold text-[#999999]">Formats: JPG, WEBP, PNG (Max 4MB)</p>
              </div>
            </div>
            <button class="px-3.5 py-1.5 rounded-full border border-primary/40 text-primary text-[12px] font-medium hover:bg-primary/10 transition-all flex items-center gap-1.5">
              <span class="material-symbols-outlined text-[14px]">photo_library</span>
              <span>Replace from Media</span>
            </button>
          </div>
          
          <form @submit.prevent="submitEdit" class="space-y-4">
            <div class="grid grid-cols-2 gap-4">
              <div>
                <label class="block text-[12px] font-medium text-on-surface-variant mb-1.5">Full Name</label>
                <input v-model="editForm.name" class="w-full bg-[#101419] text-white text-[14px] px-3.5 py-2.5 rounded-xl border border-outline-variant/65 focus:outline-none focus:border-primary" type="text" />
              </div>
              <div>
                <label class="block text-[12px] font-medium text-on-surface-variant mb-1.5">Role Title</label>
                <input v-model="editForm.role" class="w-full bg-[#101419] text-white text-[14px] px-3.5 py-2.5 rounded-xl border border-outline-variant/65 focus:outline-none focus:border-primary" type="text" />
              </div>
            </div>
            <div>
              <label class="block text-[12px] font-medium text-on-surface-variant mb-1.5">Work Email</label>
              <input v-model="editForm.email" class="w-full bg-[#101419] text-white text-[14px] px-3.5 py-2.5 rounded-xl border border-outline-variant/65 focus:outline-none focus:border-primary" type="email" />
            </div>
            <div>
              <label class="block text-[12px] font-medium text-on-surface-variant mb-1.5">Executive Bio (Public Portal)</label>
              <textarea class="w-full bg-[#101419] text-white text-[14px] px-3.5 py-2.5 rounded-xl border border-outline-variant/65 focus:outline-none focus:border-primary resize-none leading-relaxed" rows="3">Spearheading global POS & high-throughput distributed architectures across GCC & APAC regions with zero-trust orchestration.</textarea>
            </div>
            
            <div class="flex items-center justify-between pt-3 border-t border-outline-variant/40">
              <button @click="confirmDelete(editingMember); isEditModalOpen = false" type="button" class="text-[12px] font-medium text-error hover:underline flex items-center gap-1">
                <span class="material-symbols-outlined text-[14px]">person_remove</span>
                <span>Revoke Membership</span>
              </button>
              <div class="flex items-center gap-3">
                <button type="button" @click="isEditModalOpen = false" class="px-4 py-2 rounded-full border border-outline-variant/65 text-on-surface-variant hover:text-white hover:bg-surface-container text-[13px] font-medium transition-all">
                  Cancel
                </button>
                <button type="submit" class="px-6 py-2 rounded-full bg-gradient-to-r from-[#10b981] to-[#059669] text-white text-[13px] font-semibold shadow-[0_0_16px_rgba(16,185,129,0.35)] hover:shadow-[0_0_24px_rgba(16,185,129,0.5)] active:scale-[0.98] transition-all flex items-center gap-1.5">
                  <span class="material-symbols-outlined text-[16px]">save</span>
                  <span>Save Changes</span>
                </button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </transition>

    <AdminConfirmModal v-model="isConfirmOpen" title="Revoke Membership" message="Are you sure you want to remove this member? They will lose access to the admin panel and be removed from the public portal immediately." @confirm="removeMember" />
    <AdminToast ref="toastRef" />
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'

definePageMeta({ layout: 'admin', middleware: ['admin-auth'] })

const toastRef = ref()

// Modals State
const isInviteModalOpen = ref(false)
const isEditModalOpen = ref(false)
const isConfirmOpen = ref(false)
const editingMember = ref<any>(null)
const memberToDelete = ref<any>(null)

// Forms State
const inviteForm = ref({ name: '', email: '' })
const editForm = ref({ name: '', role: '', email: '' })

// Mock Active Members List
const activeMembers = ref<any[]>([])

// Mock Pending Invites List
const pendingInvites = ref<any[]>([])

// Methods
const openEditModal = (member: any) => {
  editingMember.value = member
  editForm.value = { name: member.name, role: member.role, email: member.email }
  isEditModalOpen.value = true
}

const submitEdit = () => {
  if (editingMember.value) {
    editingMember.value.name = editForm.value.name
    editingMember.value.role = editForm.value.role
    editingMember.value.email = editForm.value.email
    toastRef.value?.addToast('Team member profile updated successfully.')
  }
  isEditModalOpen.value = false
}

const submitInvite = () => {
  if(inviteForm.value.name && inviteForm.value.email) {
    const initials = inviteForm.value.name.split(' ').map(n => n[0]).join('').substring(0, 2).toUpperCase()
    pendingInvites.value.unshift({
      initials,
      status: 'Pending',
      name: inviteForm.value.name,
      role: 'Staff Personnel',
      email: inviteForm.value.email,
      icon: 'mark_email_unread',
      meta: 'Invited just now'
    })
    toastRef.value?.addToast(`Invitation sent to ${inviteForm.value.email}`)
    isInviteModalOpen.value = false
    inviteForm.value = { name: '', email: '' }
  }
}

const confirmDelete = (member: any) => {
  memberToDelete.value = member
  isConfirmOpen.value = true
}

const removeMember = () => {
  if (memberToDelete.value) {
    activeMembers.value = activeMembers.value.filter(m => m.email !== memberToDelete.value.email)
    toastRef.value?.addToast(`${memberToDelete.value.name} has been revoked from the system.`)
  }
}
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
  font-variation-settings: 'wght' 300, 'GRAD' 0, 'FILL' 0, 'opsz' 20;
  vertical-align: middle;
}

.custom-scroll::-webkit-scrollbar {
  width: 6px;
  height: 6px;
}
.custom-scroll::-webkit-scrollbar-track {
  background: #101419;
}
.custom-scroll::-webkit-scrollbar-thumb {
  background: rgba(60, 74, 66, 0.6);
  border-radius: 4px;
}
.custom-scroll::-webkit-scrollbar-thumb:hover {
  background: rgba(16, 185, 129, 0.4);
}

/* Base animations */
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

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.2s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
