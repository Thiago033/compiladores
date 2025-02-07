// Interface comum para todos os transportes
interface Transporte {
    void entregar();
}

// Implementações concretas dos transportes
class Caminhao implements Transporte {
    public void entregar() {
        System.out.println("Entrega feita por caminhão.");
    }
}

class Navio implements Transporte {
    public void entregar() {
        System.out.println("Entrega feita por navio.");
    }
}

// Criador abstrato com o Factory Method
abstract class Logistica {
    abstract Transporte criarTransporte();
    
    void planejarEntrega() {
        Transporte transporte = criarTransporte();
        transporte.entregar();
    }
}

// Criadores concretos
class LogisticaRodoviaria extends Logistica {
    Transporte criarTransporte() {
        return new Caminhao();
    }
}

class LogisticaMaritima extends Logistica {
    Transporte criarTransporte() {
        return new Navio();
    }
}

// Exemplo de uso
public class Main {
    public static void main(String[] args) {
        Logistica logistica1 = new LogisticaRodoviaria();
        logistica1.planejarEntrega(); // Saída: Entrega feita por caminhão.
        
        Logistica logistica2 = new LogisticaMaritima();
        logistica2.planejarEntrega(); // Saída: Entrega feita por navio.
    }
}
