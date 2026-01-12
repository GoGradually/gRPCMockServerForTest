package me.gogradually.grpcmockserver.controller.dto

import me.gogradually.grpcmockserver.domain.Task
import me.gogradually.grpcmockserver.domain.TaskScript

data class TaskSplitRequest(
    val title: String,
    val content: String,
    val expertise: String,
    val finishedTask: List<TaskScript>,
    val notFinishedTask: List<TaskScript>
)
