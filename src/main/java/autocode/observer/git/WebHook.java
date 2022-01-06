package autocode.observer.git;

import java.util.List;

// hook - перехват
public interface WebHook {
    String branch();
    Event.Type type();
    List<Event> caughtEvents(); // пойманные ивенты
    void onEvent(Event event);
}
