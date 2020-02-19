package com.healios.dreams.ui.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.healios.dreams.R;
import com.healios.dreams.databinding.ItemLoginCountrySelectorItemBinding;
import com.healios.dreams.model.CountryModel;

import java.util.List;

public class CountrySelectorRecyclerViewAdapter extends RecyclerView.Adapter<CountrySelectorRecyclerViewAdapter.CountrySelectorViewHolder> {

    private List<CountryModel> countryModelList;
    private CountrySelectorRecyclerViewListener listener;

    public CountrySelectorRecyclerViewAdapter(CountrySelectorRecyclerViewListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public CountrySelectorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_login_country_selector_item, parent, false);
        return new CountrySelectorViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CountrySelectorViewHolder holder, int position) {
        CountryModel countryModel = countryModelList.get(position);
        holder.bind(countryModel);
    }

    @Override
    public int getItemCount() {
        if (countryModelList != null)
            return countryModelList.size();

        return 0;
    }

    public void setData(List<CountryModel> countryModels) {
        this.countryModelList = countryModels;
        this.notifyDataSetChanged();
    }

    //region: View Holder
    class CountrySelectorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private CountrySelectorRecyclerViewListener listener;
        private ItemLoginCountrySelectorItemBinding binding;

        public CountrySelectorViewHolder(@NonNull View itemView, CountrySelectorRecyclerViewListener listener) {
            super(itemView);
            this.listener = listener;
            binding = ItemLoginCountrySelectorItemBinding.bind(itemView);
            itemView.setOnClickListener(this);
        }

        public void bind(CountryModel countryModel) {
            binding.setCountry(countryModel);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(view, getLayoutPosition());
        }
    }
    //endregion
}
