package com.cybermcplugins.sleepmanagement.listeners;


import com.cybermcplugins.sleepmanagement.SleepManagement;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaves implements Listener {

    SleepManagement sleep;

    public PlayerLeaves(SleepManagement sleep) {
        this.sleep = sleep;
    }

    @EventHandler
    public void playerLeaves(PlayerQuitEvent e) {
        sleep.getWantsDay().remove(e.getPlayer().getUniqueId());
        if (Bukkit.getWorlds().get(0).getTime() >= 12769) {
            if (((double) sleep.getWantsDay().size() / Bukkit.getOnlinePlayers().size()) * 100 >= sleep.getPercentOfPlayers()) {
                Bukkit.getWorlds().get(0).setTime(6000);
                sleep.clearWantsDay();
            }
        }
    }
}
