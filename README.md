# Exotics

An Android Development Toolbox containing readily used Android Functionalities

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

```
A Crashlytics Developer account for Crash Detection.
A Firebase Project for Firebase Integration. Even if you don't need it.
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
