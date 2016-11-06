import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class DataManger {
	static HashMap<String,String> CNtoTWmap = null;
	static HashMap<String,String> TWtoCNmap = null;

	public static void init(){
		CNtoTWmap = new LinkedHashMap<String,String>();
		TWtoCNmap = new LinkedHashMap<String,String>();
		
		File file = new File("ygo.txt");
		
		BufferedReader in;
		try {
			in = new BufferedReader(
					   new InputStreamReader(
			                      new FileInputStream(file), "UTF8"));
			
			String str;

			try {
				while ((str = in.readLine()) != null) {
					String[] languageParam = str.split("=");
					
					String CNstr = languageParam[0];
					
					String TWstr = languageParam[1].replaceAll("[\n\r]","");
					
					System.out.println(CNstr+"="+TWstr);
					if(CNstr.matches("[+]")){
						System.out.println("加號");
						CNtoTWmap.put("[+]", TWstr);
					}else
					CNtoTWmap.put(CNstr, TWstr);
					TWtoCNmap.put(TWstr, CNstr);
					

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TWtoCNmap.put("＋", "+");
	}
	
}
