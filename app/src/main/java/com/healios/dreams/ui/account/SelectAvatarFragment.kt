package com.healios.dreams.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.healios.dreams.databinding.FragmentSelectavatarBinding
import com.healios.dreams.di.SelectAvatarViewModelFactory
import com.healios.dreams.model.AvatarModel
import com.healios.dreams.util.EventObserver

interface AvatarRecyclerViewLister {
    fun onItemClick(position: Int, avatar: AvatarModel)
}

class SelectAvatarFragment : Fragment(), AvatarRecyclerViewLister {

    companion object {
        fun newInstance() = SelectAvatarFragment()
    }

    private lateinit var binding: FragmentSelectavatarBinding

    /*
    private val viewModel by lazy {
        ViewModelProvider(activity!!, SelectAvatarViewModelFactory()).get(
            SelectAvatarViewModel::class.java
        )
    }
     */
    private lateinit var viewModel: SelectAvatarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(
            activity!!,
            SelectAvatarViewModelFactory()
        ).get(SelectAvatarViewModel::class.java)

        binding = FragmentSelectavatarBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        bind()
        return binding.root
    }

    private fun bind() {

        binding.recyclerViewSelectAvatar.apply {
            val numberOfColumns = 2
            layoutManager =
                GridLayoutManager(context, numberOfColumns, RecyclerView.VERTICAL, false)
            val mAdapter = SelectAvatarRecyclerViewAdapter(
                viewModel.avatarList.value!!,
                this@SelectAvatarFragment
            )
            mAdapter.setHasStableIds(true)
            adapter = mAdapter
            addItemDecoration(SelectAvatarRecyclerViewItemDecoration(numberOfColumns, 20))
        }

        binding.toolbarSelectAvatar.setNavigationOnClickListener {
            val action =
                SelectAvatarFragmentDirections.actionSelectAvatarFragmentToPersonalInformationFragment(
                    0,
                    0
                )
            findNavController().navigate(action)
        }

        viewModel.avatarList.observe(viewLifecycleOwner, Observer {
            val adapter = binding.recyclerViewSelectAvatar.adapter
            if (adapter is SelectAvatarRecyclerViewAdapter) {
                adapter.setData(it)
            }
        })

        viewModel.selectedAvatar.observe(viewLifecycleOwner, Observer {
            val adapter = binding.recyclerViewSelectAvatar.adapter
            if (adapter is SelectAvatarRecyclerViewAdapter) {
                adapter.updateAvatar(it)
            }
        })

        viewModel.newAvatarSet.observe(viewLifecycleOwner, EventObserver {

            val action =
                SelectAvatarFragmentDirections.actionSelectAvatarFragmentToPersonalInformationFragment(
                    it.avatarCompleteImageResource,
                    it.avatarId
                )
            findNavController().navigate(action)
        })
    }

    // <AvatarRecyclerViewLister>
    override fun onItemClick(position: Int, avatar: AvatarModel) {
        viewModel.onItemChanged(position, avatar)
    }

}
