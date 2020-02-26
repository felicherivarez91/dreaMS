package com.healios.dreams.model

data class UniqueNicknameRequest(val nickname: String, val avatar: Int?, val consent: Boolean = true)