package net.violantic.totem;

import net.violantic.totem.builder.TotemBuilder;
import net.violantic.totem.builder.TotemComponent;
import net.violantic.totem.mechanic.particle.EpicycloidParticleMechanic;
import net.violantic.totem.mechanic.particle.SineWaveParticleMechanic;
import net.violantic.totem.util.VectorUtil;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.List;

public class VoidTotem extends Totem {

    public VoidTotem(Player owner, Location location) {
        super(owner, location);
        super.getTotemMechanics().add(new EpicycloidParticleMechanic(2,0.67, location, Color.fromRGB(255, 0, 140)));

        TotemBuilder builder = new TotemBuilder(this);
        List<TotemComponent> components = builder.getField().getComponents();

        Vector direction = owner.getLocation().getDirection();

        /**
         * Generate the structure of the totem
         * relative to the owner's position
         */

        // initial angle is 120 degrees
        double angle = (2 * Math.PI) / 3;
        Location currentLocation = null;

        for(int i = 0; i < 3; i++) {
            Vector towerOrigin = VectorUtil.rotateAroundAxisY(direction.clone(), angle)
                    .multiply(7);
            currentLocation = location.clone().add(towerOrigin);
            super.getTotemMechanics().add(new SineWaveParticleMechanic(currentLocation.add(0, 8, 0), owner, Color.fromRGB(140, 0, 140), 0.1, 1.5));

            for(int height = 0; height < 7; height++) {
                components.add(new TotemComponent(new Vector(towerOrigin.getX(), height, towerOrigin.getZ()), Material.OBSIDIAN));
            }

            angle += Math.PI / 3;
        }

        builder.setBuildTime(20 * 3);
        builder.setDecayTime(20 * 15);
        super.setTotemBuilder(builder);
    }
}
