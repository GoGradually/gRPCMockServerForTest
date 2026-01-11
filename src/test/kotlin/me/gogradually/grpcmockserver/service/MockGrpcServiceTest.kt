package me.gogradually.grpcmockserver.service

import io.grpc.stub.StreamObserver
import me.gogradually.grpcmockserver.proto.ScheduleGrpcResponse
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class MockGrpcServiceTest {
    private class TestObserver : StreamObserver<ScheduleGrpcResponse?> {
        val responses = mutableListOf<ScheduleGrpcResponse?>()
        var completed = false
        private val latch = CountDownLatch(1)

        override fun onNext(p0: ScheduleGrpcResponse?) {
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
    fun `getSchedules가 기대한 일정들을 반환한다`() {
        // given
        val service = MockGrpcService()
        val request = null // 요청이 필요 없으므로 null로 전달
        val observer = TestObserver()

        // when
        service.getSchedules(request, observer)
        val completed = observer.await()

        // then
        assertTrue(completed, "Response was not completed in time")
        assertTrue(observer.completed, "StreamObserver did not complete")
        assertEquals(1, observer.responses.size, "Expected exactly one response")

        val response = observer.responses[0]
        assertNotNull(response, "Response should not be null")
        assertEquals(1, response!!.finishedSchedulesCount, "Expected one finished schedule")
        assertEquals(2, response.notFinishedSchedulesCount, "Expected two not finished schedules")

        val finishedSchedule = response.getFinishedSchedules(0)
        assertEquals("kotlin 기초 공부", finishedSchedule.title)
        assertEquals("kotlin 언어의 기초 문법과 개념을 공부한다", finishedSchedule.description)

        val notFinishedSchedule1 = response.getNotFinishedSchedules(0)
        assertEquals("grpc 이해하기", notFinishedSchedule1.title)
        assertEquals("grpc의 개념과 사용법을 이해한다", notFinishedSchedule1.description)

        val notFinishedSchedule2 = response.getNotFinishedSchedules(1)
        assertEquals("mock server 구현하기", notFinishedSchedule2.title)
        assertEquals("grpc mock server를 구현하고 테스트한다", notFinishedSchedule2.description)
    }
}