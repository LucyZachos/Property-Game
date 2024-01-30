package com.example.propertygame;

import javafx.beans.property.SimpleStringProperty;

public class PropertyPrices {

    private SimpleStringProperty Property;
    private SimpleStringProperty Price;


    public PropertyPrices(String property, String price) {
        Property = new SimpleStringProperty(property);
        Price = new SimpleStringProperty(price);
    }

    public String getProperty() {
        return Property.get();
    }

    public void setProperty(String property) {
        Property = new SimpleStringProperty(property) ;
    }

    public String getPrice() {
        return Price.get();
    }

    public void setPrice(String price) {
        Price = new SimpleStringProperty(price) ;
    }
}
