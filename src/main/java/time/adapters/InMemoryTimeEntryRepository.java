// adapters/InMemoryTimeEntryRepository.java
package time.adapters;

import time.usecases.TimeEntryRepository;
import time.entities.TimeEntry;
import java.util.ArrayList;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    private List<TimeEntry> storage = new ArrayList<>();

    @Override
    public void save(TimeEntry entry) {
        // einfache Speicherung in einer Liste
        // hier könnte auch eine ID vergeben werden, z.B. entry.setId(...)
        storage.add(entry);
    }

    @Override
    public List<TimeEntry> findAll() {
        return new ArrayList<>(storage);
    }
}