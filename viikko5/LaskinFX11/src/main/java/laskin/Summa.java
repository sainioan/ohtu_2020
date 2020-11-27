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
public class Summa extends Komento {

    private int aikaisempiArvo;

    public Summa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka logiikka) {
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
        try {
            this.aikaisempiArvo = Integer.valueOf(tuloskentta.getText());
            syote = Integer.valueOf(syotekentta.getText());
            logiikka.plus(syote);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        tuloskentta.setText("" + logiikka.getTulos());
        syotekentta.clear();
        nollaa.disableProperty().set(false);
        undo.disableProperty().set(false);

    }

    @Override
    public void peru() {
        logiikka.setTulos(aikaisempiArvo);
        tuloskentta.setText("" + aikaisempiArvo);

    }
}
