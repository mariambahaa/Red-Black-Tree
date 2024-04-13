package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Selection {

    private Scene scene;
    private Stage stage;
    private RedBlackTree bst;

    public Selection(Stage stage) {
        this.stage = stage;
    }
    public void prepareScene(){
        Label choose = new Label("Dictionary: Choose Action To Perform");
        choose.setFont(new Font("Arial",20));
        choose.setTextFill(Color.WHITE);

        Label msg = new Label();
        msg.setFont(new Font("Arial",20));
        msg.setTextFill(Color.WHITE);

        Button search = new Button("Search");
        search.setFont(new Font("Arial",20));
        search.setStyle("-fx-background-radius: 15;");

        Button insert = new Button("Insert");
        insert.setFont(new Font("Arial",20));
        insert.setStyle("-fx-background-radius: 15;");

        Button height = new Button("Height");
        height.setFont(new Font("Arial",20));
        height.setStyle("-fx-background-radius: 15;");

        Button size = new Button("Size");
        size.setFont(new Font("Arial",20));
        size.setStyle("-fx-background-radius: 15;");

        Button delete = new Button("Delete");
        delete.setFont(new Font("Arial",20));
        delete.setStyle("-fx-background-radius: 15;");


        Label word = new Label("Enter Word:");
        word.setFont(new Font("Arial",20));
        word.setTextFill(Color.WHITE);

        TextField wordEntered = new TextField();
        wordEntered.setFont(new Font("Arial",20));

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(25));
        grid.add(choose,0,0);
        grid.addRow(1,new Text(""));
        grid.add(search,0,2);
        grid.add(insert,0,3);
        grid.add(delete,0,4);
        grid.add(size,0,5);
        grid.add(height,0,6);
        grid.add(new Text(""),1,4);
        grid.add(word,2,3);
        grid.add(wordEntered,2,4);
        grid.addRow(7,new Text(""));

        try {
            FileInputStream input = new FileInputStream("Book.jpg");
            Image image = new Image(input);
            ImageView imageView = new ImageView(image);
            imageView.fitWidthProperty().bind(stage.widthProperty());
            imageView.fitHeightProperty().bind(stage.heightProperty());
            BackgroundImage backgroundimage = new BackgroundImage(image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background background = new Background(backgroundimage);
            grid.setBackground(background);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String message = bst.search(wordEntered.getText());
                msg.setText(message);
            }
        });

        insert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String message = bst.inserting(wordEntered.getText());
                msg.setText(message);
                print();
            }
        });

        height.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String message = String.valueOf(bst.maxDepth(bst.getRoot()));
                msg.setText(message);
            }
        });

        size.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String message = String.valueOf(bst.getSize());
                msg.setText(message);
            }
        });

        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String message = bst.delete(wordEntered.getText());
                msg.setText(message);
                print();
            }
        });

        print();

        grid.add(msg,0,8);
        grid.setHgap(15);
        grid.setVgap(15);

        scene = new Scene(grid,700,700);
    }

    public void print(){
        System.out.println("Height of Tree: " + bst.maxDepth(bst.getRoot()));
        System.out.println("Size of Tree: " + bst.getSize());
        System.out.println("");
    }

    public void setBst(RedBlackTree bst) {
        this.bst = bst;
    }

    public Scene getScene(){
        return scene;
    }
}
