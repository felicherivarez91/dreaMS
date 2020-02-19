package com.healios.dreams.data;

import android.content.Context;

import com.healios.dreams.DreaMSApp;
import com.healios.dreams.R;
import com.healios.dreams.model.CountryModel;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CountryRepository {

    private static CountryRepository instance;


    public static CountryRepository getInstance(){
        if (instance == null)
            instance = new CountryRepository();

        return instance;
    }


    @Nullable
    public List<CountryModel> getCountries() {
        Context context = DreaMSApp.Companion.getInstance().getApplicationContext();


        ArrayList<CountryModel> countries = new ArrayList<>();
        countries.add(new CountryModel(CountryModel.SPAIN_COUNTRY_CODE, context.getResources().getString(R.string.all_spain)));
        countries.add(new CountryModel(CountryModel.SWITZERLAND_COUNTRY_CODE,context.getResources().getString(R.string.all_switzerland)));
        countries.add(new CountryModel(CountryModel.USA_COUNTRY_CODE,context.getResources().getString(R.string.all_usa)));
        countries.add(new CountryModel(CountryModel.NETHERLANDS_COUNTRY_CODE,context.getResources().getString(R.string.all_netherlands)));

        return countries;
    }
}
