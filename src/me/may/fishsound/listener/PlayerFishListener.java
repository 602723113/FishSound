package me.may.fishsound.listener;

import me.may.fishsound.Entry;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class PlayerFishListener implements Listener {

    @EventHandler
    public void onFish(PlayerFishEvent e) {
        if (e.getState().equals(PlayerFishEvent.State.BITE)) {
            Entry.getInstance().startTips(e.getPlayer());
        }
    }

}
