package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Item;
import util.ItemController;
import view.tm.ItemTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemViewController {
    public AnchorPane itemStockContext;
    public TableView<ItemTm>tblItem;
    public TableColumn colItmCode;
    public TableColumn colItmName;
    public TableColumn colItmDescription;
    public TableColumn colItmSize;
    public TableColumn colItmQtyOnHand;

    public void initialize() throws SQLException, ClassNotFoundException {

        colItmCode.setCellValueFactory(new PropertyValueFactory<>("id"));
        colItmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colItmDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colItmSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colItmQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        itemToTable(new ItemController().getAllItem());
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

    public void goToItemManage(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ItemManageView.fxml");
        Parent load = FXMLLoader.load(resource);
        itemStockContext.getChildren().clear();
        itemStockContext.getChildren().add(load);
    }
}
