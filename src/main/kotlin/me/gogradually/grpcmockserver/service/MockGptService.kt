package me.gogradually.grpcmockserver.service

import kotlinx.coroutines.delay
import me.gogradually.grpcmockserver.domain.Schedule
import me.gogradually.grpcmockserver.service.dto.SplitResult
import me.gogradually.grpcmockserver.service.dto.SplitSchedule
import org.springframework.stereotype.Service

@Service
class MockGptService {
    suspend fun splitSchedule(
        title: String,
        content: String,
        expertise: String,
        finishedSchedule: List<Schedule>,
        notFinishedSchedule: List<Schedule> ): SplitResult {
        delay(10000)
        val split = listOf(
            "1. $title - Part 1: Introduction to Kotlin",
            "2. $title - Part 2: gRPC Basics",
            "3. $title - Part 3: Building a Mock Server"
        )
        return SplitResult(
            title = title,
            split = split.map {
                Schedule(
                    title = it,
                    description = "Description for $it",
                    steps = 3,
                    estimatedTime = 60
                )
            }
        )
    }
}