package com.healios.dreams.ui.permissions;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.healios.dreams.R;
import com.healios.dreams.model.PermissionModel;

import java.util.List;

public class PermissionFragment extends Fragment {

    private TextView titleTextView;
    private TextView explanationTextView;
    private RecyclerView permissionRecyclerView;
    private Button getStartedButton;


    private PermissionRecyclerViewAdapter mAdapter;
    private PermissionViewModel mViewModel;

    public static PermissionFragment newInstance() {
        return new PermissionFragment();
    }

    //region: Lifecycle
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_permissions, container, false);
        setUpView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViewModel();
        initRecyclerView();
    }
    //endregion

    private void setUpView(View view) {
        attachView(view);
    }

    private void attachView(View view) {

        this.titleTextView = view.findViewById(R.id.textView_fragment_permissions_title);
        this.explanationTextView = view.findViewById(R.id.textView_fragment_permissions_explanation);
        this.permissionRecyclerView = view.findViewById(R.id.recyclerView_fragment_permissions);
        this.getStartedButton = view.findViewById(R.id.button_fragment_permissions_get_started);
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(PermissionViewModel.class);
        mViewModel.init();
        mViewModel.getPermissions().observe(this, new Observer<List<PermissionModel>>() {
            @Override
            public void onChanged(List<PermissionModel> permissionModels) {
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initRecyclerView() {
        mAdapter = new PermissionRecyclerViewAdapter();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        permissionRecyclerView.setLayoutManager(layoutManager);
        permissionRecyclerView.setItemAnimator(new DefaultItemAnimator());

        permissionRecyclerView.setAdapter(mAdapter);

        mAdapter.setPermissions(mViewModel.getPermissions().getValue());
    }


}
