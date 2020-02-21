package com.healios.dreams.ui.account

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.healios.dreams.databinding.ItemSelectavatarBinding
import com.healios.dreams.model.AvatarModel

class SelectAvatarRecyclerViewAdapter(private var avatarList: List<AvatarModel>, private var listener: AvatarRecyclerViewLister) :
    Adapter<SelectAvatarRecyclerViewAdapter.SelectAvatarItemViewHolder>() {


    private lateinit var binding: ItemSelectavatarBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectAvatarItemViewHolder {
        binding = ItemSelectavatarBinding.inflate(LayoutInflater.from(parent.context))
        return SelectAvatarItemViewHolder(binding)
    }

    override fun getItemCount(): Int = avatarList.size

    override fun onBindViewHolder(holder: SelectAvatarItemViewHolder, position: Int) {
        val avatar = avatarList!![position]
        holder.bind(avatar)
    }

    fun setData(avatarList: List<AvatarModel>){
        this.avatarList = avatarList
        this.notifyDataSetChanged()
    }

    fun updateAvatar(it: AvatarModel?) {

        val selectedAvatarPosition = avatarList.indexOf(it)

        avatarList.forEachIndexed { index, avatarModel ->
            if (avatarModel == it){
                avatarList[selectedAvatarPosition].isSelected = it.isSelected
            }else{
                avatarList[index].isSelected = false
            }
            this.notifyItemChanged(index)
        }
    }

    inner class SelectAvatarItemViewHolder(val binding: ItemSelectavatarBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(avatar: AvatarModel) {
            if (avatar.avatarResource != 0)
                binding.avatar = avatar

            binding.cardViewItemSelectAvatar.setOnClickListener {
                listener.onItemClick(layoutPosition, avatar)
            }
        }

    }
}