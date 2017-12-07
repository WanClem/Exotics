package com.wan.exotics.utils;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Toast;
import android.widget.ViewFlipper;

/**
 * @author Wan Clem
 */
public class UiUtils {

    /***
     * Toggles a view visibility state
     *
     * @param view The view to toggle
     * @param visible Flag indicating whether a view should be setVisible or not
     **/
    public static void toggleViewVisibility(View view, boolean visible) {
        if (view != null) {
            if (visible) {
                if (view.getVisibility() != View.VISIBLE) {
                    view.setVisibility(View.VISIBLE);
                    view.invalidate();
                }
            } else {
                if (view.getVisibility() != View.GONE) {
                    view.setVisibility(View.GONE);
                    view.invalidate();
                }
            }
        }
    }

    /***
     * Toggles a view visibility state
     *
     * @param context The Application context
     * @param toastMessage Message to be displayed in toast
     **/
    public static void displayToast(final Context context, final String toastMessage) {
        ThreadUtils.runOnMain(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show();
            }
        });
    }

    /***
     * Toggles a view visibility state
     *
     * @param mSwipeRefreshLayout The target swiperefreshlayout whose color scheme need to be refreshed
     * @param colors An array of colors
     **/
    public static void setUpRefreshColorSchemes(SwipeRefreshLayout mSwipeRefreshLayout, int... colors) {
        mSwipeRefreshLayout.setColorSchemeColors(colors);
    }

    /***
     * Toggles a ViewFlipper's Displayed Child's state
     *
     * @param viewFlipper The viewflipper to toggle
     * @param indexOfChild       The Child to switch to
     **/
    public static void toggleViewFlipper(ViewFlipper viewFlipper, int indexOfChild) {
        if (viewFlipper != null) {
            if (viewFlipper.getDisplayedChild() != indexOfChild) {
                viewFlipper.setDisplayedChild(indexOfChild);
            }
        }
    }

}
