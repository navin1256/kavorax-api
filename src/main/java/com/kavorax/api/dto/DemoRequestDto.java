package com.kavorax.api.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DemoRequestDto {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Size(max = 255, message = "Email must not exceed 255 characters")
    private String email;

    @Size(max = 100, message = "Company name must not exceed 100 characters")
    private String company;

    @JsonProperty("reqs")
    @JsonAlias({"requirements", "requirement"})
    @Size(max = 1000, message = "Requirements must not exceed 1000 characters")
    private String requirements;

    @Size(max = 30, message = "Phone must not exceed 30 characters")
    private String phone;

    @JsonProperty("date")
    @JsonAlias({"preferredDate", "preferred_date"})
    @Size(max = 50, message = "Date must not exceed 50 characters")
    private String date;

    @JsonProperty("timeSlot")
    @JsonAlias({"time_slot", "timeslot"})
    @Size(max = 50, message = "Time slot must not exceed 50 characters")
    private String timeSlot;

    @Size(max = 2000, message = "Message must not exceed 2000 characters")
    private String message;

    @Size(max = 50, message = "Type must not exceed 50 characters")
    private String type;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public String getRequirements() { return requirements; }
    public void setRequirements(String requirements) { this.requirements = requirements; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTimeSlot() { return timeSlot; }
    public void setTimeSlot(String timeSlot) { this.timeSlot = timeSlot; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
