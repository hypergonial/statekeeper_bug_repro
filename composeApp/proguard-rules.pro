-keepnames class * extends androidx.startup.Initializer
# These Proguard rules ensures that ComponentInitializers are are neither shrunk nor obfuscated,
# and are a part of the primary dex file. This is because they are discovered and instantiated
# during application startup.
-keep class * extends androidx.startup.Initializer {
    # Keep the public no-argument constructor while allowing other methods to be optimized.
    <init>();
}
# No idea why but release build fails if I don't have this, I love Android & Gradle
-dontwarn org.slf4j.impl.StaticLoggerBinder
