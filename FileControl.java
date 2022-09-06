package Voca;
import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.*;

import java.awt.event.*;

public class FileControl extends JFrame{
	public ArrayList<String> open() {
		ArrayList<String> words = new ArrayList<String>(); 
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("파일 불러오기");
		fileChooser.setFileFilter(new FileNameExtensionFilter("txt", "txt")); //txt파일만 보여줄 수 있도록
		fileChooser.setMultiSelectionEnabled(false); //다중 선택 불가
		int i=0;
		if(fileChooser.showOpenDialog(fileChooser)==JFileChooser.APPROVE_OPTION) { //선택한 파일의 경로가 맞는지
			//showOpenDialog는 파일을 읽기 위해 사용되는 네이티브 다이얼로그
			try {
				File inFile = new File(fileChooser.getSelectedFile().toString()); //선택한 파일의 경로를 받아 새로운 파일로 저장
				
				FileInputStream fis = new FileInputStream(inFile.getPath()); //선택한 파일의 경로 받아 읽을 파일을 저장한다.
				//file객체가 가리키는 파일을 바이트스트림으로 읽기 위함
				FileReader filereader = new FileReader(inFile);
				InputStreamReader reader = new InputStreamReader(fis, "UTF-8"); //파일을 UTF-8로 읽게 하기 위해
				BufferedReader bufReader = new BufferedReader(reader);//읽기속도 향상을 위해 bufferedreader를 사용
				String line ="";
				while((line = bufReader.readLine())!=null) {//한줄씩 읽기
					words.add(line);
					i++;
				}
			}catch(Exception e) { //오류발생
				e.printStackTrace(); //경고표시
			}
		}
		return words;
		
	}

	public void save(ArrayList<String> words) { //파일 저장
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("파일 저장");
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setFileFilter(new FileNameExtensionFilter("txt", "txt"));
		if(fileChooser.showSaveDialog(fileChooser)==JFileChooser.APPROVE_OPTION) {
			try {
				saveFile(fileChooser.getSelectedFile().toString(), words); 

			}catch(Exception e) {
				e.printStackTrace(); //에러의 발생근원지를 찾아서 단계별로 에러를 출력하는 것
			}
		}
	}
	
	public void saveFile(String path, ArrayList<String> words) throws IOException{
		File outfile = new File(path);
		FileOutputStream fos = new FileOutputStream(outfile.getPath()+".txt");
		OutputStreamWriter swriter = new OutputStreamWriter(fos, "UTF-8"); //문자변환보조스트림
		//BufferedWriter writer = new BufferedWriter(new FileWriter(outfile));

		for(int i=0; i<words.size(); i++) {
			swriter.write(words.get(i)+"\n");
		}
		swriter.close();
		fos.close();
		
		JOptionPane.showMessageDialog(null,  "파일 저장 성공", "파일 저장", JOptionPane.INFORMATION_MESSAGE);
	}

}
