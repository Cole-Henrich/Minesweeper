package org.coleh.minesweeper;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Set;

/**
 * You must display a win/lose message at the end of the game and no further actions (except Settings) is allowed:
 * The player loses if a mine is selected.
 * The player wins if all non-mine spaces have been selected.
 */
public class finale extends BorderPane {
    public finale(boolean winner, int row, int col){
        String s = "You Lose!";
        String info = "You clicked a mine at row "+row+", column "+col+".";
        if (winner){
            s = "You Win!";
            info = "";
        }
        Text text = new Text(s);
        text.setStyle("-fx-font-size:100px;");

        Text textInfo = new Text(info);
        textInfo.setStyle("-fx-font-size:30px;");
        Button1 Settings = new Button1("Settings");
        Settings.setStyle("-fx-font-size:50px;");
        Settings.getStyleClass().add("Settings");
        Settings.setOnAction(actionEvent -> {
            Stage stage = (Stage) (getScene().getWindow());
            stage.setTitle("Settings");
            stage.setMaximized(true);
            stage.setScene(new Scene(new SettingsGraphic()));
        });
        VBox1 vBox1 = new VBox1(text, textInfo, Settings);
        text.setTextAlignment(TextAlignment.CENTER);
        vBox1.setAlignment(Pos.CENTER);
        this.setCenter(vBox1);
    }
}
