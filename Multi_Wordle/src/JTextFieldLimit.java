import javax.swing.text.*;

@SuppressWarnings("serial")
public class JTextFieldLimit extends PlainDocument {
	
	private int limit;
	public JTextFieldLimit(int limit) {
		super();
		this.limit = limit;
	}
	
   public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
	      if (str == null)
	         return;
	      if ((getLength() + str.length()) <= limit) {
	         super.insertString(offset, str, attr);
	      }
   } 
}
	