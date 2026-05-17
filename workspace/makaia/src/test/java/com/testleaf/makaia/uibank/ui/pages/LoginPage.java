package com.testleaf.makaia.uibank.ui.pages;

import org.openqa.selenium.support.ui.ExpectedConditions;

import com.testleaf.makaia.general.utils.PropertiesHandler;
import com.testleaf.makaia.testng.hooks.UiBankTestNGHooks;
import com.testleaf.makaia.ui.design.Locators;

public class LoginPage extends UiBankTestNGHooks {
	
	public LoginPage() {
		loadUrl(PropertiesHandler.config("uibank.frontend.url"));
		getWait().until(ExpectedConditions.titleIs("UiBank-Welcome"));
	}
	
	public LoginPage enterUserName(String username) {
		type(locateElement(Locators.ID, "username"), username);
		return this;
	}
	
	public LoginPage enterPassword(String password) {
		type(locateElement(Locators.ID, "password"), password);
		return this;
	}
	
	public LoginPage clickLoginButton() {
		click(locateElement(Locators.XPATH, "//button[text()='Sign In']"));
		return this;
	}
	
	public AccountsPage clicksOnAgreePrivacyPolicyButton() {
		click(locateElement(Locators.XPATH, "//button[normalize-space(text())='I agree to the Privacy Policy']"));
		return new AccountsPage();
	}

}