package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.AttendanceView;
import util.AttendanceController;
import view.tm.AttendanceTm;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AttendanceViewController {
    public Label lblDate;
    public Label lblAttendanceCount;
    public AnchorPane attendanceContext;
    public TableView<AttendanceTm>tblAttendance;
    public TableColumn colAttendId;
    public TableColumn colEmpId;
    public TableColumn colEmpName;
    public TableColumn colEmpType;
    public TableColumn colAttendTime;

    public void initialize(){

        colAttendId.setCellValueFactory(new PropertyValueFactory<>("attendanceId"));
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("employeName"));
        colEmpType.setCellValueFactory(new PropertyValueFactory<>("employeeType"));
        colAttendTime.setCellValueFactory(new PropertyValueFactory<>("attendTime"));

        try {
            loadAllData();
            loadDate();


        tblAttendance.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                loadDetailsUi(newValue.getAttendanceId());
            } catch ( IOException e ) {
                e.printStackTrace();
            } catch ( SQLException e ) {
                e.printStackTrace();
            } catch ( ClassNotFoundException e ) {
                e.printStackTrace();
            }
        });

        } catch ( SQLException e ) {
            e.printStackTrace();
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        }
    }

    private void loadDate() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

    }

    public void goToGrnManageOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AttendanceManageView.fxml");
        Parent load = FXMLLoader.load(resource);
        attendanceContext.getChildren().clear();
        attendanceContext.getChildren().add(load);

    }

    private void loadDetailsUi(String attendId) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/ItemDetailsView.fxml"));
        Parent load = fxmlLoader.load();
        ItemDetailViewController controller = fxmlLoader.getController();
        controller.loadAllData(attendId);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setTitle("Item Detail Table");
        stage.setScene(scene);
        stage.show();
    }

    private void loadAllData() throws SQLException, ClassNotFoundException {
        ObservableList<AttendanceTm> tmList = FXCollections.observableArrayList();
        for( AttendanceView tempAttend: new AttendanceController().getAllAttendance()
        ){
            tmList.add(
                    new AttendanceTm(tempAttend.getAttendanceId(), tempAttend.getEmployeeId(),
                            tempAttend.getEmployeName(), tempAttend.getEmployeeType(), tempAttend.getAttendTime())
            );
        }
        tblAttendance.setItems(tmList);
    }
}
