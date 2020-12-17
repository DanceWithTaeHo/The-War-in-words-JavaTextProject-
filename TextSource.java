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
	private Vector<String> koreanWordVector = new Vector<String>();
	private Vector<String> englishWordVector = new Vector<String>();
	private Vector<User> userVector = new Vector<User>();
	public TextSource() {
		readFile();
	}
	private void readFile() { // 단어파일을 읽음
		try { // 한글단어 읽기
			Scanner fscanner = new Scanner(new FileReader("words_ko.txt"));
			while(fscanner.hasNext()) { // 단어를 하나씩 읽음
				String word = fscanner.next(); // with trim()
				koreanWordVector.add(word); // 벡터에 저장
			}
			fscanner.close();
			
			// 영어 단어 읽기
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
	
	// 단어 저장
	public void saveFile(String text, boolean isEnglish) { // 단어 파일을 저장
		BufferedWriter bufferedWriter;
		String fileName;
		if(isEnglish)
			fileName = "words_en.txt";
		else
			fileName = "words_ko.txt";
		try { // PrintWriter를 이용하여 버퍼에 있는 단어를 이어서 저장
			bufferedWriter = new BufferedWriter(new FileWriter(fileName, true));
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
	
	// 단어를 리턴
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
	
	// 점수 저장
	public void saveScore(String name, String score, String difficulty, boolean isEnglish) {
		BufferedWriter bufferedWriter;
		String mode;
		if(isEnglish) mode = "English";
		else mode = "Korean";
		try { // PrintWriter를 이용하여 버퍼에 있는 단어를 이어서 저장
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
	
	// 랭킹파일을 읽어 유저 벡터에 저장
	public void readRanking() {
		try {
			Scanner fscanner = new Scanner(new FileReader("ranking.txt"));
			while(fscanner.hasNext()) { // 단어를 하나씩 읽음
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
	
	// 랭킹이 기록된 유저 벡터를 반환
	public Vector<User> getRankingVector() {
		userVector.clear();		
		readRanking();
		return userVector;
	}
}
