import org.gradle.api.JavaVersion

object Config {

    object Build {

        const val ENABLE_VIEW_BINDING = true
        const val MIN_SDK = 21
        const val TARGET_SDK = 34

        val javaVersion = JavaVersion.VERSION_17
        val javaVersionString = javaVersion.majorVersion
    }

    object App {

        const val PACKAGE_NAME = "com.braincorp.githandbook"
        const val VERSION_CODE = 17
        const val VERSION_NAME = "2024.1.0"
    }

    object Testing {

        const val TEST_RUNNER_NAME = "androidx.test.runner.AndroidJUnitRunner"
    }
}
