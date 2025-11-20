package com.cypherlab.cypher_lab.dto;

public class AuthResponse {
    private String token;
    private String email;
    private Long userId;
    private String role;

    public AuthResponse(String token, String email, Long userId, String role) {
        this.token = token;
        this.email = email;
        this.userId = userId;
        this.role = role;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
