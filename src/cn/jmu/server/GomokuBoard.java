package cn.jmu.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cn.jmu.listener.GomokuListener;
import cn.jmu.util.GomokuConfig;
import cn.jmu.util.GomokuUtil;

@SuppressWarnings("serial")
public class GomokuBoard extends JFrame implements ActionListener{

	public Graphics g;
	public GomokuBoard gomoku;
	private JPanel imagePanel;
	private ImageIcon background;

	public GomokuBoard() {
		this.initUI();	
	}

	public void setBackground() {
		background = new ImageIcon();
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, background.getIconWidth(),
				background.getIconHeight());
		imagePanel = (JPanel) gomoku.getContentPane();
		imagePanel.setOpaque(false);
		imagePanel.setLayout(new FlowLayout());
		gomoku.getLayeredPane().setLayout(null);
		gomoku.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		gomoku.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gomoku.setSize(background.getIconWidth(), background.getIconHeight());
		gomoku.setResizable(false);
		gomoku.setVisible(true);
	}

	public void initUI() {
		this.setTitle("五子棋");
		this.setSize(new Dimension(650, 700));
		this.setResizable(false);
		this.setDefaultCloseOperation(3);
		this.setLocationRelativeTo(null);

		this.setLayout(null);
		JPanel jp = new JPanel() {
			public void paint(Graphics g) {
				g.setColor(Color.BLACK);
				super.paint(g);

				for (int i = 0; i < 15; i++) {
					g.drawLine(20, 20 + i * GomokuConfig.GRID_SIZE, 20
							+ (GomokuConfig.COL - 1) * GomokuConfig.GRID_SIZE,
							20 + i * GomokuConfig.GRID_SIZE);
				}

				for (int i = 0; i < 15; i++) {
					g.drawLine(20 + i * GomokuConfig.GRID_SIZE, 20, 20 + i
							* GomokuConfig.GRID_SIZE, 20
							+ (GomokuConfig.COL - 1) * GomokuConfig.GRID_SIZE);
				}

				g.setColor(Color.BLACK);
				g.fillOval(133, 133, 15, 15);
				g.fillOval(293, 133, 15, 15);
				g.fillOval(453, 133, 15, 15);
				g.fillOval(133, 293, 15, 15);
				g.fillOval(293, 293, 15, 15);
				g.fillOval(453, 293, 15, 15);
				g.fillOval(133, 453, 15, 15);
				g.fillOval(293, 453, 15, 15);
				g.fillOval(453, 453, 15, 15);
				
				GomokuListener.initData();
				
				for (int i = 0; i < 650; i++) {
					for (int j = 0; j < 650; j++) {
						if (GomokuListener.array[i][j] == GomokuUtil.BLACK) {
							g.setColor(Color.BLACK);
							g.fillOval(i, j, GomokuConfig.CHESS_SIZE,
									GomokuConfig.CHESS_SIZE);
						} else if (GomokuListener.array[i][j] == GomokuUtil.WHITE) {
							g.setColor(Color.WHITE);
							g.fillOval(i, j, GomokuConfig.CHESS_SIZE,
									GomokuConfig.CHESS_SIZE);
						}
					}
				}
			}
		};
		jp.setBackground(new Color(209, 167, 78));
		jp.setBounds(10, 10, 602, 602);
		this.add(jp);
		
		this.setLayout(new BorderLayout());
		JPanel pal = new JPanel();
		JButton btn_restart = new JButton("重新开始");
		GomokuUtil.label = new JLabel(GomokuUtil.BLACKTIME);
		JLabel label1 = new JLabel(" ");
		JLabel label2 = new JLabel(" ");
		JButton btn_exit = new JButton("返回菜单");
		btn_restart.setActionCommand(GomokuUtil.EVENT_RESTART);
		btn_exit.setActionCommand(GomokuUtil.EVENT_BACKTOMENU);

		btn_restart.addActionListener(this);
		btn_exit.addActionListener(this);
		
		pal.setLayout(new FlowLayout());
		this.add(pal, BorderLayout.SOUTH);
		pal.add(btn_restart);
		pal.add(label1);
		pal.add(GomokuUtil.label);
		pal.add(label2);
		pal.add(btn_exit);

		this.setVisible(true);
		g = jp.getGraphics();

		GomokuListener listener = new GomokuListener(g);
		jp.addMouseListener(listener);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()){
		case GomokuUtil.EVENT_RESTART:
			GomokuUtil.gomoku.dispose();
			GomokuUtil.gomoku = new GomokuBoard();
			break;
		case GomokuUtil.EVENT_BACKTOMENU:
			GomokuUtil.gomoku.dispose();
			new GomokuBegin();
			break;
		}
	}

}
