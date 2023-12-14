package com.gamovation.core.ui.level.answers

enum class AlphabetType {
    Cyrillic,
    Latin
}

fun AlphabetType.determinateAlphabetSymbols(): List<String> {
    return when (this) {
        AlphabetType.Cyrillic -> defaultCyrillicAlphabet
        AlphabetType.Latin -> defaultLatinAlphabet
    }
}

fun determinateAlphabetType(language: String): AlphabetType {
    return when (language) {
        "ua" -> AlphabetType.Cyrillic
        "ru" -> AlphabetType.Cyrillic
        else -> AlphabetType.Latin
    }
}
