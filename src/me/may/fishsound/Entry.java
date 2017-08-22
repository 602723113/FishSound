package me.may.fishsound;

import me.may.fishsound.listener.PlayerFishListener;
import me.may.fishsound.util.ActionBarUtils;
import me.may.fishsound.util.TitleUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Entry extends JavaPlugin {

    private static Entry instance;
    private Sound sound;
    private boolean hasTitle;
    private boolean hasActionBar;
    private boolean hasChatBar;

    private int fadeIn;
    private int stay;
    private int fadeOut;
    private String title;
    private String subTitle;

    private String chatBarMessage;
    private String actionBarMessage;

    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        sound = Sound.valueOf(getConfig().getString("Sound"));
        hasTitle = getConfig().getBoolean("TitleOption.Enable");
        hasActionBar = getConfig().getBoolean("ActionBarOption.Enable");
        hasChatBar = getConfig().getBoolean("ChatBarOption.Enable");
        if (hasTitle) {
            fadeIn = getConfig().getInt("TitleOption.FadeIn");
            stay = getConfig().getInt("TitleOption.Stay");
            fadeOut = getConfig().getInt("TitleOption.FadeOut");
            title = getConfig().getString("TitleOption.Title");
            subTitle = getConfig().getString("TitleOption.SubTitle");
        }
        if (hasActionBar){
            actionBarMessage = getConfig().getString("ActionBarOption.Message");
        }
        if (hasChatBar) {
            chatBarMessage = getConfig().getString("ChatBarOption.Message").replaceAll("&", "§");
        }
        Bukkit.getPluginManager().registerEvents(new PlayerFishListener(), this);

    }

    public static Entry getInstance() {
            return instance;
        }

    public void startTips(Player player) {
        player.playSound(player.getLocation(), sound, 0.3f, 1.0f);
        if (hasTitle) {
            TitleUtils.sendTitle(player, fadeIn, stay, fadeOut, title, subTitle);
        }
        if (hasActionBar) {
            ActionBarUtils.sendBar(player, actionBarMessage);
        }
        if (hasChatBar) {
            player.sendMessage(chatBarMessage);
        }
    }
}
