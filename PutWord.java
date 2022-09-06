package Voca;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.event.*;
import java.util.ArrayList;

public class PutWord implements ActionListener, KeyListener{ //단어를 입력하고 파일로 저장하는 class
	JFrame frame = new JFrame();
	ArrayList<String> word = new ArrayList<String>(); 
	FileControl f = new FileControl();
	JButton pbutton = new JButton("Plus"); // 단어 추가 버튼
	JButton dbutton = new JButton("Delete"); // 단어 삭제 버튼
	JButton sbutton = new JButton("Save"); //현재까지 입력된 단어를 파일에 저장하는 버튼
	JButton lbutton = new JButton("Loding"); //다른 파일에 저장된 단어 불러오는 버튼
	JButton Hbutton = new JButton("MAIN HOME"); //메인 홈으로 가는 버튼
	JTextField etext = new JTextField(20); // 영어 입력 텍스트필드
	JTextField ktext = new JTextField(20); // 한글 입력 텍스트필드
	JTextField dtext = new JTextField(20); //삭제 할 단어 번호 입력하는 텍스트필드
	JTextArea showtext = new JTextArea(30,30); // 저장된 내용을 보여주는 함수
	JScrollPane scrollPane = new JScrollPane(showtext);
	JLabel label1 = new JLabel("영어 :");
	JLabel label2 = new JLabel("한글 :");
	JMenuBar menu = new JMenuBar();
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	int j=0; //ActionEvent if문에 사용하는 변수
	Font font = new Font("고딕", Font.BOLD, 20);
	
	public PutWord(){
		frame.setTitle("단어 입력하기");
		
		showtext.setLineWrap(true);
		pbutton.addActionListener(this);
		dbutton.addActionListener(this);
		sbutton.addActionListener(this);
		lbutton.addActionListener(this);
		Hbutton.addActionListener(this);
		etext.addKeyListener(this);
		ktext.addKeyListener(this);
		
		p1.add(label1);
		p1.add(etext);
		p1.add(label2);
		p1.add(ktext);
		p1.add(pbutton);
		p1.add(dbutton);
		p1.setPreferredSize(new Dimension(250, 50)); //setSize와 다르게 layout에 영향을 받지 않음
		
		p2.add(showtext);
		p2.add(lbutton);
		p2.add(sbutton);
		p2.setPreferredSize(new Dimension(450, 100));
		
		p3.add(Hbutton, BorderLayout.EAST);
		
		frame.add(p1, BorderLayout.CENTER);
		frame.add(p2, BorderLayout.EAST);
		frame.add(p3, BorderLayout.SOUTH);
		frame.requestFocus();
		frame.setFocusable(true);
		frame.setVisible(true);
		frame.setSize(700,500);		
	}
	
	public void actionPerformed(ActionEvent e) {
		if((e.getSource()==pbutton)&&(etext.getText()!=null)&&(ktext.getText()!=null)) { //단어 입력하고, 입력한 단어 보여주기
			String input="";
			word.add(etext.getText());
			word.add(ktext.getText());
			for(int i=0; i<word.size(); i++)
				input+=word.get(i)+"\n";
			showtext.setText(input);
			etext.setText("");
			ktext.setText("");
		}
		else if(e.getSource() == dbutton) {  // 단어 삭제하기
			if(dtext.getText()!=null) {
				String input="";
				for(int j=0; j<word.size(); j++) {
					String item = word.get(j);
					if(item.equals(dtext.getText())) {
						word.remove(j);
						word.remove(j);
					}
				}
				for(int i=0; i<word.size(); i++)
					input+=word.get(i)+"\n";
				showtext.setText(input);
				dtext.setText("");
			}
		}
		else if(e.getSource() == sbutton) { //현재까지 입력된 단어 파일에 저장
			f.save(word);
		}
		else if(e.getSource()==lbutton) {
			String input="";
			word=f.open();
			for(int i=0; i<word.size(); i++)
				input+=word.get(i)+"\n";
			showtext.setText(input);
		}
		
		else if(e.getSource()==Hbutton) {
			Vocabulary v = new Vocabulary();
			frame.setVisible(false);
			v.setVisible(true);
		}
	}
	
	public void keyTyped(KeyEvent k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent k) {
		// TODO Auto-generated method stub
		if(k.getKeyCode()==KeyEvent.VK_ENTER) {
			if((etext.getText()!=null)&&(ktext.getText()!=null)) {
				String input="";
				word.add(etext.getText());
				word.add(ktext.getText());
				for(int i=0; i<word.size(); i++)
					input+=word.get(i)+"\n";
				showtext.setText(input);
				etext.setText("");
				ktext.setText("");
			};
		}
	}

	@Override
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub
		
	}
	

}
