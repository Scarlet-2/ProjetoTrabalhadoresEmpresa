package trabalhadoresempresa;

public class BinaryMinHeap {

    private Comparable data[];
    private int heapSize;
    private int maxHeapCapacity;

    public BinaryMinHeap(int maxHeapCapacity) {
        heapSize = 0; //inicialmente o tamanho do heap é zero (0)
        //alocamos memória para o vetor que guardará o heap: 
        data = new Comparable[maxHeapCapacity];
        this.maxHeapCapacity = maxHeapCapacity;
    }

    public boolean isEmpty() {
        return (heapSize == 0);
    }

    public int getHeapSize() {
        return heapSize; //retorna o tamanho do heap
    }
    
    public int getMaxHeapCapacity() {
        return maxHeapCapacity;
    }
    
    public Comparable [] getData() {
        return data; //retorna o vetor que guarda o heap
    }

    public Comparable getMin() {
        return data[0]; //retorna o mínimo (menor prioridade) sem eliminá-lo
    }

    public void clear() { //inutilizamos o heap
        heapSize = 0;
        data = null;
    }

    public Comparable insert(Comparable value) { //inserir value no heap
        if (heapSize == data.length) {
            return null; //retornamos null se memória insuficiente
        } else {
            heapSize++;
            data[heapSize - 1] = value; //colocar o novo valor no final do vetor
            examineUp(heapSize - 1);
            return value; //retornamos o mesmo objeto inserido no heap
        }
    }

    private void examineUp(int nodePos) {
        //este método recursivo faz trocas sempre que o objeto no nodo pai
        //tenha prioridade (valor) maior que a prioridade do filho (é um min heap)
        int parentPos;
        Comparable temp;
        if (nodePos != 0) {
            parentPos = getParentPos(nodePos);
//            if (data[parentPos].compareTo(data[nodePos]) > 0)
            if(compareTo((Trabalhador)data[parentPos], (Trabalhador)data[nodePos]) > 0)
            {
                //trocamos o objeto pai com o objeto filho:
                temp = data[parentPos];
                data[parentPos] = data[nodePos];
                data[nodePos] = temp;
                examineUp(parentPos); //chamada recursiva
            }
        }
    }

    public int getParentPos(int i) {
        //calcula e retorna a posição do nodo pai:
        return (i - 1) / 2;
    }

    public int getLeftChildPos(int i) {
        //calcula e retorna a posição (dentro do vetor) do filho esquerdo de i:
        return (2 * i) + 1;
    }

    public int getRightChildPos(int i) {
        //calcula e retorna a posição (dentro do vetor) do filho direito de i:
        return (2 * i) + 2;
    }

    public Comparable deleteMin() {
        //retorna o mínimo (item com menor prioridade) e o elimina do heap
        Comparable min;
        if (isEmpty()) {
            return null; //retornamos null se o heap está vazio
        } else {
            //guardamos em min o menor valor do heap, para retornar o mesmo ao terminar:
            min = data[0];
            //colocamos o item que está no final do vetor na posição do objeto eliminado:
            data[0] = data[heapSize - 1];
            heapSize--; //o heap terá agora um item a menos
            if (heapSize > 0) {
                examineDown(0); //começando na posição 0
            }
            return min; //retornamos o menor valor do heap
        }
    }

    private void examineDown(int nodePos) {
        int leftChildPos, rightChildPos, minPos;
        Comparable temp;
        //pegamos as posições dos filhos esquerdo e direito do nodo atual:
        leftChildPos = getLeftChildPos(nodePos);
        rightChildPos = getRightChildPos(nodePos);
        if (rightChildPos >= heapSize) {             
            if (leftChildPos >= heapSize) {
                return; //as posições ultrapassaram o tamanho do heap: nada a fazer
            } else { 
                //caso especial: um único filho
                minPos = leftChildPos;
            }
        } else {
            //verificaremos qual é menor (se é o filho esquerdo ou o filho direito)
            //e guardamos sua posição:
//            if (data[leftChildPos].compareTo(data[rightChildPos]) <= 0) {
            if(compareTo((Trabalhador)data[leftChildPos], (Trabalhador)data[rightChildPos]) <= 0) {
                minPos = leftChildPos;
            } else {
                minPos = rightChildPos;
            }
        }
        //se o objeto atual for maior que o menor dos filhos:
//        if (data[nodePos].compareTo(data[minPos]) > 0) {
        if(compareTo((Trabalhador)data[nodePos], (Trabalhador)data[minPos]) <= 0) {
            //troca o objeto atual com o menor dos filhos:
            temp = data[minPos];
            data[minPos] = data[nodePos];
            data[nodePos] = temp;
            examineDown(minPos); //e efetuamos uma chamada recursiva avançando para baixo
        }
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < heapSize; i++) {
            str += data[i] + "\n";
        }
        return str;
    }
    
    public static BinaryMinHeap merge(BinaryMinHeap heap1, BinaryMinHeap heap2) {
        int max = heap1.getMaxHeapCapacity() + heap2.getMaxHeapCapacity();
        BinaryMinHeap heap3 = new BinaryMinHeap(max);
        int m = heap1.getHeapSize();
        int n = heap2.getHeapSize();
        for (int i=0; i < m; i++) {
            heap3.insert(heap1.getData()[i]);
        }
        for (int i=0; i < n; i++) {
            heap3.insert(heap2.getData()[i]);
        }
        return heap3;
    }
    
    public int compareTo(Trabalhador propio, Trabalhador outro) {  // comparamos dois trabalhadores pelas idades
        if(propio.getIdade() < ((Trabalhador)outro).getIdade()) return -1;
        else if(propio.getIdade() == ((Trabalhador)outro).getIdade()) return 0;
        else return 1;
    }
    
}

