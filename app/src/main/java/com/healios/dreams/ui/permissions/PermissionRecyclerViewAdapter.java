package com.healios.dreams.ui.permissions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.healios.dreams.R;
import com.healios.dreams.model.PermissionModel;

import java.util.List;

public class PermissionRecyclerViewAdapter extends RecyclerView.Adapter {


    private List<PermissionModel> permissionList;
    private PermissionViewHolder permissionViewHolder;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        permissionViewHolder = new PermissionViewHolder(new PermissionItemView(parent.getContext()));
        return permissionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PermissionModel permissionModel = permissionList.get(position);
        permissionViewHolder.bindModel(permissionModel);
    }

    @Override
    public int getItemCount() {
        if (permissionList != null) {
            return permissionList.size();
        }
        return 0;
    }

    //region: Setters
    public void setPermissions(List<PermissionModel> permissionModelList) {
        this.permissionList = permissionModelList;
        //this.notifyDataSetChanged();
    }

    //endregion


    //region: ViewHolder
    class PermissionViewHolder extends RecyclerView.ViewHolder {

        private PermissionItemView permissionItemView;

        public PermissionViewHolder(@NonNull View itemView) {
            super(itemView);
            this.permissionItemView = (PermissionItemView) itemView;
        }

        public void bindModel(PermissionModel permissionModel) {
            this.permissionItemView.setModel(permissionModel);
        }
    }
    //endregion

}





