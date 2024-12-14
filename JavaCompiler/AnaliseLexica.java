import java.io.*;

enum TokenType { NUM, SOMA, MULT, APar, FPar, EOF }

class Token {
    String lexema; // Alterado para String para suportar números com mais de um dígito
    TokenType token;

    Token(String l, TokenType t) {
        lexema = l;
        token = t;
    }
}

class AnaliseLexica {

    BufferedReader arquivo;
    int lastChar = -1; // Armazena o último caractere lido, caso precise ser retornado

    AnaliseLexica(String a) throws Exception {
        this.arquivo = new BufferedReader(new FileReader(a));
    }

    Token getNextToken() throws Exception {
        int eof = -1;
        char currchar;
        int currchar1;

        // Ignorar espaços, tabulações e quebras de linha
        do {
            if (lastChar != -1) { // Usar o último caractere lido, se disponível
                currchar1 = lastChar;
                lastChar = -1;
            } else {
                currchar1 = arquivo.read();
            }
            currchar = (char) currchar1;
        } while (currchar == '\n' || currchar == ' ' || currchar == '\t' || currchar == '\r');

        if (currchar1 != eof && currchar1 != 10) {
            // Verifica se o caractere atual é um dígito
            if (currchar >= '0' && currchar <= '9') {
                StringBuilder numero = new StringBuilder();
                numero.append(currchar);

                // Ler os próximos caracteres enquanto forem dígitos
                while (true) {
                    currchar1 = arquivo.read();
                    currchar = (char) currchar1;

                    if (currchar >= '0' && currchar <= '9') {
                        numero.append(currchar);
                    } else {
                        lastChar = currchar1; // Retorna o último caractere não numérico ao buffer
                        break;
                    }
                }
                return new Token(numero.toString(), TokenType.NUM);
            } else {
                // Identificar outros tokens
                switch (currchar) {
                    case '(':
                        return new Token(String.valueOf(currchar), TokenType.APar);
                    case ')':
                        return new Token(String.valueOf(currchar), TokenType.FPar);
                    case '+':
                        return new Token(String.valueOf(currchar), TokenType.SOMA);
                    case '*':
                        return new Token(String.valueOf(currchar), TokenType.MULT);
                    default:
                        throw new Exception("Caractere inválido: " + ((int) currchar));
                }
            }
        }

        arquivo.close();
        return new Token(String.valueOf(currchar), TokenType.EOF);
    }
}
