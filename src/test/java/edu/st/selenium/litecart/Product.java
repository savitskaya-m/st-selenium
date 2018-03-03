package edu.st.selenium.litecart;

public class Product {
    private boolean status;
    private String name;
    private String code;
    private String defaultCategory;
    private String[] groups;
    private int quantity;
    private String quantityUnit;
    private String deliveryStatus;
    private String soldOutStatus;
    private String dateFrom;
    private String dateTo;
    private String imagePath;
    private String manufacturer;
    private String supplier;
    private String keywords;
    private String shortDescription;
    private String description;
    private String headTitle;
    private String metaDescription;
    private Float purchasePrice;
    private String currency;
    private Float priceUsd;
    private Float priceEur;

    public boolean getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getDefaultCategory() {
        return defaultCategory;
    }

    public String[] getGroups() {
        return groups;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public String getSoldOutStatus() {
        return soldOutStatus;
    }

    public String getImagePath(){
        return imagePath;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getSupplier() {
        return supplier;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getHeadTitle() {
        return headTitle;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public Float getPurchasePrice() {
        return purchasePrice;
    }

    public String getCurrency() {
        return currency;
    }

    public Float getPriceUsd() {
        return priceUsd;
    }

    public Float getPriceEur() {
        return priceEur;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDefaultCategory(String defaultCategory) {
        this.defaultCategory = defaultCategory;
    }

    public void setGroups(String[] groups) {
        this.groups = groups;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public void setSoldOutStatus(String soldOutStatus) {
        this.soldOutStatus = soldOutStatus;
    }

    public void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHeadTitle(String headTitle) {
        this.headTitle = headTitle;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public void setPurchasePrice(Float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setPriceUsd(Float priceUsd) {
        this.priceUsd = priceUsd;
    }

    public void setPriceEur(Float priceEur) {
        this.priceEur = priceEur;
    }
}
