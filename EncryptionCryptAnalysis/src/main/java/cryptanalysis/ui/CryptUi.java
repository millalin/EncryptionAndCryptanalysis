/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptanalysis.ui;

import cryptanalysis.blowfish.Blowfish;
import cryptanalysis.breaking.BreakingCaesarCipher;
import cryptanalysis.breaking.BreakingVigenereCipher;
import cryptanalysis.breaking.FrequencyAnalysis;
import cryptanalysis.ciphers.CaesarCipher;
import cryptanalysis.ciphers.VigenereCipher;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main class of the program that is responsible of creating graphic ui.
 *
 * @author milla
 */
public class CryptUi extends Application {

    FrequencyAnalysis f;

    @Override
    public void start(Stage stage) {

        CaesarCipher caesar = new CaesarCipher();
        VigenereCipher vigenere = new VigenereCipher();

        BreakingCaesarCipher b = new BreakingCaesarCipher();
        BreakingVigenereCipher breaking = new BreakingVigenereCipher();
        f = new FrequencyAnalysis();

        BorderPane start = new BorderPane();

        Button testCeasar = new Button("Test Ceasar");
        Button testVinenere = new Button("Test Vigenère");
        Button testBlowfish = new Button("Test Blowfish");

        Button testCeasarfile = new Button("File Ceasar");
        Button testVinenerefile = new Button("File Vigenère");
        Button testBlowfishfile = new Button("File Blowfish");

        HBox startbuttons = new HBox();
        startbuttons.getChildren().addAll(testCeasar, testVinenere, testBlowfish, testCeasarfile, testVinenerefile, testBlowfishfile);
        startbuttons.setSpacing(10);
        start.setCenter(startbuttons);
        start.setPadding(new Insets(30, 30, 30, 30));

        GridPane fileB = new GridPane();
        Label text = new Label("Write key and file name");
        TextField filebf = new TextField();
        TextField filebfkey = new TextField();
        Button encryptfile = new Button("Encrypt");
        Button decryptfile = new Button("Decrypt");
        Label crypttimelabel = new Label("Time");
        Label filesize = new Label("File size: ");

        fileB.add(text, 1, 1);
        fileB.add(filebfkey, 1, 2);
        fileB.add(filebf, 1, 3);
        fileB.add(encryptfile, 1, 4);
        fileB.add(crypttimelabel, 1, 5);
        fileB.add(filesize, 1, 6);
        fileB.setHgap(5);
        fileB.setVgap(25);
        fileB.setPadding(new Insets(10, 10, 10, 10));

        Scene bffilescene = new Scene(fileB);

        Label cipher = new Label("Ceasar cipher");
        Label encrypt = new Label("Plaintext");
        TextArea encryption = new TextArea();
        encryption.setPrefWidth(400);
        encryption.setWrapText(true);

        Label decrypt = new Label("Ciphertext");
        TextArea decryption = new TextArea();
        decryption.setPrefWidth(400);
        decryption.setWrapText(true);

        Button enButton = new Button("Encrypt");
        Button deButton = new Button("Decrypt");
        Label choice = new Label("Choose Shift");
        Label time = new Label("Time");

        ChoiceBox c = new ChoiceBox();
        c.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26);
        c.setValue(1);

        TextArea options = new TextArea();
        options.setPrefWidth(400);
        Button optionsbut = new Button("Options");

        Button freqButton = new Button("Find key: ");
        Label key = new Label("Key that will decrypt text is: ");
        Label line = new Label("Find key that will decrypt text:                      Show chart:");

        Button chartButton = new Button("Frequency chart");
        Button returnButton = new Button("Exit");

        GridPane ceasarPane = new GridPane();
        ceasarPane.setAlignment(Pos.CENTER);
        ceasarPane.setMinSize(600, 300);
        ceasarPane.add(cipher, 0, 0); //toka on rivi
        ceasarPane.add(encrypt, 1, 1);
        ceasarPane.add(choice, 1, 2);
        ceasarPane.add(c, 1, 3);

        ceasarPane.add(encryption, 1, 4);
        ceasarPane.add(decrypt, 4, 1);
        ceasarPane.add(decryption, 4, 4);

        VBox buttons = new VBox();
        buttons.getChildren().addAll(enButton, deButton);
        buttons.setSpacing(10);

        HBox buttons2 = new HBox();
        buttons2.getChildren().addAll(freqButton, chartButton);
        buttons2.setSpacing(200);
        ceasarPane.add(buttons, 2, 4);
        ceasarPane.add(optionsbut, 1, 7);
        ceasarPane.add(options, 1, 8);
        ceasarPane.add(time, 4, 8);
        ceasarPane.add(line, 4, 5);
        ceasarPane.add(buttons2, 4, 6);
        ceasarPane.add(key, 4, 7);

        ceasarPane.setHgap(5);
        ceasarPane.setVgap(25);
        ceasarPane.setPadding(new Insets(10, 10, 10, 10));

        Scene ceasarScene = new Scene(ceasarPane);

        //VIGENERE
        GridPane vigenerePane = new GridPane();
        Label keyLabel = new Label("Insert keyword");
        TextArea keywordText = new TextArea();
        keywordText.setPrefSize(20, 5);

        TextArea plainText = new TextArea();
        TextArea cipherText = new TextArea();
        cipherText.setWrapText(true);
        plainText.setWrapText(true);
        Button encButton = new Button("Encrypt");
        Button decButton = new Button("Decrypt");
        VBox buttons3 = new VBox();
        buttons3.getChildren().addAll(encButton, decButton);
        buttons3.setSpacing(10);

        Button keyLength = new Button("Suggested key length");
        Label keyLengthLabel = new Label("Key length: ");
        Label suggestedKey = new Label("Suggested key: ");
        Label vigenereTime = new Label("Time: ");

        vigenerePane.add(keyLabel, 1, 1);
        vigenerePane.add(keywordText, 1, 2);
        vigenerePane.add(plainText, 1, 4);
        vigenerePane.add(cipherText, 3, 4);
        vigenerePane.add(buttons3, 2, 4);
        vigenerePane.add(keyLength, 3, 5);
        vigenerePane.add(keyLengthLabel, 3, 6);
        vigenerePane.add(suggestedKey, 3, 7);
        vigenerePane.add(vigenereTime, 3, 8);
        vigenerePane.setHgap(5);
        vigenerePane.setVgap(25);
        vigenerePane.setPadding(new Insets(10, 10, 10, 10));

        //BLOWFISH
        GridPane blowfishPane = new GridPane();
        blowfishPane.setHgap(5);
        blowfishPane.setVgap(25);
        blowfishPane.setPadding(new Insets(10, 10, 10, 10));
        Label plainlabel = new Label("Plaintext");
        Label cipherlabel = new Label("ciphertext");
        Label bfkeyLabel = new Label("Insert keyword (32-448 bit)");
        TextArea bfkeywordText = new TextArea();
        bfkeywordText.setPrefSize(20, 5);
        Button encBut = new Button("Encrypt");
        Button decBut = new Button("Decrypt");

        TextArea bfplainText = new TextArea();
        TextArea bfcipherText = new TextArea();
        bfcipherText.setWrapText(true);
        bfplainText.setWrapText(true);

        VBox buttons4 = new VBox();
        buttons4.getChildren().addAll(encBut, decBut);
        buttons4.setSpacing(10);

        blowfishPane.add(bfkeyLabel, 1, 1);
        blowfishPane.add(bfkeywordText, 1, 2);
        blowfishPane.add(bfplainText, 1, 4);
        blowfishPane.add(bfcipherText, 3, 4);
        blowfishPane.add(buttons4, 2, 4);

        Scene vigenereScene = new Scene(vigenerePane);
        Scene blowfishScene = new Scene(blowfishPane);

        testCeasar.setOnAction((event) -> {
            stage.setScene(ceasarScene);
        });

        testVinenere.setOnAction((event) -> {
            stage.setScene(vigenereScene);
        });

        testBlowfish.setOnAction((event) -> {
            stage.setScene(blowfishScene);
        });

        testBlowfishfile.setOnAction((event) -> {
            stage.setScene(bffilescene);
        });

        enButton.setOnAction((event) -> {

            // TESTAUSTA
            String key1 = vigenere.makeKey("The quick brown", "cryptii");
            System.out.println(key1);
            String encr = vigenere.encryption("The quick brown", "cryptii");
            System.out.println(encr);
            // YLLÄ TEST

            String original = encryption.getText();
            int n = (int) c.getValue();
            String s = caesar.encryption(original, n);
            decryption.setText(s);

        });

        deButton.setOnAction((event) -> {
            String decrypted = decryption.getText();
            int n = (int) c.getValue();
            String s = caesar.decryption(decrypted, n);
            encryption.setText(s);

        });

        optionsbut.setOnAction((event) -> {
            String decrypted = decryption.getText();
            long timestart = System.currentTimeMillis();
            String s = b.breakingAllOptions(decrypted);
            long timestop = System.currentTimeMillis();
            long t = timestop - timestart;
            String ti = t + "";
            time.setText("Time: " + ti + " ms");
            options.setText(s);

        });

        freqButton.setOnAction((event) -> {
            String decrypted = decryption.getText();
            int keyValue = f.countFrequencies(decrypted);
            key.setText("Key: " + keyValue);

        });

        chartButton.setOnAction((event) -> {
            BorderPane chartPane = new BorderPane();

            BarChart<String, Number> chart = this.frequencysChart(decryption);
            chartPane.setCenter(chart);
            chartPane.setTop(returnButton);
            Scene chartScene = new Scene(chartPane, 640, 480);
            stage.setScene(chartScene);

        });

        returnButton.setOnAction((event) -> {

            stage.setScene(ceasarScene);

        });

        encButton.setOnAction((event) -> {
            String original = plainText.getText();
            String keyText = keywordText.getText();
            String encryptText = vigenere.encryption(original, keyText);

            cipherText.setText(encryptText);

        });

        decButton.setOnAction((event) -> {

            String vinCipher = cipherText.getText();
            String keyText = keywordText.getText();
            String decryptText = vigenere.decryption(vinCipher, keyText);

            plainText.setText(decryptText);

        });

        keyLength.setOnAction((event) -> {

            String vinCipher = cipherText.getText();
            long startTime = System.currentTimeMillis();
            int x = breaking.analyzingText(vinCipher);
            if (x == 0) {
                keyLengthLabel.setText("Too short text. There can't be found any factors");
            } else {
                String guessedKeyString = breaking.guessingKey(vinCipher, x);
                long stopTime = System.currentTimeMillis();
                keyLengthLabel.setText("Suggested key length: " + x);
                suggestedKey.setText("Suggested key: " + guessedKeyString);

                long timePassed = stopTime - startTime;
                vigenereTime.setText("Time: " + timePassed);

            }

        });

        encBut.setOnAction((event) -> {

            String original = bfplainText.getText();
            String keyText = bfkeywordText.getText();
            Blowfish bf = new Blowfish(original, keyText);
            String encryptText = bf.encryption();

            bfcipherText.setText(encryptText);

        });

        decBut.setOnAction((event) -> {

            String vinCipher = bfcipherText.getText();
            String keyText = bfkeywordText.getText();
            Blowfish bf = new Blowfish(vinCipher,keyText);
            String decryptText = bf.decryption(vinCipher);

            bfplainText.setText(decryptText);

        });

        encryptfile.setOnAction((event) -> {

            String filename = filebf.getText();
            try {
                String original = readFile(filename);
                String keyText = filebfkey.getText();
                Blowfish bf = new Blowfish(original,keyText);
                long starttime = System.currentTimeMillis();
                String encryptText = bf.encryption();
                double timepassed = System.currentTimeMillis() - starttime;
                double timepassedSec = timepassed / 1000;
                File file = new File(filename);
                double size = file.length() / 1024;
                double kbPerSecond = size / timepassedSec;
                crypttimelabel.setText("Encryption time: " + timepassed + "ms, " + timepassedSec + " s.");
                filesize.setText("File size: " + size + " kB. Speed: " + kbPerSecond + " kB/s.");
            } catch (Exception e) {
                //  no file
            }

        });

        Scene startScene = new Scene(start);
        stage.setScene(startScene);
        stage.show();
    }

    public static void main(String[] args) throws Exception {

        TimeTesting tt = new TimeTesting();
        System.out.println("java: " + tt.vigenereTimeArray());
        System.out.println("own: " + tt.vigenereTimeOwnArray());

        tt.testBf();
        tt.testb();

        launch(args);
    }

    public BarChart frequencysChart(TextArea decryption) {
        String decrypted = decryption.getText();
        f.countFrequencies(decrypted);
        int[] letters = f.freq();

        CategoryAxis x = new CategoryAxis();
        NumberAxis y = new NumberAxis();
        BarChart<String, Number> chart = new BarChart<>(x, y);

        chart.setTitle("Frequencies");
        chart.setLegendVisible(false);

        XYChart.Series alphabets = new XYChart.Series();

        alphabets.getData().add(new XYChart.Data("A", letters['A'] + letters['a']));
        alphabets.getData().add(new XYChart.Data("B", letters['B'] + letters['b']));
        alphabets.getData().add(new XYChart.Data("C", letters['C'] + letters['c']));
        alphabets.getData().add(new XYChart.Data("D", letters['D'] + letters['d']));
        alphabets.getData().add(new XYChart.Data("E", letters['E'] + letters['e']));
        alphabets.getData().add(new XYChart.Data("F", letters['F'] + letters['f']));
        alphabets.getData().add(new XYChart.Data("G", letters['G'] + letters['g']));
        alphabets.getData().add(new XYChart.Data("H", letters['H'] + letters['h']));
        alphabets.getData().add(new XYChart.Data("I", letters['I'] + letters['i']));
        alphabets.getData().add(new XYChart.Data("J", letters['J'] + letters['j']));
        alphabets.getData().add(new XYChart.Data("K", letters['K'] + letters['k']));
        alphabets.getData().add(new XYChart.Data("L", letters['L'] + letters['l']));
        alphabets.getData().add(new XYChart.Data("M", letters['M'] + letters['m']));
        alphabets.getData().add(new XYChart.Data("N", letters['N'] + letters['n']));
        alphabets.getData().add(new XYChart.Data("O", letters['O'] + letters['o']));
        alphabets.getData().add(new XYChart.Data("P", letters['P'] + letters['p']));
        alphabets.getData().add(new XYChart.Data("Q", letters['Q'] + letters['q']));
        alphabets.getData().add(new XYChart.Data("R", letters['R'] + letters['r']));
        alphabets.getData().add(new XYChart.Data("S", letters['S'] + letters['s']));
        alphabets.getData().add(new XYChart.Data("T", letters['T'] + letters['t']));
        alphabets.getData().add(new XYChart.Data("U", letters['U'] + letters['u']));
        alphabets.getData().add(new XYChart.Data("V", letters['V'] + letters['v']));
        alphabets.getData().add(new XYChart.Data("W", letters['W'] + letters['w']));
        alphabets.getData().add(new XYChart.Data("X", letters['X'] + letters['x']));
        alphabets.getData().add(new XYChart.Data("Y", letters['Y'] + letters['y']));
        alphabets.getData().add(new XYChart.Data("Z", letters['Z'] + letters['z']));

        chart.getData().add(alphabets);
        return chart;
    }

    public String readFile(String filename) throws Exception {
        String rivi = "";

        try {
            Scanner tiedosto = new Scanner(new File(filename));

            while (tiedosto.hasNextLine()) {
                rivi += tiedosto.nextLine();
                // System.out.println(rivi);
            }

            tiedosto.close();
        } catch (Exception e) {
            System.out.println("There is no such file");
        }

        return rivi;
    }

}
