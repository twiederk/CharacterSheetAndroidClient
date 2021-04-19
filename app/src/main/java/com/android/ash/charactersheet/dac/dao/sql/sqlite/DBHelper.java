package com.android.ash.charactersheet.dac.dao.sql.sqlite;

import static com.d20charactersheet.framework.dac.dao.sql.TableAndColumnNames.COLUMN_IMAGE;
import static com.d20charactersheet.framework.dac.dao.sql.TableAndColumnNames.TABLE_IMAGE;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.android.ash.charactersheet.gui.util.Logger;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides access to the SQLite 3 database of the Android platform. Creates the whole database by running a single
 * script.
 */
public class DBHelper extends SQLiteOpenHelper {

    /**
     * Semaphore to synchronize database access
     */
    public static final Object DB_LOCK = new Object();

    private static final List<DBHelper> DB_HELPERS = new ArrayList<>();

    private final Context context;
    private SQLiteDatabase db;

    private final String databaseName;
    private boolean upgrade;
    private boolean create;
    private int oldVersion;

    private final List<ScriptResource> createScriptResources;
    private final int[] images;
    private final DBUpdateScriptAdministration updateScriptAdministration;

    /**
     * Creates a helper to create, open and upgrade the database. Adds the helper to the list of all DBHelpers.
     *
     * @param context        The context in which the database is running.
     * @param dbVersion      The version of the database, for example 12.
     * @param gameSystemType The game system the database belongs to.
     */
    public DBHelper(final Context context, final int dbVersion, GameSystemType gameSystemType) {
        super(context, gameSystemType.getDatabaseName(), null, dbVersion);
        this.context = context;
        this.createScriptResources = gameSystemType.getCreateScriptResources();
        this.updateScriptAdministration = new DBUpdateScriptAdministration(gameSystemType.getUpdateScriptResources(), gameSystemType.getUpdateImageResources());
        this.images = gameSystemType.getImages();
        this.databaseName = gameSystemType.getDatabaseName();
        DB_HELPERS.add(this);
    }

    /**
     * Returns all instantiated db helpers.
     *
     * @return All instantiated db helpers.
     */
    public static List<DBHelper> getDBHelpers() {
        return DB_HELPERS;
    }

    /**
     * Returns the names of all databases.
     *
     * @return The names of all databases.
     */
    public static String[] getDatabaseNames() {
        final String[] databaseNames = new String[DB_HELPERS.size()];
        for (int i = 0; i < databaseNames.length; i++) {
            databaseNames[i] = DB_HELPERS.get(i).getDatabaseName();
        }
        return databaseNames;
    }

    /**
     * Creates the database from a single SQL scripts, located in the raw directory.
     *
     * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
     */
    @Override
    public void onCreate(final SQLiteDatabase db) {
        Logger.debug("onCreate: " + databaseName);
        this.db = db;
        create = true;
        createDatabase();
    }

    private void createDatabase() {
        try {
            for (final ScriptResource createScriptResource : createScriptResources) {
                executeSqlStatements(db, createScriptResource);
            }
            initImageTable();
        } catch (final SQLException sqlException) {
            Logger.error("Can't create database: " + sqlException.getMessage());
        } catch (final IOException ioException) {
            Logger.error("Can't find SQL script: create tables", ioException);
        }

    }

    void executeSqlStatements(SQLiteDatabase database, final ScriptResource scriptResource) throws IOException {
        final String[] sqlScripts = getSqlScriptsFromRawResource(scriptResource);
        executeSqlStatements(database, sqlScripts);
    }

    @NotNull
    private String[] getSqlScriptsFromRawResource(final ScriptResource scriptResource) throws IOException {
        final InputStream inputStream = scriptResource.load(context.getResources());
        return getSqlScripts(inputStream);
    }

    void executeSqlStatements(final SQLiteDatabase database, final String scriptName) throws IOException {
        String[] sqlStatements = getSqlScriptsFromClasspath(scriptName);
        executeSqlStatements(database, sqlStatements);
    }

    @NotNull
    private String[] getSqlScriptsFromClasspath(String scriptName) throws IOException {
        final InputStream inputStream = this.getClass().getResourceAsStream(scriptName);
        return getSqlScripts(inputStream);
    }

    private void executeSqlStatements(SQLiteDatabase database, String[] sqlStatements) {
        synchronized (DBHelper.DB_LOCK) {
            for (String sqlStatement : sqlStatements) {
                if (sqlStatement.trim().length() == 0) {
                    continue;
                }
//                Logger.debug("sqlStatement: " + sqlStatement);
                try {
                    database.execSQL(sqlStatement);
                } catch (final SQLException sqlException) {
                    Logger.error("Can't execute statement: " + sqlStatement, sqlException);
                }
            }
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @NotNull
    private String[] getSqlScripts(InputStream inputStream) throws IOException {
        final byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);

        // split sql script in separate sql statements
        final String sql = new String(buffer);
        return sql.split(";");
    }

    private void initImageTable() {
        for (int i = 0; i < images.length; i++) {
            final Bitmap bitmap = getBitmap(images[i]);
            insertImageAsBlob(i, bitmap);
        }
    }

    private Bitmap getBitmap(final int resourceId) {
        return BitmapFactory.decodeResource(context.getResources(), resourceId);
    }

    private void insertImageAsBlob(final int imageId, final Bitmap bitmap) {
        final byte[] bitmapData = getBitmapData(bitmap);
        final ContentValues values = new ContentValues();
        values.put("id", imageId);
        values.put(COLUMN_IMAGE, bitmapData);
        try {
            synchronized (DBHelper.DB_LOCK) {
                db.insertOrThrow(TABLE_IMAGE, null, values);
            }
        } catch (final SQLException sqlException) {
            Logger.error("Can't insert image: " + imageId, sqlException);
        }
    }

    private byte[] getBitmapData(final Bitmap bitmap) {
        final ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        return bytes.toByteArray();
    }

    /**
     * Upgrades the database to the next version.
     *
     * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
     */
    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        Logger.debug("onUpgrade: " + databaseName);
        this.db = db;
        upgradeDatabase(oldVersion, newVersion);
        upgradeImages(oldVersion, newVersion);
        configureApplication(oldVersion);
    }

    private void upgradeDatabase(final int oldVersion, final int newVersion) {
        for (int currentVersion = oldVersion; currentVersion <= newVersion; currentVersion++) {
            Logger.debug("upgradeDatabase(): currentVersion: " + currentVersion);
            ScriptResource updateScript = updateScriptAdministration.getUpdateScript(currentVersion);
            if (updateScript == null) {
                Logger.debug("No update script for version " + currentVersion);
            } else {
                Logger.debug("Update from version " + currentVersion + " to " + (currentVersion + 1));
                upgradeDatabase(updateScript);
            }
        }
    }

    private void upgradeDatabase(final ScriptResource upgradeScript) {
        try {
            executeSqlStatements(db, upgradeScript);
        } catch (final IOException ioException) {
            Logger.error("Can't upgrade Database: " + ioException.getMessage(), ioException);
        }
    }

    private void upgradeImages(int oldVersion, int newVersion) {
        for (int currentVersion = oldVersion; currentVersion <= newVersion; currentVersion++) {
            Logger.debug("upgradeImages(): currentVersion: " + currentVersion);
            ImageResources imageResources = updateScriptAdministration.getUpdateImage(currentVersion);
            if (imageResources == null) {
                Logger.debug("No update image for version " + currentVersion);
            } else {
                Logger.debug("Update images from version " + currentVersion + " to " + (currentVersion + 1));
                upgradeImages(imageResources);
            }
        }
    }

    private void upgradeImages(ImageResources imageResources) {
        for (ImageResource imageResource : imageResources.getImageResources()) {
            final Bitmap bitmap = getBitmap(imageResource.getResourceId());
            insertImageAsBlob(imageResource.getId(), bitmap);
        }
    }


    private void configureApplication(final int oldVersion) {
        this.oldVersion = oldVersion;
        upgrade = true;
    }


    /**
     * Returns true, if the database was upgraded.
     *
     * @return True, if the database was upgraded.
     */
    public boolean isUpgrade() {
        return upgrade;
    }

    /**
     * Returns true, if the database was created.
     *
     * @return True, if the database was created.
     */
    public boolean isCreate() {
        return create;
    }

    /**
     * Returns the old version of the database.
     *
     * @return The old version of the database.
     */
    public int getOldVersion() {
        return oldVersion;
    }

}
