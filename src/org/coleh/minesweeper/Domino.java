package org.coleh.minesweeper;

public class Domino {
    private String icon;
    private int SymbolicInt;
    private int LiteralInt;
    private final String[] icons = icons();
    private final int[] SymbolicPossibilities =/*I'm not a n00b I swear;
             I wrote a method to compute these but the method having served its purpose,
              it was faster to println this and make it a concrete entity instead of recomputing every time.
              */ {0, 1, 2, 3, 4, 5, 6, 10, 11, 12, 13, 14, 15, 16, 20, 21, 22, 23, 24, 25, 26, 30, 31, 32, 33, 34, 35, 36, 40, 41, 42, 43, 44, 45, 46, 50, 51, 52, 53, 54, 55, 56, 60, 61, 62, 63, 64, 65, 66};

    public Domino(int SymbolicInt){
        this.SymbolicInt = SymbolicInt;
        this.icon = computeIcon();
        this.LiteralInt = computeLiteralInt();
    }
    /**
     * If you have the domino 22, you have the domino :|:
     * 22 is its SymbolicInt
     * 4 is its LiteralInt - the sum of its digits.
     * @return please read the code. Code is more concise and less ambiguous than English.
     */
    private int computeLiteralInt(){
        int one, two = 0;
        one = Integer.parseInt(String.valueOf(String.valueOf(this.SymbolicInt).charAt(0)));
        if (String.valueOf(SymbolicInt).length() > 1) {two = Integer.parseInt(String.valueOf(String.valueOf(this.SymbolicInt).charAt(1)));}
        return one + two;
    }
    private String computeIcon(){
        String icon = "";
        for (int i = 0; i < SymbolicPossibilities.length; i++) {if (this.SymbolicInt == SymbolicPossibilities[i]){icon = icons[i]; break;}}
        return icon;
    }
    /*
    green) the SymbolicInt cannot be computed when given only the LiteralInt.
    green) for example, the LiteralInt 6 corresponds to the SymbolicInts 6, 15, 24, 33, 42, 51, 60.
    */
    /**
     * @return all the possible horizontal dominoes.
     * I use this simply to get the annoyingly long array out of my way with code folding,
     * which regrettably is not made easy for String[]s
     * */
    private String[] icons(){
        return new String[]{
                "🀱",
                "🀲",
                "🀳",
                "🀴",
                "🀵",
                "🀶",
                "🀷",
                "🀸",
                "🀹",
                "🀺",
                "🀻",
                "🀼",
                "🀽",
                "🀾",
                "🀿",
                "🁀",
                "🁁",
                "🁂",
                "🁃",
                "🁄",
                "🁅",
                "🁆",
                "🁇",
                "🁈",
                "🁉",
                "🁊",
                "🁋",
                "🁌",
                "🁍",
                "🁎",
                "🁏",
                "🁐",
                "🁑",
                "🁒",
                "🁓",
                "🁔",
                "🁕",
                "🁖",
                "🁗",
                "🁘",
                "🁙",
                "🁚",
                "🁛",
                "🁜",
                "🁝",
                "🁞",
                "🁟",
                "🁠",
                "🁡"
        };
    }
    public String getIcon(){return icon;}
    public int getSymbolicInt(){return SymbolicInt;}
    public int getLiteralInt(){return LiteralInt;}
}

