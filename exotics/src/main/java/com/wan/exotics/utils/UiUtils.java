package com.wan.exotics.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.wan.exotics.enums.DrawableDirection;

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

    public static void dismissKeyboard(Context context, View trigger) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(trigger.getWindowToken(), 0);
        }
    }

    public static synchronized void removeAllDrawablesFromTextView(TextView textView) {
        if (textView != null) {
            textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            textView.invalidate();
        }
    }

    public static synchronized void attachDrawableToTextView(Context context, TextView textView, int resource, DrawableDirection direction) {
        if (textView != null) {
            Drawable drawableToAttach = ContextCompat.getDrawable(context, resource);
            if (direction == DrawableDirection.LEFT) {
                textView.setCompoundDrawablesWithIntrinsicBounds(drawableToAttach, null, null, null);
            } else if (direction == DrawableDirection.RIGHT) {
                textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawableToAttach, null);
            } else if (direction == DrawableDirection.BOTTOM) {
                textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, drawableToAttach);
            } else if (direction == DrawableDirection.TOP) {
                textView.setCompoundDrawablesWithIntrinsicBounds(null, drawableToAttach, null, null);
            }
            textView.invalidate();
        }
    }

    /**
     * Change given image view tint
     *
     * @param imageView target image view
     * @param color     tint color
     */
    public static void tintImageView(ImageView imageView, int color) {
        imageView.setColorFilter(color);
    }

    public static void showKeyboard(Context context, View trigger) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(trigger, InputMethodManager.SHOW_FORCED);
        }
    }

}
