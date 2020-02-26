package specs

import geb.spock.GebReportingSpec

import page.LoginPage
import page.HomePage
import spock.lang.Unroll
import utils.ProjectConfig
@Unroll
class LoginSpec extends GebReportingSpec{

    def "valid Credentails"() {

        given: "user go to Jfrog artifactory"
        to LoginPage

        when: "user enters credentials"
        LoginPage loginPage = at LoginPage;
        loginPage.enterCredentials(ProjectConfig.userName,ProjectConfig.password)

        then: "user should be allowed to login "
        HomePage homePage = at HomePage
        homePage.ifOnHomeScreen()
    }

    def "Invalid Credentails"() {

        given: "user go to Jfrog artifactory"
        to LoginPage

        when: "user enters credentials"
        LoginPage loginPage = at LoginPage;
        loginPage.enterCredentials(userName,password)

        then: "Error should be displayed "
        loginPage.validateError(userNameErr,passwordErr,error)

        where:
        userName      | password        | userNameErr               | passwordErr               | error
        ""            | "passworda"     | "Username is required"    | ""                        | ""
        "admin"       | ""              | ""                        | "Password is required"    | ""
        ""            | ""              | "Username is required"    | "Password is required"    | ""
        "admin"       | "passwordda"    | ""                        | ""                       | "Username or password is incorrect"

    }

}