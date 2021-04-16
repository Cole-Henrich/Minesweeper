
package org.coleh.minesweeper;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.PercentageStringConverter;

import java.io.IOException;
import java.text.NumberFormat;

public class SettingsGraphic extends BorderPane{

    private double deathTrapRatio;
    private int numRows;
    private int numCols;

    public SettingsGraphic() {
        this.setPrefSize(540, 540);
        SpinnerValueFactory factory1 = new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 10, 5, 1);
        SpinnerValueFactory factory2 = new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 10, 5, 1);

        Spinner RowTextIndicator = new Spinner(factory1);
        Spinner ColumnTextIndicator = new Spinner(factory2);

        Slider1 row = new Slider1(true);
        Slider1 column = new Slider1(true);
        row.valueProperty().bindBidirectional(RowTextIndicator.getValueFactory().valueProperty());
        column.valueProperty().bindBidirectional(ColumnTextIndicator.getValueFactory().valueProperty());

        this.setNumRows ( (int) Double.parseDouble(String.valueOf(RowTextIndicator.getValueFactory().getValue())));
        this.setNumCols( (int) Double.parseDouble(String.valueOf(ColumnTextIndicator.getValueFactory().getValue())));

        RowTextIndicator.setOnMouseClicked(mouseEvent -> this.setNumRows( (int) Double.parseDouble(String.valueOf(RowTextIndicator.getValueFactory().getValue()))));
        ColumnTextIndicator.setOnMouseClicked(mouseEvent -> this.setNumCols( (int) Double.parseDouble(String.valueOf(ColumnTextIndicator.getValueFactory().getValue()))));
        row.setOnMouseMoved(mouseEvent -> this.setNumRows((int) row.getValue()));
        column.setOnMousePressed(mouseEvent -> this.numCols = Integer.parseInt(String.valueOf(column.getValue())));
        column.setOnMouseMoved(mouseEvent -> this.numCols = Integer.parseInt(String.valueOf(column.getValue())));
        this.setOnMouseMoved(mouseEvent -> {
            this.numCols = (int) column.getValue();
        });

        HBox RowTextIndicatorBox = new HBox();
        RowTextIndicatorBox.getChildren().addAll(RowTextIndicator);

        Button1 Easy = new Button1("Easy", .166_666_666_666_666_666);
        Button1 Medium = new Button1("Medium",.333_333_333_333_333_333);
        Button1 Hard = new Button1("Hard", 0.5);
        Button1 Play = new Button1("Play");
        Play.setMinSize(40, 40);
        Play.setOpacity(0.5);

        for (Button1 button1: new Button1[] {Easy, Medium, Hard}) {
            button1.setOnAction(actionEvent ->{
                this.deathTrapRatio = button1.getDeathTrapFrequency();
                Play.setOpacity(1);
            });
        }

        Play.setOnAction(actionEvent -> {

            if (this.deathTrapRatio > 0 && Play.getOpacity() == 1) {
                Scene s = null;
                try {
                    Stage stage = ((Stage) getScene().getWindow());

                    stage.setTitle("Game on!");
                    stage.setFullScreenExitHint("Game On! Settings is top left; reset is top right." +
                            " Click the tiles, and hope you don't hit a mine! ");
                    s = new Scene(new BoardGraphic(this.numRows, this.numCols, this.deathTrapRatio));
                    stage.setScene(s);
                    stage.setMaximized(true);
                    stage.setFullScreen(true);
                } catch (IOException e) {
                    System.err.println("!!<ERROR IN PLAY.SETONACTION>!!");
                    e.printStackTrace();
                }

            }
        });

        Button1 space_between_levelButtons = new Button1(0, 10);
        Button1 space_between_levelButtons2 = new Button1(0, 10);

        HBox easy = new HBox(Easy);
        HBox medium = new HBox(Medium);
        HBox hard = new HBox(Hard);

        HBox space_between_levelButtonsHBox = new HBox();
        HBox space_between_levelButtonsHBox2 = new HBox();
        space_between_levelButtonsHBox.getChildren().addAll(space_between_levelButtons);
        space_between_levelButtonsHBox2.getChildren().addAll(space_between_levelButtons2);

        VBox1 rBox = new VBox1(RowTextIndicatorBox, row, new Text("# Rows"));
        VBox1 cBox = new VBox1(ColumnTextIndicator, column, new Text("# Columns"));

        VBox vBox = new VBox();
        vBox.setMinWidth(100);
        vBox.getChildren().addAll(rBox, cBox);
        VBox.setVgrow(vBox, Priority.ALWAYS);
        this.setLeft(vBox);

        Button1 space_rightOfPlay = new Button1(10, 10);


        HBox HplayBox = new HBox();
        HplayBox.setAlignment(Pos.BOTTOM_RIGHT);
        HplayBox.getChildren().addAll(/*ChangeTheme,*/ new Button1(space_rightOfPlay), Play, space_rightOfPlay);

        VBox1 playBox = new VBox1();
        playBox.getChildren().addAll(HplayBox, new Button1(space_rightOfPlay));
        playBox.setAlignment(Pos.BOTTOM_RIGHT);
        VBox1.setVgrow(playBox, Priority.ALWAYS);

        VBox1 RightBox = new VBox1();
        RightBox.getChildren().addAll(easy, space_between_levelButtonsHBox, medium, space_between_levelButtonsHBox2, hard, playBox);

        HBox.setHgrow(RightBox, Priority.ALWAYS);
        this.setRight(RightBox);


        }
        public double getDeathTrapRatio(){
            return this.deathTrapRatio;
        }

        public int setNumRows(int numRows){
           if (numRows < 2) numRows = 2;
           this.numRows = numRows;
           return this.numRows;
        }
        public int setNumCols(int numCols){
            if (numCols < 2) numCols = 2;
            this.numCols = numCols;
            return this.numCols;
        }
}


