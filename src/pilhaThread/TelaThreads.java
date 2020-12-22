package pilhaThread;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaThreads extends JDialog {

	/* Painel de Componentes */
	private JPanel jPanel = new JPanel(new GridBagLayout());

	private JLabel lblNome = new JLabel("Nome");
	private JTextField txtNome = new JTextField();

	private JLabel lblEmail = new JLabel("E-mail");
	private JTextField txtEmail = new JTextField();

	private JButton botaoStart = new JButton("Gerar");
	private JButton botaoStop = new JButton("Stop");

	private ImplementacaoFilaThread fila = new ImplementacaoFilaThread();

	/*
	 * CONSTRUTOR => Ecxecuta o que estiver dentro no momento da abertura ou
	 * execução
	 */
	public TelaThreads() {
		setTitle("Tela com thread");
		setSize(new Dimension(240, 240));
		setLocationRelativeTo(null);
		setResizable(false);
		/* Primeira parte da Tela concluída */

		/* Gerenciador de posicionamento de componentes */
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.insets = new Insets(5, 10, 5, 5);
		gridBagConstraints.anchor = gridBagConstraints.WEST;

		lblNome.setPreferredSize(new Dimension(200, 25));
		jPanel.add(lblNome, gridBagConstraints);

		txtNome.setPreferredSize(new Dimension(200, 25));
		gridBagConstraints.gridy++;
		jPanel.add(txtNome, gridBagConstraints);

		lblEmail.setPreferredSize(new Dimension(200, 25));
		gridBagConstraints.gridy++;
		jPanel.add(lblEmail, gridBagConstraints);

		txtEmail.setPreferredSize(new Dimension(200, 25));
		gridBagConstraints.gridy++;
		jPanel.add(txtEmail, gridBagConstraints);

		// ------------------------------------------------------------
		gridBagConstraints.gridwidth = 1;

		botaoStart.setPreferredSize(new Dimension(92, 25));
		gridBagConstraints.gridy++;
		jPanel.add(botaoStart, gridBagConstraints);

		botaoStop.setPreferredSize(new Dimension(92, 25));
		gridBagConstraints.gridx++;
		jPanel.add(botaoStop, gridBagConstraints);

		botaoStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) { /* Executa o clique no botão */

				if (fila == null) {
					fila = new ImplementacaoFilaThread();
					fila.start();
				}

				for (int qde = 1; qde <= 100; qde++) { /* Simulando o envio em massa de e-mails */

					ObjetoFilaThread filaThread = new ObjetoFilaThread();
					filaThread.setNome(txtNome.getText());
					filaThread.setEmail(txtEmail.getText() + " - " + qde);

					fila.add(filaThread);
				}
			}
		});

		botaoStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				fila.stop();
				fila = null;
			}
		});

		add(jPanel, BorderLayout.WEST);

		fila.start();

		/* Sempre será o último comando */
		setVisible(true); /* Torna a tela visível para o usuário */
	}
}
