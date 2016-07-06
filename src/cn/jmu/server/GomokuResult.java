package cn.jmu.server;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cn.jmu.util.GomokuUtil;

@SuppressWarnings("serial")
public class GomokuResult extends JFrame implements ActionListener{
	private int i;
	@SuppressWarnings("unused")
	private int balanceBreakerType;

	public GomokuResult(int i) {
		this.i = i;
	}
	
	public GomokuResult(int i,int balanceBreakerType) {
		this.i = i;
		this.balanceBreakerType = balanceBreakerType;
	}

	public void initUI() {
		this.setTitle("������");
		this.setSize(new Dimension(400, 200));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		this.add(panel, BorderLayout.CENTER);
		if (i == 1) {
			JLabel lab = new JLabel("��������������ʤ��");
			panel.add(lab, BorderLayout.CENTER);

		} else if (i == -1) {
			JLabel lab = new JLabel("��������������ʤ��");
			panel.add(lab);
		}

		JPanel pal = new JPanel();
		JButton btn_restart = new JButton("���¿�ʼ");
		JButton btn_exit = new JButton("�˳���Ϸ");
		btn_restart.setActionCommand(GomokuUtil.EVENT_RESTART);
		btn_exit.setActionCommand(GomokuUtil.EVENT_EXIT);

		btn_restart.addActionListener(this);
		btn_exit.addActionListener(this);
		
		pal.setLayout(new FlowLayout());
		this.add(pal, BorderLayout.SOUTH);
		pal.add(btn_restart);
		pal.add(btn_exit);

		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()) {
		case GomokuUtil.EVENT_RESTART:
			GomokuUtil.gameState=false;
			this.dispose();
			GomokuUtil.gomoku.dispose();
			GomokuUtil.gomoku = new GomokuBoard();
			break;
		case GomokuUtil.EVENT_EXIT:
			System.exit(0);
			break;
		}
	}
}
