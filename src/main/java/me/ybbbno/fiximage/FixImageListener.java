package me.ybbbno.fiximage;

import io.papermc.paper.event.player.PlayerItemFrameChangeEvent;
import me.deadybbb.ybmj.PluginProvider;
import org.bukkit.*;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class FixImageListener implements Listener {
    private final NamespacedKey fixedKey;

    private final PluginProvider plugin;

    public FixImageListener(PluginProvider plugin) {
        this.plugin = plugin;
        this.fixedKey = new NamespacedKey(plugin, "image-frame-fixed");
    }

    @EventHandler
    public void onHoneycomb(PlayerItemFrameChangeEvent event) {
        if (event.getAction() != PlayerItemFrameChangeEvent.ItemFrameChangeAction.ROTATE) return;

        ItemFrame frame = event.getItemFrame();
        PlayerInventory inventory = event.getPlayer().getInventory();

        ItemStack item = inventory.getItemInMainHand();

        if (item.getType() != Material.HONEYCOMB) return;

        frame.getPersistentDataContainer().set(fixedKey, PersistentDataType.BOOLEAN, true);

        frame.setFixed(true);

        int amount = item.getAmount();
        if (amount != 1) {
            item.setAmount(amount - 1);
        } else {
            inventory.removeItem(item);
        }

        Location location = frame.getLocation();
        location.getWorld().playSound(location, Sound.ITEM_HONEYCOMB_WAX_ON, 1, 1);
        location.getWorld().spawnParticle(Particle.WAX_ON, location, 3, 0.1, 0, 0.1);

        event.setCancelled(true);
    }

    @EventHandler
    public void onAxe(PlayerInteractEntityEvent event) {
        if (!(event.getRightClicked() instanceof ItemFrame frame)) return;

        PersistentDataContainer data = frame.getPersistentDataContainer();

        if (!data.has(fixedKey)) return;

        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();

        if (!item.getType().toString().toLowerCase().contains("axe")) return;

        data.remove(fixedKey);

        frame.setFixed(false);

        Location location = frame.getLocation();
        location.getWorld().playSound(location, Sound.ITEM_AXE_WAX_OFF, 1, 1);
        location.getWorld().spawnParticle(Particle.WAX_OFF, location, 3, 0.1, 0, 0.1);
    }
}
