package net.violantic.totem.listener;

import net.violantic.totem.HealthTotem;
import net.violantic.totem.VoidTotem;
import net.violantic.totem.core.TotemPlugin;
import net.violantic.totem.SmokeCage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    private TotemPlugin plugin;

    public BlockPlaceListener(TotemPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * TODO: Create a more dynamic way of managing totem placement
     * @param event
     */
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if(!event.getBlockPlaced().getLocation().subtract(0, 1, 0).getBlock().getType().equals(Material.AIR)) {

            System.out.println("True");

            Location block = event.getBlock().getLocation();
            Location centered = new Location(block.getWorld(), block.getX() + 0.5, block.getY(), block.getZ() + 0.5);
            if(event.getBlock().getType() == Material.STONE) {

                HealthTotem healthTotem = new HealthTotem(event.getPlayer(), centered);
                plugin.getTotemManager().createTotem(healthTotem);
                System.out.println("Placing totem");
            } else if(event.getBlock().getType() == Material.OBSIDIAN) {
                event.getBlock().setType(Material.AIR);

                VoidTotem voidTotem = new VoidTotem(event.getPlayer(), centered);
                plugin.getTotemManager().createTotem(voidTotem);
                System.out.println("Placing totem");
            } else if(event.getBlock().getType() == Material.COAL_BLOCK) {
                event.getBlock().setType(Material.AIR);

                SmokeCage cage = new SmokeCage(event.getPlayer(), centered);
                plugin.getTotemManager().createTotem(cage);
            }
        }
    }

}
