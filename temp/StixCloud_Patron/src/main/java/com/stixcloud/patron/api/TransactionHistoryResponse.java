package com.stixcloud.patron.api;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stixcloud.patron.api.json.TransactionJson;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"transactions", "totalPage", "currentPage", "totalRow"})
public class TransactionHistoryResponse implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1588760972795171371L;
  @JsonProperty("transactions")
  private List<TransactionJson> transactions;
  @JsonProperty("totalPage")
  private Long totalPage;
  @JsonProperty("currentPage")
  private Integer currentPage;
  @JsonProperty("totalRow")
  private Long totalRow;

  public TransactionHistoryResponse() {
    super();
  }

  public TransactionHistoryResponse(List<TransactionJson> transactions, Long totalPage,
      Integer currentPage, Long totalRow) {
    super();
    this.transactions = transactions;
    this.totalPage = totalPage;
    this.currentPage = currentPage;
    this.totalRow = totalRow;
  }

  /**
   * @return the transactions
   */
  public List<TransactionJson> getTransactions() {
    return transactions;
  }

  /**
   * @param transactions the transactions to set
   */
  public void setTransactions(List<TransactionJson> transactions) {
    this.transactions = transactions;
  }

  /**
   * @return the totalPage
   */
  public Long getTotalPage() {
    return totalPage;
  }

  /**
   * @param totalPage the totalPage to set
   */
  public void setTotalPage(Long totalPage) {
    this.totalPage = totalPage;
  }

  /**
   * @return the currentPage
   */
  public Integer getCurrentPage() {
    return currentPage;
  }

  /**
   * @param currentPage the currentPage to set
   */
  public void setCurrentPage(Integer currentPage) {
    this.currentPage = currentPage;
  }

  /**
   * @return the totalRow
   */
  public Long getTotalRow() {
    return totalRow;
  }

  /**
   * @param totalRow the totalRow to set
   */
  public void setTotalRow(Long totalRow) {
    this.totalRow = totalRow;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    TransactionHistoryResponse that = (TransactionHistoryResponse) o;
    return new EqualsBuilder().append(transactions, that.getTransactions())
        .append(totalPage, that.getTotalPage()).append(currentPage, that.getCurrentPage())
        .append(totalRow, that.getTotalRow()).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(transactions).append(totalPage).append(currentPage)
        .append(totalRow).toHashCode();
  }
}
