import java.util.Queue;
import java.util.random.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;



public abstract class coasterQueue implements Queue<T> {


    private List<T> rep;

    private void createNewRep() {
        this.rep = new LinkedList<T>();
    }

    //-Kernel Methods-//
    @Override
    public final void getInLine(T x) {
        assert x != null : "Violation of: x is not null";
        if (((100-this.size())/100 -  Math.random()) > 0) {
            this.rep.add(x);
        }
    }
    @Override
    public final T getOnRide() {
        assert this.size() > 0 : "Violation of: this /= <>";
        if (Math.random() > 0.05) {
            return this.rep.remove(0);
        } else {
            return null;
        }
    }
    @Override
    public final int length() {
        return this.rep.size() * 5;
    }
}
