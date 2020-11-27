package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import sample.model.Bill;
import sample.model.DataSource;
import sample.model.Medicines;

import java.util.Currency;
import java.util.Locale;

public class FXMLDocumentControllerBillRecord {
    @FXML
    private Label pat_name;
    @FXML
    private TableView<Medicines> medicinesTableView;
    @FXML
    private TableColumn<Medicines,String> name;
    @FXML
    private TableColumn<Medicines,Double> med_price;
    @FXML
    private TableColumn<Medicines,Integer> quant;
    @FXML
    private ImageView tick;
    @FXML
    private Label price;
    public void initialize(){
        try{
            ObservableList<Medicines> list = FXCollections.observableArrayList();
            list.addAll(DataSource.bill.getMed_id());
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            med_price.setCellValueFactory(new PropertyValueFactory<>("med_price"));
            quant.setCellValueFactory(new PropertyValueFactory<>("quant"));
            medicinesTableView.setItems(list);
            Currency indiaCurrency = Currency.getInstance(new Locale("en","IN"));
            price.setText("Total - " + indiaCurrency.getSymbol() + " " + (DataSource.bill.getBill_amount()));
            price.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
            pat_name.setText(DataSource.bill.getPat_name());

        }catch (Exception e){
            System.out.println("initialize");
            e.printStackTrace();
        }
    }
    public void onBackClicked(ActionEvent actionEvent){
        try {
            Stage primaryStage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
            Parent root = FXMLLoader.load(getClass().getResource("RecordHistory.fxml"));
            primaryStage.setTitle("Hello ");
            primaryStage.setScene(new Scene(root, 750, 600));
            primaryStage.show();
        }catch (Exception e){
            System.out.println("Exception:" + e.getMessage());
        }
    }
}