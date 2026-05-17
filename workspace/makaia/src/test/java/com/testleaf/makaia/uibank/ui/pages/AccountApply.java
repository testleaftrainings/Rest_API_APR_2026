package com.testleaf.makaia.uibank.ui.pages;

import org.openqa.selenium.support.ui.ExpectedConditions;

import com.testleaf.makaia.testng.hooks.UiBankTestNGHooks;
import com.testleaf.makaia.ui.design.Locators;

public class AccountApply extends UiBankTestNGHooks {
	
	public AccountApply() {
		getWait().until(ExpectedConditions.titleIs("UiBank-Apply"));
	}
	
	public AccountApply enterNickName(String nickName) {
		type(locateElement(Locators.ID, "accountNickname"), nickName);
		return this;
	}
	
	public AccountApply selectSavingsAccountType() {
		dropdownSelectByValue(locateElement(Locators.ID, "typeOfAccount"), "savings");
		return this;
	}
	
	public AccountCreateResults clicksOnApplyButton() {
		click(locateElement(Locators.XPATH, "//button[text()='Apply']"));
		return new AccountCreateResults();
	}

}