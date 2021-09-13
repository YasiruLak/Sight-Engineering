package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;
import util.ItemController;
import view.tm.ItemTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ItemManageViewController {
    public ComboBox cmbSize;
    public TableView<ItemTm>tblItem;
    public TableColumn colItmCode;
    public TableColumn colItmName;
    public TableColumn colItmDescription;
    public TableColumn colItmSize;
    public TableColumn colItmQtyOnHand;
    public TextField txtItmCode;
    public TextField txtItmName;
    public TextField txtItmDescription;
    public TextField txtItmQtyOnHand;
    public TextField txtItmSearch;

    private ItemController controller = new ItemController();

    public void initialize() throws SQLException, ClassNotFoundException {

        colItmCode.setCellValueFactory(new PropertyValueFactory<>("id"));
        colItmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colItmDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colItmSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colItmQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        itemToTable(controller.getAllItem());


        cmbSize.getItems().addAll(
                "Small", "Medium", "Large"
        );
        cmbSize.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        });
    }

    private void itemToTable(ArrayList<Item> allItem){
        ObservableList<ItemTm> itemList = FXCollections.observableArrayList();
        allItem.forEach(e ->{
            itemList.add(
                    new ItemTm(e.getId(),e.getName(),e.getDescription(),e.getSize(),e.getQtyOnHand())
            );
            tblItem.setItems(itemList);
        });

    }
    public void saveItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Item item = new Item(
                txtItmCode.getText(), txtItmName.getText(), txtItmDescription.getText(), cmbSize.getValue().toString(),
                txtItmQtyOnHand.getText()
        );
        if(controller.saveItem(item)){
            new Alert(Alert.AlertType.CONFIRMATION, "Saved Successes").show();
            clear();
            itemToTable(controller.getAllItem());
        }else{
            new Alert(Alert.AlertType.ERROR, "Try Again").show();
            clear();
        }
    }

    public void updateItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Item item = new Item(
                txtItmCode.getText(), txtItmName.getText(), txtItmDescription.getText(), cmbSize.getValue().toString(),
                txtItmQtyOnHand.getText()
        );
        if (controller.updateItem(item)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
            itemToTable(controller.getAllItem());
            clear();
            }else{
                new Alert(Alert.AlertType.ERROR, "Try Again").show();
            }

    }

    public void deleteItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you suer you want to Delete?", yes, no);
        alert.setTitle("Confirmation alert");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.orElse(no) == yes) {
            if(controller.deleteItem(txtItmCode.getText()));
            new Alert(Alert.AlertType.INFORMATION, "Deleted").show();
            clear();
            itemToTable(controller.getAllItem());
        }else{
            new Alert(Alert.AlertType.ERROR, "Try Again").show();
            clear();
        }
    }

    public void cancelItemOnAction(ActionEvent actionEvent) {
        clear();

    }

    public void searchItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String itemCode = txtItmSearch.getText();
        Item item = controller.getItem(itemCode);
        if(item == null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set").show();
        }else{
            setData(item);
        }

    }

    public void clear(){
        txtItmCode.clear();
        txtItmDescription.clear();
        txtItmName.clear();
        txtItmQtyOnHand.clear();
        cmbSize.getSelectionModel().clearSelection();
        txtItmSearch.clear();
    }

    void setData(Item item){
        txtItmCode.setText(item.getId());
        txtItmName.setText(item.getName());
        txtItmDescription.setText(item.getDescription());
        cmbSize.setValue(item.getSize());
        txtItmQtyOnHand.setText(item.getQtyOnHand());


    }
}
