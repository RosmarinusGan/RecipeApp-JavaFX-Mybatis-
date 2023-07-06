package control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Model;
import view.SearchResultPage;

/**
 * The type Search result page controller is to control the search result page.
 *
 * @author Liming Gan
 */
public class SearchResultPageController implements EventHandler<ActionEvent> {
    /**
     * Instantiates a new Search result page controller.
     *
     * @param _searchResultPage the search result page
     * @param _model            the model
     */
    public SearchResultPageController(SearchResultPage _searchResultPage, Model _model){
        searchResultPage = _searchResultPage;
        model = _model;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(actionEvent.getSource() == searchResultPage.searchButton) {
            resultDisplay();
        }
    }

    /**
     * do searching by Category.
     */
    public void categorySearch(){
        model.displayCategoryResult(searchResultPage);
    }

    private void resultDisplay(){
        model.displaySearchResult(searchResultPage, searchResultPage.searchField.getText());
    }

    private final SearchResultPage searchResultPage;
    private final Model model;
}
