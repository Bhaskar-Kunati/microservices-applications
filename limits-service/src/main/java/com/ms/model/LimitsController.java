package com.ms.model;

public class LimitsController {
	
	private int maximum;
	private int minimum;
	
	protected LimitsController() {
		
	}
	public LimitsController(int maximum, int minimum) {
		super();
		this.maximum = maximum;
		this.minimum = minimum;
	}
	public int getMaximum() {
		return maximum;
	}
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	public int getMinimum() {
		return minimum;
	}
	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}
	
	
}
