import java.util.ArrayList;
import java.util.List;

public class TaskCliApp {

    public static void main(String[] arguments) {
        FileManager fileManager = new FileManager();
        List<Task> taskList = new ArrayList<>();

        if (arguments.length < 1) {
            System.out.println("invalid command message 1: java TaskCliApp <argument>");
            return;
        }

        String command = arguments[0];

        switch (command) {
            case "add":
                if (arguments.length < 2) {
                    System.out.println("invalid command 2: java TaskCliApp add <task description>");
                    break;
                }

                taskList = fileManager.getTasksFromFile();

                String taskDescription = arguments[1];

                Task task = new Task(taskList.size() + 1);

                task.addTask(taskDescription);
                taskList.add(task);

                System.out.println(taskList.toString());
        }

        fileManager.writeTasks(taskList);
    }
}
