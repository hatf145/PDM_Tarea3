package iteso.com.sesin10_tabslists.beans;

import android.content.ClipData;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hecto on 26/02/2018.
 */

public class ItemProduct implements Parcelable {
    private int image;
    private int seller;
    private int code;
    private String title;
    private String store;
    private String location;
    private String phone;
    private String description;

    public ItemProduct(Parcel in){
        image = in.readInt();
        title = in.readString();
        store = in.readString();
        location = in.readString();
        phone = in.readString();
        description = in.readString();
        code = in.readInt();
    }

    public static final Parcelable.Creator<ItemProduct> CREATOR =
            new Parcelable.Creator<ItemProduct>(){
                @Override
                public ItemProduct createFromParcel(Parcel source) {
                    return new ItemProduct(source);
                }

                @Override
                public ItemProduct[] newArray(int size) {
                    return new ItemProduct[size];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(image);
        dest.writeString(title);
        dest.writeString(store);
        dest.writeString(location);
        dest.writeString(phone);
        dest.writeString(description);
        dest.writeInt(code);
    }

    public ItemProduct(){
        image = 0;
        title = "";
        store = "";
        location = "";
        phone = "";
        description = "";
    }

    public ItemProduct(String title, String store, String address,
                       String phone, int image, String description, int seller, int code) {
        this.title = title;
        this.store = store;
        this.phone = phone;
        this.location = address;
        this.image = image;
        this.description = description;
        this.seller = seller;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getStore() {
        return store;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSeller() {
        return seller;
    }

    public void setSeller(int seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
        return  "Prodcut: '" + title + '\'' +
                "\nStore: '" + store + '\'' +
                "\nLocation: '" + location + '\'' +
                "\nPhone: '" + phone + '\'' +
                "\nDescription: '" + description + '\'';
    }
}
