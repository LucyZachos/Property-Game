package com.example.propertygame;

//import classes
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    //initialize tableview
    public TableView<PropertyPrices> propertyTableView;
    public TableColumn<PropertyPrices, String> colProperty;
    public TableColumn<PropertyPrices, String> colPrice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colProperty.setCellValueFactory(new PropertyValueFactory<>("Property"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        propertyTableView.setItems(observableList);
    }

    //insert tableView data
    ObservableList<PropertyPrices> observableList = FXCollections.observableArrayList(
            new PropertyPrices("House", "R700 000"),
            new PropertyPrices("Flat", "R150 000"),
            new PropertyPrices("Duplex", "R300 000"),
            new PropertyPrices("Loft", "R180 000")
    );

    @FXML
    private Label purchaseText;

    @FXML
    private Label capitalText;

    @FXML
    public Button confirmButton;

    @FXML
    private TextField capitalInputTextField;

    @FXML
    private TextField purchasePriceTextField;

    //Button event
    @FXML
    public void onClick(ActionEvent event) {

        int total;
        //Checks to see that there are no empty fields
        if (capitalInputTextField.getText().isEmpty() || purchasePriceTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("");
            alert.setHeaderText("Empty fields detected");
            alert.setContentText("Please ensure that you have entered information for BOTH weight and height.");
            alert.showAndWait();
            //checks that all data entered is numerical
        } else if (capitalInputTextField.getText().matches("[A-Z]") || purchasePriceTextField.getText().matches("[A-Z]")) {
            Alert alertInt = new Alert(Alert.AlertType.ERROR);
            alertInt.setTitle("Error");
            alertInt.setHeaderText("Please avoid the user of non-numerical characters");
            alertInt.showAndWait();
        } else {
            //parse integers to strings
            int startingCapital = Integer.parseInt(capitalInputTextField.getText());
            int purchasePrice = Integer.parseInt(purchasePriceTextField.getText());

            //calculation for determining the total
            total = startingCapital - purchasePrice;

            //calculation to determine discount
            int discountedTotal = (int) ((purchasePrice * 15.0f) / 100);

            try {
                // conditions

                if (purchasePrice > startingCapital) {
                    Alert alertDiscount = new Alert(Alert.AlertType.INFORMATION);
                    alertDiscount.setTitle("Purchase Information");
                    alertDiscount.setHeaderText("Purchase could not be saved. You do no have enough funds. You are short by: R" + total + " Please try again");
                    alertDiscount.showAndWait();
                    //clear purchasePriceTextField of previous data
                    purchasePriceTextField.clear();
                    //if user enters 0, close the application
                    if (purchasePriceTextField.getText().equals(String.valueOf(0))) {
                        System.exit(0);
                    }

                } else if (purchasePrice < startingCapital) {
                    if (purchasePrice >= 500000) {
                        Alert alertFailure = new Alert(Alert.AlertType.INFORMATION);
                        alertFailure.setTitle("Purchase Information");
                        alertFailure.setHeaderText("Your purchase was more than R500 000.00. You have received a discount of 15%. Your new total is R: " + discountedTotal);
                        alertFailure.showAndWait();
                        capitalInputTextField.setText(String.valueOf(discountedTotal));
                        purchasePriceTextField.clear();
                        if (purchasePriceTextField.getText().equals(String.valueOf(0))) {
                            System.exit(0);
                        }
                    } else {
                        Alert alertSuccess = new Alert(Alert.AlertType.CONFIRMATION);
                        alertSuccess.setTitle("Purchase Information");
                        alertSuccess.setHeaderText("Purchase saved. You have R" + (total) + " left in change. Would you like to make another purchase? Or press cancel to exit.");

                        //if user enters 0, close the application
                        if (purchasePriceTextField.getText().equals(String.valueOf(0))) {
                            System.exit(0);
                        }
                        //clear purchasePriceTextField of previous data
                        purchasePriceTextField.clear();
                        //set cancel button to close application
                        Optional<ButtonType> result = alertSuccess.showAndWait();
                        if (result.get() == ButtonType.CANCEL) {
                            System.exit(0);
                        } else {
                            capitalInputTextField.setText(String.valueOf(total));
                        }
                    }
                }
                //catch exception
            } catch (ArithmeticException a) {
                System.out.println("An arithmetic exception has occurred. Please review the error");
                //catch exception
            } catch (NumberFormatException f) {
                System.out.println("An NumberFormat Exception has occurred. Please review the error");
                //catch exception
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}