package com.android.ash.charactersheet.gui.util;

import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;

import com.android.ash.charactersheet.R;

/**
 * Activity slides from left into and out of activity.
 */
public abstract class SlideActivity extends LogAppCompatActivity {

    private boolean firstTime = true;

    @Override
    protected void onResume() {
        super.onResume();
        if (firstTime) {
            final View view = findViewById(R.id.slide);
            final Animation animation = AnimationUtils.loadAnimation(this, R.anim.left_in);
            view.startAnimation(animation);
            firstTime = false;
        }
    }

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            final View view = findViewById(R.id.slide);
            final Animation animation = AnimationUtils.loadAnimation(this, R.anim.left_out);
            view.startAnimation(animation);
            animation.setAnimationListener(new AnimationListener() {

                @Override
                public void onAnimationStart(final Animation animation) {
                    // nothing to do
                }

                @Override
                public void onAnimationRepeat(final Animation animation) {
                    // nothing to do
                }

                @Override
                public void onAnimationEnd(final Animation animation) {
                    finish();
                }
            });
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
