import java.awt.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Scanner;

public class Main {
    private static final Service SCHEDULE = new Service();
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            remove(scanner);
                            break;
                        case 3:
                            showTasksForDay(scanner);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }

        Scanner scanner = new Scanner(System.in);
        SCHEDULE.addTask(new DailyTask("A", "A", LocalDateTime.now(), TaskType.PERSONAL, Repeatability.DAILY));
        SCHEDULE.addTask(new MonthlyTask("B", "A", LocalDateTime.now().plusHours(2), TaskType.WORK, Repeatability.MONTHLY));
        SCHEDULE.addTask(new YearlyTask("C", "A", LocalDateTime.now(), TaskType.PERSONAL, Repeatability.YEARLY));
        SCHEDULE.addTask(new OnceTask("D", "A", LocalDateTime.now().plusHours(4), TaskType.WORK, Repeatability.ONCE));



    }


    private static void remove(Scanner scanner) {
        for (Tasks tasks : SCHEDULE.getAllTasks()) {
            System.out.printf("%d. %s ",
                    tasks.getId(), tasks.getTitle());
        }
        while (true) {
            try {
                System.out.println("Выберите задачу для удаления");
                String idLine = scanner.nextLine();
                int id = Integer.parseInt(idLine);
                SCHEDULE.removeTask(id);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Введен неверный id задачи");
            }
        }
    }

    private static void showTasksForDay(Scanner scanner) { //Выводим все задачи на день
        LocalDate localDate = readDate(scanner);
        Collection<Tasks> tasksForDate = SCHEDULE.getTasksForDay(localDate);
        System.out.println("Задачи на " + localDate.format(DATE_FORMAT));
        for (Tasks tasks : tasksForDate) {
            System.out.printf("\t" + " ~%s~ " + "\t" + " %s: %s , %s %n",
                    taskTypeLocalization(tasks.getTaskType()),
                    tasks.getTitle(),
                    tasks.getDateTime().format(TIME_FORMAT),
                    tasks.getTask());
        }
    }

    private static LocalDate readDate(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Введите дату для задачи в формате dd.MM.yyyy: ");
                String dateline = scanner.nextLine();
                return LocalDate.parse(dateline, DATE_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("Введите дату в правильном формате");
            }
        }
    }

    private static LocalTime readTime(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Введите время для задачи в формате HH:mm: ");
                String timeline = scanner.nextLine();
                return LocalTime.parse(timeline, TIME_FORMAT);
            } catch (DateTimeException e) {
                System.out.println("Введите время в правильном формате");
            }
        }
    }


    private static String taskTypeLocalization(TaskType taskType) {
        return switch (taskType) {
            case WORK -> "Рабочая задача ";
            case PERSONAL -> "Личная задача ";
            default -> "Задача ";
        };
    }

    private static void printMenu() {
        System.out.println(
                """
                        1. Добавить задачу
                        2. Удалить задачу
                        3. Получить задачу на указанный день
                        0. Выход
                        """
        );
    }

    private static void inputTask(Scanner scanner) {
        System.out.print("Введите название задачи: ");
        String title = scanner.next();

    }
}




