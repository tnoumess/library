/**
 * 
 */
package com.gcit.library.domain;

/**
 * @author Thierry Edson Noumessi
 *
 * @date Jun 29, 2015
 * @10:35:15 PM
 * @Loans.java
 */
public class Loans {
	
	private int bookId;
	private int branchId;
	private int borrId;
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
	 * @return the borrId
	 */
	public int getBorrId() {
		return borrId;
	}
	/**
	 * @param borrId the borrId to set
	 */
	public void setBorrId(int borrId) {
		this.borrId = borrId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookId;
		result = prime * result + borrId;
		result = prime * result + branchId;
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
		if (!(obj instanceof Loans)) {
			return false;
		}
		Loans other = (Loans) obj;
		if (bookId != other.bookId) {
			return false;
		}
		if (borrId != other.borrId) {
			return false;
		}
		if (branchId != other.branchId) {
			return false;
		}
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Loans [bookId=" + bookId + ", branchId=" + branchId
				+ ", borrId=" + borrId + "]";
	}

	
	
}
