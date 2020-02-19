package com.healios.dreams.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.healios.dreams.R;

public class CountryModel {

    public static final int SPAIN_COUNTRY_CODE = 0034;
    public static final int SWITZERLAND_COUNTRY_CODE = 0041;
    public static final int USA_COUNTRY_CODE = 001;
    public static final int NETHERLANDS_COUNTRY_CODE = 0031;


    private int countryId;
    private String countryName;
    private String countryCode;
    private String telephoneCountryCode;
    private String telephoneMask;
    private int countryFlagImage;
    private boolean isSelectedCountry;


    public CountryModel(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.setCountryTelephoneMask();
        this.isSelectedCountry = false;
    }

    private void setCountryTelephoneMask() {

        switch (countryId) {
            case SPAIN_COUNTRY_CODE:
                this.countryCode = "ES";
                this.telephoneCountryCode = "+34";
                this.telephoneMask = String.format("%s %s", this.telephoneCountryCode, "###-###-###");
                this.countryFlagImage = R.drawable.ic_country_flag_es;
                break;
            case SWITZERLAND_COUNTRY_CODE:
                this.countryCode = "CH";
                this.telephoneCountryCode = "+41";
                this.telephoneMask = String.format("%s %s", this.telephoneCountryCode, "## ### ####");
                this.countryFlagImage = R.drawable.ic_country_flag_ch;
                break;
            case USA_COUNTRY_CODE:
                this.countryCode = "US";
                this.telephoneCountryCode = "+1";
                this.telephoneMask = String.format("%s %s", this.telephoneCountryCode, "###-###-####");
                //this.countryFlagImage = R.drawable.ic_country_flag_us;
                break;
            case NETHERLANDS_COUNTRY_CODE:
                this.countryCode = "NL";
                this.telephoneCountryCode = "+31";
                this.telephoneMask = String.format("%s %s", this.telephoneCountryCode, "6 ########");
                //this.countryFlagImage = R.drawable.ic_country_flag_nl;
                break;
            default:
                break;
        }
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getTelephoneMask() {
        return telephoneMask;
    }

    public void setTelephoneMask(String telephoneMask) {
        this.telephoneMask = telephoneMask;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getCountryFlagImage() {
        return countryFlagImage;
    }

    public void setCountryFlagImage(int countryFlagImage) {
        this.countryFlagImage = countryFlagImage;
    }

    public boolean isSelectedCountry() {
        return isSelectedCountry;
    }

    public void setSelectedCountry(boolean selectedCountry) {
        isSelectedCountry = selectedCountry;
    }

    public String getTelephoneCountryCode() {
        return telephoneCountryCode;
    }

    public void setTelephoneCountryCode(String telephoneCountryCode) {
        this.telephoneCountryCode = telephoneCountryCode;
    }

    @BindingAdapter({"android:src"})
    public static void setImageViewResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }
}
