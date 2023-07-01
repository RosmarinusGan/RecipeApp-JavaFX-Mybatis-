package view;

import control.RecipePageController;
import dao.mappers.IngredientPOJO;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Model;

public class RecipePage extends Stage{
    public Button editRecipeButton, deleteRecipeButton;
    public TextField recipeNameTextField, cookingTimeTextField, prepareTimeTextField, serveNumberTextField, categoryDisplayField, ownerTextField;
    public TableView<IngredientPOJO> tableView;
    public ImageView recipePicture;
    public TextArea InstructionArea;
    private Tab basicInfoTab, cookingInstructionTab;
    private Label recipeNameLabel, cookingTimeLabel, prepareTimeLabel, serveNumberLabel, categoryLabel, ownerLabel;
    private final Model model;
    private final RecipePageController controller;

    private void addListener(){
        editRecipeButton.setOnAction(controller);
        deleteRecipeButton.setOnAction(controller);
        serveNumberTextField.textProperty().addListener(e -> update());
    }

    public void update(){
        if(!serveNumberTextField.getText().isEmpty()) {
            model.updateIngredient(tableView, Integer.parseInt(serveNumberTextField.getText()));
            tableView.refresh();
        }
    }

    public RecipePage() {
        model = Model.getInstance();
        controller = new RecipePageController(this, model);

        TabPane tabPane = new TabPane();
        tabPane.setPrefSize(773, 507);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        createBasicInfTab();
        createCookingInstructionTab();

        tabPane.getTabs().addAll(basicInfoTab, cookingInstructionTab);
        addListener();

        Scene scene = new Scene(tabPane);
        this.setScene(scene);
        this.setTitle("RecipePage");
        this.setResizable(false);
    }

    private void createCookingInstructionTab() {
        // Creating the "cooking instruction" tab
        cookingInstructionTab = new Tab();
        cookingInstructionTab.setText("cooking instruction");
        cookingInstructionTab.setStyle("-fx-background-color: #8fc6D0;");

        AnchorPane cookingInstructionContent = new AnchorPane();
        cookingInstructionContent.setStyle("-fx-background-color: #8fc6D0;");
        cookingInstructionContent.setPrefSize(200, 180);

        AnchorPane innerAnchorPane = new AnchorPane();
        innerAnchorPane.setLayoutX(53);
        innerAnchorPane.setLayoutY(98);
        innerAnchorPane.setPrefSize(673, 342);
        innerAnchorPane.setStyle("-fx-background-color: #FF88; -fx-background-radius: 10;");
        innerAnchorPane.setEffect(new DropShadow());

        InstructionArea = new TextArea();
        InstructionArea.setLayoutX(20);
        InstructionArea.setLayoutY(23);
        InstructionArea.setPrefSize(634, 290);
        InstructionArea.setStyle("-fx-background-color: #FF88; -fx-background-radius: 10;");
        InstructionArea.setWrapText(true);

        innerAnchorPane.getChildren().add(InstructionArea);

        TextField textField = new TextField();
        textField.setLayoutX(53);
        textField.setLayoutY(24);
        textField.setPrefSize(302, 46);
        textField.setStyle("-fx-background-color: #FF88; -fx-background-radius: 10;");
        textField.setText("Please Follow The Instructions Below:");
        textField.setEffect(new DropShadow());

        cookingInstructionContent.getChildren().addAll(innerAnchorPane, textField);

        cookingInstructionTab.setContent(cookingInstructionContent);

    }

    private void createBasicInfTab() {
        // Creating the "basic information" tab
        basicInfoTab = new Tab();
        basicInfoTab.setText("basic information");
        basicInfoTab.setStyle("-fx-background-color: #FF33;");

        AnchorPane basicInfoContent = new AnchorPane();
        basicInfoContent.setStyle("-fx-background-color: #FF33;");
        basicInfoContent.setPrefSize(200, 180);

        Pane pane = new Pane();
        pane.setLayoutX(41);
        pane.setLayoutY(177);
        pane.setPrefSize(218, 213);
        pane.setStyle("-fx-background-color: #8fc6D0; -fx-background-radius: 10;");
        pane.setEffect(new DropShadow());

        recipePicture = new ImageView();
        recipePicture.setFitHeight(218);
        recipePicture.setFitWidth(221);
        recipePicture.setLayoutX(0);
        recipePicture.setLayoutY(0);
        pane.getChildren().add(recipePicture);

        tableView = new TableView<>();
        tableView.setLayoutX(303);
        tableView.setLayoutY(175);
        tableView.setPrefSize(438, 215);
        tableView.setStyle("-fx-background-color: #8fc6D0; -fx-background-radius: 10;");
        tableView.setEffect(new DropShadow());

        TableColumn<IngredientPOJO, String> ingredientColumn = new TableColumn<>("ingredient name");
        ingredientColumn.setCellValueFactory(new PropertyValueFactory<>("ingredientName"));
        TableColumn<IngredientPOJO, String> quantityColumn = new TableColumn<>("quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        TableColumn<IngredientPOJO, String> unitColumn = new TableColumn<>("unit");
        unitColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));
        TableColumn<IngredientPOJO, String> descriptionColumn = new TableColumn<>("description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        tableView.getColumns().addAll(ingredientColumn, quantityColumn, unitColumn, descriptionColumn);

        displayRecipeName();
        displayCookingTime();
        displayPrepareTime();
        displayServeNumber();
        displayCategory();
        displayOwner();
        setEditAndDeleteButton();

        basicInfoContent.getChildren().addAll(pane, tableView, recipeNameTextField, recipeNameLabel,
                cookingTimeTextField, prepareTimeTextField, cookingTimeLabel, serveNumberLabel,
                serveNumberTextField, categoryLabel, categoryDisplayField, prepareTimeLabel, editRecipeButton, deleteRecipeButton, ownerLabel, ownerTextField);

        basicInfoTab.setContent(basicInfoContent);
    }

    private void setEditAndDeleteButton() {
        editRecipeButton = new Button("edit recipe");
        editRecipeButton.setLayoutX(500);
        editRecipeButton.setLayoutY(409);
        editRecipeButton.setPrefSize(180, 30);
        editRecipeButton.setFont(new Font(22));
        editRecipeButton.setMnemonicParsing(false);

        deleteRecipeButton = new Button("delete recipe");
        deleteRecipeButton.setLayoutX(300);
        deleteRecipeButton.setLayoutY(409);
        deleteRecipeButton.setPrefSize(180, 30);
        deleteRecipeButton.setFont(new Font(22));
        deleteRecipeButton.setMnemonicParsing(false);
    }

    private void displayCategory() {
        categoryLabel = new Label("Category:");
        categoryLabel.setLayoutX(40);
        categoryLabel.setLayoutY(125);
        categoryLabel.setFont(Font.font("System Bold", 15));

        categoryDisplayField = new TextField();
        categoryDisplayField.setEditable(false);
        categoryDisplayField.setLayoutX(129.0);
        categoryDisplayField.setLayoutY(120.0);
        categoryDisplayField.setPrefHeight(30.0);
        categoryDisplayField.setPrefWidth(129.0);
    }

    private void displayServeNumber() {
        serveNumberLabel = new Label("Serve Number:");
        serveNumberLabel.setLayoutX(38);
        serveNumberLabel.setLayoutY(79);
        serveNumberLabel.setFont(Font.font("System Bold Italic", 15));

        serveNumberTextField = new TextField();
        serveNumberTextField.setLayoutX(173);
        serveNumberTextField.setLayoutY(74);
        serveNumberTextField.setPrefSize(84, 30);
        serveNumberTextField.setAlignment(Pos.CENTER);
        serveNumberTextField.setTextFormatter(model.textFieldFormatter(10, true, false));
    }

    private void displayPrepareTime() {
        prepareTimeTextField = new TextField();
        prepareTimeTextField.setLayoutX(527);
        prepareTimeTextField.setLayoutY(74);
        prepareTimeTextField.setPrefSize(157, 30);
     //   prepareTimeTextField.setPromptText("1 hour");
        prepareTimeTextField.setAlignment(Pos.CENTER);
        prepareTimeTextField.setEditable(false);

        prepareTimeLabel = new Label("Prepare Time:");
        prepareTimeLabel.setLayoutX(387);
        prepareTimeLabel.setLayoutY(79);
        prepareTimeLabel.setFont(Font.font("System Bold Italic", 15));
    }

    private void displayCookingTime() {
        cookingTimeTextField = new TextField();
        cookingTimeTextField.setLayoutX(526);
        cookingTimeTextField.setLayoutY(120);
        cookingTimeTextField.setPrefSize(159, 30);
        cookingTimeTextField.setAlignment(Pos.CENTER);
        cookingTimeTextField.setEditable(false);

        cookingTimeLabel = new Label("Cooking Time:");
        cookingTimeLabel.setLayoutX(387);
        cookingTimeLabel.setLayoutY(125);
        cookingTimeLabel.setFont(Font.font("System Bold", 15));
    }

    private void displayRecipeName() {
        recipeNameTextField = new TextField();
        recipeNameTextField.setLayoutX(299);
        recipeNameTextField.setLayoutY(14);
        recipeNameTextField.setPrefSize(385, 46);
        recipeNameTextField.setAlignment(Pos.CENTER);
        recipeNameTextField.setEditable(false);
        recipeNameTextField.setFont(new Font(21));

        recipeNameLabel = new Label("Recipe Name:");
        recipeNameLabel.setLayoutX(64);
        recipeNameLabel.setLayoutY(20);
        recipeNameLabel.setFont(Font.font("System Bold", 27));
    }

    private void displayOwner(){
        ownerTextField = new TextField();
        ownerTextField.setEditable(false);
        ownerTextField.setMouseTransparent(true);
        ownerTextField.setPrefSize(120, 30);
        ownerTextField.setLayoutX(100);
        ownerTextField.setLayoutY(420);

        ownerLabel = new Label("Owner :");
        ownerLabel.setLayoutX(41);
        ownerLabel.setLayoutY(425);
        ownerLabel.setFont(Font.font("System Bold  Italic", 15));

    }
}

