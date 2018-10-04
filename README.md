# Selenium SuperDriver

Framework to make Selenium easier, lighter, cleaner and more robust.


## Introduction

SuperDriver improves and simplifies the automatic testing proccess with standard Selenium. Specially aimed at automation beginners, SuperDriver reduces learning time as well as development time, increasing robustness at the same time. SuperDriver includes also SuperChecker in order to ease those checks done during testing.

SuperDriver makes tests more robust, their code shorter and more understandable, and the automation project more reusable.


## Include SuperDriver in your project

- opción de maven + código que hay que meter en el pom

- u opción de descargarse el proyecto


## Usage example of SuperDriver and SuperChecker

To work with SuperDriver once you have it included in your proyect you only need to follow the next steps.
1) Create a class where you are going to run your tests.
2) Declare as global variables the drivers.

    private static ChromeDriver _driver;
	  private static SuperDriver _sd;
	  private static SuperChecker _sc;
    private static SuperFilter _sf;
    
3) Create an object from the original driver.

		_driver = new ChromeDriver();
    
4) Create objects from the classes SuperDriver, SuperFilter and SuperChecker.

		_sd = new SuperDriver(_driver);
		_sc = new SuperChecker(_sd);
		_sf = new SuperFilter(_sd);

5) Start to work with SuperDriver


## Comparison of code between SuperDriver and original

Here is an example of the code that you need to make a login in a testing website with and without SuperDriver.

With SuperDriver.

  public void LoginTestWhitSD() throws InterruptedException, AttributeNotFoundException{
    _sd.loadURL("http://automationpractice.com/index.php");
		_sd.click(How.CLASS_NAME, "login");
		_sd.sendKeys(How.ID, "email", "prueba001@email.com");
		_sd.sendKeys(How.ID, "passwd", "password");
		_sd.click(How.ID, "SubmitLogin");
	}
  
	
  Without SuperDriver.
  
	public void LoginTestWhitoutSD() throws InterruptedException, AttributeNotFoundException{
		_driver.get("http://automationpractice.com/index.php");
		WebDriverWait wait = new WebDriverWait(_driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("login")));
		_driver.findElement(By.className("login")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
		_driver.findElement(By.id("email")).sendKeys("prueba001@email.com");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("passwd")));
		_driver.findElement(By.id("passwd")).sendKeys("password");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("SubmitLogin")));
		_driver.findElement(By.id("SubmitLogin")).click();
}


## Other usage examples

- explicar aquí lo de las clases con ejemplos. Se enumeran y se dice lo qeu llevan

