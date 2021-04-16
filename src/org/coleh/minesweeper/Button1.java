package org.coleh.minesweeper;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.util.Arrays;

public class Button1 extends Button {

private int numRows;
private int numCols;
private double deathTrapFrequency;
private String text;
private String backSide;
private String frontSide;
private boolean isBackSide;
private double mandatoryWidth;
private double mandatoryHeight;
    /**
     * Please excuse the abhorrent structure of this class.
     * I am enthusiastically pursuing an end without consideration to the means in which I code it.
     * I figure that once I complete it, I will rest easy knowing the UI is there, and then I will work on the backend.
     * Turn an especially blind eye to the array of exceptions.
     * When I see you next, if I remember, I hope to discuss more intelligent ways of achieving the goal.
     */
    private static final String[] emojiSets = {
            "💣1️⃣2️⃣3️⃣",
            "🧨1️⃣2️⃣3️⃣",
            "-🥉🥈🥇",
            "🌑🌒🌓🌕",
            "🌑🌘🌗🌕",
            "🥀🌷🌹💐",
            "🍂🌱☘️🍀",
            "🦣🦛🦏🐘",
            "🍗🐣🐥🐔",
            "🛫🛩🚀🛸",
            "🦼🛺🛵🏍",
            "🛶⛵️🚤🛥",
            "🚋🚂🚝🚅",
            "🦽🚘🚄🚀",
            "🎥📹📸📱",
            "🇰🇵🇧🇷🇯🇵🇳🇴",
            "💔💓💕💞"
    };
    private static final String[] themes = {
            "bombs",
            "tnt",
            "medals, olympics, bronze, silver, gold",
            "waxing",
            "waning",
            "flowers, rose",
            "leaves, leaf",
            "mammoth, hippo, rhino, elephant, herbivorous giants",
            "chicken, drumstick",
            "planes, jet, rocket, ufo, aerialtransportation, Aerial Transportation",
            "MotorBikes, motor bikes, wheelchair, autorickshaw, scooter, motorcycle",
            "Boats, canoe, sailboat, motorboat, yacht",
            "Trains, railcar, steam locomotive, monorail, bullet train",
            "Transportation, wheelchair, car, highspeedrail, rocket",
            "Evolution_of_Videos, camera, flash, iphone",
            "FreeSpeech, North Korea, Brazil, Japan, Norway",
            "lovesMe_lovesMeNot"
    };

    private static final Exception[] exceptions = {
             new InterruptedException(),
             new RuntimeException(),
             new ArithmeticException(),
             new NullPointerException(),
             new ArrayIndexOutOfBoundsException(),
             new ArrayStoreException(),
             new ClassCastException(),
             new CloneNotSupportedException(),
             new IllegalAccessException(),
             new IllegalArgumentException(),
             new IllegalCallerException(),
             new IllegalThreadStateException(),
             new InstantiationException(),
             new LayerInstantiationException(),
             new ReflectiveOperationException(),
             new SecurityException(),
             new NoSuchMethodException()
    };
    private static Exception getExceptions(String name){
        Exception exception = new NoSuchMethodException();
        for (int i = 0; i < themes.length; i++) {
            String theme = themes[i];
            if (theme.contains(name)){
               exception = exceptions[i];
            }
        }
        return exception;
    }

    public Button1(double deathTrapFrequency, String deathTrapIcon, String frontSideIcon ){
        this.frontSide = frontSideIcon;
        this.setText(this.frontSide);
        this.setOnAction(actionEvent -> {
            this.backSide = deathTrapIcon;
            this.setText(this.backSide);
        });
    }
    public Button1(Button1 anotherSpacer){
        this(anotherSpacer.mandatoryWidth, anotherSpacer.mandatoryHeight);
    }
    public Button1(double deathTrapFrequency) {
        this(deathTrapFrequency, lovesMe_lovesMeNot(deathTrapFrequency), "❓");
    }
    public Button1(){
        super();
    }
    public Button1(String s){
        super(s);
    }
    public Button1(String s, Node node){
        super(s, node);
    }
    public Button1(String s, Slider1 slider1){
        super(s);
    }
    public Button1(double mandatoryWidth, double mandatoryHeight) {
        this();
        if (mandatoryWidth > 0){
           this.setMinWidth(mandatoryWidth);
           this.setMaxHeight(mandatoryWidth);
           this.mandatoryWidth = mandatoryWidth;
        }
        if (mandatoryHeight > 0){
            this.setMinHeight(mandatoryHeight);
            this.setMaxHeight(mandatoryHeight);
            this.mandatoryHeight = mandatoryHeight;
        }
        this.setVisible(false);
    }
    public Button1(String s, double deathTrapFrequency){
        super(s);
        this.text = s;
        this.deathTrapFrequency = deathTrapFrequency;
        this.setPadding(new Insets(10,190,10,190));//top, right, bottom, left. Clockwise from top.
    }

    public double getDeathTrapFrequency(){
        return this.deathTrapFrequency;
    }

    public double setToSliderValue(Slider1 slider1) {
        int value = (int) Math.round(slider1.getValue());
        this.setText(String.valueOf(value));
        return value;
    }
    public String check(int iterations){
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        for (int i = 0; i < iterations; i++) {
            double value = getTileBacksideValue();
            if (value == 1){
                count1++;
            }
            if (value == 2){
                count2++;
            }
            if (value == 3){
                count3++;
            }
        }
        String rtn = (count1 + " " + (float) count1/iterations + " vs " + 3.0/6.0 + " " + count2+" "+ (float) count2/iterations + " vs " + 2.0/6.0 + "" +count3+ " " + (float) count3/iterations + " vs " + 1.0/6.0 );
        System.out.println(rtn);
        return rtn;
    }
    private static int getTileBacksideValue(){
        int value;
        double random = Math.random();
        if ( random <  5.0/6.0 ){
            if (random < 3.0/6.0){value = 1;}
            else {value = 2;}
        }
        else {value = 3;}
        return value;
    }
    static String stylish(double deathTrapFrequency, String deathIcon, String onePointIcon, String twoPointsIcon, String threePointsIcon){
        String successLevel;
        if (Math.random() < deathTrapFrequency) {
            successLevel = deathIcon;
        }
        else {
            double points = getTileBacksideValue();
            if (points == 1){
                successLevel = onePointIcon;
            }
            else if (points == 2){
                successLevel = twoPointsIcon;
            }
            else {
                successLevel = threePointsIcon;
            }
        }
        return successLevel;
    }
    public static String styleFaster(double deathTrapFrequency, String emojis){
        return stylish (
                deathTrapFrequency,
                String.valueOf(emojis.charAt(0)),
                String.valueOf(emojis.charAt(1)),
                String.valueOf(emojis.charAt(2)),
                String.valueOf(emojis.charAt(3))
        );
    }

    public Button1(double deathTrapFrequency, InterruptedException bombs){
        this(deathTrapFrequency, Bombs(deathTrapFrequency), "");
    }
    public Button1(double deathTrapFrequency,  RuntimeException TNT){
        this(deathTrapFrequency, TNT(deathTrapFrequency), "");
    }
    public Button1(double deathTrapFrequency, ArithmeticException Medals){
        this(deathTrapFrequency, Medals(deathTrapFrequency), "");
    }
    public Button1(double deathTrapFrequency, NullPointerException WaxingMoon){
        this(deathTrapFrequency, WaxingMoon(deathTrapFrequency), "");
    }
    public Button1(double deathTrapFrequency, ArrayIndexOutOfBoundsException WaningMoon){
        this(deathTrapFrequency, WaningMoon(deathTrapFrequency), "");
    }
    public Button1(double deathTrapFrequency, ArrayStoreException Flowers){
        this(deathTrapFrequency, Flowers(deathTrapFrequency), "");
    }
    public Button1(double deathTrapFrequency, ClassCastException Leaves){
        this(deathTrapFrequency, Leaves(deathTrapFrequency), "");
    }
    public Button1(double deathTrapFrequency, CloneNotSupportedException HerbivorousGiants){
        this(deathTrapFrequency, HerbivorousGiants(deathTrapFrequency), "");
    }
    public Button1(double deathTrapFrequency, IllegalAccessException LifeOfAChicken){
        this(deathTrapFrequency, LifeOfAChicken(deathTrapFrequency), "🥚");
    }
    public Button1(double deathTrapFrequency, IllegalArgumentException AerialTransportation){
        this(deathTrapFrequency, AerialTransportation(deathTrapFrequency), "");
    }
    public Button1(double deathTrapFrequency, IllegalCallerException MotorBikes){
        this(deathTrapFrequency, MotorBikes(deathTrapFrequency), "");
    }
    public Button1(double deathTrapFrequency, IllegalThreadStateException Boats){
        this(deathTrapFrequency, Boats(deathTrapFrequency), "");
    }
    public Button1(double deathTrapFrequency, InstantiationException Trains){
        this(deathTrapFrequency, Trains(deathTrapFrequency), "");
    }
    public Button1(double deathTrapFrequency, LayerInstantiationException Transportation){
        this(deathTrapFrequency, Transportation(deathTrapFrequency), "");
    }
    public Button1(double deathTrapFrequency, ReflectiveOperationException Evolution_of_Videos){
        this(deathTrapFrequency, Evolution_of_Videos(deathTrapFrequency), "");
    }
    public Button1(double deathTrapFrequency, SecurityException FreeSpeech){
        this(deathTrapFrequency, FreeSpeech(deathTrapFrequency), "");
    }
    public Button1(double deathTrapFrequency, NoSuchMethodException lovesMe_lovesMeNot){
        this(deathTrapFrequency, lovesMe_lovesMeNot(deathTrapFrequency), "");
    }

    private static String Bombs (double deathTrapFrequency) {
        return styleFaster(deathTrapFrequency, "💣1️⃣2️⃣3️⃣");
    }
    private static String TNT (double deathTrapFrequency) {
        return styleFaster(deathTrapFrequency, "🧨1️⃣2️⃣3️⃣");
    }
    private static String Medals (double deathTrapFrequency) {
        return stylish(deathTrapFrequency,"", "🥉","🥈", "🥇");
    }
    private static String WaxingMoon(double deathTrapFrequency) {
        return stylish(deathTrapFrequency,"🌑","🌒","🌓", "🌕");
    }
    private static String WaningMoon (double deathTrapFrequency) {
        return stylish(deathTrapFrequency,"🌑","🌘","🌗","🌕");
    }
    //illegal characters causing problems
//    private static String SunnyWeather (double deathTrapFrequency) {
//        return stylish(deathTrapFrequency, "⛈", "⛅", "🌤", "🌞"️);
//    }
    private static String Flowers (double deathTrapFrequency) {
        return stylish(deathTrapFrequency, "🥀", "🌷", "🌹", "💐");
    }
    private static String Leaves (double deathTrapFrequency) {
        return stylish(deathTrapFrequency, "🍂", "🌱","☘️", "️🍀");
    }
    private static String HerbivorousGiants (double deathTrapFrequency) {
        return stylish(deathTrapFrequency, "🦣", "🦛", "🦏", "🐘");
    }
    private static String LifeOfAChicken (double deathTrapFrequency) {
        return stylish(deathTrapFrequency,"🍗", "🐣", "🐥", "🐔");
    }
    private static String AerialTransportation (double deathTrapFrequency) {
        return stylish(deathTrapFrequency, "🛫","🛩","🚀","🛸");
    }
    private static String MotorBikes (double deathTrapFrequency) {
        return styleFaster(deathTrapFrequency, "🦼🛺🛵🏍");
    }
    private static String Boats (double deathTrapFrequency) {
        return styleFaster(deathTrapFrequency, "🛶⛵️🚤🛥");
    }
    private static String Trains (double deathTrapFrequency) {
        return styleFaster(deathTrapFrequency,"🚋🚂🚝🚅");
    }
    private static String Transportation (double deathTrapFrequency) {
        return styleFaster(deathTrapFrequency, "🦽🚘🚄🚀");
    }
    private static String Evolution_of_Videos (double deathTrapFrequency) {
        return styleFaster(deathTrapFrequency, "🎥📹📸📱");
    }
    /**
     * @citation “World Press Freedom Index.” RSF, https://rsf.org/en/ranking/2020. Accessed 13 Mar. 2021.
     */
    private static String FreeSpeech (double deathTrapFrequency) {
        return styleFaster(deathTrapFrequency,"🇰🇵🇧🇷🇯🇵🇳🇴");
    }
    private static String lovesMe_lovesMeNot(double lovesMeNotFrequency) {
       return stylish(lovesMeNotFrequency, "💔", "💓", "💕", "💞");
    }
    public Button1 makeButton1(String name, double deathTrapFrequency){
        Button1 button1 = null;

        if (themes[0].contains(name)){button1 = new Button1(deathTrapFrequency, new InterruptedException());}
        if (themes[1].contains(name)){button1 = new Button1(deathTrapFrequency, new RuntimeException() );}
        if (themes[2].contains(name)){button1 = new Button1(deathTrapFrequency, new ArithmeticException() );}
        if (themes[3].contains(name)){button1 = new Button1(deathTrapFrequency, new NullPointerException() );}
        if (themes[4].contains(name)){button1 = new Button1(deathTrapFrequency, new ArrayIndexOutOfBoundsException() );}
        if (themes[5].contains(name)){button1 = new Button1(deathTrapFrequency, new ArrayStoreException());}
        if (themes[6].contains(name)){button1 = new Button1(deathTrapFrequency, new ClassCastException());}
        if (themes[7].contains(name)){button1 = new Button1(deathTrapFrequency, new CloneNotSupportedException());}
        if (themes[8].contains(name)){button1 = new Button1(deathTrapFrequency, new IllegalAccessException());}
        if (themes[9].contains(name)){button1 = new Button1(deathTrapFrequency, new IllegalArgumentException());}
        if (themes[10].contains(name)){button1 = new Button1(deathTrapFrequency, new IllegalCallerException());}
        if (themes[11].contains(name)){button1 = new Button1(deathTrapFrequency, new IllegalThreadStateException() );}
        if (themes[12].contains(name)){button1 = new Button1(deathTrapFrequency, new InstantiationException() );}
        if (themes[13].contains(name)){button1 = new Button1(deathTrapFrequency, new LayerInstantiationException() );}
        if (themes[14].contains(name)){button1 = new Button1(deathTrapFrequency, new ReflectiveOperationException());}
        if (themes[15].contains(name)){button1 = new Button1(deathTrapFrequency, new SecurityException());}
        if (themes[16].contains(name)){button1 = new Button1(deathTrapFrequency, new NoSuchMethodException());}
        if (button1 == null){button1 = new Button1("Invalid Name");}
        button1.setStyle("-fx-font-size:35px");
        return button1;
    }



























    public int[] autoSize(int rows, int cols){
        //1430 x 840
        int width = (int) Math.floor(1430.0/rows);
        int height = (int) Math.floor(840.0/cols);
        this.setStyle("-fx-font-size:40px;");
        this.setMinWidth(width);
        this.setMinHeight(height);
        return new int[]{width, height};
    }








}
