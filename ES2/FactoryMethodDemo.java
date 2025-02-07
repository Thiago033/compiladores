// Interface Transport
interface Transport {
    void deliver();
}

// Implementação de Transport para caminhão
class Truck implements Transport {
    @Override
    public void deliver() {
        System.out.println("Deliver by land in a box.");
    }
}

// Implementação de Transport para navio
class Ship implements Transport {
    @Override
    public void deliver() {
        System.out.println("Deliver by sea in a container.");
    }
}

// Classe abstrata Logistics
abstract class Logistics {
    // Método fábrica
    public abstract Transport createTransport();
    
    // Método que usa o produto
    public void planDelivery() {
        Transport transport = createTransport();
        transport.deliver();
    }
}

// Subclasse para logística rodoviária
class RoadLogistics extends Logistics {
    @Override
    public Transport createTransport() {
        return new Truck();
    }
}

// Subclasse para logística marítima
class SeaLogistics extends Logistics {
    @Override
    public Transport createTransport() {
        return new Ship();
    }
}

// Classe principal para testar o código
public class FactoryMethodDemo {
    public static void main(String[] args) {
        // Criando instâncias das logísticas
        Logistics roadLogistics = new RoadLogistics();
        roadLogistics.planDelivery();

        Logistics seaLogistics = new SeaLogistics();
        seaLogistics.planDelivery();
    }
}
