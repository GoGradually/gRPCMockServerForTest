package me.gogradually.grpcmockserver.service

import io.grpc.stub.StreamObserver
import me.gogradually.grpcmockserver.proto.TaskGrpcResponse
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class MockGrpcServiceTest {
    private class TestObserver : StreamObserver<TaskGrpcResponse?> {
        val responses = mutableListOf<TaskGrpcResponse?>()
        var completed = false
        private val latch = CountDownLatch(1)

        override fun onNext(p0: TaskGrpcResponse?) {
            if(p0 != null) responses.add(p0)
        }

        override fun onError(p0: Throwable?) {
            latch.countDown()
        }

        override fun onCompleted() {
            completed = true
            latch.countDown()
        }

        fun await(timeoutSec: Long = 1): Boolean {
            return latch.await(timeoutSec, TimeUnit.SECONDS)
        }
    }

    @Test
    fun `getTasks가 기대한 일정들을 반환한다`() {
        // given
        val service = MockGrpcService()
        val request = null // 요청이 필요 없으므로 null로 전달
        val observer = TestObserver()

        // when
        service.getTasks(request, observer)
        val completed = observer.await()

        // then
        assertTrue(completed, "Response was not completed in time")
        assertTrue(observer.completed, "StreamObserver did not complete")
        assertEquals(1, observer.responses.size, "Expected exactly one response")

        val response = observer.responses[0]
        assertNotNull(response, "Response should not be null")
        assertEquals(1, response!!.finishedTasksCount, "Expected one finished task")
        assertEquals(2, response.notFinishedTasksCount, "Expected two not finished tasks")

        val finishedTask = response.getFinishedTasks(0)
        assertEquals("kotlin 기초 공부", finishedTask.title)
        assertEquals("kotlin 언어의 기초 문법과 개념을 공부한다", finishedTask.description)

        val notFinishedTask1 = response.getNotFinishedTasks(0)
        assertEquals("grpc 이해하기", notFinishedTask1.title)
        assertEquals("grpc의 개념과 사용법을 이해한다", notFinishedTask1.description)

        val notFinishedTask2 = response.getNotFinishedTasks(1)
        assertEquals("mock server 구현하기", notFinishedTask2.title)
        assertEquals("grpc mock server를 구현하고 테스트한다", notFinishedTask2.description)
    }
}