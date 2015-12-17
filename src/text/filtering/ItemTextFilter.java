package text.filtering;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class ItemTextFilter {

	public static final String FILE_PATH_INPUT = "D:/u.item";
	public static final String FILE_PATH_OUTPUT = "D:/u.item.txt";

	public static void main(String[] args) {
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(FILE_PATH_INPUT)), "UTF-8"));
			bw = new BufferedWriter(new FileWriter(new File(FILE_PATH_OUTPUT)));
			String tmp_r;
			StringBuilder tmp_w = new StringBuilder();
			while ((tmp_r = br.readLine()) != null) {
				// Çå¿Õ
				tmp_w.delete(0, tmp_w.length());
				String[] ss = tmp_r.split("\\|");
				appendSplit(tmp_w.append(ss[0]));
				for (int i = 0; i < ss.length; i++) {
					if (i > 4) {
						if (ss[i].equals("1")) {
							switch (i) {
							case 5:
								appendSplit(tmp_w.append("unknown"));
								break;
							case 6:
								appendSplit(tmp_w.append("Action"));
								break;
							case 7:
								appendSplit(tmp_w.append("Adventure"));
								break;
							case 8:
								appendSplit(tmp_w.append("Animation"));
								break;
							case 9:
								appendSplit(tmp_w.append("Children's"));
								break;
							case 10:
								appendSplit(tmp_w.append("Comedy"));
								break;
							case 11:
								appendSplit(tmp_w.append("Crime"));
								break;
							case 12:
								appendSplit(tmp_w.append("Documentary"));
								break;
							case 13:
								appendSplit(tmp_w.append("Drama"));
								break;
							case 14:
								appendSplit(tmp_w.append("Fantasy"));
								break;
							case 15:
								appendSplit(tmp_w.append("Film_Noir"));
								break;
							case 16:
								appendSplit(tmp_w.append("Horror"));
								break;
							case 17:
								appendSplit(tmp_w.append("Musical"));
								break;
							case 18:
								appendSplit(tmp_w.append("Mystery"));
								break;
							case 19:
								appendSplit(tmp_w.append("Romance"));
								break;
							case 20:
								appendSplit(tmp_w.append("Sci-Fi"));
								break;
							case 21:
								appendSplit(tmp_w.append("Thriller"));
								break;
							case 22:
								appendSplit(tmp_w.append("War"));
								break;
							case 23:
								appendSplit(tmp_w.append("Western"));
								break;
							default:
								appendSplit(tmp_w.append("XXXXXXX"));
								break;
							}
						}
					}
				}
				bw.write(tmp_w.toString());
				bw.newLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void appendSplit(StringBuilder sb) {
		sb.append("\t");
	}
}
