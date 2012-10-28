package edu.ata.automation.dispatch;

/**
 * Basic class representing game modes. Handles running by itself.
 *
 * @author joel
 */
public abstract class GameMode implements Runnable {

    private final String name;
    private Thread thread;
    private int priority = Thread.NORM_PRIORITY;

    /**
     * Creates the game mode with a name and it's runnable. Priority is set to
     * {@link Thread#NORM_PRIORITY}.
     *
     * @param name name of the thread
     */
    public GameMode(String name) {
        this.name = name;
    }

    /**
     * Creates the game mode with a name, priority and it's runnable.
     *
     * @param name name of the thread
     * @param priority priority of the thread (1 - 10)
     */
    public GameMode(String name, int priority) {
        this(name);
        this.priority = priority;
    }

    /**
     * Gets the name of the thread.
     *
     * @return name of game mode
     */
    public String getName() {
        return name;
    }

    /**
     * Creates a string representation of the thread.
     *
     * @return string representation
     */
    public String toString() {
        return "GameMode:" + name;
    }

    /**
     * Starts the thread.
     *
     * <p> <i>Caution :</i> Thread will not be started if
     * {@link Thread#isAlive()} does not return false. This prevents the game
     * mode from running more than once at the same time, but is prone to
     * concurrency problems.
     */
    public void start() {
        if (thread != null && thread.isAlive()) {
            return;
        }
        thread = new Thread(this, name);
        thread.setPriority(priority);
        thread.start();
    }

    /**
     * Sets the priority of the thread. Will take effect the next time you run
     * the game mode. ({@link GameMode#start()})
     *
     * @param priority priority of the thread
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Stops the game mode. Is overridden, and thus cannot be entirely trusted.
     */
    public abstract void stop();
}
