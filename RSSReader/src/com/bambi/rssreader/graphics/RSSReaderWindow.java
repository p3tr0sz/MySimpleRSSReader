package com.bambi.rssreader.graphics;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import com.bambi.rssreader.RSSMessage;
import com.bambi.rssreader.Reader;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;

public class RSSReaderWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtUrladdress;
	JTextArea txtPage;

	private DefaultCaret caret;

	private String url;

	private List<RSSMessage> msg = new ArrayList<RSSMessage>();

	private Reader reader;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RSSReaderWindow frame = new RSSReaderWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RSSReaderWindow() {

		createWindow();

	}

	public void createWindow() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		setResizable(false);
		setTitle("RSS Reader");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 100, 640, 50, 10 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblUrl = new JLabel("URL");
		GridBagConstraints gbc_lblUrl = new GridBagConstraints();
		gbc_lblUrl.anchor = GridBagConstraints.EAST;
		gbc_lblUrl.insets = new Insets(0, 0, 5, 5);
		gbc_lblUrl.gridx = 0;
		gbc_lblUrl.gridy = 0;
		contentPane.add(lblUrl, gbc_lblUrl);

		txtUrladdress = new JTextField();
		txtUrladdress.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					url = txtUrladdress.getText();
					
					try {
						reader = new Reader(url);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					msg = reader.getMessages();
					txtPage.append(msg + "\n");
				}
			}
		});
		GridBagConstraints gbc_txtUrladdress = new GridBagConstraints();
		gbc_txtUrladdress.insets = new Insets(0, 0, 5, 5);
		gbc_txtUrladdress.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUrladdress.gridx = 1;
		gbc_txtUrladdress.gridy = 0;
		contentPane.add(txtUrladdress, gbc_txtUrladdress);
		txtUrladdress.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				url = txtUrladdress.getText();

				try {
					reader = new Reader(url);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				msg = reader.getMessages();
				txtPage.append(msg + "\n");
			}
		});
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearch.gridx = 2;
		gbc_btnSearch.gridy = 0;
		contentPane.add(btnSearch, gbc_btnSearch);

		txtPage = new JTextArea();
		txtPage.setFont(new Font("Verdana", Font.PLAIN, 11));
		txtPage.setEditable(false);
		JScrollPane scroll = new JScrollPane(txtPage);
		caret = (DefaultCaret) txtPage.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		GridBagConstraints scrollConstrains = new GridBagConstraints();
		scrollConstrains.insets = new Insets(0, 0, 0, 5);
		scrollConstrains.fill = GridBagConstraints.BOTH;
		scrollConstrains.gridx = 0;
		scrollConstrains.gridy = 1;
		scrollConstrains.gridwidth = 4;
		scrollConstrains.gridheight = 2;
		contentPane.add(scroll, scrollConstrains);
	}
}
