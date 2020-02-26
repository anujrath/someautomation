package specs

import geb.spock.GebReportingSpec
import page.HomePage
import page.LoginPage
import spock.lang.Unroll
import utils.ProjectConfig

@Unroll
class SearchSpec extends GebReportingSpec {

    def setup(){
        [ "curl", "-X", "PUT",ProjectConfig.urlPart + ProjectConfig.serverPath, "-T",
                ProjectConfig.localFilePath]
                .execute()
    }

    def "Search artifacts in the artifactor"() {

        given: "user logs into artifactory"
        to LoginPage
        LoginPage loginPage = at LoginPage;
        loginPage.enterCredentials("admin", "passworda")

        and: " user is on home page"
        HomePage homePage = at HomePage
        homePage.ifOnHomeScreen()

        when: "user searches for artifact"
        homePage.search(searchText)

        then: "artifact should be displayed"
        homePage.findArtifact(arifactName,isDisplayed)

        where:
        searchText | arifactName | isDisplayed
        "doc"      | "doc.pdf"   | "false"
        "asdf"     | "doc.pdf"   | "true"
    }

    def cleanup(){
        [ "curl", "-X", "DELETE",ProjectConfig.urlPart + ProjectConfig.serverPath].execute()
    }

}
