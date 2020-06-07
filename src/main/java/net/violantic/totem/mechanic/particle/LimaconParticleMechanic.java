package net.violantic.totem.mechanic.particle;

import net.violantic.totem.mechanic.TotemMechanic;
import net.violantic.totem.util.ParticleUtil;
import net.violantic.totem.util.VectorUtil;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public class LimaconParticleMechanic implements TotemMechanic {

    private Location location;
    private Color color;
    private double heightOffset;

    /**
     * Alpha is the angular rotation on the x axis.
     * Alpha will increase by pi/48 every cycle (3.75 degrees)
     */
    private double alpha = 0;
    private double alphaRotation = Math.PI / 48;

    /**
     * Beta is the angular rotation on the y axis.
     * Beta will increase by pi/48 every cycle ()
     */
    private double beta = 0;
    private double betaRotation = Math.PI /96;

    private double size = 1;
    private double smoothness = 100;

    private int taskId;

    public LimaconParticleMechanic(Location location, Color color) {
        this.location = location;
        this.color = color;
        this.heightOffset = 1;
    }

    @Override
    public Location getStartingLocation() {
        return location;
    }

    @Override
    public String getName() {
        return "Limacon Particle Effect";
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
            double x = size * (1+(2 * Math.sin(theta))) * Math.cos(theta);
            double y = size * (1+(2 * Math.sin(theta))) * Math.sin(theta);

            Vector direction = new Vector(x, y, 0);

            /**
             * Particle being displayed at point on curve
             */
            ParticleUtil.displayParticle(
                    getStartingLocation().clone()
                            .add(0, heightOffset, 0)
                            .add(0, 0.5 * Math.sin(alpha), 0)
                            .add(VectorUtil.rotateAroundAxisY(direction, beta)),
                    getColor()
            );
        }

        alpha += alphaRotation;
        beta += betaRotation;

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
