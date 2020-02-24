package com.healios.dreams.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.healios.dreams.R
import com.healios.dreams.databinding.FragmentSelectavatarBinding
import com.healios.dreams.di.SelectAvatarViewModelFactory
import com.healios.dreams.model.AvatarModel

interface AvatarRecyclerViewLister {
    fun onItemClick(position: Int, avatar: AvatarModel)
}

class SelectAvatarFragment : Fragment(), AvatarRecyclerViewLister{


    companion object {
        fun newInstance() = SelectAvatarFragment()
    }

    private lateinit var binding: FragmentSelectavatarBinding

    private val viewModel by lazy {
        ViewModelProvider(activity!!, SelectAvatarViewModelFactory()).get(
            SelectAvatarViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectavatarBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        bind()
        return binding.root
    }

    private fun bind() {
        binding.recyclerViewSelectAvatar.apply {
            val numberOfRows = 2
            layoutManager = GridLayoutManager(context, numberOfRows,LinearLayoutManager.VERTICAL,false)
            itemAnimator = DefaultItemAnimator()
            adapter = SelectAvatarRecyclerViewAdapter(viewModel.avatarList.value!!, this@SelectAvatarFragment)
        }

        viewModel.avatarList.observe(viewLifecycleOwner, Observer {
            val adapter = binding.recyclerViewSelectAvatar.adapter
            if (adapter is SelectAvatarRecyclerViewAdapter){
                adapter.setData(it)
            }
        })

        viewModel.selectedAvatar.observe(viewLifecycleOwner, Observer {
            val adapter = binding.recyclerViewSelectAvatar.adapter
            if (adapter is SelectAvatarRecyclerViewAdapter){
                adapter.updateAvatar(it)
            }
        })

        viewModel.savedAvatar.observe(viewLifecycleOwner, Observer {
            val action = SelectAvatarFragmentDirections.actionSelectAvatarFragmentToPersonalInformationFragment(it.avatarCompleteImageResource, it.avatarId)
            findNavController().navigate(action)
        })
    }

    // <AvatarRecyclerViewLister>
    override fun onItemClick(position: Int, avatar: AvatarModel) {
        viewModel.onItemChanged(position, avatar)
    }

}
