package jp.co.tafs.lesson;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Hardware {

	protected String hard_name; // ハードウェア名
	protected String hard_maker; // 発売メーカー
	private String software_name;
	protected boolean connectivity; // ネット接続機能の有無
	protected boolean power; // 電源
	private boolean detect_soft; // ソフトを検出済みかどうか(ソフトが入っているか)
	protected String release_date;
	protected int price;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	void playGame() {
		if ((getPower() == false) || (detect_soft == false)) {
			new Exception("エラー！\n電源がオフになっているか、ソフトが挿入されていません。\n");
		} else {
			System.out.println("now playing in " + this.software_name + "!!");
		}
	}

	void powerOn() {
		if (getPower() == false) {
			power = true;
			System.out.println("電源をオンにしました。\n");
		} else {
			System.out.println("電源はすでにオンになっています。\n");
		}
	}

	void powerOff() {
		if (getPower() == true) {
			power = false;
			System.out.println("電源をオフにしました。\n");
		} else {
			System.out.println("電源はすでにオフになっています。\n");
		}
	}

	void insertSoftware() throws Exception {
		if (getPower() == false) {
			throw new Exception("電源が入っていません！\n");
		} else {
			if (detect_soft == false) {
				detect_soft = true;
				System.out.println("ソフトを挿入してください。");
				software_name = br.readLine();
				System.out.println(software_name + "を挿入しました。\n");
			} else {
				System.out.println(this.software_name + "が挿入されています。\n");
			}
		}
	}

	void ejectSoftware() throws Exception {
		if (getPower() == false) {
			throw new Exception("電源が入っていません！\n");
		} else {
			if (detect_soft == true) {
				detect_soft = false;
				System.out.println(software_name + "を取り出しました。\n");
				software_name = "";
			} else {
				System.out.println("ソフトは挿入されていません。\n");
			}
		}
	}

	/*
	 * 以下、状態検証用メソッド群
	 * ・電源
	 * ・ソフトの有無(【有】の場合はソフト名)
	 */
	private boolean getPower() {
		return power;
	}

	String getSoftwareName() {
		try {
			if (power == false) {
				new Exception("電源が入っていません！\n");
			} else {
				if (detect_soft == false)
					detect_soft = true;
			}
		} catch (Exception e) {
			System.out.println("電源が入っていません！\n");
		}
		if (software_name.equals("")) {
			return null;
		} else {
			return software_name;
		}
	}

	public void getInfo() {
		System.out.println("*****ハードウェア情報*****");
		System.out.println("ハードウェア名：" + this.hard_name);
		System.out.println("製造元：" + this.hard_maker);
		if (this.connectivity == true) {
			System.out.println("ネット接続機能：有り");
		} else {
			System.out.println("ネット接続機能：無し");
		}
		System.out.println("発売日：" + this.release_date);
		System.out.println("販売価格：" + this.price);
		System.out.println("*****ハードウェア情報*****");
	}
}
