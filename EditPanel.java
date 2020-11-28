import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class EditPanel extends JPanel{ // �Ʒ��� ������ ��
	// �ؽ�Ʈ 20��ŭ�� �Է��� ������ �ʵ����
	private JTextField edit = new JTextField(20);
	// ��ư ����
	private JButton addButton = new JButton("�ܾ��߰�");
	private JButton saveButton = new JButton("save");
	private JLabel textLabel = new JLabel("");
	
	public EditPanel() {
		this.setBackground(Color.MAGENTA);
		this.setLayout(new FlowLayout());
		// �߰� �� ����
		add(edit);
		add(addButton);
		add(saveButton);
		add(textLabel);
		addButton.addActionListener(new addBtnActionListener());
	}
	
	// addBtn �׼��̺�Ʈ ������
	class addBtnActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String text = edit.getText();
			
			if(text.equals("")) return; // �ؽ�Ʈ�� �����̸� ����
			
			TextSource.saveFile(text);
			edit.setText(""); // clear
		}		
	}
}
