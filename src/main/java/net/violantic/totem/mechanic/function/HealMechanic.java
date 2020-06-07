package net.violantic.totem.mechanic.function;

import net.violantic.totem.Totem;
import net.violantic.totem.mechanic.TotemMechanic;
import org.bukkit.Location;
import org.bukkit.Sound;

public class HealMechanic implements TotemMechanic {

    public final static double RADIUS = 5;

    private int taskId;
    private Totem totem;

    public HealMechanic(Totem totem) {
        this.totem = totem;
    }

    @Override
    public Location getStartingLocation() {
        return totem.getLocation();
    }

    @Override
    public String getName() {
        return "Health Mechanic";
    }

    @Override
    public boolean doesRepeat() {
        return false;
    }

    @Override
    public int getDelay() {
        return 0;
    }

    @Override
    public int ticksPerInterval() {
        return 20 * 2;
    }

    @Override
    public int getTaskId() {
        return taskId;
    }

    @Override
    public void setTaskId(int id) {
        this.taskId = id;
    }

    @Override
    public void run() {

        Location playerLocation = totem.getOwner().getLocation();
        double distance = Math.sqrt(
                Math.pow(playerLocation.getX() - totem.getLocation().getX(), 2)
                + Math.pow(playerLocation.getY() - totem.getLocation().getY(), 2)
        );

        if(distance <= RADIUS) {
            if((totem.getOwner().getHealth() + 4) > 20) {
                totem.getOwner().setHealthScale(20);
                return;
            }

            totem.getOwner().setHealth(totem.getOwner().getHealth() + 4);
            playerLocation.getWorld().playSound(playerLocation, Sound.ENTITY_ITEM_PICKUP, 1, 1);
        }

    }

}
