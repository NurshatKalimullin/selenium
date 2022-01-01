package ru.stqa.selenium.litecart.model;

import java.io.File;

public class ProductData {

    private final String productName;
    private final String productCode;
    private final String quantity;
    private final String quantityUnit;
    private final String deliveryStatus;
    private final String soldOutStatus;
    private final File productImage;
    private final String dateValidFrom;
    private final String dateValidTo;
    private final String manufacturer;
    private final String shortDescription;
    private final String description;
    private final String purchasePriceAmount;
    private final String purchasePriceCurrency;
    private final String usdPriceAmount;
    private final String campaignStartDate;
    private final String campaignStartTime;
    private final String campaignEndDate;
    private final String campaignEndTime;
    private final String campaignUSDPriceAmount;

    public ProductData(String productName, String productCode, String quantity, String quantityUnit,
                       String deliveryStatus, String soldOutStatus, File productImage, String dateValidFrom,
                       String dateValidTo, String manufacturer, String shortDescription, String description,
                       String purchasePriceAmount, String purchasePriceCurrency, String usdPriceAmount,
                       String campaignStartDate, String campaignStartTime, String campaignEndDate,
                       String campaignEndTime, String campaignUSDPriceAmount){

        this.productName = productName;
        this.productCode = productCode;
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
        this.deliveryStatus = deliveryStatus;
        this.soldOutStatus = soldOutStatus;
        this.productImage = productImage;
        this.dateValidFrom = dateValidFrom;
        this.dateValidTo = dateValidTo;
        this.manufacturer = manufacturer;
        this.shortDescription = shortDescription;
        this.description = description;
        this.purchasePriceAmount = purchasePriceAmount;
        this.purchasePriceCurrency = purchasePriceCurrency;
        this.usdPriceAmount = usdPriceAmount;
        this.campaignStartDate = campaignStartDate;
        this.campaignStartTime = campaignStartTime;
        this.campaignEndDate = campaignEndDate;
        this.campaignEndTime = campaignEndTime;
        this.campaignUSDPriceAmount = campaignUSDPriceAmount;

    }

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

}
