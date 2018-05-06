package com.jacob.orderoid.Data;

public class FoodItem {
    private String Name, Desc, Price, Image, Discount, CategoryId;

    public FoodItem() {
    }

    public FoodItem(String name, String desc, String price, String image, String discount, String categoryId) {
        Name = name;
        Desc = desc;
        Price = price;
        Image = image;
        Discount = discount;
        CategoryId = categoryId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
    }
}
