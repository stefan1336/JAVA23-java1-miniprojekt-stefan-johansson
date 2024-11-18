import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class EventInterface {

    // Metod där jag displayar min ruta för att skapa event
    public static void eventWindow(JTextArea textArea,Day weekday){
        JFrame frame = new JFrame("Add Event");

        createFrame(frame);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));

        displayEvent(frame, textArea, weekday);
    }

    // Metod för att skapa själva ramen
    private static void createFrame(JFrame frame){
        frame.setSize(500, 400);
        frame.getContentPane().setBackground(Color.lightGray);
        frame.setVisible(true);
    }

    // Metod där allt i min eventruta displayas
    private static void displayEvent(JFrame frame, JTextArea textArea, Day weekday){
        JPanel panel = createPanel();
        JTextField textField = createTextField();
        JButton button = createEventBtn();
        JSpinner time = createTimer();

        buttonAction(button,textField, textArea, weekday, time,frame);
        addPanel(frame, panel,textField,button, time);
    }

    // Metod för att skapa min JPanel
    private static JPanel createPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(Color.lightGray);

        return panel;
    }

    // Metod för att skapa mitt JTextfield
    private static JTextField createTextField(){
        JTextField textField = new JTextField();
        textField.setToolTipText("Type in the event you want to add!");
        textField.setMaximumSize(new Dimension(200, 40));
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return textField;
    }

    // Metod för att skapa min JButton
    private static JButton createEventBtn(){
        JButton button = new JButton("Add Event");
        button.setMaximumSize(new Dimension(200,40));
        button.setBackground(new Color(30, 144, 255));
        button.setForeground(Color.WHITE);
        button.setToolTipText("Add Event");
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,true));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        return button;
    }

    // Metod för att göra min klocka
    private static JSpinner createTimer(){
        SpinnerDateModel spinnerModel = new SpinnerDateModel();
        JSpinner spinner = new JSpinner(spinnerModel);
        spinner.setMaximumSize(new Dimension(200, 40));
        spinner.setToolTipText("add time for your event");
        JSpinner.DateEditor setTime = new JSpinner.DateEditor(spinner,"HH:mm");

        spinner.setEditor(setTime);

        return spinner;
    }

    // Min action listener
    private static void buttonAction(JButton button, JTextField textField, JTextArea textArea, Day weekDay, JSpinner time, JFrame frame){
        button.addActionListener(e -> {
            // Hämtar texten från min textfield
            String newEventText = textField.getText();
            Date newEventTime = (Date) time.getValue();

            if(!newEventText.isEmpty() && !newEventTime.toString().isEmpty()){
                // Om inte textfieldet är tomt eller tiden är tom så körs metoden
                createEvent(newEventText, weekDay, textArea, textField, time);
                // Stänger rutan och återgår till kalendern
                frame.dispose();
            }
            else{
                // Pop upp om textField är tomt
                JOptionPane.showMessageDialog(null, "Please type an event!");
            }
        });
    }

    // Metod där jag skapar mina event
    private static void createEvent(String newEventText, Day weekDay, JTextArea textArea, JTextField textField, JSpinner time){
        // formatera om tiden
        String eventTime = new SimpleDateFormat("HH:mm").format(time.getValue());
        Event newEvent = new Event(newEventText);

        UserInterface.saveEvent(weekDay, newEvent);
        // Lägg till texten i min textarea
        textArea.append(newEventText + " - " + eventTime + "\n");
        // Pop upp för att visa vad som lagts till
        JOptionPane.showMessageDialog(time, textField.getText() + " added to calendar!");
        // Rensa texten
        textField.setText("");
    }

    // Metod för där jag lägger till alla mina J-objekt
    private static void addPanel(JFrame frame, JPanel panel, JTextField textField, JButton button, JSpinner time){
        frame.add(panel);
        panel.add(Box.createVerticalStrut(100));
        panel.add(textField);
        panel.add(Box.createVerticalStrut(30));
        panel.add(time);
        panel.add(Box.createVerticalStrut(30));
        panel.add(button);
    }
}
