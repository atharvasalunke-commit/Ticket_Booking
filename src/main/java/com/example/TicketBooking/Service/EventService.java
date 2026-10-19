package com.example.TicketBooking.Service;
import com.example.TicketBooking.Dto.EventRequest;
import com.example.TicketBooking.Dto.EventResponse;
import com.example.TicketBooking.Entity.Event;
import com.example.TicketBooking.Exception.ResourceNotFoundException;
import com.example.TicketBooking.Repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public EventResponse createEvent(EventRequest request) {
        Event event = new Event();
        applyRequest(event, request);
        return EventResponse.from(eventRepository.save(event));
    }

    @Transactional(readOnly = true)
    public List<EventResponse> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(EventResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public EventResponse getEvent(Long id) {
        return EventResponse.from(findEventOrThrow(id));
    }

    @Transactional
    public EventResponse updateEvent(Long id, EventRequest request) {
        Event event = findEventOrThrow(id);
        applyRequest(event, request);
        // No save() call needed: the entity is managed inside this transaction,
        // so Hibernate notices the changes (dirty checking) and runs the UPDATE on commit.
        return EventResponse.from(event);
    }
    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw eventNotFound(id);
        }
        eventRepository.deleteById(id);
    }

    private Event findEventOrThrow(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> eventNotFound(id));
    }

    private ResourceNotFoundException eventNotFound(Long id) {
        return new ResourceNotFoundException("Event not found with id " + id);
    }

    private void applyRequest(Event event, EventRequest request) {
        event.setTitle(request.title());
        event.setDescription(request.description());
        event.setCategory(request.category());
        event.setVenueName(request.venueName());
        event.setCity(request.city());
        event.setStartTime(request.startTime());
        event.setEndTime(request.endTime());
        event.setBasePrice(request.basePrice());
        event.setSeatCapacity(request.seatCapacity());
    }
}