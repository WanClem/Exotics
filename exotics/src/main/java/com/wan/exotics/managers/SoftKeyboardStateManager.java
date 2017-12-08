package com.wan.exotics.managers;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;

import com.wan.exotics.interfaces.SoftKeyboardStateListener;
import com.wan.exotics.loggers.ExoticsLogger;

/**
 * @author Wan Clem
 */

public class SoftKeyboardStateManager {

    private static String TAG = SoftKeyboardStateManager.class.getSimpleName();

    public static ViewTreeObserver.OnGlobalLayoutListener subscribeToSoftKeyboardChanges(final View rootView, final SoftKeyboardStateListener paramSoftKeyboardStateListener) {
        ViewTreeObserver.OnGlobalLayoutListener layoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                rootView.getWindowVisibleDisplayFrame(r);
                int screenHeight = rootView.getRootView().getHeight();
                // r.bottom is the position above soft keypad or device button.
                // if keypad is shown, the r.bottom is smaller than that before.
                int keypadHeight = screenHeight - r.bottom;
                ExoticsLogger.d(TAG, "keypadHeight = " + keypadHeight);
                if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
                    // keyboard is opened
                    paramSoftKeyboardStateListener.onKeyboardShown();
                } else {
                    // keyboard is closed
                    paramSoftKeyboardStateListener.onKeyboardHidden();
                }
            }
        };
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(layoutListener);
        return layoutListener;
    }

    public static void unsubscribeToSoftKeyboardChanges(View rootView, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        rootView.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
    }

}
