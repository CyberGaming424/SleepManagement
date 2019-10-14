package com.cybermcplugins.sleepmanagement.listeners;

import com.cybermcplugins.sleepmanagement.SleepManagement;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class LaysDown implements Listener {

    private SleepManagement sleep;

    public LaysDown(SleepManagement sleep){
        this.sleep = sleep;
    }

    @EventHandler
    public void laysDown(PlayerBedEnterEvent e){
        Player player = e.getPlayer();
        World world = player.getWorld();
        if(world.getTime() >= 12769) {
            if (world.getPlayers().size() >= 2) {
                sleep.getWantsDay().add(player.getUniqueId());
                if (sleep.getWantsDay().size() > 1) {
                    world.setTime(23000);
                } else
                    Bukkit.broadcastMessage(ChatColor.BOLD.GRAY + "[" + ChatColor.GREEN + "SleepManagement" + ChatColor.BOLD.GRAY + "] "
                            + ChatColor.BOLD + player.getDisplayName() + " wants to sleep. Either find a bed or use /sleep");
            }
        }

    }

}
