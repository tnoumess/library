/**
 * 
 */
package com.gcit.library.domain;

/**
 * @author Thierry Edson Noumessi
 *
 * @date Jun 29, 2015
 * @10:33:45 PM
 * @Borrower.java
 */
public class Borrower {
	private int borrId;
	private String Name;
	private String Address;
	private String phone;
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
	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return Address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		Address = address;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Address == null) ? 0 : Address.hashCode());
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
		result = prime * result + borrId;
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
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
		if (!(obj instanceof Borrower)) {
			return false;
		}
		Borrower other = (Borrower) obj;
		if (Address == null) {
			if (other.Address != null) {
				return false;
			}
		} else if (!Address.equals(other.Address)) {
			return false;
		}
		if (Name == null) {
			if (other.Name != null) {
				return false;
			}
		} else if (!Name.equals(other.Name)) {
			return false;
		}
		if (borrId != other.borrId) {
			return false;
		}
		if (phone == null) {
			if (other.phone != null) {
				return false;
			}
		} else if (!phone.equals(other.phone)) {
			return false;
		}
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Borrower [borrId=" + borrId + ", Name=" + Name + ", Address="
				+ Address + ", phone=" + phone + "]";
	}

	
	
}
