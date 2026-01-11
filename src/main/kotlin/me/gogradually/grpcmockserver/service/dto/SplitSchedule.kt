package me.gogradually.grpcmockserver.service.dto


data class SplitSchedule(
    val title: String,
    val description: String,
    val steps: Int,
    val estimatedTime: Int
)