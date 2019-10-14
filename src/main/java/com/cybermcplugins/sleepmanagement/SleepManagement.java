package com.cybermcplugins.sleepmanagement;

import com.cybermcplugins.sleepmanagement.commands.Sleep;
import com.cybermcplugins.sleepmanagement.listeners.GetsUp;
import com.cybermcplugins.sleepmanagement.listeners.LaysDown;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public final class SleepManagement extends JavaPlugin {

    private ArrayList<UUID> wantsDay = new ArrayList<>();

    @Override
    public void onEnable() {

        Bukkit.getPluginManager().registerEvents(new LaysDown(this), this);
        Bukkit.getPluginManager().registerEvents(new GetsUp(this), this);
        getCommand("sleep").setExecutor(new Sleep(this));

    }

    public ArrayList<UUID> getWantsDay(){return wantsDay;}

}
