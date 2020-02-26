import org.openqa.selenium.chrome.ChromeDriver

def final String DRIVER = 'src/test/resources/driver/chromedriver.exe'

System.setProperty('webdriver.chrome.driver', DRIVER);

driver = { new ChromeDriver() }

reportsDir = new File("target/geb-reports")
reportOnTestFailureOnly = true

waiting {
    timeout = 5
}