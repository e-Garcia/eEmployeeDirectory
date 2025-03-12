// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    /*
    * Note: Apply false is needed here to request gradle to not apply these dependencies to the
    * top level root project itself
    */
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.hilt.android) apply false
}