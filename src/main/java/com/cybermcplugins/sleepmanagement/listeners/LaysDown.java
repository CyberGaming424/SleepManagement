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
    private final boolean usePercent;
    private Player player;

    public LaysDown(SleepManagement sleep, boolean usePercent){
        this.sleep = sleep;
        this.usePercent = usePercent;
    }

    @EventHandler
    public void laysDown(PlayerBedEnterEvent e){
        player = e.getPlayer();
        World world = player.getWorld();
        if(world.getTime() >= 12769) {
            sleep.getWantsDay().add(player.getUniqueId());
                    if (((double) sleep.getWantsDay().size() / Bukkit.getOnlinePlayers().size())*100 >= sleep.getPercentOfPlayers()) {
                            world.setTime(6000);
                            sleep.clearWantsDay();
                    }else
                        notEnoughPlayers();
        }
    }

    private void notEnoughPlayers(){
        Bukkit.broadcastMessage(ChatColor.BOLD.GRAY + "[" + ChatColor.GREEN + "SleepManagement" + ChatColor.BOLD.GRAY + "] "
                + ChatColor.BOLD + player.getDisplayName() + " wants day.\n" + "Current players who want day: " +
                (((double) sleep.getWantsDay().size() / Bukkit.getOnlinePlayers().size())*100) + "\nRequired percent of players: " +
                sleep.getPercentOfPlayers() + "\nIf you wish for it to be day time either find a bed or do /sleep");
    }

}
