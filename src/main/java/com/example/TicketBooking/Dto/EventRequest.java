package com.example.TicketBooking.Dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
public record EventRequest(

        @NotBlank(message = "title is required")
        @Size(max = 255, message = "title must be at most 255 characters")
        String title,

        @Size(max = 2000, message = "description must be at most 2000 characters")
        String description,

        @NotBlank(message = "category is required")
        @Size(max = 100, message = "category must be at most 100 characters")
        String category,

        @NotBlank(message = "venueName is required")
        @Size(max = 255, message = "venueName must be at most 255 characters")
        String venueName,

        @NotBlank(message = "city is required")
        @Size(max = 100, message = "city must be at most 100 characters")
        String city,

        @NotNull(message = "startTime is required")
        LocalDateTime startTime,

        @NotNull(message = "endTime is required")
        LocalDateTime endTime,

        @NotNull(message = "basePrice is required")
        @DecimalMin(value = "0.00", message = "basePrice must not be negative")
        @Digits(integer = 8, fraction = 2, message = "basePrice must have at most 8 digits and 2 decimals")
        BigDecimal basePrice,

        @NotNull(message = "seatCapacity is required")
        @Min(value = 1, message = "seatCapacity must be at least 1")
        Integer seatCapacity
) {

    // Cross-field rule. Reported under the key "endAfterStart".
    @AssertTrue(message = "endTime must be after startTime")
    public boolean isEndAfterStart() {
        if (startTime == null || endTime == null) {
            return true; // the @NotNull checks already report the missing field
        }
        return endTime.isAfter(startTime);
    }
}