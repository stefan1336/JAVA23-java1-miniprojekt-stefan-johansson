import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Calendar {

    public JPanel createWeekDayPanel(Day weekDay, LocalDate date){
        // Mina paneler för veckodagarna
        JPanel panel = new JPanel();

        // Label för namn på veckodagarna
        JLabel weekdayNameLabel = new JLabel(weekDay.toString(), SwingConstants.CENTER);

        // Label för datum på veckodagarna
        JLabel todaysDateLabel = new JLabel(dateToDisplay(date), SwingConstants.CENTER);

        // TextArea där det printas ut olika händelser
        JTextArea textArea = new JTextArea();

        // TextField där man skriver in en ny händelse
        JTextField textField = new JTextField();

        // Button för att kunna lägga till en ny händelse i kalendern
        JButton button = new JButton("Add Event");

        // Mina metoder där jag skapar min kallender
        createPanel(panel);
        createDayAndDateLabel(weekdayNameLabel, todaysDateLabel);
        createTextArea(textArea);
        createTextField(textField);
        createButton(button);
        buttonAction(button, textField, textArea, weekDay);

        addPanel(panel,weekdayNameLabel,todaysDateLabel,textArea,textField,button);

        return panel;

    }

    // Metod där jag skapar min panel
    private void createPanel(JPanel panel) {
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3,true));
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
    }
    // Metod för att att skapa min label för att visa Dag och datum
    private void createDayAndDateLabel(JLabel weekdayNameLabel, JLabel todaysDateLabel){
        weekdayNameLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        weekdayNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        todaysDateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    // Metod för att skapa textarea
    private void createTextArea(JTextArea textArea){
        textArea.setEditable(false);
        textArea.setPreferredSize(new Dimension(200,100));
    }

    // Metod för att skapa textfields
    private void createTextField(JTextField textField){
        textField.setToolTipText("Type in the event you want to add!");
        textField.setMaximumSize(new Dimension(150,20));
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    // Metod för att skapa knappar
    private void createButton(JButton button){
        button.setMaximumSize(new Dimension(150,20));
        button.setBackground(new Color(30, 144, 255));
        button.setForeground(Color.WHITE);
        button.setToolTipText("Add Event");
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,true));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    // Actionlistener för mina knappar
    private void buttonAction(JButton button, JTextField textField, JTextArea textArea, Day weekDay){
        button.addActionListener(e -> {
            // Hämtar texten från min textfield
            String newEventText = textField.getText();
            if(!newEventText.isEmpty()){
                Event newEvent = new Event(newEventText);
                UserInterface.createEvent(weekDay, newEvent);
                // Lägg till texten
                textArea.append(newEventText + "\n");
                JOptionPane.showMessageDialog(null, textField.getText() + " added to calendar!");
                // Rensa texten
                textField.setText("");
            }
            else{
                // Pop upp om textField är tomt
                JOptionPane.showMessageDialog(null, "Please type an event!");
            }
        });
    }

    // Metod där jag lägger till allt i panelen samtidigt som jag sätter lite mellanrum för att göra det snyggare
    private void addPanel(JPanel panel, JLabel weekdayNameLabel, JLabel todaysDateLabel,JTextArea textArea,JTextField textField,JButton button){
        panel.add(Box.createVerticalStrut(10));
        panel.add(weekdayNameLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(todaysDateLabel);
        panel.add(Box.createVerticalStrut(30));
        panel.add(textArea);
        panel.add(Box.createVerticalStrut(10));
        panel.add(textField);
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
