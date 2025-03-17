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
                Task taskToBeAdded = new Task(taskList.size() + 1);

                String taskDescription = arguments[1];
                taskToBeAdded.addTask(taskDescription);
                taskList.add(taskToBeAdded);
            case "update":
                if (arguments.length != 3) {
                    System.out.println("invalid command 3: java TaskCliApp update <taskId> <task description>");
                    break;
                }
                int id = Integer.parseInt(arguments[1]);
                String updatedTaskDescription = arguments[2];

                taskList = fileManager.getTasksFromFile();
                Task taskToBeUpdated = taskList.get(id - 1);

                taskToBeUpdated.updateTask(id, updatedTaskDescription);
                System.out.println("Updated the task ID: " + id);
                break;
        }
        fileManager.writeTasks(taskList);
    }
}
