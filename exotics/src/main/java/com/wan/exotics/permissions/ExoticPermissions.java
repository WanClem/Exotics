package com.wan.exotics.permissions;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.wan.exotics.ui.activities.BaseActivity;

/**
 * * @author Wan Clem
 */
public class ExoticPermissions {

    private Activity activity;

    public ExoticPermissions(Activity activity) {
        this.activity = activity;
    }

    public void checkRuntimePermissionForStorage(String rationalMessage) {
        if (PermissionsUtils.checkSelfForStoragePermission(activity)) {
            requestStoragePermissions(rationalMessage);
        }
    }

    public void checkRuntimePermissionForLocation(String rationalMessage) {
        if (PermissionsUtils.checkSelfPermissionForLocation(activity)) {
            requestLocationPermissions(rationalMessage);
        } else {
            if (activity instanceof BaseActivity) {
                ((BaseActivity) activity).processLocation(null);
            }
        }
    }

    public void requestStoragePermissions(String rationalMessage) {
        if (PermissionsUtils.shouldShowRequestForStoragePermission(activity)) {
            showSnackBar(rationalMessage, PermissionsUtils.PERMISSIONS_STORAGE, PermissionsUtils.REQUEST_STORAGE);
        } else {
            PermissionsUtils.requestPermissions(activity, PermissionsUtils.PERMISSIONS_STORAGE, PermissionsUtils.REQUEST_STORAGE);
        }
    }

    public void requestLocationPermissions(String rationalMessage) {
        if (PermissionsUtils.shouldShowRequestForLocationPermission(activity)) {
            showSnackBar(rationalMessage, PermissionsUtils.PERMISSIONS_LOCATION, PermissionsUtils.REQUEST_LOCATION);
        } else {
            PermissionsUtils.requestPermissions(activity, PermissionsUtils.PERMISSIONS_LOCATION, PermissionsUtils.REQUEST_LOCATION);
        }
    }

    public void requestAudio(String rationalMessage) {
        if (PermissionsUtils.shouldShowRequestForLocationPermission(activity)) {
            showSnackBar(rationalMessage, PermissionsUtils.PERMISSIONS_LOCATION, PermissionsUtils.REQUEST_AUDIO_RECORD);
        } else {
            PermissionsUtils.requestPermissions(activity, PermissionsUtils.PERMISSIONS_RECORD_AUDIO, PermissionsUtils.REQUEST_AUDIO_RECORD);
        }
    }

    public void requestCallPermission(String rationalMessage) {
        if (PermissionsUtils.shouldShowRequestForCallPermission(activity)) {
            showSnackBar(rationalMessage, PermissionsUtils.PERMISSION_CALL, PermissionsUtils.REQUEST_CALL_PHONE);
        } else {
            PermissionsUtils.requestPermissions(activity, PermissionsUtils.PERMISSION_CALL, PermissionsUtils.REQUEST_CALL_PHONE);
        }
    }

    public void requestCameraPermission(String rationalMessage) {
        if (PermissionsUtils.shouldShowRequestForCameraPermission(activity)) {
            showSnackBar(rationalMessage, PermissionsUtils.PERMISSION_CAMERA, PermissionsUtils.REQUEST_CAMERA);
        } else {
            PermissionsUtils.requestPermissions(activity, PermissionsUtils.PERMISSION_CAMERA, PermissionsUtils.REQUEST_CAMERA);
        }
    }

    public void requestContactPermission(String rationalMessage) {
        if (PermissionsUtils.shouldShowRequestForContactPermission(activity)) {
            showSnackBar(rationalMessage, PermissionsUtils.PERMISSION_CONTACT, PermissionsUtils.REQUEST_CONTACT);
        } else {
            PermissionsUtils.requestPermissions(activity, PermissionsUtils.PERMISSION_CONTACT, PermissionsUtils.REQUEST_CONTACT);
        }
    }

    private Snackbar snackbar;

    private void showSnackBar(String message, final String[] permissions, final int requestCode) {
        if (activity != null) {
            View rootView = activity.getWindow().getDecorView().findViewById(android.R.id.content);
            if (rootView != null) {
                snackbar = Snackbar.make(rootView, message,
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction(android.R.string.ok, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                PermissionsUtils.requestPermissions(activity, permissions, requestCode);
                            }
                        });
                snackbar.show();
            }
        }
    }

    public Snackbar getSnackBar() {
        return snackbar;
    }

    public boolean permissionsForSendingAndReceivingSMSNotGranted() {
        return ((ActivityCompat.checkSelfPermission(activity, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) && ((ActivityCompat.checkSelfPermission(activity, Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED)) && ((ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED)) && ((ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED)));
    }

    public void requestSendAndReceiveSmsPermission(String rationalMessage) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.SEND_SMS)
                || ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_SMS)
                || ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.RECEIVE_SMS)) {
            showSnackBar(rationalMessage, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS,
                    Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_PHONE_STATE}, 0x11);
        } else {
            PermissionsUtils.requestPermissions(activity, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_PHONE_STATE}, 0x12);
        }
    }

    public boolean verifyPermissions(int[] grantResults) {
        return (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED);
    }

}
