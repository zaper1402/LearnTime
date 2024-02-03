package com.example.learntime.broadcastReceivers

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.learntime.databinding.FragmentBroadcastBinding


class BroadcastFragment : Fragment() {
    private var mBinding : FragmentBroadcastBinding? = null

    companion object {
        const val TAG = "BroadcastAndServicesFragment"
        @JvmStatic
        fun newInstance() = BroadcastFragment()
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
        mBinding = FragmentBroadcastBinding.inflate(inflater)
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
        /**Context Broadcast CLicks **/
        mBinding?.broadcastButton?.setOnClickListener {
            val intent = Intent()
            intent.action = BroadcastReceiverConstants.CONTEXT_REGISTERED_ACTION
            intent.putExtra(BroadcastReceiverConstants.DATA_STR, "Context broadcast received")
            activity?.sendBroadcast(intent)
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