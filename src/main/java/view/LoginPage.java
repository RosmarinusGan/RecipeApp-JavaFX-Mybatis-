package view;

import control.LoginPageController;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Model;

public class LoginPage extends Stage{

    private final LoginPageController loginPageController;
    private final Model model;


    public LoginPage(){
        model = Model.getInstance();
        loginPageController = new LoginPageController(this, model);
        init();
        addListener();
    }

    private void init(){
        AnchorPane root = new AnchorPane();
        root.setPrefSize(773, 507);

        Font labelFont = new Font(18.0);
        setupBackgroundImage();

        AnchorPane innerAnchorPane = new AnchorPane();
        setupInnerPane(innerAnchorPane);

        Label welcomeLabel = setupLoginLabel();
        Label usernameLabel = setupUserNameField(labelFont);
        Label passwordLabel = setupPasswordField(labelFont);
        setLoginButton();
        setSignupButton();

        innerAnchorPane.getChildren().addAll(
                welcomeLabel,
                usernameLabel,
                passwordLabel,
                passwordField,
                usernameField,
                loginButton,
                signupButton
        );

        root.getChildren().addAll(imageView, innerAnchorPane);

        Scene scene = new Scene(root);
        usernameField.requestFocus();
        this.setScene(scene);
        this.setTitle("LoginPage");
        this.setResizable(false);
    }

    private void setSignupButton() {
        signupButton = new Button("sign up");
        signupButton.setLayoutX(141.0);
        signupButton.setLayoutY(295.0);
    }

    private void setLoginButton() {
        loginButton = new Button("login");
        loginButton.setLayoutX(292.0);
        loginButton.setLayoutY(295.0);
    }

    private Label setupUserNameField(Font labelFont) {
        Label usernameLabel = new Label("UserName/\nUserId:");
        usernameLabel.setLayoutX(32.0);
        usernameLabel.setLayoutY(161);
        usernameLabel.setFont(labelFont);

        usernameField = new TextField();
        usernameField.setLayoutX(147.0);
        usernameField.setLayoutY(178.0);
        usernameField.setTextFormatter(model.textFieldFormatter(18, false, false));
        return usernameLabel;
    }

    private Label setupPasswordField(Font labelFont) {
        Label passwordLabel = new Label("Password:");
        passwordLabel.setLayoutX(36.0);
        passwordLabel.setLayoutY(248.0);
        passwordLabel.setFont(labelFont);

        passwordField = new PasswordField();
        passwordField.setLayoutX(147.0);
        passwordField.setLayoutY(245.0);
        passwordField.setTextFormatter(model.textFieldFormatter(18, false, false));
        return passwordLabel;
    }

    private static Label setupLoginLabel() {
        Label welcomeLabel = new Label("Welcome to E-Recipe!");
        welcomeLabel.setLayoutX(14.0);
        welcomeLabel.setLayoutY(81.0);
        Font welcomeFont = new Font(36.0);
        welcomeLabel.setFont(welcomeFont);
        return welcomeLabel;
    }

    private static void setupInnerPane(AnchorPane innerAnchorPane) {
        innerAnchorPane.setLayoutX(199.0);
        innerAnchorPane.setLayoutY(46.0);
        innerAnchorPane.setPrefHeight(416.0);
        innerAnchorPane.setPrefWidth(397.0);
        innerAnchorPane.setStyle("-fx-background-color: WHITE; -fx-border-radius: 10; -fx-background-radius: 10;");

        dropShadow = new DropShadow();
        dropShadow.setHeight(45.0);
        dropShadow.setRadius(22.0);
        dropShadow.setWidth(45.0);
        innerAnchorPane.setEffect(dropShadow);
    }

    private void setupBackgroundImage() {
        imageView = new ImageView(new Image("file:background/background.jpg"));
        imageView.setFitHeight(600);
        imageView.setFitWidth(850);
        imageView.setLayoutX(-34.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
    }

    private void addListener(){
        loginButton.setOnAction(loginPageController);
        signupButton.setOnAction(loginPageController);
    }

    public Button loginButton;
    public Button signupButton;
    public TextField usernameField;
    public PasswordField passwordField;
    public ImageView imageView;
    public static DropShadow dropShadow;
}