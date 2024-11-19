import javax.swing.*;
import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;

public class UserInterface {
    // Samla mina event i en Enum-map där jag sparar dagen det gäller och de olika händelserna man lägger till.
    private static EnumMap<Day, ArrayList<Event>> todaysEvent = new EnumMap<>(Day.class);
    private int startDay = 0;

    Calendar calendar = new Calendar();
    LocalDate today = LocalDate.now();

    // JPanel för att gruppera komponenter, en typ av container
    JFrame frame = new JFrame("Calender");

    // Konstruktor där metoderna för att skapa Kalendern och metoden för att visa kalendern körs
    public UserInterface(){
        createFrame();
        displayWeekdays();

        frame.setVisible(true);
    }

    // Metod för att spara eventen. Här skickas dagen då eventet ska ske och det nya eventet.
    // Kollar om eventet innehåller en dag i så fall läggs dagen till i min lista
    public static void saveEvent(Day weekday, Event newEvent){
        if(!todaysEvent.containsKey(weekday)){
            todaysEvent.put(weekday, new ArrayList<>());
        }
        todaysEvent.get(weekday).add(newEvent);
    }

    // Metod för att skapa min ram till själva kalendern
    private void createFrame(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 800);
        frame.setLayout(new GridLayout(1,7,10,10));
        frame.getContentPane().setBackground(Color.lightGray);
    }

    // Metod för att visa veckodagarna
    public void displayWeekdays(){
        LocalDate startOfTheWeek = today.with(DayOfWeek.MONDAY);

        // Loopar över dagarna i min Enum-Day. Därefter skapas en JPanel där jag skickar mina dagar och datum till en metod i min calenderklass.
        for(Day day : Day.values()){
            LocalDate date = startOfTheWeek.plusDays(startDay);
            JPanel panel = calendar.createWeekDayPanel(day, date);

            showTodaysColumn(date, panel);

            frame.add(panel);
            startDay++;
        }
    }

    // Metod för att färglägga den kolumnen som representerar dagens datum.
    // Här tas datum emot och om datumet är detsamma som dagens dag så körs metoden displayBackground
    private void showTodaysColumn(LocalDate date, JPanel panel){
        if(date.equals(today)){
            displayBackground(panel, new Color(144,238,144));
        }
    }

    // Metod för att tydligt visa dagens dag.
    // Tar emot panelen och färgen för att färglägga rätt panel. Det vill säga Panelen som motsvarar dagens datum.
    private void displayBackground(JPanel panel, Color color){
        panel.setBackground(color);
        panel.setOpaque(true);
        // Foreach loop där det loopas över alla komponenterna.
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
