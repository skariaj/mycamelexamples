package com.skaria.camel.examples.filecopyexample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.File;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class FileCopyExample_NIO {
	
	public static void main(String[] args) throws IOException {
		
		String inbox = "data/inbox";
		String outbox = "data/outbox";
		String filename = "sample1.txt";
		Path inboxDirPath = Paths.get(inbox );
		Path outboxDirPath = Paths.get(outbox );
		
		Path inboxFilePath = Paths.get(inbox + File.separator + filename);
		Path outboxFilePath = Paths.get(outbox + File.separator + filename);
		
		//delete contents of outbox
		if(Files.exists(outboxDirPath))
		{
			deleteDirContents(outboxDirPath);
		}
		else
		{
		//create outbox dir only
		Files.createDirectory(outboxDirPath);
		}
		//copy
		Files.copy(inboxFilePath, outboxFilePath, StandardCopyOption.REPLACE_EXISTING);
		assertNotNull(outboxFilePath);
		assertEquals(Files.readAllLines(inboxFilePath), 
				Files.readAllLines(outboxFilePath));
		
	}

	//Java 8 stream to delete all contents except for dir itself
	private static void deleteDirContents(Path outboxDirPath) throws IOException {
		Function<Path, Stream<Path>> walk = p ->{
			try {
				return Files.walk(p);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				return Stream.empty();
			}
		};
		
		Consumer<Path> delete = p ->{
			try {
				Files.delete(p);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		Files.list(outboxDirPath)
			.flatMap(walk)
			.sorted(Collections.reverseOrder())
			.forEach(delete);
	}

}
