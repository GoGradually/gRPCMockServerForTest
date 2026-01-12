package me.gogradually.grpcmockserver.service.dto

import me.gogradually.grpcmockserver.domain.Task

data class SplitResult(
    val title: String,
    val split: List<Task>
)
