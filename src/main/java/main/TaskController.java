package main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import response.Task;

import java.util.List;
import java.util.Map;

@RestController
public class TaskController {

    @PostMapping("/tasks")
    public static int addTask(Task task){
        TaskList.addTask(task);
        return task.getId();
    }

    @PostMapping("/tasks/{id}")
    public static ResponseEntity addTask(){
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
    }

    @GetMapping("/tasks")
    public static List<Task> getList(){
        return TaskList.getAllTask();
    }

    @GetMapping("/tasks/{id}")
    public static ResponseEntity getList(@PathVariable Integer id){
        if(TaskList.getTask(id) != null)
        return new ResponseEntity(TaskList.getTask(id), HttpStatus.OK);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping("/tasks/{id}")
    public static ResponseEntity edit(@RequestParam Map<String, String> params, @PathVariable Integer id){
        if(TaskList.getTask(id) != null) {
            for (Map.Entry entry : params.entrySet()) {
                switch (entry.getKey().toString()) {
                    case ("description"):
                        TaskList.getTask(id).setDescription(params.get("description"));
                        break;
                    case ("beginDate"):
                        TaskList.getTask(id).setBeginDate(params.get("beginDate"));
                        break;
                    case ("completionDate"):
                        TaskList.getTask(id).setCompletionDate(params.get("completionDate"));
                        break;
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/tasks")
    public static void deleteTasks(){
        TaskList.clearMap();
    }

    @DeleteMapping("/tasks/{id}")
    public static ResponseEntity deleteTask(@PathVariable Integer id){
        if(TaskList.getTask(id) != null) {
            TaskList.deleteTask(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
