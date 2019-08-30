/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.zhehe;

import java.util.logging.Level;
import java.util.logging.Logger;
import me.zhehe.base.ChunkGeneratorPromised;
import org.bukkit.Bukkit;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class PromisedLand extends JavaPlugin {
	private ChunkGeneratorPromised wgen;
        public static PromisedLand instance;
        private static String[] logo = {
"   .-.                                                  .     .-.                     . ",
"  (_) )-.                           .-.                /     / (_)                   /  ",
"     /   \\  ).--..-._..  .-. .-.    `-' .    .-.  .-../     /      .-.  .  .-.  .-../   ",
"    /     )/    (   )  )/   )   )  /   / \\ ./.-'_(   /     /      (  |   )/   )(   /    ",
" .-/  `--'/      `-'  '/   /   (_.(__./ ._)(__.'  `-'-...-/.    .-.`-'-''/   (  `-'-..  ",
"(_/                             `-'  /                 (_/ `-._.              `-        ",
        };
        private static Logger log;
        
        public PromisedLand() {
            instance = this;
        }

	@Override
	public void onDisable() {
	}

	@Override
	public void onEnable() {
                log = Bukkit.getLogger();
		wgen = new ChunkGeneratorPromised();
                for (String logo1 : logo) {
                    log.log(Level.INFO, logo1);
                }
	}
        
        @Override
	public ChunkGenerator getDefaultWorldGenerator(final String worldName, final String id) {
		return wgen;
	}
}