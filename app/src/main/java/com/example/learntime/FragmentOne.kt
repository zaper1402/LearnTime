package com.example.learntime

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.learntime.databinding.FragmentOneBinding

class FragmentOne : Fragment() {
    private var mBinding : FragmentOneBinding? = null

    companion object {
        const val TAG = "FragmentOne"
        fun newInstance() = FragmentOne()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Lifecycle", "$TAG, onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("Lifecycle", "$TAG, onCreateView")
        mBinding = FragmentOneBinding.inflate(inflater)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Lifecycle", "$TAG, onViewCreated")

        setupClickListeners()
        setupObservers()
    }

    private fun setupClickListeners() {
    }

    private fun setupObservers() {
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "$TAG, onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "$TAG, onResume")
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