package com.healios.dreams.model.challenge.metadata

import com.healios.dreams.R

enum class ChallengeCategory(val description: Int) {

    MOTION(R.string.challenge_category_motion),
    FINEMOTORSKILLS(R.string.challenge_category_finemotor_skills),
    COGNITIVE(R.string.challenge_category_cognitive),
    VISIONANDSURVEYS(R.string.challenge_category_vision_and_surveys);


    companion object{
        fun categoryId(obj: ChallengeCategory): Long {
            return when(obj){
                ChallengeCategory.MOTION -> 1
                ChallengeCategory.FINEMOTORSKILLS -> 2
                ChallengeCategory.COGNITIVE -> 3
                ChallengeCategory.VISIONANDSURVEYS -> 4
            }
        }
    }
}