package de.data_experts.swe_bench_verified_api.controller;

import de.data_experts.swe_bench_verified_api.model.Task;
import de.data_experts.swe_bench_verified_api.service.TaskService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/index/{index}")
    public Task getByIndex(@PathVariable int index) {
        return taskService.getTaskByIndex(index);
    }
}
