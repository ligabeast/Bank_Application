package com.app.bank_application;

import bank.Singleton;
import bank.Transaction;
import bank.exceptions.AccountDoesNotExistException;
import bank.exceptions.TransactionDoesNotExistException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.jar.JarEntry;

public class AccountviewController implements Initializable {

    @FXML
    private Label konto_balance;

    @FXML
    private Label konto_name;

    private Singleton a = Singleton.getInstance();

    private List<Transaction> Transactions = a.Bank.getTransactions(a.actualAccount);

    @FXML
    private ListView<String> allTransactions;

    @FXML
    private Button abwaerts;

    @FXML
    private Button aufwaerts;

    @FXML
    private Button negativ;

    @FXML
    private Button positiv;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        konto_balance.setText("Kontostand: "+ a.Bank.getAccountBalance(a.actualAccount));
        konto_name.setText("Konto: "+ a.actualAccount);

        setupListView();
    }

    @FXML
    void goBackToAccountView(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(BankApplication.class.getResource("Mainview.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 920, 440);

        Stage actual_stage = (Stage)konto_balance.getScene().getWindow();
        actual_stage.setScene(scene);

    }
    @FXML
    void deleteTransaction() throws AccountDoesNotExistException, TransactionDoesNotExistException, IOException {

        System.out.println(allTransactions.getSelectionModel().getSelectedIndex());

        if(allTransactions.getSelectionModel().getSelectedIndex() == -1){
            return;
        }

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Unwiederuflich löschen");
        alert.setHeaderText("Sicher das Sie das Konto löschen wollen?");
        alert.setContentText("Sie können das nicht rückgängig machen!");
        ButtonType yes = new ButtonType("ja", ButtonBar.ButtonData.YES);
        ButtonType nein = new ButtonType("nein", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(yes,nein);

        Optional<ButtonType> result = alert.showAndWait();


        if(result.get() == yes) {
            String actual = allTransactions.getSelectionModel().getSelectedItem();
            System.out.println(allTransactions.getSelectionModel().getSelectedIndex());
            Transaction tmp = Transactions.get(allTransactions.getSelectionModel().getSelectedIndex());

            a.Bank.removeTransaction(a.actualAccount, tmp);
            a.Bank.writeAccount(a.actualAccount);


            Transactions.remove(tmp);
            allTransactions.getItems().remove(actual);
            konto_balance.setText("Kontostand: "+ a.Bank.getAccountBalance(a.actualAccount));
        }
    }

    @FXML
    void createTransaction(ActionEvent event) {

        GridPane gridNode = new GridPane();
        gridNode.add(new Label("Datum: "), 0, 0);
        gridNode.add(new Label("Betrag: "), 0, 1);
        gridNode.add(new Label("Beschreibung: "), 0 , 2);
        gridNode.add(new TextField(), 1, 0 );
        gridNode.add(new TextField(), 1, 1 );
        gridNode.add(new TextField(), 1, 2);

        RadioButton transfer = new RadioButton();
        RadioButton payment = new RadioButton();
        ToggleGroup toggleGroup = new ToggleGroup();

        transfer.setToggleGroup(toggleGroup);
        payment.setToggleGroup(toggleGroup);
        transfer.setText("Transfer");
        payment.setText("Payment");

        gridNode.add(transfer, 0, 3 );
        gridNode.add(payment, 1, 3 );

        transfer.setOnAction(e -> {

            if(gridNode.contains(0,4)){
                gridNode.getChildren().clear();
                gridNode.add(new Label("Datum: "), 0, 0);
                gridNode.add(new Label("Betrag: "), 0, 1);
                gridNode.add(new Label("Beschreibung: "), 0 , 2);
                gridNode.add(new TextField(), 1, 0 );
                gridNode.add(new TextField(), 1, 1 );
                gridNode.add(new TextField(), 1, 2);
                gridNode.add(transfer, 0, 3 );
                gridNode.add(payment, 1, 3 );
                gridNode.add(new Label("Sender: "), 0, 4);
                gridNode.add(new Label("Empfänger: "), 0 , 5);
                gridNode.add(new TextField(), 1, 4 );
                gridNode.add(new TextField(), 1, 5 );
            }
            gridNode.add(new Label("Sender: "), 0, 4);
            gridNode.add(new Label("Empfänger: "), 0 , 5);
            gridNode.add(new TextField(), 1, 4 );
            gridNode.add(new TextField(), 1, 5 );
        });
        payment.setOnAction(e -> {
            if(gridNode.contains(0,4)){
                gridNode.getChildren().clear();
                gridNode.add(new Label("Datum: "), 0, 0);
                gridNode.add(new Label("Betrag: "), 0, 1);
                gridNode.add(new Label("Beschreibung: "), 0 , 2);
                gridNode.add(new TextField(), 1, 0 );
                gridNode.add(new TextField(), 1, 1 );
                gridNode.add(new TextField(), 1, 2);
                gridNode.add(transfer, 0, 3 );
                gridNode.add(payment, 1, 3 );
                gridNode.add(new Label("Einzahlungs-Zinsen: "), 0, 4);
                gridNode.add(new Label("Auszahlungs-Zinsen: "), 0 , 5);
                gridNode.add(new TextField(), 1, 4 );
                gridNode.add(new TextField(), 1, 5 );
            }

            gridNode.add(new Label("Einzahlungs-Zinsen: "), 0, 4);
            gridNode.add(new Label("Auszahlungs-Zinsen: "), 0 , 5);
            gridNode.add(new TextField(), 1, 4 );
            gridNode.add(new TextField(), 1, 5 );
        });


        Dialog newTransaction = new Dialog();
        newTransaction.setTitle("Erstelle neue Transaktion");
        newTransaction.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        newTransaction.getDialogPane().getButtonTypes().add(ButtonType.YES);
        newTransaction.getDialogPane().setContent(gridNode);

        newTransaction.setResizable(true);
        newTransaction.getDialogPane().setMinSize(300,300);

        newTransaction.show();



    }

    public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }

    @FXML
    void sortAsc(ActionEvent event) {
        allTransactions.getItems().clear();
        Transactions = a.Bank.getTransactionsSorted(a.actualAccount, true);
        setupListView();
    }

    @FXML
    void sortDesc(ActionEvent event) {
        allTransactions.getItems().clear();
        Transactions = a.Bank.getTransactionsSorted(a.actualAccount, false);
        setupListView();
    }

    @FXML
    void sortNegativ(ActionEvent event) {
        allTransactions.getItems().clear();
        Transactions = a.Bank.getTransactionsByType(a.actualAccount, false);
        setupListView();
    }

    @FXML
    void sortPositiv(ActionEvent event) {
        allTransactions.getItems().clear();
        Transactions = a.Bank.getTransactionsByType(a.actualAccount, true);
        setupListView();
    }

    private void setupListView(){
        for (Transaction tmp: Transactions ){
            allTransactions.getItems().add(String.valueOf(tmp));
        }
    }

}
