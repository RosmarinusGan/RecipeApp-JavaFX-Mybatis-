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

/**
 * The interface Model method displays the methods of Model.
 *
 * @author Liming Gan
 */
public interface ModelMethod {
    /**
     * Load recipe page.
     *
     * @param page     the recipe page
     * @param recipeId the recipe id means which recipe will be load in the page.
     */
    public void loadRecipePage(RecipePage page, int recipeId);

    /**
     * Load edit page.
     *
     * @param recipePage       the recipe page means the edit page from which recipe page.
     * @param createRecipePage the create recipe page means the empty edit page.
     */
    public void loadEditPage(RecipePage recipePage, CreateRecipePage createRecipePage);

    /**
     * Update ingredient.
     *
     * @param tableView the table view means the table view which shows the searching ingredient results.
     * @param number    the number means the serve number user inputs which will change the quantity of ingredients.
     */
    public void updateIngredient(TableView<IngredientPOJO> tableView, int number);

    /**
     * Load signup page.
     *
     * @param page the signup page
     */
    public void loadSignupPage(SignupPage page);

    /**
     * Load main page.
     *
     * @param page the main page
     */
    public void loadMainPage(MainPage page);

    /**
     * Update main page.
     *
     * @param gridPane    the grid pane displays the recipes created by owner.
     * @param currentPage the current page means the current page number in gridPane.
     */
    public void updateMainPage(GridPane gridPane, int currentPage);

    /**
     * Load search result page.
     *
     * @param page the search result page
     */
    public void loadSearchResultPage(SearchResultPage page);

    /**
     * Display search result.
     *
     * @param page the search result page
     * @param name the name means the input of user in the text field
     */
    public void displaySearchResult(SearchResultPage page, String name);

    /**
     * Display category result.
     *
     * @param page the search result page which will show the category search results
     */
    public void displayCategoryResult(SearchResultPage page);

    /**
     * Load create recipe page.
     *
     * @param page the create recipe page
     */
    public void loadCreateRecipePage(CreateRecipePage page);

    /**
     * Upload recipe to the database.
     *
     * @param page     the create recipe page
     * @param isUpdate means this upload is an update or an insert, update is true and insert is false
     */
    public void uploadRecipe(CreateRecipePage page, boolean isUpdate);

    /**
     * Image choose.
     *
     * @param stage the stage where the file chooser will display
     * @return the string means the path of stored image
     */
    public String imageChoose(Stage stage);

    /**
     * Image retrieve.
     *
     * @param recipeId the recipe id means the recipe the image belongs to
     * @return the image
     */
    public Image imageRetrieve(int recipeId);

    /**
     * Add user to database.
     *
     * @param userName the user name
     * @param password the password
     * @return the int means the generated user id
     */
    public int addUser (
            String userName,
            String password
    );

    /**
     * Delete recipe boolean.
     *
     * @return the boolean
     */
    public boolean deleteRecipe();

    /**
     * Update recipe in the database.
     *
     * @param recipeName           the recipe name
     * @param recipeCategory       the recipe category
     * @param prepareTime          the prepare time
     * @param cookTime             the cook time
     * @param recommendServeNumber the recommend serve number
     * @param instruction          the instruction
     * @param imagePath            the image path
     * @return the boolean, updating successfully is true.
     */
    public boolean updateRecipe(String recipeName, String recipeCategory, String prepareTime, String cookTime, int recommendServeNumber, String instruction, String imagePath);

    /**
     * Account judge boolean.
     *
     * @param userName     the user name
     * @param userPassword the user password
     * @return the boolean, if name and password match each other return true, otherwise return false
     */
    public boolean accountJudge(String userName, String userPassword);

    /**
     * Account judge boolean.
     *
     * @param userId       the user id
     * @param userPassword the user password
     * @return the boolean, if id and password match each other return true, otherwise return false
     */
    public boolean accountJudge(int userId, String userPassword);

    /**
     * Text field formatter.
     *
     * @param maxLength           the max length
     * @param onlyInteger         the only integer
     * @param onlyFloatAndInteger the only float and integer
     * @return the text formatter
     */
    public TextFormatter<String> textFieldFormatter(int maxLength, boolean onlyInteger, boolean onlyFloatAndInteger);

    /**
     * Alert display alert.
     *
     * @param alertType the alert type
     * @param content   the content
     * @return the alert
     */
    public Alert alertDisplay(Alert.AlertType alertType, String content);
}
