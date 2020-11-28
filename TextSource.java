import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

/* TextSource�� �ܾ� ���ͷκ��� �ܾ� 1���� ��ȯ�� */
public class TextSource {
	private Vector<String> v = new Vector<String>();
	public TextSource() {
		readFile();
	}
	private void readFile() { // �ܾ������� ����
		try {
			Scanner fscanner = new Scanner(new FileReader("words.txt"));
			while(fscanner.hasNext()) { // �ܾ �ϳ��� ����
				String word = fscanner.next(); // with trim()
				v.add(word); // ���Ϳ� ����
			}
			fscanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
	}
	
	public static void saveFile(String text) { // �ܾ� ������ ����
		BufferedWriter bufferedWriter;
		try { // PrintWriter�� �̿��Ͽ� ���ۿ� �ִ� �ܾ �̾ ����
			bufferedWriter = new BufferedWriter(new FileWriter("words.txt", true));
			PrintWriter printWriter = new PrintWriter(bufferedWriter, true);
			
			printWriter.write(text+"\n"); // ����Ʈ ������ ����
			printWriter.close();
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}		
	}
	
	public String get() {
		int index = (int)(Math.random()*v.size());
		
		return v.get(index);
	}
}
