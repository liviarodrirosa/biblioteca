package catalogo.catalogoLivros;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class CatalogoLivrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogoLivrosApplication.class, args);
	}

	@Entity
	public static class Livro {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String titulo;
		private String idioma;
		private Integer downloads;

		@ManyToOne(cascade = CascadeType.ALL)
		private Autor autor;


		public Long getId() { return id; }
		public void setId(Long id) { this.id = id; }

		public String getTitulo() { return titulo; }
		public void setTitulo(String titulo) { this.titulo = titulo; }

		public String getIdioma() { return idioma; }
		public void setIdioma(String idioma) { this.idioma = idioma; }

		public Integer getDownloads() { return downloads; }
		public void setDownloads(Integer downloads) { this.downloads = downloads; }

		public Autor getAutor() { return autor; }
		public void setAutor(Autor autor) { this.autor = autor; }

		@Override
		public String toString() {
			return "Livro{" +
					"id=" + id +
					", titulo='" + titulo +
					", idioma='" + idioma +
					", downloads=" + downloads +
					", autor=" + autor +
					'}';
		}
	}

	@Entity
	public static class Autor {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String nome;
		private Integer anoNascimento;
		private Integer anoFalecimento;

		@OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
		private List<Livro> livros;


		public Long getId() { return id; }
		public void setId(Long id) { this.id = id; }

		public String getNome() { return nome; }
		public void setNome(String nome) { this.nome = nome; }

		public Integer getAnoNascimento() { return anoNascimento; }
		public void setAnoNascimento(Integer anoNascimento) { this.anoNascimento = anoNascimento; }

		public Integer getAnoFalecimento() { return anoFalecimento; }
		public void setAnoFalecimento(Integer anoFalecimento) { this.anoFalecimento = anoFalecimento; }

		@Override
		public String toString() {
			return "Autor{" +
					"id=" + id +
					", nome='" + nome +
					", anoNascimento=" + anoNascimento +
					", anoFalecimento=" + anoFalecimento +
					'}';
		}
	}

	public interface LivroRepository extends JpaRepository<Livro, Long> {
		List<Livro> findByIdiomaIgnoreCase(String idioma);
	}

	public interface AutorRepository extends JpaRepository<Autor, Long> {
		List<Autor> findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(int ano, int ano2);
	}


    }

