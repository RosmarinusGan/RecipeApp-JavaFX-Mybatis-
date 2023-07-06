package control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Model;
import view.CreateRecipePage;

/**
 * The type Edit page controller is to control the edit page.
 *
 * @author Liming Gan
 */
public class EditPageController implements EventHandler<ActionEvent> {
    /**
     * Instantiates a new Edit page controller.
     *
     * @param _page  the create recipe page
     * @param _model the model
     */
    public EditPageController(CreateRecipePage _page, Model _model){
        page = _page;
        model = _model;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(actionEvent.getSource() == page.saveButton){
            model.uploadRecipe(page, true);
        }
        if(actionEvent.getSource() == page.uploadButton){
            String temp = model.imageChoose(page);
            if(temp != null){
                page.updateImage(temp);
            }
        }
    }

    private final CreateRecipePage page;
    private final Model model;
}
