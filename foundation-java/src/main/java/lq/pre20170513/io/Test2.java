package lq.pre20170513.io;

import java.io.*;

/**
 * 编写一个程序，将 F:\java 目录下的所有.java 文件复制到 F:\jad 目录下， 并将原来文件的扩展名从.java 改为.jad。
 *
 */
public class Test2 {
	public static void main(String[] args) throws Exception {
		File srcDir = new File("F:/java");
		if (!(srcDir.exists() && srcDir.isDirectory()))
			throw new Exception("目录不存在");
		// 这里很关键，listFiles 方法接受一个 FileFilter 对象， 这个 FileFilter 对象就是过虑的策略对象，
		// 不同的人提供不同的 FileFilter 实现，即提供了不同的过滤策略。
		File[] files = srcDir.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".java");
			}
		});

		File destDir = new File("F:/jad");
		if (!destDir.exists())
			destDir.mkdirs();
		for (File f : files) {
			FileInputStream fis = new FileInputStream(f);
			String name = f.getName().replaceAll("\\.java$", ".jad");
			FileOutputStream fos = new FileOutputStream(new File(destDir, name));
			copy(fis, fos);
			fis.close();
			fos.close();
		}

	}

	// 将一个文件复制到另一个文件
	private static void copy(FileInputStream fis, FileOutputStream fos) {
		int len = 0;
		byte[] buf = new byte[1024];
		try {
			while ((len = fis.read(buf)) != -1) {
				fos.write(buf, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 分析 listFiles 方法内部的策略模式实现原理,这里是源码分析，不属于本题代码答案
	/*File[] listFiles(FileFilter filter) {
		File[] files = listFiles();
		// ArraylistacceptedFilesList = new ArrayList();
		File[] acceptedFiles = new File[files.length];
		int pos = 0;
		for (File file : files) {
			booleanaccepted = filter.accept(file);
			if (accepted) {
				// acceptedFilesList.add(file);
				acceptedFiles[pos++] = file;
			}
		}
		Arrays.copyOf(acceptedFiles, pos);
		// return(File[])accpetedFilesList.toArray();
	}*/
}
