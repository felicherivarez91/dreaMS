package com.healios.dreams.data.challenge

import com.healios.dreams.R
import com.healios.dreams.model.challenge.metadata.Challenge
import com.healios.dreams.model.challenge.metadata.ChallengeCategory
import com.healios.dreams.model.challenge.metadata.ChallengeIntensity
import com.healios.dreams.model.challenge.metadata.ChallengeMetadata
import java.lang.IllegalArgumentException

object ChallengeBuilder {
    //TODO: Add icons, videos peak settings
    fun build(challenge: Challenge): ChallengeMetadata {
        return when(challenge) {
            Challenge.TWOMINWALK -> ChallengeMetadata(
                id = 1,
                name = R.string.challenge_dreams_2minwalk,
                icon = R.drawable.ic_challenge_2minwalk,
                category = ChallengeCategory.MOTION,
                intensity = ChallengeIntensity.MEDIUM,
                time = "",
                description = R.string.challenge_dreams_2minwalk_description,
                codeTest = "HEA-MOV-2MW",
                descriptionVideo = R.raw.heamov2mw
            )
            Challenge.MUSICALCHAIRS -> ChallengeMetadata(
                id = 2,
                name = R.string.challenge_dreams_musical_chairs,
                icon = R.drawable.ic_challenge_musical_chairs,
                category = ChallengeCategory.MOTION,
                intensity = ChallengeIntensity.HIGH,
                time = "",
                description = R.string.challenge_dreams_musical_chairs_description,
                codeTest = "HEA-MOV-MUCH"
            )
            Challenge.WOBBLER -> ChallengeMetadata(
                id = 3,
                name = R.string.challenge_dreams_wobbler,
                icon = 0,
                category = ChallengeCategory.MOTION,
                intensity = ChallengeIntensity.MEDIUM,
                time = "",
                description = R.string.challenge_dreams_wobbler_description,
                codeTest = "HEA-MOV-WOB"
            )
            Challenge.TIGHTROPE -> ChallengeMetadata(
                id = 4,
                name = R.string.challenge_dreams_tight_rope,
                icon = R.drawable.ic_challenge_tight_rope,
                category = ChallengeCategory.MOTION,
                intensity = ChallengeIntensity.MEDIUM,
                time = "",
                description = R.string.challenge_dreams_tight_rope_description,
                codeTest = "HEA-MOV-TIRO"
            )
            Challenge.CLIMBINGSTAIRS -> ChallengeMetadata(
                id = 5,
                name = R.string.challenge_dreams_climbing_stairs,
                icon = 0,
                category = ChallengeCategory.MOTION,
                intensity = ChallengeIntensity.MEDIUM,
                time = "",
                description = R.string.challenge_dreams_climbing_stairs_description,
                codeTest = "HEA-MOV-CLST"
            )
            Challenge.UTURN -> ChallengeMetadata(
                id = 6,
                name = R.string.challenge_dreams_uturn,
                icon = R.drawable.ic_challenge_u_turn,
                category = ChallengeCategory.MOTION,
                intensity = ChallengeIntensity.MEDIUM,
                time = "",
                description = R.string.challenge_dreams_uturn_description,
                codeTest = "HEA-MOV-UTRN"
            )
            Challenge.CATCHACLOUD -> ChallengeMetadata(
                id = 9,
                name = R.string.challenge_dreams_catch_a_cloud,
                icon = R.drawable.ic_challenge_catch_a_cloud,
                category = ChallengeCategory.FINEMOTORSKILLS,
                intensity = ChallengeIntensity.LOW,
                time = "",
                description = R.string.challenge_dreams_catch_a_cloud_description,
                codeTest = "HEA-MOT-CACL"
            )
            Challenge.SCREENTONOSE -> ChallengeMetadata(
                id = 10,
                name = R.string.challenge_dreams_s2n,
                icon = R.drawable.ic_challange_screen_to_nose,
                category = ChallengeCategory.FINEMOTORSKILLS,
                intensity = ChallengeIntensity.LOW,
                time = "",
                description = R.string.challenge_dreams_s2n_description,
                codeTest = "HEA-MOT-S2N"
            )
            Challenge.PEAK_FLDO -> ChallengeMetadata(
                id = 13,
                name = R.string.challenge_peak_fldo,
                icon = R.drawable.ic_challenge_flame_dodge_left_hand_peak,
                category = ChallengeCategory.FINEMOTORSKILLS,
                intensity = ChallengeIntensity.MEDIUM,
                time = "",
                description = null,
                codeTest = "PEA-MOT-FLDO"
            )
            Challenge.PEAK_FLDOR -> ChallengeMetadata(
                id = 14,
                name = R.string.challenge_peak_fldor,
                icon = R.drawable.ic_challenge_flame_dodge_right_hand_peak,
                category = ChallengeCategory.FINEMOTORSKILLS,
                intensity = ChallengeIntensity.MEDIUM,
                time = "",
                description = null,
                codeTest = "PEA-MOT-FLDOr"
            )
            Challenge.SDMT -> ChallengeMetadata(
                id = 15,
                name = R.string.challenge_dreams_sdmt,
                icon = R.drawable.ic_challenge_sdmt,
                category = ChallengeCategory.COGNITIVE,
                intensity = ChallengeIntensity.HIGH,
                time = "",
                description = R.string.challenge_dreams_sdmt_description,
                codeTest = "HEA-COG-mSDMT"
            )
            Challenge.PEAK_WOHU -> ChallengeMetadata(
                id = 17,
                name = R.string.challenge_peak_wohu,
                icon = 0,
                category = ChallengeCategory.COGNITIVE,
                intensity = ChallengeIntensity.HIGH,
                time = "",
                description = null,
                codeTest = "PEA-COG-WOHU"
            )
            Challenge.PEAK_SPCY -> ChallengeMetadata(
                id = 18,
                name = R.string.challenge_peak_spcy,
                icon = R.drawable.ic_challenge_peak_spin_cycle,
                category = ChallengeCategory.COGNITIVE,
                intensity = ChallengeIntensity.HIGH,
                time = "",
                description = null,
                codeTest = "PEA-COG-SPCY"
            )
            Challenge.PEAK_ZAGA -> ChallengeMetadata(
                id = 19,
                name = R.string.challenge_peak_zaga,
                icon = R.drawable.ic_challenge_peak_zap_gap,
                category = ChallengeCategory.COGNITIVE,
                intensity = ChallengeIntensity.HIGH,
                time = "",
                description = null,
                codeTest = "PEA-COG-ZAGA"
            )
            Challenge.PEAK_FASW -> ChallengeMetadata(
                id = 20,
                name = R.string.challenge_peak_fasw,
                icon = R.drawable.ic_challenge_peak_face_switch,
                category = ChallengeCategory.COGNITIVE,
                intensity = ChallengeIntensity.HIGH,
                time = "",
                description = null,
                codeTest = "PEA-COG-RUBA"
            )
            Challenge.PEAK_RUBA -> ChallengeMetadata(
                id = 21,
                name = R.string.challenge_peak_ruba,
                icon = R.drawable.ic_challenge_peak_rush_back,
                category = ChallengeCategory.COGNITIVE,
                intensity = ChallengeIntensity.HIGH,
                time = "",
                description = null,
                codeTest = "PEA-COG-RUBA")
            Challenge.PEAK_BACL -> ChallengeMetadata(
                id = 22,
                name = R.string.challenge_peak_bacl,
                icon = R.drawable.ic_challenge_peak_baggage_claim,
                category = ChallengeCategory.COGNITIVE,
                intensity = ChallengeIntensity.HIGH,
                time = "",
                description = null,
                codeTest = "PEA-COG-BACL")
            Challenge.PEAK_PEPA -> ChallengeMetadata(
                id = 23,
                name = R.string.challenge_peak_pepa,
                icon = R.drawable.ic_challenge_peak_perilous_path,
                category = ChallengeCategory.COGNITIVE,
                intensity = ChallengeIntensity.HIGH,
                time = "",
                description = null,
                codeTest = "PEA-COG-PEPA")
            Challenge.PEAK_PUBL -> ChallengeMetadata(
                id = 24,
                name = R.string.challenge_peak_publ,
                icon = R.drawable.ic_challenge_peak_puzzle_box,
                category = ChallengeCategory.COGNITIVE,
                intensity = ChallengeIntensity.HIGH,
                time = "",
                description = null,
                codeTest = "PEA-COG-PUBL")
            Challenge.PEAK_MUSO -> ChallengeMetadata(
                id = 25,
                name = R.string.challenge_peak_muso,
                icon = R.drawable.ic_challenge_peak_must_sort,
                category = ChallengeCategory.COGNITIVE,
                intensity = ChallengeIntensity.HIGH,
                time = "",
                description = null,
                codeTest = "PEA-COG-MUSO")
            Challenge.PEAK_LOPO -> ChallengeMetadata(
                id = 26,
                name = R.string.challenge_peak_lopo,
                icon = R.drawable.ic_challenge_peak_low_pop,
                category = ChallengeCategory.COGNITIVE,
                intensity = ChallengeIntensity.HIGH,
                time = "",
                description = null,
                codeTest = "PEA-COG-LOPO")
            Challenge.ACUITY -> ChallengeMetadata(
                id = 27,
                name = R.string.challenge_dreams_vision_acuity,
                icon = R.drawable.ic_challenge_surve_vision_acuity,
                category = ChallengeCategory.VISIONANDSURVEYS,
                intensity = ChallengeIntensity.LOW,
                time = "",
                description = R.string.challenge_dreams_vision_acuity_description,
                codeTest = "HEA-VIS-VIAC")
            Challenge.CONTRAST -> ChallengeMetadata(
                id = 28,
                name = R.string.challenge_dreams_vision_contrast,
                icon = R.drawable.ic_challenge_surve_vision_contrast,
                category = ChallengeCategory.VISIONANDSURVEYS,
                intensity = ChallengeIntensity.LOW,
                time = "",
                description = R.string.challenge_dreams_vision_contrast_description,
                codeTest = "HEA-VIS-VICO")
            Challenge.MSIS -> ChallengeMetadata(
                id = 29,
                name = R.string.challenge_dreams_scale_msis,
                icon = 0,
                category = ChallengeCategory.VISIONANDSURVEYS,
                intensity = ChallengeIntensity.LOW,
                time = "",
                description = R.string.challenge_dreams_scale_msis_description,
                codeTest = "HEA-SUR-MSIS29")
            Challenge.MSWS -> ChallengeMetadata(
                id = 30,
                name = R.string.challenge_dreams_scale_msws,
                icon = 0,
                category = ChallengeCategory.VISIONANDSURVEYS,
                intensity = ChallengeIntensity.LOW,
                time = "",
                description = R.string.challenge_dreams_scale_msws_description,
                codeTest = "HEA-SUR-MSWS12")
            Challenge.FSS -> ChallengeMetadata(
                id = 31,
                name = R.string.challenge_dreams_scale_fss,
                icon = 0,
                category = ChallengeCategory.VISIONANDSURVEYS,
                intensity = ChallengeIntensity.LOW,
                time = "",
                description = R.string.challenge_dreams_scale_msws_description,
                codeTest = "HEA-SUR-MSWS12")
            Challenge.MSST -> ChallengeMetadata(
                id = 32,
                name = R.string.challenge_dreams_scale_msst,
                icon = 0,
                category = ChallengeCategory.VISIONANDSURVEYS,
                intensity = ChallengeIntensity.LOW,
                time = "",
                description = R.string.challenge_dreams_scale_msst_description,
                codeTest = "HEA-SUR-SYMTR")
            else -> throw IllegalArgumentException("$challenge no implemented")
        }
    }
}