package com.testleaf.makaia.testng.hooks;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.testleaf.makaia.general.utils.AllureHandler;
import com.testleaf.makaia.general.utils.FakerData;
import com.testleaf.makaia.general.utils.PropertiesHandler;
import com.testleaf.makaia.general.utils.TestUtils;
import com.testleaf.makaia.selenium.base.SeleniumBase;
import com.testleaf.makaia.uibank.api.services.TokenService;

import io.qameta.allure.Allure;

public class UiBankTestNGHooks extends SeleniumBase {
	
	@BeforeSuite
	public void beforeSuite() {
		TestUtils.setTestData("nickName", FakerData.generateRandomName());
	}
	
	@BeforeMethod
	public void beforeMethod(Method method) {
		if (method.getName().toUpperCase().contains("UI")) {
			browserLaunch("chrome");
		} else if (method.getName().toUpperCase().contains("API")) {
			new TokenService()
			     .createToken(PropertiesHandler.config("uibank.username"), PropertiesHandler.secret("uibank.password"))
			     .validateCreateTokenResponse()
			     .extractToken();
		}
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {

		if (!result.isSuccess()) {

			if (result.getMethod().getMethodName().toUpperCase().contains("API")) {				
				
				// When API Test got failed				
				
			} else {

				try {
					Allure.addAttachment("Failure Screen Snapshot",
							FileUtils.openInputStream(new File(takeSnap(result.getName()))));
				} catch (IOException e) {
					e.printStackTrace();
				}

				// When UI Test got failed, Attached screenshot in the allure report				

			}

		}
		
		if(result.getMethod().getMethodName().toUpperCase().contains("UI")) {
			quit();
		}

	}
	
	@AfterSuite
	public void afterSuite() {
		AllureHandler.moveHistoryFolderToAllureResults();
	}

}