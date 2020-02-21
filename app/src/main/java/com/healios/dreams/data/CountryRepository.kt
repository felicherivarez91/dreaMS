package com.healios.dreams.data

import com.healios.dreams.model.CountryModel
import com.healios.dreams.util.JSONUtils


interface CountryRepository {

    fun getCountries() : List<CountryModel>

}


class JSONCountryRepository : CountryRepository{

    companion object {
        const val COUNTRY_FILENAME = "Countries.json"
    }

    override fun getCountries(): List<CountryModel> {
        return JSONUtils.getJsonDataFromAsset<List<CountryModel>>(COUNTRY_FILENAME) ?: ArrayList()
    }


}
