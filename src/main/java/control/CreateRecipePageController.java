package control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import model.Model;
import view.CreateRecipePage;

public class CreateRecipePageController implements EventHandler<ActionEvent> {
    public CreateRecipePageController(CreateRecipePage _createRecipePage, Model _model){
        createRecipePage = _createRecipePage;
        model = _model;
    }

    private final CreateRecipePage createRecipePage;
    private final Model model;

    @Override
    public void handle(ActionEvent actionEvent) {
        if(actionEvent.getSource() == createRecipePage.saveButton){
            model.uploadRecipe(createRecipePage, false);
        }
        if(actionEvent.getSource() == createRecipePage.uploadButton){
            String temp = model.imageChoose(createRecipePage);
            if(temp != null){
                createRecipePage.updateImage(temp);
            }
        }
        if(actionEvent.getSource() == createRecipePage.addText){
            addText();
        }
        if(actionEvent.getSource() == createRecipePage.deleteText){
            deleteText();
        }
    }

    private void addText(){
        HBox temp = new HBox();

        TextField ingredientName = new TextField();
        ingredientName.setPrefSize(97, 30);
        ingredientName.setTextFormatter(model.textFieldFormatter(20, false, false));

        TextField quantity = new TextField();
        quantity.setPrefSize(97, 30);
        quantity.setTextFormatter(model.textFieldFormatter(20, false, true));

        TextField unit = new TextField();
        unit.setPrefSize(97, 30);
        unit.setTextFormatter(model.textFieldFormatter(20, false, false));

        TextField description = new TextField();
        description.setPrefSize(97, 30);
        description.setTextFormatter(model.textFieldFormatter(40, false, false));

        temp.getChildren().addAll(ingredientName, quantity, unit, description);
        createRecipePage.scrollBox.getChildren().add(temp);
    }

    private void deleteText(){
        int temp = createRecipePage.scrollBox.getChildren().size() - 1;
        if(temp >= 0) {
            createRecipePage.scrollBox.getChildren().remove(temp);
        }
    }
}
