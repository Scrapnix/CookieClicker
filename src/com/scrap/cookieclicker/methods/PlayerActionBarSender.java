package com.scrap.cookieclicker.methods;

import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer; 

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

public class PlayerActionBarSender {

    private PacketPlayOutChat packet;
	
    public PlayerActionBarSender(String text) {
        PacketPlayOutChat packet = new PacketPlayOutChat(ChatSerializer.a("{\"text\":\"" + text + "\"}"), (byte) 2);
        this.packet = packet;
    }   
    
    public void sendToPlayer(Player p) {
    	((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);;
    }
	
}
