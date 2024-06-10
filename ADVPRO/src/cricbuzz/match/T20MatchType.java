package cricbuzz.match;

public class T20MatchType implements MatchType{

    @Override
    public int noOfOvers() {
        return 20;
    }

    @Override
    public int maxOverCountBowlers() {
        return 5;
    }

    @Override
    public int noOfInnings() {
        return 2;
    }


}
