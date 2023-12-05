enum class LibType(val value: String) {
    DEFAULT("implementation"),
    TEST("testImplementation"),
    ANDROID_TEST("androidTestImplementation"),
    DEBUG("debugImplementation"),
    KSP("ksp")
}
