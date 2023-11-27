enum class LibType(val value: String) {
    DEFAULT("implementation"),
    TEST("testImplementation"),
    ANNOTATION_PROCESSOR("annotationProcessor"),
    ANDROID_TEST("androidTestImplementation"),
    DEBUG("debugImplementation"),
    KSP("ksp")
}
