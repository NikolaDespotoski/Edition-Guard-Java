package com.edition.guard.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.edition.guard.model.AbstractRequestModel;

public class CheckUtil {

	public static<T extends AbstractRequestModel>  Object[] checkForEmptyField(T m){		
			return iterateOverFields(m);
		
	}
	private static boolean isNull(Object o){
		return o == null;
	}
	@SuppressWarnings("unchecked")
	private static <T extends AbstractRequestModel> Object[] iterateOverFields(T m){
		Field[] fields = m.getClass().getDeclaredFields();
		m = (T) m.getClass().cast(m);
		List<String> possibleEmpty = new ArrayList<String>();
		for(Field f: fields){
			f.setAccessible(true);
			try {
			//	System.out.println(f.get(m));
				if(!f.getClass().isPrimitive() && isNull(f.get(m))){
					possibleEmpty.add(f.getName());
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return possibleEmpty.toArray();
	}
	public static boolean isValidResourceId(String res){
		return res.startsWith("urn:uuid:");
	}
}
