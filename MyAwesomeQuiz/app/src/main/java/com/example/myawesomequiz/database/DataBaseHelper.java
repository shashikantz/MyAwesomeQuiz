package com.example.myawesomequiz.database;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static String TAG = "DataBaseHelper"; // Tag just for the LogCat window
    private static String DB_NAME ="9616827646"; // Database name
    private static int DB_VERSION = 1; // Database version
    private final File DB_FILE;
    private final Context mContext;

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        DB_FILE = context.getDatabasePath(DB_NAME);
        this.mContext = context;
        this.createDataBase();

    }

    private void emptyFavoriteTable() {
        String sqlQuery = "DELETE FROM favorites;";
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();
            db.execSQL(sqlQuery);
        } catch (SQLException e) {
            Log.e(TAG, " createFavoriteTable >>" + e.toString());
            throw e;
        } finally {
            db.close();
        }
    }

    public void createDataBase()  {
        // If the database does not exist, copy it from the assets.
        boolean mDataBaseExist = checkDataBase();

        if(mDataBaseExist){
            Log.d(TAG, "createDataBase >> Database file already exists: " + DB_FILE);

        } else {
            try {
                // Copy the database from assets
                Log.d(TAG, "Copying the database from assets");
                copyDataBase();
                Log.d(TAG, "createDatabase database created");
                emptyFavoriteTable();
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    // Check that the database file exists in databases folder
    private boolean checkDataBase() {
        return DB_FILE.exists();
    }

    // Copy the database from assets
    private void copyDataBase() throws IOException {
        Log.d(TAG, "copyDataBase >> Copying the database file from assests.");

        InputStream mInput = mContext.getAssets().open("databases/" + DB_NAME );
        OutputStream mOutput = new FileOutputStream(DB_FILE);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}