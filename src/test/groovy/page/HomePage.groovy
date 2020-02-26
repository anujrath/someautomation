package page

import geb.Page
import org.openqa.selenium.By

class HomePage extends Page{
    static at = { title.contentEquals("JFrog") }

    static content = {
        artifactsLink { $("a", href: "/ui/repos/tree") }
        searchBox{ $("input", id: "__BVID__23") }
        resultRow(required:false){ $("a", class: "jf-link overflow-ellipsis") }
        searchButton{ $("button", class: "btn btn-search btn-secondary") }
        noArtifactFound{$ ("span", text: contains("No artifacts found"))}
    }

    void ifOnHomeScreen(){
        waitFor{artifactsLink.isDisplayed()}
        assert artifactsLink.isDisplayed()
    }

    void search(searchText){
        artifactsLink.click()
        searchBox.value(searchText)
        searchButton.click()
    }

    void findArtifact(artifactName,isDisplayed){
        if(isDisplayed=="true") {
            assert !resultRow.isDisplayed()
        }else{
            assert waitFor {resultRow.text()}==artifactName
        }
    }
}
