package com.example.learntime.pojos

data class MainGridPojo (
    val title : String,
    val id : String
)

enum class MainGridActions(val title: String) {
    LIFECYLE("Lifecycle"),
    BROADCAST_FRAGMENT("Broadcasts"),
    SERVICES_FRAGMENT("Services"),
    GALLERY_FRAGMENT("Gallery"),
    JOB_SCHEDULER("Job Scheduler"),
}