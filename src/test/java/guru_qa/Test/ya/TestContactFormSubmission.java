package guru_qa.Test.ya;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;

public class TestContactFormSubmission {
    @BeforeEach
    void setup(){
        Configuration.pageLoadStrategy = "eager";
        open("https://automationexercise.com/contact_us");
    }
    static Stream<Arguments> sendFeedbackViaContactForm(){
        return Stream.of(
                Arguments.of(
                        "Test",
                        "test@gmail.com",
                        "Subject text",
                        "testTestTestTestTestTestTest",
                        new File("src/test/resources/testData/images.jpeg")
                ),
                Arguments.of(
                        "Test1234567890!@#@$%^&*()__+Тест",
                        "Test1234567890.Test1234567890_Test1234567890@gmail.com",
                        "Subject textтест!№;%:?*()_?*()__)12345789-=йукгшнщшзапыуа",
                        "testtextтест!№;%:?*()_?*()__)12345789-=йукгшнщшзапыуа",
                        new File("src/test/resources/testData/field_login_and_mail_with_valid_Inputs.csv")
                )
        );
    }
    @MethodSource
    @ParameterizedTest(name = "Заполнение формы обратной связи с разными параметрами")
    void sendFeedbackViaContactForm(String username, String userEmail, String inputSubject, String setMessage, File fileLoad){

        $("[data-qa='name']").setValue(username);
        $("[data-qa='email']").setValue(userEmail);
        $("[data-qa='subject']").setValue(inputSubject);
        $("[data-qa='message']").setValue(setMessage);
        $("[name='upload_file']").uploadFile(fileLoad);
        $("[data-qa='submit-button']").click();
        switchTo().alert().accept();
        $(".status.alert.alert-success").shouldBe(Condition.visible)
                .shouldHave(Condition.text("Success! Your details have been submitted successfully."));
    }

}


