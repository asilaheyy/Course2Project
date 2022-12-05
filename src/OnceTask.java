import java.time.LocalDate;
import java.time.LocalDateTime;

public class OnceTask extends Tasks {

    public OnceTask(String title, String task, LocalDateTime dateTime, TaskType taskType, Repeatability repeatability) {
        super(title, task, dateTime, taskType, Repeatability.ONCE);
    }

    public Repeatability getRepeatability() {
        return Repeatability.ONCE;
    }

    @Override
    public boolean tasksForDate(LocalDate localDate) {
        return localDate.equals(this.getDateTime().toLocalDate());
        //если переданная дата равна дате задачи то задача появляется в календаре один раз (Одноразовая)
    }
}
