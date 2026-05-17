package com.testleaf.matschie.configuration.utils;

import org.aeonbits.owner.Config;

@Config.Sources({
	"file:matschie-config.properties"
})
public interface MatschieConfig extends Config {
	
	@Key("aut.api.name")
    String apiName();

}