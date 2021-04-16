package org.coleh.minesweeper;

import javafx.geometry.Orientation;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;

public class Slider1 extends Slider {
    /**
     * Creates a default Slider1 instance.
     */
    public Slider1(){
        super();
    }
    /**
     * Constructs a Slider1 control with the specified slider min, max and current value values.
     * @param min lowest value the slider can go.
     * @param max highest value the slider can go.
     * @param value the initial location of the slider knob.
     */
    public Slider1(double min, double max, double value){
        super(min, max, value);
    }

    public Slider1(boolean custom) {
        this(1, 10, 5);
        this.setVisible(true);
        this.setMajorTickUnit(1.0);
        this.setMinorTickCount(0);
        this.setBlockIncrement(1);

        this.setSnapToTicks(true);
        this.setOrientation(Orientation.VERTICAL);
    }
}
