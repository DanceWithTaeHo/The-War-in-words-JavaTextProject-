import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class EditPanel extends JPanel{ // 아랫쪽 에딧패 널
	// 텍스트 20만큼의 입력이 가능한 필드생성
	private JTextField edit = new JTextField(20);
	// 버튼 생성
	private JButton addButton = new JButton("단어추가");
	private JButton saveButton = new JButton("save");
	private JLabel textLabel = new JLabel("");
	
	public EditPanel() {
		this.setBackground(Color.MAGENTA);
		this.setLayout(new FlowLayout());
		// 추가 및 생성
		add(edit);
		add(addButton);
		add(saveButton);
		add(textLabel);
		addButton.addActionListener(new addBtnActionListener());
	}
	
	// addBtn 액션이벤트 리스너
	class addBtnActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String text = edit.getText();
			
			if(text.equals("")) return; // 텍스트가 공백이면 리턴
			
			TextSource.saveFile(text);
			edit.setText(""); // clear
		}		
	}
}
