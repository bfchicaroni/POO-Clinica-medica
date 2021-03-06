package pessoa;

import agenda.Agenda;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import exames.Exames;
public class Paciente extends Pessoa {
	
	private String sobrenome;
	private String sexo;
	private int idade;
	private float altura;
	private int peso;
	private String telefone;
	private String email;
	private String ortopedista;
	private String fisiatra;

	public Paciente(String id, String senha, String nome, String sobrenome, String sexo, int idade, float altura, int peso, String telefone, String email) throws VazioException{
		super(id, senha, nome);
		setSobrenome(sobrenome);
		setSexo(sexo);
		setIdade(idade);
		setAltura(altura);
		setPeso(peso);
		setTelefone(telefone);
		setEmail(email);
	}
	
	@Override
	public void menu () {
		Scanner scan = new Scanner(System.in);
		
		int opcao = 0;
		
		while(opcao != 3) {
			System.out.println("O que voce quer fazer?");
			System.out.println("1 - Visualizar agenda");
			System.out.println("2 - Visualizar lista de exames");
			System.out.println("3 - Sair da conta");
			
			opcao = scan.nextInt();
			switch (opcao) {
				case 1 :
					visualizarAgenda();
					break;
				case 2:
					visualizarExames();
					break;
			}	
		}
	}
	
	@Override
	public void visualizarAgenda() {
		Agenda agenda = null;
		try { 
			agenda = Agenda.getInstance();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		try {
			agenda.visualizaAgenda(getId());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void visualizarExames() {
		Exames exames = null;
		try { 
			exames = Exames.getInstance();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		try {
			exames.visualizarExames();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private boolean profissionalExiste(String id, String file) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(file));
		
		while(scan.hasNext()){
			String line = scan.nextLine().toString();
			if (line.contains(id)) {
				return true;
			}
		} 
		return false;
	}
	
	public String getSobrenome() {
		return sobrenome;
	}
	
	public void setSobrenome(String sobrenome) {
		if (!sobrenome.isEmpty() && sobrenome!=null) {
			this.sobrenome = sobrenome;
		}
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		if (!sexo.isEmpty() && sexo!=null) {
			this.sexo = sexo;
		}
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public float getAltura() {
		return altura;
	}
	
	public void setAltura(float altura) {
		this.altura = altura;
	}
	
	public int getPeso() {
		return peso;
	}
	
	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		if (!telefone.isEmpty() && telefone!=null) {
			this.telefone = telefone;
		}
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		if (!email.isEmpty() && email!=null) {
			this.email = email;
		}
	}
	
	public String getOrtopedista() {
		return ortopedista;
	}
	
	public void setOrtopedista(String ortopedista) throws FileNotFoundException{
		if (!ortopedista.isEmpty() && ortopedista!=null && profissionalExiste(ortopedista, "../../db/listaMedico.txt")) {
			this.ortopedista = ortopedista;
		}
	}
	
	public String getFisiatra() {
		return fisiatra;
	}
	
	public void setFisiatra(String fisiatra) throws FileNotFoundException{
		if (!fisiatra.isEmpty() && fisiatra!=null && profissionalExiste(fisiatra, "../../db/listaTecEnfermagem.txt")) {
			this.fisiatra = fisiatra;
		}
	}
}
