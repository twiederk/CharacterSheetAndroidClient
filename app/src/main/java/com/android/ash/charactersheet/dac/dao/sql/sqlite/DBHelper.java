package com.android.ash.charactersheet.dac.dao.sql.sqlite;

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

import static com.d20charactersheet.framework.dac.dao.sql.TableAndColumnNames.COLUMN_IMAGE;
import static com.d20charactersheet.framework.dac.dao.sql.TableAndColumnNames.TABLE_IMAGE;

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

    private boolean upgrade;
    private int oldVersion;

    private final int[] createScripts;
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
        this.createScripts = gameSystemType.getCreateScripts();
        this.updateScriptAdministration = new DBUpdateScriptAdministration(gameSystemType.getUpdateScripts());
        this.images = gameSystemType.getImages();
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
        this.db = db;
        createDatabase();
    }

    private void createDatabase() {
        try {
            for (final int createScript : createScripts) {
                executeSqlStatements(db, createScript);
            }
            initImageTable();
        } catch (final SQLException sqlException) {
            Logger.error("Can't create database: " + sqlException.getMessage());
        } catch (final IOException ioException) {
            Logger.error("Can't find SQL script: create tables", ioException);
        }

    }

    void executeSqlStatements(SQLiteDatabase database, final int resourceId) throws IOException {
        final String[] sqlScripts = getSqlScriptsFromRawResource(resourceId);
        executeSqlStatements(database, sqlScripts);
    }

    void executeSqlStatements(final SQLiteDatabase database, final String scriptName) throws IOException {
        String[] sqlStatements = getSqlScriptsFromClasspath(scriptName);
        executeSqlStatements(database, sqlStatements);
    }

    @NotNull
    private String[] getSqlScriptsFromRawResource(final int resourceId) throws IOException {
        final InputStream inputStream = context.getResources().openRawResource(resourceId);
        return getSqlScripts(inputStream);
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
        synchronized (DBHelper.DB_LOCK) {
            db.insertOrThrow(TABLE_IMAGE, null, values);
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
        this.db = db;
        upgradeDatabase(oldVersion, newVersion);
        configureApplication(oldVersion);
    }

    private void upgradeDatabase(final int oldVersion, final int newVersion) {
        Logger.debug("upgradeDatabase(): oldVersion: " + oldVersion + ", newVersion: " + newVersion);

        for (int currentVersion = oldVersion; currentVersion <= newVersion; currentVersion++) {
            Logger.debug("upgradeDatabase(): currentVersion: " + currentVersion);
            int updateScript = updateScriptAdministration.getUpdateScript(currentVersion);
            if (updateScript == 0) {
                Logger.debug("No update script for version " + currentVersion);
            } else {
                Logger.debug("Update from version " + currentVersion + " to " + (currentVersion + 1));
                upgradeDatabase(updateScript);
            }
        }
    }

    private void configureApplication(final int oldVersion) {
        this.oldVersion = oldVersion;
        upgrade = true;
    }

    private void upgradeDatabase(final int upgradeScript) {
        try {
            executeSqlStatements(db, upgradeScript);
        } catch (final IOException ioException) {
            Logger.error("Can't upgrade Database: " + ioException.getMessage(), ioException);
        }
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
     * Returns the old version of the database.
     *
     * @return The old version of the database.
     */
    public int getOldVersion() {
        return oldVersion;
    }

}
