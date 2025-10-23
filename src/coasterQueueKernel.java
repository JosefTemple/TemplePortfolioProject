/**
 * Kernel interface for a first-in, first-out queue of people waiting for a
 * coaster.
 *
 * @param <T>
 *            type of elements in queue (e.g., Rider)
 * @author Your Name
 */
public interface coasterQueueKernel<T> extends Standard<coasterQueue<T>> {

    /**
     * Adds person x to the end of the line.
     *
     * @param x
     *            the person to add
     * @aliases x
     * @pre x is not null
     * @post this = #this * <x>
     */
    void getInLine(T x);

    /**
     * Removes and returns the person from the front of the line.
     *
     * @return the person at the front of the line
     * @updates this
     * @pre |this| > 0
     * @post getOnRide = [front of #this] and this = [rest of #this]
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