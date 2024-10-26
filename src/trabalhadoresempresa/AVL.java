// Implementa uma �rvore AVL
// Elabora��o (2016-2024): Prof. Amilton; adapta��es: Prof. Led�n.

package trabalhadoresempresa;

import java.util.ArrayList;
import java.util.LinkedList;

public class AVL {

    private NoAVL raiz;			//Raiz da �rvore
    private boolean flagInsercao;	//Verifica se j� foi feita a inser��o
    private boolean flagRemove;		//Verifica se j� foi feita a remo��o
    public ArrayList listaLinear;

    public AVL(Object dado, NoAVL pai, NoAVL esq, NoAVL dir) {
        raiz = new NoAVL(dado, pai, esq, dir);
    }

    public AVL(Object dado) {
        this(dado, null, null, null);
    }

    public AVL() {
        raiz = null;
    }

    public NoAVL getRaiz() {
        return raiz;
    }

    public void setRaiz(NoAVL _raiz) {
        raiz = _raiz;
    }

    public boolean isEmpty() {
        return (raiz == null);
    }

    private int compara(Object ob1, Object ob2) {
        return ((Comparable)ob1).compareTo(ob2);
    }
    
    private NoAVL searchNoAVL(NoAVL raiz, Object e) {
        //Se a raiz estiver nula, o elemento n�o existe
        if (raiz == null) {
            return null;
        } else //Elemento encontrado na raiz
        if (compara(e, raiz.getDado()) == 0) {
            return raiz;
        } else //Continue procurando recursivamente
        if (compara(e, raiz.getDado()) < 0) {
            return searchNoAVL(raiz.getEsq(), e);
        } else {
            return searchNoAVL(raiz.getDir(), e);
        }
    }

    public NoAVL searchAVL(Object e) {
        return searchNoAVL(raiz, e);
    }

    //Rota��o Simples para a Direita
    private NoAVL rotacaoSD(NoAVL A) {
        NoAVL B = A.getEsq();

        //Se n�o for a raiz, A tem um pai:
        if (A.getPai() != null) {
            if (A.getPai().getEsq() == A) //Se A for o filho esquerdo, o pai assume como filho esquerdo o B
            {
                A.getPai().setEsq(B);
            } else //Sen�o o pai assume como filho direito o B
            {
                A.getPai().setDir(B);
            }
        }

        //O pai de B agora � o pai de A
        B.setPai(A.getPai());

        //Como o B subiu, pode ter deixado um �rf�o (direito) que quem assume � o A
        A.setEsq(B.getDir());
        //Se A assumiu o filho do B, ent�o setar o pai dele sendo o A
        if (A.getEsq() != null) {
            A.getEsq().setPai(A);
        }

        //B passa a ser o pai de A e A ser� filho de B
        B.setDir(A);
        A.setPai(B);

        return B;
    }

    //Rota��o Simples para a Esquerda
    private NoAVL rotacaoSE(NoAVL A) {
        NoAVL B = A.getDir();
        //Se n�o for a raiz, tem um pai
        if (A.getPai() != null) //Se A for o filho esquerdo, o pai assume como filho esquerdo o B
        {
            if (A.getPai().getDir() == A) {
                A.getPai().setDir(B);
            } //Sen�o o pai assume como filho direito o B
            else {
                A.getPai().setEsq(B);
            }
        }
        //O pai de B agora � o pai de A
        B.setPai(A.getPai());
        //Como o B sumiu, pode ter deixado um �rf�o que quem assume � o A
        A.setDir(B.getEsq());
        //Se assumiu o filho, setar o pai dele sendo o A
        if (A.getDir() != null) {
            A.getDir().setPai(A);
        }
        //B passa a ser pai de A e A filho de B
        B.setEsq(A);
        A.setPai(B);
        return B;
    }

    //Rota��o dupla para a direita
    private NoAVL rotacaoDD(NoAVL A) {
        rotacaoSE(A.getEsq());
        return (rotacaoSD(A));
    }

    //Rota��o dupla para a esquerda
    private NoAVL rotacaoDE(NoAVL A) {
        rotacaoSD(A.getDir());
        return (rotacaoSE(A));
    }

    //Insere um item na �rvore a partir da raiz (m�todo p�blico)
    public void insereAVL(Object k) {
        flagInsercao = false;
        setRaiz(insereNoAVL(raiz, k));
    }

    //M�todo que faz a inser��o
    private NoAVL insereNoAVL(NoAVL raiz, Object x) {
        if (raiz != null) { //Se o n� n�o for nulo
            if (compara(x, raiz.getDado()) < 0) { //Se x for menor que o n� atual, insere recursivamente � esquerda
                raiz.setEsq(insereNoAVL(raiz.getEsq(), x));
                raiz.getEsq().setPai(raiz);
                if (flagInsercao) { //Se j� inseriu
                    switch (raiz.getFb()) {
                        case 1: //Caso ele tinha 1 filho direito, o filho esquerdo balanceou
                            raiz.setFb(0);
                            flagInsercao = false;
                            break;
                        case 0: //Caso n�o tinha filhos, agora tem s� o esquerdo
                            raiz.setFb(-1);
                            break;
                        case -1: //Caso j� tinha um filho esquerdo, tem que rotacionar
                            //Se o filho esquerdo tinha um filho esquerdo, ent�o rota��o simples para a direita
                            if (raiz.getEsq().getFb() == -1) {
                                raiz = rotacaoSD(raiz);
                                raiz.setFb(0);
                                raiz.getDir().setFb(0);
                            }                             
                            else { //Caso contr�rio a rota��o � dupla para a direita
                                raiz = rotacaoDD(raiz);  //rotacaoDD retorna a nova raiz
                                raiz.getDir().setFb(0);
                                raiz.getEsq().setFb(0);
                                raiz.setFb(0);
                            }
                            flagInsercao = false;
                            break;
                    }
                }
            } //fim da inser��o recursiva � esquerda
            else { //Insere Recursivamente � direita                
                raiz.setDir(insereNoAVL(raiz.getDir(), x));
                raiz.getDir().setPai(raiz);
                if (flagInsercao) { //Se j� inseriu
                    switch (raiz.getFb()) {
                        case 0: //Se n�o tinha filhos, agora tem s� o direito
                            raiz.setFb(1);
                            break;
                        case -1: //Se s� tinha um esquerdo, equilibrou
                            raiz.setFb(0);
                            flagInsercao = false;
                            break;
                        case 1: //Se j� tinha filhos direito, tem que rotacionar
                            //Se o filho direito tiver um filho direito, ent�o � rota��o simples para a esquerda
                            if (raiz.getDir().getFb() == 1) {
                                raiz = rotacaoSE(raiz);
                                raiz.setFb(0);
                                raiz.getEsq().setFb(0);
                            } 
                            else {  //Caso contr�rio, rota��o dupla para a esquerda
                                raiz = rotacaoDE(raiz); //rotacaoDE retorna a nova raiz
                                raiz.getDir().setFb(0);
                                raiz.getEsq().setFb(0);
                                raiz.setFb(0);
                            }
                            flagInsercao = false;
                            break;
                    }
                }
            } //fim da inser��o recursiva � direita
        } //Se chegar depois da folha (raiz==null) criar n�:
        else {   // este � o else do   if (raiz != null)
            //Quando chegar na folha, inserir novo NoAVL e trocar a flagInsercao 
            //para passar pelo processo de rota��o
            raiz = new NoAVL(x);
            flagInsercao = true;
        }

        return raiz;
    }

    //Remove uma Object k da �rvore AVl (m�todo p�blico)
    public boolean removeAVL(Object k) {
        flagRemove = false;
        if (isEmpty()) {
            System.out.println("Erro ao remover, �rvore AVL est� vazia!");
            return false;
        } else if (searchAVL(k) == null) {
            System.out.println("Erro ao remover, elemento n�o existe na �rvore!");
            return false;
        } else {
            raiz = removeNoAVL(raiz, k);
            return true;
        }
    }

    //M�todo privado recursivo
    private NoAVL removeNoAVL(NoAVL raiz, Object x) {
        //Se o elemento for menor que a raiz, chamar recursivamente para o lado esquerdo
        if (compara(x, raiz.getDado()) < 0) {
            raiz.setEsq(removeNoAVL(raiz.getEsq(), x));
            //Se j� removeu, relabancear
            if (flagRemove) {
                raiz = balanceamentoEsquerdo(raiz);
            }
        } //Se o elemento for maior que a raiz, chamar recursivamente para o lado direito
        else if (compara(x, raiz.getDado()) > 0) {
            raiz.setDir(removeNoAVL(raiz.getDir(), x));
            //Se j� removeu, relabancear
            if (flagRemove) {
                raiz = balanceamentoDireito(raiz);
            }
        } //Se o elemento a remover est� na raiz (encontrou o n�)
        else {
            //Se n�o tiver um filho direito
            if (raiz.getDir() == null) {
                //Se tiver o filho esquerdo (assume o lugar do pai)
                if (raiz.getEsq() != null) {
                    raiz.getEsq().setPai(raiz.getPai());
                }
                //Filho esquerdo sobe
                raiz = raiz.getEsq();
                flagRemove = true;
            } //Se n�o tiver um filho esquerdo
            else if (raiz.getEsq() == null) {
                //Se tiver o filho direito (assume o lugar do pai)
                if (raiz.getDir() != null) {
                    raiz.getDir().setPai(raiz.getPai());
                }
                //Filho direito sobe
                raiz = raiz.getDir();
                flagRemove = true;
            } //Tem os dois filhos, calcular o GetMax
            else {
                raiz.setEsq(buscaRemove(raiz.getEsq(), raiz));
                //Se necess�rio efetuar balanceamento esquerdo, pois a remo��o foi � esquerda
                if (flagRemove) {
                    raiz = balanceamentoEsquerdo(raiz);
                }
            }
        }
        return raiz;
    }

    //Reorganiza os fatores de balanceamento na remo��o
    private NoAVL balanceamentoEsquerdo(NoAVL no) {
        switch (no.getFb()) {
            case -1: //Se tinha um n� esquerdo, removeu e balanceou
                no.setFb(0);
                break;
            case 0:  //Se n�o tinha filhos, ficou com um � direita
                no.setFb(1);
                break;
            case 1:  //Se tinha 1 n�vel a mais � direita, Balanceou
                NoAVL subDir = no.getDir();
                int fb = subDir.getFb();
                if (fb >= 0) {
                    subDir = rotacaoSE(no);
                    if (fb == 0) {
                        no.setFb(1);
                        subDir.setFb(-1);
                        flagRemove = false;
                    } else {
                        no.setFb(0);
                        subDir.setFb(0);
                    }
                    no = subDir;
                } else {
                    no = rotacaoDD(no);
                    if (no.getFb() == 0) {
                        no.getDir().setFb(0);
                        no.getEsq().setFb(0);
                    } else if (no.getFb() == 1) {
                        no.setFb(0);
                        no.getDir().setFb(0);
                        no.getEsq().setFb(-1);
                    } else {
                        no.setFb(0);
                        no.getDir().setFb(1);
                        no.getEsq().setFb(0);
                    }
                }
        }
        return no;
    }

    //Reorganiza os fatores de balanceamento na remo��o
    private NoAVL balanceamentoDireito(NoAVL no) {
        switch (no.getFb()) {
            case 1: //Se tinha um n� direito, removeu e balanceou
                no.setFb(0);
                break;
            case 0:  //Se n�o tinha filhos, ficou com um � esquerda
                no.setFb(-1);
                flagRemove = false;
                break;
            case -1:  //Se tinha 1 n�vel a mais � direita, balanceou
                NoAVL subEsq = no.getEsq();
                int fb = subEsq.getFb();
                if (fb <= 0) {
                    subEsq = rotacaoSD(no);
                    if (fb == 0) {
                        no.setFb(-1);
                        subEsq.setFb(1);
                        flagRemove = false;
                    } else {
                        no.setFb(0);
                        subEsq.setFb(0);
                    }
                    no = subEsq;
                } else {
                    no = rotacaoDE(no);
                    if (no.getFb() == 0) {
                        no.getDir().setFb(0);
                        no.getEsq().setFb(0);
                    } else if (no.getFb() == -1) {
                        no.setFb(0);
                        no.getDir().setFb(1);
                        no.getEsq().setFb(0);
                    } else {
                        no.setFb(0);
                        no.getDir().setFb(0);
                        no.getEsq().setFb(-1);
                    }
                }
        }
        return no;
    }

    //Busca o maior valor da sub�rvore esquerda para substituir o n� exclu�do
    private NoAVL buscaRemove(NoAVL raiz, NoAVL noChave) {
        NoAVL noRemovido;
        if (raiz.getDir() != null) {
            raiz.setDir(buscaRemove(raiz.getDir(), noChave));
            if (flagRemove) {
                raiz = balanceamentoDireito(raiz);
            }
        } else {
            //Altera o valor da chave
            noChave.setDado(raiz.getDado());
            noRemovido = raiz;
            //Se n� direito com maior valor tem sub�rvore esquerda deve ser removido
            raiz = raiz.getEsq();
            if (raiz != null) {
                raiz.setPai(noRemovido.getPai());
            }
            flagRemove = true;
            noRemovido = null;
        }
        return raiz;
    }

    //M�todo p�blico que retorna a String
    public String emOrdem() {
        return emOrdem(raiz);
    }

    //Atravessamento em ordem
    private String emOrdem(NoAVL raiz) {
        String resp = "";
        if (raiz != null) {
            resp += emOrdem(raiz.getEsq());
            resp += raiz.getDado() + "  " + "\n";
            resp += emOrdem(raiz.getDir());
        }
        return resp;
    }

    public String preOrdemString() {
        return preOrdemString(raiz);
    }

    //Atravessamento em ordem
    private String preOrdemString(NoAVL raiz) {
        String resp = "";
        if (raiz != null) {
            resp += raiz.getDado() + "\n";
            resp += preOrdemString(raiz.getEsq());
            resp += preOrdemString(raiz.getDir());
        }
        return resp;
    }

    //M�todo p�blico
    public void posOrdem() {
        posOrdem(raiz);
    }

    //Atravessamento em p�s-ordem
    private void posOrdem(NoAVL raiz) {
        if (raiz != null) {
            posOrdem(raiz.getEsq());
            posOrdem(raiz.getDir());
            System.out.print(raiz.getDado() + "\n");
        }
    }

    //M�todo p�blico
    public String posOrdemString() {
        return posOrdemString(raiz);
    }

    //Atravessamento em ordem
    private String posOrdemString(NoAVL raiz) {
        String resp = "";
        if (raiz != null) {
            resp += posOrdemString(raiz.getEsq());
            resp += posOrdemString(raiz.getDir());
            resp += raiz.getDado() + "\n";
        }
        return resp;
    }

    //Atravessamento em n�vel
    public void emNivel() {
        NoAVL noAux;
        LinkedList f;
        if (!isEmpty()) {
            f = new LinkedList();
            f.addLast(raiz);
            while (!f.isEmpty()) {
                noAux = (NoAVL) f.removeFirst();
                if (noAux.getEsq() != null) {
                    f.addLast(noAux.getEsq());
                }
                if (noAux.getDir() != null) {
                    f.addLast(noAux.getDir());
                }
                System.out.print(noAux.getDado() + "\n");
            }
        }
    }

    public String emNivelString() {
        NoAVL noAux;
        LinkedList f;
        String resp = "";
        if (!isEmpty()) {
            f = new LinkedList();
            f.addLast(raiz);
            while (!f.isEmpty()) {
                noAux = (NoAVL) f.removeFirst();
                if (noAux.getEsq() != null) {
                    f.addLast(noAux.getEsq());
                }
                if (noAux.getDir() != null) {
                    f.addLast(noAux.getDir());
                }
                resp += noAux.getDado() + "\n";
            }
        }
        return resp;
    }

}
