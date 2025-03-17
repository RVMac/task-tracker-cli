import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private Path FILE_PATH = Path.of("tasks.json");

    public void writeTasks(List<Task> taskList) {

        String jsonForFileWrite = convertToJsonFormat(taskList);

        if (!Files.exists(FILE_PATH)){
            try {
                File file = new File("task.json");
                file.createNewFile();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Files.writeString(FILE_PATH, jsonForFileWrite);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String convertToJsonFormat(List<Task> taskList){
        StringBuilder sb = new StringBuilder();

        sb.append("[");

        for (int i = 0; i < taskList.size(); i++){
            sb.append(taskList.get(i));
            if (!(i + 1 == taskList.size())) {
                sb.append(",\n");
            }
        }

        sb.append("]");

        return sb.toString();
    }

    public List<Task> getTasksFromFile() {
        if (!Files.exists(FILE_PATH)){
            return new ArrayList<>();
        }

        try {
            String fileContent = Files.readString(FILE_PATH);
            if (fileContent.isEmpty()){
                return new ArrayList<>();
            }

            String[] taskSplitted = fileContent.replace("[","")
                    .replace("]","")
                    .replace(",\n", "")
                    .split("}");

            List<Task> taskLists = new ArrayList<>();

            for (String task : taskSplitted){
                String singleEntryTask = task.replace("{","").replace("\n","");
                int id = Integer.parseInt(singleEntryTask.split(",")[0].split(":")[1]);
                String taskDescription = singleEntryTask.split(",")[1].split(":")[1].replace("\"","").strip();
                String status = singleEntryTask.split(",")[2].split(":")[1].replace("\"","").strip();
                String createdAt = singleEntryTask.split(",")[3].split("[\"]:")[1].replace("\"","");
                String updatedAt = singleEntryTask.split(",")[4].split("[\"]:")[1].replace("\"","");

                Task taskFromFile = new Task(id);

                taskFromFile.readTask(taskDescription, status, createdAt, updatedAt);

                taskLists.add(taskFromFile);
            }

            System.out.println(taskLists);

            return taskLists;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void convertToTaskList(String rawStringFromFile) {
        try {
            String fileContent = Files.readString(FILE_PATH);
            String[] taskSplitted = fileContent.replace("[","")
                    .replace("]","")
                    .replace(",\n", "")
                    .split("}");

            List<String> taskLists = new ArrayList<>();

            for (String task : taskSplitted){
                String singleEntryTask = task.replace("{","").replace("\n","");
                taskLists.add(task);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
