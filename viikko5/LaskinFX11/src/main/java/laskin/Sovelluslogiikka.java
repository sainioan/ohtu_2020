package laskin;

public class Sovelluslogiikka {
 
    public int tulos;
 
    public void plus(int luku) {
        tulos += luku;
    }
     
    public void miinus(int luku) {
        tulos -= luku;
    }
 
    public void nollaa() {
        tulos = 0;
    }
 
    public int getTulos() {
        return tulos;
    }
    
    public void setTulos(int tulos){
        this.tulos = tulos;
    }
    
}