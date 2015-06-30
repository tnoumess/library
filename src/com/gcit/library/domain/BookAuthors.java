/**
 * 
 */
package com.gcit.library.domain;

/**
 * @author Thierry Edson Noumessi
 *
 * @date Jun 29, 2015
 * @10:39:04 PM
 * @BookAuthors.java
 */
public class BookAuthors {
	
	private int bookId;
	private int authorId;
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
	 * @return the authorId
	 */
	public int getAuthorId() {
		return authorId;
	}
	/**
	 * @param authorId the authorId to set
	 */
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + authorId;
		result = prime * result + bookId;
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
		if (!(obj instanceof BookAuthors)) {
			return false;
		}
		BookAuthors other = (BookAuthors) obj;
		if (authorId != other.authorId) {
			return false;
		}
		if (bookId != other.bookId) {
			return false;
		}
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BookAuthors [bookId=" + bookId + ", authorId=" + authorId + "]";
	}

	
}
