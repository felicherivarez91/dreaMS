package com.healios.dreams.ui.permissions;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.healios.dreams.R;
import com.healios.dreams.databinding.FragmentPermissionsBinding;
import com.healios.dreams.model.PermissionModel;
import com.healios.dreams.util.managers.PermissionsManager;
import com.healios.dreams.util.managers.VersionCompatibilityManager;

import java.util.List;

import static com.healios.dreams.util.managers.PermissionsManager.PERMISSION_ACCESS_FINE_LOCATION_REQUEST_CODE;
import static com.healios.dreams.util.managers.PermissionsManager.PERMISSION_ACTIVITY_RECOGNITION_REQUEST_CODE;
import static com.healios.dreams.util.managers.PermissionsManager.PERMISSION_BODY_SENSORS_REQUEST_CODE;
import static com.healios.dreams.util.managers.PermissionsManager.PERMISSION_CAMERA_REQUEST_CODE;
import static com.healios.dreams.util.managers.PermissionsManager.PERMISSION_RECORD_AUDIO_REQUEST_CODE;

public class PermissionFragment_Java extends Fragment implements PermissionRecyclerViewListener {

    private final String TAG = PermissionFragment_Java.class.getSimpleName();

    private PermissionRecyclerViewAdapter_JAVA mAdapter;
    private PermissionViewModel_Java mViewModel;
    private int currentPermissionPosition;

    private FragmentPermissionsBinding binding;

    public static PermissionFragment_Java newInstance() {
        return new PermissionFragment_Java();
    }

    //region: Lifecycle
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_permissions, container, false);
        //setUpView(view);
        //return view;
        binding = FragmentPermissionsBinding.inflate(inflater, container, false);
        //binding.setViewmodel(mViewModel);


        bind();
        return binding.getRoot();
    }

    /*
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViewModel();
        initRecyclerView();
    }

     */
    //endregion

    private void bind() {
        initView();
        setListeners();
    }

    private void setListeners() {
        //this.getStartedButton.setOnClickListener(this);
    }

    private void initView() {
        binding.textViewFragmentPermissionsTitle.setText(this.getContext().getString(R.string.fragment_permissions_title_text));
        binding.textViewFragmentPermissionsExplanation.setText(this.getContext().getString(R.string.fragment_permissions_explanation_text));
        binding.buttonFragmentPermissionsGetStarted.setText(this.getContext().getString(R.string.fragment_permissions_get_started_button_text));
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(PermissionViewModel_Java.class);
        mViewModel.init();
        mViewModel.getPermissions().observe(this, new Observer<List<PermissionModel>>() {
            @Override
            public void onChanged(List<PermissionModel> permissionModels) {
                mAdapter.setPermissions(permissionModels);
                manageGetStartedButtonStatus(permissionModels);
            }
        });
    }

    private void manageGetStartedButtonStatus(List<PermissionModel> permissionModels) {
        for (PermissionModel permission: permissionModels) {
            if (!permission.getEnabled()){
                return;
            }
        }
        //this.getStartedButton.setEnabled(true);
    }

    private void initRecyclerView() {
        mAdapter = new PermissionRecyclerViewAdapter_JAVA(this);

        binding.recyclerViewFragmentPermissions.setAdapter(mAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        binding.recyclerViewFragmentPermissions.setLayoutManager(layoutManager);
        binding.recyclerViewFragmentPermissions.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setPermissions(mViewModel.getPermissions().getValue());
    }


    @Override
    public void onSwitchStatusChanged(PermissionItemView permissionItemView, int position) {
        String permissionName = mViewModel.getPermissions().getValue().get(position).getName();
        this.currentPermissionPosition = position;
        switch (position) {
            case 0:
                manageGoogleFitPermission();
                break;
            case 1:
                managePeakPermission();
                break;
            case 2:
                manageLocationPermission();
                break;
            case 3:
                manageExternalSensorsPermission();
                break;
            case 4:
                manageActivityRecognitionPermission();
                break;
            default:
                break;
        }
    }

    private void manageExternalSensorsPermission() {
        if (!PermissionsManager.isBodySensorPermissionGranted()) {
            PermissionsManager.askForBodySensorPermission(this);
        } else {
            Log.d(TAG, String.format("%s permission already granted", "Body sensors"));
        }
    }

    private void manageActivityRecognitionPermission() {
        if (VersionCompatibilityManager.appIsCompatibleWithActivityRecognition()) {
            if (!PermissionsManager.isActivityRecognitionPermissionGranted()) {
                PermissionsManager.askForActivityRecognitionPermission(this);
            } else {
                Log.d(TAG, String.format("%s permission already granted", "Activity Recognition"));
            }
        }
    }

    private void manageLocationPermission() {
        if (!PermissionsManager.isAccesFineLocationPermissionGranted()) {
            PermissionsManager.askForLocationPermission(this);
        } else {
            Log.d(TAG, String.format("%s permission already granted", "Location"));
        }
    }

    private void managePeakPermission() {
        if (PermissionsManager.isPeakAppInstalled()) {
            Log.d(TAG, "PEAK is installed");
        }
    }

    private void manageGoogleFitPermission() {
        //TODO:
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSION_ACTIVITY_RECOGNITION_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "Activity recognition permission granted");

                } else {
                    Log.d(TAG, "Activity recognition permission denied");
                }
                return;
            }
            case PERMISSION_ACCESS_FINE_LOCATION_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "Location permission granted");
                    mViewModel.updatePermissionStatus(2, true);
                } else {
                    Log.d(TAG, "Location permission denied");
                    mViewModel.updatePermissionStatus(2, false);
                }
                return;
            }
            case PERMISSION_BODY_SENSORS_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "Body sensor permission granted");
                    mViewModel.updatePermissionStatus(3, true);

                } else {
                    Log.d(TAG, "Body sensors permission denied");
                    mViewModel.updatePermissionStatus(3, false);
                }
                return;
            }
            case PERMISSION_CAMERA_REQUEST_CODE:{
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "Camera permission granted");

                } else {
                    Log.d(TAG, "Camera permission denied");
                }
                return;
            }
            case PERMISSION_RECORD_AUDIO_REQUEST_CODE:{
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "Audio record permission granted");
                } else {
                    Log.d(TAG, "Audio record permission denied");
                }
                return;
            }
            default:
                break;
        }
    }

}
