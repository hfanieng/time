// frameworks/TimeTrackerCLI.java
package time.frameworks;

import time.adapters.InMemoryTimeEntryRepository;
import time.usecases.RecordTimeUseCase;
import time.usecases.ListTimeEntriesUseCase;
import time.entities.TimeEntry;
import java.time.LocalDateTime;
import java.util.List;

public class TimeTrackerCLI {
    public static void main(String[] args) {
        // 1. Initialisierung der Abhängigkeiten (DI)
        InMemoryTimeEntryRepository repository = new InMemoryTimeEntryRepository();
        RecordTimeUseCase recordTime = new RecordTimeUseCase(repository);
        ListTimeEntriesUseCase listTimes = new ListTimeEntriesUseCase(repository);

        // 2. Use Case "Zeit erfassen" aufrufen (Beispieldaten)
        System.out.println("Erfasse neuen Zeiteintrag...");
        TimeEntry neuerEintrag = recordTime.execute(
            LocalDateTime.of(2025, 4, 2, 7, 59),
            LocalDateTime.of(2025, 4, 1, 17, 0),
            "Implementierung Clean Architecture"
        );
        System.out.println("Neuer Eintrag gespeichert: " + neuerEintrag);

        // 3. Use Case "Zeiten anzeigen" aufrufen
        System.out.println("\nListe aller erfassten Einträge:");
        List<TimeEntry> eintraege = listTimes.execute();
        for (TimeEntry e : eintraege) {
            System.out.println(" - " + e);
        }
    }
}