package me.gogradually.grpcmockserver.service

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MockGptServiceTest {
    val service = MockGptService()

    @Test
    fun `일정 쪼개기 API 테스트`() = runTest {
        //given
        val title = "kotlin 공부"
        val content = "kotlin 언어를 공부하고 grpc mock server를 만들어 본다"
        val expertise = "MIDDLE"
        val coroutineTime = currentTime

        //when
        val result = service.splitTask(title, content, expertise, listOf(), listOf())

        //then
        val elapsedTime = currentTime - coroutineTime
        assertThat(elapsedTime).isEqualTo(10000L)
        assertThat(result).isNotNull
        assertThat(result.title).isEqualTo(title)
        assertThat(result.split.size).isEqualTo(3)
    }
}