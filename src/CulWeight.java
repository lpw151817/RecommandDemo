import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PrimitiveIterator.OfDouble;

import javax.rmi.CORBA.Util;
import javax.xml.transform.Templates;

public class CulWeight {

	public static void main(String[] args) {
		try {
			File f = new File("D:/weight.txt");
			if (f.exists())
				f.delete();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			FileOutputStream fo = new FileOutputStream(f);
			fo.write(("uid1\t" + "uid2\t" + "same movie count\t" + "uid1 moive count\t" + "uid2 moive count\t"
					+ "weight1\t" + "weight2" + "\r\n").getBytes());
			System.out.println("begin = " + df.format(new java.util.Date(System.currentTimeMillis())));
			DBUtils dbUtils = new DBUtils();
			for (int i = 1; i <= 943; i++) {
				for (int j = i + 1; j <= 943; j++) {
					String s = i + "\t" + j + "\t" + dbUtils.getWeight(i, j) + "\r\n";
					fo.write(s.getBytes());
				}
			}

			System.out.println("end = " + df.format(new java.util.Date(System.currentTimeMillis())));
			fo.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}