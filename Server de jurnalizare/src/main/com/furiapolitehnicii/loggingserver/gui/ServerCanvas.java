package com.furiapolitehnicii.loggingserver.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

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
	private JTextField tfPath;
	// config
	private JTextField tfNoOfThreads, tfLogSize, tfNoOfRot, tfLogPath;

	private JRadioButton rbClient, rbSeverity;

	public ServerCanvas(String serverName) {

		this.serverName = serverName;
		this.cp = getContentPane();
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
		setPanels();

	}
	private void loadDefaultConfiguration() {
		rbClient.setSelected(true);
		rbSeverity.setSelected(false);
		tfLogPath.setText("src/out");
		tfLogSize.setText("1000");
		tfNoOfRot.setText("2");
		tfNoOfThreads.setText("10");
		tfPath.setText("src/Logs");
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
		tfPath = new JTextField(10);
		panelClient.add(new JLabel("Client path file:"));
		panelClient.add(tfPath);
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
		rbClient.addActionListener(new MutexListener());
		rbSeverity.addActionListener(new MutexListener());

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
		box.add(tfNoOfRot = new JTextField(5));
		box.add(new JLabel("Log size"));
		box.add(tfLogSize = new JTextField(5));
		box.add(new JLabel("No of threads per client"));
		box.add(tfNoOfThreads = new JTextField(5));
		box.add(new JLabel("Insert Log Path"));
		box.add(tfLogPath = new JTextField(10));
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
			int logSize = Integer.parseInt(tfLogSize.getText());
			int noOfRotations = Integer.parseInt(tfNoOfRot.getText());
			int noOfLoggingThreads = Integer.parseInt(tfNoOfThreads.getText());
			String logPath = tfLogPath.getText();
			System.out.println(logPath);
			Criteria criteria;
			if (rbClient.isSelected()) {
				criteria = Criteria.CLIENT;
			} else
				criteria = Criteria.SEVERITY;

			Configuration config = new Configuration(serverName, logSize,
					noOfRotations, noOfLoggingThreads, logPath, criteria);
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
			String inputPath = tfPath.getText();
			server.startClients(inputPath);
			panelServer.setBackground(Color.GREEN);
			panelServer.repaint();
		}

	}
	class MutexListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			if (event.getActionCommand() == "CLIENT_CMD")
				rbSeverity.setSelected(false);
			else if (event.getActionCommand() == "SEVERITY_CMD")
				rbClient.setSelected(false);

		}

	}
}
