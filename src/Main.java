import java.io.File;

import com.edition.guard.exception.EditionGuardException;
import com.edition.guard.init.EditionGuard;
import com.edition.guard.init.EditionGuard.EditionGuardListener;
import com.edition.guard.model.DeleteRequest;
import com.edition.guard.model.FulfillmentResponse;
import com.edition.guard.model.FulfilmentRequest;
import com.edition.guard.model.PackagingRequest;
import com.edition.guard.model.PackagingResponse;
import com.edition.guard.model.ResourceItemInfo;
import com.edition.guard.model.Response;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	EditionGuard.setEmail("your@email.com");
	EditionGuard.setSecret("yourKey");
	final PackagingRequest request = new PackagingRequest();
	request.setAuthor("Auth");
	request.setTitle("Title");
	request.setHash(EditionGuard.getHash());
	request.setPublisher("p");
	request.setFile(new File("path/to/your.pdf"));
	request.setResourceId(1111);
		try {
			EditionGuard.executePackagingRequest(request, new EditionGuardListener<PackagingResponse>(){

				@Override
				public void onCompleteRequest(PackagingResponse responseData) {
					System.out.println(responseData.getDownloadType());
					System.out.println(responseData.getModified());
					System.out.println(responseData.getTitle());
					System.out.println(responseData.getSrc());
					System.out.println(responseData.getResource());
					FulfilmentRequest fr = new FulfilmentRequest();
					fr.setResourceId(responseData.getResource());
					fr.setTransactionId(Long.parseLong(request.getNonce()));
					try {
						FulfillmentResponse fulfil = EditionGuard.executeFulfillmentRequest(fr);
						System.out.println("Fullfilment: "+fulfil.getHmac());
					} catch (EditionGuardException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					DeleteRequest delete = new DeleteRequest();
					delete.setHash(EditionGuard.getHash());
					delete.setResourceId(Long.parseLong(responseData.getResource()));
				}

				@Override
				public void onFailedRequest(String message) {
					// TODO Auto-generated method stub
					
				}});
		} catch (EditionGuardException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
