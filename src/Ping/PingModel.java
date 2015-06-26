package Ping;

import java.io.*;
import java.awt.event.*; // Para el manejo de eventos, necesario para el Timer.

import javax.swing.Timer;

import Beat.BPMObserver;
import Beat.BeatObserver;

import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;

public class PingModel {
	
	String command;
	String tms; //tiempo de respuesta
	int pingMedio;
	int suma;
	int frecDefecto = 4000;
	int env, rec, per; // estados
	boolean noRespuesta;
	boolean pingIncorrecto;
	Timer time;
	String ip;
	int frec = 0;
	ArrayList beatObservers = new ArrayList();
	ArrayList bpmObservers = new ArrayList();
	
	public PingModel(){
		
	}

//-------------------------------------------------------------------------------------------------------
//FUNCIONES QUE CORRESPONDEN AL PUNTO 2
//-------------------------------------------------------------------------------------------------------
	
	void onCycle(){
		env = 0;				//Cada ciclo nuevo estos valores se reinicializan
		rec = 0;
		per = 0;
		suma = 0;
		env = 0;
		pingIncorrecto = false;
		noRespuesta = false;
		if (frec == 0){
			
			time = new Timer(frecDefecto,new ActionListener(){
				public void actionPerformed (ActionEvent evn){
					doCommand();
				}
			});
		}
		else
		{
			time.setDelay(frec);
		}
		time.start();
	}
	
	void offCycle(){
		time.stop();	
	}
	
	void setURL(String ip){
		this.ip = ip;
		doCommand();
	}
	
	String getPing(){
		return tms;
	}
	
	//NOT SURE ABOUT THIS PART
	public void registerObserver(BeatObserver o) {
		beatObservers.add(o);
	}
  
	public void notifyBeatObservers() {
		for(int i = 0; i < beatObservers.size(); i++) {
			BeatObserver observer = (BeatObserver)beatObservers.get(i);
			observer.updateBeat();
		}
	}
  
	public void registerObserver(BPMObserver o) {
		bpmObservers.add(o);
	}
  
	public void notifyBPMObservers() {
		for(int i = 0; i < bpmObservers.size(); i++) {
			BPMObserver observer = (BPMObserver)bpmObservers.get(i);
			observer.updateBPM();
		}
	}
	
//-------------------------------------------------------------------------------------------------------
//FUNCIONES PARA LA NUEVA VISTA
//-------------------------------------------------------------------------------------------------------

	
	void setFrec(int frec){
		this.frec = frec;
	}
	
//-------------------------------------------------------------------------------------------------------
//FUNCIONES NECESARIAS PARA REALIZAR LOS COMANDOS DE PING DE WINDOWS
//-------------------------------------------------------------------------------------------------------

	String getCommand(){
		
		command = "ping -n 1 " + ip;
		
		return command;
	}
	
	void doCommand(){
		
		command = getCommand();
		 try
	        {
	            Runtime r = Runtime.getRuntime();
	            Process p = r.exec(command);
	 
	            // Inicializa el lector del buffer
	            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
	 
	            String inputLine;
	            int i = 0;
	            // Bucle mientas reciba parametros del buffer
	            while ((inputLine = in.readLine()) != null)
	            {
	            	if (i>0){
		            	parsing(inputLine);
	            	}
	            	i++;
	            }
	            in.close();
	        } catch (IOException e) {
	            System.out.println(e);
	        }
	}
	
	void parsing(String line){
		
		String comienzo1 = "Respuesta desde"; //español
		String beginning1 = "Reply from"; //ingles
		String comienzo2 = "Tiempo de respuesta agotado";
		String beginning2 = "Request timed out";
		
		String[] buffer1;
		String[] buffer2;
		String[] buffer3;
		
		if (line.startsWith(comienzo1) || line.startsWith(beginning1)){
			buffer1 = line.split(":");
			buffer2 = buffer1[1].split("=");
			buffer3 = buffer2[2].split(" ");
			tms = buffer3[0];
			env++;
			rec++;
			pingMedio = promedio(tms);
		}
		
		if (line.startsWith(comienzo2) || line.startsWith(beginning2)){
			env++;
			per++;
			noRespuesta = true;
		}
		
		else{
			pingIncorrecto = true;
		}
	}
	
	int promedio(String tMS){
		String[] num = tMS.split("m");
		int a =Integer.parseInt(num[0]);
		suma= suma+a;

		return suma/rec;
	}
}