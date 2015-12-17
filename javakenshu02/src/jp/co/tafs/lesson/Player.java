package jp.co.tafs.lesson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Player {
	public static void main(String[] args) {

		String doing = ""; // ユーザのアクション
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean finished = false;

		// ゲーム機のインスタンス生成
		PlayStation4 hardware = new PlayStation4();
		hardware.getInfo();

		while (finished == false) {
			System.out
					.println("何をしますか？半角数字で入力してください。"
							+
							"\n(1:電源オン, 2:電源オフ, 3:ソフトの挿入, 4:ソフト一覧の取得, 5:ソフトの削除, \n6:アクティブなゲーム名の取得, 7:アクティブなゲームを設定, 8:ゲーム開始)");
			doing = inputRead(br);
			try {
				switch (doing) {
				case "1": // 電源ON
					hardware.powerOn();
					break;
				case "2": // 電源OFF
					hardware.powerOff();
					break;
				case "3": // ソフト挿入
					hardware.insertSoftware();
					break;
				case "4": // ソフト一覧の取得
					hardware.getSoftwareList();
					break;
				case "5": // 最も古いソフトの削除
					hardware.ejectSoftware();
					break;
				case "6": // アクティブなゲーム名の取得
					hardware.getActive();
					break;
				case "7": // アクティブにするゲームを設定
					System.out.println("アクティブにするソフトの番号を入力して下さい。\n");
					hardware.getSoftwareList();
					hardware.setActive();
					break;
				case "8": // ゲーム開始
					hardware.playGame();
					break;
				default:
					System.out.println("1～8の半角数字で入力してください。");
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	static String inputRead(BufferedReader br) {
		String str = "";
		try {
			str = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
}
