package view;

import control.SearchResultPageController;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.CATEGORY;
import model.Model;

public class SearchResultPage extends Stage{
    public TextField searchField;
    public Button searchButton;
    public ScrollPane resultPane;
    public VBox box;
    public ToggleGroup toggleGroup;
    public GridPane gridPane;
    private HBox resultLabelBox;
    private AnchorPane innerAnchorPane;
    private ImageView imageView; //背景图片
    private final Model model = Model.getInstance();
    private final SearchResultPageController controller = new SearchResultPageController(this, model);

    public SearchResultPage() {
        StackPane tempPane = new StackPane();
        box = new VBox();
        Text tempText = new Text("Not Found Recipe");
        tempText.setStyle("-fx-font-family: Forte; -fx-font-size: 25px; -fx-alignment: Center;");
        tempPane.getChildren().addAll(tempText, box);
        resultPane = new ScrollPane(tempPane);
        box.getChildren().addListener((ListChangeListener<? super Node>) change -> {
            tempText.setOpacity(box.getChildren().isEmpty() ? 1 : 0);
        });


        AnchorPane root = new AnchorPane();
        root.setPrefSize(773, 507);

        setBackgroundImage();
        setInnerPane();
        setSearchField();
        setSearchButton();
        setResultPane();
        setCategoryButton();

        innerAnchorPane.getChildren().addAll(
                searchField,
                searchButton,
                resultPane,
                resultLabelBox,
                gridPane
        );

        root.getChildren().addAll(imageView, innerAnchorPane);
        addListener();

        Scene scene = new Scene(root);
        this.setScene(scene);
        this.setTitle("Search For Recipe");
        this.setResizable(false);
    }

    private void addListener(){
        searchButton.setOnAction(controller);
        toggleGroup.selectedToggleProperty().addListener(e -> controller.categorySearch());
    }

    private void setCategoryButton(){
        toggleGroup = new ToggleGroup();
        gridPane = new GridPane();
        gridPane.setVgap(5);
        gridPane.setHgap(10);
        gridPane.setLayoutX(72);
        gridPane.setLayoutY(100);

        for(int i = 0; i < CATEGORY.values().length; i++){
            RadioButton tempButton = new RadioButton(CATEGORY.values()[i].getName());
            tempButton.setToggleGroup(toggleGroup);
            gridPane.add(tempButton, i % 3, i / 3);
        }

        RadioButton allButton = new RadioButton("All");
        allButton.setToggleGroup(toggleGroup);
        gridPane.add(allButton, 3, 0);
    }

    private void setSearchField() {
        searchField = new TextField();
        searchField.setLayoutX(72.0);
        searchField.setLayoutY(39.0);
        searchField.setPrefHeight(43.0);
        searchField.setPrefWidth(431.0);
        DropShadow textFieldDropShadow = new DropShadow();
        searchField.setEffect(textFieldDropShadow);
        Font textFieldFont = new Font(17.0);
        searchField.setFont(textFieldFont);
        searchField.setTextFormatter(model.textFieldFormatter(50, false, false));
    }

    private void setSearchButton() {
        searchButton = new Button("Search");
        searchButton.setLayoutX(521.0);
        searchButton.setLayoutY(38.0);
        searchButton.setPrefHeight(43.0);
        searchButton.setPrefWidth(113.0);
        searchButton.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 20px");
    }

    private void setResultPane(){
        resultPane.setLayoutX(47.0);
        resultPane.setLayoutY(160);
        resultPane.setPrefHeight(250);
        resultPane.setPrefWidth(616.0);

        resultLabelBox = new HBox();
        resultLabelBox.setSpacing(50);
        resultLabelBox.setLayoutX(47);
        resultLabelBox.setLayoutY(140);
        Label id = new Label("Recipe Id");
        id.setPrefSize(80, 20);
        Label name = new Label("Recipe Name");
        name.setPrefSize(200, 20);
        Label number = new Label("Serve Number");
        number.setPrefSize(90, 20);
        Label category = new Label("Category");
        category.setPrefSize(80, 20);
        resultLabelBox.getChildren().addAll(id, name, number, category);
    }

    private void setInnerPane() {
        innerAnchorPane = new AnchorPane();
        innerAnchorPane.setLayoutX(58.0);
        innerAnchorPane.setLayoutY(42.0);
        innerAnchorPane.setPrefHeight(428.0);
        innerAnchorPane.setPrefWidth(692.0);
        innerAnchorPane.setStyle("-fx-background-color: WHITE; -fx-background-radius: 10;");
        DropShadow dropShadow = new DropShadow();
        innerAnchorPane.setEffect(dropShadow);
    }

    private void setBackgroundImage() {
        imageView = new ImageView();
        imageView.setFitHeight(536.0);
        imageView.setFitWidth(794.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        Image image = new Image("file:background/background.jpg");
        imageView.setImage(image);
    }

}

