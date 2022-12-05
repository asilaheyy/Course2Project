import java.time.LocalDate;
import java.time.LocalDateTime;

public class YearlyTask extends Tasks {

    public YearlyTask(String title, String task, LocalDateTime dateTime, TaskType taskType, Repeatability repeatability) {
        super(title, task, dateTime, taskType, Repeatability.YEARLY);
    }

    public Repeatability getRepeatability() {
        return Repeatability.YEARLY;
    }

    @Override
    public boolean tasksForDate(LocalDate localDate) {
        LocalDate taskDate = this.getDateTime().toLocalDate();
        return localDate.equals(taskDate) || (localDate.isAfter(taskDate) &&
                localDate.getDayOfMonth() == taskDate.getDayOfMonth() &&
                localDate.getMonth().equals(taskDate.getMonth()));
        /*задача появляется в календаре когда введенная дата равна поставленной дате
        ИЛИ введенная дата идет после поставленной даты И день месяца введенной даты равен дню месаця поставленной даты
        И введенный месяц равен поставленному месяцу (Ежегодно)
         */
    }
}
