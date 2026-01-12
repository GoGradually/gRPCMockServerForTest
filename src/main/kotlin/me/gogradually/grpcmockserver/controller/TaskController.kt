package me.gogradually.grpcmockserver.controller

import me.gogradually.grpcmockserver.domain.Task
import me.gogradually.grpcmockserver.service.MockGptService
import me.gogradually.grpcmockserver.service.dto.SplitResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TaskController(private val mockGptService: MockGptService) {
    @PostMapping("/tasks/split")
    suspend fun splitTasks(
        title: String,
        content: String,
        expertise: String,
        finishedTask: List<Task>,
        notFinishedTask: List<Task>
    ): SplitResult {
        return mockGptService.splitTask(title, content, expertise, finishedTask, notFinishedTask)
    }
}