class Vehicle {
    private String type;
    private String number;
    private boolean isVip;
    private ArrayList<Journey> journeys;

    public Vehicle(String type, String number, boolean isVip) {
        this.type = type;
        this.number = number;
        this.isVip = isVip;
        this.journeys = new ArrayList<Journey>();
    }

    public String getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }

    public boolean isVip() {
        return isVip;
    }

    public ArrayList<Journey> getJourneys() {
        return journeys;
    }

    public void addJourney(Journey journey) {
        this.journeys.add(journey);
    }
}
