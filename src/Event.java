import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Event {

    private String event;

    HashMap<String, Event> list = new HashMap();

//    ArrayList<JTextField> newEvent;
//    ArrayList<JTextArea> events;

    // Konstruktor ett event
    public Event(String event) {
        this.event = event;
    }

    // Ska man kunna hämta event?
    public String getEvent() {
        return event;
    }

    // Skapa event
    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return event;
    }
}











// Här vill jag kunna se Event
// Skapa Event
// Hämta Event
// Ta bort Event
