/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.verkkokauppa;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author anniinasainio
 */
public class KauppaTest {
    Pankki pankki;
    Varasto varasto;
    Viitegeneraattori viite;
    public KauppaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        varasto = mock(Varasto.class);
        viite = mock(Viitegeneraattori.class);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
  
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        k.aloitaAsiointi();
        k.lisaaKoriin(1);    
        k.tilimaksu("pekka", "12345");  
        
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));
    }
    
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan2() {
       
        when(viite.uusi()).thenReturn(43);
        when(varasto.saldo(2)).thenReturn(10); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "banaani", 2));
        when(varasto.saldo(3)).thenReturn(10); 
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "kahvi", 4));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(2); 
        k.lisaaKoriin(3); 
        // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("oliver", "55555");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("oliver"), eq(43), eq("55555"), eq("33333-44455"),eq(6));   

    }
    
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan3() {
       
        when(viite.uusi()).thenReturn(44);
        when(varasto.saldo(2)).thenReturn(10); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "banaani", 2));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "banaani", 2));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(2); 
        k.lisaaKoriin(2); 
        // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("oliver", "55555");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("oliver"), eq(44), eq("55555"), eq("33333-44455"),eq(4));   

    }
    
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan4() {
       
        when(viite.uusi()).thenReturn(45);
        when(varasto.saldo(2)).thenReturn(10); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "banaani", 2));
        when(varasto.saldo(4)).thenReturn(0); 
        when(varasto.haeTuote(4)).thenReturn(new Tuote(4, "kello", 150));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(2); 
        k.lisaaKoriin(4); 
        // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("oliver", "55555");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("oliver"), eq(45), eq("55555"), eq("33333-44455"),eq(2));  
        k.aloitaAsiointi();
        
        verify(pankki).tilisiirto(eq("oliver"), anyInt(), eq("55555"), eq("33333-44455"),eq(0));  
        
    }
    @Test
    public void kaytetaanPerakkaistenViitekutsujenArvoja() {
       
        // määritellään että metodi palauttaa ensimmäisellä kutsukerralla 1, toisella 2 
        // ja kolmannella 3
        when(viite.uusi()).thenReturn(46)
            .thenReturn(47)
            .thenReturn(48);
        
        when(varasto.saldo(2)).thenReturn(10); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "banaani", 2));
        Kauppa k = new Kauppa(varasto, pankki, viite);   
        k.aloitaAsiointi();;
        k.lisaaKoriin(2); 
        k.tilimaksu("oliver", "55555");

        // varmistetaan, että nyt käytössä ensimmäisenä pyydetty viite
        verify(pankki).tilisiirto(anyString(), eq(46), eq("55555"),eq("33333-44455"), eq(2));
        
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        k.aloitaAsiointi();;;
        k.lisaaKoriin(1); 
        k.tilimaksu("oliver", "55555");

        // ... toisena pyydetty viite
        verify(pankki).tilisiirto(anyString(), eq(47), eq("55555"),eq("33333-44455"), eq(5));
        
        when(varasto.saldo(10)).thenReturn(0); 
        when(varasto.haeTuote(10)).thenReturn(new Tuote(10, "kaakao", 4));
        k.aloitaAsiointi();;;
        k.lisaaKoriin(10); 
        k.tilimaksu("oliver", "55555");

        // ... ja kolmantena pyydetty viite        
        verify(pankki).tilisiirto(anyString(), eq(48), eq("55555"),eq("33333-44455"), eq(0));
    }
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan5() {
       
        when(viite.uusi()).thenReturn(49);
        when(varasto.saldo(2)).thenReturn(10); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "banaani", 2));
        when(varasto.saldo(3)).thenReturn(10); 
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "kahvi", 4));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(2); 
        k.lisaaKoriin(3); 
        k.poistaKorista(2);
        k.tilimaksu("oliver", "55555");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("oliver"), eq(49), eq("55555"), eq("33333-44455"),eq(4));   

    }
    

}
