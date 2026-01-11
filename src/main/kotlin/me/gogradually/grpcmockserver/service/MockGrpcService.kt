package me.gogradually.grpcmockserver.service

import io.grpc.stub.StreamObserver
import me.gogradually.grpcmockserver.proto.MockGrpcServiceGrpc
import me.gogradually.grpcmockserver.proto.ScheduleGrpc
import me.gogradually.grpcmockserver.proto.ScheduleGrpcRequest
import me.gogradually.grpcmockserver.proto.ScheduleGrpcResponse
import me.gogradually.grpcmockserver.proto.SplitProto
import org.springframework.grpc.server.service.GrpcService

@GrpcService
class MockGrpcService: MockGrpcServiceGrpc.MockGrpcServiceImplBase() {
    override fun getSchedules(
        request: ScheduleGrpcRequest?,
        responseObserver: StreamObserver<ScheduleGrpcResponse?>?
    ) {
        val split1 = ScheduleGrpc.newBuilder()
            .setTitle("kotlin 기초 공부")
            .setDescription("kotlin 언어의 기초 문법과 개념을 공부한다")
            .build()

        val split2 = ScheduleGrpc.newBuilder()
            .setTitle("grpc 이해하기")
            .setDescription("grpc의 개념과 사용법을 이해한다")
            .build()

        val split3 = ScheduleGrpc.newBuilder()
            .setTitle("mock server 구현하기")
            .setDescription("grpc mock server를 구현하고 테스트한다")
            .build()

        val response = ScheduleGrpcResponse.newBuilder()
            .addFinishedSchedules(split1)
            .addAllNotFinishedSchedules(listOf(split2, split3))
            .build()

        responseObserver?.onNext(response)
        responseObserver?.onCompleted()
    }
}