package entrance;

import javafx.stage.Stage;
import view.*;

public class ApplicationEntrance extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws Exception {
        LoginPage loginPage = new LoginPage();
        loginPage.show();
        //MainPage mainPage = new MainPage();
        //mainPage.show();
        //RecipePage recipePage = new RecipePage();
        //recipePage.show();
        //CreateRecipePage createRecipePage = new CreateRecipePage();
        //createRecipePage.show();
        //SearchResultPage searchResultPage = new SearchResultPage();
        //searchResultPage.show();
        //Model model = Model.getInstance();
        //model.loadRecipePage(new RecipePage(), 1);
    }
}
