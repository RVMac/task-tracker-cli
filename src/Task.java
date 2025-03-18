import java.time.LocalDateTime;

public class Task {
    private int id;
    private String taskDescription;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task(int id){
        this.id = id;
    }

    public void addTask(String taskDescription){
        this.taskDescription = taskDescription;
        this.status = "Todo";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        System.out.println("Task added successfully (ID: " + id + ")");
    }

    @Override
    public String toString(){
        return "{" +
                "\"id\":" + id + "," +
                "\"task\":\"" + taskDescription + "\"," +
                "\"status\":\"" + status + "\"," +
                "\"createdAt\":\"" + createdAt + "\"," +
                "\"updatedAt\":\"" + updatedAt +
                "\"}";
    }

    public void readTask(String taskDescription, String status, String createdAt, String updatedAt) {
        this.taskDescription = taskDescription;
        this.status = status;
        this.createdAt = LocalDateTime.parse(createdAt);
        this.updatedAt = LocalDateTime.parse(updatedAt);
    }

    public void updateTask(int id, String updatedTaskDescription) {
        this.id = id;
        this.taskDescription = updatedTaskDescription;
        this.updatedAt = LocalDateTime.now();
    }

    public int getId() {
        return this.id;
    }
}
