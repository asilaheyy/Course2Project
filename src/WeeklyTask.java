import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeeklyTask extends Tasks {

    public WeeklyTask(String title, String task, LocalDateTime dateTime, TaskType taskType, Repeatability repeatability) {
        super(title, task, dateTime, taskType, Repeatability.WEEKLY);
    }

    public Repeatability getRepeatability(){
        return Repeatability.WEEKLY;
    }

    @Override
    public boolean tasksForDate(LocalDate localDate) {
        LocalDate taskDate = getDateTime().toLocalDate();
        return localDate.equals(taskDate) || (localDate.isAfter(taskDate)) && (localDate.getDayOfWeek().equals(taskDate.getDayOfWeek()));
        /*задача будет появлятся в календаре если введенная дата равна дате задачи ИЛИ если введенная дата
         после поставленной даты И день недели введенной даты совпадает с днем недели поставленной задачи (Еженедельно)*/
    }
}
