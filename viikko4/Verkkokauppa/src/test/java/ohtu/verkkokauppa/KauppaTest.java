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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of aloitaAsiointi method, of class Kauppa.
     */
//    @Test
//    public void testAloitaAsiointi() {
//        System.out.println("aloitaAsiointi");
//        Kauppa instance = null;
//        instance.aloitaAsiointi();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of poistaKorista method, of class Kauppa.
//     */
//    @Test
//    public void testPoistaKorista() {
//        System.out.println("poistaKorista");
//        int id = 0;
//        Kauppa instance = null;
//        instance.poistaKorista(id);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of lisaaKoriin method, of class Kauppa.
//     */
//    @Test
//    public void testLisaaKoriin() {
//        System.out.println("lisaaKoriin");
//        int id = 0;
//        Kauppa instance = null;
//        instance.lisaaKoriin(id);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of tilimaksu method, of class Kauppa.
//     */
//    @Test
//    public void testTilimaksu() {
//        System.out.println("tilimaksu");
//        String nimi = "";
//        String tiliNumero = "";
//        Kauppa instance = null;
//        boolean expResult = false;
//        boolean result = instance.tilimaksu(nimi, tiliNumero);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455",5);   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
}
