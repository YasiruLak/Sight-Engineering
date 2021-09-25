package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ItemDetailView;
import util.AttendanceController;
import view.tm.ItemDetailViewTm;

import java.sql.SQLException;

public class ItemDetailViewController {
    public TableView tblItemDetail;
    public TableColumn colItemCode;
    public TableColumn colQuantity;
    public Label lblAttendId;
    public TableColumn colItemName;

    public void initialize(){

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }



    public void loadAllData(String id) throws SQLException, ClassNotFoundException {
        lblAttendId.setText(id);
        ObservableList<ItemDetailViewTm> tmList = FXCollections.observableArrayList();

        for( ItemDetailView itemDetailView: new AttendanceController().getAllItemDetail(id)
        ){
            tmList.add(new ItemDetailViewTm(itemDetailView.getItemCode(), itemDetailView.getItemName(), itemDetailView.getQuantity()));
        }
        tblItemDetail.setItems(tmList);
    }
}
