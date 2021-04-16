package org.coleh.minesweeper;

import javafx.scene.Node;

public class fontSize {
    public fontSize(Node node, double fontSize, String unit){
        node.setStyle("-fx-font-size:" +fontSize+unit+";");
    }
}
