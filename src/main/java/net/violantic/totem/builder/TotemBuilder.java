package net.violantic.totem.builder;

import net.violantic.totem.Totem;
import net.violantic.totem.core.TotemPlugin;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

public class TotemBuilder {

    private Sound buildSound = Sound.BLOCK_STONE_FALL;
    private Sound finishSound = Sound.ENTITY_ITEM_PICKUP;
    private Sound decaySound = Sound.BLOCK_STONE_BREAK;

    /** Material Field **/
    private Totem totem;
    private TotemField totemField;
    private int buildTime;
    private int decayTime;

    public TotemBuilder(Totem totem) {
        this.totem = totem;
        this.totemField = new TotemField();
        this.buildTime = 10;
    }

    public Sound getBuildSound() {
        return buildSound;
    }

    public void setBuildSound(Sound buildSound) {
        this.buildSound = buildSound;
    }

    public Sound getFinishSound() {
        return finishSound;
    }

    public void setFinishSound(Sound finishSound) {
        this.finishSound = finishSound;
    }

    public Sound getDecaySound() {
        return decaySound;
    }

    public void setDecaySound(Sound decaySound) {
        this.decaySound = decaySound;
    }

    public Totem getTotem() {
        return totem;
    }

    public TotemField getField() {
        return totemField;
    }

    public int getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(int buildTime) {
        this.buildTime = buildTime;
    }

    public int getDecayTime() {
        return decayTime;
    }

    public void setDecayTime(int decayTime) {
        this.decayTime = decayTime;
    }

    public void constructTotem() {
        if(totemField.getComponents().size() == 0) {
            return;
        }

        int interval = buildTime / totemField.getComponents().size();
        System.out.println(totemField.getComponents().size());

        new BukkitRunnable() {
            int index = 0;

            public void run() {
                if (index >= totemField.getComponents().size()) {

                    totem.getLocation().getWorld().playSound(totem.getLocation().clone(), finishSound, 1, 1);
                    totem.registerMechanics();

                    if(decayTime > 0) {
                        new BukkitRunnable() {
                            public void run () {
                                removeTotem();
                            }
                        }.runTaskLater(TotemPlugin.getInstance(), decayTime);
                    }

                    this.cancel();
                    return;
                }

                Block current = totem.getLocation().clone().add(
                        totemField.getComponents().get(index).getVector()
                ).getBlock();

                current.setType(totemField.getComponents().get(index).getMaterial());

                totem.getTotemBlocks().add(current);
                current.getLocation().getWorld().playSound(current.getLocation(), buildSound, 1, 1);

                index++;
            }
        }.runTaskTimer(TotemPlugin.getInstance(), 0, interval).getTaskId();
    }

    public void removeTotem() {
        if(totem.getTotemBlocks().size() == 0) {
            return;
        }

        int interval = buildTime / totem.getTotemBlocks().size();
        new BukkitRunnable() {

            int index = totem.getTotemBlocks().size() - 1;

            @Override
            public void run() {

                if(totem.getTotemBlocks().size() == 0 || index < 0) {
                    totem.unregisterMechanics();
                    cancel();
                    return;
                }

                Block currentBlock = totem.getTotemBlocks().get(index);
                currentBlock.setType(Material.AIR);
                totem.getLocation().getWorld().playSound(currentBlock.getLocation(), decaySound, 1, 1);

                index--;
            }
        }.runTaskTimer(TotemPlugin.getInstance(), 0, interval);
    }

}
