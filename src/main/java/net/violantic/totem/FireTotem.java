package net.violantic.totem;

import net.violantic.totem.builder.TotemBuilder;
import net.violantic.totem.builder.TotemComponent;
import net.violantic.totem.mechanic.particle.LimaconParticleMechanic;
import net.violantic.totem.mechanic.particle.SineWaveParticleMechanic;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class FireTotem extends Totem {

    private Location location;
    private int radius;
    private int height;

    public FireTotem(Player owner, Location location, int radius, int height) {
        super(owner, location);
        this.location = location;
        this.radius = radius;
        this.height = height;

        TotemBuilder builder = new TotemBuilder(this);

        for (Vector towerVector : getTowerVectors()) {
            builder.getField().getComponents().add(new TotemComponent(towerVector, Material.COAL_BLOCK));
        }

        builder.setBuildTime(20);
        builder.setDecayTime(20 * 20);
        builder.setBuildSound(Sound.BLOCK_STONE_PLACE);
        builder.setDecaySound(Sound.BLOCK_STONE_BREAK);
        builder.setFinishSound(Sound.ENTITY_GHAST_SHOOT);

        super.getTotemMechanics().add(new LimaconParticleMechanic(location.clone().add(0, height + 1, 0), Color.ORANGE));
        super.getTotemMechanics().add(new SineWaveParticleMechanic(location.clone().add(0, height + 2, 0), owner, Color.fromRGB(204, 102, 0), 0.1, 1));
        super.setTotemBuilder(builder);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    private List<Vector> getTowerVectors() {
        List<Vector> fieldSet = new ArrayList<Vector>();

        for(int j = 0; j < height; j++) {
            for (double theta = 0; theta < (Math.PI * 2); theta += Math.PI / 48) {
                double i = radius * Math.cos(theta);
                double k = radius * Math.sin(theta);

                fieldSet.add(new Vector(i, j, k));
            }
        }

        return fieldSet;
    }

}
