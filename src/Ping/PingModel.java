package Ping;

import java.io.*;
import java.util.*;

public class PingModel {
	
	String command;
	
	public PingModel(){
		
	}
	
	String getCommand(String ip){
		
		command = "ping " + ip;
		
		return command;
	}
	
	void doCommand(String ip){
		
		command = getCommand(ip);
		
	}
	

}