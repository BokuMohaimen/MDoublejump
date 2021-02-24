package doublejump.mdoublejump;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public final class MDoubleJump extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getServer().getPluginManager().registerEvents(this,this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.getPlayer().setAllowFlight(true);
    }

    @EventHandler
    public void onDoubleJump(PlayerToggleFlightEvent e) {
        Player p = e.getPlayer();
        if (p.getGameMode() != GameMode.CREATIVE) {
            e.setCancelled(true);
            Block b = p.getWorld().getBlockAt(p.getLocation().subtract(0, 2, 0));
            p.playSound(p.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 10, 1);
            if (!b.getType().equals(Material.AIR)) {
                Vector v = p.getLocation().getDirection().multiply(1).setY(1);
                p.setVelocity(v);
            }
        }
    }
}
