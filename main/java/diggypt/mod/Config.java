package diggypt.mod;


import diggypt.mod.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;

public class Config {
	 private static final String CATEGORY_GENERAL = "general";

	    // This values below you can access elsewhere in your mod:
	    public static int rubyChances = 16;
	    public static int sapphireChances = 15;
	    public static int tanzaniteChances = 9;

	    // Call this from CommonProxy.preInit(). It will create our config if it doesn't
	    // exist yet and read the values if it does exist.
	    public static void readConfig() {
	        Configuration cfg = CommonProxy.config;
	        try {
	            cfg.load();
	            initGeneralConfig(cfg);
	        } catch (Exception e1) {
	            System.out.println("OW CONFIG DIDNT LOAD");
	        } finally {
	            if (cfg.hasChanged()) {
	                cfg.save();
	            }
	        }
	    }

	    private static void initGeneralConfig(Configuration cfg) {
	        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");
	        // cfg.getBoolean() will get the value in the config if it is already specified there. If not it will create the value.
	        rubyChances = cfg.getInt("rubyChances", CATEGORY_GENERAL, rubyChances, 0, 32, "Amount of ruby ores that appear per chunk");
	        sapphireChances = cfg.getInt("sapphireChances", CATEGORY_GENERAL, sapphireChances, 0, 32, "Amount of sapphire ores that appear per chunk");
	        tanzaniteChances = cfg.getInt("tanzaniteChances", CATEGORY_GENERAL, tanzaniteChances, 0, 32, "Amount of tanzanite ores that appear per chunk");
	    }
}