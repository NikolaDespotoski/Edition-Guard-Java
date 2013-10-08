package com.edition.guard.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;


public class HttpClientHelper {

    public static final int HTTP_TIMEOUT = 60 * 1000;

    private static HttpClient mHttpClient;

    @SuppressWarnings("deprecation")
	private static HttpClient getHttpClient() {
        if (mHttpClient == null) {
            BasicHttpParams basicParams = new BasicHttpParams();
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            final SSLSocketFactory sslSocketFactory = SSLSocketFactory.getSocketFactory();
            schemeRegistry.register(new Scheme("https", sslSocketFactory, 443));
            ClientConnectionManager cm = new ThreadSafeClientConnManager(basicParams,
                    schemeRegistry);
            mHttpClient = new DefaultHttpClient(cm, basicParams);
            final HttpParams params = mHttpClient.getParams();
            HttpConnectionParams.setConnectionTimeout(params, HTTP_TIMEOUT);
            HttpConnectionParams.setSoTimeout(params, HTTP_TIMEOUT);
            ConnManagerParams.setTimeout(params, HTTP_TIMEOUT);

        }
        return mHttpClient;
    }

    public static String executeHttpPost(String url, ArrayList<BasicNameValuePair> postParameters)
            throws Exception {
        BufferedReader in = null;
        try {
            HttpClient client = getHttpClient();
         
            HttpPost request = new HttpPost(url);
            MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null, Charset.forName(HTTP.UTF_8));

         
         
            for(BasicNameValuePair p: postParameters){
            	if(!p.getName().equals("file")){
            		System.out.println("Multi part param: " + p.getName() + " Value: "+p.getValue());
	            	reqEntity.addPart(p.getName(), new StringBody(p.getValue()));
	            //	if(p.getName().equals("hash"))
	            //		reqEntity.addPart(p.getName(), new StringBody(p.getValue().substring(0, p.getValue().length()-3)));
            	}else {
            		  FileBody fileBody  = new FileBody(new File(p.getValue()));
            		  reqEntity.addPart("file",fileBody);
            	}
            }
            reqEntity.addPart("format", new StringBody("json"));
         
            request.setEntity(reqEntity);
            client.getParams()
            .setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
            //System.exit(-1);
            HttpResponse response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();

            String result = sb.toString();
            return result;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                 }
            }
        }
    }

    public static InputStream executeHttpGetStream(String url) throws IllegalStateException,
            IOException, URISyntaxException {
        HttpClient client = getHttpClient();
        HttpGet request = new HttpGet();
        request.setURI(new URI(url));
        HttpResponse response = client.execute(request);
        return response.getEntity().getContent();
    }

    public static String executeHttpGet(String url) {

        BufferedReader in = null;
        try {
            HttpClient client = getHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(url));
            HttpResponse response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }

            String result = sb.toString();
         
            return result;
        } catch (Exception e) {
            return e.toString();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                  }
            }
        }
    }
  
}
