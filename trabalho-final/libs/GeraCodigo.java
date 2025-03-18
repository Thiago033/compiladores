package libs;

import ast.*;
import java.io.*;
import java.util.*;

public class GeraCodigo {
    private static PrintWriter writer;

    public static void gerar(Prog prog) {
        try {
            writer = new PrintWriter("program.py", "UTF-8");

            // Gera as funções
            for(Fun f : prog.fun){ 
                geraFun(f);
            }

            // Gera a função main
            geraMain(prog.main);

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

// Gera o código da função main
public static void geraMain(Main main){
    writer.println("def main():");

    for(VarDecl vardecl: main.vars){
        geraVarDecl(vardecl);
    }

    geraComandos(main.coms);
    
    writer.println();
    writer.println("if __name__ == \"__main__\":");
    writer.println("    main()");
}


    // Gera o código de uma função
    public static void geraFun(Fun fun){
        writer.println("def " + fun.nome + "(");
        
        for(int i = 0; i < fun.params.size(); i++){
            writer.print("    " + fun.params.get(i).var);
            if(i < fun.params.size() - 1){
                writer.print(", ");
            }
        }

        writer.println("):");

        for(VarDecl vardecl: fun.vars){
            geraVarDecl(vardecl);
        }

        geraComandos(fun.body);
    }

    // Gera o código dos comandos
    public static void geraComandos(ArrayList<Comando> comandos){
        for(Comando comando: comandos){
            geraComando(comando);
        }
    }

    // Gera o código de cada tipo de comando
    public static void geraComando(Comando comando){
        switch (comando.getClass().getSimpleName()) {
            case "CAtribuicao":
                geraAtribuicao((CAtribuicao)comando);
                break;
            case "CIf":
                geraIf((CIf)comando);
                break;
            case "CWhile":
                geraWhile((CWhile)comando);
                break;
            case "CPrint":
                geraPrint((CPrint)comando);
                break;
            case "CReturn":
                geraReturn((CReturn)comando);
                break;
            case "CReadInput":
                geraReadInput((CReadInput)comando);
                break;
            case "CChamadaFun":
                geraChamadaFun((CChamadaFun)comando);
                break;
            default:
                break;
        }
    }

    // Gera o código da atribuição
    public static void geraAtribuicao(CAtribuicao atribuicao){
        writer.println("    " + atribuicao.var + " = " + geraExp(atribuicao.exp));
    }

    // Gera o código do comando "if"
    public static void geraIf(CIf ifcmd){
        writer.println("    if " + geraExp(ifcmd.exp) + ":");
        geraComandos(ifcmd.bloco);
    }

    // Gera o código do comando "while"
    public static void geraWhile(CWhile whilecmd){
        writer.println("    while " + geraExp(whilecmd.exp) + ":");
        geraComandos(whilecmd.bloco);
    }

    // Gera o código do comando "print"
    public static void geraPrint(CPrint print){
        writer.println("    print(" + geraExp(print.exp) + ")");
    }

    // Gera o código do comando "return"
    public static void geraReturn(CReturn ret){
        writer.println("    return " + geraExp(ret.exp));
    }

    // Gera o código do comando de leitura
    public static void geraReadInput(CReadInput read){
        writer.println("    " + read.var + " = input()");
    }

    // Gera o código da chamada de função
    public static void geraChamadaFun(CChamadaFun chamada){
        writer.println("    " + chamada.fun + "(" + geraArgs(chamada.args) + ")");
    }

    // Gera os argumentos da chamada de função
    public static String geraArgs(ArrayList<Exp> args){
        StringBuilder ret = new StringBuilder();
        for(Exp exp: args){
            ret.append(geraExp(exp)).append(", ");
        }
        // Remove a última vírgula e espaço
        if (ret.length() > 0) {
            ret.setLength(ret.length() - 2);
        }
        return ret.toString();
    }

    // Gera a expressão
    public static String geraExp(Exp exp){
        switch (exp.getClass().getSimpleName()) {
            case "EBool":
                return "bool";
            case "ETrue":
                return "True";
            case "EFalse":
                return "False";
            case "EFloat":
                return String.valueOf(((EFloat)exp).value);
            case "EVar":
                return ((EVar)exp).var;
            case "EChamadaFun":
                return geraChamadaFun((EChamadaFun)exp);
            case "EOpExp":
                return geraOper((EOpExp)exp);
            default:
                return "";
        }
    }

    // Gera o código de uma chamada de função
    public static String geraChamadaFun(EChamadaFun chamada){
        StringBuilder ret = new StringBuilder(chamada.fun).append("(");
        for(int i = 0; i < chamada.args.size(); i++){
            ret.append(geraExp(chamada.args.get(i)));
            if(i < chamada.args.size() - 1){
                ret.append(", ");
            }
        }
        return ret + ")";
    }

    // Gera a operação
    public static String geraOper(EOpExp op){
        return geraExp(op.arg1) + " " + op.op + " " + geraExp(op.arg2);
    }

    // Gera a declaração de variáveis
    public static void geraVarDecl(VarDecl vardecl){
        writer.println("    " + vardecl.var + " = None");  // Python não requer tipo explícito
    }
}
