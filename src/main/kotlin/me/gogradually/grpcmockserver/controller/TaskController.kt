package me.gogradually.grpcmockserver.controller

import me.gogradually.grpcmockserver.controller.dto.TaskSplitRequest
import me.gogradually.grpcmockserver.domain.Task
import me.gogradually.grpcmockserver.service.MockGptService
import me.gogradually.grpcmockserver.service.dto.SplitResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TaskController(private val mockGptService: MockGptService) {
    @PostMapping("/tasks/split")
    suspend fun splitTasks(
        @RequestBody request: TaskSplitRequest,
    ): SplitResult {
        return mockGptService.splitTask(
            request.title,
            request.content,
            request.expertise,
            request.finishedTask,
            request.notFinishedTask
        )
    }
}