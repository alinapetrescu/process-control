package dcrs.events;

/**
 * abstract event Observer. Override the test() and process() functions
 * in inheriting classes to create concrete observers.
 */
public abstract class Observer implements Runnable {

    /**
     * Delay between observations, in milliseconds.
     */
    private int delay = 200;

    /**
     * Initial state of the observer.
     */
    public final int STATE_ZERO = 0;

    /**
     * State given to the observer while it is observing.
     */
    public final int STATE_OBSERVING = 1;

    /**
     * State given to the observer while it is doing the
     * required actions, after it has detected what it was
     * waiting for.
     */
    public final int STATE_PROCEEDING = 2;

    /**
     * State given to the observer after it has completed
     * its task. If this state is given while the observer
     * is observing, it will abort its work and terminate
     * itself.
     */
    public final int STATE_DONE = 3;

    /**
     * State of the observer.
     */
    protected int state = STATE_ZERO;

    /**
     * defines the test that is able to detect that a specific event happened.
     * @return true whether the event happened.
     */
    protected abstract boolean test();

    /**
     * defines the actions that are taken when event has been detected.
     */
    protected abstract void process();

    /**
     * Returns the current state of the observer.
     * @return the state
     */
    public final int state() {
        return this.state;
    }

    /**
     * starts this event Observer.
     */
    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    /**
     * event observing loop.
     */
    @Override
    public final void run() {
        this.state = STATE_OBSERVING;
        while (!test() && this.state!=STATE_DONE) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ex) {
       
            }
        }
        if (this.state==STATE_DONE) return;
        this.state = STATE_PROCEEDING;
        process();
        this.state = STATE_DONE;
    }

    /**
     * @return a string containing a displayable name of the class.
     */
    @Override
    public String toString() {
        return "(" + this.getClass().getSimpleName() + ")";
    }
}
