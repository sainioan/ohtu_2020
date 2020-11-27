/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author anniinasainio
 */
public class Nollaa extends Komento {
//
//    TextField tuloskentta;
//    TextField syotekentta;
//    Button nollaa;
//    Button undo;
//    Sovelluslogiikka logiikka;

    private int aikaisempiArvo;

    public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka logiikka) {
//        this.tuloskentta = tuloskentta;
//        this.syotekentta = syotekentta;
//        this.nollaa = nollaa;
//        this.undo = undo;
//        this.logiikka = logiikka;

        super(tuloskentta, syotekentta, nollaa, undo, logiikka);
    }

    @Override
    public void suorita() {
        int syote;
        try{
        this.aikaisempiArvo = Integer.valueOf(syotekentta.getText());
        syote =  Integer.valueOf(syotekentta.getText());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        logiikka.nollaa();
        tuloskentta.setText("" + logiikka.tulos());
        syotekentta.clear();
        nollaa.disableProperty().set(true);
        undo.disableProperty().set(false);
    }

    @Override
    public void peru() {

    }

}
