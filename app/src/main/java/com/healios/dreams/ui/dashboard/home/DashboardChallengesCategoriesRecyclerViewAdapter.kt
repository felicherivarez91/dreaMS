package com.healios.dreams.ui.dashboard.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.healios.dreams.databinding.ItemDashboardChallengeCategoryBinding

class DashboardChallengesCategoriesRecyclerViewAdapter:
    RecyclerView.Adapter<DashboardChallengesCategoriesRecyclerViewAdapter.DashboardChallengesCategoriesViewHolder>() {

    private var categoryList: List<Any>? = ArrayList(4)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DashboardChallengesCategoriesViewHolder {
        val binding = ItemDashboardChallengeCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DashboardChallengesCategoriesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        categoryList?.let {
            //FIXME: Uncomment
            return 4//it.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: DashboardChallengesCategoriesViewHolder, position: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        //holder.bind()
    }


    //region: View Holder
    inner class DashboardChallengesCategoriesViewHolder(var binding: ItemDashboardChallengeCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Any) {
            with(binding) {
                //TODO:

            }
        }

    }
    //endregion
}