import java.util.ArrayList;
import java.util.List;
import java.util.logging.LoggingMXBean;

public class Utils {

	/**
	 * ��⴫������������Ƿ�ȳ�
	 * 
	 * @param a
	 * @return
	 */
	private static boolean isSameLength(int[]... a) {
		int length = a[0].length;
		for (int i = 1; i < a.length; i++) {
			if (a[i].length != length)
				return false;
			else
				continue;
		}
		return true;
	}

	/**
	 * ���ҳ�����������ÿһ�е�ֵ����ȵ���
	 * 
	 * @param a
	 * @return ��ȵĻ����ֵ����������-1
	 */
	public static List<Integer> getSameColomn(int[]... a) {
		List<Integer> result = new ArrayList<>();
		if (isSameLength(a)) {
			for (int i = 0; i < a[0].length; i++) {
				int a0i = a[0][i];
				boolean isSame = true;
				for (int j = 1; j < a.length; j++) {
					if (a[j][i] == a0i)
						continue;
					else {
						isSame = false;
						break;
					}
				}
				if (isSame)
					result.add(a0i);
				else
					result.add(-1);
			}
		}
		return result;
	}

	/**
	 * �����������������ƶ� cos�Ƕ�
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static double culSimilarity(int[] a, int[] b) {
		if (a.length != b.length)
			return -1;
		else if (a.length == 1) {
			if (a[0] == b[0])
				return 1;
			else
				return 0;
		} else {
			return culVectorMul(a, b) / (culVectorLength(a) * culVectorLength(b));
		}
	}

	/**
	 * ���������ĳ˻�
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static long culVectorMul(int[] a, int[] b) {
		if (a.length != b.length)
			return -1;
		else {
			long result = 0;
			for (int i = 0; i < a.length; i++) {
				result += a[i] * b[i];
			}
			return result;
		}
	}

	/**
	 * ����������ģ
	 * 
	 * @param a
	 * @return
	 */
	public static double culVectorLength(int[] a) {
		long quadraticSum = 0;
		for (int i = 0; i < a.length; i++) {
			quadraticSum += a[i] * a[i];
		}
		return Math.sqrt(quadraticSum);
	}
}
