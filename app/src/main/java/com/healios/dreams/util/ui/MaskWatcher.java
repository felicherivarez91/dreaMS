package com.healios.dreams.util.ui;

import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;

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
        this.mask = this.countryModel != null ? this.countryModel.telephoneMask : "### ### ###";
    }
    //endregion


    public void setCountryModel(CountryModel countryModel) {
        this.countryModel = countryModel;
        this.mask = countryModel.telephoneMask;
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

    }



}
