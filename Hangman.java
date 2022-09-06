package Voca;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.*;
import java.io.*;

public class HangMan implements KeyListener, ActionListener{
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JPanel drawpanel = new JPanel();
	int hiddenChar; // 빈칸 개수
	StringBuffer hiddenWord; // 빈칸뚫은 단어 저장
	String newWord; // 선출된 랜덤 단어.
	Scanner scanner;
	int failCount; // 틀린 횟수
	String failAnswer; //틀린 영어
	FileControl f = new FileControl();
	ArrayList<String> words = new ArrayList<String>();
	ArrayList<String> Eng = new ArrayList<String>();
	ArrayList<String> w = new ArrayList<String>();
	char[] nword;
	char hword[];
	JTextField tf = new JTextField(20);
	JLabel label = new JLabel();
	JLabel label2 = new JLabel();
	JOptionPane p = new JOptionPane();
	JButton Hbutton = new JButton("MAIN HOME");

	public HangMan(){
		
		game();
		panel.add(Hbutton);
		panel.add(label);
		panel.add(tf);
		panel.add(label2);
		panel.setBounds(0, 0, 800, 200);

		Hbutton.addActionListener(this);
		drawpanel = new DrawPanel();
		drawpanel.setBounds(0, 200, 700, 500);
		drawpanel.setLayout(null);
		drawpanel.setBorder(BorderFactory.createLineBorder(Color.black));
		tf.addKeyListener(this);
		frame.setVisible(true);
		frame.add(panel);
		frame.add(drawpanel);
		frame.setLayout(null);
		
		frame.setSize(800,1000);
		frame.setFocusable(true);

		
	}
	
	public void game() {
		words=f.open();
		failAnswer="틀린 알파벳: ";
		failCount=0;
		for(int i=0; i<words.size(); i+=2) {
			Eng.add(words.get(i));
		}
		System.out.println(Eng);
		Random r = new Random();
		newWord = Eng.get(r.nextInt(Eng.size()));
		hiddenChar = newWord.length(); //빈칸개수 설정
		nword=newWord.toCharArray();
		hword = new char[hiddenChar];
		String hh=""; //라벨에 넣을 문자열
		for(int i=0; i<hiddenChar; i++) {
			hword[i]='_';
			hh+=hword[i]+" ";
		}
		
		for(int i=0; i<hiddenChar; i++)
		label.setText(hh);
		label2.setText(failAnswer);
		
	}
	
	public static void main(String[] args) {
		new HangMan();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		char key = e.getKeyChar();
		boolean hit = false;
		String hh="";
		for(int i=0; i<newWord.length(); i++) {
			if(nword[i]==key) {
				hword[i]=nword[i];
				hit=true;
			}
			hh+=hword[i]+" ";
			label.setText(hh);
			tf.setText("");

		}
		if(!hit){
			failCount++;
			failAnswer+=key+" ";
			label2.setText(failAnswer);
			tf.setText("");
			drawpanel.repaint();
		}
		if(isEnd()==true) {
			int result = p.showConfirmDialog(null, "성공! 게임을 더 하시겠습니까?", "Confiem", JOptionPane.YES_NO_OPTION);
			if(result==p.CLOSED_OPTION) {
				Vocabulary v = new Vocabulary();
				v.setVisible(true);
				frame.dispose();
			}
			else if(result == p.YES_OPTION) {
				new HangMan();
				
			}
			else if(result == p.NO_OPTION) {
				Vocabulary v = new Vocabulary();
				v.setVisible(true);
				frame.dispose();
			}
		}
		else if(failCount == 5) {
			int result = p.showConfirmDialog(null, "실패! 게임을 더 하시겠습니까?", "Confiem", JOptionPane.YES_NO_OPTION);
			if(result==p.CLOSED_OPTION) {
				Vocabulary v = new Vocabulary();
				v.setVisible(true);
				frame.dispose();
			}
			else if(result == p.YES_OPTION) {
				new HangMan();
			}
			else if(result == p.NO_OPTION) {
				Vocabulary v = new Vocabulary();
				v.setVisible(true);
				frame.dispose();
			}
		}
	}
	
	private boolean isEnd() {
		for(int i=0; i<newWord.length(); i++) {
			if(hword[i]=='_')
				return false;
		}
		return true;
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	class DrawPanel extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawLine(100,100,500,100);
			g.drawLine(100,100,100,600);
			g.drawLine(50,600,250,600);
			g.drawLine(500,100,500,200);

			switch(failCount) {
			case 1: //1번 틀렸을 때
				g.drawOval(450, 200, 100, 100);
				break;
			case 2: //2번 틀렸을 때
				g.drawOval(450, 200, 100, 100);
				g.drawLine(500,300,500,450);
				break;
			case 3: //3번 틀렸을 때
				g.drawOval(450, 200, 100, 100);
				g.drawLine(500,300,500,450);
				g.drawLine(430, 300, 500, 350);
				g.drawLine(500, 350, 570, 300);
				break;
			case 4: //4번 틀렸을 때
				g.drawOval(450, 200, 100, 100);
				g.drawLine(500,300,500,450);
				g.drawLine(430, 300, 500, 350);
				g.drawLine(500, 350, 570, 300);
				g.drawLine(430, 510, 500, 450);
				break;
			case 5: //5번 틀렸을 때
				g.drawOval(450, 200, 100, 100);
				g.drawLine(500,300,500,450);
				g.drawLine(430, 300, 500, 350);
				g.drawLine(500, 350, 570, 300);
				g.drawLine(430, 510, 500, 450);
				g.drawLine(500, 450, 570, 510);
				break;
			}
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Hbutton) {
			frame.setVisible(false);
			Vocabulary v = new Vocabulary();
			v.setVisible(true);
		}
		
	}

}
