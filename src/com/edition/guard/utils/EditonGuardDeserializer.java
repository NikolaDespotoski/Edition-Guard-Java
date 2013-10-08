package com.edition.guard.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map.Entry;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class EditonGuardDeserializer<T> implements com.google.gson.JsonDeserializer<T>{


	@Override
	public T deserialize(JsonElement arg0, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		Class klass =  ((Class)arg1);
		Field[] fields = klass.getDeclaredFields();
		T r = null;
		try {
			r = (T)klass.newInstance();
		} catch (InstantiationException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} catch (IllegalAccessException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		JsonObject o = 	arg0.getAsJsonObject();
		parse(o, r);
		
		return r;
	}

	private void parse(JsonObject o, T r){
		Iterator<Entry<String, JsonElement>> i = o.entrySet().iterator();
		while(i.hasNext()){
			Entry<String, JsonElement> e = i.next();
			JsonElement el = e.getValue();
			if(el.isJsonObject())
				parse(el.getAsJsonObject(), r);
			for(Field f : r.getClass().getDeclaredFields()){
				f.setAccessible(true);
				if(f.getName().equals(e.getKey())){
					try {
						f.set(r,el.getAsString());
					} catch (IllegalArgumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			
			}
		}
	}
}
