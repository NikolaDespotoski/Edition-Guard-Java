package com.edition.guard.model;

public class PackagingResponse{

	
	private String downloadType;
	private String resourceItem;
	private String resource;
	private String title;
	private String publisher;
	private String src;
	private String modified;
	private String author;
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	
	public static PackagingResponse getInstance(){
		return new PackagingResponse();
	}
	public String getDownloadType() {
		return downloadType;
	}

	public void setDownloadType(String downloadType) {
		this.downloadType = downloadType;
	}

	public String getResourceItem() {
		return resourceItem;
	}

	public void setResourceItem(String resourceItem) {
		this.resourceItem = resourceItem;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}
	
}