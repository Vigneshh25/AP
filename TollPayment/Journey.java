class Journey {
    private String start;
    private String destination;
    private ArrayList<Toll> tollsPassed;
    private int amountPaid;

    public Journey(String start, String destination, ArrayList<Toll> tollsPassed, int amountPaid) {
        this.start = start;
        this.destination = destination;
        this.tollsPassed = tollsPassed;
        this.amountPaid = amountPaid;
    }

    public String getStart() {
        return start;
    }

    public String getDestination() {
        return destination;
    }

    public ArrayList<Toll> getTollsPassed() {
        return tollsPassed;
    }

    public int getAmountPaid() {
        return amountPaid;
    }
}
