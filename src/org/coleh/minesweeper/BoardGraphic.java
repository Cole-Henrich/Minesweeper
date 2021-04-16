package org.coleh.minesweeper;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class BoardGraphic extends BorderPane {
    int numRows;
    int numCols;
    double deathTrapRatio;
    String covered = "🥾";
    BoardLogic boardLogic;
    Button1[][] button1s;

    public BoardGraphic(int numRows, int numCols, double deathTrapRatio) throws IOException {
        button1s = new Button1[numRows][numCols];
        this.numRows = numRows;
        this.numCols = numCols;
        this.deathTrapRatio = deathTrapRatio;

        boardLogic = new BoardLogic(numRows, numCols, deathTrapRatio);
        SettingsGraphic settingsGraphic = new SettingsGraphic();
        this.deathTrapRatio = settingsGraphic.getDeathTrapRatio();
        getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        Button1 Settings = new Button1("Settings");
        Settings.getStyleClass().add("Settings");

        Settings.setOnAction(actionEvent -> ((Stage) (getScene().getWindow())).setScene(new Scene(new SettingsGraphic())));
        Button1 Reset = new Button1("Reset");
        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox topBox = new HBox();
        topBox.getChildren().addAll(Settings, spacer, Reset);
        this.setTop(topBox);
        render();
        Reset.setOnAction(actionEvent -> {
           boardLogic = new BoardLogic(numRows, numCols, deathTrapRatio);
           render();
        });
    }

    private VBox1 render(){
        VBox1 vBox1 = new VBox1();
        VBox1.setVgrow(vBox1, Priority.ALWAYS);
        for (int i = 0; i < this.numRows; i++) {
            HBox row = new HBox();
            HBox.setHgrow(row, Priority.ALWAYS);
            for (int j = 0; j < this.numCols; j++) {

                Button1 button1 = new Button1();
                button1.autoSize(numRows, numCols);
                int finalI = i;
                int finalJ = j;
                button1.setText(covered);
                button1.setOnAction(actionEvent -> {
                    
                    int space = boardLogic.getSpace(finalI, finalJ);

                        if (space == 0) {
                            for (int k = 0; k < 3; k++) {
                                boardLogic.pickSpace(finalI, finalJ);//ameliorating the glitch requiring multiple clicks.
                            }
                            for (int k = finalI - 1; k <= finalI + 1; k++) {
                                for (int l = finalJ - 1; l <= finalJ + 1; l++) {
                                    boardLogic.pickSpace(k, l);
                                }
                            }
                        }
                    else {
                            if (space == -9){
                                button1.setText("💣");
                            }
                        else{button1.setText(String.valueOf(space));}

                    }
                    if (boardLogic.pickSpace(finalI, finalJ) || wins()) {
                        Stage stage = ((Stage) (getScene().getWindow()));
                        stage.setTitle("Good Game");
                        boolean winner = false;
                       boardLogic.pickSpace(finalI, finalJ);
                       //there is a slight glitch where you have to click an extra time before the game recognizes that you've won.
                        //this "clicks" that for you.
                        if (wins()) {
                            winner=true;
                        }
                        stage.setScene(new Scene(new finale(winner, finalI, finalJ)));
                    }
                    render();
                });
                button1.setPadding(new Insets(20, 20, 20, 20));
                row.getChildren().add(button1);
                button1s[i][j]=button1;

            }
            vBox1.getChildren().add(row);
        }

        this.setCenter(vBox1);
        return vBox1;
    }
    public boolean wins(){
        boolean wins;
        int count = 0;
        for (int i = 0; i < button1s.length ; i++) {
            Button1[] bs = button1s[i];
            for (int j = 0; j < bs.length; j++) {
                Button1 b = bs[j];
                String face = b.getText();
                if (face.equals(covered) && boardLogic.getSpace(i, j) != -9){
                    count++;
                }
            }
        }
        wins = count == 0;
        return wins;
    }
}


