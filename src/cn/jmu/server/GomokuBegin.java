package cn.jmu.server;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cn.jmu.util.GomokuUtil;

@SuppressWarnings("serial")
public class GomokuBegin extends JFrame implements ActionListener {

	public GomokuBegin() {
		this.initUI();
	}

	public void initUI() {
		this.setTitle("五子棋");		
		try {
			Image image = ImageIO.read(this.getClass().getResource(GomokuUtil.TITLE));
			this.setIconImage(image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}               
		this.setSize(new Dimension(400, 200));
		ImageIcon background=new ImageIcon(GomokuUtil.BEGIN_BACKGROUND);
		JLabel label = new JLabel(background); 
		label.setBounds(0, 0, this.getWidth(), this.getHeight());   
        this.setBounds( 0, 0, 400, 200); 
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        
		JButton btn_single = new JButton("单人游戏");
		JButton btn_double = new JButton("双人游戏");
		JButton btn_exit = new JButton("退出游戏");
		btn_single.setActionCommand(GomokuUtil.EVENT_SINGLE);
		btn_double.setActionCommand(GomokuUtil.EVENT_DOUBLE);
		btn_exit.setActionCommand(GomokuUtil.EVENT_EXIT);
		btn_single.addActionListener(this);
		btn_double.addActionListener(this);
		btn_exit.addActionListener(this);
		
		JPanel jpanel = new JPanel();		
		BoxLayout boxLayout = new BoxLayout(jpanel, BoxLayout.Y_AXIS);
		jpanel.setLayout(boxLayout);
		JLabel label1 = new JLabel(" ");
		JLabel label2 = new JLabel(" ");
		JLabel label3 = new JLabel(" ");
		jpanel.add(label1);
		jpanel.add(btn_single);
		jpanel.add(label2);
		jpanel.add(btn_double);
		jpanel.add(label3);
		jpanel.add(btn_exit);	
		jpanel.setOpaque(false);
		this.add(jpanel,BorderLayout.CENTER);
		JPanel imagePanel = (JPanel) this.getContentPane();  
        imagePanel.setOpaque(false);
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE)); 
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		case GomokuUtil.EVENT_SINGLE:
			GomokuUtil.GameModel = GomokuUtil.SINGLE;
			this.dispose();
			GomokuUtil.gomoku = new GomokuBoard();
			break;
		case GomokuUtil.EVENT_DOUBLE:
			GomokuUtil.GameModel = GomokuUtil.DOUBLE;
			this.dispose();
			GomokuUtil.gomoku = new GomokuBoard();
			break;
		case GomokuUtil.EVENT_EXIT:
			System.exit(0);
			break;
		}
	}

}
