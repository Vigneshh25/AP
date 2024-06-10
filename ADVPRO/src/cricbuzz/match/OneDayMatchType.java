package cricbuzz.match;

public class OneDayMatchType implements MatchType{
    @Override
    public int noOfOvers() {
        return 50;
    }

    @Override
    public int maxOverCountBowlers() {
        return 10;
    }

    @Override
    public int noOfInnings() {
        return 2;
    }

}
