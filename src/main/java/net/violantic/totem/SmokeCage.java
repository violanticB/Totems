package net.violantic.totem;

import net.violantic.totem.builder.TotemBuilder;
import net.violantic.totem.builder.TotemComponent;
import net.violantic.totem.mechanic.particle.LineParticleMechanic;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class SmokeCage extends Totem {
    public SmokeCage(Player owner, Location location) {
        super(owner, location);

        TotemBuilder builder = new TotemBuilder(this);
        List<TotemComponent> components = builder.getField().getComponents();

        Vector last = null;
        for (Vector octagonPoint : getOctagonPoints(7)) {

            for(int i = 0; i < 7; i++) {

                Vector current = octagonPoint.clone().add(new Vector(0, i, 0));
                components.add(new TotemComponent(current, i < 6 ? Material.COAL_BLOCK : Material.COBBLESTONE_WALL));

                if(last != null) {

                    Location start = location.clone().add(last);
                    Location end = location.clone().add(octagonPoint).add(0, i + 1, 0);

                    getTotemMechanics().add(new LineParticleMechanic(start, end, Color.BLACK));
                    getTotemMechanics().add(new LineParticleMechanic(location.clone().add(octagonPoint), location.clone().add(last).add(0, i +1, 0), Color.BLACK));

                }

            }

            if(last != null)
                getTotemMechanics().add(new LineParticleMechanic(location.clone().add(last).add(0, 5.5, 0), location.clone().add(octagonPoint).add(0, 5.5, 0), Color.PURPLE));

            last = octagonPoint;
        }

        builder.setBuildTime(20 * 7);
        builder.setDecayTime(20 * 15);
        super.setTotemBuilder(builder);
    }

    public List<Vector> getOctagonPoints(int radius) {
        List<Vector> list = new ArrayList<>();

        double theta = 0;
        double gain = Math.PI / 4;

        for(; theta < Math.PI * 2; theta += gain) {
            double x = radius * Math.sin(theta);
            double z = radius * Math.cos(theta);

            list.add(new Vector(x, 0, z));
        }

        return list;
    }
}
