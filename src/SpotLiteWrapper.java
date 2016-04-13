import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

//import Wrapper.Selection;

public class SpotLiteWrapper {

	public List<String> usingXml(String urladd) {
		String urladdress = "";
		List<String> retLst = new ArrayList<String>();
		try {

			
			
			URL url = new URL(urladd);
			URLConnection urlConnection = url.openConnection();
			HttpURLConnection connection = null;
			if (urlConnection instanceof HttpURLConnection) {
				connection = (HttpURLConnection) urlConnection;
			} else {
				System.out.println("Please enter an HTTP URL.");
				return retLst;
			}
			
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(connection.getInputStream());

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("surfaceForm");
			
			
			//System.out.println("----------------------------");
			boolean flg = true;
			for (int temp = 0; temp < nList.getLength(); temp++) {
				//System.out.println("Inside");
				Node nNode = nList.item(temp);
				
				//System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					String text = eElement.getAttribute("name");
					String offset = eElement.getAttribute("offset");
					//System.out.println("Text : "    + text);
					//System.out.println("Offset : "    + offset );
					String startEnd = Integer.parseInt(offset)+","+(text.length()+Integer.parseInt(offset));
					retLst.add(startEnd);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retLst;
	}
	
	
	public List<String> usingXmlAlchemy(String urladd) {
		String urladdress = "";
		List<String> retLst = new ArrayList<String>();
		try {
			
			URL url = new URL("http://access.alchemyapi.com/calls/text/TextGetRankedNamedEntities?");
			
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			
			connection.setRequestProperty("Content-Type header", "application/x-www-form-urlencoded");
			
			
			String  urlParameters= "apikey=7fdef5a245edb49cfc711e80217667be512869b9&text="+urladd;
			
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();
			
			
			String xmlResp = "";
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			xmlResp = response.toString();
			//System.out.println("xml: "+xmlResp);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(response.toString()));
			Document doc = dBuilder.parse(is);

			
			
			
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("entity");
			
			
			//System.out.println("----------------------------");
			boolean flg = true;
			for (int temp = 0; temp < nList.getLength(); temp++) {
				//System.out.println("Inside");
				Node nNode = nList.item(temp);
				
				//System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					
					String aName = eElement.getElementsByTagName("text").item(0).getTextContent();
					
					retLst.add(aName);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retLst;
	}
//	public Selection[] usingXmlStr(String urladd) {
//		String urladdress = "";
//		List<String> retLst = new ArrayList<String>();
//		Selection[] selArray = null;
//		try {
//
//			
//			
//			URL url = new URL(urladd);
//			URLConnection urlConnection = url.openConnection();
//			HttpURLConnection connection = null;
//			if (urlConnection instanceof HttpURLConnection) {
//				connection = (HttpURLConnection) urlConnection;
//			} else {
//				System.out.println("Please enter an HTTP URL.");
//				return selArray;
//			}
//			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//			String urlString = "";
//			String current;
//			BufferedWriter bw = new BufferedWriter(new FileWriter("d:/myFileOutput.xml"));
//			while ((current = in.readLine()) != null) {
//				urlString += current;
//				// System.out.println(current);
//				bw.write(current);
//				bw.newLine();
//
//			}
//			bw.close();
//			File fXmlFile = new File("d:/myFileOutput.xml");
//			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//			Document doc = dBuilder.parse(fXmlFile);
//
//			doc.getDocumentElement().normalize();
//
//			NodeList nList = doc.getElementsByTagName("surfaceForm");
//			
//			
//			//System.out.println("----------------------------");
//			boolean flg = true;
//			selArray = new Selection[nList.getLength()];
//			for (int temp = 0; temp < nList.getLength(); temp++) {
//				//System.out.println("Inside");
//				Node nNode = nList.item(temp);
//				
//				//System.out.println("\nCurrent Element :" + nNode.getNodeName());
//
//				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//
//					Element eElement = (Element) nNode;
//					
//					String aName = eElement.getElementsByTagName("name").item(0).getTextContent();
//					
//					
//					//System.out.println("Text : "    + text);
//					//System.out.println("Offset : "    + offset );
//					
//					//String startEnd = Integer.parseInt(offset)+","+(text.length()+Integer.parseInt(offset));
//					//retLst.add(startEnd);
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return selArray;
//	}
	
	
	public List<String> getResults(String input)
	{
		
		String madeUrlFromInput = "http://spotlight.sztaki.hu:2222/rest/spot?text=";
		String qns[] = input.split(" ");
		String append = String.join("%20", qns);
		madeUrlFromInput += append;//+"&executeSparqlQuery=on&relationExtractorType=Semantic";
		List<String> retLst = new ArrayList<String>();
		
		{
			
			System.out.println("URL is: "+madeUrlFromInput);
			retLst = usingXml(madeUrlFromInput);
		}
		
		return retLst;
	}

	
	
	
	public static void main(String s[]) throws IOException {

		// an Example of How to call the code
		SpotLiteWrapper qaw = new SpotLiteWrapper();
		String question = "What is the capital of Germany";
		List<String > stEn = new ArrayList<String>();
		stEn  = qaw.getResults(question);
		int cnt = 0;
		
		List<String> textAnswers = new ArrayList<String>();
		textAnswers = qaw.usingXmlAlchemy(question);
		System.out.println("The Alchemy Response: "+textAnswers.toString());
		
		for(String str: stEn)
		{
			System.out.println(str);
			Selection s1 = new Selection();
			String str1[] = str.split(",");
			s1.begin = Integer.parseInt(str1[0]);
			s1.end = Integer.parseInt(str1[1]);
			StringBuilder test=new StringBuilder(question);
			test = test.insert(s1.begin+cnt, "<entity>");
			//
			test = test.insert(s1.end+cnt+8, "</entity>");
			question = new String(test);
			cnt+=17;
			System.out.println("My out is : "+ question);
			//System.out.println("My out is : "+ test.insert(s1.begin, "<entity>"));
			
		}
		
		//ProcessBuilder pb = new ProcessBuilder("curl","--data-urlencode", "\"text='"+question+"'\"","-d type='agdistis'","http://139.18.2.164:8080/AGDISTIS");
		String myURL= "curl --data-urlencode \"text='"+question+"'\" -d type='agdistis' http://139.18.2.164:8080/AGDISTIS";
		//ProcessBuilder pb = new ProcessBuilder(myURL);
		System.out.println(myURL);
		//pb.directory(new File("/Users/kulsingh/"));
	   // pb.redirectErrorStream(true);
		
		//pb.redirectOutput(new File("d:/curloutput.txt"));
		 
	   //Process p=  pb.start();	    
	   
	   /* InputStream is = p.getInputStream();
	    
	    
	    BufferedReader r = new BufferedReader(new InputStreamReader(is));
	    String line = null;
	    while ((line=r.readLine()) != null) {
	        System.out.println(line);
	    }*/
		//String myURL= "curl --data-urlencode \"text='"+question+"'\" -d type='agdistis' http://139.18.2.164:8080/AGDISTIS";
		//System.out.println(pb.command());
		//Runtime.getRuntime().exec(myURL);
		StringBuilder test=new StringBuilder(" just testing.");
		System.out.println("My out is "+ test.insert(6, "code "));
		qaw.runCurl("", question);
		qaw.runCurl1("", question);
		//System.out.println("Without XML:\n"+qaw.getResults(question));
	}
	public void runCurl(String str,String question) throws HttpException, IOException
	{
		
		URL url = new URL("http://139.18.2.164:8080/AGDISTIS?");
		
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		
		
		String  urlParameters= "text=What is the capital of <entity>Germany</entity>.&type=agdistis";
		
		
		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		
		
		String xmlResp = "";
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		xmlResp = response.toString();
		
		System.out.println("Curl Response: \n"+xmlResp);
		
		
//		HttpClient client = new HttpClient();
//		GetMethod get = new GetMethod("http://139.18.2.164:8080/AGDISTIS?urlencodetext='The <entity>University of Leipzig</entity> in <entity>Barack Obama</entity>.'&type='agdistis'");
//		
//		String url = "http://139.18.2.164:8080/AGDISTIS";
//		URL obj = new URL(url);
//		URLConnection con = (HttpsURLConnection) obj.openConnection();
//	
//	//	con.setRequestMethod("GET");
//		con.setRequestProperty("--data-urlencode", "text='The <entity>University of Leipzig</entity> in <entity>Barack Obama</entity>.'");
//		con.setRequestProperty("-d", "type='agdistis'");
//		
//		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//		System.out.println("Response code: "+status);
//		if (status == 200) {
//		//String response = con.
//		}
//		
//		else
//		{
//			System.out.println("Not Working");
//		}
	}


public void runCurl1(String str,String question) throws HttpException, IOException
{
	
	URL url = new URL("http://spotlight.sztaki.hu:2222/rest/disambiguate/");
	
	
	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	String temp = "text=<?xml version=\"1.0\" encoding=\"UTF-8\"?><annotation text=\"What is the capital of India\"><surfaceForm name=\"India\" offset=\"23\"/></annotation>>";
	connection.setRequestMethod("POST");
	connection.setDoOutput(true);
	
	connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	connection.setRequestProperty("xml", temp);
	
	
	
	
	/*DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
	wr.writeBytes(temp);
	wr.flush();
	wr.close();
	*/
	
	String xmlResp = "";
	BufferedReader in = new BufferedReader(
	        new InputStreamReader(connection.getInputStream()));
	String inputLine;
	StringBuffer response = new StringBuffer();

	while ((inputLine = in.readLine()) != null) {
		response.append(inputLine);
	}
	in.close();
	xmlResp = response.toString();
	
	System.out.println("Curl1 Response: \n"+xmlResp);
	
//	HttpClient client = new HttpClient();
//	GetMethod get = new GetMethod("http://139.18.2.164:8080/AGDISTIS?urlencodetext='The <entity>University of Leipzig</entity> in <entity>Barack Obama</entity>.'&type='agdistis'");
//	
//	String url = "http://139.18.2.164:8080/AGDISTIS";
//	URL obj = new URL(url);
//	URLConnection con = (HttpsURLConnection) obj.openConnection();
//
////	con.setRequestMethod("GET");
//	con.setRequestProperty("--data-urlencode", "text='The <entity>University of Leipzig</entity> in <entity>Barack Obama</entity>.'");
//	con.setRequestProperty("-d", "type='agdistis'");
//	
//	BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//	System.out.println("Response code: "+status);
//	if (status == 200) {
//	//String response = con.
//	}
//	
//	else
//	{
//		System.out.println("Not Working");
//	}
}
}

class Selection {
	public int begin;
	public int end;
}