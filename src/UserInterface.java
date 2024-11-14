import javax.swing.*;
import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap; // hämta enummap

public class UserInterface {
    Event event;

    // Samla mina event
    private EnumMap<Day, ArrayList<Event>> eventEachDay = new EnumMap<>(Day.class);
    private int startDay = 0;
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
        frame.setLayout(new GridLayout(1,7));
    }

    public void displayWeekdays(){
        LocalDate today = LocalDate.now();

        LocalDate startOfTheWeek = today.with(DayOfWeek.MONDAY);

        for(Day day : Day.values()){
            LocalDate date = startOfTheWeek.plusDays(startDay);
            JPanel panel = createWeekDayPanel(day, date);
            frame.add(panel);
            startDay++;
        }
//        Day[] weekDays = Day.values();
//        for (int i = 0; i < weekDays.length; i++){
//            Day weekDay = weekDays[i];
//            LocalDate date = today.plusDays(i);
//            JPanel panel = createWeekDayPanel(weekDay,date);
//
//            frame.add(panel);
//        }

    }

    private JPanel createWeekDayPanel(Day weekDay, LocalDate date){

        // Mina paneler för veckodagarna
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3,true));
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        // Label för namn på veckodagarna
        JLabel weekdayNameLabel = new JLabel(weekDay.toString(), SwingConstants.CENTER);
        weekdayNameLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        weekdayNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

//         Label för datum på veckodagarna
        JLabel todaysDateLabel = new JLabel(date.toString(), SwingConstants.CENTER);
        todaysDateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // TextArea där det printas ut olika händelser
        JTextArea textArea = new JTextArea();
        textArea.setSize(textArea.getPreferredSize());
        textArea.add(new Scrollbar(Scrollbar.HORIZONTAL));
        textArea.setEditable(false); // Vill jag kunna ändra?
        textArea.setAlignmentX(Component.CENTER_ALIGNMENT);

        // TextField där man skriver in en ny händelse
        JTextField textField = new JTextField();
        textField.setToolTipText("Type in the event you want to add!");
        textField.setMaximumSize(new Dimension(200,20));
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Button för att kunna lägga till en ny händelse i kalendern
        JButton button = new JButton("Add Event");
        button.setMaximumSize(new Dimension(200,20));
        button.setBackground(new Color(48, 188, 237));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK,1,true));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ActionListener
        button.addActionListener(e -> {
            // Hämtar texten från min textfield
           String newEventText = textField.getText();
           if(!newEventText.isEmpty()){
               event = new Event(newEventText);
               createEvent(weekDay, event);
               // Lägg till texten
               textArea.append(newEventText + "\n");
               // Rensa texten
               textField.setText("");
           }
           else{
               // Fixa någon pop up?
           }

        });

        // Addar allt och sätter mellanrum
        panel.add(Box.createVerticalStrut(10));
        panel.add(weekdayNameLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(todaysDateLabel);
        panel.add(Box.createVerticalStrut(30));
//        panel.add(new JScrollPane(textArea));
        panel.add(textArea);
        panel.add(Box.createVerticalGlue());
        panel.add(textField, BorderLayout.SOUTH);
        panel.add(Box.createVerticalStrut(10));
        panel.add(button, BorderLayout.SOUTH);
        panel.add(Box.createVerticalStrut(10));

        return panel;

    }

    // Metod för att skapa event
    private void createEvent(Day weekday, Event newEvent){
        if(!eventEachDay.containsKey(weekday)){
            eventEachDay.put(weekday, new ArrayList<>());
        }
        eventEachDay.get(weekday).add(newEvent);
    }
}
