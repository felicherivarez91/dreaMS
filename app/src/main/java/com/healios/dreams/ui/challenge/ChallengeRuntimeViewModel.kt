package com.healios.dreams.ui.challenge

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.healios.dreams.R
import com.healios.dreams.data.TestRepository
import com.healios.dreams.model.challenge.metadata.ChallengeMetadata
import com.healios.dreams.model.challenge.runtime.ChallengeInstanceData
import com.healios.dreams.model.challenge.runtime.ChallengeRuntime
import com.healios.dreams.util.*
import com.healios.sourcetracker.TrackerService

abstract class ChallengeRuntimeViewModel(val runtime: ChallengeRuntime,
                                         private val testRepository: TestRepository,
                                         val trackerService: TrackerService): ViewModel() {

    val challengeInstance: ChallengeInstanceData
    get() = runtime.instance

    val challengeMetadata: ChallengeMetadata
    get() = challengeInstance.metadata

    val challengeName: Int
    get() = challengeMetadata.name

    //region Events
    protected val _playBeepEvent = EmptyMutableLiveEvent()
    val playBeepEvent: EmptyLiveEvent = _playBeepEvent

    protected val _playVoiceTextEvent = MutableLiveEvent<Int>()
    val playVoiceTextEvent: LiveEvent<Int> = _playVoiceTextEvent

    protected val _finishChallengeEvent = MutableLiveEvent<ChallengeInstanceData>()
    val finishChallengeEvent: LiveEvent<ChallengeInstanceData> = _finishChallengeEvent

    protected val _cancelChallengeEvent = MutableLiveEvent<ChallengeInstanceData>()
    val cancelChallengeEvent: LiveEvent<ChallengeInstanceData> = _cancelChallengeEvent

    private val _communicationInProgress = MutableLiveData<Boolean>(false)
    val communicationInProgress: LiveData<Boolean> = _communicationInProgress
    //endregion

    open fun startChallenge() {
        runtime.instance.startTime = System.currentTimeMillis() / 1000
        //TODO: Start tracker Service
    }

    open fun finishChallenge() {
        playBeepEvent
        _playVoiceTextEvent.postValue(Event(R.string.challenge_speech_test_completed))
        //TODO: Stop tracker Service

        testRepository?.saveTest(challengeInstance)
    }




}