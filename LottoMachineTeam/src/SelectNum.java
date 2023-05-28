import java.util.Arrays;

public class SelectNum {
	private int[] selectNum = new int[6];
	private int isAuto;
	
	public SelectNum(int[] selectNum, int isAuto) {
		this.selectNum = selectNum;
		this.isAuto = isAuto;	
	}
	
	/**
	 * 선택한 로또 번호 호출
	 * 작성자 : 장명근 버젼 : 1.0.0
	 * @return 로또 번호 6개의 배열
	 */
	public int[] getSelectNum() {
		return selectNum;
	}
	
	/**
	 * 자동 모드 판별(0 : 자동, 1: 반자동, 2: 수동)
	 * 작성자 : 장명근 버젼 : 1.0.0
	 * @return 선택한 번호에 따른 모드
	 */
	public int getIsAuto() {
		return isAuto;
	}			
}