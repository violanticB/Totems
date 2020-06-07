package net.violantic.totem.mechanic.particle;

import net.violantic.totem.mechanic.TotemMechanic;
import net.violantic.totem.util.ParticleUtil;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public class HelixParticleMechanic implements TotemMechanic {

    private Location location;
    private Color color;
    private double heightOffset;
    private double height;

    /**
     * Alpha is the angular rotation around the 6 axis.
     * Alpha will increase by 2pi/5 every cycle (5 degrees)
     */
    private double alpha = 0;
    private double alphaRotation = (2 * Math.PI) / 5;

    /**
     * Time parameter of parametric equations
     * x(t), y(t), z(t)
     */
    private double t;

    private double strands = 7;

    private double size = 1;
    private double smoothness = 50;
    private double lineDensity = 0.062;

    private int taskId;

    public HelixParticleMechanic(Location location, Color color, double height) {
        this.location = location;
        this.color = color;
        this.heightOffset = 0;
        this.height = height;
    }

    @Override
    public Location getStartingLocation() {
        return location;
    }

    @Override
    public String getName() {
        return "HelixParticle Effect";
    }

    @Override
    public boolean doesRepeat() {
        return true;
    }

    @Override
    public int getDelay() {
        return 10;
    }

    @Override
    public int ticksPerInterval() {
        return 1;
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

        for(double theta = 0; theta < (2 * Math.PI); theta += Math.PI / 4) {

            /**
             * Reveal cartesian coordinates from
             * parametric equations:
             * x(t) = radius * sin(theta)
             * y(t) =
             * z(t) = radius * cos(theta)
             */
            double x0 = size * Math.sin(theta);
            double z0 = size * Math.cos(theta);

            Vector v = new Vector(x0, 0, z0);
            Location line = location.clone().add(v);

            double i = size * Math.sin(alpha);
            double k = size * Math.cos(alpha);

            ParticleUtil.displayParticle(line.add(new Vector(i, t, k)), Color.BLACK);

            alpha += alphaRotation;
        }

        t += 0.1;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getHeightOffset() {
        return heightOffset;
    }

    public void setHeightOffset(double heightOffset) {
        this.heightOffset = heightOffset;
    }
}
