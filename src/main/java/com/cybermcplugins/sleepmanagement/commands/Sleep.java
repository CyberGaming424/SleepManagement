package com.cybermcplugins.sleepmanagement.commands;

import com.cybermcplugins.sleepmanagement.SleepManagement;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Sleep implements CommandExecutor {

    private final SleepManagement sleep;

    public Sleep(SleepManagement sleep){
        this.sleep = sleep;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(!sleep.getWantsDay().contains(player.getUniqueId()))
                sleep.getWantsDay().add(player.getUniqueId());

            if (Bukkit.getWorlds().get(0).getTime() >= 12769) {
                if (((double) sleep.getWantsDay().size() / Bukkit.getOnlinePlayers().size())*100 >= sleep.getPercentOfPlayers()) {
                    Bukkit.getWorlds().get(0).setTime(6000);
                    sleep.clearWantsDay();
                } else
                    Bukkit.broadcastMessage(ChatColor.BOLD.GRAY + "[" + ChatColor.GREEN + "SleepManagement" + ChatColor.BOLD.GRAY + "] "
                            + ChatColor.BOLD + player.getDisplayName() + " wants day.\n" + "Current players who want the time to be day: " +
                            sleep.getWantsDay().size() + "\nRequired percent of players: " +
                            sleep.getPercentOfPlayers() + "\nIf you wish for it to" +
                            " be day time either find a bed or do /sleep");
            } else
                player.sendMessage(ChatColor.RED + "It is not nighttime!");
        }else
            sender.sendMessage(ChatColor.BOLD.RED + "Just use time set day");

        return false;
    }
}
