package com.cybermcplugins.sleepmanagement.commands;

import com.cybermcplugins.sleepmanagement.SleepManagement;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Sleep implements CommandExecutor {

    private SleepManagement sleep;

    public Sleep(SleepManagement sleep){
        this.sleep = sleep;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            World world = player.getWorld();
            if (world.getTime() >= 12769) {
                if (sleep.getWantsDay().size() >= 1) {
                    world.setTime(6000);
                } else
                    Bukkit.broadcastMessage(ChatColor.BOLD.GRAY + "[" + ChatColor.GREEN + "SleepManagement" + ChatColor.BOLD.GRAY + "] "
                            + ChatColor.BOLD + player.getDisplayName() + " wants day. Either two people find a bed or" +
                            " one person can sleep and then another can do /sleep");
            } else
                player.sendMessage(ChatColor.RED + "It is not nighttime!");
        }else
            sender.sendMessage(ChatColor.BOLD.RED + "Just use /time set day");

        return false;
    }
}
