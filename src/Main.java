import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		System.out.println(System.currentTimeMillis());
		DBUtils dbUtils = new DBUtils();
		for (int i = 1; i <= 5; i++) {
			for (int j = i + 1; j <= 5; j++) {
				if (dbUtils.getSameMovieIds(i, j).size() == 0)
					System.out.println(i);
			}
		}

		System.out.println(System.currentTimeMillis());
	}

}
