package com.healios.dreams.ui.permissions;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.healios.dreams.R;
import com.healios.dreams.model.PermissionModel;


public class PermissionItemView extends RelativeLayout implements CompoundButton.OnCheckedChangeListener {

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
        this.context = context;
        if (attrs != null) {
        }
        View root = inflate(getContext(), R.layout.item_permission, this);
        attachView(root);
        //setListeners();
    }

    private void attachView(View itemView) {
        this.permissionName = itemView.findViewById(R.id.textView_item_permission_permission_name_text);
        this.permissionStateSwitch = itemView.findViewById(R.id.switch_item_permission);
    }

    private void setListeners(){
        this.permissionStateSwitch.setOnCheckedChangeListener(this);
    }

    public void setModel(PermissionModel permissionModel) {
        this.permissionName.setText(permissionModel.getName());
        this.permissionStateSwitch.setChecked(permissionModel.getEnabled());
        manageStatus(permissionModel.getEnabled());
    }

    //region: <OnCheckedChangeListener>
    private void manageStatus(boolean permissionGranted) {
        if (permissionGranted) {
            this.permissionName.setTextAppearance(context, R.style.AppTheme_PermissionText_Granted);
        } else {
            this.permissionName.setTextAppearance(context, R.style.AppTheme_PermissionText_NotGranted);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        manageStatus(b);
    }
    //endregion
}
