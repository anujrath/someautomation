package page

import  geb.Page
import org.openqa.selenium.By
import utils.ProjectConfig


class LoginPage extends Page {
    static url = ProjectConfig.AppURL
    static at = { title.contentEquals("JFrog") }
    static content = {
        username { $("input", name: "username") }
        password { $("input", name: "password") }
        login { $("button", type: "submit") }
        errorMsg { $("span", class: "el-alert__title") }
        usernameErr { $(By.xpath("//label[.='Username']/..//div[2]")) }
        passwordErr { $(By.xpath("//label[.='Password']/..//div[2]")) }
    }

    void enterCredentials(userName,passWord) {
        waitFor{username.isDisplayed()}
        username.value(userName)
        password.value(passWord)
        login.click()
    }

    void validateError(userNameErr,passWordErr,error){
        if(userNameErr!=""){
            assert waitFor {usernameErr.text()==userNameErr}
        }
        if(passWordErr!=""){
            assert waitFor {passwordErr.text()==passWordErr}
        }
        if(error!="") {
            assert waitFor { errorMsg.text() } == error
        }
    }

}