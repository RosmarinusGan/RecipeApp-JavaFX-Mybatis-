package control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Model;
import view.CreateRecipePage;

public class EditPageController implements EventHandler<ActionEvent> {
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
