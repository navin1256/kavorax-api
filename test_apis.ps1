$baseUrl = "http://localhost:8080/api/v1"
$headers = @{"Content-Type"="application/json"}

Write-Host "Testing Demo POST..."
$demoPayload = '{"name":"John Doe","email":"john@test.com","company":"Acme Corp","reqs":"Need a POS system"}'
$demoResponse = Invoke-RestMethod -Uri "$baseUrl/demo-requests" -Method Post -Body $demoPayload -Headers $headers -ErrorAction Stop
Write-Host "Demo Response: $($demoResponse | ConvertTo-Json)"

Write-Host "Testing Contact Message POST..."
$contactPayload = '{"name":"Bob Brown","email":"bob@test.com","subject":"Pricing","message":"How much?"}'
$contactResponse = Invoke-RestMethod -Uri "$baseUrl/contact-messages" -Method Post -Body $contactPayload -Headers $headers -ErrorAction Stop
Write-Host "Contact Message Response: $($contactResponse | ConvertTo-Json)"

Write-Host "Testing Admin Login..."
$loginPayload = '{"email":"admin@kavorax.com","password":"SecureAuth_2025!"}'
try {
    $loginResponse = Invoke-RestMethod -Uri "$baseUrl/auth/login" -Method Post -Body $loginPayload -Headers $headers -ErrorAction Stop
    Write-Host "Login Response: $($loginResponse | ConvertTo-Json -Depth 5)"
    $token = $loginResponse.token
    
    $adminHeaders = @{"Content-Type"="application/json"; "Authorization"="Bearer $token"}
    
    Write-Host "Testing Admin Dashboard Stats GET..."
    $statsResponse = Invoke-RestMethod -Uri "$baseUrl/admin/dashboard/stats" -Method Get -Headers $adminHeaders -ErrorAction Stop
    Write-Host "Dashboard Stats Response: $($statsResponse | ConvertTo-Json)"
    
    Write-Host "Testing Admin Contact Messages GET..."
    $adminContactResponse = Invoke-RestMethod -Uri "$baseUrl/admin/contact-messages" -Method Get -Headers $adminHeaders -ErrorAction Stop
    Write-Host "Admin Contact Messages Response: $($adminContactResponse | ConvertTo-Json)"

} catch {
    Write-Host "Error during admin calls: $_"
}
