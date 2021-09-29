package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Item;
import util.ItemController;
import util.ValidationUtil;
import view.tm.ItemTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.regex.Pattern;

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
    public JFXButton btnSave;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXButton btnCancel;

    private ItemController controller = new ItemController();

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern idPattern = Pattern.compile("^(I00-)[0-9]{3,4}$");
    Pattern namePattern = Pattern.compile("^[A-z ]{3,30}$");
    Pattern descPattern = Pattern.compile("^[A-z ]{3,50}$");
    Pattern qtyPattern = Pattern.compile("^[0-9]{1,5}$");

    public void initialize() throws SQLException, ClassNotFoundException {

        btnSave.setDisable(true);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);

        storeValidation();

        try {

            colItmCode.setCellValueFactory(new PropertyValueFactory<>("id"));
            colItmName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colItmDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            colItmSize.setCellValueFactory(new PropertyValueFactory<>("size"));
            colItmQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

            itemToTable(controller.getAllItem());
        }catch ( Exception e ){
            e.printStackTrace();
        }

        cmbSize.getItems().addAll(
                "Small", "Medium", "Large"
        );
        cmbSize.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        });
    }

    private void storeValidation() {
        map.put(txtItmCode, idPattern);
        map.put(txtItmName, namePattern);
        map.put(txtItmDescription, descPattern);
        map.put(txtItmQtyOnHand, qtyPattern);
    }

    private void itemToTable(ArrayList<Item> allItem){
        ObservableList<ItemTm> itemList = FXCollections.observableArrayList();
        allItem.forEach(e ->{
            itemList.add(
                    new ItemTm(e.getId(),e.getName(),e.getDescription(),e.getSize(),e.getQtyOnHand()));
        });
        tblItem.setItems(itemList);
    }

    public void saveItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Item item = new Item(
                txtItmCode.getText(), txtItmName.getText(), txtItmDescription.getText(), cmbSize.getValue().toString(),
                Integer.parseInt(txtItmQtyOnHand.getText())
        );
        if(controller.saveItem(item)){
            new Alert(Alert.AlertType.CONFIRMATION, "Saved Successes").show();
            clear();
            btnSave.setDisable(true);
            itemToTable(controller.getAllItem());

        }else{
            new Alert(Alert.AlertType.ERROR, "Try Again").show();
            clear();
        }
    }

    public void updateItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Item item = new Item(
                txtItmCode.getText(), txtItmName.getText(), txtItmDescription.getText(), cmbSize.getValue().toString(),
                Integer.parseInt(txtItmQtyOnHand.getText())
        );
        if (controller.updateItem(item)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
            itemToTable(controller.getAllItem());
            clear();
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
            btnSave.setDisable(true);
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
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
            btnSave.setDisable(true);
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
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
            btnSave.setDisable(true);

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
        txtItmQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnSave);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Success").showAndWait();
            }
        }
    }
}
