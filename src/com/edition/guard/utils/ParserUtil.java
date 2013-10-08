package com.edition.guard.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.edition.guard.model.PackagingResponse;
import com.edition.guard.model.StatusReportResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class ParserUtil {

  private static Gson sGSON;
public static PackagingResponse parsePackagingResponse(String response){
	  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	 Document d;
	try {
		d = dbf.newDocumentBuilder().parse(new ByteArrayInputStream(response.getBytes()));
		 NodeList all = d.getElementsByTagName("*");
		 PackagingResponse r = new PackagingResponse();
		// iteratePackageNodeList(all,r );
		 return r;
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;

	 
  }
  public static <T> T getResponse(String json, Class<T> cls){
	  	 GsonBuilder b = new GsonBuilder();
		 b.registerTypeHierarchyAdapter(cls, new EditonGuardDeserializer<T>());
		 Gson g = b.create();
		 return g.fromJson(json, cls);
  }
  
  private static Gson getGSon(){
	  if(sGSON == null)
		  sGSON = new Gson();
	  return sGSON;
  }
  public static List<StatusReportResponse> getStatusReportResponses(String json){
		  Type listType = new TypeToken<List<StatusReportResponse>>(){}.getType();
		  List<StatusReportResponse> reports = (List<StatusReportResponse>)getGSon().fromJson(json, listType);
		  return reports;
  }
  public static String toJSON(String xml, boolean preprocess){
	 try {
		 if(preprocess)
			 xml = xml.replace("dc:", "").replace(" xmlns:dc=\"http://purl.org/dc/elements/1.1/\"", "").replace("xmlns=\"http://ns.adobe.com/adept\"", "");
		return org.json.XML.toJSONObject(xml).toString();
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
  }
  private static String getValue(String tag, Element element) {
	  NodeList nodes = element.getChildNodes();
	  Node node = (Node) nodes.item(0);
	  return node.getNodeValue();
	  }

 
}
