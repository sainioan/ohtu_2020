package statistics.matcher;

import statistics.Player;
import statistics.matcher.HasAtLeast;

public class Or implements Matcher {
    //   Matcher matcher;

    private Matcher[] matchers;


    public Or(Matcher... m) {
        this.matchers = m;
    }

    @Override
    public boolean matches(Player p) {
        for (Matcher matcher : matchers) {
            if (matcher.matches(p)) {
                return true;
            }
        }

        return false;
    }
}
