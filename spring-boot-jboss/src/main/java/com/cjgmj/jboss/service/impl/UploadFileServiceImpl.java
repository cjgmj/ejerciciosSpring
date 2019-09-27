package com.cjgmj.jboss.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.cjgmj.jboss.service.IUploadFileService;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

	private final static Logger LOG = LoggerFactory.getLogger(UploadFileServiceImpl.class);

	private final static String UPLOADS_FOLDER = "uploads";

	@Override
	public Resource load(String filename) throws MalformedURLException {
		Path pathFoto = getPath(filename);
		LOG.info("Ruta foto: " + pathFoto);

		Resource recurso = new UrlResource(pathFoto.toUri());

		if (!recurso.exists() || !recurso.isReadable()) {
			throw new RuntimeException("Error: no se puede cargar la imagen: " + pathFoto.toString());
		}

		return recurso;
	}

	@Override
	public String copy(MultipartFile file) throws IOException {
		String fName = file.getOriginalFilename();
		String[] arrFileName = fName.split("[.]");
		String fileName = fName.replace("." + arrFileName[arrFileName.length - 1], "");
		String uniqueFilename = fileName + "_" + UUID.randomUUID().toString() + "."
				+ arrFileName[arrFileName.length - 1];

		Path rootPath = getPath(uniqueFilename);

		LOG.info("Ruta: " + rootPath);

		Files.copy(file.getInputStream(), rootPath);

		return uniqueFilename;
	}

	@Override
	public boolean delete(String filename) {
		Path rootPath = getPath(filename);
		File archivo = rootPath.toFile();

		if (archivo.exists() && archivo.canRead()) {
			return archivo.delete();
		}
		return false;
	}

	private Path getPath(String filename) {
		return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(Paths.get(UPLOADS_FOLDER).toFile());
	}

	@Override
	public void init() throws IOException {
		Files.createDirectory(Paths.get(UPLOADS_FOLDER));
	}

}
