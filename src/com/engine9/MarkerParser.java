package com.engine9;

import java.io.InputStream;
import java.util.Vector;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

import com.google.android.gms.maps.model.LatLng;

public class MarkerParser {

	public static Vector<LatLng> main(InputStream in){
		
		final Vector<LatLng> markers = new Vector<LatLng>();
		try {
			
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			
			DefaultHandler handler = new DefaultHandler(){
				
			Boolean latb = false;
			Boolean lonb = false;
			Long latl;
			Long lonl;
			
			public void startElement(String uri, String localName,String qName, 
	                Attributes attributes) throws SAXException {
				if (qName.equalsIgnoreCase("LATITUDE")) {
					latb = true;
				}
				if (qName.equalsIgnoreCase("LONGITUDE")) {
					lonb = true;
				}
			}
			
			public void characters(char ch[], int start, int length) throws SAXException {
				
				if(latb){
					latl = Long.parseLong(new String(ch, start, length));
				}
				if(lonb){
					lonl = Long.parseLong(new String(ch, start, length));
				}
			}
			
			public void endElement(String uri, String localName,
					String qName) throws SAXException {
				if (qName.equalsIgnoreCase("LATITUDE")) {
					latb = false;
				}
				if (qName.equalsIgnoreCase("LONGITUDE")) {
					lonb = false;
				}
				if (qName.equalsIgnoreCase("MARKER")) {
					markers.add(new LatLng(latl, lonl));
				}
			}
			
			};
			
			saxParser.parse(in, handler);
			return markers;
		} catch (Exception e) {
		       e.printStackTrace();
		       return null;
	     }
	}
}
