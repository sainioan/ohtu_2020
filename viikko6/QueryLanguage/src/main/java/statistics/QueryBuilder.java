package statistics;
import statistics.matcher.*;

public class QueryBuilder {

    private Matcher thisMatcher;

    public QueryBuilder() {
        this.thisMatcher = new All();
    }

    public QueryBuilder and(Matcher... m) {
        this.thisMatcher = new And(m);
        return this;
    }

    public QueryBuilder not(Matcher m) {
        this.thisMatcher = new Not(m);
        return this;
    }

    public QueryBuilder playsIn(String team) {
        and(thisMatcher, new PlaysIn(team));
        return this;
    }

    public QueryBuilder hasAtLeast(int value, String category) {
        and(thisMatcher, new HasAtLeast(value, category));
        return this;
    }

    public QueryBuilder hasFewerThan(int value, String category) {
        and(thisMatcher, new HasFewerThan(value, category));
        return this;
    }

    public QueryBuilder oneOf(Matcher... matchers) {

        this.thisMatcher = new Or(matchers);
        return this;

    }

    public Matcher build() {
        Matcher m = this.thisMatcher;
        this.thisMatcher = new All();
        return m;
    }
}
