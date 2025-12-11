package components;

import components.standard.Standard;

/**
 * Kernel interface for a first-in, first-out queue of people waiting for a
 * coaster, with probabilistic behavior simulating real-world delays.
 *
 * @param <T>
 *            type of elements in queue (e.g., Rider)
 * @initially <pre>
 * default:
 * ensure
 * this = <>
 * </pre>
 */
public interface CoasterQueueKernel<T>
        extends Standard<CoasterQueue<T>>, Iterable<T> {

    /**
     * Attempts to add person x to the end of the line. Note: Due to line length
     * or boredom, the person may not successfully enter the line.
     *
     * @param x
     *            the person to add
     * @aliases x
     * @updates this
     * @pre x is not null
     * @ensures this = #this * <x> OR this = #this
     */
    void getInLine(T x);

    /**
     * Attempts to remove and return the person from the front of the line.
     * Note: The operator might be slow, resulting in no one getting on the ride
     * this turn.
     *
     * @return the person at the front of the line, or null if the operation
     *         failed
     * @updates this
     * @pre |this| > 0
     * @ensures (#this = <getOnRide> * this) OR (getOnRide = null and this =
     *          #this)
     */
    T getOnRide();

    /**
     * Reports the number of people in the line.
     *
     * @return the number of people in line
     * @ensures length = |this|
     */
    int length();

}
