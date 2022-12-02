package com.iesfranciscodelosrios.Proyecto_Hilos;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.iesfranciscodelosrios.Proyecto_Hilos.model.Chronometer;
import com.iesfranciscodelosrios.Proyecto_Hilos.model.Race;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class PrincipalController implements Runnable,Initializable{
	@FXML
	private ImageView runner1;
	@FXML
	private ImageView runner2;
	@FXML
	private ImageView runner3;
	@FXML
	private ImageView runner4;
	@FXML
	private Pane barrera;
	@FXML
	private Button reanudar;
	@FXML
	private Button stop;
	@FXML
	private Button rerace;
	@FXML
	private Label millis;
	@FXML
	private Label seconds;
	@FXML
	private Button time;
	@FXML
	private Label linea1;
	@FXML
	private Label linea2;
	@FXML
	private Label linea3;
	@FXML
	private Label linea4;
	@FXML
	private Label tiempo1;
	@FXML
	private Label tiempo2;
	@FXML
	private Label tiempo3;
	@FXML
	private Label tiempo4;
	@FXML
	private AnchorPane tiempos;
	private boolean started;
	private Chronometer cro;
	private Thread thread;
	private ArrayList<String> times = new ArrayList<String>();
	private ArrayList<String> id = new ArrayList<String>();
	Race j1;
	Race j2;
	Race j3;
	Race j4;
	
	public ImageView getRunner1() {
		return runner1;
	}
	
	public void setRunner1(ImageView runner1) {
		this.runner1 = runner1;
	}
	
	public ImageView getRunner2() {
		return runner2;
	}
	
	public void setRunner2(ImageView runner2) {
		this.runner2 = runner2;
	}
	
	public ImageView getRunner3() {
		return runner3;
	}
	
	public void setRunner3(ImageView runner3) {
		this.runner3 = runner3;
	}
	
	public ImageView getRunner4() {
		return runner4;
	}
	
	public void setRunner4(ImageView runner4) {
		this.runner4 = runner4;
	}
	
	public Pane getBarrera() {
		return barrera;
	}
	
	public void setBarrera(Pane barrera) {
		this.barrera = barrera;
	}
	
	public Label getMillis() {
		return millis;
	}
	
	public void setMillis(Label millis) {
		this.millis = millis;
	}
	
	public Label getSeconds() {
		return seconds;
	}
	
	public void setSeconds(Label seconds) {
		this.seconds = seconds;
	}
	
	public Button getTime() {
		return time;
	}
	
	public void setTime(Button time) {
		this.time = time;
	}
	
	public ArrayList<String> getTimes() {
		return times;
	}
	
	public void setTimes(ArrayList<String> times) {
		this.times = times;
	}
	
	public ArrayList<String> getId() {
		return id;
	}
	
	public void setId(ArrayList<String> id) {
		this.id = id;
	}
	/**
	 * Metodo para empezar la carrera donde creamos los hilos
	 */
	@FXML
	private void startRace() {
		j1 = new Race(runner1,this);
		j2 = new Race(runner2,this);
		j3 = new Race(runner3,this);
		j4 = new Race(runner4,this);
		
		j1.start();
		j2.start();
		j3.start();
		j4.start();
		startCro();
	
		rerace.setVisible(true);
		rerace.setDisable(false);
	}
	
	/**
	 * Metodo para empezar el cronometro
	 */
	private void startCro() {
			started = true;
			cro = new Chronometer();
			thread = new Thread(this);
			thread.start();
	}
	
	/**
	 * Metodo para parar el cronometro
	 */
	public void stopCro() {
		this.started = false;
	}
	
	/**
	 * Boton que muestra una tabla para ver los tiempos y el ganador de la carrera
	 */
	@FXML
	private void viewTime() {
		tiempos.setVisible(true);
		linea1.setText(id.get(0));
		linea2.setText(id.get(1));
		linea3.setText(id.get(2));
		linea4.setText(id.get(3));
		tiempo1.setText(times.get(0));
		tiempo2.setText(times.get(1));
		tiempo3.setText(times.get(2));
		tiempo4.setText(times.get(3));
	}
	
	/**
	 * Boton para cerrar la pestaña de tiempos
	 */
	@FXML
	private void closeTimes() {
		tiempos.setVisible(false);
	}
	
	/**
	 * Boton para reiniciar la carrera
	 */
	@FXML
	private void restartRace() {
		tiempos.setVisible(false);
		reRace();
	}
	
	/**
	 * Metodo para parar la carrera, se paran los hilos
	 */
	@FXML
	private void stopRace() {
		j1.stopThread();
		j2.stopThread();
		j3.stopThread();
		j4.stopThread();
		this.started = false;
		stop.setVisible(false);
		stop.setDisable(true);
		reanudar.setVisible(true);
		reanudar.setDisable(false);
	}
	
	/**
	 * Metodo para empezar de nuevo la carrera, ponemos la posicion a 0 y comienza la carrera de nuevo
	 */
	@FXML
	private void reRace() {
		runner1.setLayoutX(0);
		runner2.setLayoutX(0);
		runner3.setLayoutX(0);
		runner4.setLayoutX(0);
		startRace();	
	}
	
	/**
	 * Metodo para reanudar la carrera despues de que los hilos hayan sido parados
	 * Este boton no se muestra hasta que le demos al boton de parar
	 */
	@FXML
	private void reanudeRace() {
		j1.reanudeThread();
		j2.reanudeThread();
		j3.reanudeThread();
		j4.reanudeThread();
		j1 = new Race(runner1,this);
		j2 = new Race(runner2,this);
		j3 = new Race(runner3,this);
		j4 = new Race(runner4,this);
		
		j1.start();
		j2.start();
		j3.start();
		j4.start();
		
		this.started = true;
		thread = new Thread(this);
		thread.start();
		
		reanudar.setVisible(false);
		reanudar.setDisable(true);
		stop.setVisible(true);
		stop.setDisable(false);
	}
	
	/**
	 * Inicia el programa con los botones que queremos que no funcionen al principio
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		reanudar.setDisable(true);
		rerace.setVisible(false);
		rerace.setDisable(true);
		time.setVisible(false);
	}
	
	/**
	 * Metodo run para contar con un hilo el cronometro y setearlo en los label que queremos
	 */
	@Override
	public void run() {
		try {
			while(started) {
				Thread.sleep(4);
				int milli = cro.getMilisecond();
				milli += 4;
				cro.setMilisecond(milli);
				
				if(cro.getMilisecond() >= 1000) {
					cro.setMilisecond(0);
					int seconds = cro.getSecond();
					seconds++;
					cro.setSecond(seconds);
				}
				
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					if(cro.getMilisecond() < 100) {
						millis.setText("0" + cro.getMilisecond());
						
						if(cro.getMilisecond() < 10) {
							millis.setText("0" + "0" + cro.getMilisecond());
						}
					} else {
						millis.setText(String.valueOf(cro.getMilisecond()));
					}
					if(cro.getSecond() < 10) {
						seconds.setText("0" + cro.getSecond());
					} else {
						seconds.setText(String.valueOf(cro.getSecond()));
					}
					
				}
			});
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
