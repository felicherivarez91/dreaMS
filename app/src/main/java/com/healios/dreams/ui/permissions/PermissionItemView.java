package com.healios.dreams.ui.permissions;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.healios.dreams.R;
import com.healios.dreams.databinding.ItemPermissionBinding;
import com.healios.dreams.model.PermissionModel;


public class PermissionItemView extends RelativeLayout implements CompoundButton.OnCheckedChangeListener {

    ItemPermissionBinding mBinding;

    private TextView permissionName;
    private Switch permissionStateSwitch;
    private Context context;

    public PermissionItemView(Context context) {
        super(context);
        this.init(context, null);
    }

    public PermissionItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init(context, attrs);
    }

    public PermissionItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(infService);
        mBinding = ItemPermissionBinding.inflate(layoutInflater, this, true);
    }

    public void setModel(PermissionModel permissionModel) {
        mBinding.setPermission(permissionModel);
        //manageStatus(permissionModel.getEnabled());
    }
    private void manageStatus(boolean permissionGranted) {
        if (permissionGranted) {
            mBinding.textViewItemPermissionPermissionNameText.setTextAppearance(context, R.style.AppTheme_PermissionText_Granted);
        } else {
            mBinding.textViewItemPermissionPermissionNameText.setTextAppearance(context, R.style.AppTheme_PermissionText_NotGranted);
        }
    }

    //region: <OnCheckedChangeListener>
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        manageStatus(b);
    }
    //endregion
}
