import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Reader;
import java.io.StringReader;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Toolkit;

public class Run extends JFrame {
	static int l;
	static String msg;
	static Run frame;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Run();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Run() {
		setTitle("KAZ IDE");
		setBackground(Color.BLUE);
		setForeground(Color.BLUE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Asus\\Pictures\\13646.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1033, 509);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 10, 409);
		BoxLayout lay=new BoxLayout(panel, BoxLayout.Y_AXIS);
		
		panel.setLayout(lay);

		JTextArea textArea = new JTextArea();
		textArea.setBackground(Color.CYAN);
		textArea.setFont(new Font("Courier New", Font.PLAIN, 27));
		textArea.setBounds(16, 0, 947, 412);
	
		
		JScrollPane scrollPane = new JScrollPane(textArea);
	
		scrollPane.setBounds(27, 37, 947, 412);
		scrollPane.setRowHeaderView(panel);
		contentPane.add(scrollPane);
	
		textArea.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				warn();
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0) {
					
				warn();
				
			}
			public void warn() {
				
				String txt=textArea.getText();
				if(txt.lastIndexOf("\n")==txt.length()-1 && txt.length()>0) {
					txt=txt.substring(0, txt.length()-1);
				}
				String[] lines=txt.split("\n");
				
				
				
				
				Reader reader = new StringReader(txt);
				Scanner scanner = new Scanner(reader);
		           parser p=new parser(scanner);
		           try {
					p.parse();
					textArea.getHighlighter().removeAllHighlights();
					l=-1;
				} catch (Exception e) {
					textArea.getHighlighter().removeAllHighlights();
					 HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.ORANGE);
		                int start=-1;
					 try {
						 while(lines[l-1].equals(""))
							 l--;
							textArea.getHighlighter().addHighlight(textArea.getLineStartOffset(l-1),textArea.getLineEndOffset(l-1), painter);
						} catch (BadLocationException e2) {
							// TODO Auto-generated catch block
//							e2.printStackTrace();
							
						}
					
				}
		           panel.removeAll();
		           for(int i=0;i<lines.length;i++) { 
		        	   JLabel s=new JLabel(String.valueOf(i+1));
		        	   Dimension d = new Dimension(15, 32);
		        	   s.setMaximumSize(new Dimension(d));
		        	   s.setPreferredSize(d);
		        	   s.setMaximumSize(d);
		        	   if(l-1==i) {
		        		   s.setForeground(Color.RED);
		        		   s.setToolTipText(msg);
		        	   }
						panel.add(s);}
		           
		           frame.revalidate();
					  frame.repaint();
		           
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				warn();
				
			}
		});

	
		
	}
}
