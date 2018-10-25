package org.superdriver.Examples;

import org.openqa.selenium.By;

public class PCComponentesPage {
	
	/// Login page
	
	public static By LoginButton() {
		return By.className("login");
	}
	
	public static By LoginEmail() {
		return By.id("email");
	}
	
	public static By LoginPassword() {
		return By.id("passwd");
	}
	
	public static By LoginSubmitButton() {
		return By.id("SubmitLogin");
	}

}
