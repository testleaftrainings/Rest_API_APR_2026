package com.testleaf.matschie.configuration.utils;

import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config;

@LoadPolicy(LoadType.MERGE)
@Config.Sources({
	"file:src/test/resources/servicenow/config.properties",
	"file:src/test/resources/servicenow/secret.properties"
})
public interface ServicenowConfig extends Config {
	
	@Key("base.uri")
    String baseUri();
	
	@Key("base.path")
    String basePath();
	
	@Key("username")
    String username();
	
	@Key("password")
    String password();

}