package view;

import control.CreateRecipePageController;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.CATEGORY;
import model.Model;

public class CreateRecipePage extends Stage{
    public Button uploadButton, saveButton, addText, deleteText;
    public TextField recipeNameField, serveNumberTextField, prepareTimeTextField, cookingTimeTextField;
    public TextArea instructionTextField;
    public ToggleGroup toggleGroup;
    public VBox scrollBox;
    public TabPane tabPane;
    public ImageView recipePicture;
    private GridPane categoryPane;
    private ScrollPane scrollPane;
    private Tab basicInfoTab, cookingInstructionTab;
    private Label recipeNameLabel, serveNumberLabel, categoryLabel, prepareTimeLabel, cookingTimeLabel, ingredientsLabel, quantityLabel, unitLabel, descriptionLabel;
    private final Model model = Model.getInstance();
    private final CreateRecipePageController controller = new CreateRecipePageController(this, model);

    private void addListener(){
        saveButton.setOnAction(controller);
        uploadButton.setOnAction(controller);
        addText.setOnAction(controller);
        deleteText.setOnAction(controller);
    }

    public void updateImage(String path){
        recipePicture.setImage(new Image("file:" + "image/_" + path));
    }

    public CreateRecipePage( ) {
        tabPane = new TabPane();
        tabPane.setPrefSize(773, 507);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        setBasicInfoTab();
        setCookInstructionTab(tabPane);

        addListener();

        Scene scene = new Scene(tabPane);
        this.setScene(scene);
        this.setTitle("Create Your Recipe");
        this.setResizable(false);
    }

    private void setCookInstructionTab(TabPane tabPane) {
        cookingInstructionTab = new Tab("cooking instruction");
        cookingInstructionTab.setStyle("-fx-background-color: #8fc6D0;");
        AnchorPane cookingInstructionPane = new AnchorPane();
        cookingInstructionPane.setStyle("-fx-background-color: #8fc6D0;");
        cookingInstructionTab.setContent(cookingInstructionPane);

        AnchorPane innerAnchorPane = new AnchorPane();
        innerAnchorPane.setLayoutX(53.0);
        innerAnchorPane.setLayoutY(98.0);
        innerAnchorPane.setPrefHeight(287.0);
        innerAnchorPane.setPrefWidth(673.0);
        innerAnchorPane.setStyle("-fx-background-color: #FF88; -fx-background-radius: 10;");
        DropShadow innerDropShadow = new DropShadow();
        innerDropShadow.setColor(javafx.scene.paint.Color.valueOf("#8d8a8a"));
        innerAnchorPane.setEffect(innerDropShadow);

        instructionTextField = new TextArea();
        instructionTextField.setLayoutX(20.0);
        instructionTextField.setLayoutY(23.0);
        instructionTextField.setPrefHeight(242.0);
        instructionTextField.setPrefWidth(634.0);
        instructionTextField.setStyle("-fx-background-color: rgba(255,255,136,0.53); -fx-background-radius: 10;");
        instructionTextField.setWrapText(true);
        instructionTextField.setTextFormatter(model.textFieldFormatter(2000, false, false));

        Label instructionLabel = new Label("Please Input The Instruction:");
        instructionLabel.setLayoutX(53.0);
        instructionLabel.setLayoutY(24.0);
        instructionLabel.setPrefHeight(46.0);
        instructionLabel.setPrefWidth(256.0);
        instructionLabel.setStyle("-fx-background-color: #FF88; -fx-background-radius: 10;");
        DropShadow instructionDropShadow = new DropShadow();
        instructionDropShadow.setColor(javafx.scene.paint.Color.valueOf("#9a8a8a"));
        instructionLabel.setEffect(instructionDropShadow);

        cookingInstructionPane.getChildren().addAll(innerAnchorPane, instructionLabel);
        innerAnchorPane.getChildren().add(instructionTextField);

        tabPane.getTabs().addAll(basicInfoTab, cookingInstructionTab);
    }

    private void setBasicInfoTab() {
        basicInfoTab = new Tab("basic information");
        basicInfoTab.setStyle("-fx-background-color: #FF33;");
        AnchorPane basicInfoPane = new AnchorPane();
        basicInfoPane.setStyle("-fx-background-color: #FF33;");
        basicInfoTab.setContent(basicInfoPane);

        Pane innerPane = new Pane();
        innerPane.setLayoutX(41.0);
        innerPane.setLayoutY(177.0);
        innerPane.setPrefHeight(213.0);
        innerPane.setPrefWidth(218.0);
        innerPane.setStyle("-fx-background-color: #8fc6D0; -fx-background-radius: 10;");
        DropShadow dropShadow = new DropShadow();
        innerPane.setEffect(dropShadow);

        recipePicture = new ImageView();
        recipePicture.setFitHeight(218);
        recipePicture.setFitWidth(221);
        recipePicture.setLayoutX(0);
        recipePicture.setLayoutY(0);
        innerPane.getChildren().add(recipePicture);

        setUploadPictureButton();
        setIngredientsField();
        setRecipeName();
        setServeNumber();
        setCategory();
        setPrepareTime();
        setCookingTime();
        setSaveButton();

        basicInfoPane.getChildren().addAll(innerPane, uploadButton, addText, deleteText, recipeNameField, recipeNameLabel,
                serveNumberTextField, serveNumberLabel, ingredientsLabel, quantityLabel, categoryLabel, prepareTimeTextField,
                prepareTimeLabel, cookingTimeTextField, cookingTimeLabel, saveButton, categoryPane, scrollPane, unitLabel, descriptionLabel);
    }

    private void setSaveButton() {
        saveButton = new Button("save");
        saveButton.setLayoutX(663.0);
        saveButton.setLayoutY(19.0);
        saveButton.setMnemonicParsing(false);
        saveButton.setPrefHeight(37.0);
        saveButton.setPrefWidth(72.0);
        Font saveButtonFont = new Font(18.0);
        saveButton.setFont(saveButtonFont);
    }

    private void setCookingTime() {
        cookingTimeLabel = new Label("Cooking Time:");
        cookingTimeLabel.setLayoutX(468.0);
        cookingTimeLabel.setLayoutY(112.0);
        Font cookingTimeFont = new Font("System Bold", 15.0);
        cookingTimeLabel.setFont(cookingTimeFont);

        cookingTimeTextField = new TextField();
        cookingTimeTextField.setLayoutX(583.0);
        cookingTimeTextField.setLayoutY(112.0);
        cookingTimeTextField.setPrefHeight(30.0);
        cookingTimeTextField.setPrefWidth(157.0);
        cookingTimeTextField.setTextFormatter(model.textFieldFormatter(20, false, false));
    }

    private void setPrepareTime() {
        prepareTimeLabel = new Label("Prepare Time:");
        prepareTimeLabel.setLayoutX(470.0);
        prepareTimeLabel.setLayoutY(79.0);
        Font prepareTimeFont = new Font("System Bold Italic", 15.0);
        prepareTimeLabel.setFont(prepareTimeFont);

        prepareTimeTextField = new TextField();
        prepareTimeTextField.setLayoutX(584.0);
        prepareTimeTextField.setLayoutY(74.0);
        prepareTimeTextField.setPrefHeight(30.0);
        prepareTimeTextField.setPrefWidth(159.0);
        prepareTimeTextField.setTextFormatter(model.textFieldFormatter(20, false, false));
    }

    private void setCategory() {
        categoryLabel = new Label("Category:");
        categoryLabel.setLayoutX(40.0);
        categoryLabel.setLayoutY(92.0);
        Font categoryFont = new Font("System Bold", 15.0);
        categoryLabel.setFont(categoryFont);

        toggleGroup = new ToggleGroup();
        categoryPane = new GridPane();
        categoryPane.setVgap(5);
        categoryPane.setLayoutX(129);
        categoryPane.setLayoutY(91);

        for(int i = 0; i < CATEGORY.values().length; i++){
            RadioButton tempButton = new RadioButton(CATEGORY.values()[i].getName());
            tempButton.setToggleGroup(toggleGroup);
            categoryPane.add(tempButton, i % 3, i / 3);
        }
    }

    private void setServeNumber() {
        serveNumberLabel = new Label("Serve Number:");
        serveNumberLabel.setLayoutX(40);
        serveNumberLabel.setLayoutY(136);
        Font serveNumberFont = new Font("System Bold Italic", 15.0);
        serveNumberLabel.setFont(serveNumberFont);

        serveNumberTextField = new TextField();
        serveNumberTextField.setLayoutX(178.0);
        serveNumberTextField.setLayoutY(131.0);
        serveNumberTextField.setPrefHeight(30.0);
        serveNumberTextField.setPrefWidth(84.0);
        serveNumberTextField.setTextFormatter(model.textFieldFormatter(10, true, false));
    }

    private void setRecipeName() {
        recipeNameLabel = new Label("Recipe Name:");
        recipeNameLabel.setLayoutX(40.0);
        recipeNameLabel.setLayoutY(20.0);
        Font recipeNameFont = new Font("System Bold", 27.0);
        recipeNameLabel.setFont(recipeNameFont);

        recipeNameField = new TextField();
        recipeNameField.setLayoutX(266.0);
        recipeNameField.setLayoutY(14.0);
        recipeNameField.setPrefHeight(45.0);
        recipeNameField.setPrefWidth(350.0);
        Font textFieldFont = new Font(21.0);
        recipeNameField.setFont(textFieldFont);
        recipeNameField.setTextFormatter(model.textFieldFormatter(30, false, false));
    }

    private void setIngredientsField() {
        ingredientsLabel = new Label("ingredients");
        ingredientsLabel.setAlignment(javafx.geometry.Pos.CENTER);
        ingredientsLabel.setPrefSize(97, 31);
        ingredientsLabel.setStyle("-fx-background-color: #8fc6D0; -fx-background-radius: 10;");
        ingredientsLabel.setLayoutX(371);
        ingredientsLabel.setLayoutY(162);

        quantityLabel = new Label("quantity");
        quantityLabel.setAlignment(javafx.geometry.Pos.CENTER);
        quantityLabel.setPrefSize(97, 31);
        quantityLabel.setStyle("-fx-background-color: #8fc6D0; -fx-background-radius: 10;");
        quantityLabel.setLayoutX(468);
        quantityLabel.setLayoutY(162);

        unitLabel = new Label("unit");
        unitLabel.setAlignment(javafx.geometry.Pos.CENTER);
        unitLabel.setPrefSize(97, 31);
        unitLabel.setStyle("-fx-background-color: #8fc6D0; -fx-background-radius: 10;");
        unitLabel.setLayoutX(565);
        unitLabel.setLayoutY(162);

        descriptionLabel = new Label("description");
        descriptionLabel.setAlignment(javafx.geometry.Pos.CENTER);
        descriptionLabel.setPrefSize(97, 31);
        descriptionLabel.setStyle("-fx-background-color: #8fc6D0; -fx-background-radius: 10;");
        descriptionLabel.setLayoutX(662);
        descriptionLabel.setLayoutY(162);

        addText = new Button("+");
        addText.setPrefSize(20, 20);
        addText.setLayoutX(340);
        addText.setLayoutY(200);

        deleteText = new Button("-");
        deleteText.setPrefSize(25, 25);
        deleteText.setLayoutX(340);
        deleteText.setLayoutY(230);

        scrollBox = new VBox();
        scrollPane = new ScrollPane(scrollBox);
        scrollPane.setPrefSize(389, 260);
        scrollPane.setLayoutX(371);
        scrollPane.setLayoutY(200);
    }


    private void setUploadPictureButton() {
        uploadButton = new Button("upload a picture");
        uploadButton.setLayoutX(64.0);
        uploadButton.setLayoutY(412.0);
        uploadButton.setPrefHeight(42.0);
        uploadButton.setPrefWidth(175.0);
        DropShadow buttonDropShadow = new DropShadow();
        uploadButton.setEffect(buttonDropShadow);
        Font buttonFont = new Font(18.0);
        uploadButton.setFont(buttonFont);
    }

}
