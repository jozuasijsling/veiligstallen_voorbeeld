# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html

-optimizations !code/simplification/cast,!field/*,!class/merging/*
-optimizationpasses 5
-allowaccessmodification
-dontpreverify
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose

# Attributes are needed for reflection, proguard should not strip them (yet)
-keepattributes *Annotation*, Signature, InnerClasses, EnclosingMethod

# For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
-keepclasseswithmembernames class * {
    native <methods>;
}

# keep setters in Views so that animations can still work.
# see http://proguard.sourceforge.net/manual/examples.html#beans
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

# We want to keep methods in Activity that could be used in the XML attribute onClick
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

# For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepclassmembers class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator CREATOR;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}

# The support library contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version.  We know about them, and they are safe.
-dontnote android.support.**
-dontwarn android.support.**

# Proguard has a known issue where it duplicates the apache http commons
# library before running its scans.
# https://issuetracker.google.com/issues/37070898
-dontnote org.apache.http.**
-dontnote android.net.http.HttpResponseCache
-dontnote android.net.http.SslCertificate
-dontnote android.net.http.SslCertificate$DName
-dontnote android.net.http.SslError


# SimpleXML uses the XML stream package from javax, not available on Android
-dontwarn javax.xml.stream.**

# Several libraries use annotations from javax, not available on Android
-dontwarn javax.annotation.**

# Missing non-runtime annotation can be ignored
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement


# SimpleXml classes specified here must remain on the classpath
-keep interface org.simpleframework.xml.core.Label { public *; }
-keep class * implements org.simpleframework.xml.core.Label { public *; }
-keep interface org.simpleframework.xml.core.Parameter { public *; }
-keep class * implements org.simpleframework.xml.core.Parameter { public *; }
-keep interface org.simpleframework.xml.core.Extractor { public *; }
-keep class * implements org.simpleframework.xml.core.Extractor { public *; }
-dontnote org.simpleframework.xml.**

# DTOs are mapped from XML using reflection
-keep class nl.jozuasijsling.veiligstallen.service.dto.** { *; }
