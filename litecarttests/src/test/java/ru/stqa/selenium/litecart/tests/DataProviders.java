package ru.stqa.selenium.litecart.tests;

import org.testng.annotations.DataProvider;
import ru.stqa.selenium.litecart.model.Product;

import java.io.File;

public class DataProviders {


    @DataProvider(name = "validProduct")
    public static Object[][] validProduct() {
        File productImage = new File("src/test/resources/redDuck.png");
        String shortDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sollicitudin ante massa, eget ornare libero porta congue.";
        String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sollicitudin ante massa, eget ornare libero porta congue. Cras scelerisque dui non consequat sollicitudin. Sed pretium tortor ac auctor molestie. Nulla facilisi. Maecenas pulvinar nibh vitae lectus vehicula semper. Donec et aliquet velit. Curabitur non ullamcorper mauris. In hac habitasse platea dictumst. Phasellus ut pretium justo, sit amet bibendum urna. Maecenas sit amet arcu pulvinar, facilisis quam at, viverra nisi. Morbi sit amet adipiscing ante. Integer imperdiet volutpat ante, sed venenatis urna volutpat a. Proin justo massa, convallis vitae consectetur sit amet, facilisis id libero.";
        return new Object[][] {
                { Product.newProduct()
                        .withProductName("Luxury Red Duck").withProductCode("RD003").withQuantity("1")
                        .withQuantityUnit("pcs").withDeliveryStatus("3-5 days").withSoldOutStatus("Temporary sold out")
                        .withProductImage(productImage).withDateValidFrom("01012022").withDateValidTo("31012022")
                        .withManufacturer("ACME Corp.").withShortDescription(shortDescription).withDescription(description)
                        .withPurchasePriceAmount("25").withPurchasePriceCurrency("US Dollars").withUsdPriceAmount("30")
                        .withCampaignStartDate("05012022").withCampaignStartTime("0000").withCampaignEndDate("15012022")
                        .withCampaignEndTime("2300").withCampaignUSDPriceAmount("22").build() },
                /* ... */
        };
    }

}
