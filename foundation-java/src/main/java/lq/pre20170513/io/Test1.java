package lq.pre20170513.io;

import java.io.*;

/**
 * 编写一个程序，将 1.txt 文件中的单词与 2.txt 文件中的单词交替合并到 3.txt
 * 文件中， 1.txt 文件中的单词用回车符分隔， 2.txt 文件中用回车或空格进行分隔。 
 *
 */
public class Test1 {
	public static void main(String[] args) {
		String[] s1 = getWords("1.txt", new char[]{'\n'});
		String[] s2 = getWords("2.txt", new char[]{'\n',' '}); 
		write(s1, s2, "3.txt");
		
	}
	
	private static void write(String[] s1, String[] s2, String fileName){
		try {
			int len1 = s1.length;
			int len2 = s2.length;
			File f = new File(fileName);
			FileWriter fw = new FileWriter(f);
			if(len1 <= len2){
				for(int i = 0; i < len1; i++){
					fw.write(s1[i] + '\n');
					fw.write(s2[i] + '\n');
				}
				for(int i = len1; i < len2; i++){
					fw.write(s2[i] + '\n');
				}
			}else{
				for(int i = 0; i < len2; i++){
					fw.write(s1[i] + '\n');
					fw.write(s2[i] + '\n');
				}
				for(int i = len2; i < len1; i++){
					fw.write(s1[i] + '\n');
				}
			}
			fw.close();
		} catch (IOException e) { 
			e.printStackTrace();
		} 
	}
	
	// 通过分隔符得到字符串数组
	private static String[] getWords(String fileName, char[] seperators){
		String[] result = null;
		try {
			File f = new File(fileName);
			FileReader fr = new FileReader(f);
			char[] buf = new char[(int)f.length()];
			int len = fr.read(buf);
			String str = new String(buf, 0, len); //文件的内容
			String regex = null;  //匹配格式
			//下面拼接字符串前面加了空串，是因为char不能强转为String
			if(seperators.length > 1){
				regex = ""+seperators[0]+"|"+seperators[1];
			}else{
				regex = ""+seperators[0];
			} 
			result = str.split(regex);
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		}
		return result;
	}
	
	
}
