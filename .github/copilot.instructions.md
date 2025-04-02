# Copilot Instructions for Clean Architecture Time Tracking Project

**Project Overview:**

This project implements a simple time-tracking application using Clean Architecture principles. The primary goal is to keep the architecture clean, maintainable, testable, and adaptable.

## Key Principles

- Separation of concerns: Clearly separated layers (Entities, Use Cases, Interfaces, Frameworks).
- Dependency inversion: Inner layers do not depend on outer layers.
- Framework agnostic: Core logic is independent from external frameworks, databases, or UI.

---

## Project Structure

```plaintext
project-root/
├── entities/         # Core business models and rules
├── usecases/         # Application-specific business logic
├── adapters/         # Interface adapters (repositories, controllers, presenters)
└── frameworks/       # Infrastructure implementations (databases, UI, APIs)
```

---

## Layer Responsibilities

- **Entities**
  - Define core business models (e.g., TimeEntry).
  - Contain domain-specific logic and validations.

- **Use Cases**
  - Contain business logic for specific operations (e.g., RecordTimeUseCase, ListTimeEntriesUseCase).
  - Interact only with abstractions (interfaces).

- **Adapters (Interface Adapters)**
  - Implement interfaces for input/output (Controllers, Presenters).
  - Implement gateways for data persistence (Repositories).

- **Frameworks & Drivers**
  - Contain specific implementations for external services (databases, REST APIs, CLI, UI frameworks).
  - Should not influence inner application layers directly.

---

## Coding Guidelines

### General Rules

- Always respect Clean Architecture principles.
- Keep classes and methods focused on a single responsibility (SRP).
- Clearly name packages, classes, methods based on business domain, not technical jargon.

### Entities

- Entities must be independent of external frameworks or technologies.
- Include validations directly within entities.

Example:

```java
public class TimeEntry {
    private LocalDateTime start;
    private LocalDateTime end;
    private String description;

    public TimeEntry(LocalDateTime start, LocalDateTime end, String description) {
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("End time must be after start time.");
        }
        this.start = start;
        this.end = end;
        this.description = description;
    }
}
```

---

### Use Cases

- Clearly define input and output through methods (execute(...)).
- Depend only on interfaces (no direct dependency on concrete implementations).

Example:

```java
public class RecordTimeUseCase {
    private final TimeEntryRepository repository;

    public RecordTimeUseCase(TimeEntryRepository repository) {
        this.repository = repository;
    }

    public TimeEntry execute(LocalDateTime start, LocalDateTime end, String description) {
        TimeEntry entry = new TimeEntry(start, end, description);
        repository.save(entry);
        return entry;
    }
}
```

---

### Adapters

- Implement interfaces defined in the inner layers.
- Convert data between external and internal formats.

Example (Repository):

```java
public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    private List<TimeEntry> entries = new ArrayList<>();

    public void save(TimeEntry entry) {
        entries.add(entry);
    }

    public List<TimeEntry> findAll() {
        return new ArrayList<>(entries);
    }
}
```

---

### Frameworks & Drivers

- Only here use concrete technologies and frameworks.
- Keep all external dependencies isolated.

Example (CLI):

```java
public class TimeTrackerCLI {
    public static void main(String[] args) {
        TimeEntryRepository repository = new InMemoryTimeEntryRepository();
        RecordTimeUseCase recordTime = new RecordTimeUseCase(repository);

        TimeEntry entry = recordTime.execute(
            LocalDateTime.now().minusHours(2),
            LocalDateTime.now(),
            "Clean Architecture session"
        );
        System.out.println("Time recorded: " + entry);
    }
}
```

---

## Development Best Practices

- Always write unit tests for Entities and Use Cases.
- Use Dependency Injection to easily swap implementations (e.g., mock repository for tests).
- Keep methods small and readable; prefer multiple short methods over one large method.
- Periodically review the architecture for compliance with Clean Architecture principles.

---

## Collaboration and Reviews

- Use this document as a reference for all team members.
- Ensure all new code adheres to the Clean Architecture principles outlined here.
- Conduct regular code reviews focusing on architecture adherence.
- Discuss any deviations from the principles and document the reasons.  
- Follow this instruction set closely during development.
- Use pull requests and code reviews to ensure adherence to these guidelines.
- Keep communication clear and focused on Clean Architecture principles.

---
