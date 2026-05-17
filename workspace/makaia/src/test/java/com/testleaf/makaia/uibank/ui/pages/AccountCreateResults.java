package com.testleaf.makaia.uibank.ui.pages;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.testleaf.makaia.general.utils.TestUtils;
import com.testleaf.makaia.testng.hooks.UiBankTestNGHooks;
import com.testleaf.makaia.ui.design.Locators;

public class AccountCreateResults extends UiBankTestNGHooks {
	
	public AccountCreateResults() {
		getWait().until(ExpectedConditions.titleIs("UiBank-Creation Result"));
	}
	
	public AccountCreateResults validateAccountNickName(String expected) {
		Assert.assertEquals(getElementText(locateElement(Locators.ID, "accountName")), expected);
		return this;
	}
	
	public AccountCreateResults extractAccountNumer() {
		TestUtils.setTestData("accountNumber", getElementText(locateElement(Locators.ID, "accountId")).trim());
		return this;
	}

}