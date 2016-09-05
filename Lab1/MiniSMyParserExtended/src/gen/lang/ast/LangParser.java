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
		static public final short NOT = 2;
		static public final short NUMERAL = 3;
		static public final short FOR = 4;
		static public final short ASSIGN = 5;
		static public final short IF = 6;
		static public final short UNTIL = 7;
		static public final short DO = 8;
		static public final short OD = 9;
		static public final short THEN = 10;
		static public final short FI = 11;

		static public final String[] NAMES = {
			"EOF",
			"ID",
			"NOT",
			"NUMERAL",
			"FOR",
			"ASSIGN",
			"IF",
			"UNTIL",
			"DO",
			"OD",
			"THEN",
			"FI"
		};
	}

	static final ParsingTables PARSING_TABLES = new ParsingTables(
		"U9nTKMjFma0Gd0CgpzeMoqiX4eq9uQ69VEFN#PjLdYBhtDqMcxjcTlQcqzlj7e0DAgYYXaX" +
		"4lX3x75tb74qCSOCn4WmmmeF676sdrwzwZjGfhS3PftfFDD317MxHaszav8J#372QpmisTC" +
		"QiRdYQSTqLqUlTakwLUk2qgaN4gct#GSIdnLgJ3xWL4pMR5#2olRowVe0fqIUMn8XOgUUHU" +
		"2EUYBbgxyGiE6jHojUMETlFiaVVTPIhoebw3oNFKBLpyErpi7y9zXDFSf5VEIDOydFDJYuU" +
		"vI1xyawslZFEmV16$f5mZYddcBdv6aM5ipIy4OFy3oU7FfC=");

	static final Action RETURN9 = new Action() {
		public Symbol reduce(Symbol[] _symbols, int offset) {
			return _symbols[offset + 9];
		}
	};

	static final Action RETURN5 = new Action() {
		public Symbol reduce(Symbol[] _symbols, int offset) {
			return _symbols[offset + 5];
		}
	};

	static final Action RETURN3 = new Action() {
		public Symbol reduce(Symbol[] _symbols, int offset) {
			return _symbols[offset + 3];
		}
	};

	static final Action RETURN2 = new Action() {
		public Symbol reduce(Symbol[] _symbols, int offset) {
			return _symbols[offset + 2];
		}
	};

	static public class SyntaxError extends RuntimeException { public SyntaxError(String msg) {super(msg);}}
	// Disable syntax error recovery
	protected void recoverFromError(Symbol token, TokenStream in) {
		throw new SyntaxError("Cannot recover from the syntax error");
	}

	private final Action[] actions;

	public LangParser() {
		super(PARSING_TABLES);
		actions = new Action[] {
			Action.RETURN,	// [0] program = stmt
			Action.RETURN,	// [1] stmt = forStmt
			Action.RETURN,	// [2] stmt = ifStmt
			Action.RETURN,	// [3] stmt = assignment
			RETURN9,	// [4] forStmt = FOR id ASSIGN expr UNTIL expr DO stmt OD; returns 'OD' although none is marked
			RETURN5,	// [5] ifStmt = IF expr THEN stmt FI; returns 'FI' although none is marked
			RETURN3,	// [6] assignment = ID ASSIGN expr; returns 'expr' although none is marked
			Action.RETURN,	// [7] expr = ID
			Action.RETURN,	// [8] expr = numeral
			RETURN2,	// [9] expr = NOT expr; returns 'expr' although none is marked
			Action.RETURN,	// [10] numeral = NUMERAL
			Action.RETURN	// [11] id = ID
		};
	}

	protected Symbol invokeReduceAction(int rule_num, int offset) {
		return actions[rule_num].reduce(_symbols, offset);
	}
}
