package com.example.learntime.services

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.Handler
import android.os.HandlerThread
import android.util.Log

/**
 * Prior to Android version Build.VERSION_CODES.S, jobs could only have a maximum of 100 jobs scheduled at a time.
 * Starting with Android version Build.VERSION_CODES.S, that limit has been increased to 150.
 * Expedited jobs also count towards the limit.
 *
 * In Android version Build.VERSION_CODES.LOLLIPOP, jobs had a maximum execution time of one minute.
 * Starting with Android version Build.VERSION_CODES.M and ending with Android version Build.VERSION_CODES.R, jobs had a maximum execution time of 10 minutes.
 * Starting from Android version Build.VERSION_CODES.S, jobs will still be stopped after 10 minutes if the system is busy or needs the resources, but if not, jobs may continue running longer than 10 minutes.
 *
 * Beginning with API 30 (Build.VERSION_CODES.R), JobScheduler will throttle runaway applications.
 * Calling schedule(android.app.job.JobInfo) and other such methods with very high frequency can have a high cost and so, to make sure the system doesn't get overwhelmed,
 * JobScheduler will begin to throttle apps, regardless of target SDK version.
 */
class MyJobService : JobService() {

    companion object {
        var count = 1
    }
    private val TAG = "MyJobService"

    /** Whenever the constraints are satisfied this will get fired.**/
    override fun onStartJob(params: JobParameters?): Boolean {
        val handlerThread = HandlerThread("SomeOtherThread")
        handlerThread.start()
        val handler = Handler(handlerThread.looper)
        handler.post(Runnable {
            Log.d(TAG, "Running Job $count")
            count++
            /** Marking Job Finished **/
            jobFinished(params, true)
        })

        /** True : Means the job will keep running in background don't release the resources and wake locks we'll notify you once the job is finished using [jobFinished]
            False : Means the job has finished release the resources and wake locks **/
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d(TAG, "onStopJob() was called")

        /** True : Means the job will start again when constraint are satisfied
            False : Means the job will end here and won't start again **/
        return count<5
    }
}