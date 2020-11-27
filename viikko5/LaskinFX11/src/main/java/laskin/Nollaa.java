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

    private int aikaisempiArvo;
    private int aikaisempiTulos;
    public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka logiikka) {

        super(tuloskentta, syotekentta, nollaa, undo, logiikka);
    }

    @Override
    public void suorita() {
        int syote;
        try {
            this.aikaisempiArvo = Integer.valueOf(tuloskentta.getText());
            syote = Integer.valueOf(syotekentta.getText());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        logiikka.nollaa();
        tuloskentta.setText("" + logiikka.getTulos());
        syotekentta.clear();
        nollaa.disableProperty().set(true);
        undo.disableProperty().set(false);
    }

    @Override
    public void peru() {

        logiikka.setTulos(aikaisempiArvo);
        tuloskentta.setText("" + aikaisempiArvo);
    }

}
