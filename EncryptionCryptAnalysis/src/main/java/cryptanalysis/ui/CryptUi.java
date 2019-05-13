/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptanalysis.ui;

import cryptanalysis.braking.BreakingCeasarCipher;
import cryptanalysis.braking.FrequencyAnalysis;
import cryptanalysis.ciphers.CaesarCipher;
import cryptanalysis.ciphers.VigenereCipher;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author milla
 */
public class CryptUi extends Application {

    /**
     *
     * @param stage
     */
    @Override
    public void start(Stage stage) {

        CaesarCipher ceasar = new CaesarCipher();
        VigenereCipher vigenere = new VigenereCipher();
        BreakingCeasarCipher b = new BreakingCeasarCipher();
        FrequencyAnalysis f = new FrequencyAnalysis();

        BorderPane start = new BorderPane();
        Button testCeasar = new Button("Test Ceasar");
        Button testVinenere = new Button("Test Vigenère");
        HBox startbuttons = new HBox();
        startbuttons.getChildren().addAll(testCeasar, testVinenere);
        startbuttons.setSpacing(10);
        start.setCenter(startbuttons);
        start.setPadding(new Insets(10, 10, 10, 10));

        GridPane pane = new GridPane();

        Label cipher = new Label("Ceasar cipher");
        Label encrypt = new Label("Encrypt");
        TextArea encryption = new TextArea();

        Label decrypt = new Label("Decrypt");
        TextArea decryption = new TextArea();
        Button enButton = new Button("Encrypt");
        Button deButton = new Button("Decrypt");
        Label choice = new Label("Shift");
        Label time = new Label("Time");

        ChoiceBox c = new ChoiceBox();
        c.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26);
        c.setValue(1);

        TextArea options = new TextArea();
        Button optionsbut = new Button("Options");

        Button freq = new Button("Frequency analysis");
        Label key = new Label("Key: ");

        GridPane group = new GridPane();
        group.setAlignment(Pos.CENTER);
        group.setMinSize(600, 300);
        group.add(cipher, 0, 0); //toka on rivi
        group.add(encrypt, 1, 1);
        group.add(choice, 1, 2);
        group.add(c, 1, 3);

        group.add(encryption, 1, 4);
        group.add(decrypt, 4, 1);
        group.add(decryption, 4, 4);

        HBox buttons = new HBox();
        buttons.getChildren().addAll(enButton, deButton);
        buttons.setSpacing(10);
        group.add(buttons, 2, 4);
        group.add(optionsbut, 1, 6);
        group.add(options, 1, 7);
        group.add(time, 4, 7);
        group.add(freq, 4, 5);
        group.add(key, 4, 6);

        group.setHgap(10);
        group.setVgap(25);
        group.setPadding(new Insets(10, 10, 10, 10));

        Scene ceasarScene = new Scene(group);

        testCeasar.setOnAction((event) -> {
            stage.setScene(ceasarScene);
        });

        enButton.setOnAction((event) -> {

            // TESTAUSTA
            String key1 = vigenere.makeKey("hellot", "key");
            System.out.println(key1);
            String encr = vigenere.encryption("TESTTEXT", "KEY");
            System.out.println(encr);
            // YLLÄ TEST
            
            String original = encryption.getText();
            int n = (int) c.getValue();
            String s = ceasar.encryption(original, n);
            decryption.setText(s);

        });

        deButton.setOnAction((event) -> {
            String decrypted = decryption.getText();
            int n = (int) c.getValue();
            String s = ceasar.decryption(decrypted, n);
            encryption.setText(s);

        });

        optionsbut.setOnAction((event) -> {
            String decrypted = decryption.getText();
            long timestart = System.currentTimeMillis();
            String s = b.breaking(decrypted);
            long timestop = System.currentTimeMillis();
            long t = timestop - timestart;
            String ti = t + "";
            time.setText("Time: " + ti + " ms");
            options.setText(s);

        });

        freq.setOnAction((event) -> {
            String decrypted = decryption.getText();
            int keyValue = f.set(decrypted);
            key.setText("Key: " + keyValue);

        });

        Scene startScene = new Scene(start);
        stage.setScene(startScene);
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        /*  CaesarCipher c = new CaesarCipher();
        BreakingCeasarCipher b = new BreakingCeasarCipher();
        String original = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int n = 23;
        System.out.println("Text  : " + original);
        System.out.println("Shift : " + n);
        System.out.println("Cipher text: " + c.encryption(original, n));
        String changed = "XYZABCDEFGHIJKLMNOPQRSTUVW";
        System.out.println("Original: " + c.decryption(changed, n));
        System.out.print("options:");
        b.breaking(changed); */
        
        launch(args);
    }

}
