package com.healios.dreams.model

import com.healios.dreams.model.challenge.metadata.ChallengeCategoryMetadata

data class CategoryChallengesNavigationArgument(
    val selectedDay: Int,
    val category: ChallengeCategoryMetadata
) {}