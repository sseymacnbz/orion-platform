package main.java.com.orion.platform.domain;

import main.java.com.orion.platform.domain.EventId;
import main.java.com.orion.platform.domain.EventStatus;

import java.time.LocalDateTime;

public class Event{
    private final EventId id;
    private final Long ownerId;

    private String title;
    private String description;

    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    private EventStatus status;

    public Event(EventId id, Long ownerId, LocalDateTime startTime, LocalDateTime endTime){
        validate(id, ownerId, startTime, endTime);
        this.id = id;
        this.ownerId = ownerId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = EventStatus.CREATED;
    }

    private void validate(EventId id, Long ownerId, LocalDateTime startTime, LocalDateTime endTime){
        if(id == null){
            throw new IllegalArgumentException("Event id cannot be null");
        }

        if(ownerId == null){
            throw new IllegalArgumentException("Owner id cannot be null");
        }

        if(startTime == null || endTime == null){
            throw new IllegalArgumentException("Event time cannot be null");
        }

        if(!startTime.isBefore(endTime)){
            throw new IllegalArgumentException("Start time must be before end time");
        }

        if(startTime.isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("Event cannot start in the past");
        }
    }

    public void updateDetails(String title, String description){
        if(status == EventStatus.CANCELLED){
            throw new IllegalStateException("Cancelled event cannot be updated");
        }

        if(title == null || title.isBlank()){
            throw new IllegalArgumentException("Title cannot be null or blank");
        }

        if(description == null || description.isBlank()){
            throw new IllegalArgumentException("Description cannot be null or blank");
        }

        this.title = title;
        this.description = description;
        this.status = EventStatus.UPDATED;
    }

    public  void cancel(){
        if(status == EventStatus.CANCELLED){
            return;
        }

        if(status == EventStatus.FINISHED){
            throw new IllegalStateException("Finished status cannot be cancelled");
        }

        if(!startTime.isAfter(LocalDateTime.now())){
            throw new IllegalStateException("Event cannot be cancelled after it has started");
        }

        this.status = EventStatus.CANCELLED;
    }
}