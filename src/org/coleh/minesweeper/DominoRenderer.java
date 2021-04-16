package org.coleh.minesweeper;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;


public class DominoRenderer extends StackPane{
    public DominoRenderer(Domino[] dominoes) {
        int k=0;
        int lim = (int) (Math.sqrt(dominoes.length));
        System.out.println(dominoes.length);
        System.out.println(lim);
        VBox1 stack = new VBox1();

        for (int i = 0; i < lim; i++) {
            HBox row = new HBox();
            for (int j = 0; j < dominoes.length/lim; j++) {
                Button1 square = new Button1(dominoes[k].getIcon());
//                square.setStyle("-fx-fontSize-size:100px");
                row.getChildren().add(square);
                k++;
            }
            stack.getChildren().add(row);
        }
        this.getChildren().addAll(stack);
    }

}
