package cn.jmu.util;

import cn.jmu.listener.GomokuListener;

public class GomokuDouble {

	/*
	 * ��Ӯ����
	 */

	/*
	 * �ж������������
	 */
	public static boolean winRow(int row, int col) {
		int count = 1;
		for (int i = col + 1; i < 15; i++) {// ���Ҳ���
			if (GomokuListener.array_win[row][col] == GomokuListener.array_win[row][i]) {
				count++;
			} else
				break;
		}
		if ((col + count) < 5) {
			return false;
		}
		for (int i = col - 1; i >= 0; i--) {// �������
			if (GomokuListener.array_win[row][col] == GomokuListener.array_win[row][i]) {
				count++;
			} else
				break;
		}

		if (count >= 5) {
			if (count > 5 && GomokuUtil.balanceBreaker 
					&& GomokuListener.array_win[row][col] == GomokuUtil.BLACKCHESS) {
				GomokuUtil.balanceBreakerType = 1;
			}
			return true;
		} else
			return false;
	}

	/*
	 * �ж������������
	 */
	public static boolean winColumn(int row, int col) {
		int count = 1;
		for (int i = row + 1; i < 15; i++) {// ���²���
			if (GomokuListener.array_win[row][col] == GomokuListener.array_win[i][col]) {
				count++;
			} else
				break;
		}
		if ((row + count) < 5) {
			return false;
		}
		for (int i = row - 1; i >= 0; i--) {// ���ϲ���
			if (GomokuListener.array_win[row][col] == GomokuListener.array_win[i][col]) {
				count++;
			} else
				break;
		}
		if (count >= 5) {
			return true;
		} else
			return false;
	}

	/*
	 * �ж�б�������������
	 */
	public static boolean winRightDown(int row, int col) {
		int count = 1;
		if ((col <= 3 && row >= (col + 11)) || (row <= 3 && col >= (row + 11))) {
			return false;
		}
		for (int i = col + 1, j = row + 1; i < 15 && j < 15; i++, j++) {// ���Ҳ���
			if (GomokuListener.array_win[row][col] == GomokuListener.array_win[j][i]) {
				count++;
			} else
				break;
		}
		if ((col + count) < 5 || (row + count) < 5) {
			return false;
		}
		for (int i = col - 1, j = row - 1; i >= 0 && j >= 0; i--, j--) {// �������
			if (GomokuListener.array_win[row][col] == GomokuListener.array_win[j][i]) {
				count++;
			} else
				break;
		}
		if (count >= 5) {
			return true;
		} else
			return false;
	}

	/*
	 * �ж�б�������������
	 */
	public static boolean winLeftDown(int row, int col) {
		int count = 1;
		if ((row + col) <= 3 || (row + col) >= 25) {
			return false;
		}
		for (int i = col - 1, j = row + 1; i >= 0 && j < 15; i--, j++) {// �������
			if (GomokuListener.array_win[row][col] == GomokuListener.array_win[j][i]) {
				count++;
			} else
				break;
		}
		if ((row + count) < 5 || (14 - col + count) < 5) {
			return false;
		}
		for (int i = col + 1, j = row - 1; i < 15 && j >= 0; i++, j--) {// ���Ҳ���
			if (GomokuListener.array_win[row][col] == GomokuListener.array_win[j][i]) {
				count++;
			} else
				break;
		}
		if (count >= 5) {
			return true;
		} else
			return false;
	}

	public static int Win(int row, int col) {
		if (winRow(row, col) || winColumn(row, col) || winRightDown(row, col)
				|| winLeftDown(row, col)) {
			if (GomokuListener.array_win[row][col] == 1)
				return 1;
			else if (GomokuListener.array_win[row][col] == -1)
				return -1;
		}
		return 0;
	}
}
