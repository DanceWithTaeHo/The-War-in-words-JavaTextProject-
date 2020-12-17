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
	private Vector<String> koreanWordVector = new Vector<String>();
	private Vector<String> englishWordVector = new Vector<String>();
	private Vector<User> userVector = new Vector<User>();
	public TextSource() {
		readFile();
	}
	private void readFile() { // �ܾ������� ����
		try { // �ѱ۴ܾ� �б�
			Scanner fscanner = new Scanner(new FileReader("words_ko.txt"));
			while(fscanner.hasNext()) { // �ܾ �ϳ��� ����
				String word = fscanner.next(); // with trim()
				koreanWordVector.add(word); // ���Ϳ� ����
			}
			fscanner.close();
			
			// ���� �ܾ� �б�
			fscanner = new Scanner(new FileReader("words_en.txt"));
			while(fscanner.hasNext()) {
				String word = fscanner.next();
				englishWordVector.add(word);
			}
			fscanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
	}
	
	// �ܾ� ����
	public void saveFile(String text, boolean isEnglish) { // �ܾ� ������ ����
		BufferedWriter bufferedWriter;
		String fileName;
		if(isEnglish)
			fileName = "words_en.txt";
		else
			fileName = "words_ko.txt";
		try { // PrintWriter�� �̿��Ͽ� ���ۿ� �ִ� �ܾ �̾ ����
			bufferedWriter = new BufferedWriter(new FileWriter(fileName, true));
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
	
	// �ܾ ����
	public String getWord(boolean isEnglish) {
		if(isEnglish) {
			int index = (int)(Math.random()*englishWordVector.size());		
			return englishWordVector.get(index);
		}
		else {
			int index = (int)(Math.random()*koreanWordVector.size());		
			return koreanWordVector.get(index);
		}
	}
	
	// ���� ����
	public void saveScore(String name, String score, String difficulty, boolean isEnglish) {
		BufferedWriter bufferedWriter;
		String mode;
		if(isEnglish) mode = "English";
		else mode = "Korean";
		try { // PrintWriter�� �̿��Ͽ� ���ۿ� �ִ� �ܾ �̾ ����
			bufferedWriter = new BufferedWriter(new FileWriter("ranking.txt", true));
			PrintWriter printWriter = new PrintWriter(bufferedWriter, true);
			
			printWriter.write(name+" " + score+ " " + difficulty + " " + mode + "\n");
			printWriter.close();
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
	
	// ��ŷ������ �о� ���� ���Ϳ� ����
	public void readRanking() {
		try {
			Scanner fscanner = new Scanner(new FileReader("ranking.txt"));
			while(fscanner.hasNext()) { // �ܾ �ϳ��� ����
				User user = new User();
				fscanner.next();
				user.name= fscanner.next();
				user.score = Integer.parseInt(fscanner.next());
				user.difficulty = fscanner.next();
				user.mode = fscanner.next();
				userVector.add(user);

			}
			fscanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
	}
	
	// ��ŷ�� ��ϵ� ���� ���͸� ��ȯ
	public Vector<User> getRankingVector() {
		userVector.clear();		
		readRanking();
		return userVector;
	}
}
