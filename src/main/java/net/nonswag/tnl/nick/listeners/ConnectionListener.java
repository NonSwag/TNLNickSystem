package net.nonswag.tnl.nick.listeners;

import net.nonswag.tnl.listener.api.player.TNLPlayer;
import net.nonswag.tnl.nick.utils.NickName;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import javax.annotation.Nonnull;

public class ConnectionListener implements Listener {

    @EventHandler
    public void onLogin(@Nonnull PlayerLoginEvent event) {
        TNLPlayer player = TNLPlayer.cast(event.getPlayer());
        String name = NickName.get(player);
        if (name != null) player.setName(name);
    }
}
