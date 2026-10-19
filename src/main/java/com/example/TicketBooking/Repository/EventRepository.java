package com.example.TicketBooking.Repository;
import com.example.TicketBooking.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}