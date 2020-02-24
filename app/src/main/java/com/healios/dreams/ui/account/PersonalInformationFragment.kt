package com.healios.dreams.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.healios.dreams.R
import com.healios.dreams.databinding.FragmentPersonalinformationBinding
import com.healios.dreams.di.PersonalInformationViewModelFactory
import com.healios.dreams.util.EventObserver
import com.healios.dreams.util.makeLinks

class PersonalInformationFragment : Fragment() {

    companion object {
        fun newInstance() = PersonalInformationFragment()
    }

    private lateinit var binding: FragmentPersonalinformationBinding

    private val viewModel by lazy {
        ViewModelProvider(activity!!, PersonalInformationViewModelFactory()).get(
            PersonalInformationViewModel::class.java
        )
    }

    private val args: PersonalInformationFragmentArgs by navArgs()

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

        viewModel.withArgs(args)

        bind()
        return binding.root
    }

    //endregion

    private fun bind() {

        binding.textViewPersonalInformationTermsAndConditionsText.text =
            HtmlCompat.fromHtml(
                getString(R.string.personalInformation_termsAndConditionsText),
                HtmlCompat.FROM_HTML_MODE_COMPACT
            )

        binding.textViewPersonalInformationTermsAndConditionsText.makeLinks(
            Pair(getString(R.string.personalInformation_termsAndConditionsLinkText),View.OnClickListener {
                findNavController().navigate(R.id.action_personalInformationFragment_to_termsAndConditionsFragment)
            })

            /*
            Pair("Terms and Conditions", View.OnClickListener {
                Toast.makeText(context, "Terms and Conditions Clicked", Toast.LENGTH_SHORT).show()
            }),
            Pair("Privacy Policy", View.OnClickListener {
                Toast.makeText(context, "Privacy Policy Clicked", Toast.LENGTH_SHORT).show()
            })
             */
        )

        binding.buttonPersonalInformationContinue.setOnClickListener {
            viewModel.continueButtonPressed(binding.editTextPersonalInformationNicknameText.text.toString())
        }

        binding.checkBoxPersonalInformationTermsAndConditionsCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.onCheckedChangeListener(isChecked)
        }

        viewModel.changeAvatarEvent.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(R.id.action_personalInformationFragment_to_selectAvatarFragment)
        })

        viewModel.acceptedNickname.observe(viewLifecycleOwner, EventObserver {
            val action =
                PersonalInformationFragmentDirections.actionPersonalInformationFragmentToScheduleFragment(
                    viewModel.avatarImageResource.value!!,
                    viewModel.nickname
                )
            findNavController().navigate(action)
        })

    }

}
