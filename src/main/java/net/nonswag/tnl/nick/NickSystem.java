package net.nonswag.tnl.nick;

import net.nonswag.tnl.listener.api.plugin.PluginUpdate;
import net.nonswag.tnl.listener.api.plugin.TNLPlugin;
import net.nonswag.tnl.listener.api.settings.Settings;
import net.nonswag.tnl.nick.commands.NickCommand;
import net.nonswag.tnl.nick.listeners.ConnectionListener;

public class NickSystem extends TNLPlugin {

    @Override
    public void enable() {
        getEventManager().registerListener(new ConnectionListener());
        getCommandManager().registerCommand(new NickCommand());
        async(() -> {
            if (Settings.AUTO_UPDATER.getValue()) new PluginUpdate(this).downloadUpdate();
        });
    }
}
