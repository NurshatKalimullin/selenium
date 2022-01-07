package ru.stqa.selenium.litecart.model;

import java.io.File;

public class ProductData {

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



    public ProductData withProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public ProductData withProductCode(String productCode) {
        this.productCode = productCode;
        return this;
    }

    public ProductData withQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public ProductData withQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
        return this;
    }

    public ProductData withDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
        return this;
    }

    public ProductData withSoldOutStatus(String soldOutStatus) {
        this.soldOutStatus = soldOutStatus;
        return this;
    }

    public ProductData withProductImage(File productImage) {
        this.productImage = productImage;
        return this;
    }

    public ProductData withDateValidFrom(String dateValidFrom) {
        this.dateValidFrom = dateValidFrom;
        return this;
    }

    public ProductData withDateValidTo(String dateValidTo) {
        this.dateValidTo = dateValidTo;
        return this;
    }

    public ProductData withManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public ProductData withShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
        return this;
    }

    public ProductData withDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductData withPurchasePriceAmount(String purchasePriceAmount) {
        this.purchasePriceAmount = purchasePriceAmount;
        return this;
    }

    public ProductData withPurchasePriceCurrency(String purchasePriceCurrency) {
        this.purchasePriceCurrency = purchasePriceCurrency;
        return this;
    }

    public ProductData withUsdPriceAmount(String usdPriceAmount) {
        this.usdPriceAmount = usdPriceAmount;
        return this;
    }

    public ProductData withCampaignStartDate(String campaignStartDate) {
        this.campaignStartDate = campaignStartDate;
        return this;
    }

    public ProductData withCampaignStartTime(String campaignStartTime) {
        this.campaignStartTime = campaignStartTime;
        return this;
    }

    public ProductData withCampaignEndDate(String campaignEndDate) {
        this.campaignEndDate = campaignEndDate;
        return this;
    }

    public ProductData withCampaignEndTime(String campaignEndTime) {
        this.campaignEndTime = campaignEndTime;
        return this;
    }

    public ProductData withCampaignUSDPriceAmount(String campaignUSDPriceAmount) {
        this.campaignUSDPriceAmount = campaignUSDPriceAmount;
        return this;
    }

}
