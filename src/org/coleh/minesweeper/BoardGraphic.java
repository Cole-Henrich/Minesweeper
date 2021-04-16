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
    int numRecursions;
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
                    numRecursions=0;
                    boardLogic.pickSpace(finalI, finalJ);
                    int space = boardLogic.getSpace(finalI, finalJ);
                    if (space ==-1){
                        for (int k = 0; k < 4; k++) {
                            boardLogic.pickSpace(finalI, finalJ);
                            button1.setText(String.valueOf(boardLogic.getSpace(finalI, finalJ)));
                        }
                    }
                        if (space == 0) {graphic_recursion(finalI, finalJ);}
                    else {
                            if (space == -9){
                                button1.setText("💣");
                            }
                        else{button1.setText(String.valueOf(space));}

                    }
                    if (boardLogic.pickSpace(finalI, finalJ) || wins()) {
                        Stage stage = ((Stage) (getScene().getWindow()));
                        stage.setTitle("Good Game");

                       boardLogic.pickSpace(finalI, finalJ);
                       //there is a slight glitch where you have to click an extra time before the game recognizes that you've won.
                        //this "clicks" that for you.

                        stage.setScene(new Scene(new finale(wins(), finalI, finalJ)));
                    }
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

    /**
     *
     * @param row
     * @param col
     * assume that this method is called when boardLogic.getSpace(i, j) is already 0.
     * That is a given. That is the context in which you call this method.
     * Still, this checks anyways.
     */
    private void graphic_recursion(int row, int col) {
        if (boardLogic.getSpace(row, col) == 0) {






            if (row > 0) {
                if (col > 0) {
                    boardLogic.pickSpace(row - 1, col - 1);
                    button1s[row - 1][col - 1].setText(String.valueOf(boardLogic.getSpace(row - 1, col - 1)));
                    if (String.valueOf(button1s[row - 1][col - 1].getText()).equals(covered)) {
                        if (boardLogic.getSpace(row - 1, col - 1) == 0) {
                            graphic_recursion(row - 1, col - 1);
                        }
                    }
                }
                boardLogic.pickSpace(row - 1, col);
                button1s[row - 1][col].setText(String.valueOf(boardLogic.getSpace(row - 1, col)));
                if (String.valueOf(button1s[row - 1][col].getText()).equals(covered)) {
                    if (boardLogic.getSpace(row - 1, col) == 0) {
                        graphic_recursion(row - 1, col);
                    }
                }
                if (col < button1s.length-1) {
                    boardLogic.pickSpace(row - 1, col + 1);
                    button1s[row - 1][col + 1].setText(String.valueOf(boardLogic.getSpace(row - 1, col + 1)));
                    if (String.valueOf(button1s[row - 1][col + 1].getText()).equals(covered)) {
                        if (boardLogic.getSpace(row - 1, col + 1) == 0) {
                            graphic_recursion(row - 1, col + 1);
                        }
                    }
                }
            }





            if (col > 0) {
                boardLogic.pickSpace(row, col - 1);
                button1s[row][col - 1].setText(String.valueOf(boardLogic.getSpace(row, col - 1)));
                if (String.valueOf(button1s[row][col - 1].getText()).equals(covered)) {
                    if (boardLogic.getSpace(row, col - 1) == 0) {
                        graphic_recursion(row, col - 1);
                    }
                }
            }
            boardLogic.pickSpace(row, col);
            button1s[row][col].setText(String.valueOf(boardLogic.getSpace(row, col)));

            if (col < button1s.length-1) {
                boardLogic.pickSpace(row, col + 1);
                button1s[row][col + 1].setText(String.valueOf(boardLogic.getSpace(row, col + 1)));
                if (String.valueOf(button1s[row][col + 1].getText()).equals(covered)) {
                    if (boardLogic.getSpace(row, col + 1) == 0) {
                        graphic_recursion(row, col + 1);
                    }
                }
            }





            if (row < button1s.length-1) {
                if (col > 0) {
                    boardLogic.pickSpace(row + 1, col - 1);
                    button1s[row + 1][col - 1].setText(String.valueOf(boardLogic.getSpace(row + 1, col - 1)));
                    if (String.valueOf(button1s[row + 1][col - 1].getText()).equals(covered)) {
                        if (boardLogic.getSpace(row + 1, col - 1) == 0) {
                            graphic_recursion(row + 1, col - 1);
                        }
                    }
                }

                boardLogic.pickSpace(row + 1, col);
                button1s[row + 1][col].setText(String.valueOf(boardLogic.getSpace(row + 1, col)));

                if (String.valueOf(button1s[row + 1][col].getText()).equals(covered)) {
                    if (boardLogic.getSpace(row + 1, col) == 0) {
                        graphic_recursion(row + 1, col);
                    }
                }

                if (col < button1s.length-1) {
                    boardLogic.pickSpace(row + 1, col + 1);
                    button1s[row + 1][col + 1].setText(String.valueOf(boardLogic.getSpace(row + 1, col + 1)));
                    if (String.valueOf(button1s[row + 1][col + 1].getText()).equals(covered)) {
                        if (boardLogic.getSpace(row + 1, col + 1) == 0) {
                            graphic_recursion(row + 1, col + 1);
                        }
                    }
                }

            }




        }
    }
//        int[] cols;
//        int[] rows;
//        if (j == 0) {cols = new int[]{j, j + 1};}
//        else if (j >= button1s.length) {cols = new int[]{j-1, j};}
//        else{cols = new int[]{j-1, j, j + 1};}
//
//        if (i == 0) {rows = new int[]{i, i + 1};}
//        else if (i >= button1s.length) {rows = new int[]{i-1, i};}
//        else{rows = new int[]{i-1, i, i + 1};}
//
//        for (int r: rows) {
//            for (int k : cols) {
//                boolean selfSame = (r == i && k == j);
//                if (!selfSame) {
//                    boardLogic.pickSpace(r, k);
//                    int space = boardLogic.getSpace(r, k);
//                    button1s[r][k].setText(String.valueOf(space));
//                    if (space == 0) {
//                        numRecursions++;
//                        System.out.println("numRecursions: " + numRecursions);
//                        graphic_recursion(r, k);
//                    }
//                }
//            }
//        }
//    }
}


