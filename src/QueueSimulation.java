import components.CoasterQueue;
import components.CoasterQueue1L;

public final class QueueSimulation {

    private QueueSimulation() {
    }

    public static void main(String[] args) {
        System.out.println("=== Starting Coaster Simulation ===\n");

        // 1. Create two separate queues
        CoasterQueue<String> brokenRide = new CoasterQueue1L<>();
        CoasterQueue<String> workingRide = new CoasterQueue1L<>();

        // 2. Populate the first queue (The "Broken" Ride)
        System.out.println(
                "-> People are trying to line up for the Broken Ride...");
        for (int i = 1; i <= 10; i++) {
            String guest = "Guest#" + i;
            brokenRide.getInLine(guest);
        }

        System.out.println("People currently in line for Broken Ride: "
                + brokenRide.length());
        System.out.println("Queue: " + brokenRide.toString());

        // 3. Simulate the breakdown
        System.out.println("\n*** ATTENTION: The ride has broken down! ***");
        System.out.println("*** Please move to the Working Ride! ***\n");

        // 4. Use switchRides to move everyone
        System.out.println("-> Transferring everyone to the Working Ride...");
        workingRide.switchRides(brokenRide);

        // 5. Verify the state
        System.out.println(
                "Broken Ride Length (should be 0): " + brokenRide.length());
        System.out.println("Working Ride Length: " + workingRide.length());
        System.out.println("Working Ride Queue: " + workingRide.toString());

        // 6. Process the line on the working ride
        System.out.println("\n-> Processing the line on the Working Ride...");
        while (workingRide.length() > 0) {
            String rider = workingRide.getOnRide();
            if (rider != null) {
                System.out.println("   " + rider + " is enjoying the ride!");
            } else {
                System.out.println(
                        "   (A cart left empty due to slow operations...)");
            }
        }

        System.out.println("\n=== Simulation Complete ===");
    }
}
