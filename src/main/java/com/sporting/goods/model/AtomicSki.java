package com.sporting.goods.model;

import java.util.Date;

import com.sporting.goods.enums.DownHillSkiingLevel;
import com.sporting.goods.enums.SnowBoardStance;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class AtomicSki {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long atomicSkiId;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	private String city;
	
	@Column
	private String phone;
	
	@Column
	private String address;
	
	@Column
	private String email;
	
	@Column
	private Integer stateId;
	
	@Column
	private String zipCode;
	
	@Column
	private Boolean canWeContact;
	
	@Column
	private String localAccomodation;
	
	@Column
	private String drivingLicenseNo;
	
	@Column 
	private Integer drivingLicenseStateId;
	
	@Column
	private Integer age;
	
	@Column
	private String dateOfBirth;
	
	@Column
	private String weight;
	
	@Column
	private String height;
	
	@Column
	private DownHillSkiingLevel downHillSkiingLevel;
	
	@Column
	private SnowBoardStance snowboardStance;
	
	@Column
	private String bootIdNo;
	
	@Column
	private String soleLength;
	
	@Column
	private String skiBoardIdNo;
	
	@Column
	private String poleCode;
	
	@Column
	private String dateOut;
	
	@Column
	private String dateDue;
	
	@Column
	private String visualIndicatorSettings;
	
	@Column
	private String requestedSettings;
	
	@Column
	private Integer equipmentSubtotal;
	
	@Column
	private Integer equipmentProtectionDamage;
	
	@Column
	private Integer total;
	
	@Column
	private String technician;
	
	@Column
	private Boolean releaseOfSignedLiability;
	
	@Column
	private String note;
	
	@ManyToOne
	private Employee lastModifiedBy;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModified;
	
	@Column
	private Integer active;
	
	@Column
	private Boolean otherThanUsa;
	
	@Column
	private String province;
	
	@Column
	private String country;
	
	@Column
	private String filePath;
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Boolean getOtherThanUsa() {
		return otherThanUsa;
	}

	public void setOtherThanUsa(Boolean otherThanUsa) {
		this.otherThanUsa = otherThanUsa;
	}

	public Employee getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(Employee lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Long getAtomicSkiId() {
		return atomicSkiId;
	}

	public void setAtomicSkiId(Long atomicSkiId) {
		this.atomicSkiId = atomicSkiId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Boolean getCanWeContact() {
		return canWeContact;
	}

	public void setCanWeContact(Boolean canWeContact) {
		this.canWeContact = canWeContact;
	}

	public String getLocalAccomodation() {
		return localAccomodation;
	}

	public void setLocalAccomodation(String localAccomodation) {
		this.localAccomodation = localAccomodation;
	}

	public String getDrivingLicenseNo() {
		return drivingLicenseNo;
	}

	public void setDrivingLicenseNo(String drivingLicenseNo) {
		this.drivingLicenseNo = drivingLicenseNo;
	}

	public Integer getDrivingLicenseStateId() {
		return drivingLicenseStateId;
	}

	public void setDrivingLicenseStateId(Integer drivingLicenseStateId) {
		this.drivingLicenseStateId = drivingLicenseStateId;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public DownHillSkiingLevel getDownHillSkiingLevel() {
		return downHillSkiingLevel;
	}

	public void setDownHillSkiingLevel(DownHillSkiingLevel downHillSkiingLevel) {
		this.downHillSkiingLevel = downHillSkiingLevel;
	}

	public SnowBoardStance getSnowboardStance() {
		return snowboardStance;
	}

	public void setSnowboardStance(SnowBoardStance snowboardStance) {
		this.snowboardStance = snowboardStance;
	}

	public String getBootIdNo() {
		return bootIdNo;
	}

	public void setBootIdNo(String bootIdNo) {
		this.bootIdNo = bootIdNo;
	}

	public String getSoleLength() {
		return soleLength;
	}

	public void setSoleLength(String soleLength) {
		this.soleLength = soleLength;
	}

	public String getSkiBoardIdNo() {
		return skiBoardIdNo;
	}

	public void setSkiBoardIdNo(String skiBoardIdNo) {
		this.skiBoardIdNo = skiBoardIdNo;
	}

	public String getPoleCode() {
		return poleCode;
	}

	public void setPoleCode(String poleCode) {
		this.poleCode = poleCode;
	}

	public String getDateOut() {
		return dateOut;
	}

	public void setDateOut(String dateOut) {
		this.dateOut = dateOut;
	}

	public String getDateDue() {
		return dateDue;
	}

	public void setDateDue(String dateDue) {
		this.dateDue = dateDue;
	}

	public String getVisualIndicatorSettings() {
		return visualIndicatorSettings;
	}

	public void setVisualIndicatorSettings(String visualIndicatorSettings) {
		this.visualIndicatorSettings = visualIndicatorSettings;
	}

	public String getRequestedSettings() {
		return requestedSettings;
	}

	public void setRequestedSettings(String requestedSettings) {
		this.requestedSettings = requestedSettings;
	}

	public Integer getEquipmentSubtotal() {
		return equipmentSubtotal;
	}

	public void setEquipmentSubtotal(Integer equipmentSubtotal) {
		this.equipmentSubtotal = equipmentSubtotal;
	}

	public Integer getEquipmentProtectionDamage() {
		return equipmentProtectionDamage;
	}

	public void setEquipmentProtectionDamage(Integer equipmentProtectionDamage) {
		this.equipmentProtectionDamage = equipmentProtectionDamage;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getTechnician() {
		return technician;
	}

	public void setTechnician(String technician) {
		this.technician = technician;
	}

	public Boolean getReleaseOfSignedLiability() {
		return releaseOfSignedLiability;
	}

	public void setReleaseOfSignedLiability(Boolean releaseOfSignedLiability) {
		this.releaseOfSignedLiability = releaseOfSignedLiability;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "AtomicSki [atomicSkiId=" + atomicSkiId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", city=" + city + ", phone=" + phone + ", address=" + address + ", email=" + email + ", stateId="
				+ stateId + ", zipCode=" + zipCode + ", canWeContact=" + canWeContact + ", localAccomodation="
				+ localAccomodation + ", drivingLicenseNo=" + drivingLicenseNo + ", drivingLicenseStateId="
				+ drivingLicenseStateId + ", age=" + age + ", dateOfBirth=" + dateOfBirth + ", weight=" + weight
				+ ", height=" + height + ", downHillSkiingLevel=" + downHillSkiingLevel + ", snowboardStance="
				+ snowboardStance + ", bootIdNo=" + bootIdNo + ", soleLength=" + soleLength + ", skiBoardIdNo="
				+ skiBoardIdNo + ", poleCode=" + poleCode + ", dateOut=" + dateOut + ", dateDue=" + dateDue
				+ ", visualIndicatorSettings=" + visualIndicatorSettings + ", requestedSettings=" + requestedSettings
				+ ", equipmentSubtotal=" + equipmentSubtotal + ", equipmentProtectionDamage="
				+ equipmentProtectionDamage + ", total=" + total + ", technician=" + technician
				+ ", releaseOfSignedLiability=" + releaseOfSignedLiability + ", note=" + note + ", lastModifiedBy="
				+ lastModifiedBy + ", lastModified=" + lastModified + ", active=" + active + "]";
	}
	
}
