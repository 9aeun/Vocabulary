package Voca;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class learn implements ActionListener,KeyListener{
	//Model model = new Model();
	//MenuBar menu = new MenuBar();
	JFrame frame = new JFrame();
	FileControl f = new FileControl();
	ArrayList<String> word = f.open();
	JPanel panel1;
	JPanel panel2;
	JButton bt;
	JButton next;
	JButton before;
	JButton home = new JButton("MAIN HOME");
	int i=0;
	String s = "";
	Font font = new Font("고딕", Font.BOLD, 50);
	public learn() {
		frame.setTitle("단어 학습하기");
		frame.setLayout(new BorderLayout());

		panel1 = new JPanel();
		panel2 = new JPanel();
		bt = new JButton();
		next = new JButton("▷");
		next.addActionListener(this);
		before = new JButton("◁");
		before.addActionListener(this);
		home.addActionListener(this);
		//bt.setText(model.words.get(0));
		bt.setText(word.get(0));
		bt.setBackground(Color.white);
		bt.setFont(font);
		bt.addKeyListener(this); //키보드 눌렸을 때
		panel1.add(bt);
		panel1.setLayout(new CardLayout(10,10));
		panel2.add(before);
		panel2.add(next);
		panel2.add(home);
		frame.add(panel1, BorderLayout.CENTER);
		frame.add(panel2, BorderLayout.SOUTH);
		frame.setVisible(true);
		frame.setSize(800,1000);
		frame.setFocusable(true);
		frame.addKeyListener(this);
	}	


	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()==39) { //오른쪽 눌렀을 때
			bt.setText(word.get(i+1));
			i++;
		}
		if(e.getKeyCode()==37) { //왼쪽 눌렀을 때
			bt.setText(word.get(i-1));
			i--;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==next) {
			if(word.get(i+1)!=null) {
				bt.setText(word.get(i+1));
				i++;
			}
			else {
				bt.setText(word.get(i));
			}
		}
		if(e.getSource()==before) {
			if(word.get(i-1)!=null) {
				bt.setText(word.get(i-1));
				i--;
			}
			else {
				bt.setText(word.get(i));
			}
		}
		else if(e.getSource()==home) {
			frame.setVisible(false);
			Vocabulary v = new Vocabulary();
			v.setVisible(true);
		}
	}

}
