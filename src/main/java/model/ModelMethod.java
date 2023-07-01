package model;

import dao.mappers.IngredientPOJO;
import dao.mappers.RecipePOJO;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.*;

public interface ModelMethod {
    public void loadRecipePage(RecipePage page, int recipeId);
    public void loadEditPage(RecipePage recipePage, CreateRecipePage createRecipePage);
    public void updateIngredient(TableView<IngredientPOJO> tableView, int number);
    public void loadSignupPage(SignupPage page);
    public void loadMainPage(MainPage page);
    public void updateMainPage(GridPane gridPane, int currentPage);
    public void loadSearchResultPage(SearchResultPage page);
    public void displaySearchResult(SearchResultPage page, String name);
    public void displayCategoryResult(SearchResultPage page);
    public void loadCreateRecipePage(CreateRecipePage page);
    public void uploadRecipe(CreateRecipePage page, boolean isUpdate);
    public String imageChoose(Stage stage);
    public Image imageRetrieve(int recipeId);
    public int addUser (
            String userName,
            String password
    );
    public boolean deleteRecipe();
    public boolean updateRecipe(String recipeName, String recipeCategory, String prepareTime, String cookTime, int recommendServeNumber, String instruction, String imagePath);
    public boolean deleteUser(); //尚未使用
    public boolean updateUser(String userName); //尚未使用
    public boolean accountJudge(String userName, String userPassword);
    public boolean accountJudge(int userId, String userPassword);
    public TextFormatter<String> textFieldFormatter(int maxLength, boolean onlyInteger, boolean onlyFloatAndInteger);
    public Alert alertDisplay(Alert.AlertType alertType, String content);
}
