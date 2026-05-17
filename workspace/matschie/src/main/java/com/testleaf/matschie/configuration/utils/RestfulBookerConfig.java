package com.testleaf.matschie.configuration.utils;

import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config;

@LoadPolicy(LoadType.MERGE)
@Config.Sources({
	"file:src/test/resources/restful-booker/config.properties",
	"file:src/test/resources/restful-booker/secret.properties"
})
public interface RestfulBookerConfig extends Config {
	
	@Key("base.uri")
    String baseUri();
	
	@Key("base.path")
    String basePath();
	
	@Key("username")
    String username();
	
	@Key("password")
    String password();

}