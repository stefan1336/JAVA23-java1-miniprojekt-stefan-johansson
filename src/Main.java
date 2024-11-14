import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Datum idag " + todaysDate);
        new UserInterface();
    }

}

// Enum för veckodagarna.
// Klass för min kalender
// Klass för Event där man skapar och ser event
// En klass för min JFrame och EventListener
// setToolTip <-- Använd
// Använda FlowLayout, BorderLayout eller GridLayout?

//Huvudklassen (Main): Här sätter du upp fönstret (JFrame), och det är härifrån programmet startas.
//GUI-klassen: Skapa en separat klass för att hantera all GUI-uppbyggnad (t.ex. lägga till knappar, paneler och andra komponenter). Denna klass ansvarar för att skapa och sätta upp fönstret och komponenterna.
//ActionListener-klasser: Om du har flera knappar med olika åtgärder kan du skapa en egen ActionListener-klass för varje grupp av åtgärder. För ett grundläggande projekt räcker det oftast att ha en ActionListener per knapp. Alternativt kan du skapa en enda ActionListener och använda en switch-sats för att hantera olika knapptryck.
// Inte för mycket logig i mina ActionListener.
//Hantera fönsterstorlek: Använd frame.pack() istället för att hårdkoda fönsterstorleken där det är möjligt. Detta anpassar fönstret till komponenterna och ser till att alla komponenter får plats.