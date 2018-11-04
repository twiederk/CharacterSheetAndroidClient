package com.android.ash.charactersheet.gui.sheet;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.widget.ImageView;

import com.android.ash.charactersheet.CharacterSheetApplication;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.boc.service.AndroidImageService;
import com.android.ash.charactersheet.boc.service.PreferenceService;
import com.android.ash.charactersheet.gui.util.Logger;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.service.GameSystem;

/**
 * The character sheet of the character. Displays the stats, abilities and combat values of the character. The
 * background shows the image of the character.
 */
public class CharacterSheetActivity extends FragmentActivity implements ActionBar.TabListener {

    private GameSystem gameSystem;
    private PreferenceService preferenceService;
    private Character character;

    private PageAdapter pageAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        Logger.info(getClass().getSimpleName() + ".onCreate()");
        super.onCreate(savedInstanceState);

        final CharacterSheetApplication application = (CharacterSheetApplication) getApplication();
        gameSystem = application.getGameSystem();

        preferenceService = application.getPreferenceService();
        character = application.getCharacter();

        setContentView(R.layout.activity_character_sheet);
        setBackground();

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        pageAdapter = new PageAdapter(getSupportFragmentManager(), getResources());

        // Set up the ViewPager with the page adapter.
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pageAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(final int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        for (int i = 0; i < pageAdapter.getCount(); i++) {
            actionBar.addTab(actionBar.newTab().setText(pageAdapter.getPageTitle(i)).setTabListener(this));
        }

    }

    protected void setBackground() {
        final boolean showImageAsBackground = preferenceService.getBoolean(PreferenceService.SHOW_IMAGE_AS_BACKGROUND);
        if (showImageAsBackground) {
            final AndroidImageService imageService = (AndroidImageService) gameSystem.getImageService();
            final Bitmap bitmap = imageService.getBitmap(character.getImageId());
            configImageView(Color.TRANSPARENT, bitmap, 0, 0);
        } else {
            final int color = preferenceService.getInt(PreferenceService.BACKGROUND_COLOR);
            final Display display = getWindowManager().getDefaultDisplay();
            final Point size = new Point();
            display.getSize(size);
            final int minWidth = size.x;
            final int minHeight = size.y;
            configImageView(color, null, minWidth, minHeight);
        }
    }

    private void configImageView(final int color, final Bitmap bitmap, final int minWidth, final int minHeight) {
        final ImageView imageView = (ImageView) findViewById(R.id.background);
        imageView.setBackgroundColor(color);
        imageView.setMinimumWidth(minWidth);
        imageView.setMinimumHeight(minHeight);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public void onTabSelected(final ActionBar.Tab tab, final FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(final ActionBar.Tab tab, final FragmentTransaction fragmentTransaction) {
        // nothing to do
    }

    @Override
    public void onTabReselected(final ActionBar.Tab tab, final FragmentTransaction fragmentTransaction) {
        // nothing to do
    }

    @Override
    protected void onResume() {
        Logger.info(getClass().getSimpleName() + ".onResume()");
        super.onResume();
        setTitle(character.getName());
    }

    @Override
    protected void onDestroy() {
        Logger.info(getClass().getSimpleName() + ".onDestroy()");
        super.onDestroy();
    }

}
