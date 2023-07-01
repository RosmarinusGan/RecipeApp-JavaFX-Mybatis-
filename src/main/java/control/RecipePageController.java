package control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.WindowEvent;
import model.Model;
import view.CreateRecipePage;
import view.RecipePage;

public class RecipePageController implements EventHandler<ActionEvent> {
    public RecipePageController(RecipePage _recipePage, Model _model){
        recipePage = _recipePage;
        model = _model;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(actionEvent.getSource() == recipePage.editRecipeButton){
            recipePage.close();
            CreateRecipePage tempPage = new CreateRecipePage();
            model.loadEditPage(recipePage, tempPage);
        }
        if(actionEvent.getSource() == recipePage.deleteRecipeButton){
            Alert alert = model.alertDisplay(Alert.AlertType.CONFIRMATION, "Are you sure to Delete this Recipe ?");
            alert.getDialogPane().getButtonTypes().setAll(ButtonType.YES, ButtonType.CANCEL);
            Button tempButton = (Button) alert.getDialogPane().lookupButton(ButtonType.YES);
            tempButton.setOnAction(e ->{
                model.deleteRecipe();
                recipePage.close();
                recipePage.fireEvent(new WindowEvent(recipePage, WindowEvent.WINDOW_CLOSE_REQUEST));
            });
        }
    }

    private RecipePage recipePage;
    private Model model;



}
