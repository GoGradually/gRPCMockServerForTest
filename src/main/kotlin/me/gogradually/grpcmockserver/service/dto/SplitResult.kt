package me.gogradually.grpcmockserver.service.dto

import me.gogradually.grpcmockserver.domain.Schedule

data class SplitResult(
    val title: String,
    val split: List<Schedule>
)
