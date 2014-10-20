# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/jozua/android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:


# -- Butter Knife -- #
# Butter Knife generates and uses classes dynamically which means that static
# analysis tools like ProGuard may think they are unused. In order to prevent
# them from being removed, explicitly mark them to be kept. To prevent ProGuard
# renaming classes that use @InjectView on a member field the keepnames option is used.
-dontwarn butterknife.internal.**
-keep class **$$ViewInjector { *; }
-keepnames class * { @butterknife.InjectView *;}


# -- Cupboard -- #
# When using Cupboard (or any other ORM for that matter) in combination with ProGuard,
# it's important to make sure that ProGuard does not rename or remove entity fields.
# If fields are removed by ProGuard, created tables will be incomplete queries might
# fail as a result. When fields are renamed, queries will also fail and duplicate column
# name errors might occur.
-keep nl.parckr.app.model.** {*;}


# -- Otto -- #
# This ensures your annotated methods aren't removed by ProGuard.
-keepclassmembers class ** {
    @com.squareup.otto.Subscribe public *;
    @com.squareup.otto.Produce public *;
}

# -- Conceal -- #
# Keep our interfaces so they can be used by other ProGuard rules.
# See http://sourceforge.net/p/proguard/bugs/466/
-keep,allowobfuscation @interface com.facebook.proguard.annotations.DoNotStrip
-keep,allowobfuscation @interface com.facebook.proguard.annotations.KeepGettersAndSetters

# Do not strip any method/class that is annotated with @DoNotStrip
-keep @com.facebook.proguard.annotations.DoNotStrip class *
-keepclassmembers class * {
    @com.facebook.proguard.annotations.DoNotStrip *;
}

-keepclassmembers @com.facebook.proguard.annotations.KeepGettersAndSetters class * {
  void set*(***);
  *** get*();
}


# -- Google Play Services -- #
# To prevent ProGuard from stripping away required classes.
-keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
}

-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}

-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
}

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}