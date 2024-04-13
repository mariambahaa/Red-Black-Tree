package sample;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        RedBlackTree bst = new RedBlackTree();
        bst.load(bst);

        Selection selection = new Selection(primaryStage);
        selection.setBst(bst);
        selection.prepareScene();

        primaryStage.setTitle("Dictionary");
        primaryStage.setScene(selection.getScene());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
