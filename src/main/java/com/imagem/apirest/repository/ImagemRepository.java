package com.imagem.apirest.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.imagem.apirest.models.Imagem;

@Component
public class ImagemRepository {
	
	public void salvarFoto(String diretorioRaiz, String diretorio, MultipartFile arquivo) {
		Path diretorioPath = Paths.get(diretorioRaiz, diretorio);
		Path arquivoPath = diretorioPath.resolve(arquivo.getOriginalFilename());
		
		try {
			Files.createDirectories(diretorioPath);
			arquivo.transferTo(arquivoPath.toFile());			
		} catch (IOException e) {
			throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
		}		
	}
	
	public List<Imagem> findAllImagens(String diretorioRaiz){

		List<Imagem> listResult = new ArrayList<Imagem>();
		File f = new File(diretorioRaiz);
		String[] listUsuarios = f.list();

		for(String usuario : listUsuarios){
			Imagem img = new Imagem();
			img.setIdUsuario(usuario);

			String dirImagem = diretorioRaiz + "/" + usuario + "/";
			File arqImagem = new File(dirImagem);

			String[] nameImagem = arqImagem.list();

			for(String item : nameImagem){
				String name = item.substring(0, item.lastIndexOf("."));
				String tipo = item.substring(item.lastIndexOf("."), item.length());
				
				img.setNomeArquivo(name);
				img.setTipoArquivo(tipo);
			}

			listResult.add(img);
		} 	     
		return listResult;
	}

	public List<Imagem> findImagemByName(String nameBusca, String diretorioRaiz){
		List<Imagem> listResult = new ArrayList<Imagem>();
		File f = new File(diretorioRaiz);
		String[] listUsuarios = f.list();

		for(String usuario : listUsuarios){
			Imagem img = new Imagem();
			img.setIdUsuario(usuario);

			String dirImagem = diretorioRaiz + "/" + usuario + "/";
			File arqImagem = new File(dirImagem);

			String[] nameImagem = arqImagem.list();

			for(String item : nameImagem){
				String name = item.substring(0, item.lastIndexOf("."));
				String tipo = item.substring(item.lastIndexOf("."), item.length());

				img.setNomeArquivo(name);
				img.setTipoArquivo(tipo);
			}

			if(img.getNomeArquivo().equals(nameBusca)) {
				listResult.add(img);
			} 	     
		}
		return listResult;
	}
	
	public void deleteByUsuarioID(String idUsuario, String diretorioRaiz){
		String diretorioUser = diretorioRaiz + "/" + idUsuario + "/";
		File file = new File(diretorioUser);
		if(file.exists() && file.isDirectory()) {
			File[] imagens = file.listFiles();
			for(File toDelete : imagens) {
				toDelete.delete();
			}			
			file.delete();
		}else {
			throw new RuntimeException("Deleção não realizada. Motivo: Usuário não encontrado.");
		}
	}
}
