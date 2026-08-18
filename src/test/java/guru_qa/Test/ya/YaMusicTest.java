package guru_qa.Test.ya;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.Duration;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class YaMusicTest {
    @BeforeAll
    static void setupConfig() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "2100x1080";
    }

    @EnumSource(LoginSubtext.class)
    @ParameterizedTest(name = "Проверка локализации текстового блока с аккаунтом")
    void testLoginSubtextOnLocalization (LoginSubtext language){
        open("https://music.yandex.ru/");

        if ($(".PaywallModal_closeButton__rkLNM").is(visible, Duration.ofSeconds(2))){
            $(".PaywallModal_closeButton__rkLNM").shouldBe(visible).click();}

        $(".UserID-Wrapper").click();
        var UserWidget = $("iframe.UserWidget-Iframe").shouldBe(visible);
        switchTo().frame(UserWidget);
        $("[data-log='lang']").click();
        String languageKey =language.name().toLowerCase();
        $("[data-key='" + languageKey + "']").click();
        switchTo().defaultContent();

        $(byText(language.headertext)).shouldBe(visible);
        $(byText(language.headersubtext)).shouldBe(visible);

    }

    @ValueSource(strings = {"77777", "777777777", "+7777777-77-777777", "00000000000"})
    @ParameterizedTest(name = "Валидация по введенному значению в поле Введите номер телефона")
    void testPhoneNumberFieldBehavior  (String numberQuery){
        open("https://passport.yandex.ru/");

        $("[data-testid='text-field-input']").setValue(numberQuery).pressEnter();
        $("[data-testid='error-message']").shouldHave(text("Недопустимый формат номера"));
    }

    @CsvFileSource(resources = "/testData/field_login_and_mail_with_valid_Inputs.csv")
    @ParameterizedTest(name = "Вход по полю Введите логин с успешными вариантами значений логина и почты")
    void testSuccessfulLoginWithValidInputs  (String loginQuery){
        open("https://passport.yandex.ru/");

        $("[data-testid='split-add-user-more-button']").click();
        $("[data-testid='menu-option-switchToLogin']").click();
        $("[data-testid='text-field-input']").setValue(loginQuery).pressEnter();
        $("[data-testid='page-password'").shouldBe(visible);
    }

}
