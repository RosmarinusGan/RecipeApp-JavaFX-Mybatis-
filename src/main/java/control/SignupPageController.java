package control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.stage.WindowEvent;
import model.Model;
import view.SignupPage;

public class SignupPageController implements EventHandler<ActionEvent> {
    public SignupPageController(SignupPage _signupPage, Model _model){
        signupPage = _signupPage;
        model = _model;
    }

    private final SignupPage signupPage;
    private final Model model;

    @Override
    public void handle(ActionEvent actionEvent) {
        saveUser();
    }

    private void saveUser(){
        String userName = signupPage.newUserNameTextField.getText();
        String password = signupPage.newPasswordTextField.getText();
        int temp = model.addUser(userName, password);
        if(temp != -1 && temp != -2){
            Alert tempAlert = model.alertDisplay(Alert.AlertType.INFORMATION, "Successful Enrollment!\n" + "please remember your id " + temp);
            tempAlert.setOnCloseRequest(e -> signupPage.fireEvent(new WindowEvent(signupPage, WindowEvent.WINDOW_CLOSE_REQUEST)));
            return;
        }
        if(temp == -1){
            signupPage.newUserNameTextField.clear();
            signupPage.newPasswordTextField.clear();
        }
    }
}
