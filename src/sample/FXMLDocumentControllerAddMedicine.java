package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.DataSource;
import sample.model.Medicines;
import sample.model.UserData;

public class FXMLDocumentControllerAddMedicine {
    @FXML
    private TextField med_name;
    @FXML
    private TextField company_name;
    @FXML
    private TextField quantity;
    @FXML
    private TextField price;
    @FXML
    private ListView<Medicines> medicinesListView;
    @FXML
    private TextField searchMed;
    @FXML
    private Button searchButton;
    public void initialize(){
        try {
            medicinesListView.getItems().clear();
            String search = searchMed.getText();
            DataSource dataSource = new DataSource();
            dataSource.connectionOpen();
            if(DataSource.MedNameHashMap.isEmpty()) dataSource.createMedicineList();
            DataSource.MedNameHashMap.forEach((k,v)-> {
                medicinesListView.getItems().add(v);
            });
            dataSource.connectionClose();
        } catch (Exception e) {
            System.out.println("Exception:(onClickSearchMedicine) " + e.getMessage());
        }
    }
    public void onClickSearchMedicine(ActionEvent actionEvent) {
        try {
            medicinesListView.getItems().clear();
            String search = searchMed.getText();
            DataSource dataSource = new DataSource();
            DataSource.MedNameHashMap.forEach((k,v)-> {
                if(k.contains(search)){
                    medicinesListView.getItems().add(v);
                }
            });
            dataSource.connectionClose();
        } catch (Exception e) {
            System.out.println("Exception:(onClickSearchMedicine) " + e.getMessage());
        }
    }
    public void fillData(){
        try {
            Medicines selectedItemsMedicine = medicinesListView.getSelectionModel().getSelectedItem();
            med_name.setText(selectedItemsMedicine.getName());
            company_name.setText(selectedItemsMedicine.getCompany());
            quantity.setText(String.format("%d",selectedItemsMedicine.getQuantity()));
            price.setText(String.format("%f",selectedItemsMedicine.getMed_price()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void onClickProceed(ActionEvent actionEvent){
        try {
            Medicines newMed= new Medicines();
            DataSource dataSource = new DataSource();
            dataSource.connectionOpen();
            Medicines selectedItemsMedicine = medicinesListView.getSelectionModel().getSelectedItem();
            if(selectedItemsMedicine!=null){
                DataSource.selectedMedicine = selectedItemsMedicine;
                DataSource.selectedMedicine.setQuantity(Integer.parseInt(quantity.getText()));
            }else {
                newMed.setName(med_name.getText());
                newMed.setQuantity(Integer.parseInt(quantity.getText()));
                newMed.setMed_price(Double.parseDouble(price.getText()));
                newMed.setCompany(company_name.getText());
                DataSource.selectedMedicine = newMed;
            }
            dataSource.addMedicine();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void onBackClicked(ActionEvent actionEvent){
        try {
            Stage primaryStage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
            Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            primaryStage.setTitle("Hello ");
            primaryStage.setScene(new Scene(root, 750, 600));
            primaryStage.show();
        }catch (Exception e){
            System.out.println("Exception:" + e.getMessage());
        }
    }
}