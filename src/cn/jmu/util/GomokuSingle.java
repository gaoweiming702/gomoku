package cn.jmu.util;

import cn.jmu.listener.GomokuListener;

public class GomokuSingle {

	private static int x1, y1, x2, y2;
	private static int[] chessLevel = new int[225];
	private static int[] chessX = new int[225];
	private static int[] chessY = new int[225];
	private static int num = 0;
	
	public static int X = 7, Y = 7;
	/*
	 * 判赢方法
	 */

	/*
	 * 判定横向五个相连
	 */
	
	public static void initData() {
		chessLevel = new int[225];
		chessX = new int[225];
		chessY = new int[225];
		num = 0;
		X = Y = 7;
	}

	public static void winRow(int row, int col) {
		int count = 1;
		int type = 0;
		int emptyNum = 0;
		for (int i = col + 1; i < 15; i++) {// 向右查找
			if (GomokuListener.array_win[row][col] == GomokuListener.array_win[row][i]) {
				count++;
			} else if(GomokuListener.array_win[row][i] == GomokuUtil.EMPTY) {
				emptyNum++;
				if(emptyNum == 1)
				    type = changeType(row,i,type,true);
				if(emptyNum == 2)
				    break;
			} else
				break;
		}
		if ((col + count) < 5) {
			return;
		}
		emptyNum = 0;
		for (int i = col - 1; i >= 0; i--) {// 向左查找
			if (GomokuListener.array_win[row][col] == GomokuListener.array_win[row][i]) {
				count++;
			} else if(GomokuListener.array_win[row][i] == GomokuUtil.EMPTY) {
				emptyNum++;
				if(emptyNum == 1)
				    type = changeType(row,i,type,false);
				if(emptyNum == 2)
				    break;
			}  else
				break;
		}
		if(type != 0)
			countLevel(type ,count ,GomokuListener.array_win[row][col]);		
	}

	/*
	 * 判定竖向五个相连
	 */
	public static void winColumn(int row, int col) {
		int count = 1;
		int type = 0;
		int emptyNum = 0;
		for (int i = row + 1; i < 15; i++) {// 向下查找
			if (GomokuListener.array_win[row][col] == GomokuListener.array_win[i][col]) {
				count++;
			} else if(GomokuListener.array_win[i][col] == GomokuUtil.EMPTY) {
				emptyNum++;
				if(emptyNum == 1)
				    type = changeType(i,col,type,true);
				if(emptyNum == 2)
				    break;
			} else
				break;
		}
		if ((row + count) < 5) {
			return;
		}
		emptyNum = 0;
		for (int i = row - 1; i >= 0; i--) {// 向上查找
			if (GomokuListener.array_win[row][col] == GomokuListener.array_win[i][col]) {
				count++;
			} else if(GomokuListener.array_win[i][col] == GomokuUtil.EMPTY) {
				emptyNum++;
				if(emptyNum == 1)
				    type = changeType(i,col,type,false);
				if(emptyNum == 2)
				    break;
			} else
				break;
		}
		if(type != 0)
			countLevel(type ,count ,GomokuListener.array_win[row][col]);

	}

	/*
	 * 判定斜向右下五个相连
	 */
	public static void winRightDown(int row, int col) {
		int count = 1;
		int type = 0;
		int emptyNum = 0;
		if ((col <= 3 && row >= (col + 11)) || (row <= 3 && col >= (row + 11))) {
			return;
		}
		for (int i = col + 1, j = row + 1; i < 15 && j < 15; i++, j++) {// 向右查找
			if (GomokuListener.array_win[row][col] == GomokuListener.array_win[j][i]) {
				count++;
			} else if(GomokuListener.array_win[j][i] == GomokuUtil.EMPTY) {
				emptyNum++;
				if(emptyNum == 1)
				    type = changeType(j,i,type,true);
				if(emptyNum == 2)
				    break;
			} else
				break;
		}
		if ((col + count) < 5 || (row + count) < 5) {
			return;
		}
		emptyNum = 0;
		for (int i = col - 1, j = row - 1; i >= 0 && j >= 0; i--, j--) {// 向左查找
			if (GomokuListener.array_win[row][col] == GomokuListener.array_win[j][i]) {
				count++;
			} else if(GomokuListener.array_win[j][i] == GomokuUtil.EMPTY) {
				emptyNum++;
				if(emptyNum == 1)
				    type = changeType(j,i,type,false);				
				if(emptyNum == 2)
				    break;
			} else
				break;
		}
		if(type != 0)
			countLevel(type ,count ,GomokuListener.array_win[row][col]);
	}

	/*
	 * 判定斜向左下五个相连
	 */
	public static void winLeftDown(int row, int col) {
		int count = 1;
		int type = 0;
		int emptyNum = 0;
		if ((row + col) <= 3 || (row + col) >= 25) {
			return;
		}
		for (int i = col - 1, j = row + 1; i >= 0 && j < 15; i--, j++) {// 向右查找
			if (GomokuListener.array_win[row][col] == GomokuListener.array_win[j][i]) {
				count++;
			} else if(GomokuListener.array_win[j][i] == GomokuUtil.EMPTY) {
				emptyNum++;
				if(emptyNum == 1)
				    type = changeType(j,i,type,true);
				if(emptyNum == 2)
				    break;
			} else
				break;
		}
		if ((14 - row + count) < 5 || (col + count) < 5) {
			return;
		}
		emptyNum = 0;
		for (int i = col + 1, j = row - 1; i < 15 && j >= 0; i++, j--) {// 向左查找
			if (GomokuListener.array_win[row][col] == GomokuListener.array_win[j][i]) {
				count++;
			} else if(GomokuListener.array_win[j][i] == GomokuUtil.EMPTY) {
				emptyNum++;
				if(emptyNum == 1)
				    type = changeType(j,i,type,false);
				if(emptyNum == 2)
				    break;
			} else
				break;
		}
		if(type != 0)
			countLevel(type ,count ,GomokuListener.array_win[row][col]);
	}

	public static int Win(int row, int col) {
		//System.out.print("扫描棋子：");
		if (GomokuDouble.winRow(row, col) || GomokuDouble.winColumn(row, col) || GomokuDouble.winRightDown(row, col)
				|| GomokuDouble.winLeftDown(row, col)) {
			if (GomokuListener.array_win[row][col] == 1)
				return 1;
			else if (GomokuListener.array_win[row][col] == -1)
				return -1;
		}
		
		for(int i = 0;i < 15;i++) {
			for(int j = 0;j < 15;j++) {
				if(GomokuListener.array_win[i][j] != GomokuUtil.EMPTY) {
					//System.out.print("("+i+","+j+")");
					winRow(i, j);
					winColumn(i, j);
					winRightDown(i, j);
					winLeftDown(i, j);
				}		
			}
		}
		//System.out.print("\n");
		int chess = getChess();
		
		if(chess != -1) {
			X = chessX[chess];
			Y = chessY[chess];
		}
		return 0;
	}
	
	public static int changeType(int row ,int col ,int type ,boolean isLeft) {
		if(isLeft) {
			x1 = row;
			y1 = col;
			type = 1;
		}
		else {
			x2 = row;
			y2 = col;
			if(type == 1)
				type = 3;
			else
				type = 2;
		}
		return type;		
	}
	
	public static int getChess() {
		int[] maxChess = new int[225];
		int n = 0;
		int maxLevel = 0;
		//System.out.print("选择棋子：");
		for(int i = 0;i < num;i++) {
			//System.out.print("("+chessX[i]+","+chessY[i]+","+chessLevel[i]+")");
			if(chessLevel[i] > maxLevel) {
				maxLevel = chessLevel[i];
				maxChess[0] = i;
				n = 1;
			}
			else if(chessLevel[i] == maxLevel) {
				maxChess[n] = i;
				n++;
			}
		}
		//System.out.print("\n");
		if(n != 0) {
			int x=(int)(Math.random()*(n-1));
			//System.out.println("选中棋子:("+chessX[maxChess[x]]+","+chessY[maxChess[x]]+","+chessLevel[maxChess[x]]+")");
			return maxChess[x];
		}
		return -1;
	}
	
	public static void countLevel(int type ,int count ,int chessColor) {
		if(type == 1 || type == 3) {
			if(GomokuListener.array_win[x1][y1] != GomokuUtil.EMPTY)
				return;
		}
		if(type == 2 || type == 3) {
			if(GomokuListener.array_win[x2][y2] != GomokuUtil.EMPTY)
				return;
		}
		int chess1 = -1;
		int chess2 = -1;
		int[] founded = new int[2];
		if(type == 3) {
			for(int i = 0;i < num;i++) {
				if(chessX[i] == x1 && chessY[i] == y1) {
					chess1 = i;
					founded[0] = 1;
				}
				if(chessX[i] == x1 && chessY[i] == y1) {
					chess2 = i;
					founded[1] = 1;
				}
			}
			if(founded[0] != 1) {
				chess1 = num;
				num++;
				chessX[chess1] = x1;
				chessY[chess1] = y1;
			}
			if(founded[1] != 1) {
				chess2 = num;
				num++;	
				chessX[chess2] = x2;
				chessY[chess2] = y2;
			}
			if(count == 4) {
				if(chessColor == -1) {
					chessLevel[chess1]++;
					chessLevel[chess2]++;
				}
				chessLevel[chess1] = chessLevel[chess1] + 7;
				chessLevel[chess2] = chessLevel[chess2] + 7;
			}
			else if(count == 3) {
				chessLevel[chess1] = chessLevel[chess1] + 4;
				chessLevel[chess2] = chessLevel[chess2] + 4;
			}
			else if(count == 2) {
				chessLevel[chess1] = chessLevel[chess1] + 3;
				chessLevel[chess2] = chessLevel[chess2] + 3;
			}
			else if(count == 1){
				chessLevel[chess1] = chessLevel[chess1] + 1;
				chessLevel[chess2] = chessLevel[chess2] + 1;
			}
		}
		else if(type == 1) {
			for(int i = 0;i < num;i++) {
				if(chessX[i] == x1 && chessY[i] == y1) {
					chess1 = i;
					founded[0] = 1;
				}
			}
			if(founded[0] != 1) {
				chess1 = num;
				num++;
				chessX[chess1] = x1;
				chessY[chess1] = y1;
			}
			if(count == 4) {
				//System.out.print("(#("+x1+""+y1+")level+4)");
				if(chessColor == -1) {
					chessLevel[chess1]++;
				}
				chessLevel[chess1] = chessLevel[chess1] + 7;
			}
			else if(count == 3) {
				chessLevel[chess1] = chessLevel[chess1] + 2;
			}
			else if(count == 2) {
				chessLevel[chess1] = chessLevel[chess1] + 1;
			}
			else if(count == 1){
				chessLevel[chess1] = chessLevel[chess1] + 1;
			}
		}
		else if(type == 2) {
			for(int i = 0;i < num;i++) {
				if(chessX[i] == x2 && chessY[i] == y2) {
					chess2 = i;
					founded[1] = 1;
				}
			}
			if(founded[1] != 1) {
				chess2 = num;
				num++;				
				chessX[chess2] = x2;
				chessY[chess2] = y2;
			}
			if(count == 4) {
				//System.out.print("(#("+x2+""+y2+")level+4)");
				if(chessColor == -1) {
					chessLevel[chess2]++;
				}
				chessLevel[chess2] = chessLevel[chess2] + 7;
			}
			else if(count == 3) {
				chessLevel[chess2] = chessLevel[chess2] + 2;
			}
			else if(count == 2) {
				chessLevel[chess2] = chessLevel[chess2] + 1;
			}
			else if(count == 1){
				chessLevel[chess2] = chessLevel[chess2] + 1;
			}
		}
	}
}
