package control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Model;
import view.SearchResultPage;

public class SearchResultPageController implements EventHandler<ActionEvent> {
    public SearchResultPageController(SearchResultPage _searchResultPage, Model _model){
        searchResultPage = _searchResultPage;
        model = _model;
    }

    private SearchResultPage searchResultPage;
    private Model model;

    @Override
    public void handle(ActionEvent actionEvent) {
        if(actionEvent.getSource() == searchResultPage.searchButton) {
            resultDisplay();
        }
    }

    public void categorySearch(){
        model.displayCategoryResult(searchResultPage);
    }

    private void resultDisplay(){
        model.displaySearchResult(searchResultPage, searchResultPage.searchField.getText());
    }
}
