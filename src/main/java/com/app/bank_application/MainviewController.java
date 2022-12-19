package com.app.bank_application;

import bank.Singleton;
import bank.exceptions.AccountAlreadyExistsException;
import bank.exceptions.AccountDoesNotExistException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class MainviewController implements Initializable {

    @FXML
    private ListView<String> allAccounts;

    @FXML
    private ContextMenu Context;

    @FXML
    private MenuItem select;

    @FXML
    private MenuItem delete;

    private Singleton a = Singleton.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            a.deserialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> Accounts = a.Bank.getAllAccounts();
        allAccounts.getItems().addAll(Accounts.toArray(String[]::new));
    }

    @FXML
    void deleteAccount(ActionEvent event) throws IOException, AccountDoesNotExistException {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Unwiederuflich löschen");
        alert.setHeaderText("Sicher das Sie das Konto löschen wollen?");
        alert.setContentText("Sie können das nicht rückgängig machen!");
        ButtonType yes = new ButtonType("ja", ButtonBar.ButtonData.YES);
        ButtonType nein = new ButtonType("nein", ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(yes,nein);

        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == yes) {

            String Konto = allAccounts.getSelectionModel().getSelectedItem();
            File jsonFile = new File(a.Bank.getDirectoryName() + "\\" + Konto + ".json");

            if (jsonFile.delete()) {
                a.Bank.deleteAccount(Konto);
                allAccounts.getItems().remove(Konto);

            } else {
                throw new FileNotFoundException();
            }
        }
    }

    @FXML
    void selectAccount(ActionEvent event) throws IOException {

        String Konto = allAccounts.getSelectionModel().getSelectedItem();
        a.actualAccount = Konto;

        getAccountview();

    }

    public void getAccountview() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BankApplication.class.getResource("Accountview.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 920, 440);
        Stage actual_stage = (Stage)allAccounts.getScene().getWindow();

        actual_stage.setScene(scene);
    }

    @FXML
    void createAccount(ActionEvent event) throws AccountAlreadyExistsException, IOException {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Konto erstellen");
        dialog.setHeaderText("Konto erstellen, dazu wird der Name benötigt");
        dialog.setContentText("Bitte geben Sie den Namen ein:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                String name = result.get();
                a.Bank.createAccount(name);
                allAccounts.getItems().add(name);
                a.Bank.writeAccount(name);
            }
            catch(AccountAlreadyExistsException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Account schon vorhanden");
                alert.setHeaderText("Bitte wählen Sie einen anderen Namen");
                Optional<ButtonType> result2 = alert.showAndWait();
            }
        }

    }

}