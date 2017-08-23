package com.sistic.be.patron.session;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sistic.be.cart.seatmap.PatronSelection;
import com.sistic.be.patron.model.PatronLogin;
import com.sistic.be.sales.order.TransactionPayment;

/**
 * 
 * @author jianhong
 * This is a test online user class, used for testing.
 * Not the final version.
 *
 */
public class OnlineUserSession implements Serializable {
	
	private static final long serialVersionUID = -1807931473683135548L;
	
	private String sessionId;
	private PatronSelection patronSelection;
	private String cartGuid;
	private OffsetDateTime cartTimer;
	private TransactionPayment transactionPayment;
	private PatronLogin patronLogin;
	
    /**
     * List of products that user does not want to show donation for current session
     */
    private List<Long> addonExclusion;
  
    public List<Long> getAddonExclusion() {
      return addonExclusion;
    }
  
    public void setAddonExclusion(List<Long> excludedAddon) {
      this.addonExclusion = excludedAddon;
    }
  
    /**
     * When user click on "No, thanks" of a donation line item -> this product's donation wont be
     * shown again in current session
     * 
     * @param productId
     */
    public void excludeAddon(Long productId) {
      this.addonExclusion.add(productId);
    }

	public OnlineUserSession() {
		// TODO: Clean up
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		this.sessionId = attr.getSessionId();
		this.patronSelection = new PatronSelection();
		this.cartGuid = "";
		this.addonExclusion = new ArrayList<Long>();
	}
	
	// Getter Setter
	public PatronSelection getPatronSelection() {
		if (this.patronSelection == null) {
			this.patronSelection = new PatronSelection();
		}
		return patronSelection;
	}

	public String getCartGuid() {
		return cartGuid;
	}

	public void setCartGuid(String cartGuid) {
		this.cartGuid = cartGuid;
	}

	public TransactionPayment getTransactionPayment() {
		return transactionPayment;
	}

	public void setTransactionPayment(TransactionPayment transactionPayment) {
		this.transactionPayment = transactionPayment;
	}

	public PatronLogin getPatronLogin() {
		return patronLogin;
	}

	public void setPatronLogin(PatronLogin patronLogin) {
		this.patronLogin = patronLogin;
	}

	public void setPatronSelection(PatronSelection patronSelection) {
		this.patronSelection = patronSelection;
	}

	public OffsetDateTime getCartTimer() {
		return cartTimer;
	}

	public void setCartTimer(OffsetDateTime cartTimer) {
		this.cartTimer = cartTimer;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public String toString() {
		return "OnlineUserSession [sessionId=" + sessionId + ", patronSelection=" + patronSelection + ", cartGuid="
				+ cartGuid + ", cartTimer=" + cartTimer + ", transactionPayment=" + transactionPayment
				+ ", patronLogin=" + patronLogin + "]";
	}	
	
}
