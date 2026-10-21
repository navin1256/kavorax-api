package com.kavorax.api.dto;

public class LoginResponse {
    private String token;
    private UserData user;

    public LoginResponse(String token, UserData user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public UserData getUser() { return user; }
    public void setUser(UserData user) { this.user = user; }

    public static class UserData {
        private Long id;
        private String email;
        private String role;

        public UserData(Long id, String email, String role) {
            this.id = id;
            this.email = email;
            this.role = role;
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
    }
}
