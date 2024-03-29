package Pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;

import database.WinningNumData;
import database.WinningPrice;
import utility.FontData;
import utility.IconData;
import utility.Utility;

public class MainPage extends JFrame {

	public static final WinningNumData WINNING_NUM_DATA = new WinningNumData();
	
	private IconData iconData = new IconData();
	private Utility utility = new Utility();
	private JButton buyButton; 
	private JButton myNumButton;
	private JButton makeLotteryButton;
	private JButton nextTurnButton;
	private static Integer currentRound = 1;
	private JLabel[] lastWinningNums;
	private JLabel lastBonusNum;
	private JLayeredPane layeredPane;
	private JLabel mainBackGround;
	private JLabel lastTurnLabel;
	private JLabel curruntTurnLabel;
	private String lastTurnString;
	private String curruntTurnString;
	private FontData fontData = new FontData();
	public static int eggCount = 0; 
	private JButton eggBtn;
	private WinningPrice winningPrice = new WinningPrice();
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setResizable(false);
		
		lastTurnString =  WINNING_NUM_DATA.getLastTurn()+"회 당첨 결과";
		curruntTurnString = currentRound+"회";
		mainBackGround = new JLabel(iconData.mainIcon());
		lastWinningNums = new JLabel[6];
		lastBonusNum = new JLabel();
		lastTurnLabel = new JLabel("이전회차 당첨결과가 없습니다.");
		curruntTurnLabel = new JLabel(curruntTurnString);
		
		Font customFont = fontData.nanumFont25();
		Color customColor = Color.WHITE;
		lastTurnLabel.setFont(customFont);
		curruntTurnLabel.setFont(customFont);
		
		lastTurnLabel.setForeground(customColor);
		curruntTurnLabel.setForeground(customColor);
		
		lastTurnLabel.setHorizontalAlignment(SwingConstants.CENTER);
		curruntTurnLabel.setHorizontalAlignment(SwingConstants.LEFT);
		

		btnBounds();
		btnUnVisuable();

		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(
				new Dimension(iconData.mainIcon().getIconWidth(), iconData.mainIcon().getIconHeight()));

		// 레이블 및 버튼 위치 설정
		mainBackGround.setBounds(0, 0, iconData.mainIcon().getIconWidth(), iconData.mainIcon().getIconHeight());
		for (int i = 0; i < lastWinningNums.length; i++) {
			lastWinningNums[i] = new JLabel();
			lastWinningNums[i].setBounds(33 + i * 50, 199, 40, 40);
		}
		lastBonusNum.setBounds(357, 199, 40, 40);
		lastTurnLabel.setBounds(15, 130, 400, 40);
		curruntTurnLabel.setBounds(52, 411, 72, 31);
		
		// 레이블 및 버튼을 JLayeredPane에 추가
		addLayeredPan();
	
		
		// JLayeredPane을 프레임의 contentPane에 추가
		setContentPane(layeredPane);
	

		buyBtn();
		myNumBtn();
		makeLotteryBtn();
		nextTurnBtn();
		eggBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				eggCount++;
				System.out.println(eggCount);
			}
		});
		System.out.println(WINNING_NUM_DATA);
		System.out.println(WINNING_NUM_DATA.getLastTurn());
		System.out.println(WINNING_NUM_DATA.getLastWinningNum());
		
		layeredPane.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton()== MouseEvent.BUTTON3) {
					eggCount++;
					System.out.println(eggCount);
					
				}
			}
			
			
		});
		
		pack();
		System.out.println(winningPrice.totalPrice());
		System.out.println(winningPrice.firstWinningPrice());

	}
	private void addLayeredPan() {
		layeredPane.add(mainBackGround, new Integer(1)); // 레이블은 뒤쪽 레이어에 추가
		layeredPane.add(buyButton, new Integer(2)); // 버튼은 앞쪽 레이어에 추가
		layeredPane.add(myNumButton, new Integer(2));
		layeredPane.add(makeLotteryButton, new Integer(2));
		layeredPane.add(nextTurnButton, new Integer(2));
		layeredPane.add(lastBonusNum, new Integer(2));
		layeredPane.add(lastTurnLabel, new Integer(2));
		layeredPane.add(curruntTurnLabel, new Integer(2));
		layeredPane.add(eggBtn, new Integer(2));
		for (int i = 0; i < lastWinningNums.length; i++) {
			layeredPane.add(lastWinningNums[i], new Integer(2));
		}
		
	}

	
	private void buyBtn() {
		buyButton.addActionListener(e -> {
			if (currentRound == WINNING_NUM_DATA.getLastTurn()) {
				
//				buyErrorBtn();
//				layeredPane.add(buyErrorButton, new Integer(2));
//				buyErrorButton = new JButton(iconData.buyErrorIcon());
//				buyErrorButton.setBounds(76, 610, 280, 81);
//				utility.setButtonProperties(buyErrorButton);
				
				new BuyErrorPage().setVisible(true);
			    System.out.println("바이에러 페이로 넘어가기");
			} else {
				new BuyPage().setVisible(true); // pass this frame to the next one				
			}
		});
		
		
	}
	private void myNumBtn() {

		myNumButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(currentRound != WINNING_NUM_DATA.getLastTurn()) {
					MyNumCheckPage myNumCheckPage = new MyNumCheckPage();
					myNumCheckPage.setVisible(true);
					System.out.println("나의번호로 넘어가기");
				} else {
					new WinningNumPage().setVisible(true);
					System.out.println("위닝넘으로 넘어가기");
				}
				
			}
		});
		
	}
	private void makeLotteryBtn() {
		makeLotteryButton.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if (currentRound > WINNING_NUM_DATA.getLastTurn()) {
		            UnderLotteryPage underLotteryPage = new UnderLotteryPage();
		            underLotteryPage.addWindowListener(new WindowAdapter() {
		                @Override
		                public void windowClosed(WindowEvent e) {
		                    if (WINNING_NUM_DATA.getLastWinningNum() != null) {
		                        showWinningNum();
		                    }
		                    System.out.println("UnderLotteryPage 창이 닫힘");
		                   
		                   // buyButton.setEnabled(false);
		                    buyButton.setIcon(iconData.buyErrorIcon());
		                    makeLotteryButton.setIcon(iconData.winnerCheckIcon());
		                    
		                }
		            });
		            underLotteryPage.setVisible(true);
		        	
		        }
		        	
		        	WinningNumPage winningNumPage = new WinningNumPage();
		        	winningNumPage.setVisible(true);
				
		        
		        nextBtnActivate();
		    }
		});
	}
	private void nextTurnBtn() {

		nextTurnButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (WINNING_NUM_DATA.getLastTurn()!= currentRound) {
					System.out.println("추첨 진행 해라");
				} else {
					currentRound++;
					curruntTurnString = currentRound+"회";
					curruntTurnLabel.setText(curruntTurnString);
					BuyPage.PAYMENT_NUM_DATA.clearData();
					BuyPage.SELECT_NUM_DATA.clearList();
					System.out.println(currentRound);
					
				}
				buyButton.setIcon(iconData.buyIcon());
				makeLotteryButton.setIcon(iconData.makeLotteryIcon());
				nextBtnActivate();
			}
			
		});
	}
	private void showWinningNum() {
		System.out.println("진입");
		Collection<Integer> set = WINNING_NUM_DATA.getLastWinningNum().getWinningNum();
		List<Integer> sortedList = new ArrayList<>(set);
		Collections.sort(sortedList);
		System.out.println(sortedList);
		
		for (int i = 0; i < sortedList.size(); i++) {
		    int element = sortedList.get(i);
		    // 원하는 작업 수행
		    lastWinningNums[i].setIcon(iconData.LCIcons()[element]);
		    
		}
		lastBonusNum.setIcon(iconData.LCIcons()[WINNING_NUM_DATA.getLastWinningNum().getBonusNum()]);
		lastTurnString =  WINNING_NUM_DATA.getLastTurn()+"회 당첨 결과";
		lastTurnLabel.setText(lastTurnString);
		
	}

	/**
	 * 버튼 안보이게 만듬
	 */
	private void btnUnVisuable() {

		utility.setButtonProperties(buyButton);
		utility.setButtonProperties(myNumButton);
		utility.setButtonProperties(makeLotteryButton);
		utility.setButtonProperties(nextTurnButton);
		utility.setButtonProperties(eggBtn);
	}

	/**
	 * 버튼 크기 및 위치 조정
	 */
	private void btnBounds() {
		buyButton = new JButton(iconData.buyIcon());
		buyButton.setBounds(76, 610, 280, 81); // 위치와 크기 설정
		myNumButton = new JButton(iconData.myNumIcon());
		myNumButton.setBounds(30, 780, 111, 36);
		makeLotteryButton = new JButton(iconData.makeLotteryIcon());
		makeLotteryButton.setBounds(160, 780, 111, 36);
		nextTurnButton = new JButton(iconData.nextGrayTurnIcon());
		nextTurnButton.setBounds(290, 780, 111, 36);
		eggBtn = new JButton();
		eggBtn.setBounds(30, 30, 100, 40);
	}
	private void nextBtnActivate() {
		if (WINNING_NUM_DATA.getLastTurn()!= currentRound) {
			System.out.println("추첨 해야함 ");
			eggCount = 0;
			nextTurnButton.setIcon(iconData.nextGrayTurnIcon());
		} else {
			System.out.println("추첨 안해도 됨");
			nextTurnButton.setIcon(iconData.nextTurnIcon());
			eggCount = 0;
		}
	}
}
