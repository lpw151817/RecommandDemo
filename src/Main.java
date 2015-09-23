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

public class Main {

	public static void main(String[] args) {
		// int a[] = { 1, 2, 3 };
		// int b[] = { 1, 2, 3 };
		//
		// List<Integer> result = Utils.getSameColomn(a, b);
		// for (Integer integer : result) {
		// System.out.println(integer);
		// }
		try {
			File f = new File("D:/similarity.txt");
			if (f.exists())
				f.delete();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			FileOutputStream fo = new FileOutputStream(f);
			fo.write(("begin = " + df.format(new java.util.Date(System.currentTimeMillis()))
					+ "\r\n").getBytes());
			System.out.println(
					"begin = " + df.format(new java.util.Date(System.currentTimeMillis())));
			DBUtils dbUtils = new DBUtils();
			for (int i = 7; i <= 943; i++) {
				for (int j = i + 1; j <= 943; j++) {
					String s = i + "\t" + j + "\t" + dbUtils.getUBCFSimilarity(i, j) + "\r\n";
					fo.write(s.getBytes());
				}
			}

			System.out
					.println("end = " + df.format(new java.util.Date(System.currentTimeMillis())));
			fo.write(("begin = " + df.format(new java.util.Date(System.currentTimeMillis()))
					+ "\r\n").getBytes());
			fo.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
