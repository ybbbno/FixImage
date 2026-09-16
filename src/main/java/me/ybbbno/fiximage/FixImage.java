package me.ybbbno.fiximage;

import me.deadybbb.ybmj.PluginProvider;

public final class FixImage extends PluginProvider {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getServer().getPluginManager().registerEvents(new FixImageListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
