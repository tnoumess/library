/**
 * 
 */
package com.gcit.library.domain;

/**
 * @author Thierry Edson Noumessi
 *
 * @date Jun 29, 2015
 * @10:35:32 PM
 * @Copies.java
 */
public class Copies {

	private int bookId;
	private int branchId;
	private int nbreOfcopies;
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
	 * @return the branchId
	 */
	public int getBranchId() {
		return branchId;
	}
	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	/**
	 * @return the nbreOfcopies
	 */
	public int getNbreOfcopies() {
		return nbreOfcopies;
	}
	/**
	 * @param nbreOfcopies the nbreOfcopies to set
	 */
	public void setNbreOfcopies(int nbreOfcopies) {
		this.nbreOfcopies = nbreOfcopies;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Copies [bookId=" + bookId + ", branchId=" + branchId
				+ ", nbreOfcopies=" + nbreOfcopies + "]";
	}
	
	
}
