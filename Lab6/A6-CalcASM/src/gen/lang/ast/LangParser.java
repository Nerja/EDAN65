package lang.ast;

import beaver.*;
import java.util.ArrayList;

/**
 * This class is a LALR parser generated by
 * <a href="http://beaver.sourceforge.net">Beaver</a> v0.9.6.1
 * from the grammar specification "parser.beaver".
 */
public class LangParser extends Parser {
	static public class Terminals {
		static public final short EOF = 0;
		static public final short ID = 1;
		static public final short LET = 2;
		static public final short ASK = 3;
		static public final short NUMERAL = 4;
		static public final short MUL = 5;
		static public final short DIV = 6;
		static public final short ASSIGN = 7;
		static public final short IN = 8;
		static public final short END = 9;
		static public final short USER = 10;

		static public final String[] NAMES = {
			"EOF",
			"ID",
			"LET",
			"ASK",
			"NUMERAL",
			"MUL",
			"DIV",
			"ASSIGN",
			"IN",
			"END",
			"USER"
		};
	}

	static final ParsingTables PARSING_TABLES = new ParsingTables(
		"U9o5aKjFma0KXKyjAFHbeV8eB5omS67KnEZ261Si#5$#OmKK1H#7CvEXDarDR$BrdlkOtDi" +
		"P00kSeOrZdA238qmmHC2NKYugwJ5QXKuArKSNf#WrUH8ZvF0HMp808GQQPw8SAPUA8#b4tQ" +
		"azbsbsELUl$vIdF$2rOvE7zlRi$eQkDklOUIRo4AWAJ9zXxLpSYPc9gyZcubvSY5jnwV8FT" +
		"VskTYrknADuEkH9lhjwM7VErEMkvEdwqlzcLEQrA$4KplZ3B#wuvISAuxVOSCLNVl2NRpQt" +
		"5cPwgXVnz3Nubb2wJAHRZ7NFcNGctrDF8jqNveK6UxNlIZG=");

	static public class SyntaxError extends RuntimeException { public SyntaxError(String msg) {super(msg);}}
	// Disable syntax error recovery
	protected void recoverFromError(Symbol token, TokenStream in) {
		throw new SyntaxError("Cannot recover from the syntax error");
	}

	private final Action[] actions;

	public LangParser() {
		super(PARSING_TABLES);
		actions = new Action[] {
			new Action() {	// [0] program = exp.a
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 1];
					final Expr a = (Expr) _symbol_a.value;
					 return new Program(a);
				}
			},
			Action.RETURN,	// [1] exp = factor
			new Action() {	// [2] exp = exp.a MUL factor.b
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 1];
					final Expr a = (Expr) _symbol_a.value;
					final Symbol _symbol_b = _symbols[offset + 3];
					final Expr b = (Expr) _symbol_b.value;
					 return new Mul(a, b);
				}
			},
			new Action() {	// [3] exp = exp.a DIV factor.b
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 1];
					final Expr a = (Expr) _symbol_a.value;
					final Symbol _symbol_b = _symbols[offset + 3];
					final Expr b = (Expr) _symbol_b.value;
					 return new Div(a, b);
				}
			},
			Action.RETURN,	// [4] factor = let
			Action.RETURN,	// [5] factor = ask
			Action.RETURN,	// [6] factor = numeral
			Action.RETURN,	// [7] factor = id_use
			new Action() {	// [8] let = LET binding_list.a IN exp.b END
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 2];
					final List a = (List) _symbol_a.value;
					final Symbol _symbol_b = _symbols[offset + 4];
					final Expr b = (Expr) _symbol_b.value;
					 return new Let(a, b);
				}
			},
			new Action() {	// [9] ask = ASK USER
				public Symbol reduce(Symbol[] _symbols, int offset) {
					 return new Ask();
				}
			},
			new Action() {	// [10] binding_list = binding.a
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 1];
					final Binding a = (Binding) _symbol_a.value;
					 return new List().add(a);
				}
			},
			new Action() {	// [11] binding_list = binding_list.a binding.b
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 1];
					final List a = (List) _symbol_a.value;
					final Symbol _symbol_b = _symbols[offset + 2];
					final Binding b = (Binding) _symbol_b.value;
					 return a.add(b);
				}
			},
			new Action() {	// [12] binding = id_decl.a ASSIGN exp.b
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 1];
					final IdDecl a = (IdDecl) _symbol_a.value;
					final Symbol _symbol_b = _symbols[offset + 3];
					final Expr b = (Expr) _symbol_b.value;
					 return new Binding(a, b);
				}
			},
			new Action() {	// [13] numeral = NUMERAL.num
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol num = _symbols[offset + 1];
					 return new Numeral(num);
				}
			},
			new Action() {	// [14] id_decl = ID.id
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol id = _symbols[offset + 1];
					 return new IdDecl(id);
				}
			},
			new Action() {	// [15] id_use = ID.id
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol id = _symbols[offset + 1];
					 return new IdUse(id);
				}
			}
		};
	}

	protected Symbol invokeReduceAction(int rule_num, int offset) {
		return actions[rule_num].reduce(_symbols, offset);
	}
}
