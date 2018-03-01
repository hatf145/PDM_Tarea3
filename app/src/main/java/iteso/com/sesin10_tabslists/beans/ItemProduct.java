package iteso.com.sesin10_tabslists.beans;

/**
 * Created by hecto on 26/02/2018.
 */

public class ItemProduct {
    private int image;
    private int seller;
    private String title;
    private String store;
    private String location;
    private String phone;
    private String description;

    public ItemProduct(){
        image = 0;
        title = "";
        store = "";
        location = "";
        phone = "";
        description = "";
    }

    public ItemProduct(String title, String store, String address,
                       String phone, int image, String description, int seller) {
        this.title = title;
        this.store = store;
        this.phone = phone;
        this.location = address;
        this.image = image;
        this.description = description;
        this.seller = seller;
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
