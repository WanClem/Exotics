package com.wan.exotics.ui.activities;

import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author Wan Clem
 */

public abstract class BaseActivity extends AppCompatActivity {

    @NonNull
    public BaseActivity getCurrentActivityInstance() {
        return BaseActivity.this;
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkAndRegEventBus();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkAndRegEventBus();
    }

    @Override
    protected void onStop() {
        super.onStop();
        checkAndUnRegEventBus();
    }

    @Override
    protected void onDestroy() {
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

    public void processLocation(Location location) {

    }

}
