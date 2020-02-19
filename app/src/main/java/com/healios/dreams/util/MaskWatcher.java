package com.healios.dreams.util;

import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.healios.dreams.model.CountryModel;
import com.healios.dreams.ui.login.LoginViewModel;

import org.jetbrains.annotations.NotNull;


public class MaskWatcher extends PhoneNumberFormattingTextWatcher {

    private final String TAG = MaskWatcher.class.getSimpleName();
    private final LoginViewModel viewModel;

    private CountryModel countryModel;
    private String mask;

    private boolean isRunning = false;
    private boolean isDeleting = false;


    //region: Constructor
    public MaskWatcher(@NotNull LoginViewModel viewModel) {
        this.viewModel = viewModel;
        setupMaskFromCountry();
    }

    private void setupMaskFromCountry() {
        this.countryModel = viewModel.getSelectedCountry().getValue();
        this.mask = this.countryModel != null ? this.countryModel.getTelephoneMask() : "### ### ###";
    }
    //endregion


    public void setCountryModel(CountryModel countryModel) {
        this.countryModel = countryModel;
        this.mask = countryModel.getTelephoneMask();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
        isDeleting = count > after;
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable editable) {

        setupMaskFromCountry();

        if (isRunning || isDeleting) {
            viewModel.setPhoneFormatValid(isValidPhone(editable.toString()));
            return;
        }
        isRunning = true;

        int indexOf = mask.indexOf("#");

        int editableLength = editable.length();
        if (editableLength < mask.length() && editableLength > 0) {
            if (mask.charAt(editableLength-1) != '#') {
                if (editableLength -1 == 0){
                    editable.insert(editableLength - 1, mask, 0, indexOf);
                }else {
                    editable.insert(editableLength - 1, mask, editableLength - 1, editableLength);
                }
            }else if (mask.charAt(editableLength) != '#') {
                editable.append(mask.charAt(editableLength));
            }
        }

        isRunning = false;

        viewModel.setPhoneFormatValid(isValidPhone(editable.toString()));
    }

    private boolean isValidPhone(String phoneText) {
        final String ES_MASK = "^\\+34 \\d{3}-\\d{3}-\\d{3}$";
        final String CH_MASK = "\\+41 \\d{2} \\d{3} \\d{4}$";
        final String US_MASK = "^\\+1 \\d{3}-\\d{3}-\\d{4}$";
        final String NL_MASK = "\\+31 6 \\d{8}$";

        switch (countryModel.getCountryCode().toUpperCase()) {
            case "ES":
                return phoneText.matches(ES_MASK);
            case "CH":
                return phoneText.matches(CH_MASK);
            case "US":
                return phoneText.matches(US_MASK);
            case "NL":
                return phoneText.matches(NL_MASK);
            default:
                return true;
        }


    }


}
