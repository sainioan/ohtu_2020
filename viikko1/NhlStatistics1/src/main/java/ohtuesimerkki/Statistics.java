package ohtuesimerkki;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Statistics {

    private List<Player> players;

    public Statistics(Reader reader) {
//        Reader reader = new PlayerReader("https://nhlstatisticsforohtu.herokuapp.com/players.txt");
        players = reader.getPlayers();       
    }

    public Player search(String name) {
        for (Player player : players) {
            if (player.getName().contains(name)) {
                return player;
            }
        }

        return null;
    }

    public List<Player> team(String teamName) {
        ArrayList<Player> playersOfTeam = new ArrayList<Player>();
        
        try{
        for (Player player : players) {
            if ( player.getTeam().equals(teamName)) {
                playersOfTeam.add(player);
            }
        }
        } catch (Exception e){
            System.out.print(e);
        }
        return playersOfTeam;
    }

    public List<Player> topScorers(int howMany) {
        try{
        Collections.sort(players);
        ArrayList<Player> topScorers = new ArrayList<Player>();
        Iterator<Player> playerIterator = players.iterator();
        
        while (howMany>=0) {
            topScorers.add( playerIterator.next() );            
            howMany--;
        }
    
        return topScorers;
            } catch (Exception e){
            System.out.println(e);
       
         }
        return null;
    }

}
