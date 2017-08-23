package com.sistic.be.configuration.multitenant;

public class TenantComponents {

	private Boolean evoucher;
	private Boolean subscription;
	private Boolean membership;
	private Boolean addon;
	private Boolean ticketProtector;
	private Boolean masterpass;
	private Boolean promotion;
	
	public TenantComponents() {
		this.evoucher = false;
		this.subscription = true;
		this.membership = true;
		this.addon = true;
		this.ticketProtector = false;
		this.masterpass = true;
		this.promotion = false;
	}

	public Boolean getEvoucher() {
		return evoucher;
	}

	public void setEvoucher(Boolean evoucher) {
		this.evoucher = evoucher;
	}

	public Boolean getSubscription() {
		return subscription;
	}

	public void setSubscription(Boolean subscription) {
		this.subscription = subscription;
	}

	public Boolean getMembership() {
		return membership;
	}

	public void setMembership(Boolean membership) {
		this.membership = membership;
	}

	public Boolean getAddon() {
		return addon;
	}

	public void setAddon(Boolean addon) {
		this.addon = addon;
	}

	public Boolean getTicketProtector() {
		return ticketProtector;
	}

	public void setTicketProtector(Boolean ticketProtector) {
		this.ticketProtector = ticketProtector;
	}

	public Boolean getMasterpass() {
		return masterpass;
	}

	public void setMasterpass(Boolean masterpass) {
		this.masterpass = masterpass;
	}

	public Boolean getPromotion() {
		return promotion;
	}

	public void setPromotion(Boolean promotion) {
		this.promotion = promotion;
	}

	@Override
	public String toString() {
		return "TenantComponents [evoucher=" + evoucher + ", subscription=" + subscription + ", membership="
				+ membership + ", addon=" + addon + ", ticketProtector=" + ticketProtector + ", masterpass="
				+ masterpass + ", promotion=" + promotion + "]";
	}
	
}
