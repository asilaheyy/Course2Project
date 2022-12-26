import java.time.LocalDate;
import java.time.LocalDateTime;

public class Tasks {

    private final String title;
    private final String task;
    private final LocalDateTime dateTime;
    private final int id;
    public static int counter = 1;

    private final TaskType taskType;
    private Repeatability repeatability;


    public Tasks(String title, String task, LocalDateTime dateTime, TaskType taskType, Repeatability repeatability) {
        this.title = title;
        if (this.title == null || this.title.isEmpty()) {
            throw new IllegalArgumentException("Укажите заголовок.");
        }
        this.task = task;
        if (this.task == null || this.task.isEmpty()) {
            throw new IllegalArgumentException("Опишите задачу.");
        }
        this.id = counter++;
        this.dateTime = dateTime;
        this.taskType = taskType;
        if (this.taskType == null) {
            throw new IllegalArgumentException("Укажите тип задачи.");
        }
        this.repeatability = repeatability;
        if (this.repeatability == null) {
            throw new IllegalArgumentException("Укажите повторяемость.");
        }
    }

    public String getTitle() {
        return title;
    }

    public String getTask() {
        return task;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public boolean tasksForDate(LocalDate localDate){
        return localDate.equals(this.getDateTime().toLocalDate());}//выдает все задачи на дату

    public  Repeatability getRepeatability(){return repeatability;} //возвращает тип повтора

}
