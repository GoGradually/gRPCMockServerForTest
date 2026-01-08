package me.gogradually.grpcmockserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GRpcMockServerApplication

fun main(args: Array<String>) {
    runApplication<GRpcMockServerApplication>(*args)
}
