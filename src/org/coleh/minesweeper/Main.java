package org.coleh.minesweeper;//package org.coleh.minesweeper;
//
//import javafx.application.Application;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.layout.Border;
//import javafx.scene.layout.BorderPane;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//
//public class Main extends Application {
//
//    @Override
//    public void start(Stage primaryStage) throws IOException {
//
//        Parent root =  new SettingsGraphic();
//        primaryStage.setTitle("Minesweeper Settings");
//        primaryStage.setFullScreen(true);
//        primaryStage.setFullScreenExitHint("""
//                Welcome to Minesweeper. At left, you can alter the # of rows and columns. At right, set the # of mines. Play is bottom right.
//
//
//
//
//                _
//                """);
//
//      primaryStage.setMinHeight(840);
//      primaryStage.setMinWidth(1430);
//
//        Scene scene = new Scene(root);
//
//        primaryStage.setScene(scene);
//        primaryStage.show();
//
//
//    }
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//}

/*Sai Dandem's (bless his soul) answer.
Trying to reuse the existing functionality is not a ba_d idea.
But unfortunately this cannot be applied here, because you don't have any control over the message box displayed for full screen.
This cannot be done even with the node lookup/traversing to find the correct node,
because the message box is not part of the scene graph.
You might be wondering then how it is rendered,
its the internal implementation in Scene to provide the box directly to the "Painter" to paint it over the screen.
So instead of relying on "setFullScreenExitHint" method,
 I would recommend to create your own message box so that you will have full control over all the parameters
 (display duration, location, font size, styles...).

        Below is a full working demo of a custom message box shown on full screen:

 */

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = new SettingsGraphic();
        /*some code is from Sai Dandem on Stack Overflow,
         who explained how to use the message box instead of relying on FullScreenExitHint.*/
       //Sai wrote this; I'm keeping it in a comment in case I want to play around with the background. root.setStyle("-fx-background-color: linear-gradient(from 41px 34px to 50px 50px, reflect, #fffffd 30%, #fffffe 47%);");
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Minesweeper");
        primaryStage.setMinWidth(1430);
        primaryStage.setMinHeight(840);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint(""); // Setting empty string will not show the default message box
        primaryStage.setOnShown(e->{
            Duration displayDuration = Duration.millis(5000);

            Label msg = new Label("Welcome to Minesweeper. At left, you can alter the # of rows and columns. At right, set the # of mines. Play is bottom right.");
            msg.setWrapText(true);
            msg.setStyle("-fx-font-size:15px;-fx-font-family:verdana;-fx-text-fill:#FFFFFF;");

            StackPane box = new StackPane(msg);
            box.setMaxWidth(500);
            box.setStyle("-fx-padding:20px;-fx-background-radius:5px,4px;-fx-background-color:#33333380,#AAAAAA60;-fx-background-insets:0,2;");

            Popup popup = new Popup();
            popup.getContent().add(box);
            popup.show(primaryStage);

            PauseTransition pause = new PauseTransition(displayDuration);
            FadeTransition fade = new FadeTransition(Duration.millis(1000), box);
            fade.setFromValue(1);
            fade.setToValue(0);

            SequentialTransition overlayTransition = new SequentialTransition();
            overlayTransition.getChildren().addAll(pause,fade);
            overlayTransition.setOnFinished(event -> popup.hide());
            overlayTransition.play();

        });
        primaryStage.show();
    }
}
