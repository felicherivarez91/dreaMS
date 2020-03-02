package com.healios.dreams.ui.dashboard.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.healios.dreams.databinding.ItemDashboardNonCompletedChallengeBinding
import com.healios.dreams.model.Test

class DashboardNonCompletedChallengesRecyclerViewAdapter :
    RecyclerView.Adapter<DashboardNonCompletedChallengesRecyclerViewAdapter.DashboardNonCompletedChallengesViewHolder>() {

    private var nonCompletedChallenges: List<Test>? = ArrayList(5)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DashboardNonCompletedChallengesViewHolder {
        val binding = ItemDashboardNonCompletedChallengeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DashboardNonCompletedChallengesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        nonCompletedChallenges?.let {
            //FIXME: Uncomment
            return 5//it.size
        }
        return 0
    }

    override fun onBindViewHolder(
        holder: DashboardNonCompletedChallengesViewHolder,
        position: Int
    ) {
        //val test = nonCompletedChallenges!![position]
        //holder.bind(test)
    }


    fun setData(nonCompletedChallengesList: List<Test>?) {
        nonCompletedChallenges = nonCompletedChallengesList
        notifyDataSetChanged()
    }

    //region: ViewHolder
    inner class DashboardNonCompletedChallengesViewHolder(var binding: ItemDashboardNonCompletedChallengeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(test: Test?) {
            //TODO:
            with(binding) {

            }
        }
    }
    //endregion
}