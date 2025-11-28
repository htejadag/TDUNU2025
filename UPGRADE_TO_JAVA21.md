# Upgrade to Java 21 — Steps and notes

This repository was updated to target Java 21 (LTS) and Spring Boot 3.3.8 in several modules. The following steps were performed:

- Set `java.version` to `21` across multiple module POMs
- Upgraded `spring-boot-starter-parent` to `3.3.8` where applicable
- Added `maven-compiler-plugin` with `<release>${java.version}</release>`
- Added `maven-enforcer-plugin` to require Java >= 21 for each module
- Migrated `javax.persistence.*` imports to `jakarta.persistence.*` in `MsCaja` entities

IMPORTANT: You need to install JDK 21 on your system and point `JAVA_HOME` to it before building. The project will fail to compile if the JDK is older (for example Java 17).

## Steps to install and configure JDK 21 (Windows)

1. Download JDK 21 (Microsoft, Eclipse Adoptium or other vendor). Example (Microsoft Temurin):
   - https://aka.ms/download-jdk/microsoft-jdk-21.0.8-windows-x64.zip

2. Extract it to a folder, e.g. `C:\Program Files\Java\jdk-21` or `C:\Users\<yourUser>\.jdk21`.

3. Set environment variables (PowerShell example):

```powershell
setx JAVA_HOME "C:\Program Files\Java\jdk-21" -m
setx PATH "%JAVA_HOME%\bin;%PATH%" -m
```

4. Open a new shell and verify:

```powershell
java -version
mvn -v
```

Both should show Java 21 (and your maven will use that default JDK).

## Build the modules (per module)

From each module directory (e.g., `MsCaja`):

```powershell
cd MsCaja
mvn -T 1C clean package
```

Run all tests normally with `mvn test` or `mvn -DskipTests=false test` if needed.

## Notes / Next steps

- This migration upgrades the Spring Boot version; there may be other code changes required (e.g., replacing `javax.*` imports across other modules, updating client configs, or adjusting 3rd-party libs). Search for other `javax.` usage and update if needed.
- Check 3rd-party dependencies that may not support Java 21 or Spring Boot 3.x and update them to compatible versions.
- Run unit and integration tests and fix any compile or runtime failures.

If you want, I can continue the migration by running the build and tests locally (if you install JDK 21 on this machine), and fix any compile/test errors step-by-step.
