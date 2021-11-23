package net.nonswag.tnl.nick;

import net.nonswag.tnl.listener.api.plugin.TNLPlugin;
import net.nonswag.tnl.nick.commands.NickCommand;
import net.nonswag.tnl.nick.listeners.ConnectionListener;

public class NickSystem extends TNLPlugin {

    @Override
    protected void enable() {
        getEventManager().registerListener(new ConnectionListener());
        getCommandManager().registerCommand(new NickCommand());
    }
}
