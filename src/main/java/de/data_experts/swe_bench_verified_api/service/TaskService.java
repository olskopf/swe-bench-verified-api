package de.data_experts.swe_bench_verified_api.service;

import de.data_experts.swe_bench_verified_api.model.Task;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class TaskService {
    private List<Task> tasks;

    @PostConstruct
    public void init() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = getClass().getClassLoader().getResourceAsStream("swe-bench-verified.json");
            tasks = mapper.readValue(is, new TypeReference<>() {});
            for (int i = 0; i < tasks.size(); i++) {
                tasks.get(i).setTaskNumber(i + 1);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error loading JSON", e);
        }
    }

    public Task getTaskByIndex(int index) {
        if (index < 1 || index > tasks.size()) {
            return null;
        }
        return tasks.get(index - 1);
    }
}
