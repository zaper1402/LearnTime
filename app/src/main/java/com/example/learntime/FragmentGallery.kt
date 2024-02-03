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
import com.example.learntime.databinding.FragmentGalleryBinding

class FragmentGallery : Fragment() {
    private var mBinding : FragmentGalleryBinding? = null
    private var galleryAdapter : GalleryAdapter? = null
    private var imageUris: MutableList<Uri> = mutableListOf()

    companion object {
        const val TAG = "FragmentGallery"
        @JvmStatic
        fun newInstance() = FragmentGallery()
    }

    @Deprecated("Deprecated in Java")
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        Log.d("Lifecycle", "${TAG}, onAttach")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Lifecycle", "${TAG}, onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentGalleryBinding.inflate(inflater)
        Log.d("Lifecycle", "${TAG}, onCreateView")
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Lifecycle", "${TAG}, onViewCreated")
        loadImages()
        galleryAdapter = GalleryAdapter(imageUris)
        mBinding?.galleryRv?.adapter = galleryAdapter
    }

    private fun loadImages() {
        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Images.Media._ID)
        val cursor: Cursor? = context?.contentResolver?.query(uri, projection, null, null, null)

        cursor?.use {
            val columnIndexID = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            while (cursor.moveToNext()) {
                val imageId: Long = cursor.getLong(columnIndexID)
                val imageUri: Uri = Uri.withAppendedPath(uri, "" + imageId)
                imageUris.add(imageUri)
            }
        }
        Log.d("Gallery","${imageUris.size}")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "${TAG}, onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "${TAG}, onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "${TAG}, onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "${TAG}, onStop")
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