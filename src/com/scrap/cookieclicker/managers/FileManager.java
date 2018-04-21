package com.scrap.cookieclicker.managers;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import lombok.Data;

@Data
public class FileManager {

	public String prefix = getConfigFileConfiguration().getString("prefix");
	
	public File getConfigFile(){
	    return new File("plugins/CookieClicker", "config.yml");
	}
	  
	public FileConfiguration getConfigFileConfiguration() {
	    return org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(getConfigFile());
	}
	  
	public File getSavesFile() {
	    return new File("plugins/CookieClicker", "saves.yml");
	}
	  
	  public FileConfiguration getSavesFileConfiguration() {
	    return org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(getSavesFile());
	  }
	  
	  public void setConfigFile(){
	    FileConfiguration config = getConfigFileConfiguration();
	    
	    config.set("prefix", "§6§bCookieClicker §r§7| ");
	    config.set("timebeforestart", 5);
	    config.set("gametime", 60);
	    
	    try{
	      config.save(getConfigFile());
	    } catch (Exception e) {
	    	Bukkit.getServer().getConsoleSender().sendMessage("§6§bCookieClicker §r§7| §cEin schwerwiegender Fehler ist während des Sicherns von der CONFIG.YML aufgetreten!");
	      e.printStackTrace();
	    }
	  }
	  
	  public void setSavesFile(){
	    try {
	      getSavesFile().createNewFile();
	      java.io.FileWriter fw = new java.io.FileWriter(getSavesFile());
	      fw.write("#Diese Datei speichert die Spielerinformationen ab! (Der Text wird beim erstmaligen Betreten des Servers gelöscht!");
	      fw.close();
	    } catch (java.io.IOException e) {
	    	Bukkit.getServer().getConsoleSender().sendMessage("§6§bCookieClicker §r§7| §cEin schwerwiegender Fehler ist während des Sicherns von der SAVES.YML aufgetreten!");
	      e.printStackTrace();
	    }
	  }
	
	  public void setFiles() {
		  if (getConfigFile().exists()) {
				Bukkit.getServer().getConsoleSender().sendMessage("§6§bCookieClicker §r§7| Die CONFIG.YML existiert bereits! UEBERSPRINGEN");
		      }else {
		    	setConfigFile();
		    	Bukkit.getServer().getConsoleSender().sendMessage("§6§bCookieClicker §r§7| Erstelle die CONFIG.YML");
		      }
		      if (getSavesFile().exists()) {
		    	Bukkit.getServer().getConsoleSender().sendMessage("§6§bCookieClicker §r§7| Die SAVES.YML existiert bereits! UEBERSPRINGEN");
		      }else {
		    	setSavesFile();
		    	Bukkit.getServer().getConsoleSender().sendMessage("§6§bCookieClicker §r§7| Erstelle die SAVES.YML");
		      }
	  	}
	  
}
