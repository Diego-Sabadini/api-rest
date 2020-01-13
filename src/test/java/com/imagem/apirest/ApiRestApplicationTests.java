package com.imagem.apirest;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.imagem.apirest.business.ImagemBusiness;
import com.imagem.apirest.models.Imagem;
 

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiRestApplicationTests {

	@Autowired
	ImagemBusiness imagemBusiness;
	
	@Test
	public void testSalvar() {
		
	}
	
	@Test
	public void testFindAllImagem() {
		Boolean result = imagemBusiness.findAllImagens().size() > 1;
		assertTrue(result);
	}
	 
	@Test
	public void testFindImagemByName() {
		List<Imagem> listResult = imagemBusiness.findImagemByName("figura-teste");
		assertTrue(!listResult.isEmpty());
	}

	@Test
	public void testDeleteByUsuario() {
		imagemBusiness.deleteByUsuarioID("usuario-teste");
		List<Imagem> listResult = imagemBusiness.findImagemByName("figura-teste");
		assertTrue(listResult.isEmpty());
	}
}

