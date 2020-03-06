package com.healios.dreams.ui.navigation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.healios.dreams.databinding.ItemDashboardChallengeCategoryBinding
import com.healios.dreams.model.challenge.metadata.ChallengeCategoryMetadata

class DashboardChallengesCategoriesRecyclerViewAdapter(private val listener: DashboardChallengesCategoriesRecyclerViewListener) :
    RecyclerView.Adapter<DashboardChallengesCategoriesRecyclerViewAdapter.DashboardChallengesCategoriesViewHolder>() {

    private var categoryList: List<ChallengeCategoryMetadata>? = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DashboardChallengesCategoriesViewHolder {
        val binding = ItemDashboardChallengeCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DashboardChallengesCategoriesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        categoryList?.let {
            return it.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: DashboardChallengesCategoriesViewHolder, position: Int) {
        val category = categoryList!![position]
        holder.bind(category)
    }

    fun setData(categories: List<ChallengeCategoryMetadata>) {
        categoryList = categories
        notifyDataSetChanged()
    }


    //region: View Holder
    inner class DashboardChallengesCategoriesViewHolder(var binding: ItemDashboardChallengeCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: ChallengeCategoryMetadata) {
            with(binding) {
                binding.category = category
                binding.relativeLayoutItemDashboardChallengeCategoryStartButton.setOnClickListener {
                    listener.onItemClick(layoutPosition, category)
                }
            }
        }
    }
    //endregion
}