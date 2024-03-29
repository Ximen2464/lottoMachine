package utility;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class FontData {
	public Font nanumFont25() {
		try {
            // 폰트 파일 로드
            InputStream is = getClass().getClassLoader().getResourceAsStream("NanumSquareNeo-dEb.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);

            // 폰트 크기 및 스타일 설정
            Font customFont = font.deriveFont(Font.BOLD, 25);
            return customFont;
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            // 폰트 로드에 실패하면 기본 폰트를 반환하거나 예외를 처리할 수 있습니다.
            // 여기서는 null을 반환합니다.
            return null;
        }
	}
	public Font nanumFont18() {
		try {
			// 폰트 파일 로드
			InputStream is = getClass().getClassLoader().getResourceAsStream("NanumSquareNeo-dEb.ttf");
			Font font = Font.createFont(Font.TRUETYPE_FONT, is);
			
			// 폰트 크기 및 스타일 설정
			Font customFont = font.deriveFont(Font.BOLD, 18);
			return customFont;
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
			// 폰트 로드에 실패하면 기본 폰트를 반환하거나 예외를 처리할 수 있습니다.
			// 여기서는 null을 반환합니다.
			return null;
		}
		
	}
	
	public Font nanumFont38() {
		try {
			// 폰트 파일 로드
			InputStream is = getClass().getClassLoader().getResourceAsStream("NanumSquareNeo-dEb.ttf");
			Font font = Font.createFont(Font.TRUETYPE_FONT, is);
			
			// 폰트 크기 및 스타일 설정
			Font customFont = font.deriveFont(Font.BOLD, 38);
			return customFont;
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
			// 폰트 로드에 실패하면 기본 폰트를 반환하거나 예외를 처리할 수 있습니다.
			// 여기서는 null을 반환합니다.
			return null;
		}
		
	}
	
}
