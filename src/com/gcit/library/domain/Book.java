/**
 * 
 */
package com.gcit.library.domain;

/**
 * @author Thierry Edson Noumessi
 *
 * @date Jun 29, 2015
 * @10:33:58 PM
 * @Book.java
 */
public class Book {
	private int bookId;
	private String bookTitle;
	private int publisherId;
	/**
	 * @return the bookId
	 */
	public int getBookId() {
		return bookId;
	}
	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	/**
	 * @return the bookTitle
	 */
	public String getBookTitle() {
		return bookTitle;
	}
	/**
	 * @param bookTitle the bookTitle to set
	 */
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	/**
	 * @return the publisherId
	 */
	public int getPublisherId() {
		return publisherId;
	}
	/**
	 * @param publisherId the publisherId to set
	 */
	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookId;
		result = prime * result
				+ ((bookTitle == null) ? 0 : bookTitle.hashCode());
		result = prime * result + publisherId;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Book)) {
			return false;
		}
		Book other = (Book) obj;
		if (bookId != other.bookId) {
			return false;
		}
		if (bookTitle == null) {
			if (other.bookTitle != null) {
				return false;
			}
		} else if (!bookTitle.equals(other.bookTitle)) {
			return false;
		}
		if (publisherId != other.publisherId) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookTitle=" + bookTitle
				+ ", publisherId=" + publisherId + "]";
	}
	

}
