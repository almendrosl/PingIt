package Ping;

import java.io.*;
import java.util.*;

public class PingModel {
	
	String command;
	String tms; //tiempo de respuesta
	int pingMedio;
	int suma;
	//int frec;
	int env, rec, per; // estados
	boolean noRespuesta = true;
	boolean pingIncorrecto = true;
	
	public PingModel(){
		
	}
	
	void Start(String ip, int frec){
		
		
	}
	String getCommand(String ip){
		
		command = "ping -n 1 " + ip;
		
		return command;
	}
	
	void doCommand(String ip){
		
		command = getCommand(ip);
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