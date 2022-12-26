import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyTask extends Tasks {

    public DailyTask(String title, String task, LocalDateTime dateTime, TaskType taskType, Repeatability repeatability) {
        super(title, task, dateTime, taskType, Repeatability.DAILY);
    }

    public Repeatability getRepeatability() {
        return Repeatability.DAILY;
    }

    @Override
    public boolean tasksForDate(LocalDate localDate) {
        LocalDate taskDate = this.getDateTime().toLocalDate();
        return localDate.equals(taskDate) || localDate.isAfter(taskDate);
        /*задача появляется в календаре когда введенная дата равна дате задачи
         ИЛИ после даты постановки (Ежедневно)*/
    }
}
