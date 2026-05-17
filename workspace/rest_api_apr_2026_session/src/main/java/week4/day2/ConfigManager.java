package week4.day2;

import org.aeonbits.owner.ConfigCache;

public class ConfigManager {
	
	public static ServiceNowConfig getConfigProperties() {
		return ConfigCache.getOrCreate(ServiceNowConfig.class);
	}
	
}