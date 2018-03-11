# Exotics

An Android Development Toolbox containing readily used Android Functionalities

### Prerequisites

What things you need to install the software and how to install them

```
A Crashlytics Developer account for Crash Detection.
```

### Download

Add the following dependencies to your project

```
allprojects {
	repositories 
	{
	   maven { url 'https://jitpack.io' }
	}
}
```

And add the below to your app's build.gradle file

```
dependencies {
	     compile 'com.github.wanclem:exotics:1.1.3'
}
```

## Sample Usage

```
//The following displays a toast in a single line of code without replication on the Main Thread.

UiUtils.showSafeToast(getContext(),"Welcome to Exotics");

//Toggles a View Visibility
UiUtils.toggleViewVisibility(signInButton,true);

```

## Authors

** Wan Clem

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
