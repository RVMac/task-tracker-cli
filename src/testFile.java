import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class testFile {
    private static Path FILE_PATH = Path.of("tasks.json");
    public static void main(String[] args){
        try {
            String fileContent = Files.readString(FILE_PATH);
            String[] taskSplitted = fileContent.replace("[","")
                    .replace("]","")
                    .replace(",\n", "")
                    .split("}");

            List<String> taskLists = new ArrayList<>();

            for (String task : taskSplitted){
                String singleEntryTask = task.replace("{","").replace("\n","");
                int id = Integer.parseInt(singleEntryTask.split(",")[0].split(":")[1]);
                String taskDescription = singleEntryTask.split(",")[1].split(":")[1].replace("\"","").strip();
                String status = singleEntryTask.split(",")[2].split(":")[1].replace("\"","").strip();
                String createdAt = singleEntryTask.split(",")[3].split("[\"]:")[1].replace("\"","");
                String updatedAt = singleEntryTask.split(",")[4].split("[\"]:")[1].replace("\"","");
            }

            System.out.println(taskLists);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
