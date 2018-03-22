package iteso.com.sesin10_tabslists;

import android.app.Fragment;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import iteso.com.sesin10_tabslists.beans.Category;
import iteso.com.sesin10_tabslists.beans.ItemProduct;
import iteso.com.sesin10_tabslists.beans.Store;
import iteso.com.sesin10_tabslists.database.CategoryControl;
import iteso.com.sesin10_tabslists.database.DatabaseHandler;
import iteso.com.sesin10_tabslists.database.ItemProductControl;
import iteso.com.sesin10_tabslists.database.StoreControl;

public class ActivityItem extends AppCompatActivity {

    EditText title;
    Spinner img, store, category;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        title = findViewById(R.id.activity_item_title);
        img = findViewById(R.id.activity_item_img);
        store = findViewById(R.id.activity_item_store);
        category = findViewById(R.id.activity_item_category);
        save = findViewById(R.id.activity_product_save);

        final DatabaseHandler dh = DatabaseHandler.getInstance(getApplicationContext());
        StoreControl storeControl = new StoreControl();
        CategoryControl categoryControl = new CategoryControl();
        final ItemProductControl itemProductControl = new ItemProductControl();

        ArrayList<Store> arStore = storeControl.getStores(dh);
        ArrayList<Category> arCategory = categoryControl.getCategories(dh);

        ArrayAdapter<Category> adCategory = new ArrayAdapter<Category>(this,
                R.layout.support_simple_spinner_dropdown_item, arCategory);
        category.setAdapter(adCategory);

        ArrayAdapter<Store> adStore = new ArrayAdapter<Store>(this,
                R.layout.support_simple_spinner_dropdown_item, arStore);
        store.setAdapter(adStore);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemProduct item = new ItemProduct();

                item.setTitle(title.getText().toString());
                item.setStore((Store) store.getSelectedItem());
                item.setCategory((Category) category.getSelectedItem());
                item.setImage(getResources().getIdentifier(img.getSelectedItem().toString(), "drawable", getPackageName()));

                itemProductControl.addItemProduct(item, dh);

                finish();
            }
        });

    }
}
