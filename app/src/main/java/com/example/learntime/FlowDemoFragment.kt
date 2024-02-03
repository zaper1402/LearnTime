package com.example.learntime

import android.app.Activity
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.learntime.databinding.FragmentFlowDemoBinding
import com.example.learntime.databinding.FragmentGalleryBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FlowDemoFragment : Fragment() {
    private var mBinding : FragmentFlowDemoBinding? = null
    private var flow : Flow<Int>? = null

    companion object {
        const val TAG = "FlowDemoFragment"
        @JvmStatic
        fun newInstance() = FlowDemoFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentFlowDemoBinding.inflate(inflater)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       setupClickListeners()
    }

    private fun setupClickListeners() {
        mBinding?.button1?.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                flowDemo1()
            }
        }
        mBinding?.button2?.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                flowDemo2()
            }
        }
    }

    private suspend fun flowDemo1() {
        /** Other builder operators to try
         * [flowOf] :  It is used to create flow from a given set of items.Eg-  flowOf(4, 2, 5, 1, 7)
         * [asFlow] : It is an extension function that helps to convert type into flows.Eg- (1..5).asFlow()
         * [channelFlow] : This builder creates flow with the elements using send provided by the builder itself.Eg - channelFlow {}
         **/
        flow  = flow {
            (0..10).forEach {
                emit(it)
            }
        }.flowOn(Dispatchers.Default)

    }

    private suspend fun flowDemo2() {
        flow?.filter {
            it % 2 == 0
        }?.map {
            it * it
        }?.collect {
            Log.d(TAG, it.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("Lifecycle", "${TAG}, onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "${TAG}, onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("Lifecycle", "${TAG}, onDetach")
    }
}