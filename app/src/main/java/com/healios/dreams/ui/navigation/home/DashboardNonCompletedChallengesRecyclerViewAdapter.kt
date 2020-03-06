package com.healios.dreams.ui.navigation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.healios.dreams.databinding.ItemDashboardNonCompletedChallengeBinding
import com.healios.dreams.model.challenge.metadata.ChallengeMetadata

class DashboardNonCompletedChallengesRecyclerViewAdapter :
    RecyclerView.Adapter<DashboardNonCompletedChallengesRecyclerViewAdapter.DashboardNonCompletedChallengesViewHolder>() {

    private var nonCompletedChallenges: List<ChallengeMetadata>? = ArrayList()

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
            return it.size
        }
        return 0
    }

    override fun onBindViewHolder(
        holder: DashboardNonCompletedChallengesViewHolder,
        position: Int
    ) {
        val challengeMetadata = nonCompletedChallenges!![position]
        holder.bind(challengeMetadata)
    }


    fun setData(nonCompletedChallengesList: List<ChallengeMetadata>) {
        nonCompletedChallenges = nonCompletedChallengesList
        notifyDataSetChanged()
    }

    //region: ViewHolder
    inner class DashboardNonCompletedChallengesViewHolder(var binding: ItemDashboardNonCompletedChallengeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(challenge: ChallengeMetadata) {
            with(binding) {
                binding.challenge = challenge
            }
        }
    }
    //endregion
}