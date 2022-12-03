package com.iesfranciscodelosrios.Proyecto_Hilos.model;

import java.util.ArrayList;
import java.util.Random;

import com.iesfranciscodelosrios.Proyecto_Hilos.PrincipalController;

import javafx.scene.image.ImageView;

public class Race extends Thread {
	private ImageView img;
	private PrincipalController runner;
	private volatile boolean exit = true;
	private String time;
	
	public Race(ImageView img, PrincipalController runner) {
		this.img = img;
		this.runner = runner;
	}
	
	public Race() {
	}

	@Override
	public void run() {
		this.startThread();
	}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * Metodo que genera numeros aleatorios para el avance de las imágenes, cuando llega a la meta
	 * se sale del bucle, provocando que ese hilo haya sido el ganador
	 */
	public void startThread() {
		double runner1 = 0;
		String millis="";
		String seconds="";
		
		while(exit) {
			try {
				sleep((int)(Math.random() * 1000));
				runner1 = img.getLayoutX();
				Random r = new Random();
				int low = 10;
				int high = 30;
				int result = r.nextInt(high-low) + low;
				
				if(runner1 < runner.getBarrera().getLayoutX() -50) {
					img.setLayoutX(img.getLayoutX() + result);
				} else {
					seconds = runner.getSeconds().getText();
					millis = runner.getMillis().getText();
					time = seconds.concat("."+millis);
					runner.getTimes().add(time);
					runner.getId().add(img.getId());
					this.exit = false;
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(runner.getTimes().size() == 4) {
			runner.getTime().setVisible(true);
			runner.stopCro();
			runner.getStart().setVisible(true);
		} 
	}
	
	/**
	 * Metodo que pone la variable exit a false, que después llamaremos en el controlador para parar los hilos
	 */
	public void stopThread() {
	      this.exit = false;
	 }
	
	/**
	 * Metodo que pone la variable exit a true, para poder reanudar la carrera por donde se haya quedado
	 */
	public void reanudeThread() {
	      this.exit = true;
	 }
}
