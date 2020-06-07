package net.violantic.totem.util;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;

public class ParticleUtil {

    public static void displayParticle(Location location, Color color) {
        Particle.DustOptions options = new Particle.DustOptions(color, 1);
        location.getWorld().spawnParticle(Particle.REDSTONE, location.getX(), location.getY(), location.getZ(), 0, 0, 0, 0, options);
    }

}
