package taxtoserver.taxtoserver;

import net.tnemc.core.TNE;
import net.tnemc.core.common.api.TNEAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.math.BigDecimal;


public final class TaxToServer extends JavaPlugin {
    @Override
    public void onEnable() {
    }

    @EventHandler
    public void pluginEvent(PluginEnableEvent pluginEnableEvent) {
        if (pluginEnableEvent.getPlugin().getName().contains("TNE") || pluginEnableEvent.getPlugin().getName().contains("TheNewEconomy")) {
            SQLiteJDBCDriverConnection sql = new SQLiteJDBCDriverConnection();
            while (true) {
                int tax = sql.taxCalculate();
                TNEAPI ecoApi = TNE.instance().api();
                ecoApi.getAccount("Chmoqq").addHoldings(BigDecimal.valueOf(tax));
                SQLiteJDBCDriverConnection.time = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void onDisable() {
    }
}
