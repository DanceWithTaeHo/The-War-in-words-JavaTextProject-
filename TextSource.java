import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

/* TextSource는 단어 벡터로부터 단어 1개를 반환함 */
public class TextSource {
	private Vector<String> v = new Vector<String>();
	public TextSource() {
		readFile();
	}
	private void readFile() { // 단어파일을 읽음
		try {
			Scanner fscanner = new Scanner(new FileReader("words.txt"));
			while(fscanner.hasNext()) { // 단어를 하나씩 읽음
				String word = fscanner.next(); // with trim()
				v.add(word); // 벡터에 저장
			}
			fscanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
	}
	
	public static void saveFile(String text) { // 단어 파일을 저장
		BufferedWriter bufferedWriter;
		try { // PrintWriter를 이용하여 버퍼에 있는 단어를 이어서 저장
			bufferedWriter = new BufferedWriter(new FileWriter("words.txt", true));
			PrintWriter printWriter = new PrintWriter(bufferedWriter, true);
			
			printWriter.write(text+"\n"); // 바이트 형으로 저장
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
