package com.edition.guard.init;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.protocol.HTTP;

import com.edition.guard.exception.EditionGuardAuthException;
import com.edition.guard.exception.EditionGuardException;
import com.edition.guard.exception.EditionGuardException.EditionGuardExceptionReason;
import com.edition.guard.http.HttpClientHelper;
import com.edition.guard.model.DeleteRequest;
import com.edition.guard.model.EditionGuardError;
import com.edition.guard.model.EditionGuardHash;
import com.edition.guard.model.FulfillmentResponse;
import com.edition.guard.model.FulfilmentRequest;
import com.edition.guard.model.PackagingRequest;
import com.edition.guard.model.PackagingResponse;
import com.edition.guard.model.StatusReportResponse;
import com.edition.guard.model.StatusRequest;
import com.edition.guard.utils.CheckUtil;
import com.edition.guard.utils.Nonce;
import com.edition.guard.utils.ParserUtil;
import com.edition.guard.utils.Signature;


public class EditionGuard {

	public static String PACKAGING_URL_API = "http://www.editionguard.com/api/package";
	public static String DELETION_URL_API = "http://www.editionguard.com/api/delete";
	public static String FULFILLMENT_URL_API = "http://acs4.editionguard.com/fulfillment/URLLink.acsm?%s";
	private static String secret;
	private static String email;
	public interface EditionGuardListener<T>{
		void onCompleteRequest(T responseData);
		void onFailedRequest(String message);
	}
	public String getSecret() {
		return secret;
	}
	public static void setSecret(String s) {
		secret = s;
	}
	public static String getEmail() {
		return email;
	}
	public static void setEmail(String e) {
		email = e;
	}
	
	public static EditionGuardHash getHash(){	
		String nonceString = generateRandomNonce();
		return  new EditionGuardHash(nonceString, Signature.hmacSha1(nonceString+email, secret));
	}
	
	public static String generateRandomNonce(){
		return Nonce.getNextNonce();
	}

	public static PackagingResponse executePackagingRequest(PackagingRequest request, EditionGuardListener<PackagingResponse> listener) throws EditionGuardException{
		Object[] emptyFields = CheckUtil.checkForEmptyField(request);
		if(emptyFields.length == 0 || (emptyFields.length == 1 && Arrays.asList(emptyFields).contains("resource_id"))){
		try {
			String response = HttpClientHelper.executeHttpPost(PACKAGING_URL_API, request.asNameValuePairs());
			System.out.println("Response Original: "+response);
			response = ParserUtil.toJSON(response, true);
			System.out.println("Response JSON: "+response);
			EditionGuardError error = ParserUtil.getResponse(response, EditionGuardError.class);
			if(error.hasError()){
			 throw new EditionGuardAuthException(EditionGuardExceptionReason.AUTH_FAILED, error.getError().getData());
			}else { 
				PackagingResponse pck = ParserUtil.getResponse(response, PackagingResponse.class);
				if(listener != null ) listener.onCompleteRequest(pck);
				else
					return pck;
			}
		} catch (Exception e) {
			// TODO Auto-generated ch block
			e.printStackTrace();
		}
		}else {
			throw new EditionGuardException(EditionGuardExceptionReason.MISSING_PARAM, emptyFields);
		}
			
		return null;
	}
	public static String executeDeleteRequest(DeleteRequest request) throws EditionGuardException{
		if(request.getResourceId() > -1 ){
			try {
				String response = HttpClientHelper.executeHttpPost(DELETION_URL_API, request.asNameValuePairs());
				return response;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			throw new EditionGuardException(EditionGuardExceptionReason.MISSING_PARAM, "resourceId");
		}
		return null;
	}
	public static List<StatusReportResponse> executeStatusRequest(StatusRequest request) throws EditionGuardException{
		Object[] emptyFields = CheckUtil.checkForEmptyField(request);
		if(emptyFields.length == 0){
			try {
				String response =  ParserUtil.toJSON(HttpClientHelper.executeHttpPost(DELETION_URL_API, request.asNameValuePairs()), false);
				EditionGuardError error = ParserUtil.getResponse(response, EditionGuardError.class);
				if(error.hasError())
				 throw new EditionGuardAuthException(EditionGuardExceptionReason.AUTH_FAILED, error.getError().getData());
				else 
					return ParserUtil.getStatusReportResponses(response);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			throw new EditionGuardException(EditionGuardExceptionReason.MISSING_PARAM, emptyFields);
		}
		return null;
	}
	public static FulfillmentResponse executeFulfillmentRequest(FulfilmentRequest ful) throws EditionGuardException{
		if(getEmail()==null)throw new EditionGuardException(EditionGuardExceptionReason.OTHER, "Email is missing. Initialize using EditionGuard.setEmail(\"your@email.com");
		Object[] params = CheckUtil.checkForEmptyField(ful);
		if(params.length > 1)
			throw new EditionGuardException(EditionGuardExceptionReason.MISSING_PARAM,params);
		if(!CheckUtil.isValidResourceId(ful.getResourceIdUrn()))throw new EditionGuardException(EditionGuardExceptionReason.OTHER, "ResourceId is not valid. It should start with: \"urn:uuid:\"");
		long dateEval = System.currentTimeMillis() / 1000L;
		StringBuilder b = new StringBuilder();
		try {			
			b.append("action=enterorder&ordersource=")
			.append(URLEncoder.encode(EditionGuard.getEmail(),HTTP.UTF_8))
			.append("&orderid=")
			.append(URLEncoder.encode(Long.toString(ful.getTransactionId()),HTTP.UTF_8))
			.append("&resid=")
			.append(URLEncoder.encode(ful.getResourceIdUrn(), HTTP.UTF_8))
			.append("&dateval=")
			.append(Long.toString(dateEval))
			.append("&gblver=4");
		    if(ful.getValidUntil() != null)
		    	b.append("&rights=").append(Long.toString(TimeUnit.MILLISECONDS.toSeconds(ful.getValidUntil().getTime())));		    
			String auth = Signature.hmacSha1(b.toString(),secret);
		    b.append("&auth=").append(auth);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String url = String.format(FULFILLMENT_URL_API, b.toString());
		System.out.println(url);
		url = HttpClientHelper.executeHttpGet(url);
		url =  ParserUtil.toJSON(url, true);
		EditionGuardError error = ParserUtil.getResponse(url, EditionGuardError.class);
		System.out.println("RESPONSE: "+url);
		if(error.hasError()){
		 throw new EditionGuardAuthException(EditionGuardExceptionReason.AUTH_FAILED, error.getError().getData());
		}else { 
			return ParserUtil.getResponse(url, FulfillmentResponse.class);
		}
		
	}
	

}
