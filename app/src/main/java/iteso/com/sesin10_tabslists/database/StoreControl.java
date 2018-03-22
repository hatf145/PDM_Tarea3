package iteso.com.sesin10_tabslists.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.util.ArrayList;

import iteso.com.sesin10_tabslists.beans.City;
import iteso.com.sesin10_tabslists.beans.Store;

/**
 * Created by hecto on 21/03/2018.
 */

public class StoreControl {
    public long addStore(Store store,DatabaseHandler dh){
        long inserted = 0;
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseHandler.KEY_STORE_CITY, store.getCity().getIdCity());
        values.put(DatabaseHandler.KEY_STORE_LAT, store.getLatitude());
        values.put(DatabaseHandler.KEY_STORE_LNG, store.getLongitude());
        values.put(DatabaseHandler.KEY_STORE_NAME, store.getName());
        values.put(DatabaseHandler.KEY_STORE_PHONE, store.getPhone());
        values.put(DatabaseHandler.KEY_STORE_THUMBNAIL, store.getThumbnail());

        inserted = db.insert(DatabaseHandler.TABLE_STORE, null, values);

        try{
            db.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        db = null;
        values = null;

        return inserted;
    }

    public void deleteStore(int idStore, DatabaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        db.delete(DatabaseHandler.TABLE_STORE, DatabaseHandler.KEY_STORE_ID + " = ?",
                new String[]{String.valueOf(idStore)});

        try{
            db.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        db = null;
    }

    public int updateStore(Store store, DatabaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseHandler.KEY_STORE_CITY, store.getCity().getIdCity());
        values.put(DatabaseHandler.KEY_STORE_LAT, store.getLatitude());
        values.put(DatabaseHandler.KEY_STORE_LNG, store.getLongitude());
        values.put(DatabaseHandler.KEY_STORE_NAME, store.getName());
        values.put(DatabaseHandler.KEY_STORE_PHONE, store.getPhone());
        values.put(DatabaseHandler.KEY_STORE_THUMBNAIL, store.getThumbnail());

        int count = db.update(DatabaseHandler.TABLE_STORE, values,
                DatabaseHandler.KEY_STORE_ID + " = ?",
                new String[]{String.valueOf(store.getId())});

        try{
            db.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        db = null;

        return count;
    }

    public Store getStoreById(int idStore, DatabaseHandler dh){
        Store store = new Store();
        City city = new City();
        String selectQuery = "SELECT S." + DatabaseHandler.KEY_STORE_ID + ","
                + "S." + DatabaseHandler.KEY_STORE_LAT + ","
                + "S." + DatabaseHandler.KEY_STORE_LNG + ","
                + "S." + DatabaseHandler.KEY_CATEGORY_NAME + ","
                + "S." + DatabaseHandler.KEY_STORE_PHONE + ","
                + "S." + DatabaseHandler.KEY_STORE_THUMBNAIL + ","
                + "C." + DatabaseHandler.KEY_CITY_ID + ","
                + "C." + DatabaseHandler.KEY_CITY_NAME + " FROM "
                + DatabaseHandler.TABLE_STORE + " S, "
                + DatabaseHandler.TABLE_CITY + " C, WHERE S."
                + DatabaseHandler.KEY_STORE_ID + " = " + idStore
                + " AND S." + DatabaseHandler.KEY_STORE_CITY
                + " = C." + DatabaseHandler.KEY_CITY_ID;

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            store.setId(cursor.getInt(0));
            store.setLatitude(cursor.getDouble(1));
            store.setLongitude(cursor.getDouble(2));
            store.setName(cursor.getString(3));
            store.setPhone(cursor.getString(4));
            store.setThumbnail(cursor.getInt(5));
            city.setIdCity(cursor.getInt(6));
            city.setName(cursor.getString(7));
            store.setCity(city);
        }

        try{
            cursor.close();
            db.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        cursor = null;
        db = null;

        return store;
    }

    public ArrayList<Store> getStores(DatabaseHandler dh){
        Store store = new Store();
        ArrayList<Store> storeArrayList = new ArrayList<>();
        City city = new City();
        String selectQuery = "SELECT S." + DatabaseHandler.KEY_STORE_ID + ","
                + "S." + DatabaseHandler.KEY_STORE_LAT + ","
                + "S." + DatabaseHandler.KEY_STORE_LNG + ","
                + "S." + DatabaseHandler.KEY_CATEGORY_NAME + ","
                + "S." + DatabaseHandler.KEY_STORE_PHONE + ","
                + "S." + DatabaseHandler.KEY_STORE_THUMBNAIL + ","
                + "C." + DatabaseHandler.KEY_CITY_ID + ","
                + "C." + DatabaseHandler.KEY_CITY_NAME + " FROM "
                + DatabaseHandler.TABLE_STORE + " S, "
                + DatabaseHandler.TABLE_CITY + " C WHERE S."
                + DatabaseHandler.KEY_STORE_CITY
                + " = C." + DatabaseHandler.KEY_CITY_ID;

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                store.setId(cursor.getInt(0));
                store.setLatitude(cursor.getDouble(1));
                store.setLongitude(cursor.getDouble(2));
                store.setName(cursor.getString(3));
                store.setPhone(cursor.getString(4));
                store.setThumbnail(cursor.getInt(5));
                city.setIdCity(cursor.getInt(6));
                city.setName(cursor.getString(7));
                store.setCity(city);
                storeArrayList.add(store);
            }while (cursor.moveToNext()) ;
        }

        try{
            cursor.close();
            db.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        cursor = null;
        db = null;

        return storeArrayList;
    }

}
