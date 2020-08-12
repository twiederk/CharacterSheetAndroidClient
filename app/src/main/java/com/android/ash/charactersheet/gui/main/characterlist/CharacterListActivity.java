package com.android.ash.charactersheet.gui.main.characterlist;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.ash.charactersheet.billing.Billing;
import com.android.ash.charactersheet.gui.main.characterlist.promocode.PromoCode;
import com.android.ash.charactersheet.gui.util.LogAppCompatActivity;
import com.android.billingclient.api.BillingClient;
import com.google.android.gms.ads.MobileAds;

import org.jetbrains.annotations.NotNull;

import kotlin.Lazy;

import static org.koin.java.KoinJavaComponent.inject;

/**
 * The list of characters available. Each character is listed with an image, its name, race, class and level.
 */
public class CharacterListActivity extends LogAppCompatActivity implements AbstractAsyncTask.GameSystemLoadable {

    private final Lazy<Billing> billing = inject(Billing.class);

    private final CharacterListGameSystem characterListGameSystem = new CharacterListGameSystem();
    protected final PromoCode promoCode = new PromoCode();
    private final CharacterListOptionsMenu optionsMenu = new CharacterListOptionsMenu(this);
    private final CharacterListContextMenu contextMenu = new CharacterListContextMenu();
    private final CharacterListLayout characterListLayout = new CharacterListLayout(this);

    /**
     * Creates list of all available character. The characters are retrieved from the CharacterDao implementation.
     */
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileAds.initialize(this);
        billing.getValue().startConnection(BillingClient.newBuilder(this));
        characterListGameSystem.onCreateGameSystem(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        billing.getValue().startConnection(BillingClient.newBuilder(this));
        characterListGameSystem.onResumeGameSystem(this, characterListLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        return optionsMenu.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NotNull final MenuItem menuItem) {
        return optionsMenu.onOptionsItemSelected(menuItem);
    }

    /**
     * Creates a context menu displaying a delete option of the selected character.
     *
     * @see android.app.Activity#onCreateContextMenu(android.view.ContextMenu, android.view.View,
     * android.view.ContextMenu.ContextMenuInfo)
     */
    @Override
    public void onCreateContextMenu(final ContextMenu menu, final View v, final ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        contextMenu.onCreateContextMenu(menu, v, menuInfo);
    }

    /**
     * Deletes the character if the delete option is selected.
     *
     * @see android.app.Activity#onContextItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onContextItemSelected(@NotNull final MenuItem item) {
        return contextMenu.onContextItemSelected(item, findViewById(android.R.id.list));
    }

    @Override
    public void onGameSystemLoaded() {
        characterListGameSystem.setUserProperties();
        promoCode.execute(this);
        characterListLayout.layout();
        characterListLayout.resumeLayout();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        billing.getValue().endConnection();
        characterListGameSystem.onDestroy();
    }

}