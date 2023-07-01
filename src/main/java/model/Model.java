package model;

import control.EditPageController;
import dao.dataAccess.DataAccess;
import dao.mappers.IngredientPOJO;
import dao.mappers.RecipePOJO;
import dao.mappers.UserPOJO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import view.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public final class Model implements ModelMethod {
    //Singleton
    private Model(){
        init();
    }
    private static class SingletonInstance{
        private static final Model singleton = new Model();
    }
    public static Model getInstance(){
        return SingletonInstance.singleton;
    }

    //加载一个RecipePage
    @Override
    public void loadRecipePage(RecipePage page, int recipeId) {
        currentOperateRecipe = dataAccess.getRecipe_Id(recipeId).get(0);
        page.recipeNameTextField.setText(currentOperateRecipe.getRecipeName());
        page.cookingTimeTextField.setText(currentOperateRecipe.getCookTime());
        page.prepareTimeTextField.setText(currentOperateRecipe.getPrepareTime());
        page.serveNumberTextField.setText(String.valueOf(currentOperateRecipe.getRecommendServeNumber()));
        page.InstructionArea.setText(currentOperateRecipe.getInstruction());
        page.categoryDisplayField.setText(currentOperateRecipe.getRecipeCategory());
        page.recipePicture.setImage(new Image("file:" + defaultImagePath + "_" + currentOperateRecipe.getImagePath()));
        page.editRecipeButton.setVisible(currentOperateRecipe.getOwnerId() == currentUser.getUserId()); //设置edit按钮对非owner不可见
        page.deleteRecipeButton.setVisible(currentOperateRecipe.getOwnerId() == currentUser.getUserId()); //设置delete按钮对非owner不可见

        ObservableList<IngredientPOJO> data = FXCollections.observableArrayList();
        if(currentOperateRecipe.getIngredients() == null){
            page.show();
            return;
        }

        //深拷贝 把ingredient表值复制
        List<IngredientPOJO> tempList = new ArrayList<>();
        for(IngredientPOJO e : currentOperateRecipe.getIngredients()){
            IngredientPOJO tempPojo = new IngredientPOJO();
            tempPojo.setIngredientName(e.getIngredientName());
            tempPojo.setQuantity(e.getQuantity());
            tempPojo.setUnit(e.getUnit());
            tempPojo.setDescription(e.getDescription());
            tempList.add(tempPojo);
        }

        page.tableView.setItems(null);
        data.addAll(tempList);
        page.tableView.setItems(data);
        page.show();
    }

    //加载一个Edit属性的CreateRecipePage
    @Override
    public void loadEditPage(RecipePage recipePage, CreateRecipePage createRecipePage) {
        //改变监听事件
        createRecipePage.saveButton.setOnAction(new EditPageController(createRecipePage, this));
        createRecipePage.uploadButton.setOnAction(new EditPageController(createRecipePage, this));
        /*recipePage.setOpacity(0);//似乎不能设置其close（）或者hide（）
        createRecipePage.show();
        RecipePage tempPage = new RecipePage();
        tempPage.setOnCloseRequest(e -> recipePage.fireEvent(new WindowEvent(recipePage, WindowEvent.WINDOW_CLOSE_REQUEST)));

         */
        createRecipePage.show();
        createRecipePage.setOnCloseRequest(e -> loadRecipePage(recipePage, currentOperateRecipe.getRecipeId()));

        //从RecipePage获取数据
        createRecipePage.recipeNameField.setText(currentOperateRecipe.getRecipeName());
        createRecipePage.prepareTimeTextField.setText(currentOperateRecipe.getPrepareTime());
        createRecipePage.cookingTimeTextField.setText(currentOperateRecipe.getCookTime());
        createRecipePage.instructionTextField.setText(currentOperateRecipe.getInstruction());
        createRecipePage.serveNumberTextField.setText(String.valueOf(currentOperateRecipe.getRecommendServeNumber()));
        currentImagePath = currentOperateRecipe.getImagePath();

        for(Toggle toggle : createRecipePage.toggleGroup.getToggles()){
            RadioButton tempButton = (RadioButton) toggle;
            if(tempButton.getText().equals(currentOperateRecipe.getRecipeCategory())){
                tempButton.setSelected(true);
            }
        }
        createRecipePage.updateImage(currentOperateRecipe.getImagePath());

        //获取ingredients
        for(IngredientPOJO e : currentOperateRecipe.getIngredients()){
            HBox temp = new HBox();

            TextField ingredientName = new TextField();
            ingredientName.setPrefSize(97, 30);
            ingredientName.setTextFormatter(textFieldFormatter(20, false, false));
            ingredientName.setText(e.getIngredientName());

            TextField quantity = new TextField();
            quantity.setPrefSize(97, 30);
            quantity.setText(String.valueOf(e.getQuantity()));
            quantity.setTextFormatter(textFieldFormatter(20, false, true));

            TextField unit = new TextField();
            unit.setPrefSize(97, 30);
            unit.setTextFormatter(textFieldFormatter(20, false, false));
            unit.setText(e.getUnit());

            TextField description = new TextField();
            description.setPrefSize(97, 30);
            description.setTextFormatter(textFieldFormatter(40, false, false));
            description.setText(e.getDescription());

            temp.getChildren().addAll(ingredientName, quantity, unit, description);
            createRecipePage.scrollBox.getChildren().add(temp);
        }
    }

    //更新RecipePage中的Ingredient中的quantity
    @Override
    public void updateIngredient(TableView<IngredientPOJO> tableView, int number){
        for(IngredientPOJO e : tableView.getItems()){
            float temp = e.getQuantity() * ((float)number / currentOperateRecipe.getRecommendServeNumber()); //不能向下取整
            temp = Float.parseFloat(String.format("%.5f", temp));
            e.setQuantity(temp);
        }

        for(int i = 0; i < tableView.getItems().size(); i++){
            float temp = currentOperateRecipe.getIngredients().get(i).getQuantity() *  ((float)number / currentOperateRecipe.getRecommendServeNumber());
            temp = Float.parseFloat(String.format("%.5f", temp));
            tableView.getItems().get(i).setQuantity(temp);
        }
    }

    @Override
    public void loadSignupPage(SignupPage page) {
        page.show();
    }

    //加载一个MainPage
    @Override
    public void loadMainPage(MainPage page) {
        page.mainPageLabel.setText("Dear " + currentUser.getUserName() + ",\n Welcome to Your Recipe Page !");
        List<RecipePOJO> list = dataAccess.getRecipe_Owner(currentUser.getUserId());
        page.totalPageNumber.setText(String.valueOf((list.size() / 4) + 1));
        page.pageNumber.setText(String.valueOf(1));
        updateMainPage(page.userRecipesPane, 1);
        page.show();
    }

    //加载MainPage中的Owner的Recipe
    @Override
    public void updateMainPage(GridPane gridPane, int currentPage){
        List<RecipePOJO> list = dataAccess.getRecipe_Owner(currentUser.getUserId());
        gridPane.getChildren().clear();
        for(int i = (currentPage - 1) * 4; i <= 4 * currentPage - 1; i++){
            if(i >= list.size()){
                return;
            }
            VBox box = new VBox();
            ImageView imageView = new ImageView(imageRetrieve(list.get(i).getRecipeId()));
            imageView.setFitHeight(150);
            imageView.setFitWidth(150);
            Label recipeId = new Label(String.valueOf(list.get(i).getRecipeId()));
            recipeId.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 16px");
            recipeId.setPrefSize(60, 30);
            Label recipeName = new Label(list.get(i).getRecipeName());
            recipeName.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 16px");
            recipeName.setPrefSize(60, 30);
            box.getChildren().addAll(imageView, recipeId, recipeName);
            box.setOnMouseEntered(e -> box.setStyle("-fx-background-color: rgba(238,255,0,0.62)"));
            box.setOnMouseExited(e -> box.setStyle("-fx-background-color: rgba(255,255,255,0)"));
            box.setOnMouseClicked(e -> {
                RecipePage temp = new RecipePage();
                MainPage stage = (MainPage) gridPane.getScene().getWindow();
                stage.close();
                temp.setOnCloseRequest(event -> loadMainPage(stage));
                this.loadRecipePage(temp, Integer.parseInt(recipeId.getText()));
            });
            gridPane.add(box, i % 4, 0);
        }
    }

    //加载SearchResultPage
    @Override
    public void loadSearchResultPage(SearchResultPage page) {
        List<RecipePOJO> tempList = dataAccess.getRecipe_All();
        updateResults(page.box, tempList);
        page.show();
    }

    //SearchResultPage中的搜索逻辑
    @Override
    public void displaySearchResult(SearchResultPage page, String name){
        List<RecipePOJO> recipeList;
        RadioButton tempButton = (RadioButton) page.toggleGroup.getSelectedToggle();
        if(tempButton != null){
            if(name != null){
                if(tempButton.getText().equals("All")) {
                    recipeList = dataAccess.getRecipe_Name(name);
                }else{
                    recipeList = dataAccess.getRecipe_CateAndName(tempButton.getText(), name);
                }
            }else{
                displayCategoryResult(page);
                return;
            }
        }else {
            if(name != null){
                recipeList = dataAccess.getRecipe_Name(name);
            }else {
                recipeList = dataAccess.getRecipe_All();
            }
        }
        updateResults(page.box, recipeList);
    }

    //根据种类的搜索结果
    @Override
    public void displayCategoryResult(SearchResultPage page){
        List<RecipePOJO> recipeList;
        RadioButton tempButton = (RadioButton) page.toggleGroup.getSelectedToggle();
        if(tempButton == null){
            return;
        }
        String temp = tempButton.getText();
        if(temp.equals("All")){
            recipeList = dataAccess.getRecipe_All();
        }else{
            recipeList = dataAccess.getRecipe_Category(temp);
        }
        updateResults(page.box, recipeList);
    }

    //将结果更新到SearchResultPage
    private void updateResults(VBox box, List<RecipePOJO> recipeList){
        box.getChildren().clear();
        for (RecipePOJO recipePOJO : recipeList) {
            HBox tempBox = new HBox();
            tempBox.setSpacing(70);
            Label recipeId = new Label(String.valueOf(recipePOJO.getRecipeId()));
            recipeId.setPrefSize(80, 40);
            Label recipeName = new Label(String.valueOf(recipePOJO.getRecipeName()));
            recipeName.setPrefSize(80, 40);
            Label serveNumber = new Label(String.valueOf(recipePOJO.getRecommendServeNumber()));
            serveNumber.setPrefSize(80, 40);
            Label recipeCategory = new Label(String.valueOf(recipePOJO.getRecipeCategory()));
            recipeCategory.setPrefSize(80, 40);
            tempBox.getChildren().addAll(recipeId, recipeName, serveNumber, recipeCategory);
            tempBox.setOnMouseEntered(e -> tempBox.setStyle("-fx-background-color: rgba(0,208,255,0.5)"));
            tempBox.setOnMouseExited(e -> tempBox.setStyle("-fx-background-color: rgba(255,255,255,0)"));
            tempBox.setOnMouseClicked(e -> {
                RecipePage temp = new RecipePage();
                SearchResultPage stage = (SearchResultPage) box.getScene().getWindow();
                stage.close();
                temp.setOnCloseRequest(event -> loadSearchResultPage(stage));
                this.loadRecipePage(temp, Integer.parseInt(recipeId.getText()));
            });
            box.getChildren().add(tempBox);
        }
    }

    @Override
    public void loadCreateRecipePage(CreateRecipePage page) {
        page.show();
    }

    //将CreateRecipePage中的数据保存进数据库，当按下SaveButton时候
    @Override
    public void uploadRecipe(CreateRecipePage page, boolean isUpdate){
        String recipeName = page.recipeNameField.getText();
        RadioButton temp = (RadioButton) page.toggleGroup.getSelectedToggle();
        String prepareTime = page.prepareTimeTextField.getText();
        String cookTime = page.cookingTimeTextField.getText();
        int ownerId = currentUser.getUserId();
        String instruction = page.instructionTextField.getText();
        String recipeCategory;
        int serveNumber;
        int addRecipeId;
        List<IngredientPOJO> tempList = new ArrayList<>();

        if(recipeName.isEmpty()){
            alertDisplay(Alert.AlertType.WARNING, "Recipe Name can't be empty !");
            page.recipeNameField.requestFocus();
            return;
        }
        if(temp == null){
            alertDisplay(Alert.AlertType.WARNING, "You need to Choose a Category");
            return;
        }else{
            recipeCategory = temp.getText();
        }
        if(prepareTime.isEmpty()){
            alertDisplay(Alert.AlertType.WARNING, "Prepare Time can't be empty !");
            page.prepareTimeTextField.requestFocus();
            return;
        }
        if(cookTime.isEmpty()){
            alertDisplay(Alert.AlertType.WARNING, "Cook Time can't be empty !");
            page.cookingTimeTextField.requestFocus();
            return;
        }
        if(page.serveNumberTextField.getText().isEmpty()){
            alertDisplay(Alert.AlertType.WARNING, "Serve Number can't be empty !");
            page.serveNumberTextField.requestFocus();
            return;
        }else{
            serveNumber = Integer.parseInt(page.serveNumberTextField.getText());
        }
        if(instruction.isEmpty()){
            Optional<ButtonType> result = confirmAlertDisplay("Your Instruction is Empty, Are you sure ?");
            if(result.isPresent()){
                ButtonType buttonType = result.get();
                if(buttonType == ButtonType.CANCEL){
                    page.tabPane.getSelectionModel().select(1);
                    return;
                }
            }
        }
        if(page.scrollBox.getChildren().size() == 0){
            alertDisplay(Alert.AlertType.WARNING, "Ingredients haven't be added!");
            page.addText.requestFocus();
            return;
        }

        for(int i = 0; i < page.scrollBox.getChildren().size(); i++){
            HBox tempBox = (HBox) page.scrollBox.getChildren().get(i);
            TextField name = (TextField) tempBox.getChildren().get(0);
            TextField quantity = (TextField) tempBox.getChildren().get(1);
            TextField unit = (TextField) tempBox.getChildren().get(2);
            TextField description = (TextField) tempBox.getChildren().get(3);

            String inName = name.getText();
            String inQuan = quantity.getText();
            String inUnit = unit.getText();
            String inDes = description.getText();

            if(inName.isEmpty()){
                alertDisplay(Alert.AlertType.WARNING, "ingredient name can't be empty!");
                name.requestFocus();
                return;
            }else {
                if (inName.matches("-?\\d+")) {
                    alertDisplay(Alert.AlertType.WARNING, "ingredient name can't be pure number!");
                    name.clear();
                    name.requestFocus();
                    return;
                }
            }
            if(inQuan.isEmpty()){
                alertDisplay(Alert.AlertType.WARNING, "quantity can't be empty!");
                quantity.requestFocus();
                return;
            }
            if(inUnit.isEmpty()){
                alertDisplay(Alert.AlertType.WARNING, "unit can't be empty!");
                unit.requestFocus();
                return;
            }else{
                if(inUnit.matches("-?\\d+")){
                    Optional<ButtonType> result = confirmAlertDisplay("Your Unit is Number, Are you Sure ?");
                    if(result.isPresent()){
                        ButtonType buttonType = result.get();
                        if(buttonType == ButtonType.CANCEL){
                            unit.requestFocus();
                            return;
                        }
                    }
                }
            }
            if(inDes.isEmpty()){
                Optional<ButtonType> result = confirmAlertDisplay("Your Description is Empty, Are you Sure?");
                if(result.isPresent()){
                    ButtonType buttonType = result.get();
                    if(buttonType == ButtonType.CANCEL){
                        description.requestFocus();
                        return;
                    }
                }
            }
            IngredientPOJO tempPojo = new IngredientPOJO();
            tempPojo.setIngredientName(inName);
            tempPojo.setQuantity(Float.parseFloat(inQuan));
            tempPojo.setUnit(inUnit);
            tempPojo.setDescription(inDes);
            tempList.add(tempPojo);
        }
        if(currentImagePath == null){
            alertDisplay(Alert.AlertType.WARNING, "you haven't add your recipe image!");
            return;
        }

        if(!isUpdate) {
            addRecipeId = addRecipe(recipeName, recipeCategory, prepareTime, cookTime, serveNumber, ownerId, instruction, currentImagePath);
            tempList.forEach(e -> addIngredient(addRecipeId, e.getIngredientName(), e.getQuantity(), e.getUnit(), e.getDescription()));
            alertDisplay(Alert.AlertType.INFORMATION, "Successfully Add Recipe !");

            //清空
            page.recipeNameField.clear();
            page.toggleGroup.getSelectedToggle().setSelected(false);
            page.cookingTimeTextField.clear();
            page.prepareTimeTextField.clear();
            page.scrollBox.getChildren().clear();
            page.serveNumberTextField.clear();
            page.instructionTextField.clear();
            //清除图片
            page.recipePicture.setImage(null);
            currentImagePath = null;
        }
        else{
            updateRecipe(recipeName, recipeCategory, prepareTime, cookTime, serveNumber, instruction, currentImagePath);
            tempList.forEach(e -> addIngredient(currentOperateRecipe.getRecipeId(), e.getIngredientName(), e.getQuantity(), e.getUnit(), e.getDescription()));
            Alert tempAlert = alertDisplay(Alert.AlertType.INFORMATION, "Successfully Update Recipe !");
            tempAlert.setOnCloseRequest(e -> page.fireEvent(new WindowEvent(page, WindowEvent.WINDOW_CLOSE_REQUEST)));
        }
    }

    //图片选择与保存
    @Override
    public String imageChoose(Stage stage){
        FileChooser fileChooser = new FileChooser();
        stage.setOpacity(0);
        Window tempWindow = new Stage();
        File temp = fileChooser.showOpenDialog(tempWindow);
        stage.setOpacity(1); //不能hide和show， 否则会报线程错误
        if(imageStore(temp)){
            currentImagePath = temp.getName();
            return temp.getName();
        }
        return null;
    }

    private boolean imageStore(File file){
        if(file!= null) {
            String tempString = defaultImagePath + "_" + file.getName();
            File newFile = new File(tempString);
            if(!newFile.exists()) {
                try {
                    Files.copy(file.toPath(), newFile.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Image Add Error!");
                    return false;
                }
                System.out.println("Image Add!");
            }
            return true;
        }
        return false;
    }

    //图片获取
    @Override
    public Image imageRetrieve(int recipeId){
        RecipePOJO temp = dataAccess.getRecipe_Id(recipeId).get(0);
        String imagePath = temp.getImagePath();
        return new Image("file:" + defaultImagePath + "_" + imagePath);
    }

    //图片删除
    private void imageDelete(){
        try {
            Files.deleteIfExists(Path.of(defaultImagePath + currentOperateRecipe.getRecipeId() + "_" + currentOperateRecipe.getImagePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //添加一个RecipePOJO对象进入数据库
    private int addRecipe(String recipeName, String recipeCategory, String prepareTime, String cookTime, int recommendServeNumber, int ownerId, String instruction, String imagePath) {
        RecipePOJO tempRecipe = new RecipePOJO();
        tempRecipe.setRecipeId(recipeIdGenerator());
        tempRecipe.setRecipeName(recipeName);
        tempRecipe.setRecipeCategory(recipeCategory);
        tempRecipe.setPrepareTime(prepareTime);
        tempRecipe.setCookTime(cookTime);
        tempRecipe.setRecommendServeNumber(recommendServeNumber);
        tempRecipe.setOwnerId(ownerId);
        tempRecipe.setInstruction(instruction);
        tempRecipe.setImagePath(imagePath);
        dataAccess.insertRecipe(tempRecipe);
        return tempRecipe.getRecipeId();
    }

    //添加一个IngredientPojo对象进入数据库
    private int addIngredient(int recipeId, String ingredientName, float quantity, String unit, String description) {
        IngredientPOJO tempIngredient = new IngredientPOJO();
        tempIngredient.setIngredientName(ingredientName);
        tempIngredient.setIngredientId(ingredientIdGenerator(recipeId));
        tempIngredient.setRecipeId(recipeId);
        tempIngredient.setQuantity(quantity);
        tempIngredient.setUnit(unit);
        tempIngredient.setDescription(description);
        dataAccess.insertIngredient(tempIngredient);
        return tempIngredient.getIngredientId();
    }

    //添加一个UserPOJO对象进入数据库
    @Override
    public int addUser(String userName, String password){
        if(userName.isEmpty()){
            alertDisplay(Alert.AlertType.WARNING, "user name can't be null");
            return -2;
        }
        if(password.isEmpty()){
            alertDisplay(Alert.AlertType.WARNING, "password can't be null");
            return -2;
        }
        if(userName.matches("-?\\d+")){
            alertDisplay(Alert.AlertType.WARNING, "user name can't be pure integer");
            return -2;
        }
        if(judgeRepeatUserName(userName)) {
            UserPOJO tempUser = new UserPOJO();
            tempUser.setUserId(userIdGenerator());
            tempUser.setUserName(userName);
            tempUser.setPassword(password);
            dataAccess.insertUser(tempUser);
            return tempUser.getUserId();
        }else{
            alertDisplay(Alert.AlertType.WARNING,"Already Have The Same Name");
            return -1;
        }
    }

    //判断是否有重复的User名字
    private boolean judgeRepeatUserName(String userName){
        List<UserPOJO> tempList = dataAccess.getUser(userName);
        return tempList.size() == 0;
    }

    //删除一个Recipe
    @Override
    public boolean deleteRecipe() {
        imageDelete();
        return dataAccess.deleteRecipe(currentOperateRecipe);
    }

    //更新一个Recipe
    @Override
    public boolean updateRecipe(String recipeName, String recipeCategory, String prepareTime, String cookTime, int recommendServeNumber, String instruction, String imagePath) {
        RecipePOJO tempPojo = new RecipePOJO();
        tempPojo.setRecipeId(currentOperateRecipe.getRecipeId());
        tempPojo.setRecipeName(recipeName);
        tempPojo.setRecipeName(recipeName);
        tempPojo.setRecipeCategory(recipeCategory);
        tempPojo.setPrepareTime(prepareTime);
        tempPojo.setCookTime(cookTime);
        tempPojo.setRecommendServeNumber(recommendServeNumber);
        tempPojo.setOwnerId(currentOperateRecipe.getOwnerId());
        tempPojo.setInstruction(instruction);
        tempPojo.setImagePath(imagePath);
        deleteRecipe(); //删除当前recipe
        return dataAccess.insertRecipe(tempPojo);
    }

    //删除一个User
    @Override
    public boolean deleteUser() {
        return dataAccess.deleteUser(currentUser);
    }

    //更新一个User
    @Override
    public boolean updateUser(String userName) {
        List<UserPOJO> tempList = dataAccess.getUser(userName);
        return dataAccess.updateUser(tempList.get(0));
    }

    @Override
    public boolean accountJudge(String userName, String userPassword) {
        return accountJudge(userPassword, dataAccess.getUser(userName));
    }

    @Override
    public boolean accountJudge(int userId, String userPassword) {
        return accountJudge(userPassword, dataAccess.getUser(userId));
    }

    @Override
    public TextFormatter<String> textFieldFormatter(int maxLength, boolean onlyInteger, boolean onlyFloatAndInteger) {
        TextFormatter<String> temp;
        temp = new TextFormatter<>(change -> {
            //change.getText();获取当前输入的一个字符
            //change.getControlText();获取当前组件中已有的字符串（不包括当前输入的）
            if(change.isAdded()){
                if(change.getControlText().length() > maxLength) {
                    change.setText("");
                }
                if(onlyInteger && !change.getText().matches("\\d+")){
                    change.setText("");
                }
                if(onlyFloatAndInteger){
                    if(!change.getText().matches("\\d+")) {
                        if (change.getText().equals(".")) {
                            if (change.getControlText().matches("-?\\d+\\.\\d+|\\.\\d+|-?\\d+\\.|\\.")) {
                                change.setText("");
                            }
                        } else {
                            change.setText("");
                        }
                    }
                }
            }
            return change;
        });
        return temp;
    }


    //判断账户是否存在且正确
    private boolean accountJudge(String userPassword, List<UserPOJO> user) {
        if(user.isEmpty()){
            alertDisplay(Alert.AlertType.WARNING, "User doesn't exist!");
            return false;
        }
        if(!user.get(0).getPassword().equals(userPassword)){
            alertDisplay(Alert.AlertType.WARNING, "Wrong Password!");
            return false;
        }
        currentUser = user.get(0); // 设置当前user
        Alert tempAlert = alertDisplay(Alert.AlertType.INFORMATION, "Welcome " + currentUser.getUserName() + " !");
        tempAlert.setOnCloseRequest(e -> loadMainPage(new MainPage()));
        return true;
    }

    //警告显示函数
    @Override
    public Alert alertDisplay(Alert.AlertType alertType, String content){
        Alert tempAlert = new Alert(alertType);
        if(alertType == Alert.AlertType.CONFIRMATION){
            tempAlert.getDialogPane().getButtonTypes().setAll(ButtonType.YES, ButtonType.CANCEL);
        }
        tempAlert.setContentText(content);
        tempAlert.show();
        return tempAlert;
    }

    private Optional<ButtonType> confirmAlertDisplay(String content){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(content);
        Optional<ButtonType> result = alert.showAndWait();
        alert.getDialogPane().getButtonTypes().setAll(ButtonType.YES, ButtonType.CANCEL);
        return  result;
    }

    //UserId生成器
    private int userIdGenerator(){
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 8; i++){
            str.append(random.nextInt(10));
        }
        int tempId = Integer.parseInt(str.toString());
        if(dataAccess.getUser(tempId).isEmpty()){
            return tempId;
        }else{
            return userIdGenerator();
        }
    }

    //RecipeId生成器
    private int recipeIdGenerator(){
        return recipeNumber++;
    }

    //IngredientId生成器
    private int ingredientIdGenerator(int recipeId){
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 5; i++){
            str.append(random.nextInt(10));
        }
        int tempId = Integer.parseInt(str.toString());

        List<IngredientPOJO> tempList = dataAccess.getRecipe_Id(recipeId).get(0).getIngredients();
        if(tempList == null){
            return tempId;
        }
        for (IngredientPOJO ingredientPOJO : tempList) {
            if (tempId == ingredientPOJO.getIngredientId()) {
                return ingredientIdGenerator(recipeId);
            }
        }
        return tempId;
    }

    //model初始化
    private void init(){
        dataAccess = DataAccess.getInstance();
        defaultImagePath = "image/";
    }

    //Variables
    private DataAccess dataAccess;
    private RecipePOJO currentOperateRecipe;
    private UserPOJO currentUser;
    private String defaultImagePath;
    private int recipeNumber = 1;
    private String currentImagePath = null;

}
