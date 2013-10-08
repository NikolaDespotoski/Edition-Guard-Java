package com.edition.guard.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

import com.edition.guard.init.EditionGuard;
import com.edition.guard.model.AbstractRequestModel;
import com.edition.guard.model.EditionGuardHash;

public class EditionGuardParamUtil {

	@SuppressWarnings("unchecked")
	public static <T extends AbstractRequestModel> ArrayList<BasicNameValuePair> asNameValuePairList(T request){
		Field[] fields = request.getClass().getDeclaredFields();
		request = (T) request.getClass().cast(request);
		ArrayList<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
		list.add(0,new BasicNameValuePair("email", EditionGuard.getEmail()));
			EditionGuardHash h = (EditionGuardHash) request.getHash();
			list.add(1,new BasicNameValuePair("hash", h.getHash()));
			list.add(2,new BasicNameValuePair("nonce",h.getNonce()));
		for(Field f: fields){
			f.setAccessible(true);
			try {
					if(!f.getDeclaringClass().isAssignableFrom(EditionGuardHash.class) && !f.getName().equals("hash") && !f.getName().equals("mHash") && !f.getName().equals("nonce")){
			
						if(!f.getDeclaringClass().isPrimitive())
							list.add(new BasicNameValuePair(f.getName(), f.get(request).toString()));
						else if	(f.getLong(f) > -1 )
							list.add(new BasicNameValuePair(f.getName(), f.get(request).toString()));
					}
					
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return list;
	}
}
