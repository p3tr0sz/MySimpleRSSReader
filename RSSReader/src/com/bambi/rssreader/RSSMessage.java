package com.bambi.rssreader;

import java.util.List;

public class RSSMessage {

	private String title;
	private String description;
	private String category;
	private String pubDate;
	private String guid;
	
	
	public RSSMessage(String title, String description, String category, String pubDate, String guid){
		this.title = title;
		this.description = description;
		this.category = category;
		this.pubDate = pubDate;
		this.guid = guid;
	}
	
	
	public void setTitle(String t){
		this.title = t;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setDescription(String d){
		this.description = d;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setCategory(String c){
		this.category = c;
	}
	
	public String getCategory(){
		return category;
	}
	
	public void setPubDate(String p){
		this.pubDate = p;
	}
	
	public String getPubDate(){
		return pubDate;
	}
	
	public void setGuid(String g){
		this.guid = g;
	}
	
	public String getGuid(){
		return guid;
	}

	public String toString() {
		return title +"\n" + description
				+"\n"+  category+ "\n"  + pubDate +"\n"
				+ guid + "\n";
	}
	
	
}
