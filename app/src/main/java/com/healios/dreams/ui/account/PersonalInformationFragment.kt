package com.healios.dreams.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.healios.dreams.R
import com.healios.dreams.databinding.FragmentPersonalinformationBinding

class PersonalInformationFragment : Fragment() {

    companion object {
        fun newInstance() = PersonalInformationFragment()
    }

    private lateinit var binding: FragmentPersonalinformationBinding
    private lateinit var viewModel: PersonalInformationViewModel

    //region: Lifecycle
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPersonalinformationBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        binding.editTextPersonalInformationNicknameText.addTextChangedListener {
            viewModel.onTextChanged(it.toString())
        }

        binding.buttonPersonalInformationContinue.setOnClickListener {
            viewModel.continueButtonPressed(binding.editTextPersonalInformationNicknameText.text.toString())
        }
        
        bind()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PersonalInformationViewModel::class.java)
        // TODO: Use the ViewModel
    }

    //endregion

    private fun bind() {

        binding.textViewPersonalInformationTermsAndConditionsText.text =
            HtmlCompat.fromHtml(getString(R.string.personalInformation_termsAndConditionsText),
            HtmlCompat.FROM_HTML_MODE_COMPACT)

    }

}
