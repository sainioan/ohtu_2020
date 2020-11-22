package ohtu.intjoukkosovellus;

import java.util.Arrays;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujonoTaulukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm = 0;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {

        lukujonoTaulukko = new int[KAPASITEETTI];
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (parametriPositiivinen(kapasiteetti)) {
            lukujonoTaulukko = new int[kapasiteetti];
            this.kasvatuskoko = OLETUSKASVATUS;
        }
    }

    public boolean parametriPositiivinen(int parametri) {
        if (parametri < 0) {
            return false;
        }
        return true;
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
       if(parametriPositiivinen(kapasiteetti)&& parametriPositiivinen(kasvatuskoko)){
        lukujonoTaulukko = new int[kapasiteetti];
        this.kasvatuskoko = kasvatuskoko;
       }
    }

    public boolean lisaa(int luku) {

        if (alkioidenLkm == 0) {
            lukujonoTaulukko[0] = luku;
            alkioidenLkm++;
            return true;
        } else {
        }
        if (!kuuluu(luku)) {
            lukujonoTaulukko[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % lukujonoTaulukko.length == 0) {
                int[] taulukkoAlkuperainen = new int[lukujonoTaulukko.length];
                taulukkoAlkuperainen = lukujonoTaulukko;
                kopioiTaulukko(lukujonoTaulukko, taulukkoAlkuperainen);
                lukujonoTaulukko = new int[alkioidenLkm + kasvatuskoko];
                kopioiTaulukko(taulukkoAlkuperainen, lukujonoTaulukko);
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        boolean kuuluu = false;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (lukujonoTaulukko[i] == luku) {
                kuuluu = true;
            }
        }
        return kuuluu;
    }

    public boolean poista(int luku) {
        int kohta = -1;
        int apu;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujonoTaulukko[i]) {
                kohta = i; //siis luku löytyy tuosta kohdasta 
                lukujonoTaulukko[kohta] = 0;
                break;
            }
        }
        if (kohta != -1) {
            for (int j = kohta; j < alkioidenLkm - 1; j++) {
                apu = lukujonoTaulukko[j];
                lukujonoTaulukko[j] = lukujonoTaulukko[j + 1];
                lukujonoTaulukko[j + 1] = apu;
            }
            alkioidenLkm--;
            return true;
        }

        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + lukujonoTaulukko[0] + "}";
        } else {
            String s = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                s += lukujonoTaulukko[i];
                s += ", ";
            }
            s += lukujonoTaulukko[alkioidenLkm - 1];
            s += "}";
            return s;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukujonoTaulukko[i];
        }
        return taulu;
    }
       public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko();
        for (int i = 0; i < a.alkioidenLkm; i++) {
            yhdiste.lisaa(a.lukujonoTaulukko[i]);
        }
        for (int i = 0; i < b.alkioidenLkm; i++) {
            yhdiste.lisaa(b.lukujonoTaulukko[i]);
        }
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
                IntJoukko leikkaus = new IntJoukko();
        for (int i = 0; i < b.lukujonoTaulukko.length; i++) {
            if (a.kuuluu(b.lukujonoTaulukko[i])) {
                leikkaus.lisaa(b.lukujonoTaulukko[i]);
            }
        }   
        return leikkaus;
    }


    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();
        for (int i = 0; i < a.lukujonoTaulukko.length; i++) {
            erotus.lisaa(a.lukujonoTaulukko[i]);
        }
        for (int i = 0; i < a.lukujonoTaulukko.length; i++) {
            erotus.poista(b.lukujonoTaulukko[i]);
        }

        return erotus;
    }

}
