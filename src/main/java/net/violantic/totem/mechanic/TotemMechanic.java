package net.violantic.totem.mechanic;

import org.bukkit.Location;

public interface TotemMechanic extends Runnable {

    /**
     * Starting location of mechanic.
     * @return Start location
     */
    Location getStartingLocation();

    /**
     * Name of the Totem Mechanic.
     * @return Totem Name
     */
    String getName();

    /**
     * If enabled, a task timer will be invoked.
     * upon the totems mechanic registry {@see Totem.registerMechanics}
     * @return Totem Repetition
     */
    boolean doesRepeat();

    /**
     * Delay in server ticks until mechanic starts.
     * @return Delay time
     */
    int getDelay();

    /**
     * Ticks in between repeated runnable cycle.
     * @return Ticks per cycle
     */
    int ticksPerInterval();

    /**
     * Id of the mechanic's task in the Bukkit Scheduler
     * @return Task ID
     */
    int getTaskId();

    /**
     * Sets the id of the task in Bukkit scheduler
     * @param id New Task ID
     */
    void setTaskId(int id);

    /**
     * Runnable controls behavior of totem.
     * @return Mechanic runnable
     */
    void run();

}
