Description
------------

Java wrapper for secure and robust eBook DRM (digital rights management) provided by [Edition Guard](www.editionguard.com)

Usage
------
1. Initialize the EditionGuard with your secret key and email:

<pre><code>EditionGuard.setEmail("your@email.com");
EditionGuard.setSecret("yourKey");
</code></pre>

2. Create your request:

Packaging request:
<pre><code>PackagingRequest request = new PackagingRequest();
request.setAuthor("Auth");
request.setTitle("Title");
request.setHash(EditionGuard.getHash());
request.setPublisher("p");
request.setFile(new File("path/to/your.pdf"));
request.setResourceId(1111);</code></pre>

Fullfilment request:
<pre><code>FulfilmentRequest fr = new FulfilmentRequest();
fr.setResourceId(responseData.getResource());
fr.setTransactionId(Long.parseLong(request.getNonce()));</code></pre>

3. Execute your request
 
Executing packaging request:

<pre><code>try {
PackagingResponse packResponse = EditionGuard.executePackagingRequest(request, new EditionGuardListener<PackagingResponse>(){

				@Override
				public void onCompleteRequest(PackagingResponse responseData) {
					System.out.println(responseData.getDownloadType());
					System.out.println(responseData.getModified());
					System.out.println(responseData.getTitle());
					System.out.println(responseData.getSrc());
					System.out.println(responseData.getResource());
			    }

				@Override
				public void onFailedRequest(String message) {
				    System.out.println("Request failed due: "+message);
					
				}});
		} catch (EditionGuardException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}</code></pre>
Executing fullfilment request:

<pre><code>try {
    FulfillmentResponse fulfil = EditionGuard.executeFulfillmentRequest(fr);
	System.out.println("Fullfilment: "+fulfil.getHmac());
} catch (EditionGuardException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}</code></pre>

Dependencies
------------
[Gson](https://code.google.com/p/google-gson/downloads/list)

[JSON-org](https://code.google.com/p/org-json-java/downloads/list)

[XStream](http://xstream.codehaus.org/download.html)

[HttpComponentClient](http://hc.apache.org/downloads.cgi)



