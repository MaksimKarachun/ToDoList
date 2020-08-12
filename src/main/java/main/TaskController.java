package main;

import main.model.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class TaskController {

    @Autowired
    private TodoRepository todoRepository;

    @PostMapping("/tasks")
    public int addTask(Task task){
        Task newTask = todoRepository.save(task);
        return newTask.getId();
    }

    @PostMapping("/tasks/{id}")
    public ResponseEntity addTask(){
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
    }

    @GetMapping("/tasks")
    public List<Task> getList(){
        ArrayList<Task> tasks = new ArrayList<>();
        todoRepository.findAll().forEach(tasks::add);
        return tasks;
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity getList(@PathVariable Integer id){
        return todoRepository.findById(id)
                .map(t -> new ResponseEntity(t, HttpStatus.OK))
                .orElse(new ResponseEntity(null, HttpStatus.NOT_FOUND));
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity edit(@RequestParam Map<String, String> params, @PathVariable Integer id){
        Optional<Task> taskOptional = todoRepository.findById(id);
        if(taskOptional.isPresent()) {
            Task task = taskOptional.get();
            for (Map.Entry entry : params.entrySet()) {
                switch (entry.getKey().toString()) {
                    case ("description"):
                        task.setDescription(params.get("description"));
                        break;
                    case ("beginDate"):
                        task.setBeginDate(params.get("beginDate"));
                        break;
                    case ("completionDate"):
                        task.setCompletionDate(params.get("completionDate"));
                        break;
                }
            }
            todoRepository.save(task);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/tasks")
    public void deleteTasks(){
        todoRepository.deleteAll();
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity deleteTask(@PathVariable Integer id){
        if(!todoRepository.findById(id).isPresent())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else {
            todoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }
}
