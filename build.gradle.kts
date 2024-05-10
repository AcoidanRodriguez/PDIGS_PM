plugins {
    id("org.sonarqube") version "4.4.1.3373"
    id("com.android.library") version "8.1.2" apply false
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
}

sonar {
  properties {
    property("sonar.projectKey", "AcoidanRodriguez_PDIGS_PM")
    property("sonar.organization", "acoidanrodriguez-1")
    property("sonar.host.url", "https://sonarcloud.io")
  }
}
