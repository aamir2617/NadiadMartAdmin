package com.example.nadiadmartadmin.addProductActivity;

public class ProductDetails {

    String productName,offerPrice,originalPrice,code,category,imageUrl;

    public ProductDetails() {
    }

    public ProductDetails(String productName, String offerPrice, String originalPrice, String code, String category, String imageUrl) {
        this.productName = productName;
        this.offerPrice = offerPrice;
        this.originalPrice = originalPrice;
        this.code = code;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(String offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
