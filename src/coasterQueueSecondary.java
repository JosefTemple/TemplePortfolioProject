public abstract class coasterQueueSecondary<T> implements coasterQueue<T> {
    @Override
    public final void clear() {
        // Implements clear by repeatedly calling getOnRide
        // until the queue is empty.
        while (this.length() > 0) {
            this.getOnRide();
        }
    }

    @Override
    public final void transferFrom(coasterQueue<T> s) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";

        this.clear();

        // Dequeue from source and enqueue to this, preserving order
        // and emptying source.
        int n = s.length();
        for (int i = 0; i < n; i++) {
            this.getInLine(s.getOnRide());
        }
    }

}
