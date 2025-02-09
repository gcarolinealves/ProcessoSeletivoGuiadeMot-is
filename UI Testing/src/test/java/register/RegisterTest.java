package register;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("User Registration")

public class RegisterTest {
    WebDriver browser;
    WebElement name, gender, dateOfBirth, cep, email, emailConfirmation, password,
            acceptMotelDiscountsPromotions, acceptPartnerAdvertisements, agreeToTermsPrivacyPolicy,
            confirmRegistration;
    String successfulMessage, errorMessage;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        browser.get("https://www.guiademoteis.com.br/usuario/cadastro");

        name = browser.findElement(By.id("Nome"));
        gender = browser.findElement(By.id("sexo-Feminino"));
        dateOfBirth = browser.findElement(By.id("DataNascimento"));
        cep = browser.findElement(By.id("Cep"));
        email = browser.findElement(By.id("Email"));
        emailConfirmation = browser.findElement(By.id("ConfEmail"));
        password = browser.findElement(By.id("Senha"));
        acceptMotelDiscountsPromotions = browser.findElement(By.id("checkbox-descontos-email"));
        acceptPartnerAdvertisements = browser.findElement(By.id("checkbox-publicidade-email"));
        agreeToTermsPrivacyPolicy = browser.findElement(By.id("checkbox-privacy"));
        confirmRegistration = browser.findElement(By.className("btCadastrar"));
    }

    @AfterEach
    public void tearDown(){
       browser.quit();
    }

    @Test
    @DisplayName("Deve registrar com sucesso quando todos os campos forem válidos.")
    public void shouldRegisterSuccessfullyWhenAllFieldsAreValid(){
        name.sendKeys("Guia de Motéis");
        gender.click();
        dateOfBirth.sendKeys("01/01/2000");
        cep.sendKeys("12345678");
        email.sendKeys("guiademoteis011@example.com");
        emailConfirmation.sendKeys("guiademoteis011@example.com");
        password.sendKeys("Guia1234");
        acceptMotelDiscountsPromotions.click();
        acceptPartnerAdvertisements.click();
        agreeToTermsPrivacyPolicy.click();
        scrollToElement(confirmRegistration);
        confirmRegistration.click();
        successfulMessage = browser.findElement(By.id("cadastroOk")).getText();
        assertTrue(successfulMessage.contains("Tudo Ok! Seu cadastro VIP Guia de Motéis foi concluido com sucesso."));
    }


    @Test
    @DisplayName("Deve exibir erro quando os campos obrigatórios estiverem vazios.")
    public void shouldShowErrorWhenRequiredFieldsAreEmpty(){
        name.sendKeys("");
        gender.click();
        dateOfBirth.sendKeys("01/01/2000");
        cep.sendKeys("12345678");
        email.sendKeys("guiademoteis002@example.com");
        emailConfirmation.sendKeys("guiademoteis002@example.com");
        password.sendKeys("Guia1234");
        acceptMotelDiscountsPromotions.click();
        acceptPartnerAdvertisements.click();
        agreeToTermsPrivacyPolicy.click();
        scrollToElement(confirmRegistration);
        confirmRegistration.click();
        errorMessage = browser.findElement(By.xpath("//span[@for='Nome']")).getText();
        assertTrue(errorMessage.contains("Campo nome obrigatório!"));
    }

    @Test
    @DisplayName("Deve exibir erro quando a senha for fraca.")
    public void shouldShowErrorWhenPasswordIsWeak(){
        name.sendKeys("Guia de Motéis");
        gender.click();
        dateOfBirth.sendKeys("01/01/2000");
        cep.sendKeys("12345678");
        email.sendKeys("guiademoteis002@example.com");
        emailConfirmation.sendKeys("guiademoteis002@example.com");
        password.sendKeys("1");
        acceptMotelDiscountsPromotions.click();
        acceptPartnerAdvertisements.click();
        agreeToTermsPrivacyPolicy.click();
        scrollToElement(confirmRegistration);
        confirmRegistration.click();
        errorMessage = browser.findElement(By.xpath("//span[@for='Senha']")).getText();
        assertTrue(errorMessage.contains("Senha deve ter 4 ou mais caracteres."));
    }

    @Test
    @DisplayName("Deve exibir erro quando a confirmação de e-mail não corresponder.")
    public void shouldShowErrorWhenEmailConfirmationDoesNotMatch(){
        name.sendKeys("Guia de Motéis");
        gender.click();
        dateOfBirth.sendKeys("01/01/2000");
        cep.sendKeys("12345678");
        email.sendKeys("guiademoteis002@example.com");
        emailConfirmation.sendKeys("guiademoteis003@example.com");
        password.sendKeys("Guia1234");
        acceptMotelDiscountsPromotions.click();
        acceptPartnerAdvertisements.click();
        agreeToTermsPrivacyPolicy.click();
        scrollToElement(confirmRegistration);
        confirmRegistration.click();
        errorMessage = browser.findElement(By.xpath("//span[@for='ConfEmail']")).getText();
        assertTrue(errorMessage.contains("O campo confirmação de email deve ser identico ao campo email."));
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) browser;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
