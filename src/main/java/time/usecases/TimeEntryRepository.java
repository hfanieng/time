// usecases/TimeEntryRepository.java  (Interface der Domäne)

package time.usecases;

import time.entities.TimeEntry;
import java.util.List;

public interface TimeEntryRepository {
    void save(TimeEntry entry);
    List<TimeEntry> findAll();
    // Optional: weitere Methoden, z.B. findById, delete etc.
}