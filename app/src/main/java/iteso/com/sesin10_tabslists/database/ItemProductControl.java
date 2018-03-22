package iteso.com.sesin10_tabslists.database;

import android.content.ClipData;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.util.ArrayList;

import iteso.com.sesin10_tabslists.beans.Category;
import iteso.com.sesin10_tabslists.beans.City;
import iteso.com.sesin10_tabslists.beans.ItemProduct;
import iteso.com.sesin10_tabslists.beans.Store;

/**
 * Created by hecto on 21/03/2018.
 */

public class ItemProductControl {
    public long addItemProduct(ItemProduct product, DatabaseHandler dh){
        long inserted = 0;
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseHandler.KEY_PRODUCT_CATEGORY, product.getCategory().getName());
        values.put(DatabaseHandler.KEY_PRODUCT_IMAGE, product.getImage());
        values.put(DatabaseHandler.KEY_PRODUCT_TITLE, product.getTitle());

        inserted = db.insert(DatabaseHandler.TABLE_PRODUCT, null, values);

        values.clear();

        values.put(DatabaseHandler.KEY_STOREPRODUCT_STORE, product.getStore().getId());
        values.put(DatabaseHandler.KEY_STOREPRODUCT_PRODUCT, product.getCode());

        inserted = db.insert(DatabaseHandler.TABLE_STORE_PRODUCT, null, values);

        try{
            db.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        db = null;
        values = null;

        return inserted;
    }

    public void deleteProduct(int idProduct, DatabaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        db.delete(DatabaseHandler.TABLE_PRODUCT, DatabaseHandler.KEY_PRODUCT_ID + " = ?",
                new String[]{String.valueOf(idProduct)});

        try{
            db.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        db = null;
    }

    public int updateProduct(ItemProduct product, DatabaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseHandler.KEY_PRODUCT_ID, product.getCode());
        values.put(DatabaseHandler.KEY_PRODUCT_CATEGORY, product.getCategory().getName());
        values.put(DatabaseHandler.KEY_PRODUCT_IMAGE, product.getImage());
        values.put(DatabaseHandler.KEY_PRODUCT_TITLE, product.getTitle());

        int count = db.update(DatabaseHandler.TABLE_PRODUCT, values,
                DatabaseHandler.KEY_PRODUCT_ID + " = ?",
                new String[]{String.valueOf(product.getCode())});

        try{
            db.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        db = null;

        return count;
    }

    public ArrayList<ItemProduct> getProductsByCategory(int idCategory, DatabaseHandler dh){
        ArrayList<ItemProduct> productArray = new ArrayList<>();
        ItemProduct product = new ItemProduct();

        String selectQuery =
                "SELECT product." + DatabaseHandler.KEY_PRODUCT_ID + ", " //0
                + "product." + DatabaseHandler.KEY_PRODUCT_CATEGORY + ", " //1
                + "product." + DatabaseHandler.KEY_PRODUCT_IMAGE + ", " //2
                + "product." + DatabaseHandler.KEY_PRODUCT_TITLE + ", " //3
                + "category." + DatabaseHandler.KEY_CATEGORY_NAME + ", " //4
                + "storeProduct." + DatabaseHandler.KEY_STOREPRODUCT_STORE + ", " //5
                + "store." + DatabaseHandler.KEY_STORE_NAME + ", " //6
                + "store." + DatabaseHandler.KEY_STORE_PHONE + ", " //7
                + "store." + DatabaseHandler.KEY_STORE_CITY + ", " //8
                + "store." + DatabaseHandler.KEY_STORE_THUMBNAIL + ", " //9
                + "store." + DatabaseHandler.KEY_STORE_LAT + ", " //10
                + "store." + DatabaseHandler.KEY_STORE_LNG + ", "//11
                + "city." + DatabaseHandler.KEY_CITY_NAME //12
                + " FROM " + DatabaseHandler.TABLE_PRODUCT + " "
                + "INNER JOIN " + DatabaseHandler.TABLE_CATEGORY + " ON product." + DatabaseHandler.KEY_PRODUCT_CATEGORY + " = category." + DatabaseHandler.KEY_CATEGORY_ID
                + " INNER JOIN " + DatabaseHandler.TABLE_STORE_PRODUCT + " ON product." + DatabaseHandler.KEY_PRODUCT_ID + " = storeProduct." + DatabaseHandler.KEY_STOREPRODUCT_PRODUCT
                + " INNER JOIN " + DatabaseHandler.TABLE_STORE + " ON store." + DatabaseHandler.KEY_STORE_ID + " = storeProduct." + DatabaseHandler.KEY_STOREPRODUCT_STORE
                + " INNER JOIN " + DatabaseHandler.TABLE_CITY + " ON store." + DatabaseHandler.KEY_STORE_CITY + " = city." + DatabaseHandler.KEY_CITY_ID
                + " WHERE"
                + " product." + DatabaseHandler.KEY_PRODUCT_CATEGORY + " = " + idCategory;

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do {
                productArray.add(
                        new ItemProduct(
                                cursor.getInt(0),
                                cursor.getString(3),
                                "Generic Description",
                                cursor.getInt(2),
                                new Store(
                                        cursor.getInt(5),
                                        cursor.getString(6),
                                        cursor.getString(7),
                                        new City(
                                                cursor.getInt(8),
                                                cursor.getString(12)
                                        ),
                                        cursor.getInt(9),
                                        cursor.getDouble(10),
                                        cursor.getDouble(11)
                                ),
                                new Category(
                                        cursor.getInt(1),
                                        cursor.getString(4)
                                )
                        )
                );
            }while (cursor.moveToNext());
        }

        return productArray;
    }
}
