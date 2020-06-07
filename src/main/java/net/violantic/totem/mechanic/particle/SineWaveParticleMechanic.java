package net.violantic.totem.mechanic.particle;

import net.violantic.totem.mechanic.TotemMechanic;
import net.violantic.totem.util.ParticleUtil;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class SineWaveParticleMechanic implements TotemMechanic {

    private final Location from;
    private final Entity entity;
    private Color color;
    private double stopDistance;

    private double distance;
    private double lineSpacing;
    private double amplitude;
    private double theta;
    private double angularVelocity;

    private int taskId;
    int red = 140;

    public SineWaveParticleMechanic(Location from, Entity entity, Color color, double lineSpacing, double stopDistance) {
        this.from = from;
        this.entity = entity;
        this.color = color;
        this.lineSpacing = lineSpacing;
        this.stopDistance = 1.25;
        this.amplitude = 0.5;
        this.theta = 0;
        this.angularVelocity = Math.PI / 12;
    }

    @Override
    public Location getStartingLocation() {
        return from;
    }

    @Override
    public String getName() {
        return "SineWave Particle Mechanic";
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
        return 2;
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
        if((++red) >= 255) {
            red = 140;
        }

        Vector line =  entity.getLocation().add(0, 1.5, 0).toVector().subtract(from.toVector());
        distance = from.distance(entity.getLocation());

        theta += angularVelocity;

        for(double d = 0; d < distance - stopDistance; d += lineSpacing) {
            ParticleUtil.displayParticle(
                    from.clone()
                            .add(line.normalize().clone().multiply(d))
                            .add(new Vector(0, amplitude * Math.sin( d + theta), 0)),
                    getColor().setRed(red)
            );
            line.normalize();
        }

    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getLineSpacing() {
        return lineSpacing;
    }

    public void setLineSpacing(double lineSpacing) {
        this.lineSpacing = lineSpacing;
    }

    public double getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    public double getAngularVelocity() {
        return angularVelocity;
    }

    public void setAngularVelocity(double angularVelocity) {
        this.angularVelocity = angularVelocity;
    }
}
