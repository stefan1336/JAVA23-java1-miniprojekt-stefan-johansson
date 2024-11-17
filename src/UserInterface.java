import javax.swing.*;
import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;

public class UserInterface {

    // Samla mina event
    private static EnumMap<Day, ArrayList<Event>> eventEachDay = new EnumMap<>(Day.class);
    private int startDay = 0;

    Calendar calendar = new Calendar();
    LocalDate today = LocalDate.now();

    // JPanel för att gruppera komponenter, en typ av container
    JFrame frame = new JFrame("Calender");

    // Ny frame för information till varje veckodag?
    public UserInterface(){
        createFrame();
        displayWeekdays();

        frame.setVisible(true);
    }

    // Metod för att skapa min ram till själva kalendern
    private void createFrame(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 800);
        frame.setLayout(new GridLayout(1,7,10,10));
    }

    // Metod för att visa veckodagarna
    public void displayWeekdays(){
        LocalDate startOfTheWeek = today.with(DayOfWeek.MONDAY);

        for(Day day : Day.values()){
            LocalDate date = startOfTheWeek.plusDays(startDay);
            JPanel panel = calendar.createWeekDayPanel(day, date);

            showTodaysColumn(date, panel);

            frame.add(panel);
            startDay++;
        }
    }

    // Metod för att skapa event
    public static void createEvent(Day weekday, Event newEvent){
        if(!eventEachDay.containsKey(weekday)){
            eventEachDay.put(weekday, new ArrayList<>());
        }
        eventEachDay.get(weekday).add(newEvent);
    }

    // Metod för att färglägga den kolumnen som representerar dagens datum
    private void showTodaysColumn(LocalDate date, JPanel panel){
        if(date.equals(today)){
            displayBackground(panel, new Color(144,238,144));
        }
    }

    // Metod för att tydligt visa dagens dag
    private void displayBackground(JPanel panel, Color color){
        panel.setBackground(color);
        panel.setOpaque(true);
        for (Component component : panel.getComponents()) {
            // Går igenom mina komponenter och hoppar över JButton och JTextField om dom hittas
            if (component instanceof JButton || component instanceof JTextField) {
                continue;
            }
            // Sätt bakgrundsfärg på dagens datum
            if (component instanceof JComponent) {
                component.setBackground(color);
                ((JComponent) component).setOpaque(true);
            }
        }
    }
}
