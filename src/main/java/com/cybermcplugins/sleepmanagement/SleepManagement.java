package com.cybermcplugins.sleepmanagement;

import com.cybermcplugins.sleepmanagement.commands.Sleep;
import com.cybermcplugins.sleepmanagement.commands.SleepTab;
import com.cybermcplugins.sleepmanagement.listeners.GetsUp;
import com.cybermcplugins.sleepmanagement.listeners.LaysDown;
import com.cybermcplugins.sleepmanagement.listeners.PlayerLeaves;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;


import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public final class SleepManagement extends JavaPlugin {

    private ArrayList<UUID> wantsDay = new ArrayList<>();

    private BukkitTask actionBarTask;



    @Override
    public void onEnable() {

        this.getConfig().options().copyDefaults();
        saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(new LaysDown(this), this);
        Bukkit.getPluginManager().registerEvents(new GetsUp(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerLeaves(this), this);
        Objects.requireNonNull(getCommand("sleep")).setExecutor(new Sleep(this));
        Objects.requireNonNull(getCommand("sleep")).setTabCompleter(new SleepTab());
        this.getLogger().info("Percent of players required: " + getPercentOfPlayers());

    }

    public ArrayList<UUID> getWantsDay(){return wantsDay;}

    public void clearWantsDay() {
        wantsDay = new ArrayList<>();
    }
    public double getPercentOfPlayers() {
        if(this.getConfig().getDouble("percent") == -1){
            return 100;
        }else{
            return this.getConfig().getDouble("percent");
        }
    }

    public void notEnoughPlayers(Player player){
        Bukkit.broadcastMessage(ChatColor.BOLD.GRAY + "[" + ChatColor.GREEN + "SleepManagement" + ChatColor.BOLD.GRAY + "] "
                + ChatColor.BOLD + player.getDisplayName() + " wants day.\n" + "Current players who want the time to be day: " +
                getWantsDay().size() + "\nRequired percent of players: " +
                getPercentOfPlayers() + "\nIf you wish for it to" +
                " be day time either find a bed or do /sleep");
        if(this.getConfig().getBoolean("useActionBar")) {
            if(actionBarTask!=null){
                actionBarTask.cancel();
            }
            actionBarTask = Bukkit.getScheduler().runTaskTimer(this, ()->{
                Bukkit.getOnlinePlayers().forEach(player1 -> {
                    player1.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacy(String.valueOf(getWantsDay().size())+
                            "/"+ (int)Math.round((getPercentOfPlayers()/100)*Bukkit.getOnlinePlayers().size()) + " players sleeping"));
                });
            },0, 40);
        }
    }

    public void cancelActionBar(){
        if(actionBarTask!=null){
            actionBarTask.cancel();
        }
    }
}
