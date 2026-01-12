package me.gogradually.grpcmockserver.service.dto


data class SplitTask(
    val title: String,
    val description: String,
    val steps: Int,
    val estimatedTime: Int
)