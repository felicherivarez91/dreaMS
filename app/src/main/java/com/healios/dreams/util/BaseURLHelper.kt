package com.healios.dreams.util

internal enum class BaseURLHelper(var raw: String) {
    LOCAL(""),
    DEV("https:///dnadmindev.dreamsconnect.app/api/v1/"),
    TEST(""),
    INTERNAL(""),
    PROD("")
}
