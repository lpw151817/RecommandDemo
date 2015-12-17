package data100k;
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

public class CulRate {

	public static void main(String[] args) {
		try {
			File f = new File("D:/rate.txt");
			if (f.exists())
				f.delete();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			FileOutputStream fo = new FileOutputStream(f);
			fo.write(("uid\t" + "mid\t" + "rate\r\n").getBytes());
			System.out.println("begin = " + df.format(new java.util.Date(System.currentTimeMillis())));
			DBUtils dbUtils = new DBUtils();
			for (int i = 5; i <= 943; i++) {
				for (int j = 1; j <= 1682; j++) {
					String s = i + "\t" + j + "\t" + dbUtils.getRate(i, j) + "\r\n";
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
