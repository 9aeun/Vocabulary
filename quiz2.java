package Voca;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Collections.*;
import java.util.Random;

class MyButton extends JButton{
	static int count = 0;
	int index;
	public MyButton(String s) {
		super(s); //버튼 내용 설정
		index = count++;
	}
}

public class quiz2 implements ActionListener{ //카드 짝맞추기와 같이 같은 뜻의 한글과 영어를 짝맞추는 
	JFrame frame = new JFrame();
	FileControl f = new FileControl();
	ArrayList<String> word=new ArrayList<String>();
	ArrayList<String> En=new ArrayList<String>();
	ArrayList<String> Ko=new ArrayList<String>();
	MyButton[] buttons;
	MyButton reset;
	JButton temp=null;
	JButton home = new JButton("MAIN HOME");
	ArrayList<String> qcard = new ArrayList<String>();
	int t=0;
	int collectcard=0;
	long start;
	JOptionPane p = new JOptionPane();
	public quiz2() {
		JPanel p1= new JPanel();
		JPanel panel = new JPanel();
		home.addActionListener(this);
		panel.setLayout(new GridLayout(0,4,2,2));
		buttons = new MyButton[16];
		frame.add(panel);
		frame.setSize(500,600);
		frame.setVisible(true);
		word=f.open();
		String[] words = word.toArray(new String[word.size()]);
		for(int i=0; i<word.size(); i+=2) {
			En.add(word.get(i));
			Ko.add(word.get(i+1));
		}
		//랜덤으로 배열하기
		long seed = System.nanoTime();
		Collections.shuffle(En, new Random(seed)); //문자열리스트를 무작위로 섞는 함수, seed를 쓰는 이유: 고정할 수 있기 때문에
		Collections.shuffle(Ko, new Random(seed)); //arraylist를 무작위로 섞는 함수 두 개 같이 쓴 이유: 같은 순서로 섞기 위해
		for(int i=0; i<8; i++) {
			qcard.add(En.get(i));
			qcard.add(Ko.get(i));
		}
		Collections.shuffle(qcard);
		start = System.currentTimeMillis();
		for(int i=0; i<16; i++) { //버튼 만들기
			buttons[i]=new MyButton(qcard.get(i));
			buttons[i].addActionListener(this);
			buttons[i].setBackground(new Color(255,255,255));
			panel.add(buttons[i]);
		}

		panel.add(home);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)e.getSource();
		if(b.getText()=="MAIN HOME") {
			frame.setVisible(false);
			Vocabulary v = new Vocabulary();
			v.setVisible(true);
			return;
		}

		else if(b.getText()!="맞춤") {
			if(temp==null) {
				temp=b;
				temp.setBackground(new Color(255,212,0));
				while(b.getText()!=word.get(t)) {
					t++;
				}
			}
			else {
				if(t%2==0 && b.getText().equals(word.get(t+1))) {
					temp.setText("맞춤");
					b.setText("맞춤");
					b.setBackground(new Color(255,212,0));
					temp=null;
					t=0;
					collectcard+=2;
				}
				else if(t%2==1 && b.getText().equals(word.get(t-1))) {
					temp.setText("맞춤");
					b.setText("맞춤");
					b.setBackground(new Color(255,212,0));
					temp=null;
					t=0;
					collectcard+=2;
				}
				else {
					temp.setBackground(new Color(255,255,255));
					b.setBackground(new Color(255,255,255));
					temp=null;
					t=0;
				}
			}
		}
		
		if(collectcard==16) {
			long end = System.currentTimeMillis();
			if(((end-start)/1000.0)<12) {
				p.showMessageDialog(null, (end-start)/1000.0+"초 걸림" + "\n점수: ★★★");
			}
			else if(((end-start)/1000.0)>=12 && ((end-start)/1000.0)<18) {
				p.showMessageDialog(null, (end-start)/1000.0+"초 걸림" + "\n점수: ★★☆");
			}
			else {
				p.showMessageDialog(null, (end-start)/1000.0+"초 걸림" + "\n점수: ★☆☆");
			}
		}

	}
}
