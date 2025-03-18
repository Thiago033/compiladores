package libs;

import ast.*;
import java.io.*;
import java.util.*;

public class GeraCodigo {
    private static PrintWriter writer;

    public static void gerar(Prog prog) {
        try {
            writer = new PrintWriter("program.py", "UTF-8");

            // gera as funcoes
            for (Fun f: prog.fun) {
                geraFun(f);
            }

            // gera a funcao main
            geraMain(prog.main);

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    // gera o codigo da funcao main
    public static void geraMain(Main main) {
        writer.println("def main():");

        for (VarDecl vardecl: main.vars) {
            geraVarDecl(vardecl);
        }

        geraComandos(main.coms);

        writer.println();
        writer.println("if __name__ == \"__main__\":");
        writer.println("    main()");
    }


    // gera o codigo de uma funcao
    public static void geraFun(Fun fun) {
        writer.println("def " + fun.nome + "(");

        for (int i = 0; i < fun.params.size(); i++) {
            writer.print("    " + fun.params.get(i).var);
            if (i < fun.params.size() - 1) {
                writer.print(", ");
            }
        }

        writer.println("):");

        for (VarDecl vardecl: fun.vars) {
            geraVarDecl(vardecl);
        }

        geraComandos(fun.body);
    }

    // gera o codigo dos comandos
    public static void geraComandos(ArrayList < Comando > comandos) {
        for (Comando comando: comandos) {
            geraComando(comando);
        }
    }

    // gera o codigo de cada tipo de comando
    public static void geraComando(Comando comando) {
        switch (comando.getClass().getSimpleName()) {
            case "CAtribuicao":
                geraAtribuicao((CAtribuicao) comando);
                break;
            case "CIf":
                geraIf((CIf) comando);
                break;
            case "CWhile":
                geraWhile((CWhile) comando);
                break;
            case "CPrint":
                geraPrint((CPrint) comando);
                break;
            case "CReturn":
                geraReturn((CReturn) comando);
                break;
            case "CReadInput":
                geraReadInput((CReadInput) comando);
                break;
            case "CChamadaFun":
                geraChamadaFun((CChamadaFun) comando);
                break;
            default:
                break;
        }
    }

    // gera o codigo da atribuicao
    public static void geraAtribuicao(CAtribuicao atribuicao) {
        writer.println("    " + atribuicao.var+" = " + geraExp(atribuicao.exp));
    }

    // gera o codigo do comando "if"
    public static void geraIf(CIf ifcmd) {
        writer.println("    if " + geraExp(ifcmd.exp) + ":");
        geraComandos(ifcmd.bloco);
    }

    // gera o codigo do comando "while"
    public static void geraWhile(CWhile whilecmd) {
        writer.println("    while " + geraExp(whilecmd.exp) + ":");
        geraComandos(whilecmd.bloco);
    }

    // gera o codigo do comando "print"
    public static void geraPrint(CPrint print) {
        writer.println("    print(" + geraExp(print.exp) + ")");
    }

    // gera o codigo do comando "return"
    public static void geraReturn(CReturn ret) {
        writer.println("    return " + geraExp(ret.exp));
    }

    // gera o codigo do comando de leitura
    public static void geraReadInput(CReadInput read) {
        writer.println("    " + read.var+" = input()");
    }

    // gera o codigo da chamada de funcao
    public static void geraChamadaFun(CChamadaFun chamada) {
        writer.println("    " + chamada.fun + "(" + geraArgs(chamada.args) + ")");
    }

    // gera os argumentos da chamada de funcao
    public static String geraArgs(ArrayList < Exp > args) {
        StringBuilder ret = new StringBuilder();
        for (Exp exp: args) {
            ret.append(geraExp(exp)).append(", ");
        }
        // remove a ultima virgula e espaço
        if (ret.length() > 0) {
            ret.setLength(ret.length() - 2);
        }
        return ret.toString();
    }

    // gera a expressao
    public static String geraExp(Exp exp) {
        switch (exp.getClass().getSimpleName()) {
            case "EBool":
                return "bool";
            case "ETrue":
                return "True";
            case "EFalse":
                return "False";
            case "EFloat":
                return String.valueOf(((EFloat) exp).value);
            case "EVar":
                return ((EVar) exp).var;
            case "EChamadaFun":
                return geraChamadaFun((EChamadaFun) exp);
            case "EOpExp":
                return geraOper((EOpExp) exp);
            default:
                return "";
        }
    }

    // gera o codigo de uma chamada de funcao
    public static String geraChamadaFun(EChamadaFun chamada) {
        StringBuilder ret = new StringBuilder(chamada.fun).append("(");
        for (int i = 0; i < chamada.args.size(); i++) {
            ret.append(geraExp(chamada.args.get(i)));
            if (i < chamada.args.size() - 1) {
                ret.append(", ");
            }
        }
        return ret + ")";
    }

    // gera a operacao
    public static String geraOper(EOpExp op) {
        return geraExp(op.arg1) + " " + op.op + " " + geraExp(op.arg2);
    }

    // gera a declaracao de variaveis
    public static void geraVarDecl(VarDecl vardecl) {
        writer.println("    " + vardecl.var+" = None"); // Python não requer tipo explícito
    }
}