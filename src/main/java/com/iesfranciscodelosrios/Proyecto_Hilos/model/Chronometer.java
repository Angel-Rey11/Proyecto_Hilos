package com.iesfranciscodelosrios.Proyecto_Hilos.model;

public class Chronometer {
	private int second;
	private int milisecond;
	
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		this.second = second;
	}
	public int getMilisecond() {
		return milisecond;
	}
	public void setMilisecond(int milisecond) {
		this.milisecond = milisecond;
	}
	
	public Chronometer(int second, int milisecond) {
		super();
		this.second = second;
		this.milisecond = milisecond;
	}

	public Chronometer() {
		super();
	}
	@Override
	public String toString() {
		return "Chronometer [second=" + second + ", milisecond=" + milisecond + "]";
	}
}
