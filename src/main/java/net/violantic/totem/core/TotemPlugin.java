package net.violantic.totem.core;

import net.violantic.totem.TotemManager;
import net.violantic.totem.listener.BlockPlaceListener;
import org.bukkit.plugin.java.JavaPlugin;

public class TotemPlugin extends JavaPlugin {

    private static TotemPlugin instance;
    private TotemManager manager;

    public void onEnable() {
        instance = this;
        this.manager = new TotemManager(this);
        registerListener();
    }

    public static TotemPlugin getInstance() {
        return instance;
    }

    public TotemManager getTotemManager() {
        return manager;
    }

    private void registerListener() {
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(this), this);
    }

}
