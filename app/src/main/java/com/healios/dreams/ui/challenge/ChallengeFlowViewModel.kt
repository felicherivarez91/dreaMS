package com.healios.dreams.ui.challenge

import com.healios.dreams.data.TestRepository
import com.healios.dreams.model.challenge.runtime.ChallengeFlowItem
import com.healios.dreams.model.challenge.runtime.ChallengeFlowRuntime
import com.healios.dreams.model.challenge.runtime.ChallengeRuntime
import com.healios.dreams.util.*
import com.healios.sourcetracker.TrackerService

class ChallengeFlowViewModel(workingData: ChallengeRuntime, testRepository: TestRepository,
                             trackerService: TrackerService
) : ChallengeRuntimeViewModel(workingData, testRepository, trackerService) {


    private val flowRuntime: ChallengeFlowRuntime
    get() =  runtime as ChallengeFlowRuntime

    private var flowCount: Int = 0

    private val currentFlow: ChallengeFlowItem
    get() = flowRuntime.items[flowCount]


    override fun startChallenge() {
        if (flowRuntime.items.count() == 0) return
        super.startChallenge()
    }

    fun nextFlowItem() {
        if(currentFlow.playBeepAtStart) {
            _playBeepEvent.postValue(Event(Unit))
        }

        currentFlow.startText?.let {
            _playVoiceTextEvent.postValue(Event(it))
        }

        currentFlow.sendToTrackerService?.let { sendToTrackerService ->
            sendToTrackerService(trackerService)
        }

    }

    fun finishFlowItem() {

        if(currentFlow.playBeepAtEnd) {
            _playBeepEvent.postValue(Event(Unit))
        }

        currentFlow.endText?.let {
            _playVoiceTextEvent.postValue(Event(it))
        }

        flowCount++

        if(flowCount < flowRuntime.items.count()) {
            nextFlowItem()
        }else {
            finishChallenge()
        }


    }
}