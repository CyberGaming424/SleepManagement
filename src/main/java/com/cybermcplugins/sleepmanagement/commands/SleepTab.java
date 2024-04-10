package com.cybermcplugins.sleepmanagement.commands;

import com.google.common.base.Strings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class SleepTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {

        List<String> results = new ArrayList<>();

        if(args.length == 1){
            results.add("setpercent");
            results.add("useactionbar");
        }else if(args[0].equalsIgnoreCase("useactionbar") && args.length == 2){
            results.remove("setpercent");
            results.remove("useactionbar");
            results.add("true");
            results.add("t");
            results.add("false");
            results.add("f");
        }

        return results;
    }
}
