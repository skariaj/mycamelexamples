package com.skaria.camel.examples.filecopyexample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCopyExample {

	public static void main(String[] args) throws IOException {
		
		File inboxDir = new File("data/inbox");
		File outboxDir = new File("data/outbox");
		
		outboxDir.mkdir();
		File[] files = inboxDir.listFiles();
		if(files == null)
		{
			System.out.println("No files found!!");
		}
		else
		{
		for(File file : files)
		{
			if(file.isFile())
			{
				File destFile = new File(
						outboxDir.getPath()
						+ File.separator
						+ file.getName());
				
				copyFile(file, destFile);
			}
		}
		}
	}

	private static void copyFile(File file, File destFile) throws IOException {
		InputStream inputStream = new FileInputStream(file);
		OutputStream outputStream = new FileOutputStream(destFile);
		byte[] buffer = new byte[(int) file.length()];
		try {

			inputStream.read(buffer);
			outputStream.write(buffer);
		} finally {
			inputStream.close();
			outputStream.close();
		}

	}

}
