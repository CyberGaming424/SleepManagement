package com.cybermcplugins.sleepmanagement;

import com.cybermcplugins.sleepmanagement.commands.Sleep;
import com.cybermcplugins.sleepmanagement.listeners.GetsUp;
import com.cybermcplugins.sleepmanagement.listeners.LaysDown;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public final class SleepManagement extends JavaPlugin {

    private ArrayList<UUID> wantsDay = new ArrayList<>();


    @Override
    public void onEnable() {

        this.getConfig().options().copyDefaults();
        saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(new LaysDown(this, this.getConfig().getBoolean("usePercent")), this);
        Bukkit.getPluginManager().registerEvents(new GetsUp(this), this);
        Objects.requireNonNull(getCommand("sleep")).setExecutor(new Sleep(this));
        this.getLogger().info("Percent of players required: " + getPercentOfPlayers());

    }

    public ArrayList<UUID> getWantsDay(){return wantsDay;}

    public void clearWantsDay() {
        this.getLogger().info("Before: " + wantsDay.toString());
        wantsDay = new ArrayList<>();

        this.getLogger().info("After: " + wantsDay.toString());
    }
    public double getPercentOfPlayers() {
        if(this.getConfig().getDouble("percent") == 0){
            return 100;
        }else{
            return this.getConfig().getDouble("percent");
        }
    }


}
