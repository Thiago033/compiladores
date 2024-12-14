public class Interpreter {
    
    int interpreta(ArvoreSintatica arv) throws Exception {
        if (arv instanceof Num) {
            return ((Num) arv).num; // retorna numero

        } else if (arv instanceof Soma) {
            return interpreta(((Soma) arv).arg1) + interpreta(((Soma) arv).arg2);

        } else if (arv instanceof Mult) {
            return interpreta(((Mult) arv).arg1) * interpreta(((Mult) arv).arg2);

        } else if (arv instanceof Sub) {
            return interpreta(((Sub) arv).arg1) - interpreta(((Sub) arv).arg2);

        } else if (arv instanceof Div) {
            int divisor = interpreta(((Div) arv).arg2);

            if (divisor == 0) {
                throw new Exception("ERRO: divisao por zero");
            }

            return interpreta(((Div) arv).arg1) / divisor;

        } else {
            throw new Exception("ERRO: tipo de expressao desconhecido");
        }
    }
}
