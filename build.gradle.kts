plugins {
  id("org.sonarqube") version "4.4.1.3373"
}

sonar {
  properties {
    property("sonar.projectKey", "AcoidanRodriguez_PDIGS_PM")
    property("sonar.organization", "acoidanrodriguez-1")
    property("sonar.host.url", "https://sonarcloud.io")
  }
}
