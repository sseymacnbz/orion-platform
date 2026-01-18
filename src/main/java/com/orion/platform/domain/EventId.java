package main.java.com.orion.platform.domain;

public class EventId {
    private final Long value;

    public EventId(Long value){
        this.value = value;

        if( value == null){
            throw new IllegalArgumentException("EventId cannot be null");
        }
    }

    public Long value(){
        return value();
    }
}