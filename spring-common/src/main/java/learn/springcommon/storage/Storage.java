package learn.springcommon.storage;

import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * 与存储层（例如文件系统）进行交互
 */
public interface Storage {

	void store(InputStream inputStream, long contentLength, String contentType, String keyName);

	Path load(String keyName);

	Stream<Path> loadAll();

	Resource loadAsResource(String filename);

	void delete(String keyName);

	String generateUrl(String keyName);



}