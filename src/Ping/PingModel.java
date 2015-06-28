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

public class PingModel implements PingModelInterface{
	
	String command;
	String tms; //tiempo de respuesta
	int ping, pingMedio;
	int suma;
	int frec = 1000;
	int env, rec, per; // estados
	boolean noRespuesta;
	boolean pingIncorrecto;
	Timer time;
	String ip = "www.google.com";
	ArrayList beatObservers = new ArrayList();
	ArrayList bpmObservers = new ArrayList();
	
	public PingModel(){
		
	}

//-------------------------------------------------------------------------------------------------------
//FUNCIONES QUE CORRESPONDEN AL PUNTO 2
//-------------------------------------------------------------------------------------------------------
	
	public void onCycle(){
			env = 0;				//Cada ciclo nuevo estos valores se reinicializan
			rec = 0;
			per = 0;
			suma = 0;
			env = 0;
			pingIncorrecto = false;
			noRespuesta = false;
			time = new Timer(frec,new ActionListener(){
				public void actionPerformed (ActionEvent evn){
					doCommand();
					time.setDelay(frec);
				}
			});
			if (frec != 0){
				time.setDelay(frec);
			}
			time.start();
	}
	
	public void offCycle(){
		time.stop();
	}
	
	public int getPing(){
		return ping;
	}
	
	
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
	
	public void setFrec(int frec){
		this.frec = frec;
		
		doCommand();
	}
	
	public int getFrec(){
		return frec;
	}
	

	
//-------------------------------------------------------------------------------------------------------
//FUNCIONES PARA LA NUEVA VISTA
//-------------------------------------------------------------------------------------------------------

	
	public void setURL(String ip){
		this.ip = ip;
		//this.frec = frec;
		//time.setDelay(frec);
		doCommand();
	}
	
	public String getURL(){
		return ip;
	}
	
	public int getPingMedio(){
		return pingMedio;
	}
	public int getEnviados(){
		return env;
	}
	
	public int getRecibidos(){
		return rec;
	}
	
	public int getPerdidos(){
		return per;
	}
	
	public String getPing2(){
		if (noRespuesta){
			return "Timed Out";
		}
		
		if (pingIncorrecto){
			return "IP incorrecta";
		}
		
		else
			return "Ping: " + tms;
	}
	
//-------------------------------------------------------------------------------------------------------
//FUNCIONES NECESARIAS PARA REALIZAR LOS COMANDOS DE PING DE WINDOWS
//-------------------------------------------------------------------------------------------------------

	public String getCommand(){
		
		command = "ping -n 1 " + ip;
		
		return command;
	}
	
	public void doCommand(){
		
		command = getCommand();
		 try
	        {
	            Runtime r = Runtime.getRuntime();
	            Process p = r.exec(command);
	            notifyBeatObservers();
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
	            notifyBPMObservers();
	            in.close();
	        } catch (IOException e) {
	            System.out.println(e);
	        }
	}
	
	public void parsing(String line){
		
		String comienzo1 = "Respuesta desde"; //espanol
		String beginning1 = "Reply from"; //ingles
		String comienzo2 = "Tiempo de respuesta agotado";
		String beginning2 = "Request timed out";
		String comienzo3 = "La solicitud";
		
		String[] buffer1;
		String[] buffer2;
		String[] buffer3;
		
		if (line.startsWith(comienzo1) || line.startsWith(beginning1)){
			buffer1 = line.split(":");
			buffer2 = buffer1[1].split("=");
			buffer3 = buffer2[2].split(" ");
			tms = buffer3[0];
			
			String num = tms.substring(0, tms.length() - 2);
			ping = Integer.parseInt(num);
			
			
			
			env++;
			rec++;
			pingMedio = promedio(tms);
		}
		
		if (line.startsWith(comienzo2) || line.startsWith(beginning2)){
			env++;
			per++;
			noRespuesta = true;
		}
		
		if(line.startsWith(comienzo3)){
			pingIncorrecto = true;
		}
	}
	
	public int promedio(String tMS){
		String[] num = tMS.split("m");
		int a =Integer.parseInt(num[0]);
		suma= suma+a;

		return suma/rec;
	}

	@Override
	public void removeObserver(BeatObserver o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeObserver(BPMObserver o) {
		// TODO Auto-generated method stub
		
	}

	
}