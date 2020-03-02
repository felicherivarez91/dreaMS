package com.healios.dreams.ui.challenge.motion

import com.healios.dreams.ui.challenge.ChallengeFlowViewModel
import com.healios.dreams.ui.challenge.ChallengeFragment
import com.healios.dreams.util.EventObserver

abstract class ChallengeFlowFragment<T: ChallengeFlowViewModel>: ChallengeFragment<T>() {

    val flowViewModel: ChallengeFlowViewModel
    get() = viewModel

    override fun bind() {
        super.bind()
        flowViewModel.finishChallengeEvent.observe(this, EventObserver{
            //TODO Navigate to challenge completed
        })

        flowViewModel.cancelChallengeEvent.observe(this, EventObserver{
            //TODO Navigate to challenge challengeStop
        })

    }
}