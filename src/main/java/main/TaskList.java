package main;

import response.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskList {

    private static HashMap<Integer, Task> taskMap = new HashMap<>();
    private static volatile int count = 1;

    public static int addTask(Task task){
        int id;

        synchronized (taskMap) {
            id = count++;
            taskMap.put(count++, task);
        }

        task.setId(id);
        return id;
    }

    public static int deleteTask(Integer id){
        taskMap.remove(id);
        return id;
    }

    public static List<Task> getAllTask(){
        ArrayList<Task> allTask = new ArrayList<>();
        allTask.addAll(taskMap.values());
        return allTask;
    }

    public static Task getTask(Integer id){
        return taskMap.get(id);
    }

    public static void clearMap(){
        taskMap.clear();
    }
}
