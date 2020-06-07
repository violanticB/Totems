package net.violantic.totem.mechanic.particle;

import net.violantic.totem.mechanic.TotemMechanic;
import net.violantic.totem.util.ParticleUtil;
import net.violantic.totem.util.VectorUtil;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public class HeartParticleMechanic implements TotemMechanic {

    private Location location;
    private Color color;
    private double heightOffset;

    /**
     * Alpha is the angular rotation on the x axis.
     * Alpha will increase by pi/48 every cycle (3.75 degrees)
     */
    private double alpha = 0;
    private double alphaRotation = Math.PI / 48;

    private double size = 2;
    private double smoothness = 75;

    private int taskId;

    public HeartParticleMechanic(Location location, Color color) {
        this.location = location;
        this.color = color;
        this.heightOffset = 4.5;
    }

    @Override
    public Location getStartingLocation() {
        return location;
    }

    @Override
    public String getName() {
        return "Heart Particle Effect";
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

        for(double theta = 0; theta < (2 * Math.PI); theta += (Math.PI * 2) / smoothness) {

            /**
             * Parametric equations for Limacon curve
             * is as follows:
             *
             * P(x(t), y(t))
             * x(t) = (1 + 2sin(t))cos(t)
             * y(t) = (1 + 2sin(t))sin(t)
             */
            double x = size * Math.sin(theta) * Math.cos(theta) * Math.log(theta);
            double y = size * Math.pow(theta, 0.3) * Math.pow(Math.cos(theta), 0.5);

            Vector direction = new Vector(x, y, 0);

            /**
             * Particle being displayed at point on curve
             * will be a green-ish color
             */
            ParticleUtil.displayParticle(
                    getStartingLocation().clone()
                            .add(0, heightOffset, 0)
                            .add(0, 0.5 * Math.sin(alpha), 0)
                            .add(VectorUtil.rotateAroundAxisY(direction, alpha)),
                    getColor()
            );
        }

        alpha += alphaRotation;

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
