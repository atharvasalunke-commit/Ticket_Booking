package com.example.TicketBooking.Dto;
import com.example.TicketBooking.Entity.Event;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
public record EventResponse(
        Long id,
        String title,
        String description,
        String category,
        String venueName,
        String city,
        LocalDateTime startTime,
        LocalDateTime endTime,
        BigDecimal basePrice,
        int seatCapacity,
        Instant createdAt
) {

    public static EventResponse from(Event event) {
        return new EventResponse(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getCategory(),
                event.getVenueName(),
                event.getCity(),
                event.getStartTime(),
                event.getEndTime(),
                event.getBasePrice(),
                event.getSeatCapacity(),
                event.getCreatedAt()
        );
    }
}