package com.example.learntime

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.learntime.adapters.DelegateClickListener
import com.example.learntime.adapters.MainGridAdapter
import com.example.learntime.broadcastReceivers.BroadcastFragment
import com.example.learntime.databinding.FragmentMainBinding
import com.example.learntime.pojos.MainGridActions
import com.example.learntime.pojos.MainGridPojo
import com.example.learntime.services.ServiceFragment

class FragmentMain: Fragment(), DelegateClickListener {
    private var mBinding : FragmentMainBinding? = null
    private var mAdapter : MainGridAdapter? = null
    private var gridList : MutableList<MainGridPojo> = mutableListOf()

    companion object {
        const val TAG = "FragmentMain"
        fun newInstance() = FragmentMain()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentMainBinding.inflate(inflater)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = MainGridAdapter(gridList,this)
        mBinding?.mainGridRv?.apply {
            adapter = mAdapter
        }
        setupList()
    }

    private fun setupList() {
        val list = mutableListOf<MainGridPojo>()
        MainGridActions.entries.forEach {
            list.add(MainGridPojo(it.title,it.name))
        }
        mAdapter?.updateRecyclerView(list)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("Lifecycle", "$TAG, onDetach")
    }

    override fun onClick(data: Any?, id: String, extraData: Any?) {
        when(id) {
            MainGridActions.LIFECYLE.name -> {
                activity?.supportFragmentManager?.beginTransaction()?.add(R.id.fragment_parent,FragmentOne.newInstance())?.commit()
                activity?.supportFragmentManager?.beginTransaction()?.add(R.id.fragment_parent,FragmentTwo.newInstance())?.addToBackStack(FragmentTwo.TAG)?.commit()
            }
            MainGridActions.BROADCAST_FRAGMENT.name -> {
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_parent,
                    BroadcastFragment.newInstance())?.addToBackStack(
                    BroadcastFragment.TAG)?.commit()
            }
            MainGridActions.SERVICES_FRAGMENT.name -> {
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_parent, ServiceFragment.newInstance())?.addToBackStack(ServiceFragment.TAG)?.commit()
            }
            MainGridActions.GALLERY_FRAGMENT.name -> {
                activity?.supportFragmentManager?.beginTransaction()?.add(R.id.fragment_parent,FragmentTwo.newInstance())?.addToBackStack(FragmentTwo.TAG)?.commit()
            }
            MainGridActions.JOB_SCHEDULER.name -> {
                activity?.supportFragmentManager?.beginTransaction()?.add(R.id.fragment_parent,JobSchedulerFragment.newInstance())?.addToBackStack(JobSchedulerFragment.TAG)?.commit()
            }
        }
    }
}