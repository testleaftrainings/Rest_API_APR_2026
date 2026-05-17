package week4.day2;

import org.aeonbits.owner.Config;

@Config.Sources({
	"file:config.properties"
})
public interface ServiceNowConfig extends Config {
	
	@Key("url")
	String getUrl();
	
	@Key("path")
	String getPath();
	
	@Key("username")
	String getUserName();
	
	@Key("password")
	String getPassword();
	
	@Key("tableName")
	String getTableName();

}