import os
import re
import shutil

admin_dir = r"C:\Users\aaa\Desktop\Admin panel\kavorax-admin\app"

# 1. Update layout navigation
admin_layout_path = os.path.join(admin_dir, "layouts", "admin.vue")
with open(admin_layout_path, "r", encoding="utf-8") as f:
    layout_content = f.read()

leads_old_nav = r"""          <!-- 2. Leads & Demo Requests -->
          <NuxtLink to="/admin/leads" exact-active-class="bg-surface-container !text-primary border-l-4 !border-primary" class="flex items-center justify-between px-4 py-3 text-on-surface-variant hover:text-on-surface hover:bg-surface-container text-\[14px\] font-medium rounded-lg transition-all duration-200 border-l-4 border-transparent group outline-none focus-visible:ring-2 focus-visible:ring-primary" style="font-family: 'Plus Jakarta Sans', sans-serif;">
            <div class="flex items-center gap-3">
              <span class="material-symbols-outlined text-\[20px\] transition-colors" data-icon="group">group</span>
              <span class="whitespace-nowrap">Leads &amp; Demos</span>
            </div>
          </NuxtLink>"""

leads_new_nav = """          <!-- 2. Leads -->
          <div class="space-y-1">
            <div class="w-full flex items-center justify-between px-4 py-3 text-on-surface-variant text-[14px] font-medium rounded-lg" style="font-family: 'Plus Jakarta Sans', sans-serif;">
              <div class="flex items-center gap-3">
                <span class="material-symbols-outlined text-[20px] transition-colors" data-icon="group">group</span>
                <span>Leads</span>
              </div>
            </div>
            <div class="ml-[26px] py-1 space-y-1 border-l border-outline-variant/40">
              <NuxtLink to="/admin/leads/demo-bookings" exact-active-class="!text-[#10b981] !border-[#10b981]" class="block py-1.5 text-[12px] font-medium text-[#999999] hover:text-white transition-colors border-l border-transparent -ml-[1px] pl-[21px] outline-none focus-visible:ring-2 focus-visible:ring-primary rounded-r-md">Demo Bookings</NuxtLink>
              <NuxtLink to="/admin/leads/session-requests" exact-active-class="!text-[#10b981] !border-[#10b981]" class="block py-1.5 text-[12px] font-medium text-[#999999] hover:text-white transition-colors border-l border-transparent -ml-[1px] pl-[21px] outline-none focus-visible:ring-2 focus-visible:ring-primary rounded-r-md">Schedule Your Session</NuxtLink>
              <NuxtLink to="/admin/leads/enquiries" exact-active-class="!text-[#10b981] !border-[#10b981]" class="block py-1.5 text-[12px] font-medium text-[#999999] hover:text-white transition-colors border-l border-transparent -ml-[1px] pl-[21px] outline-none focus-visible:ring-2 focus-visible:ring-primary rounded-r-md">Enquiries</NuxtLink>
            </div>
          </div>"""

layout_content = re.sub(leads_old_nav, leads_new_nav, layout_content)
with open(admin_layout_path, "w", encoding="utf-8") as f:
    f.write(layout_content)


# 2. Update dashboard index.vue
index_path = os.path.join(admin_dir, "pages", "admin", "index.vue")
with open(index_path, "r", encoding="utf-8") as f:
    index_content = f.read()

card1_old = """        <div class="mt-4">
          <p class="text-[28px] font-bold text-outline leading-none" style="font-family: 'Plus Jakarta Sans', sans-serif;">--</p>
          <p class="text-[12px] font-medium text-outline mt-1">No data available yet.</p>
        </div>"""
card1_new = """        <div class="mt-4">
          <p class="text-[28px] font-bold text-outline leading-none" style="font-family: 'Plus Jakarta Sans', sans-serif;">{{ totalLeads }}</p>
          <p class="text-[12px] font-medium text-outline mt-1">Total Leads</p>
        </div>"""

card2_old = """        <div class="mt-4">
          <p class="text-[28px] font-bold text-outline leading-none" style="font-family: 'Plus Jakarta Sans', sans-serif;">--</p>
          <p class="text-[12px] font-medium text-outline mt-1">No data available yet.</p>
        </div>"""
card2_new = """        <div class="mt-4">
          <p class="text-[28px] font-bold text-outline leading-none" style="font-family: 'Plus Jakarta Sans', sans-serif;">{{ totalDemos }}</p>
          <p class="text-[12px] font-medium text-outline mt-1">Demo Bookings</p>
        </div>"""

card3_old = """        <div class="mt-4">
          <p class="text-[28px] font-bold text-outline leading-none" style="font-family: 'Plus Jakarta Sans', sans-serif;">--</p>
          <p class="text-[12px] font-medium text-outline mt-1">No data available yet.</p>
        </div>"""
card3_new = """        <div class="mt-4">
          <p class="text-[28px] font-bold text-outline leading-none" style="font-family: 'Plus Jakarta Sans', sans-serif;">{{ totalSessions }}</p>
          <p class="text-[12px] font-medium text-outline mt-1">Schedule Sessions</p>
        </div>"""

card4_old = """        <div class="mt-4">
          <p class="text-[28px] font-bold text-outline leading-none" style="font-family: 'Plus Jakarta Sans', sans-serif;">--</p>
          <p class="text-[12px] font-medium text-outline mt-1">No data available yet.</p>
        </div>"""
card4_new = """        <div class="mt-4">
          <p class="text-[28px] font-bold text-outline leading-none" style="font-family: 'Plus Jakarta Sans', sans-serif;">{{ totalEnquiries }}</p>
          <p class="text-[12px] font-medium text-outline mt-1">Enquiries</p>
        </div>"""

index_content = index_content.replace(card1_old, card1_new, 1)
index_content = index_content.replace(card2_old, card2_new, 1)
index_content = index_content.replace(card3_old, card3_new, 1)
index_content = index_content.replace(card4_old, card4_new, 1)

# Add variables to the setup script
script_start = "const recentLeads = ref<any[]>([])"
script_vars = """const recentLeads = ref<any[]>([])
const totalLeads = ref(0)
const totalDemos = ref(0)
const totalSessions = ref(0)
const totalEnquiries = ref(0)"""
index_content = index_content.replace(script_start, script_vars)

# Populate the variables
fetch_logic = """    const enquiries = enquiriesRes.status === 'fulfilled' ? (enquiriesRes.value as any[]) : []
    const demoRequests = demoRequestsRes.status === 'fulfilled' ? (demoRequestsRes.value as any[]) : []
    const sessionRequests = sessionRequestsRes.status === 'fulfilled' ? (sessionRequestsRes.value as any[]) : []"""
fetch_logic_new = """    const enquiries = enquiriesRes.status === 'fulfilled' ? (enquiriesRes.value as any[]) : []
    const demoRequests = demoRequestsRes.status === 'fulfilled' ? (demoRequestsRes.value as any[]) : []
    const sessionRequests = sessionRequestsRes.status === 'fulfilled' ? (sessionRequestsRes.value as any[]) : []
    
    totalEnquiries.value = enquiries.length
    totalDemos.value = demoRequests.length
    totalSessions.value = sessionRequests.length
    totalLeads.value = enquiries.length + demoRequests.length + sessionRequests.length"""
index_content = index_content.replace(fetch_logic, fetch_logic_new)

with open(index_path, "w", encoding="utf-8") as f:
    f.write(index_content)

print("Updated layout and index")
