import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Tests {
    static WebDriver _globalDriver;
    WebDriver _globalDriverFull;
    String _email = new String();
    String _password = new String();

    public static boolean isElementPresent(WebDriver driver, By by) {
        try {
            WebElement element = driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @BeforeClass
    public static String generateRandomEmail() {
        String[] domains = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "example.com"};
        String[] characters = {"abcdefghijklmnopqrstuvwxyz", "0123456789"};

        Random random = new Random();
        StringBuilder email = new StringBuilder();

        // Generate username part
        int usernameLength = random.nextInt(10) + 5; // Random length between 5 to 14 characters
        for (int i = 0; i < usernameLength; i++) {
            String characterSet = characters[random.nextInt(2)]; // Selecting either alphabets or numbers
            char randomChar = characterSet.charAt(random.nextInt(characterSet.length()));
            email.append(randomChar);
        }

        // Adding '@' symbol
        email.append("@");

        // Selecting random domain
        String randomDomain = domains[random.nextInt(domains.length)];
        email.append(randomDomain);

        return email.toString();
    }

    @BeforeClass
    public static String generateRandomUserame() {
        String[] characters = {"abcdefghijklmnopqrstuvwxyz", "0123456789"};

        Random random = new Random();
        StringBuilder username = new StringBuilder();

        // Generate username part
        int usernameLength = random.nextInt(10) + 5; // Random length between 5 to 14 characters
        for (int i = 0; i < usernameLength; i++) {
            String characterSet = characters[random.nextInt(2)]; // Selecting either alphabets or numbers
            char randomChar = characterSet.charAt(random.nextInt(characterSet.length()));
            username.append(randomChar);
        }
        return username.toString();
    }

    @BeforeClass
    public void SetupUserDetails() {
        _email = generateRandomEmail();
        _password = generateRandomUserame();
    }

    @BeforeTest
    public void SetupWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        _globalDriver = new ChromeDriver();
        _globalDriver.get("https://www.saucedemo.com/");
        _globalDriver.manage().window().maximize();
        _globalDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        _globalDriver.findElement(By.id("user-name")).sendKeys("problem_user");//suvedamas vartotojo vardas
        _globalDriver.findElement(By.id("password")).sendKeys("secret_sauce");//suvedamas vartotojo sleptažodis
        _globalDriver.findElement(By.id("login-button")).click();

    }

    @Test//Add to cart function
    public static void testTC0101() {
        WebElement resultBackpack = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[1]/div[2]/div[1]/a/div"));
        String backpack = resultBackpack.getText();
        WebElement resultBikeLight = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[2]/div[2]/div[1]/a/div"));
        String bikeLight = resultBikeLight.getText();
        WebElement resultTshirt = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[3]/div[2]/div[1]/a/div"));
        String tshirt = resultTshirt.getText();
        WebElement resultJacket = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[4]/div[2]/div[1]/a/div"));
        String jacket = resultJacket.getText();
        WebElement resultOnesie = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[5]/div[2]/div[1]/a/div"));
        String onesie = resultOnesie.getText();
        WebElement resultRedTshirt = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[6]/div[2]/div[1]/a/div"));
        String redTshirt = resultRedTshirt.getText();
        String linkText = "nothing";
        boolean backpackCart = false;
        boolean bikeLightCart = false;
        boolean tshirtCart = false;
        boolean jacketCart = false;
        boolean onesieCart = false;
        boolean redTshirtCart = false;

        _globalDriver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();//Backpack in cart
        _globalDriver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();//Bike Light in cart
        _globalDriver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();//Bolt T-shirt in cart
        _globalDriver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();//Fleece Jacket in cart
        _globalDriver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();//Onesie in cart
        _globalDriver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();//T-shirt (Red) in cart
        _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[3]/a")).click();//open cart
        var allElementsInCart = _globalDriver.findElements(By.className("inventory_item_name"));//finds all elements from class "inventory_item_name" that are in cart
        for(var x : allElementsInCart)//prints names of all elements that are in class "inventory_item_name"
        {
            System.out.println(x.getText());
        }

//        for (int i = 3; i < 9; i++) {
//            String link = "/html/body/div/div/div/div[2]/div/div[1]/div[" + i + "]/div[2]/a/div";//Products name in cart
//            boolean productExists = isElementPresent(_globalDriver,By.xpath(link));
//            Assert.assertEquals(productExists,false);}

//            try {
//                WebElement resultText = _globalDriver.findElement(By.xpath(link));//get the name of first product in cart
//                linkText = resultText.getText();
//            } catch (Exception e) {
//                continue;
//            }
//
//            if (linkText.contains(backpack)) {
//                backpackCart = true;
//            } else if (linkText.contains(bikeLight)) {
//                bikeLightCart = true;
//            } else if (linkText.contains(tshirt)) {
//                tshirtCart = true;
//            } else if (linkText.contains(jacket)) {
//                jacketCart = true;
//            } else if (linkText.contains(onesie)) {
//                onesieCart = false;
//            } else if (linkText.contains(redTshirt)) {
//                redTshirtCart = false;
//            }
//        }
        if (backpackCart = false){
            System.out.println("Backpack is not in the cart");}
        if (bikeLightCart = false){
            System.out.println("Bike Light is not in the cart");}
        if (tshirtCart = false){
        System.out.println("Bolt T-shirt is not in the cart");}
        if (jacketCart = false){
            System.out.println("Fleece Jacket is not in the cart");}
        if (onesieCart = false){
           System.out.println("Onesie is not in the cart");}
        if (redTshirtCart = false){
            System.out.println("T-shirt (Red) is not in the cart");}
    }

    @Test//Backpack description
        public static void testTC0201() {
            WebElement resultBackpack = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[1]/div[2]/div[1]/a/div"));
            String backpack = resultBackpack.getText();
            _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[1]/div[2]/div[1]/a/div")).click();
            WebElement resultAboutBackpack = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div[2]/div[1]"));
            String aboutBackpack = resultAboutBackpack.getText();
            Assert.assertEquals(backpack, aboutBackpack);
            _globalDriver.close();
        }

        @Test//Bike Light description
    public static void testTC0202() {
        WebElement resultBikeLight = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[2]/div[2]/div[1]/a/div"));
        String bikeLight = resultBikeLight.getText();
        _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[2]/div[2]/div[1]/a/div")).click();
        WebElement result = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div[2]/div[1]"));
        String aboutBikeLight = result.getText();
        Assert.assertEquals(bikeLight, aboutBikeLight);
        _globalDriver.close();
    }

    @Test//Tshirt description
    public static void testTC0203() {
        WebElement resultTshirt = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[3]/div[2]/div[1]/a/div"));
        String tshirt = resultTshirt.getText();
        _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[3]/div[2]/div[1]/a/div")).click();
        WebElement result = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div[2]/div[1]"));
        String aboutTshirt = result.getText();
        Assert.assertEquals(tshirt, aboutTshirt);
        _globalDriver.close();
    }

    @Test//Jacket description
    public static void testTC0204() {
        WebElement resultJacket = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[4]/div[2]/div[1]/a/div"));
        String jacket = resultJacket.getText();
        _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[4]/div[2]/div[1]/a/div")).click();
        WebElement result = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div[2]/div[1]"));
        String aboutJacket = result.getText();
        Assert.assertEquals(jacket, aboutJacket);
        _globalDriver.close();
    }

    @Test//Onesie description
    public static void testTC0205() {
        WebElement resultOnesie = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[5]/div[2]/div[1]/a/div"));
        String onesie = resultOnesie.getText();
        _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[5]/div[2]/div[1]/a/div")).click();
        WebElement result = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div[2]/div[1]"));
        String aboutOnesie = result.getText();
        Assert.assertEquals(onesie, aboutOnesie);
        _globalDriver.close();
    }

    @Test//Red Tshirt description
    public static void testTC0206() {
        WebElement resultRedTshirt = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[6]/div[2]/div[1]/a/div"));
        String redTshirt = resultRedTshirt.getText();
        _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[6]/div[2]/div[1]/a/div")).click();
        WebElement result = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div[2]/div[1]"));
        String aboutRedTshirt = result.getText();
        Assert.assertEquals(redTshirt, aboutRedTshirt);
        _globalDriver.close();
    }
    @Test//Backpack price in cart
    public static void testTC0301() {
        _globalDriver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        WebElement result = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[1]/div[2]/div[2]/div"));
        String itemPrice = result.getText();
        _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[3]/a")).click();//open the cart
        WebElement result1 = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div[1]/div[3]/div[2]/div[2]/div"));//find the item price in cart
        String cartPrice = result1.getText();
        Assert.assertEquals(cartPrice, itemPrice);
        _globalDriver.close();
    }

    @Test//Bike Light price in cart
    public static void testTC0302() {
        _globalDriver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        WebElement result = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[2]/div[2]/div[2]/div"));
        String itemPrice = result.getText();
        _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[3]/a")).click();//open the cart
        WebElement result1 = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div[1]/div[3]/div[2]/div[2]/div"));//find the item price in cart
        String cartPrice = result1.getText();
        Assert.assertEquals(cartPrice, itemPrice);
        _globalDriver.close();
    }

    @Test//Bolt T-shirt price in cart
    public static void testTC0303() {
        _globalDriver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        WebElement result = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[3]/div[2]/div[2]/div"));
        String itemPrice = result.getText();
        _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[3]/a")).click();//open the cart
        WebElement result1 = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div[1]/div[3]/div[2]/div[2]/div"));//find the item price in cart
        String cartPrice = result1.getText();
        Assert.assertEquals(cartPrice, itemPrice);
        _globalDriver.close();
    }

    @Test//Fleece Jacket price in cart
    public static void testTC0304() {
        _globalDriver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        WebElement result = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[4]/div[2]/div[2]/div"));
        String itemPrice = result.getText();
        _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[3]/a")).click();//open the cart
        WebElement result1 = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div[1]/div[3]/div[2]/div[2]/div"));//find the item price in cart
        String cartPrice = result1.getText();
        Assert.assertEquals(cartPrice, itemPrice);
        _globalDriver.close();
    }

    @Test//Onesie price in cart
    public static void testTC0305() {
        _globalDriver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();
        WebElement result = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[5]/div[2]/div[2]/div"));
        String itemPrice = result.getText();
        _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[3]/a")).click();//open the cart
        WebElement result1 = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div[1]/div[3]/div[2]/div[2]/div"));//find the item price in cart
        String cartPrice = result1.getText();
        Assert.assertEquals(cartPrice, itemPrice);
        _globalDriver.close();
    }

    @Test//Red T-Shirt price in cart
    public static void testTC0306() {
        _globalDriver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
        WebElement result = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[6]/div[2]/div[2]/div"));
        String itemPrice = result.getText();
        _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[3]/a")).click();//open the cart
        WebElement result1 = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div[1]/div[3]/div[2]/div[2]/div"));//find the item price in cart
        String cartPrice = result1.getText();
        Assert.assertEquals(cartPrice, itemPrice);
        _globalDriver.close();
    }



//        @Test//open the Backpack description
//        public static String testTC0201() {
//            _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[1]/div[2]/div[1]/a/div")).click();//Backpack description
//            WebElement resultText = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div[2]/div[1]"));//text in description
//            Assert.assertEquals(resultText, "Sauce Labs Backpack");
//            _globalDriver.close();
//        }

}