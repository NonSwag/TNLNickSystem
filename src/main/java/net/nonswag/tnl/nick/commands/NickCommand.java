package net.nonswag.tnl.nick.commands;

import net.nonswag.tnl.core.api.command.CommandSource;
import net.nonswag.tnl.core.api.command.Invocation;
import net.nonswag.tnl.listener.TNLListener;
import net.nonswag.tnl.listener.api.command.TNLCommand;
import net.nonswag.tnl.listener.api.command.exceptions.PlayerNotOnlineException;
import net.nonswag.tnl.listener.api.player.TNLPlayer;
import net.nonswag.tnl.nick.utils.NickName;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class NickCommand extends TNLCommand {

    public NickCommand() {
        super("nick", "tnl.nick");
    }

    @Override
    protected void execute(@Nonnull Invocation invocation) {
        CommandSource source = invocation.source();
        String[] args = invocation.arguments();
        if (args.length >= 1) {
            if (args[0].equalsIgnoreCase("reset")) {
                if (args.length >= 2) {
                    TNLPlayer arg = TNLPlayer.cast(args[1]);
                    if (arg != null) {
                        if (NickName.get(arg) == null) {
                            source.sendMessage("%prefix% §4" + arg.getName() + "§c is not nicked");
                        } else {
                            source.sendMessage("%prefix% §aUnnicked §6" + arg.getName() + " §8(§7" + arg.getRealName() + "§8)");
                            source.sendMessage("%prefix% §aAll changes takes affect after a rejoin");
                            NickName.set(arg, null);
                        }
                    } else throw new PlayerNotOnlineException(args[1]);
                } else if (source.isPlayer()) {
                    TNLPlayer player = (TNLPlayer) source.player();
                    if (NickName.get(player) != null) {
                        source.sendMessage("%prefix% §aUnnicked you");
                        source.sendMessage("%prefix% §aAll changes takes affect after a rejoin");
                        NickName.set(player, null);
                    } else source.sendMessage("%prefix% §cYou are not nicked");
                } else source.sendMessage("%prefix% §c/nick reset §8[§6Player§8]");
            } else if (args.length >= 2) {
                TNLPlayer arg = TNLPlayer.cast(args[0]);
                if (arg != null) {
                    if (arg.getName().equals(NickName.get(arg))) {
                        source.sendMessage("%prefix% §cNothing could be changed");
                    } else {
                        NickName.set(arg, args[1]);
                        source.sendMessage("%prefix% §6" + arg.getRealName() + "§a is now nicked as §6" + arg.getName());
                        source.sendMessage("%prefix% §aAll changes takes affect after a rejoin");
                    }
                } else throw new PlayerNotOnlineException(args[0]);
            } else if (source.isPlayer()) {
                TNLPlayer player = (TNLPlayer) source.player();
                if (args[0].equals(player.getName())) {
                    source.sendMessage("%prefix% §cNothing could be changed");
                } else {
                    NickName.set(player, args[0]);
                    source.sendMessage("%prefix% §aYou are now nicked as §6" + player.getName());
                    source.sendMessage("%prefix% §aAll changes takes affect after a rejoin");
                }
            } else source.sendMessage("%prefix% §c/nick " + args[0] + " §8[§6Name§8]");
        } else {
            source.sendMessage("%prefix% §c/nick §8[§6Player§8] §8[§6Name§8]");
            if (source.isPlayer()) {
                source.sendMessage("%prefix% §c/nick reset §8(§6Player§8)");
                source.sendMessage("%prefix% §c/nick §8[§6Name§8]");
            } else source.sendMessage("%prefix% §c/nick reset §8[§6Player§8]");
        }
    }

    @Nonnull
    @Override
    protected List<String> suggest(@Nonnull Invocation invocation) {
        List<String> suggestions = new ArrayList<>();
        String[] args = invocation.arguments();
        if (args.length <= 1) {
            suggestions.add("reset");
            for (TNLPlayer all : TNLListener.getOnlinePlayers()) suggestions.add(all.getName());
        } else if (args[0].equalsIgnoreCase("reset")) {
            for (TNLPlayer all : TNLListener.getOnlinePlayers()) suggestions.add(all.getName());
        }
        return suggestions;
    }
}
