package com.mikaeel.nickbook.ui;

import com.mikaeel.nickbook.NickBookPlugin;
import com.mikaeel.nickbook.service.NickService;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public final class NickBook {
    private NickBook() {}
    public static void openRank(NickBookPlugin p, Player player, NickService s) {
        ComponentBuilder b = new ComponentBuilder(s.color("&0Let's get you set up with your nickname!\n&0Choose your &lRANK&r&0.\n\n"));
        String[] ranks = {"DEFAULT", "VIP", "VIP+", "MVP", "MVP+"}; for (String r : ranks) b.append(button(s, "&8➤ " + r + "\n", "/nick rank " + r)); open(player, b.create());
    }
    public static void openSkin(NickBookPlugin p, Player player, NickService s) { ComponentBuilder b = new ComponentBuilder(s.color("&0Choose your &lSKIN&r&0.\n\n")); String[] x={"normal","steve","random","previous"}; String[] y={"My normal skin","Steve/Alex skin","Random skin","Reuse [Previous Skin]"}; for(int i=0;i<x.length;i++) b.append(button(s,"&8➤ &1"+y[i]+"\n","/nick skin "+x[i])); open(player,b.create()); }
    public static void openName(NickBookPlugin p, Player player, NickService s) { ComponentBuilder b = new ComponentBuilder(s.color("&0Choose the &lNAME&r&0.\n\n")); b.append(button(s,"&8➤ &1Use a random name\n","/nick name random")); b.append(button(s,"&8➤ &1Reuse [Previous Name]\n","/nick name previous")); b.append(button(s,"&c/nick reset","/nick reset")); open(player,b.create()); }
    public static void openRandomName(NickBookPlugin p, Player player, NickService s) { s.setRandomName(player); String n=s.get(player).name(); ComponentBuilder b=new ComponentBuilder(s.color("&0Generated username:\n&0&n["+n+"]\n\n")); b.append(button(s,"&a&l[USE NAME]\n","/nick name previous")); b.append(button(s,"&c&l[TRY AGAIN]","/nick name random")); open(player,b.create()); }
    public static void openConfirmation(NickBookPlugin p, Player player, NickService s) { String n=s.get(player).name(); open(player, new ComponentBuilder(s.color("&0You have finished setting up your nickname!\n\n&0You will be nicked as &7"+n+"&0.\n\n&0You will not be nicked in lobbies.\n\n&c/nick reset")).create()); }
    private static BaseComponent[] button(NickService s,String text,String cmd){ BaseComponent[] a=TextComponent.fromLegacyText(s.color(text)); for(BaseComponent c:a){c.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,cmd)); c.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,TextComponent.fromLegacyText(s.color("&eClick to select"))));} return a; }
    private static void open(Player p, BaseComponent[] page){ ItemStack book=new ItemStack(Material.WRITTEN_BOOK); BookMeta m=(BookMeta)book.getItemMeta(); m.setTitle("Nick Setup"); m.setAuthor("NickBook"); m.spigot().addPage(page); book.setItemMeta(m); p.openBook(book); }
}
