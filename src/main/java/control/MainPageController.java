package control;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.Model;
import view.CreateRecipePage;
import view.MainPage;
import view.SearchResultPage;

/**
 * The type Main page controller is to control the main page.
 *
 * @author Zhongyu Zhou
 */
public class MainPageController implements EventHandler<MouseEvent> {
    /**
     * Instantiates a new Main page controller.
     *
     * @param _mainPage the main page
     * @param _model    the model
     */
    public MainPageController(MainPage _mainPage, Model _model){
        mainPage = _mainPage;
        model = _model;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() == mainPage.overlaySearchTextField) {
            mainPage.hide();
            SearchResultPage temp = new SearchResultPage();
            temp.setOnCloseRequest(e -> model.loadMainPage(mainPage));
            model.loadSearchResultPage(temp);
        }
        if(mouseEvent.getSource() == mainPage.createRecipeButton){
            mainPage.close();
            CreateRecipePage temp = new CreateRecipePage();
            temp.setOnCloseRequest(e -> model.loadMainPage(new MainPage()));
            model.loadCreateRecipePage(temp);
        }
        if(mouseEvent.getSource() == mainPage.nextPageButton){
            int temp = Integer.parseInt(mainPage.pageNumber.getText());
            temp = temp + 1 > Integer.parseInt(mainPage.totalPageNumber.getText()) ? temp : temp + 1;
            mainPage.pageNumber.setText(String.valueOf(temp));
            mainPage.updateUserRecipePane();
        }
        if(mouseEvent.getSource() == mainPage.previousPageButton){
            int temp = Integer.parseInt(mainPage.pageNumber.getText());
            temp = temp - 1 <= 0 ? temp : temp - 1;
            mainPage.pageNumber.setText(String.valueOf(temp));
            mainPage.updateUserRecipePane();
        }
    }
    private final MainPage mainPage;
    private final Model model;
}
