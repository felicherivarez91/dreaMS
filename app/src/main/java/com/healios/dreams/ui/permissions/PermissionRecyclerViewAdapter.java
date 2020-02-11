package com.healios.dreams.ui.permissions;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.healios.dreams.model.PermissionModel;

import java.util.List;

public class PermissionRecyclerViewAdapter extends RecyclerView.Adapter<PermissionRecyclerViewAdapter.PermissionViewHolder> {

    private List<PermissionModel> permissionList;

    private PermissionRecyclerViewListener listener;

    public PermissionRecyclerViewAdapter(PermissionRecyclerViewListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public PermissionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PermissionViewHolder(new PermissionItemView(parent.getContext()), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PermissionViewHolder holder, int position) {
        PermissionModel permissionModel = permissionList.get(position);
        holder.bindModel(permissionModel);
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
        this.notifyDataSetChanged();
    }
    //endregion


    //region: ViewHolder
    class PermissionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final String TAG = PermissionViewHolder.class.getSimpleName();
        private final PermissionRecyclerViewListener recyclerViewListener;

        private PermissionItemView permissionItemView;

        public PermissionViewHolder(@NonNull View itemView, PermissionRecyclerViewListener recyclerViewListener) {
            super(itemView);
            this.recyclerViewListener = recyclerViewListener;
            this.permissionItemView = (PermissionItemView) itemView;
            this.permissionItemView.setOnClickListener(this);
        }

        public void bindModel(PermissionModel permissionModel) {
            this.permissionItemView.setModel(permissionModel);
        }

        @Override
        public void onClick(View view) {
            this.recyclerViewListener.onSwitchStatusChanged(permissionItemView, getLayoutPosition());
        }
    }
    //endregion

}





