package com.sistic.be.patron.api.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PatronTransaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6212995567767323392L;
	
	private List<TransactionDetail> transactions;
	private Integer totalPage;
	private Integer currentPage;
	private Integer totalRow;

	public List<TransactionDetail> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionDetail> transactions) {
		this.transactions = transactions;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(Integer totalRow) {
		this.totalRow = totalRow;
	}
	
	public PatronTransaction returnSortedPatronTransaction() {
		if (transactions != null) {
			// sort the transactions by date latest first
			List<TransactionDetail> sortedTransactions = transactions.stream().sorted(Comparator.comparing(TransactionDetail::getDateOfPurchase).reversed()).collect(Collectors.toList());
			for (TransactionDetail transaction : sortedTransactions) {
				List<TransactionLineItem> transactionLineItems = transaction.getLineItems();
				transactionLineItems.sort(TransactionLineItem.transactionLineItemComparator);
			}
			this.setTransactions(sortedTransactions);
		}
		return this;
	}

	@Override
	public String toString() {
		return "PatronTransaction [transactions=" + transactions + ", totalPage=" + totalPage + ", currentPage="
				+ currentPage + ", totalRow=" + totalRow + "]";
	}
	
	
}
