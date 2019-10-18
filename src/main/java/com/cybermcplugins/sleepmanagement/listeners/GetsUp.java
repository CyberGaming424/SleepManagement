package com.cybermcplugins.sleepmanagement.listeners;

import com.cybermcplugins.sleepmanagement.SleepManagement;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;

public class GetsUp implements Listener {

    private SleepManagement sleep;

    public  GetsUp(SleepManagement sleep){
        this.sleep = sleep;
    }

    @EventHandler
    public void getsUP(PlayerBedLeaveEvent e){
        Player player = e.getPlayer();
        sleep.getWantsDay().remove(player.getUniqueId());
    }

}
