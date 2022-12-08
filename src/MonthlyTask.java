import java.time.LocalDate;
import java.time.LocalDateTime;

public class MonthlyTask extends Tasks {

    public MonthlyTask(String title, String task, LocalDateTime dateTime, TaskType taskType, Repeatability repeatability) {
        super(title, task, dateTime, taskType, Repeatability.MONTHLY);
    }

    public Repeatability getRepeatability() {
        return Repeatability.MONTHLY;
    }

    @Override
    public boolean tasksForDate(LocalDate localDate) {
        LocalDate taskDate = this.getDateTime().toLocalDate();
        return taskDate.equals(localDate) ||
                (taskDate.isBefore(localDate) && localDate.getDayOfMonth() == taskDate.getDayOfMonth());
        /*задача появляется в календаре если введенная дата равна поставленной дате
         ИЛИ введеная дата после поставленной даты И день месяца введенноя даты равен дню месяца поставленной даты (Ежемесячная)*/
    }
}
