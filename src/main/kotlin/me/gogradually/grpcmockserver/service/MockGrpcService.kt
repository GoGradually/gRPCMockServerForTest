package me.gogradually.grpcmockserver.service

import io.grpc.stub.StreamObserver
import me.gogradually.grpcmockserver.proto.MockGrpcServiceGrpc
import me.gogradually.grpcmockserver.proto.TaskGrpc
import me.gogradually.grpcmockserver.proto.TaskGrpcRequest
import me.gogradually.grpcmockserver.proto.TaskGrpcResponse
import me.gogradually.grpcmockserver.proto.SplitProto
import org.springframework.grpc.server.service.GrpcService

@GrpcService
class MockGrpcService: MockGrpcServiceGrpc.MockGrpcServiceImplBase() {
    override fun getTasks(
        request: TaskGrpcRequest?,
        responseObserver: StreamObserver<TaskGrpcResponse?>?
    ) {
        val split1 = TaskGrpc.newBuilder()
            .setTitle("kotlin 기초 공부")
            .setDescription("kotlin 언어의 기초 문법과 개념을 공부한다")
            .build()

        val split2 = TaskGrpc.newBuilder()
            .setTitle("grpc 이해하기")
            .setDescription("grpc의 개념과 사용법을 이해한다")
            .build()

        val split3 = TaskGrpc.newBuilder()
            .setTitle("mock server 구현하기")
            .setDescription("grpc mock server를 구현하고 테스트한다")
            .build()

        val response = TaskGrpcResponse.newBuilder()
            .addFinishedTasks(split1)
            .addAllNotFinishedTasks(listOf(split2, split3))
            .build()

        responseObserver?.onNext(response)
        responseObserver?.onCompleted()
    }
}