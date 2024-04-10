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

    private final SleepManagement sleep;

    public LaysDown(SleepManagement sleep){
        this.sleep = sleep;
    }

    @EventHandler
    public void laysDown(PlayerBedEnterEvent e){
        Player player = e.getPlayer();
        if(Bukkit.getWorlds().get(0).getTime() >= 12769) {
            sleep.getWantsDay().add(player.getUniqueId());
                    if (((double) sleep.getWantsDay().size() / Bukkit.getOnlinePlayers().size())*100 >= sleep.getPercentOfPlayers()) {
                        Bukkit.getWorlds().get(0).setTime(6000);
                            sleep.clearWantsDay();
                            sleep.cancelActionBar();
                    }else
                        sleep.notEnoughPlayers(player);
        }
    }

}
