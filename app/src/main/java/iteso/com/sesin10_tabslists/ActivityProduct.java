package iteso.com.sesin10_tabslists;

import android.content.Intent;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import iteso.com.sesin10_tabslists.beans.ItemProduct;

public class ActivityProduct extends AppCompatActivity{
    ImageView image;
    EditText title, store, location, phone;
    Button save, cancel;
    ItemProduct myProduct, myProductB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        image = findViewById(R.id.activity_product_image);
        title = findViewById(R.id.activity_product_title);
        store = findViewById(R.id.activity_product_store);
        location = findViewById(R.id.activity_product_location);
        phone = findViewById(R.id.activity_product_phone);

        save = findViewById(R.id.activity_product_save);
        cancel = findViewById(R.id.activity_product_cancel);

        if(getIntent().getExtras() != null){
            myProduct = getIntent().getParcelableExtra("ITEM");
            if(myProduct != null){
                switch (myProduct.getImage()){
                    case 0:
                        image.setImageResource(R.drawable.mac);
                        break;
                    case 1:
                        image.setImageResource(R.drawable.alienware);
                        break;
                    case 2:
                        image.setImageResource(R.drawable.lanix);
                        break;
                        default:
                            image.setImageResource(R.drawable.mac);
                }
                title.setText(myProduct.getTitle());
                store.setText(myProduct.getStore().getName());
                location.setText(myProduct.getStore().getCity().getName());
                phone.setText(myProduct.getStore().getPhone());
            }
        }

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                myProductB = new ItemProduct();
                myProductB.setTitle(title.getText().toString());
                myProductB.getStore().setName(store.getText().toString());
                myProductB.getStore().getCity().setName(location.getText().toString());
                myProductB.getStore().setPhone(phone.getText().toString());
                myProductB.setCode(myProduct.getCode());
                myProductB.setImage(myProduct.getImage());

                Intent intent = new Intent();
                intent.putExtra("ITEM", myProductB);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

}
