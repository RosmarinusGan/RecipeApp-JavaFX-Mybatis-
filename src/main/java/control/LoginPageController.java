package control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Model;
import view.LoginPage;
import view.SignupPage;

public class LoginPageController implements EventHandler<ActionEvent> {
    private final LoginPage loginPage;
    private final Model model;
    public LoginPageController(LoginPage _loginPage, Model _model){
        loginPage = _loginPage;
        model = _model;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        if(actionEvent.getSource() == loginPage.signupButton){
            SignupUser();
            return;
        }
        if(actionEvent.getSource() == loginPage.loginButton){
            LoginUser();
        }
    }

    private void SignupUser(){
        SignupPage temp = new SignupPage();
        temp.setOnCloseRequest(e -> loginPage.show());
        model.loadSignupPage(temp);
        loginPage.hide();
    }

    private void LoginUser(){
        String userAccount = loginPage.usernameField.getText();
        String password = loginPage.passwordField.getText();
        boolean temp;
        if(userAccount.matches("-?\\d+")){
            //这里必须要限制纯数字的位数，因为Integer.parseInt最多支持10位string转int
            if(userAccount.length() > 10){
                userAccount = userAccount.substring(0, 10);
                System.out.println(userAccount);
            }
            temp = model.accountJudge(Integer.parseInt(userAccount), password);
        }else{
            temp = model.accountJudge(userAccount, password);
        }
        if(temp){
            loginPage.close();
        }
    }
}
