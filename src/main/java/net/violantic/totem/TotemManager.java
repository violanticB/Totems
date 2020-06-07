package net.violantic.totem;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public class TotemManager {

    private final JavaPlugin plugin;
    private final Set<Totem> totems;

    public TotemManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.totems = new HashSet<>();
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public Set<Totem> getTotems() {
        return totems;
    }

    public void createTotem(Totem totem) {
        totems.add(totem);
        totem.getTotemBuilder().constructTotem();
    }
}
