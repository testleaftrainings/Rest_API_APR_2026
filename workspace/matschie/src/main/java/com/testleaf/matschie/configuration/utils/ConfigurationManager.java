package com.testleaf.matschie.configuration.utils;

import org.aeonbits.owner.ConfigCache;

public class ConfigurationManager {
	
	public static MatschieConfig getMatschieConfig() {
		return ConfigCache.getOrCreate(MatschieConfig.class);
	}
	
	public static ServicenowConfig getServicenowConfig() {
		return ConfigCache.getOrCreate(ServicenowConfig.class);
	}
	
	public static RestfulBookerConfig getRestfulBookerConfig() {
		return ConfigCache.getOrCreate(RestfulBookerConfig.class);
	}

}