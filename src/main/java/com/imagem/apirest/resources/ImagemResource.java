package com.imagem.apirest.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.imagem.apirest.business.ImagemBusiness;
import com.imagem.apirest.models.Imagem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/api")
@Api(value="API REST Imagens")
public class ImagemResource {

	@Autowired
	ImagemBusiness imagemBusiness;

	@ApiOperation(value="Salva uma imagem")
	@PostMapping
	public void upload(@RequestParam MultipartFile foto, @RequestParam String idUsuario) {
		imagemBusiness.salvarFoto(idUsuario, foto);
	}
	
	@ApiOperation(value="Retorna uma lista de imagens")
	@GetMapping("/imagens")
	public List<Imagem> findAllImagens(){
		return imagemBusiness.findAllImagens();
	}
	
	@ApiOperation(value="Retorna lista de imagens buscadas pelo nome")
	@GetMapping("/busca-imagem/{name}")
	public List<Imagem> findImagemByName(@PathVariable(value="name") String name){
		return imagemBusiness.findImagemByName(name);
	}
	
	@ApiOperation(value="Deleta imagens por ID usuário")
	@DeleteMapping("/delete-imagem/{id}")
	public void deletaImagemByUsuario(@RequestParam @Valid String idUsuario) {
		imagemBusiness.deleteByUsuarioID(idUsuario);
	}
}