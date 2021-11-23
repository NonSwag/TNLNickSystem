package net.nonswag.tnl.nick.utils;

import com.google.gson.JsonObject;
import net.nonswag.tnl.listener.api.player.TNLPlayer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class NickName {

    @Nullable
    public static String get(@Nonnull TNLPlayer player) {
        JsonObject root = player.data().getFile().getJsonElement().getAsJsonObject();
        return root.has("nick-name") ? root.get("nick-name").getAsString() : null;
    }

    public static void set(@Nonnull TNLPlayer player, @Nullable String name) {
        if (name != null && player.getRealName().equals(name)) name = null;
        JsonObject root = player.data().getFile().getJsonElement().getAsJsonObject();
        if (name == null) root.remove("nick-name");
        else root.addProperty("nick-name", name);
        player.setName(name == null ? player.getRealName() : name);
    }
}
