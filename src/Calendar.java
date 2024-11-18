import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Calendar {

    EventInterface eventInterface = new EventInterface();

    // Metod för att skapa panelen för kalendern
    public JPanel createWeekDayPanel(Day weekDay, LocalDate date){
        JPanel panel = createPanel();

        JLabel weekdayNameLabel = new JLabel(weekDay.toString(), SwingConstants.CENTER);
        JLabel todaysDateLabel = new JLabel(dateToDisplay(date), SwingConstants.CENTER);

        JTextArea textArea = createTextArea();
        JButton button = createButton();
        createDayAndDateLabel(weekdayNameLabel, todaysDateLabel,weekDay);

        buttonAction(button, textArea, weekDay);
        addPanel(panel,weekdayNameLabel,todaysDateLabel,textArea,button);

        return panel;
    }

    // Metod där jag skapar min panel
    private JPanel createPanel()  {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3,true));
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        return panel;
    }

    // Metod för att att skapa min label för att visa Dag och datum
    private void createDayAndDateLabel(JLabel weekdayNameLabel, JLabel todaysDateLabel, Day weekDay) {
        weekdayNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        weekdayNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Loop för att markera lite tydligare vilka dagar som är helg
        if(weekDay.getDayType().equalsIgnoreCase("Weekend")){
            weekdayNameLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
            weekdayNameLabel.setOpaque(true);
        }

        todaysDateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    // Metod för att skapa textarea
    private JTextArea createTextArea(){
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setPreferredSize(new Dimension(200,100));

        return textArea;
    }

    // Metod för att skapa knappar
    private JButton createButton(){
        JButton button = new JButton("Add event");
        button.setMaximumSize(new Dimension(150,20));
        button.setBackground(new Color(30, 144, 255));
        button.setForeground(Color.WHITE);
        button.setToolTipText("Create Event");
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,false));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        return button;
    }

    // Actionlistener för mina knappar
    private void buttonAction(JButton button, JTextArea textArea, Day weekDay){
        button.addActionListener(e -> {
            // Öppnar upp mitt nya fönster för event
                eventInterface.eventWindow(textArea, weekDay);
        });
    }

    // Metod där jag lägger till allt i panelen samtidigt som jag sätter lite mellanrum för att göra det snyggare
    private void addPanel(JPanel panel, JLabel weekdayNameLabel, JLabel todaysDateLabel,JTextArea textArea,JButton button){
        panel.add(Box.createVerticalStrut(10));
        panel.add(weekdayNameLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(todaysDateLabel);
        panel.add(Box.createVerticalStrut(30));
        panel.add(textArea);
        panel.add(Box.createVerticalStrut(10));
        panel.add(button);
        panel.add(Box.createVerticalStrut(10));
    }

    // Metod för att visa Dagens datum samt månad
    private String dateToDisplay(LocalDate date){
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd MMMM");
        return date.format(formatDate);
    }

}
