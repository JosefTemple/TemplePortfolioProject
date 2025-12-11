import components.CoasterQueue;
import components.CoasterQueue1L;

public class AmusementParkRide {

    /**
     * The name of the ride.
     */
    private String rideName;

    /**
     * The queue of people waiting for this ride.
     */
    private CoasterQueue<String> line;

    /**
     * Counter for how many people successfully rode the ride today.
     */
    private int totalRidersToday;

    /**
     * Constructor.
     *
     * @param name
     *            the name of the ride
     */
    public AmusementParkRide(String name) {
        this.rideName = name;
        this.line = new CoasterQueue1L<>();
        this.totalRidersToday = 0;
    }

    /**
     * Attempts to add a guest to the line.
     *
     * @param guestName
     *            name of the guest
     */
    public void joinLine(String guestName) {
        int initialLength = this.line.length();
        this.line.getInLine(guestName);

        // We can check if they actually got in line (probabilistic check)
        if (this.line.length() > initialLength) {
            System.out.println(guestName + " successfully joined the line for "
                    + this.rideName);
        } else {
            System.out.println(guestName + " got bored and walked away from "
                    + this.rideName);
        }
    }

    /**
     * Runs one cycle of the ride car. Tries to load the next person.
     */
    public void runRideCycle() {
        if (this.line.length() == 0) {
            System.out.println("[" + this.rideName
                    + "] Line is empty. Waiting for guests...");
            return;
        }

        // Try to get the next person
        String rider = this.line.getOnRide();

        if (rider != null) {
            this.totalRidersToday++;
            System.out.println("[" + this.rideName + "] SPLASH! " + rider
                    + " just finished the ride.");
        } else {
            System.out.println("[" + this.rideName
                    + "] Operator error! The seat went empty this cycle.");
        }
    }

    /**
     * Reports statistics.
     */
    public void printStats() {
        System.out.println("--- Stats for " + this.rideName + " ---");
        System.out.println("Current Queue Length: " + this.line.length());
        System.out.println("Total Riders Today:   " + this.totalRidersToday);
        System.out.println("Current Queue: " + this.line.toString());
    }
}
