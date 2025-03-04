/* Generated By:JavaCC: Do not edit this line. Lugosi.java */
import java.io.*;
import java.util.ArrayList;
import ast.*;

public class Lugosi implements LugosiConstants {

    public static void main(String args[]) throws Exception {
        try {
            FileInputStream fs = new FileInputStream(new File(args[0]));
            Lugosi parser = new Lugosi(fs);
            Prog arvore = parser.Lugosi();
            System.out.println("Parsing completed successfully!");
            geraCodigo(arvore, args[0]);
        } catch (ParseException e) {
            System.out.println("Parsing error: " + e.getMessage());
        }
    }

    public static void geraCodigo(Prog prog, String arquivo) {
        System.out.println("AST: " + prog);
    }

  static final public Prog Lugosi() throws ParseException {
  Main main; ArrayList<Fun> funs = new ArrayList<Fun>(); Fun fun;
    main = Main();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DEF:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      fun = Funcao();
                                funs.add(fun);
    }
      {if (true) return new Prog(main, funs);}
    throw new Error("Missing return statement in function");
  }

  static final public Main Main() throws ParseException {
    ArrayList<VarDecl> vars = new ArrayList<VarDecl>();
    ArrayList<Comando> coms = new ArrayList<Comando>();
    jj_consume_token(VOID);
    jj_consume_token(MAIN);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case APAREN:
      jj_consume_token(APAREN);
      jj_consume_token(FPAREN);
      break;
    default:
      jj_la1[1] = jj_gen;
      ;
    }
    jj_consume_token(ACHAVES);
    vars = VarDecl();
    coms = SeqComandos();
    jj_consume_token(FCHAVES);
      {if (true) return new Main(vars, coms);}
    throw new Error("Missing return statement in function");
  }

  static final public ArrayList<VarDecl> VarDecl() throws ParseException {
  ArrayList<VarDecl> vars = new ArrayList<VarDecl>(); String tipo; Token id;
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LET:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_2;
      }
      jj_consume_token(LET);
      tipo = Tipo();
      id = jj_consume_token(ID);
      jj_consume_token(PONTOVIRGULA);
                                                 vars.add(new VarDecl(tipo, id.image));
    }
      {if (true) return vars;}
    throw new Error("Missing return statement in function");
  }

  static final public String Tipo() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case FLOAT:
      jj_consume_token(FLOAT);
              {if (true) return "float";}
      break;
    case BOOL:
      jj_consume_token(BOOL);
             {if (true) return "bool";}
      break;
    case VOID:
      jj_consume_token(VOID);
             {if (true) return "void";}
      break;
    case INT:
      jj_consume_token(INT);
            {if (true) return "int";}
      break;
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public ArrayList<Comando> SeqComandos() throws ParseException {
  ArrayList<Comando> comandos = new ArrayList<Comando>(); Comando cmd;
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case RETURN:
      case PRINTIO:
      case IF:
      case WHILE:
      case ID:
        ;
        break;
      default:
        jj_la1[4] = jj_gen;
        break label_3;
      }
      cmd = Comando();
                     comandos.add(cmd);
    }
      {if (true) return comandos;}
    throw new Error("Missing return statement in function");
  }

  static final public Comando Comando() throws ParseException {
  Exp exp; Token id; ArrayList<Comando> bloco = new ArrayList<Comando>();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ID:
      id = jj_consume_token(ID);
      exp = ComandoTokenID();
                                    {if (true) return new CAtribuicao(id.beginLine, id.image, exp);}
      break;
    case IF:
      jj_consume_token(IF);
      exp = Expressao();
      jj_consume_token(ACHAVES);
      bloco = SeqComandos();
      jj_consume_token(FCHAVES);
      jj_consume_token(PONTOVIRGULA);
                                                                                   {if (true) return new CIf(getToken(1).beginLine, exp, bloco);}
      break;
    case WHILE:
      jj_consume_token(WHILE);
      exp = Expressao();
      jj_consume_token(DO);
      jj_consume_token(ACHAVES);
      bloco = SeqComandos();
      jj_consume_token(FCHAVES);
      jj_consume_token(PONTOVIRGULA);
                                                                                           {if (true) return new CWhile(getToken(1).beginLine, exp, bloco);}
      break;
    case RETURN:
      jj_consume_token(RETURN);
      exp = Expressao();
      jj_consume_token(PONTOVIRGULA);
                                               {if (true) return new CReturn(getToken(1).beginLine, exp);}
      break;
    case PRINTIO:
      jj_consume_token(PRINTIO);
      exp = Expressao();
      jj_consume_token(PONTOVIRGULA);
                                                {if (true) return new CPrint(getToken(1).beginLine, exp);}
      break;
    default:
      jj_la1[5] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public Exp ComandoTokenID() throws ParseException {
  Exp exp = null; ArrayList<Exp> args = new ArrayList<Exp>();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ATRIBUICAO:
      jj_consume_token(ATRIBUICAO);
      exp = Expressao();
      jj_consume_token(PONTOVIRGULA);
                                                  {if (true) return exp;}
      break;
    case APAREN:
      jj_consume_token(APAREN);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case TRUE:
      case FALSE:
      case APAREN:
      case NUM:
      case ID:
        args = ListaExpressao();
        break;
      default:
        jj_la1[6] = jj_gen;
        ;
      }
      jj_consume_token(FPAREN);
      jj_consume_token(PONTOVIRGULA);
                                                                {if (true) return null;}
      break;
    default:
      jj_la1[7] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public Exp Expressao() throws ParseException {
  Exp arg1, arg2; String op;
    arg1 = Termo();
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
      case MINUS:
      case MULT:
      case DIV:
      case AND:
      case OR:
      case LT:
      case GT:
      case EQ:
        ;
        break;
      default:
        jj_la1[8] = jj_gen;
        break label_4;
      }
      op = OP();
      arg2 = Termo();
                                         {if (true) return new EOpExp(op, arg1, arg2);}
    }
      {if (true) return arg1;}
    throw new Error("Missing return statement in function");
  }

  static final public String OP() throws ParseException {
  Token token;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PLUS:
      token = jj_consume_token(PLUS);
      break;
    case MINUS:
      token = jj_consume_token(MINUS);
      break;
    case MULT:
      token = jj_consume_token(MULT);
      break;
    case DIV:
      token = jj_consume_token(DIV);
      break;
    case AND:
      token = jj_consume_token(AND);
      break;
    case OR:
      token = jj_consume_token(OR);
      break;
    case LT:
      token = jj_consume_token(LT);
      break;
    case GT:
      token = jj_consume_token(GT);
      break;
    case EQ:
      token = jj_consume_token(EQ);
      break;
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
      {if (true) return token.image;}
    throw new Error("Missing return statement in function");
  }

  static final public Exp Termo() throws ParseException {
  Exp exp;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case APAREN:
      jj_consume_token(APAREN);
      exp = Expressao();
      jj_consume_token(FPAREN);
                                        {if (true) return exp;}
      break;
    case TRUE:
    case FALSE:
    case NUM:
    case ID:
      exp = Fator();
                  {if (true) return exp;}
      break;
    default:
      jj_la1[10] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public Exp Fator() throws ParseException {
  Token num; Token id;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NUM:
      num = jj_consume_token(NUM);
                {if (true) return new EFloat(Float.parseFloat(num.image));}
      break;
    case ID:
      id = jj_consume_token(ID);
              {if (true) return new EVar(id.image);}
      break;
    case TRUE:
      jj_consume_token(TRUE);
             {if (true) return new ETrue();}
      break;
    case FALSE:
      jj_consume_token(FALSE);
              {if (true) return new EFalse();}
      break;
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public ArrayList<Exp> ListaExpressao() throws ParseException {
  ArrayList<Exp> exps = new ArrayList<Exp>(); Exp exp;
    exp = Expressao();
                      exps.add(exp);
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VIRGULA:
        ;
        break;
      default:
        jj_la1[12] = jj_gen;
        break label_5;
      }
      jj_consume_token(VIRGULA);
      exp = Expressao();
                                 exps.add(exp);
    }
      {if (true) return exps;}
    throw new Error("Missing return statement in function");
  }

  static final public Fun Funcao() throws ParseException {
    Token id;
    String tipo;
    ArrayList<ParamFormalFun> params = new ArrayList<ParamFormalFun>();
    ArrayList<VarDecl> vars = new ArrayList<VarDecl>();
    ArrayList<Comando> body = new ArrayList<Comando>();
    jj_consume_token(DEF);
    tipo = Tipo();
    id = jj_consume_token(ID);
    jj_consume_token(APAREN);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case VOID:
    case INT:
    case FLOAT:
    case BOOL:
      params = ListaArg();
      break;
    default:
      jj_la1[13] = jj_gen;
      ;
    }
    jj_consume_token(FPAREN);
    jj_consume_token(ACHAVES);
    vars = VarDecl();
    body = SeqComandos();
    jj_consume_token(FCHAVES);
      {if (true) return new Fun(id.image, params, tipo, vars, body);}
    throw new Error("Missing return statement in function");
  }

  static final public ArrayList<ParamFormalFun> ListaArg() throws ParseException {
  ArrayList<ParamFormalFun> args = new ArrayList<ParamFormalFun>(); String tipo; Token id;
    tipo = Tipo();
    id = jj_consume_token(ID);
                          args.add(new ParamFormalFun(tipo, id.image));
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VIRGULA:
        ;
        break;
      default:
        jj_la1[14] = jj_gen;
        break label_6;
      }
      jj_consume_token(VIRGULA);
      tipo = Tipo();
      id = jj_consume_token(ID);
                                     args.add(new ParamFormalFun(tipo, id.image));
    }
      {if (true) return args;}
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public LugosiTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[15];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x80000,0x80000000,0x80,0x720,0x36000,0x36000,0x80001800,0x80000000,0x1ff00000,0x1ff00000,0x80001800,0x1800,0x0,0x720,0x0,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x0,0x20,0x20,0x30,0x8,0x0,0x0,0x30,0x30,0x2,0x0,0x2,};
   }

  /** Constructor with InputStream. */
  public Lugosi(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Lugosi(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new LugosiTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Lugosi(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new LugosiTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Lugosi(LugosiTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(LugosiTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List jj_expentries = new java.util.ArrayList();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[38];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 15; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 38; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
