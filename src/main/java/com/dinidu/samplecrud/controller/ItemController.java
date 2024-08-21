package com.dinidu.samplecrud.controller;

import com.dinidu.samplecrud.model.ItemModel;
import com.dinidu.samplecrud.dto.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemController {
    private final ItemModel ITEM_MODEL;

    @FXML
    private TableView<Item> itemTableView;
    @FXML
    private TableColumn<Item, String> itemCodeColumn;
    @FXML
    private TableColumn<Item, String> descriptionColumn;
    @FXML
    private TableColumn<Item, String> packSizeColumn;
    @FXML
    private TableColumn<Item, String> unitPriceColumn;
    @FXML
    private TableColumn<Item, Integer> qtyOnHandColumn;

    @FXML
    private TextField itemCodeField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField packSizeField;
    @FXML
    private TextField unitPriceField;
    @FXML
    private TextField qtyOnHandField;

    @FXML
    private Button saveButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;

    public ItemController() {
        this.ITEM_MODEL = new ItemModel();
    }

    @FXML
    public void initialize() {
        itemCodeColumn.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        packSizeColumn.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        unitPriceColumn.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        qtyOnHandColumn.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        try {
            loadData();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        itemTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Item selectedItem = itemTableView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    displayItemDetails(selectedItem);
                }
            }
        });

        saveButton.setOnAction(event -> saveItem());
        updateButton.setOnAction(event -> updateItem());
        deleteButton.setOnAction(event -> deleteItem());
    }

    private void loadData() throws SQLException, ClassNotFoundException {
        ArrayList<Item> items = ITEM_MODEL.getAllItems();
        ObservableList<Item> itemList = FXCollections.observableArrayList(items);
        itemTableView.setItems(itemList);
    }

    private void displayItemDetails(Item item) {
        itemCodeField.setText(item.getItemCode());
        descriptionField.setText(item.getDescription());
        packSizeField.setText(item.getPackSize());
        unitPriceField.setText(item.getUnitPrice());
        qtyOnHandField.setText(String.valueOf(item.getQtyOnHand()));
    }

    private void saveItem() {
        Item newItem = new Item(
                itemCodeField.getText(),
                descriptionField.getText(),
                packSizeField.getText(),
                unitPriceField.getText(),
                Integer.parseInt(qtyOnHandField.getText())
        );

        try {
            ITEM_MODEL.saveItem(newItem);
            showAlert("Success", "Item saved successfully.");
            clearFields();
            loadData(); // Reload data to show updated list
        } catch (SQLException | ClassNotFoundException e) {
            showAlert("Error", "Failed to save the item.");
            e.printStackTrace();
        }
    }

    private void updateItem() {
        Item updatedItem = new Item(
                itemCodeField.getText(),
                descriptionField.getText(),
                packSizeField.getText(),
                unitPriceField.getText(),
                Integer.parseInt(qtyOnHandField.getText())
        );

        try {
            ITEM_MODEL.updateItem(updatedItem);
            showAlert("Success", "Item updated successfully.");
            clearFields();
            loadData(); // Reload data to show updated list
        } catch (SQLException | ClassNotFoundException e) {
            showAlert("Error", "Failed to update the item.");
            e.printStackTrace();
        }
    }

    private void deleteItem() {
        String itemCode = itemCodeField.getText();

        try {
            ITEM_MODEL.deleteItem(itemCode);
            showAlert("Success", "Item deleted successfully.");
            clearFields();
            loadData(); // Reload data to show updated list
        } catch (SQLException | ClassNotFoundException e) {
            showAlert("Error", "Failed to delete the item.");
            e.printStackTrace();
        }
    }

    private void clearFields() {
        itemCodeField.clear();
        descriptionField.clear();
        packSizeField.clear();
        unitPriceField.clear();
        qtyOnHandField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}