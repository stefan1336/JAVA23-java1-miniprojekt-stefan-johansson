import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class UserInterface {

    // JPanel för att gruppera komponenter, en typ av container
    JFrame frame = new JFrame();

    // Ny frame för information till varje veckodag?

    JLabel label1 = new JLabel();
    JLabel label2 = new JLabel();
    JLabel label3 = new JLabel();

    // Mina paneler
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel();
    JPanel panel5 = new JPanel();
    JPanel panel6 = new JPanel();
    JPanel panel7 = new JPanel();

    public UserInterface(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 800);
        frame.setLayout(new GridLayout(1,7));
//        frame.setLayout(new GridLayout());

//        frame.add(label1,BorderLayout.NORTH);
//        frame.add(label2,BorderLayout.CENTER);
//        frame.add(label3, BorderLayout.SOUTH);
//
//        // Sätter bakgrundsfärg för att se vad jag jobbar med
//        label1.setBackground(Color.blue);
//        label2.setBackground(Color.green);
//        label3.setBackground(Color.red);
////        frame.add(new Button("1"));
//
//        // Gör Mina labels synliga
//        label1.setOpaque(true);
//        label2.setOpaque(true);
//        label3.setOpaque(true);

        getWeekdays();

        frame.setVisible(true);

    }

    public void getWeekdays(){
        Day todaysDay;

        // Lite hjälp för att få ut dagarna
//        Color[] weekDayColors = {Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW, Color.CYAN, Color.MAGENTA, Color.ORANGE};
        Day[] todaysDays = Day.values();
        for (int i = 0; i < 7; i++){
            JPanel panel = new JPanel();
            panel.setBackground(Color.lightGray);
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.setLayout(new BorderLayout());

            // Lägga till Label för veckodagarna
            JLabel weekDayLabel = new JLabel(todaysDays[i].toString(), SwingConstants.CENTER);
            weekDayLabel.setForeground(Color.black);
            panel.add(weekDayLabel, BorderLayout.NORTH);

            frame.add(panel);
        }

    }


}
