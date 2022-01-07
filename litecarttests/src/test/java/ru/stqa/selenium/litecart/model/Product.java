package ru.stqa.selenium.litecart.model;

import java.io.File;

public class Product {

    private String productName;
    private String productCode;
    private String quantity;
    private String quantityUnit;
    private String deliveryStatus;
    private String soldOutStatus;
    private File productImage;
    private String dateValidFrom;
    private String dateValidTo;
    private String manufacturer;
    private String shortDescription;
    private String description;
    private String purchasePriceAmount;
    private String purchasePriceCurrency;
    private String usdPriceAmount;
    private String campaignStartDate;
    private String campaignStartTime;
    private String campaignEndDate;
    private String campaignEndTime;
    private String campaignUSDPriceAmount;


    public static Builder newProduct() { return new Product().new Builder(); }


    public String getProductName() {
        return productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getQuantity() {
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

    public File getProductImage() {
        return productImage;
    }

    public String getDateValidFrom() { return dateValidFrom; }

    public String getDateValidTo() { return dateValidTo; }

    public String getManufacturer() { return manufacturer; }

    public String getShortDescription() { return shortDescription; }

    public String getDescription() { return description; }

    public String getPurchasePriceAmount() { return purchasePriceAmount; }

    public String getPurchasePriceCurrency() { return purchasePriceCurrency; }

    public String getUsdPriceAmount() { return usdPriceAmount; }

    public String getCampaignStartDate() { return campaignStartDate; }

    public String getCampaignStartTime() { return campaignStartTime; }

    public String getCampaignEndDate() { return campaignEndDate; }

    public String getCampaignEndTime() { return campaignEndTime; }

    public String getCampaignUSDPriceAmount() { return campaignUSDPriceAmount; }



    public class Builder {
        private Builder() {
        }

        public Builder withProductName(String productName) {
            Product.this.productName = productName;
            return this;
        }

        public Builder withProductCode(String productCode) {
            Product.this.productCode = productCode;
            return this;
        }

        public Builder withQuantity(String quantity) {
            Product.this.quantity = quantity;
            return this;
        }

        public Builder withQuantityUnit(String quantityUnit) {
            Product.this.quantityUnit = quantityUnit;
            return this;
        }

        public Builder withDeliveryStatus(String deliveryStatus) {
            Product.this.deliveryStatus = deliveryStatus;
            return this;
        }

        public Builder withSoldOutStatus(String soldOutStatus) {
            Product.this.soldOutStatus = soldOutStatus;
            return this;
        }

        public Builder withProductImage(File productImage) {
            Product.this.productImage = productImage;
            return this;
        }

        public Builder withDateValidFrom(String dateValidFrom) {
            Product.this.dateValidFrom = dateValidFrom;
            return this;
        }

        public Builder withDateValidTo(String dateValidTo) {
            Product.this.dateValidTo = dateValidTo;
            return this;
        }

        public Builder withManufacturer(String manufacturer) {
            Product.this.manufacturer = manufacturer;
            return this;
        }

        public Builder withShortDescription(String shortDescription) {
            Product.this.shortDescription = shortDescription;
            return this;
        }

        public Builder withDescription(String description) {
            Product.this.description = description;
            return this;
        }

        public Builder withPurchasePriceAmount(String purchasePriceAmount) {
            Product.this.purchasePriceAmount = purchasePriceAmount;
            return this;
        }

        public Builder withPurchasePriceCurrency(String purchasePriceCurrency) {
            Product.this.purchasePriceCurrency = purchasePriceCurrency;
            return this;
        }

        public Builder withUsdPriceAmount(String usdPriceAmount) {
            Product.this.usdPriceAmount = usdPriceAmount;
            return this;
        }

        public Builder withCampaignStartDate(String campaignStartDate) {
            Product.this.campaignStartDate = campaignStartDate;
            return this;
        }

        public Builder withCampaignStartTime(String campaignStartTime) {
            Product.this.campaignStartTime = campaignStartTime;
            return this;
        }

        public Builder withCampaignEndDate(String campaignEndDate) {
            Product.this.campaignEndDate = campaignEndDate;
            return this;
        }

        public Builder withCampaignEndTime(String campaignEndTime) {
            Product.this.campaignEndTime = campaignEndTime;
            return this;
        }

        public Builder withCampaignUSDPriceAmount(String campaignUSDPriceAmount) {
            Product.this.campaignUSDPriceAmount = campaignUSDPriceAmount;
            return this;
        }

        public Product build() {return Product.this; }

    }

}
