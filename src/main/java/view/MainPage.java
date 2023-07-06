package view;

import control.MainPageController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Model;

/**
 * The type Main page.
 *
 * @author Zhongyu Zhou
 */
public class MainPage extends Stage{
    /**
     * The Create recipe button.
     */
    public Button createRecipeButton, /**
     * The Next page button.
     */
    nextPageButton, /**
     * The Previous page button.
     */
    previousPageButton;
    /**
     * The Overlay of search text field.
     */
    public Pane overlaySearchTextField;
    /**
     * The User recipes pane.
     */
    public GridPane userRecipesPane;
    /**
     * The Current Page number.
     */
    public Text pageNumber, /**
     * The Total page number.
     */
    totalPageNumber;
    /**
     * The Main page label.
     */
    public Label mainPageLabel;
    private ImageView imageView;
    private AnchorPane innerAnchorPane;
    private Label slashLabel;
    private final Model model = Model.getInstance();
    private final MainPageController mainPageController = new MainPageController(this, model);

    /**
     * Update user recipe pane.
     */
    public void updateUserRecipePane(){
        model.updateMainPage(userRecipesPane, Integer.parseInt(pageNumber.getText()));
    }

    private void addListener(){
        previousPageButton.setOnMouseClicked(mainPageController);
        nextPageButton.setOnMouseClicked(mainPageController);
        createRecipeButton.setOnMouseClicked(mainPageController);
        overlaySearchTextField.setOnMouseClicked(mainPageController);
    }

    /**
     * Instantiates a new Main page.
     */
    public MainPage() {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(507.0);
        anchorPane.setPrefWidth(773.0);

        setUserRecipesPane();
        setImageBackground();
        setInnerPane();
        setSearchTextField();
        setCreateRecipeButton();
        setPageLabel();
        setPageNumberDisplay();
        setNextPageButton();
        setPreviousPageButton();
        addListener();

        innerAnchorPane.getChildren().addAll(
                createRecipeButton,
                mainPageLabel,
                pageNumber,
                previousPageButton,
                slashLabel,
                totalPageNumber,
                nextPageButton,
                userRecipesPane);

        anchorPane.getChildren().addAll(imageView, innerAnchorPane);

        Scene scene = new Scene(anchorPane);
        this.setScene(scene);
        this.setTitle("Recipe Go !");
        this.setResizable(false);
    }

    private void setUserRecipesPane(){
        userRecipesPane = new GridPane();
        userRecipesPane.setHgap(20);
        userRecipesPane.setVgap(20);
        userRecipesPane.setLayoutX(30);
        userRecipesPane.setLayoutY(150);

        RowConstraints row1 = new RowConstraints();
        row1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        userRecipesPane.getRowConstraints().addAll(row1);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        ColumnConstraints col3 = new ColumnConstraints();
        col1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        ColumnConstraints col4 = new ColumnConstraints();
        col2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        userRecipesPane.getColumnConstraints().addAll(col1, col2, col3, col4);
    }

    private void setPreviousPageButton() {
        previousPageButton = new Button();
        previousPageButton.setLayoutX(219.0);
        previousPageButton.setLayoutY(407.0);
        previousPageButton.setPrefHeight(45.0);
        previousPageButton.setPrefWidth(45.0);
        previousPageButton.setText("<");
        previousPageButton.setFont(Font.font(21.0));
        previousPageButton.setVisible(true);
    }

    private void setNextPageButton() {
        nextPageButton = new Button();
        nextPageButton.setLayoutX(423.0);
        nextPageButton.setLayoutY(407.0);
        nextPageButton.setPrefHeight(45.0);
        nextPageButton.setPrefWidth(45.0);
        nextPageButton.setText(">");
        nextPageButton.setFont(Font.font(21.0));
        nextPageButton.setVisible(true);
    }

    private void setPageNumberDisplay() {
        pageNumber = new Text();
        pageNumber.setLayoutX(300);
        pageNumber.setLayoutY(440);
        pageNumber.setFont(new Font(20));

        slashLabel = new Label();
        slashLabel.setLayoutX(341.0);
        slashLabel.setLayoutY(409.0);
        slashLabel.setText("/");
        slashLabel.setFont(Font.font(27.0));

        totalPageNumber = new Text();
        totalPageNumber.setLayoutX(380);
        totalPageNumber.setLayoutY(440);
        totalPageNumber.setFont(new Font(20));
    }

    private void setPageLabel() {
        mainPageLabel = new Label();
        mainPageLabel.setLayoutX(150);
        mainPageLabel.setLayoutY(14.0);
        mainPageLabel.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 23px; -fx-text-fill: rgba(0,51,255,0.8)");
    }


    private void setCreateRecipeButton() {
        createRecipeButton = new Button();
        createRecipeButton.setLayoutX(554.0);
        createRecipeButton.setLayoutY(408.0);
        createRecipeButton.setMnemonicParsing(false);

        createRecipeButton.setPrefHeight(37.0);
        createRecipeButton.setPrefWidth(145.0);
        createRecipeButton.setText("Create Your Recipe !");
        createRecipeButton.setStyle("-fx-font-family: 'Times New Roman'; -fx-text-fill: #ff0000");
    }

    private void setSearchTextField() {
        TextField searchTextField = new TextField();
        searchTextField.setPrefHeight(37.0);
        searchTextField.setPrefWidth(355.0);

        searchTextField.setText("Find More Recipes !");
        searchTextField.setFocusTraversable(false);
        searchTextField.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 20px; -fx-text-fill: #ff0000");
        searchTextField.setEditable(false);
        searchTextField.setMouseTransparent(true);

        overlaySearchTextField = new Pane();
        overlaySearchTextField.requestFocus();
        overlaySearchTextField.setStyle("-fx-background-color: transparent;");
        overlaySearchTextField.setMouseTransparent(false);
        StackPane stackPane = new StackPane(searchTextField, overlaySearchTextField);
        stackPane.setLayoutX(160);
        stackPane.setLayoutY(90);

        innerAnchorPane.getChildren().add(stackPane);
    }

    private void setInnerPane() {
        innerAnchorPane = new AnchorPane();
        innerAnchorPane.setLayoutX(54.0);
        innerAnchorPane.setLayoutY(31.0);
        innerAnchorPane.setPrefHeight(459.0);
        innerAnchorPane.setPrefWidth(716.0);
        innerAnchorPane.setStyle("-fx-background-color: WHITE; -fx-background-radius: 10;");
        DropShadow dropShadow = new DropShadow();
        innerAnchorPane.setEffect(dropShadow);

    }

    private void setImageBackground() {
        imageView = new ImageView(new Image("file:background/background.jpg"));
        imageView.setFitHeight(522.0);
        imageView.setFitWidth(798.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
    }
}

