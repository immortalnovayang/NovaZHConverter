import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import com.spreada.utils.chinese.ZHConverter;


public class MainPanel extends JPanel{
	JMenuBar menuBar;
	JMenu menu;
	
	public MainPanel(){
		this.setLayout(new BorderLayout());
        /*Border blackline = BorderFactory.createMatteBorder(
                5, 5, 5, 5, Color.black);
        setBorder(blackline);*/
    	//Create the menu bar.
    	menuBar = new JMenuBar();
    	

    	//Build the first menu.
    	menu = new JMenu("幫助(Help)");
    	menu.setMnemonic(KeyEvent.VK_H);
    	//menu.getAccessibleContext().setAccessibleDescription(
    	//        "The only menu in this program that has menu items");
    	
    	JMenuItem menuItem = new JMenuItem("關於(About)",
                KeyEvent.VK_A);
    	menuItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				
				JOptionPane.showMessageDialog(null, new MessageWithLink(
						"本軟體由Nova所製作<p>"
						+ "網站 <a href=\"http://immortalnova.hatenablog.com\">http://immortalnova.hatenablog.com/</a>"));
			}
    		
    	});
    	
    	
    	menu.add(menuItem);
    	
    	menuBar.add(menu);

        
        
    	TextPanel TextPanel = new TextPanel();
    	
    	ButtonPanel ButtonPanel = new ButtonPanel();
        
        add(BorderLayout.NORTH , menuBar);
        add(BorderLayout.CENTER, TextPanel);
        add(BorderLayout.SOUTH, ButtonPanel);

	}
	
	
}

class TextPanel extends JPanel{
	static JTextArea textarea1;
	static JTextArea textarea2;
	
	public TextPanel(){
		textarea1 = new JTextArea("");
        textarea2 = new JTextArea("");
        textarea1.setLineWrap(true); 
        textarea2.setLineWrap(true); 
        JScrollPane scrollPane1 = new JScrollPane(textarea1);
        JScrollPane scrollPane2 = new JScrollPane(textarea2);
        
        setLayout(new GridLayout(1,2));
        
        add(scrollPane1);
        add(scrollPane2);
        
	}
}

class ButtonPanel extends JPanel{
	JButton CNtoTWbutton = new JButton("簡體轉繁體");
	JButton TWtoCNbutton = new JButton("繁體轉簡體");
	
	public ButtonPanel(){
		setLayout(new GridLayout(1,2));
		
		add(CNtoTWbutton);
		add(TWtoCNbutton);
		
		CNtoTWbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	TextPanel.textarea2.setText(process(TextPanel.textarea1.getText()));
            }

			private String process(String text) {
				String result = "";
				
				ZHConverter converter = ZHConverter.getInstance(ZHConverter.TRADITIONAL);
				result = converter.convert(text);
				
				System.out.println("取代文字");
				for(String key:DataManger.CNtoTWmap.keySet()){
					String value = DataManger.CNtoTWmap.get(key);
					//result = result.replaceAll(key, value);
					System.out.println("key,value = "+key+","+value);
					result = result.replace(key, value);
				}
				
				result = result.replaceAll("机", "機");
				
				StringSelection selection = new StringSelection(result);
			    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			    clipboard.setContents(selection, selection);
				
				return result;
			}

        });
		
		TWtoCNbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	TextPanel.textarea1.setText(process(TextPanel.textarea2.getText()));
            }

			private String process(String text) {
				String result = "";
				
				System.out.println("第0次轉換"+text);
				
				for(String key:DataManger.TWtoCNmap.keySet()){
					String value = DataManger.TWtoCNmap.get(key);
					
					if(key.charAt(0)=='+'){
						key = "[+]";
					}
					System.out.println(key+","+value);
					text = text.replaceAll(key, value);
				}
				
				
				System.out.println("第1次轉換"+text);
				
				ZHConverter converter = ZHConverter.getInstance(ZHConverter.SIMPLIFIED);
				result = converter.convert(text);
				
				System.out.println("第2次轉換"+result);
				
				//result = result.replaceAll("機", "机");
				
				StringSelection selection = new StringSelection(result);
			    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			    clipboard.setContents(selection, selection);
				
				return result;
			}

        });
		
	}
}