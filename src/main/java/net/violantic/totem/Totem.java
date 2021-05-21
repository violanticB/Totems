package net.violantic.totem;

import net.violantic.totem.builder.TotemBuilder;
import net.violantic.totem.core.TotemPlugin;
import net.violantic.totem.mechanic.TotemMechanic;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Totem {

    private UUID owner;

    private Location location;
    private List<Block> totemBlocks;
    private TotemBuilder totemBuilder;
    private Set<TotemMechanic> totemMechanics;
    private int decayTime;

    public Totem(Player owner, Location location) {
        this.owner = owner.getUniqueId();
        this.location = location;
        this.totemBlocks = new ArrayList<>();
        this.totemMechanics = new HashSet<>();
        this.decayTime = -1;
    }

    public Player getOwner() {
        return Bukkit.getPlayer(owner);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Block> getTotemBlocks() {
        return totemBlocks;
    }

    public TotemBuilder getTotemBuilder() {
        return totemBuilder;
    }

    public void setTotemBuilder(TotemBuilder totemBuildAnimation) {
        this.totemBuilder = totemBuildAnimation;
    }

    public Set<TotemMechanic> getTotemMechanics() {
        return totemMechanics;
    }

    public void registerMechanics() {
        totemMechanics.forEach((mechanic) -> {
            int taskId = TotemPlugin.getInstance().getServer().getScheduler().scheduleSyncRepeatingTask(
                    TotemPlugin.getInstance(),
                    mechanic,
                    mechanic.getDelay(),
                    mechanic.ticksPerInterval()
            );

            mechanic.setTaskId(taskId);
        });
    }

    public void unregisterMechanics() {
        totemMechanics.forEach((mechanic) -> {
            TotemPlugin.getInstance().getServer().getScheduler().cancelTask(mechanic.getTaskId());
        });
    }

    public int getDecayTime() {
        return decayTime;
    }

    public void setDecayTime(int decayTime) {
        this.decayTime = decayTime;
    }
}
