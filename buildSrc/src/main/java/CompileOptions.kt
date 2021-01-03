import org.gradle.api.JavaVersion

object CompileOptions {
    val sourceCompatibility = JavaVersion.VERSION_1_6
    val targetCompatibility = JavaVersion.VERSION_1_6
    val jvmTarget = targetCompatibility.toString()
    val kotlinJdk = when {
        targetCompatibility.isJava7 -> "-jdk7"
        targetCompatibility.isJava8 -> "-jdk8"
        else -> String()
    }
}