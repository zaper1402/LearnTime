package com.example.learntime

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.learntime.databinding.FragmentTwoBinding

class FragmentTwo : Fragment() {
    private var mBinding : FragmentTwoBinding? = null
    private var mActivityViewModel : MainActivityViewModel? = null

    companion object {
        private const val PERMISSION_REQUEST_READ_EXTERNAL_STORAGE = 101
        const val TAG = "FragmentTwo"
        fun newInstance() = FragmentTwo()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Lifecycle", "$TAG, onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("Lifecycle", "$TAG, onCreateView")
        mBinding = FragmentTwoBinding.inflate(inflater)
        mActivityViewModel = ViewModelProvider(requireActivity())[MainActivityViewModel::class.java]
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Lifecycle", "$TAG, onViewCreated")
        mBinding?.tv1?.text = TAG

        setupClickListeners()
        setupObservers()
    }

    private fun setupClickListeners() {
        mBinding?.button?.setOnClickListener {
            mActivityViewModel?.makeReq1("temp data")
        }

        mBinding?.button2?.setOnClickListener {
            context?.let{
                if (ContextCompat.checkSelfPermission(it, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // Request the permission
                    activity?.let { it1 -> ActivityCompat.requestPermissions(it1, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), PERMISSION_REQUEST_READ_EXTERNAL_STORAGE) }
                } else {
                    // Permission has already been granted
                    childFragmentManager.beginTransaction().add(R.id.gallery_container,FragmentGallery()).addToBackStack(FragmentGallery.TAG).commitAllowingStateLoss()
                }
            }
        }
    }

    private fun setupObservers() {
        mActivityViewModel?.mainLiveData?.observe(viewLifecycleOwner) {
            Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_REQUEST_READ_EXTERNAL_STORAGE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permission was granted
                    childFragmentManager.beginTransaction().replace(R.id.gallery_container,FragmentGallery()).addToBackStack(FragmentGallery.TAG).commitAllowingStateLoss()
                } else {
                    // Permission denied. Handle the feature limitation accordingly.
                }
                return
            }
            else -> {
                // Ignore other requests.
            }
        }
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