package com.imagem.apirest.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.imagem.apirest.models.Imagem;
import com.imagem.apirest.repository.ImagemRepository;

@Component
public class ImagemBusiness {

	// Diretório raiz fixo, sendo que este deve ser alterado conforme necessidade.
	private final String DIRETORIO_RAIZ = "C:/tmp/projeto-api-rest";
	
	@Autowired
	ImagemRepository imagemRepository;
	
	public void salvarFoto(String diretorio, MultipartFile arquivo) {
		validateSize(arquivo);
		validateExtension(arquivo);
	
		imagemRepository.salvarFoto(DIRETORIO_RAIZ, diretorio, arquivo);
	}
	
	private void validateSize(MultipartFile arq) {
		Integer cincoMB = 5000000;
		if(arq.getSize()> cincoMB) {
			throw new RuntimeException("Arquivo Inválido. Motivo: Tamanho superior a 5MB.");
		}
	}
	
	private void validateExtension(MultipartFile arq) {
		String nameArq = arq.getOriginalFilename();
		String tipo = nameArq.substring(nameArq.lastIndexOf("."), nameArq.length()).toUpperCase();
		if(!tipo.equals(".JPEG") && !tipo.equals(".JFIF") && !tipo.equals(".GIF")
		 && !tipo.equals(".BMP") && !tipo.equals(".PNG")) {
			throw new RuntimeException("Arquivo Inválido. Motivo: Extensão não permitida!");			
		}
	}

	public List<Imagem> findAllImagens(){
		return imagemRepository.findAllImagens(DIRETORIO_RAIZ);
	}
	
	public List<Imagem> findImagemByName(String nameBusca){
		return imagemRepository.findImagemByName(nameBusca, DIRETORIO_RAIZ);		
	}
	
	public void deleteByUsuarioID(String idUsuario){
		imagemRepository.deleteByUsuarioID(idUsuario, DIRETORIO_RAIZ);
	}
}
