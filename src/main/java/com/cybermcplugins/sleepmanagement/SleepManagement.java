package com.cybermcplugins.sleepmanagement;

import com.cybermcplugins.sleepmanagement.commands.Sleep;
import com.cybermcplugins.sleepmanagement.listeners.GetsUp;
import com.cybermcplugins.sleepmanagement.listeners.LaysDown;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;


import java.util.ArrayList;
import java.util.UUID;

public final class SleepManagement extends JavaPlugin {

    private ArrayList<UUID> wantsDay = new ArrayList<>();
    private BukkitTask clearWantsDay;
    private long clearWantsDayDelay;


    @Override
    public void onEnable() {

        this.getConfig().options().copyDefaults();
        saveDefaultConfig();
        clearWantsDayDelay = this.getConfig().getLong("delay");

        Bukkit.getPluginManager().registerEvents(new LaysDown(this, this.getConfig().getBoolean("usePercent")), this);
        Bukkit.getPluginManager().registerEvents(new GetsUp(this), this);
        getCommand("sleep").setExecutor(new Sleep(this, this.getConfig().getBoolean("usePercent")));

    }

    public ArrayList<UUID> getWantsDay(){return wantsDay;}

    @SuppressWarnings( {"deprecation"} )
    public void clearWantsDay() {
        this.clearWantsDay = Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
            for (UUID a: wantsDay) {
                wantsDay.remove(a);
            }
        });
    }

    public boolean isUsePercent() { return this.getConfig().getBoolean("usePercent");}

    public int getPercentOfPlayers() {return this.getConfig().getInt("percent");}

    public int getAmountOfPlayers() {return this.getConfig().getInt("amount"); }

}
