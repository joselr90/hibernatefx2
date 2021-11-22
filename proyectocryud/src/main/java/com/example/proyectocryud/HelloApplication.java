package com.example.proyectocryud;

import animatefx.animation.AnimationFX;
import animatefx.animation.ZoomIn;
import animatefx.animation.ZoomOut;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    Parent root;
    private double x,y;
    @Override
    public void start(Stage primaryStage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        root= loader.load();
        Scene scene = new Scene(root);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();

        ChangeListener<? super Boolean> minimizeAnimation = (obs, wasFocused, isNowFocused) ->
        {
            if (isNowFocused) {
                try
                {

                    System.out.print("\n  in");
                    AnimationFX in = new ZoomIn(root);
                    in.setSpeed(1.75D); //Set speed for animation
                    in.setResetOnFinished(true); //if dont want a unli loop
//                    primaryStage = (Stage) root.getScene().getWindow(); //get back
                    in.setOnFinished(actionEvent -> primaryStage.setIconified(false));
                    in.play();

                }catch (NullPointerException e)
                {
                    System.out.println(e);
                    e.printStackTrace();
                }
            } else {
                try
                {
                    System.out.print("\n out");
                    AnimationFX fxs = new ZoomOut(root);
                    fxs.setSpeed(2.75D);
                    fxs.setResetOnFinished(true);
                    fxs.setOnFinished(actionEvent -> primaryStage.setIconified(true));
                    fxs.play();
                }catch (NullPointerException e)
                {
                    System.out.println(e);
                    e.printStackTrace();

                }
            }

        };

//        root.setOnMousePressed(event ->
//        {
//            x =event.getSceneX();
//            y =event.getSceneY();
//        } );
//
//        root.setOnMouseDragged(event ->
//        {
//            primaryStage.setX(event.getScreenX() - x);
//            primaryStage.setY(event.getScreenY() - y);
//        } );

        primaryStage.focusedProperty().addListener(minimizeAnimation);
        HelloController controller = loader.getController();
        controller.Onload();
        controller.setMinimizeAnimation(minimizeAnimation);
        controller.setPrimaryStage(primaryStage);
        controller.editButton();
//        controller.rightClickDelete();


    }


    public static void main(String[] args) {
        launch();
    }
}