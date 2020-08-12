package main;


import main.model.Task;
import main.model.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Date;


@Controller
public class DefaultController {

    @Autowired
    TodoRepository todoRepository;

    @RequestMapping("/")
    public String index(Model model){
        Iterable<Task> taskIterable = todoRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        for(Task task : taskIterable){
            tasks.add(task);
        }
        model.addAttribute("Tasks", tasks);
        model.addAttribute("TasksCount", tasks.size());
        return "index";
    }
}
