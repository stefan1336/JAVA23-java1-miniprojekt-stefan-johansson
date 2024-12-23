import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class EventInterface {

    // Metod där jag displayar min ruta för att skapa event
    public static void eventWindow(JTextArea textArea,Day weekday){
        JFrame frame = new JFrame("New Event");

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
        JLabel label = createLabel();
        JTextField textField = createTextField();
        JButton button = createEventBtn();
        JSpinner time = createTimer();

        buttonAction(button,textField, textArea, weekday, time,frame);
        addPanel(frame, panel,textField,button, time,label);
    }

    // Metod för att skapa min label med beskrivande text för vad som man gör i panelen
    private static JLabel createLabel(){
        JLabel label = new JLabel("New Event");
        label.setFont(new Font("Tahoma", Font.BOLD, 20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        return label;
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
        // Tooltip som visas om man håller musen över inputrutan för att göra det tydligt för användaren vad som ska göras.
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

    // Metod för att göra min spinner, panelen där man ändrar tid.
    private static JSpinner createTimer(){
        SpinnerDateModel spinnerModel = new SpinnerDateModel();
        JSpinner spinner = new JSpinner(spinnerModel);
        spinner.setMaximumSize(new Dimension(200, 40));

        // Håller man musen över klockan så står det en liten text om vad som önskas här för att göra det tydligt för användaren.
        spinner.setToolTipText("add time for your event");

        // Formaterar om så det visas timme och minut i min ruta.
        JSpinner.DateEditor setTime = new JSpinner.DateEditor(spinner, "HH:mm");
        spinner.setEditor(setTime);
        return spinner;
    }

    // Min action listener för att spara nytt event
    private static void buttonAction(JButton button, JTextField textField, JTextArea textArea, Day weekDay, JSpinner time, JFrame frame){
        button.addActionListener(e -> {
            // Hämtar texten från min textfield och tiden
            String newEventText = textField.getText();
            Date newEventTime = (Date) time.getValue();

            // Kollar att inte textfieldet är tomt eller tiden inte är tom.
            if(!newEventText.isEmpty() && !newEventTime.toString().isEmpty()){
                // Kör metoden createEvent
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
        textArea.append(eventTime + ": " + newEventText + "\n");
        // Pop upp för att visa vad som lagts till
        JOptionPane.showMessageDialog(time, textField.getText() + " added to calendar!");
        // Rensa texten
        textField.setText("");
    }

    // Metod för där jag lägger till alla mina J-objekt
    private static void addPanel(JFrame frame, JPanel panel, JTextField textField, JButton button, JSpinner time, JLabel label){
        frame.add(panel);
        panel.add(Box.createVerticalStrut(60));
        panel.add(label);
        panel.add(Box.createVerticalStrut(30));
        panel.add(textField);
        panel.add(Box.createVerticalStrut(30));
        panel.add(time);
        panel.add(Box.createVerticalStrut(30));
        panel.add(button);
    }
}
