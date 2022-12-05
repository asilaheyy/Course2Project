import java.time.LocalDate;
import java.util.*;

public class Service {

    private final Map<Integer, Tasks> tasks = new HashMap<>();

    public void addTask(Tasks task) {
        this.tasks.put(task.getId(), task);
    }

    public Collection<Tasks> getTasksForDay(LocalDate date) {
        List<Tasks> tasksForDay = new ArrayList<>();
        for (Tasks task : tasks.values()) {
            if (task.tasksForDate(date)) {
                tasksForDay.add(task);
            }
        }
        return tasksForDay;
    }

    public void removeTask(int id) {
        this.tasks.remove(id);
    }

}
