/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptanalysis.ui;

import cryptanalysis.ciphers.CaesarCipher;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
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

        BorderPane start = new BorderPane();
    }

    public static void main(String[] args) throws Exception {
        CaesarCipher c = new CaesarCipher();
        String original = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
        int n = 23; 
        System.out.println("Text  : " + original); 
        System.out.println("Shift : " + n); 
        System.out.println("Cipher text: " + c.encryption(original, n)); 
        String changed ="XYZABCDEFGHIJKLMNOPQRSTUVW";
        System.out.println("Original: " + c.decryption(changed, n)); 
        launch(args);
    }

}
