package com.healios.dreams.ui.permissions


import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.healios.dreams.R
import com.healios.dreams.databinding.FragmentPermissionsBinding
import com.healios.dreams.di.PermissionViewModelFactory
import com.healios.dreams.model.PermissionModel
import com.healios.dreams.util.managers.PermissionsManager
import com.healios.dreams.util.managers.PermissionsManager.*
import com.healios.dreams.util.managers.VersionCompatibilityManager


class PermissionFragment : Fragment(),PermissionRecyclerViewListener  {

    private val TAG = PermissionFragment::class.java.simpleName


    private var currentPermissionPosition: Int = 0
    private val viewModel by lazy {
        ViewModelProvider(activity!!, PermissionViewModelFactory()).get(
            PermissionViewModel::class.java)
    }

    private lateinit var binding: FragmentPermissionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPermissionsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        bind()
        return binding.root
    }

    private fun bind() {

        initView()

        binding.recyclerViewFragmentPermissions.apply {
            layoutManager = LinearLayoutManager(context)
            adapter =
                PermissionRecyclerViewAdapter(this@PermissionFragment)
        }

        viewModel.permissionList.observe(viewLifecycleOwner,
            Observer<List<PermissionModel>> { permissionModels ->
                val adapter = binding.recyclerViewFragmentPermissions.adapter
                if (adapter is PermissionRecyclerViewAdapter){
                    adapter.setPermissions(permissionModels)
                }
            })
    }

    private fun initView() {
        binding.textViewFragmentPermissionsTitle.text =
            this.context!!.getString(R.string.fragment_permissions_title_text)
        binding.textViewFragmentPermissionsExplanation.text =
            this.context!!.getString(R.string.fragment_permissions_explanation_text)
        binding.buttonFragmentPermissionsGetStarted.text =
            this.context!!.getString(R.string.fragment_permissions_get_started_button_text)
    }



    override fun onSwitchStatusChanged(itemView: PermissionItemView?, position: Int) {
        val permissionName = viewModel.permissionList.value!![position].name
        this.currentPermissionPosition = position
        when (position) {
            0 -> manageGoogleFitPermission()
            1 -> managePeakPermission()
            2 -> manageLocationPermission()
            3 -> manageExternalSensorsPermission()
            4 -> manageActivityRecognitionPermission()
            else -> {
            }
        }
    }

    private fun manageExternalSensorsPermission() {
        if (!PermissionsManager.isBodySensorPermissionGranted()) {
            PermissionsManager.askForBodySensorPermission(this)
        } else {
            Log.d(TAG, String.format("%s permission already granted", "Body sensors"))
        }
    }

    private fun manageActivityRecognitionPermission() {
        if (VersionCompatibilityManager.appIsCompatibleWithActivityRecognition()) {
            if (!PermissionsManager.isActivityRecognitionPermissionGranted()) {
                PermissionsManager.askForActivityRecognitionPermission(this)
            } else {
                Log.d(TAG, String.format("%s permission already granted", "Activity Recognition"))
            }
        }
    }

    private fun manageLocationPermission() {
        if (!PermissionsManager.isAccesFineLocationPermissionGranted()) {
            PermissionsManager.askForLocationPermission(this)
        } else {
            Log.d(TAG, String.format("%s permission already granted", "Location"))
        }
    }

    private fun managePeakPermission() {
        if (PermissionsManager.isPeakAppInstalled()) {
            Log.d(TAG, "PEAK is installed")
        }
    }

    private fun manageGoogleFitPermission() {
        //TODO:
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_ACTIVITY_RECOGNITION_REQUEST_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "Activity recognition permission granted")

                } else {
                    Log.d(TAG, "Activity recognition permission denied")
                }
                return
            }
            PERMISSION_ACCESS_FINE_LOCATION_REQUEST_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "Location permission granted")
                    viewModel.updatePermissionStatus(2, true)
                } else {
                    Log.d(TAG, "Location permission denied")
                    viewModel.updatePermissionStatus(2, false)
                }
                return
            }
            PERMISSION_BODY_SENSORS_REQUEST_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "Body sensor permission granted")
                    viewModel.updatePermissionStatus(3, true)

                } else {
                    Log.d(TAG, "Body sensors permission denied")
                    viewModel.updatePermissionStatus(3, false)
                }
                return
            }
            PERMISSION_CAMERA_REQUEST_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "Camera permission granted")

                } else {
                    Log.d(TAG, "Camera permission denied")
                }
                return
            }
            PERMISSION_RECORD_AUDIO_REQUEST_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "Audio record permission granted")
                } else {
                    Log.d(TAG, "Audio record permission denied")
                }
                return
            }
            else -> {
            }
        }
    }


}
