import java.util.Vector;
import java.util.Comparator;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Collections;

import javax.swing.*;

public class RankingDialog extends JDialog{
	private TextSource textSource = new TextSource();
	private Vector<User> userVector = null;
	public RankingDialog() {
		setLayout(new FlowLayout());
		setSize(280,300);
		setTitle("∞‘¿” ∑©≈∑");
		userVector = textSource.getRankingVector();
		
		Collections.sort(userVector, new Comparator<User>() {
            public int compare(User p1, User p2) {
                if(p1.score > p2.score) {
                    return -1;
                }
                else if(p1.score == p2.score) {
                    return Integer.compare(p1.score, p2.score);
                }
                else {
                    return 1;
                }
            }
        });

		for(int i=0; i<userVector.size(); i++) {
			setLocation(800, 450);
			User user = userVector.get(i);
			String str = Integer.toString(i+1) + "¿ß " + user.name + " " + Integer.toString(user.score) +" " + user.difficulty + " " + user.mode; 
			JLabel rank = new JLabel(str);
			rank.setLocation(25, 80);
			rank.setFont(new Font("Gothic",Font.CENTER_BASELINE, 20));
			add(rank);
		}
		
		setVisible(true);
	}
}