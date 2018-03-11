# Exotics

An Android Development Toolbox containing readily used Android Functionalities

### Prerequisites

```
A Crashlytics Developer account for Crash Detection.
```

### Download

Add the following dependencies to your project level  `build.gradle`  file

```java
allprojects {
	repositories {
	   maven { url 'https://jitpack.io' }
	}
}
```

And add the below to your app's `build.gradle` file

```java
dependencies {
     compile 'com.github.wanclem:exotics:1.1.3'
}
```

## Sample Usage

```java
//The following displays a toast in a single line of code without replication on the Main Thread.

UiUtils.showSafeToast(getContext(),"Welcome to Exotics");

//Toggles a View Visibility
UiUtils.toggleViewVisibility(signInButton,true);


/***Say you are building an app to fetch all the user's photos and videos 
in a folder structure, 
You could do that in a single line of code
***/

//Fetch all User Videos on the device
List<MediaEntry>allUserVideos = MediaUtils.getSortedVideos(getContext());

//Fetch all user photos on the device
List<MediaEntry>allUserPhotos = MediaUtils.getSortedPhotos(getContext());

//Fetch all of a user Music Files
List<MediaEntry>allUserMusic = MediaUtils.getSortedMusicFiles(getContext());

//Checking and asking for permissions
if(!PermissionUtils.isContactPermissionsGranted(activity)){
    PermissionUtils.requestContactPermissions(activity);
}
```

## Authors

** Wan Clem

## License

This project is licensed under the MIT License
