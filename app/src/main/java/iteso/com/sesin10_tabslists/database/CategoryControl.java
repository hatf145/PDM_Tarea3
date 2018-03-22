package iteso.com.sesin10_tabslists.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.util.ArrayList;

import iteso.com.sesin10_tabslists.beans.Category;

/**
 * Created by hecto on 21/03/2018.
 */

public class CategoryControl {
    public ArrayList<Category> getCategories(DatabaseHandler dh){
        ArrayList<Category> categoryArrayList = new ArrayList<>();
        SQLiteDatabase db = dh.getReadableDatabase();

        String selectQuery = "SELECT " + DatabaseHandler.KEY_CATEGORY_ID
                + ", " + DatabaseHandler.KEY_CATEGORY_NAME
                + " FROM " + DatabaseHandler.TABLE_CATEGORY;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                categoryArrayList.add(new Category(cursor.getInt(0), cursor.getString(1)));
            }while(cursor.moveToNext());
        }

        return categoryArrayList;

    }
}
