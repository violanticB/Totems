package net.violantic.totem.mechanic.particle;

import net.violantic.totem.mechanic.TotemMechanic;
import net.violantic.totem.util.ParticleUtil;
import net.violantic.totem.util.VectorUtil;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public class EpicycloidParticleMechanic implements TotemMechanic {

    /**
     * Our two circle radii, and their cartesian coordinates
     */
    private double a;
    private double b;
    private double x;
    private double z;

    private double phi = 0;
    private double alpha = 0;
    private double alphaIncrease = Math.PI / 96;

    private double smoothness;

    private Location location;
    private Color color;
    private int red = 255;

    private int taskId;

    public EpicycloidParticleMechanic(double a, double b, Location location, Color color) {
        this.a = a;
        this.b = b;
        this.location = location;
        this.color = color;
        this.smoothness = 50;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Location getStartingLocation() {
        return location;
    }

    @Override
    public String getName() {
        return "Epicycloid Particle Mechanic";
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

        if((red - 2) <= 140) {
            red = 255;
        }

        red -= 2;

        double theta = 0;
        for (theta = 0; theta < 360; theta += 2) {
            /**
             * Parametric equations for Epicycloid curve
             * is as follows:
             *
             * P(x(t), y(t))
             * x(t) = (a + b)cos(t) - bcos((a/b + 1)t)
             * z(t) = (1 + 2sin(t))sin(t)
             */
            x =  ((a + b) * Math.cos(Math.toRadians(theta)) - b * Math.cos((a/b + 1.0)*Math.toRadians(theta)) +.5);
            z = ((a + b) * Math.sin(Math.toRadians(theta)) - b * Math.sin((a/b + 1.0)*Math.toRadians(theta)) +.5);

            Vector direction = new Vector(x, Math.sin(phi), z);

            ParticleUtil.displayParticle(
                    getStartingLocation().clone()
                            .add(0, 0.25, 0)
                            .add(VectorUtil.rotateAroundAxisY(direction, alpha)),
                    getColor().setRed(red)
            );

            phi += Math.PI / 6;

        }

        phi += Math.PI / 96;
        alpha += alphaIncrease;
    }

    public double getSmoothness() {
        return smoothness;
    }

    public void setSmoothness(double smoothness) {
        this.smoothness = smoothness;
    }
}