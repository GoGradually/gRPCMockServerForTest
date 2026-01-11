package me.gogradually.grpcmockserver.service.dto

data class SplitResult(
    val title: String,
    val split: List<SplitSchedule>
)
