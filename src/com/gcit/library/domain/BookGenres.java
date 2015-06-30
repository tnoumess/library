/**
 * 
 */
package com.gcit.library.domain;

/**
 * @author Thierry Edson Noumessi
 *
 * @date Jun 29, 2015
 * @11:42:49 PM
 * @BookGenres.java
 */
public class BookGenres {
	private int genreId;
	private int bookId;
	/**
	 * @return the genreId
	 */
	public int getGenreId() {
		return genreId;
	}
	/**
	 * @param genreId the genreId to set
	 */
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}
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
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookId;
		result = prime * result + genreId;
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
		if (!(obj instanceof BookGenres)) {
			return false;
		}
		BookGenres other = (BookGenres) obj;
		if (bookId != other.bookId) {
			return false;
		}
		if (genreId != other.genreId) {
			return false;
		}
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BookGenres [genreId=" + genreId + ", bookId=" + bookId + "]";
	}
	
	

}
