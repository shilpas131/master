package webscraping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class AppInitializer {
	static String mainDocumentURL = null;
	static String otherDocumentURLs = null;
	static String word = null;
	
	public AppInitializer() {
		fecthDocumentURL();
	}
	private void fecthDocumentURL() {
		File file = new File(getClass().getClassLoader().getResource("document-configurer.properties").getFile()
    			);
		FileInputStream fis = null;
		Properties prop = null;
	      try {
	    	 fis = new FileInputStream(file);
	         prop = new Properties();
	         prop.load(fis);
	      } catch(FileNotFoundException fnfe) {
	         fnfe.printStackTrace();
	      } catch(IOException ioe) {
	         ioe.printStackTrace();
	      } finally {
	         try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	      }
	      mainDocumentURL = prop.getProperty("maindocumenturl");
	      otherDocumentURLs = prop.getProperty("otherdocumenturls");
	      word = prop.getProperty("word");
	}
	public void startThread() {
	    String[] otherDocumentlistArray = otherDocumentURLs.split(";");
	    String mainString = "";
	    StringBuilder otherString = new StringBuilder();
	    
	    WebScrapingWithJSOUP mainWbsj = new WebScrapingWithJSOUP(mainDocumentURL);
	    mainString = mainWbsj.getParsedDataFromDocument();
	    otherString.append(mainString);
		 
		for(int i=0;i<otherDocumentlistArray.length;i++) {
			 String document = otherDocumentlistArray[i];
			 WebScrapingWithJSOUP wbsj = new WebScrapingWithJSOUP(document);
			 otherString.append(" nextParsedDocument "+wbsj.getParsedDataFromDocument());
		}
		
		TFIDFCalculator calc = new TFIDFCalculator();
		calc.calculate(mainString, otherString.toString(), word);
	}

}
