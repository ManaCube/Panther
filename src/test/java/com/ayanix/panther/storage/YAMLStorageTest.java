/*
 *                                                            _...---.._
 *                                                        _.'`       -_ ``.
 *                                                    .-'`                 `.
 *                                                 .-`                     q ;
 *                                              _-`                       __  \
 *                                          .-'`                  . ' .   \ `;/
 *                                      _.-`                    /.      `._`/
 *                              _...--'`                        \_`..._
 *                           .'`                         -         `'--:._
 *                        .-`                           \                  `-.
 *                       .                               `-..__.....----...., `.
 *                      '                   `'''---..-''`'              : :  : :
 *                    .` -                '``                           `'   `'
 *                 .-` .` '             .``
 *             _.-` .-`   '            .
 *         _.-` _.-`    .' '         .`
 * (`''--'' _.-`      .'  '        .'
 *  `'----''        .'  .`       .`
 *                .'  .'     .-'`    _____               _    _
 *              .'   :    .-`       |  __ \             | |  | |
 *              `. .`   ,`          | |__) |__ _  _ __  | |_ | |__    ___  _ __
 *               .'   .'            |  ___// _` || '_ \ | __|| '_ \  / _ \| '__|
 *              '   .`              | |   | (_| || | | || |_ | | | ||  __/| |
 *             '  .`                |_|    \__,_||_| |_| \__||_| |_| \___||_|
 *             `  '.
 *             `.___;
 */
package com.ayanix.panther.storage;

import com.ayanix.panther.impl.bukkit.storage.BukkitDefaultStorage;
import com.ayanix.panther.impl.bukkit.storage.BukkitYAMLStorage;
import org.bukkit.plugin.PluginBase;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;

/**
 * Panther - Developed by Lewes D. B.
 * All rights reserved 2017.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({JavaPlugin.class, PluginBase.class})
public class YAMLStorageTest
{

	@Test
	public void testBukkitYAMLStorage()
	{
		JavaPlugin   plugin = PowerMockito.mock(JavaPlugin.class);
		PluginLogger logger = PowerMockito.mock(PluginLogger.class);
		PowerMockito.when(plugin.getLogger()).thenReturn(logger);
		PowerMockito.when(plugin.getDataFolder()).thenReturn(new File(System.getProperty("user.dir") + File.separator + "target"));

		/* Test loading storage */
		BukkitYAMLStorage storage = PowerMockito.spy(new BukkitYAMLStorage(plugin, "test"));

		Assert.assertFalse("Config generated by YAMLStorage must not be null", storage.getConfig() == null);

		/* Test inserting default values */
		BukkitDefaultStorage defaultStorage = PowerMockito.spy(new BukkitDefaultStorage(null, null));
		defaultStorage.insert("testKey", "testValue");

		storage.insertDefault(defaultStorage);

		Assert.assertTrue("Default storage cannot be inserted", storage.getConfig().contains("testKey"));
	}

}