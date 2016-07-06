package cn.jmu.util;

import javax.swing.JLabel;

import cn.jmu.server.GomokuBegin;
import cn.jmu.server.GomokuBoard;

public class GomokuUtil {
	
	public static int GameModel = GomokuUtil.DOUBLE;
	public static boolean balanceBreaker = false;
	public static boolean gameState = false;
	public static int balanceBreakerType = 0;
	public static int playerColor = GomokuUtil.BLACKCHESS;
	public static int autoColor = GomokuUtil.WHITECHESS;
	public static GomokuBoard gomoku;
	public static GomokuBegin begin;
	public static JLabel label;
	
	public static final String TITLE = "/img/title.png";
	public static final String BEGIN_BACKGROUND  = "src/img/beginbackground.png";
	
    public static final int BLACKCHESS = 1;
    public static final int WHITECHESS = -1;
    public static final int EMPTY = 0;
    public static final int BLACK = 1;
    public static final int WHITE = 2;
    public static final int SINGLE = 1;
    public static final int DOUBLE = 2;
    
    public static final String EVENT_SINGLE = "single";
    public static final String EVENT_DOUBLE = "double";
    public static final String EVENT_RESTART = "restart";
    public static final String EVENT_BACKTOMENU = "backToMenu";
    public static final String EVENT_EXIT = "exit";
    
    public static final String BLACKTIME = "黑棋回合";
    public static final String WHITETIME = "白棋回合";
    
    public static int[][] clearArray(int[][] array) {
		for(int m = 0;m < array.length;m++) {
			for(int n = 0;n < array[m].length;n++) {
				array[m][n] = EMPTY;
			}
		}
    	return array;
    }
    
}
