package com.healios.dreams.ui.dashboard.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.healios.dreams.R;
import com.healios.dreams.databinding.ItemDashboardNonCompletedChallengeBinding;

public class DashboardNonCompletedChallengesRecyclerViewAdapter extends RecyclerView.Adapter<DashboardNonCompletedChallengesRecyclerViewAdapter.DashboardNonCompletedChallengesViewHolder> {
    //TODO:


    @NonNull
    @Override
    public DashboardNonCompletedChallengesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dashboard_non_completed_challenge, parent, false);
        return new DashboardNonCompletedChallengesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardNonCompletedChallengesViewHolder holder, int position) {

        holder.bind();
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class DashboardNonCompletedChallengesViewHolder extends RecyclerView.ViewHolder {

        private ItemDashboardNonCompletedChallengeBinding binding;

        public DashboardNonCompletedChallengesViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemDashboardNonCompletedChallengeBinding.bind(itemView);

        }

        public void bind() {

        }
    }
}
