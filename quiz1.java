package Voca;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class quiz1 implements ActionListener, KeyListener{ //저장된 단어 파일을 불러와서 영어가 화면에 뜨면 한글을 입력하는 퀴즈 프로그램
	ArrayList<String> word = new ArrayList<String>(); 
	JFrame frame = new JFrame();
	String[] words;
	JTextField textfield1;
	JTextField textfield;
	JLabel label1;
	JLabel label2;
	JButton button1;
	JButton home = new JButton("MAIN HOME");
	FileControl f = new FileControl();
	int i=0;
	int count=0;
	Font font = new Font("고딕", Font.PLAIN, 20);
	public quiz1() {
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu menu = new JMenu("파일");
		menu.addSeparator();
		JMenuItem loadfile = new JMenuItem("불러오기");
		menu.add(loadfile);
		menuBar.add(menu);
		JPanel p1= new JPanel();
		textfield = new JTextField(20);
		textfield.setFont(font);
		textfield.addKeyListener(this);
		button1 = new JButton("확인");
		button1.setFont(font);
		button1.addActionListener(this);
		home.addActionListener(this);
		home.setFont(font);
		label1 = new JLabel();
		label1.setFont(font);
		label2 = new JLabel();
		label2.setFont(font);
		//label1.setAlignment(Label.CENTER);
		//label1.setText(words[0]);
		p1.add(label1);
		p1.add(textfield);
		p1.add(button1);
		p1.add(label2);		
		
		frame.add(p1, BorderLayout.CENTER);
		frame.add(home, BorderLayout.SOUTH);
		frame.setVisible(true);
		frame.setSize(800,400);
		
		 loadfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				word=f.open();
				label1.setText(word.get(0));
				words=word.toArray(new String[word.size()]);
			}
		});
		

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String input;
		if(e.getSource()==button1) {
			if(words[i+1].equals(textfield.getText())) {
				textfield.setText("");
				i+=2;
				label1.setText(words[i]);
				label2.setText("       틀린횟수: "+count);
			}
			else {
				textfield.setText("");
				count++;
				label2.setText("틀림, 틀린횟수: "+count);
			}
		}
		else if(e.getSource()==home) {
			frame.setVisible(false);
			Vocabulary v = new Vocabulary();
			v.setVisible(true);
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			if(words[i+1].equals(textfield.getText())) {
				textfield.setText("");
				i+=2;
				label1.setText(words[i]);
				label2.setText("       틀린횟수: "+count);
			}
			else {
				textfield.setText("");
				count++;
				label2.setText("틀림, 틀린횟수: "+count);
			}
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
