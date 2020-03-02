package com.healios.dreams.ui.permissions

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.healios.dreams.model.PermissionModel

class PermissionRecyclerViewAdapter(private var listener: PermissionRecyclerViewListener) :
    RecyclerView.Adapter<PermissionRecyclerViewAdapter.PermissionViewHolder>() {

    private var permissionList: List<PermissionModel>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PermissionViewHolder {
        return PermissionViewHolder(PermissionItemView(parent.context), listener)
    }

    override fun getItemCount(): Int {
        if (permissionList != null) {
            return this.permissionList!!.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: PermissionViewHolder, position: Int) {
        val permissionModel = permissionList!![position]
        holder.bindModel(permissionModel)
    }

    //region: Setters
    fun setPermissions(permissionModelList: List<PermissionModel>) {
        this.permissionList = permissionModelList
        this.notifyDataSetChanged()
    }
    //endregion


    //region: ViewHolder
    inner class PermissionViewHolder(
        itemView: View,
        private val recyclerViewListener: PermissionRecyclerViewListener
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val TAG = PermissionViewHolder::class.java.simpleName

        private val permissionItemView: PermissionItemView = itemView as PermissionItemView

        init {
            this.permissionItemView.setOnClickListener(this)
        }

        fun bindModel(permissionModel: PermissionModel) {
            this.permissionItemView.setModel(permissionModel)
        }

        override fun onClick(view: View) {
            this.recyclerViewListener.onSwitchStatusChanged(permissionItemView, layoutPosition)
        }
    }
    //endregion

}