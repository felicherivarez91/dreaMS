package com.healios.dreams.ui.challenge.motion

import com.healios.dreams.data.TestRepository
import com.healios.dreams.model.challenge.runtime.ChallengeRuntime
import com.healios.dreams.ui.challenge.ChallengeRuntimeViewModel
import com.healios.sourcetracker.TrackerService

class ChallengeMotionViewModel(workingData: ChallengeRuntime, testRepository: TestRepository,
                               trackerService: TrackerService) :
    ChallengeRuntimeViewModel(workingData, testRepository, trackerService) {


    override fun startChallenge() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}