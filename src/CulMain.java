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

public class CulMain {

	public static void main(String[] args) {
		try {
			File f = new File("D:/IBCF.txt");
			if (f.exists())
				f.delete();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			FileOutputStream fo = new FileOutputStream(f);
			fo.write(("mid1\t" + "mid2\t" + "same user count\t" + "mid1 user count\t" + "mid2 user count\t"
					+ "of1(二次)\t" + "of2(一次)\t" + "of1的平方\t" + "欧式距离\t" + "of1*sim\t" + "of2*sim\t" + "of1的平方*sim\t"
					+ "uid1 rate\t\t" + "无of预测值\tof1预测值\tof2预测值	of1平方预测值" + "\r\n").getBytes());
			System.out.println("begin = " + df.format(new java.util.Date(System.currentTimeMillis())));
			DBUtils dbUtils = new DBUtils();
			for (int i = 55; i <= 1682; i++) {
				for (int j = 1; j <= 272; j++) {
					String s = i + "\t" + j + "\t" + dbUtils.getIBCFWeight(i, j) + "\t"
							+ dbUtils.getIBCFSimilarity(i, j) + "\r\n";
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
