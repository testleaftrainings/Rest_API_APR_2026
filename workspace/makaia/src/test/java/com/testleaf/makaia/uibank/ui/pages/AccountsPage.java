package com.testleaf.makaia.uibank.ui.pages;

import org.openqa.selenium.support.ui.ExpectedConditions;

import com.testleaf.makaia.testng.hooks.UiBankTestNGHooks;
import com.testleaf.makaia.ui.design.Locators;

public class AccountsPage extends UiBankTestNGHooks {
	
	public AccountsPage() {
		getWait().until(ExpectedConditions.titleIs("UiBank - Accounts"));
	}
	
	public AccountApply clicksOnApplyNewAccountButton() {
		click(locateElement(Locators.XPATH, "//div[normalize-space(text())='Apply For New Account']"));
		return new AccountApply();
	}

}