package net.violantic.totem;

import net.violantic.totem.builder.TotemBuilder;
import net.violantic.totem.builder.TotemComponent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.List;

public class PowerTotem extends Totem {

    public static final int HEIGHT = 4;

    public PowerTotem(Player owner, Location location) {
        super(owner, location);
//        super.getTotemMechanics().add(new HelixParticleMechanic(location, Color.RED));

        TotemBuilder builder = new TotemBuilder(this);
        List<TotemComponent> components = builder.getField().getComponents();

        for(int i = 0; i < HEIGHT; i++) {
            components.add(new TotemComponent(
                    new Vector(0, i, 0),
                    Material.COBBLESTONE_WALL
            ));
        }

        super.setTotemBuilder(builder);
    }

}
