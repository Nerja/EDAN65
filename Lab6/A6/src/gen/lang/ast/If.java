/* This file was generated with JastAdd2 (http://jastadd.org) version 2.2.2 */
package lang.ast;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.HashSet;
/**
 * @ast node
 * @declaredat /home/marcus/git/EDAN65/Lab6/A6/src/jastadd/lang.ast:16
 * @production If : {@link Stmt} ::= <span class="component">{@link Expr}</span> <span class="component">Do:{@link StmtBlock}</span> <span class="component">[Else:{@link StmtBlock}]</span>;

 */
public class If extends Stmt implements Cloneable {
  /**
   * @aspect CodeGen
   * @declaredat /home/marcus/git/EDAN65/Lab6/A6/src/jastadd/CodeGen.jrag:64
   */
  public void genCode(PrintStream out) {
		String elseLbl = uniqueName() + "else";
		String doLbl = uniqueName() + "then";
		String fiLbl = uniqueName() + "fi";
		getExpr().genCode(out); //generate cmp
		getExpr().genConditionalJump(out, elseLbl); //jump to elselbl
		out.println(doLbl + ":"); //dolbl
		getDo().genCode(out); //gen code for do block
		out.println("	jmp " + fiLbl); //jump to fi
		out.println(elseLbl + ":");	
		if(hasElse()) //gencode for elseblock if it exists
			getElse().genCode(out);
		out.println(fiLbl + ":");
		
	}
  /**
   * @aspect Interp
   * @declaredat /home/marcus/git/EDAN65/Lab6/A6/src/jastadd/Interp.jrag:61
   */
  public void eval(ActivationRecord actRec) throws ReturnException {
		if(getExpr().eval(actRec) == 1)
			getDo().eval(actRec);
		else if(hasElse())
			getElse().eval(actRec);
	}
  /**
   * @declaredat ASTNode:1
   */
  public If() {
    super();
  }
  /**
   * Initializes the child array to the correct size.
   * Initializes List and Opt nta children.
   * @apilevel internal
   * @ast method
   * @declaredat ASTNode:10
   */
  public void init$Children() {
    children = new ASTNode[3];
    setChild(new Opt(), 2);
  }
  /**
   * @declaredat ASTNode:14
   */
  public If(Expr p0, StmtBlock p1, Opt<StmtBlock> p2) {
    setChild(p0, 0);
    setChild(p1, 1);
    setChild(p2, 2);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:20
   */
  protected int numChildren() {
    return 3;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:24
   */
  public void flushAttrCache() {
    super.flushAttrCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:28
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:32
   */
  public If clone() throws CloneNotSupportedException {
    If node = (If) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:37
   */
  public If copy() {
    try {
      If node = (If) clone();
      node.parent = null;
      if (children != null) {
        node.children = (ASTNode[]) children.clone();
      }
      return node;
    } catch (CloneNotSupportedException e) {
      throw new Error("Error: clone not supported for " + getClass().getName());
    }
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @deprecated Please use treeCopy or treeCopyNoTransform instead
   * @declaredat ASTNode:56
   */
  @Deprecated
  public If fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:66
   */
  public If treeCopyNoTransform() {
    If tree = (If) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        ASTNode child = (ASTNode) children[i];
        if (child != null) {
          child = child.treeCopyNoTransform();
          tree.setChild(child, i);
        }
      }
    }
    return tree;
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The subtree of this node is traversed to trigger rewrites before copy.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:86
   */
  public If treeCopy() {
    If tree = (If) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        ASTNode child = (ASTNode) getChild(i);
        if (child != null) {
          child = child.treeCopy();
          tree.setChild(child, i);
        }
      }
    }
    return tree;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:100
   */
  protected boolean is$Equal(ASTNode node) {
    return super.is$Equal(node);    
  }
  /**
   * Replaces the Expr child.
   * @param node The new node to replace the Expr child.
   * @apilevel high-level
   */
  public void setExpr(Expr node) {
    setChild(node, 0);
  }
  /**
   * Retrieves the Expr child.
   * @return The current node used as the Expr child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="Expr")
  public Expr getExpr() {
    return (Expr) getChild(0);
  }
  /**
   * Retrieves the Expr child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the Expr child.
   * @apilevel low-level
   */
  public Expr getExprNoTransform() {
    return (Expr) getChildNoTransform(0);
  }
  /**
   * Replaces the Do child.
   * @param node The new node to replace the Do child.
   * @apilevel high-level
   */
  public void setDo(StmtBlock node) {
    setChild(node, 1);
  }
  /**
   * Retrieves the Do child.
   * @return The current node used as the Do child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="Do")
  public StmtBlock getDo() {
    return (StmtBlock) getChild(1);
  }
  /**
   * Retrieves the Do child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the Do child.
   * @apilevel low-level
   */
  public StmtBlock getDoNoTransform() {
    return (StmtBlock) getChildNoTransform(1);
  }
  /**
   * Replaces the optional node for the Else child. This is the <code>Opt</code>
   * node containing the child Else, not the actual child!
   * @param opt The new node to be used as the optional node for the Else child.
   * @apilevel low-level
   */
  public void setElseOpt(Opt<StmtBlock> opt) {
    setChild(opt, 2);
  }
  /**
   * Replaces the (optional) Else child.
   * @param node The new node to be used as the Else child.
   * @apilevel high-level
   */
  public void setElse(StmtBlock node) {
    getElseOpt().setChild(node, 0);
  }
  /**
   * Check whether the optional Else child exists.
   * @return {@code true} if the optional Else child exists, {@code false} if it does not.
   * @apilevel high-level
   */
  public boolean hasElse() {
    return getElseOpt().getNumChild() != 0;
  }
  /**
   * Retrieves the (optional) Else child.
   * @return The Else child, if it exists. Returns {@code null} otherwise.
   * @apilevel low-level
   */
  public StmtBlock getElse() {
    return (StmtBlock) getElseOpt().getChild(0);
  }
  /**
   * Retrieves the optional node for the Else child. This is the <code>Opt</code> node containing the child Else, not the actual child!
   * @return The optional node for child the Else child.
   * @apilevel low-level
   */
  @ASTNodeAnnotation.OptChild(name="Else")
  public Opt<StmtBlock> getElseOpt() {
    return (Opt<StmtBlock>) getChild(2);
  }
  /**
   * Retrieves the optional node for child Else. This is the <code>Opt</code> node containing the child Else, not the actual child!
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The optional node for child Else.
   * @apilevel low-level
   */
  public Opt<StmtBlock> getElseOptNoTransform() {
    return (Opt<StmtBlock>) getChildNoTransform(2);
  }
/** @apilevel internal */
protected boolean uniqueName_visited = false;
  /**
   * @attribute syn
   * @aspect UniqueNamesForStatements
   * @declaredat /home/marcus/git/EDAN65/Lab6/A6/src/jastadd/UniqueNamesForStatements.jrag:8
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="UniqueNamesForStatements", declaredAt="/home/marcus/git/EDAN65/Lab6/A6/src/jastadd/UniqueNamesForStatements.jrag:8")
  public String uniqueName() {
    if (uniqueName_visited) {
      throw new RuntimeException("Circular definition of attribute If.uniqueName().");
    }
    uniqueName_visited = true;
    String uniqueName_value = uniqueNamePrefix() + "if_";
    uniqueName_visited = false;
    return uniqueName_value;
  }
  /**
   * @attribute inh
   * @aspect UniqueNamesForStatements
   * @declaredat /home/marcus/git/EDAN65/Lab6/A6/src/jastadd/UniqueNamesForStatements.jrag:4
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="UniqueNamesForStatements", declaredAt="/home/marcus/git/EDAN65/Lab6/A6/src/jastadd/UniqueNamesForStatements.jrag:4")
  public String uniqueNamePrefix() {
    if (uniqueNamePrefix_visited) {
      throw new RuntimeException("Circular definition of attribute If.uniqueNamePrefix().");
    }
    uniqueNamePrefix_visited = true;
    String uniqueNamePrefix_value = getParent().Define_uniqueNamePrefix(this, null);
    uniqueNamePrefix_visited = false;
    return uniqueNamePrefix_value;
  }
/** @apilevel internal */
protected boolean uniqueNamePrefix_visited = false;
  /**
   * @declaredat /home/marcus/git/EDAN65/Lab6/A6/src/jastadd/TypeAnalysis.jrag:34
   * @apilevel internal
   */
  public Type Define_expectedType(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getExprNoTransform()) {
      // @declaredat /home/marcus/git/EDAN65/Lab6/A6/src/jastadd/TypeAnalysis.jrag:40
      return boolType();
    }
    else {
      return getParent().Define_expectedType(this, _callerNode);
    }
  }
  protected boolean canDefine_expectedType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
}
