// @ author mfpledon

package trabalhadoresempresa;

import java.io.Serializable;

class Trabalhador implements Serializable, Comparable {
   private String nome;
   private float salario;
   private char sexo;
   private int idade;

   public Trabalhador() {
      nome = "-";
      salario = 0;
      sexo = '-';
      idade = 0;
   }

   public Trabalhador(String inNome, float inSalario,
   	                  char inSexo, int inidade) {
      setNome(inNome);
      setSalario(inSalario);
      setSexo(inSexo);
      setIdade(inidade);
   }

   public void setNome(String inNome) {
      nome = inNome;
   }

   public void setSalario(float inSalario) {
      if (inSalario>0) salario = inSalario; else salario=0;
   }

   public void setSexo(char inSexo) {
      sexo = '-';
      if(inSexo=='F' || inSexo=='M') sexo = inSexo;
   }

  public String getNome() {
  	return nome;  //ou this.nome
  }

  public float getSalario() {
  	return salario;  //ou this.salario
  }

  public String getSalarioStr() {
  	return "R$" + salario;
  }

  public char getSexo() {
  	return sexo;
  }

   public String toString()  {
       return( getNome() + ", salário mensal: R$" + getSalario()
       	       + ", salário anual: R$" + getSalarioAnual()
               + ", sexo: " + getSexo() + ", idade: " + getIdade() );
   }

   public float getSalarioAnual()  {
   	  return(salario * 13 + salario/3);
   }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
    
    public int compareTo(Object outro) {  // comparamos dois trabalhadores pelos nomes
        if(nome.compareToIgnoreCase(((Trabalhador)outro).getNome()) < 0) return -1;
        else if(nome.compareToIgnoreCase(((Trabalhador)outro).getNome()) == 0) return 0;
        else return 1;
    }
    
    public int compareTo2(Object outro) {  // comparamos dois trabalhadores pelas idades
        if(idade < ((Trabalhador)outro).getIdade()) return -1;
        else if(idade == ((Trabalhador)outro).getIdade()) return 0;
        else return 1;
    }
    
} //fim da classe Trabalhador

