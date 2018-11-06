# Selenium SuperDriver
 	 
Framework to make Selenium easier, lighter, cleaner and more robust.

## 'The proof is in the pudding'

### By using SuperDriver and SuperChecker...

```java
    public void LoginTestWhitSD() throws InterruptedException, AttributeNotFoundException{
        _sd.loadURL("http://automationpractice.com/index.php");
        _sd.click(By.className("login"));
        _sd.sendKeys(By.id("email"), "prueba001@email.com");
        _sd.sendKeys(By.id("passwd"), "password");
        _sd.click(By.id("SubmitLogin"));
        _sc.assertElementDisplayed(By.className("navigation_page"));
    }
``` 	 

### By using equivalent Selenium code...

```java
    public void LoginTestWhitoutSD() throws InterruptedException, AttributeNotFoundException{
        final int WAIT_TIMEOUT = 6;
        By selector = null;
        WebElement elem = null;
        WebDriverWait wait = null;

        //_sd.loadURL("http://automationpractice.com/index.php");
        _driver.get("http://automationpractice.com/index.php");

        //_sd.click(By.className("login"));
        try {
            selector = By.className("login");
            wait = new WebDriverWait(_driver, WAIT_TIMEOUT);
            wait.until(ExpectedConditions.presenceOfElementLocated(selector)); 
            elem = _driver.findElement(selector);
            if (elem != null) {
                wait.until(ExpectedConditions.elementToBeClickable(elem));
                elem.click();
            }
            else throw new NoSuchElementException("WebElement not found.");

            //_sd.sendKeys(By.id("email"), "prueba001@email.com");
            selector = By.id("email");
            wait = new WebDriverWait(_driver, WAIT_TIMEOUT);
            wait.until(ExpectedConditions.presenceOfElementLocated(selector)); 
            elem = _driver.findElement(selector);
            if (elem != null) elem.sendKeys("prueba001@email.com");
            else throw new NoSuchElementException("WebElement not found.");

            //_sd.sendKeys(By.id("passwd"), "password");
            selector = By.id("passwd");
            wait = new WebDriverWait(_driver, WAIT_TIMEOUT);
            wait.until(ExpectedConditions.presenceOfElementLocated(selector)); 
            elem = _driver.findElement(selector);
            if (elem != null) elem.sendKeys("password");
            else throw new NoSuchElementException("WebElement not found.");

            //_sd.click(By.id("SubmitLogin"));
            selector = By.id("SubmitLogin");
            wait.until(ExpectedConditions.presenceOfElementLocated(selector)); 
            elem = _driver.findElement(selector);
            if (elem != null) {
                wait.until(ExpectedConditions.elementToBeClickable(elem));
                elem.click();
            }
            else throw new NoSuchElementException("WebElement not found.");

            //_sc.assertTrue(_sd.isElementDisplayed(By.className("navigation_page")));
            selector = By.className("navigation_page");
            wait.until(ExpectedConditions.presenceOfElementLocated(selector)); 
            elem = _driver.findElement(selector);

            if (elem != null) Assert.assertTrue(elem.isDisplayed());
            else Assert.fail("Element not found: " + selector.toString());
        } catch (org.openqa.selenium.TimeoutException e) {
            throw new NotFoundException("Element not found " + selector.toString());
        }
    }
```

## And now, the introduction...
 	 
SuperDriver improves and simplifies the automatic testing proccess with Selenium. Specially aimed at beginners in automation, it reduces learning time as well as development time, and increases robustness at the same time. Tests code remain shorter and more understandable, thus creating a more reusable automation project. SuperDriver includes also SuperChecker in order to ease those checks to be done during testing.

This sounds great and really primising... maybe a little bold? Let's try it and give us your feedback ;-)   
   	   
 ## How to include SuperDriver in your project
 
   1) Include the 3 classes compounding SUperDriver.
   2) Add maven dependencies.
   3) Enjoy!
   
In a near future we'll include this project into Maven repositories.
  	 	  	 
  	 	  	 
## Usage example of SuperDriver and SuperChecker
  	 	  	 
To work with SuperDriver once you have it included in your proyect you only need to follow the next steps.
 	 
   1) Create a class where you are going to run your tests.
 	 
   2) Declare as global variables the drivers.
```java  	 	  	 
  		private static ChromeDriver _driver;
  		private static SuperDriver _sd;
  		private static SuperChecker _sc;
  		private static SuperFilter _sf;
```

   3) Create an object from the original driver.
```java  	 	  	 
  		_driver = new ChromeDriver();
```      	     	      	     
   4) Create objects from the classes SuperDriver, SuperFilter and SuperChecker.
```java  	 	  	 
  		_sd = new SuperDriver(_driver);
  		_sc = new SuperChecker(_sd);
  		_sf = new SuperFilter(_sd);
```

  5) Start working with SuperDriver.
  
