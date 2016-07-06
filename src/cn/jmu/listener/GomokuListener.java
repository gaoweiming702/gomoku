package cn.jmu.listener;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cn.jmu.server.GomokuResult;
import cn.jmu.util.GomokuConfig;
import cn.jmu.util.GomokuDouble;
import cn.jmu.util.GomokuSingle;
import cn.jmu.util.GomokuUtil;

public class GomokuListener extends MouseAdapter {

	public static int[][] array = new int[700][700];
	public static int[][] array_win = new int[15][15];
	public static int[][] array_pve = new int[15][15];

	public static boolean state = true;

	private Graphics g;
	private int x, y;
	private static GomokuResult result;

	public GomokuListener(Graphics g) {
		this.g = g;
	}

	public void mouseReleased(MouseEvent e) {
		x = correctXY(e.getX());
		y = correctXY(e.getY());
		/*
		 * 判定为人人对战
		 */
		if (GomokuUtil.GameModel == GomokuUtil.DOUBLE) {
			if (!GomokuUtil.gameState) {
				if (x < 582 && x >= 0 && y < 582 && y >= 0) {
					if (state && array[x][y] == GomokuUtil.EMPTY) {
						g.setColor(Color.BLACK);
						g.fillOval(x, y, GomokuConfig.CHESS_SIZE,
								GomokuConfig.CHESS_SIZE);

						array[x][y] = GomokuUtil.BLACK;

						array_win[getXY(y)][getXY(x)] = GomokuUtil.BLACKCHESS;
						state = false;
						GomokuUtil.label.setText(GomokuUtil.WHITETIME);

					} else if (array[x][y] == GomokuUtil.EMPTY) {
						g.setColor(Color.WHITE);
						g.fillOval(x, y, GomokuConfig.CHESS_SIZE,
								GomokuConfig.CHESS_SIZE);

						array[x][y] = GomokuUtil.WHITE;

						array_win[getXY(y)][getXY(x)] = GomokuUtil.WHITECHESS;

						state = true;
						GomokuUtil.label.setText(GomokuUtil.BLACKTIME);
					}

					int theResult = GomokuDouble.Win(getXY(y), getXY(x));

					if (theResult == 1) {
						if(GomokuUtil.balanceBreaker) {
							if(GomokuUtil.balanceBreakerType > 0) {
								result = new GomokuResult(-1 ,GomokuUtil.balanceBreakerType);
								result.initUI();
								return;
							}
						}
						GomokuUtil.gameState = true;
						result = new GomokuResult(1);
						result.initUI();						
					} else if (theResult == -1) {						
						GomokuUtil.gameState = true;
						result = new GomokuResult(-1);
						result.initUI();						
					}
				}
			}

		}

		/*
		 * 人机对战
		 */
		else if (GomokuUtil.GameModel == GomokuUtil.SINGLE) {
			if (!GomokuUtil.gameState) {
				if (x < 582 && x >= 0 && y < 582 && y >= 0
						&& array[x][y] == GomokuUtil.EMPTY) {
					GomokuUtil.gameState = true;
					g.setColor(Color.BLACK);
					g.fillOval(x, y, GomokuConfig.CHESS_SIZE,
							GomokuConfig.CHESS_SIZE);

					array[x][y] = GomokuUtil.playerColor;

					array_win[getXY(x)][getXY(y)] = 1;
					array_pve[getXY(x)][getXY(y)] = 0;
					
					GomokuUtil.label.setText(GomokuUtil.WHITETIME);

					int theResult = GomokuSingle.Win(getXY(x), getXY(y));

					if (theResult == 1) {
						if(GomokuUtil.balanceBreaker) {
							if(GomokuUtil.balanceBreakerType > 0) {
								result = new GomokuResult(-1 ,GomokuUtil.balanceBreakerType);
								result.initUI();
								return;
							}
						}
						result = new GomokuResult(1);
						result.initUI();
					}
					if (theResult == -1) {
						result = new GomokuResult(-1);
						result.initUI();
					}
					
					int X = GomokuSingle.X*40;					
					int Y = GomokuSingle.Y*40;					
					GomokuSingle.initData();

					g.setColor(Color.WHITE);
					g.fillOval(X, Y, GomokuConfig.CHESS_SIZE,
							GomokuConfig.CHESS_SIZE);

					array[X][Y] = GomokuUtil.WHITE;

					array_win[getXY(X)][getXY(Y)] = GomokuUtil.autoColor;
					array_pve[getXY(X)][getXY(Y)] = 0;					

					theResult = GomokuDouble.Win(getXY(X), getXY(Y));

					if (theResult == 1) {
						if(GomokuUtil.balanceBreaker) {
							if(GomokuUtil.balanceBreakerType > 0) {
								result = new GomokuResult(-1 ,GomokuUtil.balanceBreakerType);
								result.initUI();
								return;
							}
						}
						result = new GomokuResult(1);
						result.initUI();
					}
					else if (theResult == -1) {
						result = new GomokuResult(-1);
						result.initUI();
					}
					else					
					    GomokuUtil.gameState = false;
					
					GomokuUtil.label.setText(GomokuUtil.BLACKTIME);
				}

			}
		}

	}/*
	 * 下棋位置坐标修正的方法
	 */

	public int correctXY(int x) {
		x = x / 40;
		return x * 40;
	}

	public int getXY(int x) {
		x = x / 40;
		return x;
	}

	public static void initData() {
		state = true;
		GomokuUtil.balanceBreakerType = 0;
		GomokuUtil.clearArray(array);
		GomokuUtil.clearArray(array_win);
		GomokuUtil.clearArray(array_pve);
	}

}
