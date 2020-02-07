package com.healios.dreams.ui.permissions;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.healios.dreams.R;

public class PermissionActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FrameLayout frameLayout;

    private PermissionFragment permissionFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);

        setUpView();
    }

    private void setUpView() {
        attachView();
        loadInitialFragment();
    }

    private void attachView() {
        this.toolbar = findViewById(R.id.toolbar_activity_permissions);
        this.frameLayout = findViewById(R.id.frameLayout_activity_permissions);
    }

    private void loadInitialFragment() {

        permissionFragment = PermissionFragment.newInstance();
        permissionFragment.setArguments(getIntent().getExtras());

        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout_activity_permissions, permissionFragment).commit();
    }


}
