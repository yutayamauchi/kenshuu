package jp.co.tafs.lesson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PlayStation4 extends Hardware {
	private ArrayList<String> soft_list = new ArrayList<String>();
	private int active_num = -1; // アクティブなソフトの切り替え用インデックス
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	PlayStation4() {
		this.hard_name = "PrayStation4";
		this.hard_maker = "SONY";
		this.connectivity = true;
		this.power = false;
		this.price = 34980;
		this.release_date = "2014/02/22";
	}

	@Override
	void playGame() {
		if ((getPower() == false) || (soft_list.size() == 0)) {
			new Exception("エラー！\n電源がオフになっているか、ソフトがインストールされていません。\n");
		} else {
			if (active_num == -1) {
				System.out.println("ソフトをアクティブにしてください。");
			} else if (soft_list.size() < active_num) {
				System.out.println("選択された番号のソフトは存在しません。");
			} else {
				System.out.println(soft_list.get(active_num - 1) + " is Playing!!");
			}
		}
	}

	// ソフトのインストール
	@Override
	void insertSoftware() throws Exception {
		if (getPower() == false) {
			throw new Exception("電源が入っていません！\n");
		} else {
			System.out.println("ソフトを挿入してください。");
			soft_list.add(br.readLine());
			System.out.println(soft_list.get(soft_list.size() - 1) + "を挿入しました。\n");
		}
	}

	// 最も古いソフトを削除(リストの先頭)
	@Override
	void ejectSoftware() throws Exception {
		if (getPower() == false) {
			throw new Exception("電源が入っていません！\n");
		}
		if (soft_list.size() > 0) {
			System.out.print("削除するソフトを選択しますか？(y/n):");
			String select = br.readLine();
			if (select.equals("y") || select.equals("Y")) {
				System.out.println("ソフト番号を半角数字で入力してください。");
				int num = Integer.valueOf(br.readLine());
				if (Character.isDigit(num)) {
					if ((soft_list.size() < num) || (0 > num)) { //要素外へのアクセス
						System.out.println("入力された番号のソフトは存在しません。");
					} else {
						soft_list.remove(num - 1);
						System.out.println("選択された番号のソフトは正常に削除されました。");
					}
				} else {
					System.out.println("ゲーム番号を半角数字で入力して下さい。");
				}
			} else {
				System.out.println(soft_list.get(0) + "を削除しました。\n");
				soft_list.remove(0);
			}
		} else {
			System.out.println("ソフトは挿入されていません。\n");
		}

	}

	public void setActive() {
		try {
			active_num = Integer.valueOf(br.readLine());
		} catch (Exception e) {
			System.out.println("半角数字で入力してください。");
		}
		if ((active_num >= 0) && (active_num <= soft_list.size())) {
			System.out.println(soft_list.get(active_num - 1) + "がアクティブになりました。");
		} else {
			System.out.println("アクティブなゲームソフトがありません。\n");
		}
	}

	/*
	 * 以下、状態検証用メソッド群
	 */
	// 電源の状態取得
	private boolean getPower() {
		return power;
	}

	// インストール済みソフトのリストの取得
	public void getSoftwareList() {
		int i = 1;
		if (soft_list.size() == 0) {
			System.out.println("ゲームソフトがインストールされていません！");
		} else {
			for (String software : soft_list) {
				System.out.println(i + " : " + software);
				i++;
			}
		}
	}

	// アクティブなゲームソフト名の取得
	public void getActive() {
		if (soft_list.size() == 0) {
			System.out.println("インストール済みのソフトは存在しません。");
		} else {
			if (active_num != -1) {
				System.out.println("アクティブなソフトは " + soft_list.get(active_num - 1) + " です。\n");
			} else {
				System.out.println("アクティブなゲームソフトがありません。\n");
			}
		}
	}
}
