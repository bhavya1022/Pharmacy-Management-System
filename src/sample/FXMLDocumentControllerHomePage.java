package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import sample.model.DataSource;

import java.io.IOException;
import java.util.jar.Attributes;

public class FXMLDocumentControllerHomePage {
    @FXML
    private Label notiNo;
    @FXML
    public Label name;
    @FXML
    public Label email;
    @FXML
    private ImageView setting;
    @FXML
    private ImageView notify;
    @FXML
    private Button logout;
    @FXML
    private Button details;
    @FXML
    private Button transactionDetails;
    @FXML
    private Button AddMed;
    @FXML
    private Button searchMed;
    private final ListView<Label> stringListView = new ListView<>();
    public void initialize(){
        try{
            name.setText(DataSource.loginBoy.getEmp_name());
            name.setTextFill(Color.WHITE);
            name.setTextAlignment(TextAlignment.CENTER);
            email.setText(DataSource.loginBoy.getEmail());
            email.setTextFill(Color.WHITE);
            email.setTextAlignment(TextAlignment.CENTER);
            if(DataSource.notificationList.isEmpty()) notiNo.setVisible(false);
            else notiNo.setText(DataSource.notificationList.size() + "");
            details.setText(DataSource.loginBoy.getEmp_role() + " Details");
            if(DataSource.loginBoy.getEmp_role().equals("Pharmacist")){
                searchMed.setVisible(false);
            }else if (DataSource.loginBoy.getEmp_role().equals("Cashier")){
                AddMed.setVisible(false);
            }
        }catch (Exception e){
            System.out.println("Exception: " +e.getMessage());
        }
    }
    public void onLogOutClicked(ActionEvent actionEvent){
        try{
            try{
                DataSource.loginBoy = null;
                DataSource.employees.clear();
                DataSource.selectedPatient = null;
                DataSource.MedNameHashMap.clear();
                DataSource.amount = -1;
                Stage primaryStage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
                Parent root = FXMLLoader.load(getClass().getResource("Registration.fxml"));
                primaryStage.setTitle("Hello ");
                primaryStage.setScene(new Scene(root, 750, 600));
                primaryStage.show();
            }catch (IOException exception){
                System.out.println("Exception: (login->homePage)" + exception);
            }
        }catch (Exception e){
            System.out.println("Exception(onLogoutClicked):" + e.getMessage());
        }
    }
    public  void onSettingCLicked(ActionEvent actionEvent){
        try{
            try{
                Stage primaryStage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
                Parent root = FXMLLoader.load(getClass().getResource("SettingsPage.fxml"));
                primaryStage.setTitle("Hello ");
                primaryStage.setScene(new Scene(root, 750, 600));
                primaryStage.show();
            }catch (IOException exception){
                System.out.println("Exception: (login->homePage)" + exception);
                exception.printStackTrace();
            }
        }catch (Exception e){
            System.out.println("Exception(onSettingClicked):" + e.getMessage());
        }
    }
    public void onNotificationClicked(ActionEvent actionEvent){
        try{
            Stage notifyStage = new Stage();
            BorderPane bp = new BorderPane();
            Button clear = new Button("Clear All notification");
            clear.setOnAction(this::onClearClicked);
            if(DataSource.notificationList.isEmpty()) DataSource.notificationList.add(new Label("No Notification"));
            stringListView.getItems().addAll(DataSource.notificationList);
            bp.setBottom(clear);
            bp.setCenter(stringListView);
            notifyStage.setTitle("Hello ");
            notifyStage.setScene(new Scene(bp,300, 200));
            notifyStage.show();
        }catch (Exception e){
            System.out.println("Exception(onNotificationClicked):" + e.getMessage());
        }
    }

    private void onClearClicked(ActionEvent actionEvent) {
        DataSource.notificationList.clear();
        stringListView.getItems().clear();
        notiNo.setVisible(false);
        ((Stage) (((Node) actionEvent.getSource()).getScene().getWindow())).close();
    }

    public void onDetailsClicked(ActionEvent actionEvent){
        try{
            Stage primaryStage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
            Parent root = FXMLLoader.load(getClass().getResource("Details.fxml"));
            primaryStage.setTitle("Hello ");
            primaryStage.setScene(new Scene(root, 750, 600));
            primaryStage.show();
        }catch (Exception e){
            System.out.println("Exception(): " + e.getMessage() );
        }
    }
    public void onTransactionClicked(ActionEvent actionEvent){
        try{
            Stage primaryStage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
            Parent root = FXMLLoader.load(getClass().getResource("RecordHistory.fxml"));
            primaryStage.setTitle("Hello ");
            primaryStage.setScene(new Scene(root, 750, 600));
            primaryStage.show();
        }catch (Exception e){
            System.out.println("Exception(): " + e.getMessage() );
            e.printStackTrace();
        }
    }public void onAddMedicineClicked(ActionEvent actionEvent){
        try{
            Stage primaryStage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
            Parent root = FXMLLoader.load(getClass().getResource("PharmacistAddMedicine.fxml"));
            primaryStage.setTitle("Hello ");
            primaryStage.setScene(new Scene(root, 750, 600));
            primaryStage.show();
        }catch (Exception e){
            System.out.println("Exception(): " + e.getMessage());
        }
    }
    public void onSearchClicked(ActionEvent actionEvent){
        try{
            Stage primaryStage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
            Parent root = FXMLLoader.load(getClass().getResource("Transaction.fxml"));
            primaryStage.setTitle("Hello ");
            primaryStage.setScene(new Scene(root, 750, 600));
            primaryStage.show();
        }catch (Exception e){
            System.out.println("Exception(): " + e.getMessage() );
        }
    }
}
