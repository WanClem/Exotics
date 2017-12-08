package com.wan.exotics.permissions;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.wan.exotics.utils.BuildUtils;
import com.wan.exotics.utils.Capture;

/**
 * @author Wan Clem
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class PermissionsUtils {

    private static Capture<Activity> contextCapture = new Capture<>();

    private static final int REQUEST_STORAGE = 0;
    private static final int REQUEST_LOCATION = 1;
    private static final int REQUEST_AUDIO_RECORD = 3;
    private static final int REQUEST_CALL_PHONE = 4;
    private static final int REQUEST_CAMERA = 5;
    private static final int REQUEST_CONTACT = 6;
    private static final int REQUEST_SMS = 7;

    private static String[] PERMISSIONS_LOCATION = {Manifest.permission.ACCESS_FINE_LOCATION};
    private static String[] PERMISSION_CALL = {Manifest.permission.CALL_PHONE};
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

    private static String[] PERMISSIONS_RECORD_AUDIO = {Manifest.permission.RECORD_AUDIO};
    private static String[] PERMISSION_CAMERA = {Manifest.permission.CAMERA};
    private static String[] PERMISSION_CONTACT = {Manifest.permission.READ_CONTACTS};

    private static String[] PERMISSION_SMS = {Manifest.permission.READ_SMS, Manifest.permission.SEND_SMS, Manifest.permission.READ_PHONE_STATE};

    public static boolean verifyPermissions(int[] grantResults) {
        if (grantResults.length < 1) {
            return false;
        }
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private static void setCurrentActivityContext(Activity activity) {
        contextCapture.set(activity);
    }

    private static boolean shouldRequestForLocationPermissions(Activity activity) {
        setCurrentActivityContext(activity);
        return (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.ACCESS_FINE_LOCATION)
                || ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.ACCESS_COARSE_LOCATION));
    }

    public static void requestLocationPermissions(String rationalMessage) {
        if (shouldRequestForLocationPermissions(contextCapture.get())) {
            showSnackBar(rationalMessage, PERMISSIONS_LOCATION, REQUEST_LOCATION);
        } else {
            requestPermissions(contextCapture.get(), PERMISSIONS_LOCATION, REQUEST_LOCATION);
        }
    }

    private static boolean shouldRequestForAudioPermissions(Activity activity) {
        setCurrentActivityContext(activity);
        return (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.RECORD_AUDIO));
    }

    public static void requestAudioPermissions(String rationalMessage) {
        if (shouldRequestForAudioPermissions(contextCapture.get())) {
            showSnackBar(rationalMessage, PERMISSIONS_LOCATION, REQUEST_AUDIO_RECORD);
        } else {
            requestPermissions(contextCapture.get(), PERMISSIONS_RECORD_AUDIO, REQUEST_AUDIO_RECORD);
        }
    }

    private static boolean shouldRequestForStoragePermissions(Activity activity) {
        setCurrentActivityContext(activity);
        return (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                || ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.READ_EXTERNAL_STORAGE));
    }

    public static void requestStoragePermissions(String rationalMessage) {
        if (shouldRequestForStoragePermissions(contextCapture.get())) {
            showSnackBar(rationalMessage, PERMISSIONS_STORAGE, REQUEST_STORAGE);
        } else {
            requestPermissions(contextCapture.get(), PERMISSIONS_STORAGE, REQUEST_STORAGE);
        }
    }

    public static void requestCallPermissions(String rationalMessage) {
        if (shouldRequestForCallPermissions(contextCapture.get())) {
            showSnackBar(rationalMessage, PERMISSION_CALL, REQUEST_CALL_PHONE);
        } else {
            requestPermissions(contextCapture.get(), PERMISSION_CALL, REQUEST_CALL_PHONE);
        }
    }

    private static boolean shouldRequestForCallPermissions(Activity activity) {
        setCurrentActivityContext(activity);
        return (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.CALL_PHONE));
    }

    private static boolean shouldRequestForCameraPermissions(Activity activity) {
        setCurrentActivityContext(activity);
        return (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.CAMERA));
    }

    public static void requestCameraPermissions(String rationalMessage) {
        if (shouldRequestForCameraPermissions(contextCapture.get())) {
            showSnackBar(rationalMessage, PERMISSION_CAMERA, REQUEST_CAMERA);
        } else {
            requestPermissions(contextCapture.get(), PERMISSION_CAMERA, REQUEST_CAMERA);
        }
    }

    private static boolean shouldRequestForContactPermissions(Activity activity) {
        setCurrentActivityContext(activity);
        return (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.READ_CONTACTS));
    }

    public static void requestContactPermissions(String rationalMessage) {
        if (shouldRequestForContactPermissions(contextCapture.get())) {
            showSnackBar(rationalMessage, PERMISSION_CONTACT, REQUEST_CONTACT);
        } else {
            requestPermissions(contextCapture.get(), PERMISSION_CONTACT, REQUEST_CONTACT);
        }
    }

    private static boolean shouldRequestForSMSAccessPermissions(Activity activity) {
        setCurrentActivityContext(activity);
        return (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.SEND_SMS)
                || ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.RECEIVE_SMS)
                || ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_SMS)
                || ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_PHONE_STATE));
    }

    public static void requestSmsAccessPermission(String rationalMessage) {
        if (shouldRequestForSMSAccessPermissions(contextCapture.get())) {
            showSnackBar(rationalMessage, PERMISSION_SMS, REQUEST_SMS);
        } else {
            requestPermissions(contextCapture.get(), new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_PHONE_STATE}, 0x12);
        }
    }

    public static boolean isSMSAccessPermissionNeeded(Activity activity) {
        setCurrentActivityContext(activity);
        return ((ActivityCompat.checkSelfPermission(contextCapture.get(), Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) && ((ActivityCompat.checkSelfPermission(contextCapture.get(), Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED)) && ((ActivityCompat.checkSelfPermission(contextCapture.get(), Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED)) && ((ActivityCompat.checkSelfPermission(contextCapture.get(), Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED)));
    }

    public static boolean isStorageAccessPermissionNeeded(Activity activity) {
        setCurrentActivityContext(activity);
        return BuildUtils.hasMarshmallow() && (ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED);
    }

    public static boolean isLocationAccessPermissionNeeded(Activity activity) {
        setCurrentActivityContext(activity);
        return BuildUtils.hasMarshmallow() && (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED);
    }

    public static boolean isAudioAccessPermissionNeededd(Activity activity) {
        setCurrentActivityContext(activity);
        return BuildUtils.hasMarshmallow() && (ActivityCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED);
    }

    public static boolean callPermissionIsNeeded(Activity activity) {
        setCurrentActivityContext(activity);
        return BuildUtils.hasMarshmallow() && (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED);
    }

    public static boolean isCameraAccessPermissionNeeded(Activity activity) {
        setCurrentActivityContext(activity);
        return BuildUtils.hasMarshmallow() && (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED);
    }

    public static boolean isContactAccessPermissionNeeded(Activity activity) {
        setCurrentActivityContext(activity);
        return BuildUtils.hasMarshmallow() && (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED);
    }

    public static void requestPermissions(Activity activity, String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

    public static boolean isAudioRecordingPermissionGranted(Context context) {
        String permission = "android.permission.RECORD_AUDIO";
        int res = context.checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

    public static boolean isCameraPermissionGranted(Context context) {
        int res = context.checkCallingOrSelfPermission(Manifest.permission.CAMERA);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

    public static boolean isCallPermissionGranted(Context context) {
        int res = context.checkCallingOrSelfPermission(Manifest.permission.CALL_PHONE);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

    private static Snackbar snackbar;

    private static void showSnackBar(String message, final String[] permissions, final int requestCode) {
        if (contextCapture.get() != null) {
            View rootView = contextCapture.get().getWindow().getDecorView().findViewById(android.R.id.content);
            if (rootView != null) {
                snackbar = Snackbar.make(rootView, message,
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction(android.R.string.ok, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                requestPermissions(contextCapture.get(), permissions, requestCode);
                            }
                        });
                snackbar.show();
            }
        }
    }

    public Snackbar getSnackBar() {
        return snackbar;
    }

}
