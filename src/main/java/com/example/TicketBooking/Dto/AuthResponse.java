package com.example.TicketBooking.Dto;
import com.example.TicketBooking.Entity.Role;

public record AuthResponse(String token, String tokenType, String email, String name, Role role) {}
