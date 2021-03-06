package lang;

import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

import beaver.Parser.Exception;

import lang.ast.Program;
import lang.ast.LangParser;
import lang.ast.LangScanner;
import lang.ast.IdDecl;
import java.util.Set;
import lang.ast.ErrorMessage;
/**
 * Dumps the parsed Abstract Syntax Tree of a Calc program.
 */
public class UnusedAnalysis {
	/**
	 * Entry point
	 * @param args
	 */
    
    public static Object DrAST_root_node; //Enable debugging with DrAST
    
	public static void main(String[] args) {
		try {
			if (args.length != 1) {
				System.err.println(
						"You must specify a source file on the command line!");
				printUsage();
				System.exit(1);
				return;
			}

			String filename = args[0];
			LangScanner scanner = new LangScanner(new FileReader(filename));
			LangParser parser = new LangParser();
			Program program = (Program) parser.parse(scanner);
            		DrAST_root_node = program; //Enable debugging with DrAST
			System.out.println(program.dumpTree());
			Set<ErrorMessage> errors = program.errors();
			if(errors.size() == 0) { //no error detected in analysis continue
				Set<IdDecl> unused = program.unusedVars();
				System.out.println("---Unused vars---");
				for(IdDecl decl : unused)
					System.out.println(decl.getID());
			} else {
				System.out.println("*************************************");
				System.out.println("  Interpreter halt, semantic error!  ");
				System.out.println("*************************************");
				for(ErrorMessage errorMsg : errors)
					System.out.println(errorMsg);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace(System.err);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void printUsage() {
		System.err.println("Usage: Interpreter FILE");
		System.err.println("  where FILE is the file to be interpeterd");
	}
}

