package com.bambi.rssreader;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.XMLEvent;



public class Reader {

	private String title = "";
	private String description = "";
	private String category = "";
	private String pubDate = "";
	private String guid = "";

	
	public  static final String TITLE = "title";
	public  static final String DESCRIPTION = "description";
	public  static final String CATEGORY = "category";
	public  static final String PUB_DATE = "pubDate";
	public  static final String GUID = "guid";
	public  static final String ITEM = "item";

	
	private URL url;
	
	private List<RSSMessage> messages = new ArrayList<RSSMessage>();
	
	public Reader(String urll) throws Exception{
		
		
		url = new URL(urll);
		InputStream is = url.openStream();
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLEventReader reader = factory.createXMLEventReader(is);
		
		while(reader.hasNext()){
			XMLEvent event = reader.nextEvent();
			
			if(event.isStartElement()){
				String localname = event.asStartElement().getName().getLocalPart();
				
				if(localname.equals(TITLE)){
					title = reader.nextEvent().asCharacters().getData();
				}
				if(localname.equals(DESCRIPTION)){
					description = reader.nextEvent().asCharacters().getData();
				}
				if(localname.equals(CATEGORY)){
					category = reader.nextEvent().asCharacters().getData();
				}
				if(localname.equals(PUB_DATE)){
					pubDate = reader.nextEvent().asCharacters().getData();
				}
				if(localname.equals(GUID)){
					guid = reader.nextEvent().asCharacters().getData();
				}
				
			}else if(event.isEndElement()){
				if(event.asEndElement().getName().getLocalPart() == ITEM){
					RSSMessage tmp = new RSSMessage( title, description, category,  pubDate,  guid);
					messages.add(tmp);
				}
			}
		}
	}
	
	public List<RSSMessage> getMessages(){
		return messages;
	}
	
}
