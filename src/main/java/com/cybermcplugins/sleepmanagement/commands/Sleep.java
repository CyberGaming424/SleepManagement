package com.cybermcplugins.sleepmanagement.commands;

import com.cybermcplugins.sleepmanagement.SleepManagement;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

public class Sleep implements CommandExecutor {

    private final SleepManagement sleep;

    public Sleep(SleepManagement sleep){
        this.sleep = sleep;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(args.length >= 1){

            switch (args[0].toLowerCase()){
                case "setpercent":
                    if (sender instanceof Player player && !player.hasPermission("sleep.setPercent")) {
                        player.sendMessage(ChatColor.BOLD.GRAY + "[" + ChatColor.GREEN + "SleepManagement" + ChatColor.BOLD.GRAY + "] " +
                                ChatColor.RED + "you do not have the sleep.setPercent permission!");
                    }else{
                        setPercent(args[1]);
                    }
                    break;
                case "useactionbar":
                    if (sender instanceof Player player && !player.hasPermission("sleep.useactionbar")) {
                        player.sendMessage(ChatColor.BOLD.GRAY + "[" + ChatColor.GREEN + "SleepManagement" + ChatColor.BOLD.GRAY + "] " +
                                ChatColor.RED + "you do not have the sleep.useactionbar permission!");
                    }else{
                        if(!useActionbar(args[1])){
                            sender.sendMessage(ChatColor.BOLD.GRAY + "[" + ChatColor.GREEN + "SleepManagement" + ChatColor.BOLD.GRAY + "] "+
                                    ChatColor.RED + "please use either [true, t], or [false, f]");
                        }
                    }
                    break;
            }

        }else {
            if (sender instanceof Player player) {
                standardSleep(player);
            } else
                sender.sendMessage(ChatColor.BOLD.RED + "Just use time set day");
        }

        return false;
    }

    private void standardSleep(Player player){
        if(!sleep.getWantsDay().contains(player.getUniqueId()))
            sleep.getWantsDay().add(player.getUniqueId());

        if (Bukkit.getWorlds().get(0).getTime() >= 12769) {
            if (((double) sleep.getWantsDay().size() / Bukkit.getOnlinePlayers().size())*100 >= sleep.getPercentOfPlayers()) {
                Bukkit.getWorlds().get(0).setTime(6000);
                sleep.clearWantsDay();
                sleep.cancelActionBar();
            } else
                sleep.notEnoughPlayers(player);
        } else
            player.sendMessage(ChatColor.RED + "It is not nighttime!");
    }

    private void setPercent(String percentStr){
        if(StringUtils.isNumeric(percentStr)){
            int percent = Integer.parseInt(percentStr);
            if(percent <= 100){
                sleep.getConfig().set("percent", percent);
                sleep.saveConfig();
            }
        }
    }

    private boolean useActionbar(String truFal){
        if(truFal.equalsIgnoreCase("true") || truFal.equalsIgnoreCase("t")){
            sleep.getConfig().set("useActionBar", true);
            sleep.saveConfig();
        }else if(truFal.equalsIgnoreCase("false") || truFal.equalsIgnoreCase("f")){
            sleep.getConfig().set("useActionBar", false);
            sleep.saveConfig();
        }else{
            return false;
        }
        return true;
    }
}
