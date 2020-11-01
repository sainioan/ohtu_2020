/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author anniinasainio
 */
public class StatisticsTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();
    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
            return players;
        }
    };

    Statistics stats;

    public StatisticsTest() {
    }

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @Test
    public void testSearch() {
        System.out.println("search");
        String name = "Gretzky";
        Player result = stats.search(name);
        assertEquals("EDM", result.getTeam());

    }

    @Test
    public void testSearchEmpty() {
        System.out.println("search");
        String name = "notThere";
        try {
            Player result = stats.search(name);
            assertEquals(null, result.getTeam());
            throw new Exception("Exception message");
        } catch (Exception e) {
            System.out.println(e);
            
            assertEquals(null, e.getMessage());
        }
    }
    @Test
    public void testTeam() {
        System.out.println("team");
        String teamName = "EDM";

        List<Player> result = stats.team(teamName);
        assertEquals("Semenko", result.get(0).getName());
        
        assertEquals(result.size(), 3);

    }
    @Test
    public void testTeamEmpty() {
    String noSuchTeam = "bogus";
        List<Player> result = stats.team(noSuchTeam);
        assertTrue(result.isEmpty());
        
    }

    @Test
    public void playerNotFound() {
        assertEquals(null, stats.search("Granlund"));
    }

    @Test
    public void returnsTeams() {
        assertEquals("Lemieux", stats.team("PIT").get(0).getName());
    }


    @Test
    public void testTopScorers() {
        System.out.println("topScorers");
        int howMany = 2;
        List<Player> result = stats.topScorers(howMany);
        assertEquals("Gretzky", result.get(0).getName());

    }

    @Test
    public void testTopScorersFail() {

        System.out.println("topScorers");
        int howMany = 100;
        try {
            List<Player> result = stats.topScorers(howMany);
            assertFalse(result.get(99) != null);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
