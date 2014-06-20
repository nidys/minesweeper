package client.views;

import java.awt.GridLayout;
import java.util.ArrayList;

import common.model.DiscoveredField;
import client.controllers.MyBombFielsBtnController;
import client.views.component.GameBoardPanel;

@SuppressWarnings("serial")
public class PerksGamePanel extends GamePanelBase {
	private GridLayout layout;
	ArrayList<GameBoardPanel> gameBoardPanels = new ArrayList<GameBoardPanel>();

	public PerksGamePanel() {
		super();

		initUi();
	}

	private void initUi() {
		setBounds(0, 0, 450, 301);
		layout = new GridLayout(0, 1);
		setLayout(layout);
	}

	public void addNewPlayerGameBoardPanel(GameBoardPanel gameBoardPanel) {
		add(gameBoardPanel);
		gameBoardPanels.add(gameBoardPanel);

	}

	public void addBombFieldBtnListener(MyBombFielsBtnController listener) {
		gameBoardPanels.get(gameBoardPanels.size() - 1)
				.addBombFieldBtnListener(listener);
	}

	public void gameBoardPanels() {
		gameBoardPanels.get(0).resetFields();

	}
	@Override
	public void setField(DiscoveredField field) {
		gameBoardPanels.get(0).setField(field);

	}
	
	@Override
	public void setFieldFlagged(int position) {
		gameBoardPanels.get(0).setFieldFlagged(position);

	}

	@Override
	public void setProgress(String opponentName, int progressValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resetFields() {
		gameBoardPanels.get(0).resetFields();
		
	}

	@Override
	public void addOpponent(String opponentName) {
		// TODO Auto-generated method stub
		
	}

}
