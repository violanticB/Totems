package net.violantic.totem;

import net.violantic.totem.builder.TotemBuilder;
import net.violantic.totem.builder.TotemComponent;
import net.violantic.totem.mechanic.function.HealMechanic;
import net.violantic.totem.mechanic.particle.HeartParticleMechanic;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.List;

public class HealthTotem extends Totem {

    public static final Material BLOCK_MATERIAL = Material.STONE;
    public static final int HEIGHT = 4;

    public HealthTotem(Player owner, Location location) {
        super(owner, location);
        super.getTotemMechanics().add(new HeartParticleMechanic(location, Color.FUCHSIA));
        super.getTotemMechanics().add(new HealMechanic(this));

        TotemBuilder builder = new TotemBuilder(this);
        List<TotemComponent> components = builder.getField().getComponents();

        for(int i = 0; i < HEIGHT; i++) {
            components.add(new TotemComponent(
                    new Vector(0, i, 0),
                    BLOCK_MATERIAL
            ));
        }

        super.setTotemBuilder(builder);

    }

}
