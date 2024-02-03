package com.example.learntime

import android.annotation.SuppressLint
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context.JOB_SCHEDULER_SERVICE
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.learntime.databinding.FragmentJobSchedulerBinding
import com.example.learntime.services.MyJobService
import java.util.concurrent.TimeUnit


class JobSchedulerFragment : Fragment() {

    private var mBinding : FragmentJobSchedulerBinding? = null
    companion object {
        @JvmStatic
        fun newInstance() = JobSchedulerFragment()
        const val TAG = "JobSchedulerFragment"
        const val JOB_ID = 1012
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding =  FragmentJobSchedulerBinding.inflate(inflater)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupClicks()
    }

    private fun setupClicks() {
        mBinding?.jobScheduleBtn?.setOnClickListener {
            scheduleJob()
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun scheduleJob() {
        context?.apply {
            val interval: Long = TimeUnit.SECONDS.toMillis(1)

            val networkType = JobInfo.NETWORK_TYPE_ANY

            val jobInfo: JobInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                JobInfo.Builder(JOB_ID, ComponentName(this, MyJobService::class.java))
                    .setMinimumLatency(interval)
                    .setRequiredNetworkType(networkType)
                    .build()
            } else {
                JobInfo.Builder(JOB_ID, ComponentName(this, MyJobService::class.java))
                    .setPeriodic(interval)
                    .setRequiredNetworkType(networkType)
                    .build()
            }

            val jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as? JobScheduler
            jobScheduler?.schedule(jobInfo)
        }
    }
}