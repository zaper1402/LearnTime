package com.example.learntime.services

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.learntime.databinding.ServicesLayoutBinding
import com.example.learntime.services.BoundedService.MyBinder


class ServiceFragment : Fragment() {
    private var mBinding : ServicesLayoutBinding? = null
    var mBoundService: BoundedService? = null
    var IMyAidlStub: IMyAidlInterface? = null
    var mServiceBound = false
    var mAIDLServiceBound = false

    companion object {
        const val TAG = "BroadcastAndServicesFragment"
        @JvmStatic
        fun newInstance() = ServiceFragment()
    }

    @Deprecated("Deprecated in Java")
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        Log.d("Lifecycle", "$TAG, onAttach")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Lifecycle", "$TAG, onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = ServicesLayoutBinding.inflate(inflater)
        Log.d("Lifecycle", "$TAG, onCreateView")
        return mBinding?.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Lifecycle", "$TAG, onViewCreated")
        setOnClick()
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "$TAG, onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "$TAG, onResume")
    }

    private fun setOnClick() {
        /** Foreground Service CLicks **/
        mBinding?.fgServiceStartButton?.setOnClickListener {
            startForegroundService()
        }
        mBinding?.fgServiceStopButton?.setOnClickListener {
            stopForegroundService()
        }

        /** Background Service CLicks **/
        mBinding?.bgServiceStartButton?.setOnClickListener {
            startBackgroundService()
        }
        mBinding?.bgServiceStopButton?.setOnClickListener {
            stopBackgroundService()
        }

        /** Bound Service CLicks **/
        mBinding?.boundServiceTimer?.setOnClickListener {
            if (mServiceBound) {
                mBinding?.boundServiceTimer?.text = mBoundService!!.timestamp
            }
        }
        mBinding?.boundServiceStartButton?.setOnClickListener {
            startBoundedService()
        }
        mBinding?.boundServiceStopButton?.setOnClickListener {
            stopBoundedService()
        }
        mBinding?.boundServiceBindButton?.setOnClickListener {
            bindService()
        }
        mBinding?.boundServiceUnbindButton?.setOnClickListener {
            unbindService()
        }

        /** Intent Service CLicks **/
        mBinding?.intentServiceStartButton?.setOnClickListener {
            startIntentService()
        }
        mBinding?.intentServiceStopButton?.setOnClickListener {
            stopIntentService()
        }

        /** AIDL Service CLicks **/
        mBinding?.aidlServiceBindButton?.setOnClickListener {
            bindAIDLService()
        }
        mBinding?.aidlServiceUnbindButton?.setOnClickListener {
            unbindAIDLService()
        }
        mBinding?.aidlServiceText?.setOnClickListener {
            if (mAIDLServiceBound) {
                mBinding?.aidlServiceText?.text = IMyAidlStub?.addition(4,2).toString()
            }
        }
    }

    /** Foreground Service Fn **/
    private fun startForegroundService() {
        val serviceIntent = Intent(context, ForegroundService::class.java)
        serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android")
        context?.let { ContextCompat.startForegroundService(it, serviceIntent) }
    }

    private fun stopForegroundService() {
        val serviceIntent = Intent(context, ForegroundService::class.java)
        activity?.stopService(serviceIntent)
    }

    /** Background Service Fn **/
    private fun startBackgroundService() {
        val serviceIntent = Intent(context, BackgroundService::class.java)
        serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android")
        activity?.startService(serviceIntent)
    }

    private fun stopBackgroundService() {
        val serviceIntent = Intent(context, BackgroundService::class.java)
        activity?.stopService(serviceIntent)
    }

    /** Bound  Service Fn **/
    private fun startBoundedService() {
        val serviceIntent = Intent(context, BoundedService::class.java)
        serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android")
        activity?.startService(serviceIntent)
    }

    private fun stopBoundedService() {
        val serviceIntent = Intent(context, BoundedService::class.java)
        activity?.stopService(serviceIntent)
    }

    private fun bindService() {
        val intent = Intent(context, BoundedService::class.java)
        activity?.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)
    }

    private fun unbindService() {
        if (mServiceBound) {
            activity?.unbindService(mServiceConnection);
            mServiceBound = false;
        }
    }

    /** Intent Service Fn **/
    private fun startIntentService() {
        val serviceIntent = Intent(context, MyIntentService::class.java)
        serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android")
        activity?.startService(serviceIntent)
    }

    private fun stopIntentService() {
        val serviceIntent = Intent(context, MyIntentService::class.java)
        activity?.stopService(serviceIntent)
    }

    /** AIDL service fn **/
    private fun bindAIDLService() {
        val intent = Intent(context, AIDLService::class.java)
        activity?.bindService(intent, mAIDLServiceConnection, Context.BIND_AUTO_CREATE)
    }

    private fun unbindAIDLService() {
        if (mAIDLServiceBound) {
            activity?.unbindService(mAIDLServiceConnection);
            mAIDLServiceBound = false;
        }
    }

    /** bound service connection **/
    private val mServiceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName) {
            mServiceBound = false
        }

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val myBinder = service as MyBinder
            mBoundService = myBinder.service
            mServiceBound = true
        }
    }

    /** AIDL service connection **/
    private val mAIDLServiceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName) {
            mAIDLServiceBound = false
        }

        override fun onServiceConnected(name: ComponentName, binder: IBinder) {
            val myBinder = IMyAidlInterface.Stub.asInterface(binder)
            IMyAidlStub = myBinder
            mAIDLServiceBound = true
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "$TAG, onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "$TAG, onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("Lifecycle", "$TAG, onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "$TAG, onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("Lifecycle", "$TAG, onDetach")
    }
}