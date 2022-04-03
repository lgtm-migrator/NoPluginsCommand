package net.simplyvanilla.nopluginscommand;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class NoPluginsCommand extends JavaPlugin implements Listener {

    private static final Set<String> COMMANDS = new HashSet<>(Arrays.asList("?", "pl", "about", "version", "ver", "plugins", "bukkit:?", "bukkit:pl", "bukkit:about", "bukkit:version", "bukkit:ver", "bukkit:plugins", "minecraft:pl", "minecraft:plugins", "minecraft:about", "minecraft:version", "minecraft:ver"));
    private static final Set<String> NO_AUTO_COMPLETE = new HashSet<>(COMMANDS); // or Arrays.asList("command", "command2");

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onCommandUse(PlayerCommandPreprocessEvent event) {
        String command = event.getMessage().toLowerCase().split(" ", 2)[0];

        if (command.length() == 1) { // Command is only /
            return;
        }

        command = command.substring(1);

        if (COMMANDS.contains(command)) {
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void onTabComplete(TabCompleteEvent event) {
        String buffer = event.getBuffer().trim().substring(1);

        if (NO_AUTO_COMPLETE.contains(buffer)) {
            event.getCompletions().clear();
        }
    }

}