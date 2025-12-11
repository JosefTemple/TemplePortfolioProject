
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.CoasterQueue;
import components.CoasterQueue1L;

/**
 * JUnit test fixture for CoasterQueueSecondary methods.
 */
public class CoasterQueueSecondaryTest {

    /**
     * Helper to create a queue.
     * 
     * @return a new CoasterQueue1L<String> instance
     */
    private CoasterQueue<String> createFromArgs(String... args) {
        CoasterQueue<String> q = new CoasterQueue1L<>();
        for (String arg : args) {
            q.getInLine(arg);
        }
        return q;
    }

    /**
     * Tests that front() returns the first element and restores the queue.
     * Note: Because the implementation of front() uses getOnRide/getInLine, it
     * is subject to the random failures of the kernel. This test might fail if
     * the probabilistic kernel "drops" a person during rotation. * However,
     * standard testing procedure assumes we verify the INTENDED logic.
     */
    @Test
    public void testFront() {
        // Setup: Ensure we have a queue with elements.
        CoasterQueue<String> q = this.createFromArgs();
        while (q.length() < 2) {
            q.clear();
            q.getInLine("A"); // 100% chance
            q.getInLine("B"); // 99% chance
        }

        CoasterQueue<String> qCopy = q.newInstance();
        qCopy.transferFrom(q);
        q.transferFrom(qCopy.newInstance());
        q = this.createFromArgs();
        q.getInLine("A");
        q.getInLine("B");
        CoasterQueue<String> expectedState = this.createFromArgs();
        expectedState.getInLine("A");
        expectedState.getInLine("B");

        // Execute
        String front = q.front();

        // Assert
        assertEquals("A", front);
        assertEquals(expectedState, q);
    }

    /**
     * Tests switchRides to ensure q is appended to this.
     */
    @Test
    public void testSwitchRides() {
        CoasterQueue<String> q1 = this.createFromArgs("A"); // 100% chance
        CoasterQueue<String> q2 = this.createFromArgs("B"); // 100% chance

        // Execute
        q1.switchRides(q2);

        // Assert
        // q2 should be cleared
        assertEquals(0, q2.length());

        assertEquals("<A,B>", q1.toString());
    }

    /**
     * Switch rides when source is empty.
     */
    @Test
    public void testSwitchRides_EmptySource() {
        CoasterQueue<String> q1 = this.createFromArgs("A");
        CoasterQueue<String> q2 = this.createFromArgs();

        q1.switchRides(q2);

        assertEquals(1, q1.length());
        assertEquals("<A>", q1.toString());
        assertEquals(0, q2.length());
    }

    /**
     * Switch rides when target is empty.
     */
    @Test
    public void testSwitchRides_EmptyTarget() {
        CoasterQueue<String> q1 = this.createFromArgs();
        CoasterQueue<String> q2 = this.createFromArgs("A");

        q1.switchRides(q2);

        assertEquals(1, q1.length());
        assertEquals("<A>", q1.toString());
        assertEquals(0, q2.length());
    }
}