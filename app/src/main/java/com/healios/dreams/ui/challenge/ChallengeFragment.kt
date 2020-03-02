package com.healios.dreams.ui.challenge

import androidx.fragment.app.Fragment
import com.healios.dreams.util.EventObserver

abstract class ChallengeFragment<T: ChallengeRuntimeViewModel> : Fragment() {

    protected abstract val viewModel: T

    open fun bind() {

        viewModel.playVoiceTextEvent.observe(this, EventObserver {
          //TODO PLay voice
        })

        viewModel.playBeepEvent.observe(this, EventObserver {
            //TODO PLay beep
        })
    }
}