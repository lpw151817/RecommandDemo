package data100k;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class FileUtils {
	/**
	 * 写文件，并自动换行
	 *
	 * @param f
	 * @param s
	 *            需要写入的一行内容
	 */
	public static void writeFile(File f, String s) throws IOException {
		FileWriter filerWriter = new FileWriter(f, true);
		BufferedWriter bufWriter = new BufferedWriter(filerWriter);
		bufWriter.write(s);
		bufWriter.newLine();
		bufWriter.close();
		filerWriter.close();
	}

	/**
	 * 读文件
	 */
	public static String readFile(String path, String filename) {
		File f = new File(path, filename);
		if (!f.exists())
			return null;
		try {
			BufferedReader br;
			InputStream inputStream = new FileInputStream(f);
			br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			StringBuilder sb = new StringBuilder();
			String tmp;
			while ((tmp = br.readLine()) != null) {
				sb.append(tmp).append("\r\n");
			}
			br.close();
			inputStream.close();
			return sb.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
