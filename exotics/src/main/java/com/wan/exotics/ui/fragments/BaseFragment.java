package com.wan.exotics.ui.fragments;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author Wan Clem
 */

public class BaseFragment extends android.support.v4.app.Fragment{


    @Override
    public void onStart() {
        super.onStart();
        checkAndRegEventBus();
    }

    @Override
    public void onResume() {
        super.onResume();
        checkAndRegEventBus();
    }

    @Override
    public void onStop() {
        super.onStop();
        checkAndUnRegEventBus();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        checkAndUnRegEventBus();
    }

    private void checkAndRegEventBus() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    private void checkAndUnRegEventBus() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(sticky = true, threadMode = ThreadMode.ASYNC)
    public void onEventAsync(final Object o) {

    }
}
