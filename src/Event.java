public class Event {

    private String event;

    // Konstruktor ett event
    public Event(String event) {
        this.event = event;
    }

    // Ska man kunna hämta event?
    public String getEvent() {
        return event;
    }

    @Override
    public String toString() {
        return event;
    }
}

