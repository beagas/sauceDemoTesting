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
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Tests {
    static WebDriver _globalDriver;
    WebDriver _globalDriverFull;
    String _email = new String();
    String _password = new String();

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

    @Test//
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


        _globalDriver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();//Backpack in cart
        _globalDriver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();//Bike Light in cart
        _globalDriver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();//Bolt T-shirt in cart
        _globalDriver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();//Fleece Jacket in cart
        _globalDriver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();//Onesie in cart
        _globalDriver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();//T-shirt (Red) in cart
        _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[3]/a")).click();//open cart
        for (int i = 3; i <= 9; i++) {
            String link = "/html/body/div/div/div/div[2]/div/div[1]/div[" + i + "]/div[2]/a/div";
            WebElement resultText = _globalDriver.findElement(By.xpath(link));
            String linkText = resultText.getText();
            if (linkText.contains(backpack)) {
                System.out.println("Backpack is in cart");
            } else if (linkText.contains(bikeLight)) {
                System.out.println("Bike Light is in cart");
            } else if (linkText.contains(tshirt)) {
                System.out.println("Bolt T-shirt is in cart");
            } else if (linkText.contains(jacket)) {
                System.out.println("Fleece Jacket is in cart");
            } else if (linkText.contains(onesie)) {
                System.out.println("Onesie is in cart");
            } else if (linkText.contains(redTshirt)) {
                System.out.println("T-shirt (Red) is in cart");
            }
        }


//        @Test//open the Backpack description
//        public static String testTC0201() {
//            _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[1]/div[2]/div[1]/a/div")).click();//Backpack description
//            WebElement resultText = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div[2]/div[1]"));//text in description
//            Assert.assertEquals(resultText, "Sauce Labs Backpack");
//            _globalDriver.close();
//        }
    }
}