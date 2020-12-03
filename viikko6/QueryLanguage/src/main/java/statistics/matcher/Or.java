package statistics.matcher;
import statistics.Player;
import statistics.matcher.HasAtLeast;

public class Or implements Matcher {
 //   Matcher matcher;
    HasAtLeast first;
    HasAtLeast second;
    
    public Or(HasAtLeast first, HasAtLeast second){
   //     this.matcher = matcher;
        this.first = first;
        this.second = second;
    }
    
    @Override
    public boolean matches(Player p) {

        if(first.matches(p) || second.matches(p)) {
        return true;
        }
        return false;
    }
}
