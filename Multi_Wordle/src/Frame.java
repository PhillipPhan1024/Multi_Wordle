import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

@SuppressWarnings("serial")
public class Frame extends JFrame implements KeyListener {
	
	private ArrayList<JTextField> gm;
	private boolean CONTINUE = false;
	private int guesses = 0;
	
	private String ans = "CHIPS";
	private String result;

	Font fontBig = new Font("Serif", Font.BOLD, 30);
	private JPanel gl;
	private JFrame frame;
	
	public Frame() {
		
		frame = new JFrame();
		
		gm = new ArrayList<>();
		
		frame.setSize(1920, 1080);
		frame.setLayout(new GridBagLayout());
		
		gl = new JPanel();
		gl.setLayout(new GridLayout(6, 5, 10, 10));	
		gl.setPreferredSize(new Dimension(600, 600));
		gl.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);	
		frame.add(gl);
		
		frame.addKeyListener(this);
		
		result = "";
		
		// Put Text onto thing
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 5; j++) {
				
				JTextField jtf = new JTextField();
				jtf.setBackground(Color.LIGHT_GRAY);
				//jtf.setCaretColor(Color.LIGHT_GRAY);
				jtf.setFont(fontBig);
				jtf.setHorizontalAlignment(JTextField.CENTER);
				jtf.setDocument(new JTextFieldLimit(1));
				jtf.setEnabled(false);
				jtf.addKeyListener(this);
				gl.add(jtf);
				gm.add(jtf);

			}
		} 
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true); 
	}

	/*public void create() {
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 5; j++) {
			
				JTextField jtf = new JTextField();
				jtf.setBackground(Color.LIGHT_GRAY);
				//jtf.setCaretColor(Color.LIGHT_GRAY);
				jtf.setFont(fontBig);
				jtf.setHorizontalAlignment(JTextField.CENTER);
				jtf.setDocument(new JTextFieldLimit(1));
				jtf.setEnabled(false);
				jtf.addKeyListener(this);
				gl.add(jtf);
				gm.add(jtf);

			}
		} 
		
		wordle();
	}*/
	
	public void wordle() {
		
		for(int i = 0; i < 29; i+=5) {
			
			for(int j = 0; j < 5; j++) {
				gm.get(i+j).setEnabled(true);
				
				try {
					boolean BREAKLOOP = false;
					while(!BREAKLOOP) {
						if(gm.get(i+j).getText().equals("")) {
							BREAKLOOP = false;
						}
						else {
							BREAKLOOP = true;
						}
					}	
				} catch (Exception ignore) {
					// Nothing
				}
												
			}
			
			while(!CONTINUE) {
				System.out.println("...");
			}
			
			CONTINUE = false;

			String s = gm.get(i).getText() + gm.get(i+1).getText() + gm.get(i+2).getText() +
					gm.get(i+3).getText() + gm.get(i+4).getText();
			s = s.toUpperCase();
			
			if(s.equals(ans)) {
				gm.get(i).setBackground(Color.GREEN);
				gm.get(i+1).setBackground(Color.GREEN);
				gm.get(i+2).setBackground(Color.GREEN);
				gm.get(i+3).setBackground(Color.GREEN);
				gm.get(i+4).setBackground(Color.GREEN);
				
				result = "" + guesses;
				disableAllButtons();
				CONTINUE = true;
				return;
			}
			
			for(int j = 0; j < 5; j++) {
				String s1 = gm.get(i+j).getText();
				s1 = s1.toUpperCase();
				
				System.out.println(ans.indexOf(s1));
				
				if(ans.indexOf(s1) == j) {
					gm.get(i+j).setBackground(Color.GREEN);
				}
				else if(ans.indexOf(s1) >= 0) {
					gm.get(i+j).setBackground(Color.YELLOW);
				}
				gm.get(i+j).setEnabled(false);
			
			}
		}
	}
	
	public void disableAllButtons() {
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 5; j++) {
				gm.get(i+j).setEnabled(false);
			}
		} 
		
	}
	
	public String getResult() {
		return result;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyChar() == KeyEvent.VK_ENTER) {
			CONTINUE = true;
			guesses++;
		}
		if(e.getKeyChar() == KeyEvent.VK_SLASH) {
			result = "Exit";
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}
