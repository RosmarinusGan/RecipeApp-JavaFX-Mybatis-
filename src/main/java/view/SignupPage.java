package view;

import control.SignupPageController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Model;

public class SignupPage extends Stage{

    private final SignupPageController loginPageController;
    private final Model model;

    public SignupPage() {
        model = Model.getInstance();
        loginPageController = new SignupPageController(this, model);
        init();
        addListener();
    }

    private void init(){
        AnchorPane root = new AnchorPane();
        root.setPrefSize(773, 507);
        Font labelFont = new Font(18.0);

        setupImageBackground();
        AnchorPane innerAnchorPane = setupInnerPane();
        Label titleLabel = setupTitleLabel();
        Label usernameLabel = setupNewUserNameField(labelFont);
        Label passwordLabel = setupNewPasswordField(labelFont);
        setupSaveButton(labelFont);

        innerAnchorPane.getChildren().addAll(
                titleLabel,
                usernameLabel,
                passwordLabel,
                newUserNameTextField,
                newPasswordTextField,
                saveAccountButton
        );

        root.getChildren().addAll(imageView, innerAnchorPane);

        Scene scene = new Scene(root);
        this.setScene(scene);
        this.setTitle("SignupPage");
        this.setResizable(false);
    }

    private void setupSaveButton(Font labelFont) {
        saveAccountButton = new Button("save");
        saveAccountButton.setLayoutX(234.0);
        saveAccountButton.setLayoutY(269.0);
        saveAccountButton.setFont(labelFont);
    }

    private Label setupNewPasswordField(Font labelFont) {
        Label passwordLabel = new Label("Password:");
        passwordLabel.setLayoutX(43.0);
        passwordLabel.setLayoutY(216.0);
        passwordLabel.setFont(labelFont);

        newPasswordTextField = new TextField();
        newPasswordTextField.setLayoutX(169.0);
        newPasswordTextField.setLayoutY(210.0);
        newPasswordTextField.setFont(labelFont);
        newPasswordTextField.setTextFormatter(model.textFieldFormatter(18, false, false));
        return passwordLabel;
    }

    private Label setupNewUserNameField(Font labelFont) {
        Label usernameLabel = new Label("UserName:");
        usernameLabel.setLayoutX(39.0);
        usernameLabel.setLayoutY(149.0);
        usernameLabel.setFont(labelFont);
        newUserNameTextField = new TextField();
        newUserNameTextField.setLayoutX(169.0);
        newUserNameTextField.setLayoutY(143.0);
        newUserNameTextField.setFont(labelFont);
        newUserNameTextField.setTextFormatter(model.textFieldFormatter(18, false, false));
        return usernameLabel;
    }

    private static Label setupTitleLabel() {
        Label titleLabel = new Label("Create a new Account!");
        titleLabel.setLayoutX(17.0);
        titleLabel.setLayoutY(60.0);
        Font titleFont = new Font("Arial Black", 36.0);
        titleLabel.setFont(titleFont);
        return titleLabel;
    }

    private static AnchorPane setupInnerPane() {
        AnchorPane innerAnchorPane = new AnchorPane();
        innerAnchorPane.setLayoutX(176.0);
        innerAnchorPane.setLayoutY(81.0);
        innerAnchorPane.setPrefHeight(350.0);
        innerAnchorPane.setPrefWidth(485.0);
        innerAnchorPane.setStyle("-fx-background-color: White; -fx-background-radius: 10;");
        return innerAnchorPane;
    }

    private void setupImageBackground() {
        imageView = new ImageView(new Image("file:background/background.jpg"));
        imageView.setFitHeight(600);
        imageView.setFitWidth(850);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
    }

    private void addListener(){
        saveAccountButton.setOnAction(loginPageController);
    }

    public ImageView imageView;
    public TextField newUserNameTextField, newPasswordTextField;
    public Button saveAccountButton;
}

