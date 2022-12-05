import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Service SCHEDULE = new Service();
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SCHEDULE.addTask(new DailyTask("A", "A", LocalDateTime.now(), TaskType.PERSONAL, Repeatability.DAILY));
        SCHEDULE.addTask(new MonthlyTask("B", "A", LocalDateTime.now().plusHours(2), TaskType.WORK, Repeatability.MONTHLY));
        SCHEDULE.addTask(new YearlyTask("C", "A", LocalDateTime.now(), TaskType.PERSONAL, Repeatability.YEARLY));
        SCHEDULE.addTask(new OnceTask("D", "A", LocalDateTime.now().plusHours(4), TaskType.WORK, Repeatability.ONCE));
        showTasksForDay(scanner);

    }

    private static void showTasksForDay(Scanner scanner) { //Выводим все задачи на день
        LocalDate localDate = readDate(scanner);
        Collection<Tasks> tasksForDate = SCHEDULE.getTasksForDay(localDate);
        System.out.println("Задачи на " + localDate.format(DATE_FORMAT));
        for (Tasks tasks : tasksForDate) {
            System.out.printf("\t"+" ~%s~ "+"\t"+" %s: %s , %s %n",
                    taskTypeLocalization(tasks.getTaskType()),
                    tasks.getTitle(),
                    tasks.getDateTime().format(TIME_FORMAT),
                    tasks.getTask());
        }
    }

    private static LocalDate readDate(Scanner scanner){
        while (true) {
            try {
            System.out.print("Введите дату для задачи в формате dd.MM.yyyy: ");
            String dateline = scanner.nextLine();
            return LocalDate.parse(dateline, DATE_FORMAT);
            } catch (DateTimeParseException e){
                System.out.println("Введите дату в правильнпм формате");
            }
        }
    }

    private static String taskTypeLocalization(TaskType taskType){
        return switch (taskType){
            case WORK -> "Рабочая задача ";
            case PERSONAL -> "Личная задача ";
            default -> "Задача ";
        };

    }
}




