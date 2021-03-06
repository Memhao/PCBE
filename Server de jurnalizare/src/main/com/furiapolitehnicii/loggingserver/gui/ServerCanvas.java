package com.furiapolitehnicii.loggingserver.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.furiapolitehnicii.constants.Constants;
import com.furiapolitehnicii.loggingserver.server.Configuration;
import com.furiapolitehnicii.loggingserver.server.Criteria;
import com.furiapolitehnicii.loggingserver.server.Server;

public class ServerCanvas extends JFrame {
	/**
	 * TODO check for clients duplicate etc TODO criteria for sort logs by
	 * Client name / by severity check box TODO block other fields till config
	 * is valid one
	 */
	private static final long serialVersionUID = 1L;
	private String serverName;
	private Server server;

	private Container cp;
	private JButton bStart, bExit, bLogin;
	private JPanel panelServer;
	private JPanel panelClient;
	private JPanel panelConfig;
	private JLabel lbConfig;
	private JTextField tfSourcePath, tfLogPath, tfConsole;
	// config
	private JTextField tfNoOfThreads, tfLogSize, tfNoOfRot;

	private JRadioButton rbClient, rbSeverity;

	public ServerCanvas(String serverName) {

		this.serverName = serverName;
		this.cp = getContentPane();
		this.tfConsole = new JTextField(5);

		cp.setLayout(new GridLayout(0, 1));

		setTitle("Server");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		initServerPanel();
		initClientPanel();
		initConfigPanel();
		cp.add(tfConsole);
		setPanels();

	}
	private void loadDefaultConfiguration() {
		rbClient.setSelected(true);
		rbSeverity.setSelected(false);
		tfLogPath.setText("src/out");
		tfLogSize.setText("1000");
		tfNoOfRot.setText("2");
		tfNoOfThreads.setText("10");
		tfSourcePath.setText("src/Logs");
	}

	private void initServerPanel() {
		panelServer = new JPanel();
		bStart = new JButton("Start");
		bStart.setBackground(Color.green);
		panelServer.add(bStart);
		bStart.addActionListener(new StartBehavior());

		bExit = new JButton("Exit");
		bExit.setBackground(Color.red);
		panelServer.add(bExit);
		bExit.addActionListener(new ExitBehavior());
	}
	private void initClientPanel() {
		panelClient = new JPanel();
		tfSourcePath = new JTextField(10);
		panelClient.add(new JLabel("Client path file:"));
		panelClient.add(tfSourcePath);
		bLogin = new JButton("Login");
		bLogin.addActionListener(new LoginBehavior());
		panelClient.add(bLogin);
	}
	private void initConfigPanel() {
		panelConfig = new JPanel();

		lbConfig = new JLabel("====Configuration====");
		rbClient = new JRadioButton("Client");
		rbSeverity = new JRadioButton("Severity");
		rbClient.setBackground(Color.white);
		rbSeverity.setBackground(Color.white);
		rbClient.setActionCommand("CLIENT_CMD");
		rbSeverity.setActionCommand("SEVERITY_CMD");
		ButtonGroup group = new ButtonGroup();
		group.add(rbClient);
		group.add(rbSeverity);
		Box box = Box.createVerticalBox();
		box.setVisible(true);
		box.add(lbConfig);
		box.add(new JLabel("Logging criteria"));
		Box radio = Box.createHorizontalBox();
		box.setVisible(true);
		radio.add(rbClient);
		radio.add(rbSeverity);
		box.add(radio);
		box.add(new JLabel("No of rotation"));

		box.add(tfNoOfRot = new JTextField());
		box.add(new JLabel("Log size"));
		box.add(tfLogSize = new JTextField());
		box.add(new JLabel("No of threads per client"));
		box.add(tfNoOfThreads = new JTextField());
		box.add(new JLabel("Insert Log Path"));
		box.add(tfLogPath = new JTextField());

		panelConfig.add(box);
		loadDefaultConfiguration();
	}
	private void setPanels() {
		Box box = Box.createVerticalBox();
		box.add(panelServer);
		box.add(panelClient);
		box.add(panelConfig);
		cp.add(box);
	}
	private Configuration getConfig() {
		int logSize = 1024;
		int noOfRotations = 3;
		int noOfLoggingThreads = 10;
		String logPath = "src/out";

		String logSize_str = tfLogSize.getText();
		boolean logSize_b = logSize_str.matches(Constants.FIVE_DIGIT_PATTERN);
		if (logSize_b)
			logSize = Integer.parseInt(logSize_str);
		else
			tfConsole.setText("Invalid log size default 1K considered"
					+ System.lineSeparator());

		String noOfRotations_str = tfNoOfRot.getText();
		boolean noOfRotations_b = noOfRotations_str
				.matches(Constants.THREE_DIGIT_PATTERN);
		if (noOfRotations_b)
			noOfRotations = Integer.parseInt(noOfRotations_str);
		else
			tfConsole.setText("Invalid no of rotations default 3 considered"
					+ System.lineSeparator());

		String noOfThreads_str = tfNoOfThreads.getText();
		boolean noOfThreads_b = noOfThreads_str
				.matches(Constants.THREE_DIGIT_PATTERN);
		if (noOfThreads_b)
			noOfLoggingThreads = Integer.parseInt(noOfThreads_str);
		else
			tfConsole.setText("Invalid no of threads default 10 considered"
					+ System.lineSeparator());

		String extractedlogPath = tfLogPath.getText();
		boolean tfLogPath_b = extractedlogPath.matches(Constants.PATH_PATERN);
		if (tfLogPath_b)
			logPath = extractedlogPath;
		else
			tfConsole.setText("Invalid path default src/out considered"
					+ System.lineSeparator());

		System.out.println(logPath);
		Criteria criteria;
		if (rbClient.isSelected()) {
			criteria = Criteria.CLIENT;
		} else
			criteria = Criteria.SEVERITY;

		return new Configuration(serverName, logSize, noOfRotations,
				noOfLoggingThreads, logPath, criteria);
	}
	/**
	 *
	 * @author xander
	 * @start server threads
	 */
	private class StartBehavior implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

			panelServer.setBackground(Color.CYAN);
			Configuration config = getConfig();
			server = new Server(serverName, config);
			server.startServer();
			panelServer.setBackground(Color.BLUE);
			panelServer.repaint();
		}

	}
	private class ExitBehavior implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			System.exit(0);

		}

	}
	private class LoginBehavior implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String inputPath = tfSourcePath.getText();
			if (inputPath.matches(Constants.PATH_PATERN))
				server.startClients(inputPath);
			else {
				server.startClients("src/Logs");
				tfConsole.setText("Default input considered for input path");
			}
			panelServer.setBackground(Color.GREEN);
			panelServer.repaint();
		}

	}
}
