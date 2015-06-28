package View;
    
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Beat.*;
import ControllerInterface.ControllerInterface;
import Ping.PingModelInterface;

public class PingView implements ActionListener,  BeatObserver, BPMObserver {
	PingModelInterface model;
	ControllerInterface controller;
    JFrame viewFrame;
    JPanel viewPanel;
	BeatBar beatBar;
	JLabel pingOutputLabel;
    JFrame controlFrame;
    JPanel controlPanel;
    JLabel urlLabel,ipURLOputLabel,pingSegOUPUT,ERPOUPUT,pingMedOUPUT;
    JTextField pingSegTextField, urlSegTextField;
    JButton setBPMButton;
    JButton increaseBPMButton;
    JButton decreaseBPMButton;
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem startMenuItem;
    JMenuItem stopMenuItem;

    public PingView(ControllerInterface controller, PingModelInterface model) {	
		this.controller = controller;
		this.model = model;
		model.registerObserver((BeatObserver)this);
		model.registerObserver((BPMObserver)this);
    }
    
    public void createView() {
		// Create all Swing components here
        viewPanel = new JPanel(new GridLayout(1, 2));
        viewFrame = new JFrame("View");
        viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewFrame.setSize(new Dimension(100, 80));
        pingOutputLabel = new JLabel("Offline", SwingConstants.CENTER);
        pingOutputLabel.setForeground(Color.WHITE);
        pingOutputLabel.setFont(new java.awt.Font("Tahoma", 0, 30)); 
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.BLACK);
        titlePanel.add(pingOutputLabel);
        
        beatBar = new BeatBar();
		beatBar.setValue(0);
		ipURLOputLabel = new JLabel("IP/URL: ", SwingConstants.CENTER);
        JPanel dataPanel = new JPanel(new GridLayout(4, 1));
        JPanel estadisticas = new JPanel(new GridLayout(2,5));
        
        dataPanel.add(ipURLOputLabel);
        dataPanel.add(titlePanel);
        dataPanel.add(beatBar);
        dataPanel.add(estadisticas);
        
        JLabel pingSegLabel = new JLabel("Ping por Segundo: ", SwingConstants.CENTER);
        pingSegOUPUT = new JLabel("",SwingConstants.CENTER);	
        JLabel ERPLabel = new JLabel("Env/Rec/Per:", SwingConstants.CENTER);
        ERPOUPUT = new JLabel("",SwingConstants.CENTER);	
        JLabel pingMedioLabel = new JLabel("Ping medio:", SwingConstants.CENTER);
        pingMedOUPUT = new JLabel("",SwingConstants.CENTER);	
        
        estadisticas.add(pingSegLabel);
        estadisticas.add(new JSeparator(SwingConstants.VERTICAL));
        estadisticas.add(ERPLabel);
        estadisticas.add(new JSeparator(SwingConstants.VERTICAL));
        estadisticas.add(pingMedioLabel);
       
        estadisticas.add(pingSegOUPUT);
        estadisticas.add(new JSeparator(SwingConstants.VERTICAL));
        estadisticas.add(ERPOUPUT);
        estadisticas.add(new JSeparator(SwingConstants.VERTICAL));
        estadisticas.add(pingMedOUPUT);
        
        viewPanel.add(dataPanel);
        viewFrame.getContentPane().add(viewPanel, BorderLayout.CENTER);
        viewFrame.pack();
        viewFrame.setVisible(true);
	}
  
  
    public void createControls() {
		// Create all Swing components here
        JFrame.setDefaultLookAndFeelDecorated(true);
        controlFrame = new JFrame("Control");
        controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controlFrame.setSize(new Dimension(100, 80));

        controlPanel = new JPanel(new GridLayout(1, 2));

        menuBar = new JMenuBar();
        menu = new JMenu("Ping Control");
        startMenuItem = new JMenuItem("Start");
        menu.add(startMenuItem);
        startMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                controller.start();
            }
        });
        stopMenuItem = new JMenuItem("Stop");
        menu.add(stopMenuItem); 
        stopMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                controller.stop();
            }
        });
        JMenuItem exit = new JMenuItem("Quit");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        menu.add(exit);
        menuBar.add(menu);
        controlFrame.setJMenuBar(menuBar);

        pingSegTextField = new JTextField(2);
        urlSegTextField = new JTextField(2);
        urlLabel = new JLabel("Enter URL/IP:", SwingConstants.RIGHT);
        JLabel pingsSeg = new JLabel("Enter Pings por Seg:",SwingConstants.RIGHT);
        setBPMButton = new JButton("Set");
        setBPMButton.setSize(new Dimension(10,40));
        increaseBPMButton = new JButton(">>");
        decreaseBPMButton = new JButton("<<");
        setBPMButton.addActionListener(this);
        increaseBPMButton.addActionListener(this);
        decreaseBPMButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

		buttonPanel.add(decreaseBPMButton);
		buttonPanel.add(increaseBPMButton);

        JPanel enterPanel = new JPanel(new GridLayout(2, 2));
        enterPanel.add(urlLabel);
        enterPanel.add(urlSegTextField);
        enterPanel.add(pingsSeg);
        enterPanel.add(pingSegTextField);
        
        
        
        JPanel insideControlPanel = new JPanel(new GridLayout(3, 1));
        insideControlPanel.add(enterPanel);
        insideControlPanel.add(setBPMButton);
        insideControlPanel.add(buttonPanel);
        controlPanel.add(insideControlPanel);
        
        urlLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        pingOutputLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        controlFrame.getRootPane().setDefaultButton(setBPMButton);
        controlFrame.getContentPane().add(controlPanel, BorderLayout.CENTER);

        controlFrame.pack();
        controlFrame.setVisible(true);
    }

	public void enableStopMenuItem() {
    	stopMenuItem.setEnabled(true);
	}

	public void disableStopMenuItem() {
    	stopMenuItem.setEnabled(false);
	}

	public void enableStartMenuItem() {
    	startMenuItem.setEnabled(true);
	}

	public void disableStartMenuItem() {
    	startMenuItem.setEnabled(false);
	}

    public void actionPerformed(ActionEvent event) {
		if (event.getSource() == setBPMButton) {
			int pingSeg = Integer.parseInt(pingSegTextField.getText());
			String URLip = urlSegTextField.getText();
        	controller.setBPM(pingSeg,URLip);
		} else if (event.getSource() == increaseBPMButton) {
			controller.increaseBPM();
		} else if (event.getSource() == decreaseBPMButton) {
			controller.decreaseBPM();
		}
    }

	

	public void updateBPM() {
		if (model != null) {
			String ping = model.getPing2();
			if (ping == null) {
				if (pingOutputLabel != null) {
        			pingOutputLabel.setText("Offline");
				}
			} else {
				if (pingOutputLabel != null) {
        			pingOutputLabel.setText(model.getPing2());
				}
			}
			ipURLOputLabel.setText("IP/URL: " + model.getURL());
			pingSegOUPUT.setText(""+(model.getFrec()/1000));
			ERPOUPUT.setText(model.getEnviados() +"/"+ model.getRecibidos() 
					+"/"+ model.getPerdidos());
			pingMedOUPUT.setText(model.getPingMedio()+"ms");
		}
	}
  
	public void updateBeat() {
		if (beatBar != null) {
			 beatBar.setValue(100);
		}
	}
	
}
