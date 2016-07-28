

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientForWebServices {

	public static void runCurlPOSTWithParam(String weburl,String data,String contentType) throws Exception
	{
		URL url = new URL(weburl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		
		connection.setRequestProperty("Content-Type", contentType);
				
		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		wr.writeBytes(data);
		wr.flush();
		wr.close();
		
		
		String resp = "";
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		resp = response.toString();
		
		System.out.println("Curl Response: \n"+resp);
	}
	
/*	public void runCurl() throws HttpException, IOException
	{
		URL url = new URL("http://ws.okbqa.org:1515/templategeneration/rocknrole");
		
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		
		connection.setRequestProperty("Content-Type", "application/json");
		
		
		String  urlParameters= "{  \"string\": \"Which river flows through Seoul?\",  \"language\": \"en\"}";
		
		
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
	}*/
	
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		String url = "";
		String data = "";
		String contentType = "application/json";
		 
		//http://repository.okbqa.org/components/21
		//Sample input	
		/* 
		 * {
		  	"string": "Which river flows through Seoul?",
		  	"language": "en"
		   } http://ws.okbqa.org:1515/templategeneration/rocknrole
		*/
		url = "http://ws.okbqa.org:1515/templategeneration/rocknrole";
		data = "{  \"string\": \"Which river flows through Seoul?\",  \"language\": \"en\"}";
		System.out.println("\nComponent : 21");
		ClientForWebServices.runCurlPOSTWithParam(url, data, contentType);
		
		//http://repository.okbqa.org/components/9
				//Sample input	
				/* 
				 * {
				  	"string": "Which river flows through Seoul?",
				  	"language": "en"
				   } http://ws.okbqa.org:2360/ko/tgm/stub/service
				*/
		//
		
		url = "http://ws.okbqa.org:2360/ko/tgm/stub/service";
		data = "{  \"string\": \"Which river flows through Seoul?\",  \"language\": \"en\"}";
		System.out.println("\nComponent : 9");
		ClientForWebServices.runCurlPOSTWithParam(url, data, contentType);
		
		
		//http://repository.okbqa.org/components/15
		//Sample input	
		/* 
		 * [
				{
					\"query\":\"SELECT ?river WHERE { ?river rdf:type dbo:River . ?river dbo:state dbr:Seoul }\",
					\"score\":0.5
				}
		   ] http://ws.okbqa.org:7744/agm
		*/
//
		url = "http://ws.okbqa.org:7744/agm";
		data = "[ {	\"query\":\"SELECT ?river WHERE { ?river rdf:type dbo:River . ?river dbo:state dbr:Seoul }\",\"score\":0.5}]";
		System.out.println("\nComponent : 15");
		ClientForWebServices.runCurlPOSTWithParam(url, data, contentType);
		
		
		
		//http://repository.okbqa.org/components/1
		//Sample input
		/*
			{
		"disambiguation": [
		{
			"entities": [{"var": "v6", "score": 1, "value": "http://dbpedia.org/resource/Gunsan"}],
			"classes": [
				{"var": "v3", "score": 0.25, "value": "http://dbpedia.org/ontology/River"},
				{"var": "v3", "score": 0.2564102564102564, "value": "http://dbpedia.org/ontology/River"}
			],
			"score": 1,
			"properties": [
				{"var": "v2", "score": 0.2564102564102564, "value": "http://dbpedia.org/ontology/city"}
			]
		}
	],
	"template": {
		"query": "SELECT ?v4 WHERE { ?v4 ?v2 ?v6 ; ?v7 ?v3 . }",
		"slots": [
			{"p": "is", "s": "v2", "o": "rdf:Property"},
			{"p": "verbalization", "s": "v2", "o": "flow"},
			{"p": "is", "s": "v6", "o": "rdf:Resource|rdfs:Literal"},
			{"p": "verbalization", "s": "v6", "o": "Gunsan"},
			{"p": "is", "s": "v7", "o": "<http://lodqa.org/vocabulary/sort_of>"},
			{"p": "is", "s": "v3", "o": "rdf:Class"},
			{"p": "verbalization", "s": "v3", "o": "rivers"}
		],
		"score": "1.0",
		"question": "Which rivers flow through Gunsan?"
	}
}' http://ws.okbqa.org:38401/queries?max_hop=1
			
		
		*/
		url = "http://ws.okbqa.org:38401/queries?max_hop=1";
		data = "{		\"disambiguation\": [		{			\"entities\": [{\"var\": \"v6\", \"score\": 1, \"value\": \"http://dbpedia.org/resource/Gunsan\"}],			\"classes\": [				{\"var\": \"v3\", \"score\": 0.25, \"value\": \"http://dbpedia.org/ontology/River\"},				{\"var\": \"v3\", \"score\": 0.2564102564102564, \"value\": \"http://dbpedia.org/ontology/River\"}			],			\"score\": 1,			\"properties\": [				{\"var\": \"v2\", \"score\": 0.2564102564102564, \"value\": \"http://dbpedia.org/ontology/city\"}			]		}	],	\"template\": {		\"query\": \"SELECT ?v4 WHERE { ?v4 ?v2 ?v6 ; ?v7 ?v3 . }\",		\"slots\": [			{\"p\": \"is\", \"s\": \"v2\", \"o\": \"rdf:Property\"},			{\"p\": \"verbalization\", \"s\": \"v2\", \"o\": \"flow\"},			{\"p\": \"is\", \"s\": \"v6\", \"o\": \"rdf:Resource|rdfs:Literal\"},			{\"p\": \"verbalization\", \"s\": \"v6\", \"o\": \"Gunsan\"},			{\"p\": \"is\", \"s\": \"v7\", \"o\": \"<http://lodqa.org/vocabulary/sort_of>\"},			{\"p\": \"is\", \"s\": \"v3\", \"o\": \"rdf:Class\"},			{\"p\": \"verbalization\", \"s\": \"v3\", \"o\": \"rivers\"}		],		\"score\": \"1.0\",		\"question\": \"Which rivers flow through Gunsan?\"	}}";
		System.out.println("\nComponent : 1");
		ClientForWebServices.runCurlPOSTWithParam(url, data, contentType);
		
	}

}
