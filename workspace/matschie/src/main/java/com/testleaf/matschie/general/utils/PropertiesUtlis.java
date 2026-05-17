package com.testleaf.matschie.general.utils;

import com.testleaf.matschie.configuration.utils.ConfigurationManager;

public class PropertiesUtlis {	

	public static String getBaseUri() {
		if (ConfigurationManager.getMatschieConfig().apiName().equalsIgnoreCase("SERVICENOW")) {
			return ConfigurationManager.getServicenowConfig().baseUri();
		} else if (ConfigurationManager.getMatschieConfig().apiName().equalsIgnoreCase("RESTFUL-BOOKER")) {
			return ConfigurationManager.getRestfulBookerConfig().baseUri();
		}
		return null;
	}
	
	public static String getBasePath() {
		if (ConfigurationManager.getMatschieConfig().apiName().equalsIgnoreCase("SERVICENOW")) {
			return ConfigurationManager.getServicenowConfig().basePath();
		} else if (ConfigurationManager.getMatschieConfig().apiName().equalsIgnoreCase("RESTFUL-BOOKER")) {
			return ConfigurationManager.getRestfulBookerConfig().basePath();
		}
		return null;
	}
	
	public static String getUsername() {
		if (ConfigurationManager.getMatschieConfig().apiName().equalsIgnoreCase("SERVICENOW")) {
			return ConfigurationManager.getServicenowConfig().username();
		} else if (ConfigurationManager.getMatschieConfig().apiName().equalsIgnoreCase("RESTFUL-BOOKER")) {
			return ConfigurationManager.getRestfulBookerConfig().username();
		}
		return null;
	}
	
	public static String getPassword() {
		if (ConfigurationManager.getMatschieConfig().apiName().equalsIgnoreCase("SERVICENOW")) {
			return ConfigurationManager.getServicenowConfig().password();
		} else if (ConfigurationManager.getMatschieConfig().apiName().equalsIgnoreCase("RESTFUL-BOOKER")) {
			return ConfigurationManager.getRestfulBookerConfig().password();
		}
		return null;
	}

}