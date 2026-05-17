package com.testleaf.uibank.e2e.tests;

import org.testng.annotations.Test;

import com.testleaf.makaia.general.utils.PropertiesHandler;
import com.testleaf.makaia.general.utils.TestUtils;
import com.testleaf.makaia.testng.hooks.UiBankTestNGHooks;
import com.testleaf.makaia.uibank.api.services.AccountService;
import com.testleaf.makaia.uibank.ui.pages.LoginPage;

public class AccountsTest extends UiBankTestNGHooks {
	
	@Test
	public void userShouldAbleToCreateAccountsInUi() {
		new LoginPage()
		    .enterUserName(PropertiesHandler.config("uibank.username"))
		    .enterPassword(PropertiesHandler.secret("uibank.password"))
		    .clickLoginButton()
		    .clicksOnAgreePrivacyPolicyButton()
		    .clicksOnApplyNewAccountButton()
		    .enterNickName(TestUtils.getTestData("nickName"))
		    .selectSavingsAccountType()
		    .clicksOnApplyButton()
		    .validateAccountNickName(TestUtils.getTestData("nickName"))
		    .extractAccountNumer();
	}
	
	@Test
	public void userShouldAbleToSeeAccountsDetailsInApi() {
		new AccountService()
		    .fliterAccountDetailsByNickName(TestUtils.getTestData("nickName"))
		    .validateSuccessResponse()
		    .validateFriendlyName(TestUtils.getTestData("nickName"))
		    .validateAccountNumber(TestUtils.getTestData("accountNumber"))
		    .validateAccontType("savings");
	}
	
	@Test
	public void userShouldAbleToCreateAccountInApi() {
		
	}
	
	@Test
	public void userShouldAbleToSeeAccountDetailsInUi() {
		
	}

}