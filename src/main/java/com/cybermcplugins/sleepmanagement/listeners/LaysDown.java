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
    private boolean usePercent;
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
            if(!sleep.isUsePercent()){
                if(usePercent) {
                    if (sleep.getPercentOfPlayers()/100 >= sleep.getWantsDay().size()/Bukkit.getOnlinePlayers().size()) {
                        sleep.getWantsDay().add(player.getUniqueId());
                        world.setTime(23000);
                        sleep.clearWantsDay();
                    } else
                        notEnoughPlayers();
                }else{
                    if (sleep.getPercentOfPlayers()/100 >= sleep.getWantsDay().size()/Bukkit.getOnlinePlayers().size()) {
                        sleep.getWantsDay().add(player.getUniqueId());
                        if (sleep.getWantsDay().size() > 1) {
                            world.setTime(23000);
                            sleep.clearWantsDay();
                        } else
                            notEnoughPlayers();
                    }
                }
            }
        }

    }

    private void notEnoughPlayers(){
        Bukkit.broadcastMessage(ChatColor.BOLD.GRAY + "[" + ChatColor.GREEN + "SleepManagement" + ChatColor.BOLD.GRAY + "] "
                + ChatColor.BOLD + player.getDisplayName() + " wants to sleep. Either find a bed or use /sleep");
    }

}
