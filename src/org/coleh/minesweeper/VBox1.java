package org.coleh.minesweeper;

import javafx.scene.Node;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class VBox1 extends VBox {
    public VBox1(){
        super();
    }
    public VBox1(double v){
        super(v);
    }
    public VBox1(Node... children){
        super(children);
    }
    public VBox1(double v, Node... children){
        super(v, children);
    }
    public VBox1(Button1 button1, Slider1 slider1, Text text, Node... children){
       super(children);
        this.Button1_Slider1_Text(button1, slider1, text);
    }
    private VBox1 Button1_Slider1_Text(Button1 button1, Slider1 slider1, Text text){
        text.setVisible(true);
        VBox1.setVgrow(this, Priority.ALWAYS);
        this.getChildren().addAll(button1, slider1, text);
        return this;
    }
}
