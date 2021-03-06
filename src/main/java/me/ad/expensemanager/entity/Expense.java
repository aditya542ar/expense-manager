package me.ad.expensemanager.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Expense {
	
	@Id
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator"
	)
	@GeneratedValue(generator = "UUID")
	@Column(name = "txn_id")
	private String txnId;
	
	@NotNull
	@ManyToOne
	private Account fromAccount;
	
	@ManyToOne
	private Account toAccount;
	
	@NotNull
	private double amount;
	
	@Size(max = 200, message = "Expense Description can't be of more than 200 characters")
	@Column(length = 200)
	private String description;
	
	@ManyToOne(cascade=CascadeType.ALL)
	//@JoinColumn(name = "category_FK", referencedColumnName = "category_id")
	private Category category;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private TransactionType txnType;
	
	@CreationTimestamp
	private Date createdDate;
	
	@UpdateTimestamp
	private Date modifiedDate;
	
	@ManyToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "user_id_FK")
	@JsonIgnore
	private User user;
	
	public Expense() {
		super();
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public Account getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}
	public Account getToAccount() {
		return toAccount;
	}
	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public TransactionType getTxnType() {
		return txnType;
	}
	public void setTxnType(TransactionType txnType) {
		this.txnType = txnType;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Expense [txnId=" + txnId + ", fromAccountId=" + fromAccount.getId() 
				+ ", toAccountId=" + toAccount == null ? "" : toAccount.getId()
				+ ", amount=" + amount + ", description=" + description + ", category=" + category + ", txnType="
				+ txnType + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", user.userId=" + user.getId() + "]";
	}
	
	
	
	
}
