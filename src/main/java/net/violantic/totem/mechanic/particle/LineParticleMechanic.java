package net.violantic.totem.mechanic.particle;

import net.violantic.totem.mechanic.TotemMechanic;
import net.violantic.totem.util.ParticleUtil;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public class LineParticleMechanic implements TotemMechanic {

    private int taskId;
    private Location start;
    private Location end;
    private Color color;

    public LineParticleMechanic(Location start, Location end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }

    @Override
    public Location getStartingLocation() {
        return start;
    }

    @Override
    public String getName() {
        return "Line Particle Mechanic";
    }

    @Override
    public boolean doesRepeat() {
        return true;
    }

    @Override
    public int getDelay() {
        return 0;
    }

    @Override
    public int ticksPerInterval() {
        return 5;
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

        Vector line = end.toVector().subtract(start.toVector());
        double distance = start.distance(end);

        for (double d = 0; d < distance; d += 0.25) {

            ParticleUtil.displayParticle(start.clone().add(line.normalize().clone().multiply(d)), color);
            line.normalize();
        }

    }
}
