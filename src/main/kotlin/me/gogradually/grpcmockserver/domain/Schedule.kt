package me.gogradually.grpcmockserver.domain

class Schedule(
    title: String,
    description: String,
    steps: Int,
    estimatedTime: Int
) {
    var title: String = title
        private set
    var description: String = description
        private set
    var steps: Int = steps
        private set
    var estimatedTime: Int = estimatedTime
        private set


}