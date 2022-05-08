package io.anemortalkid.lox;

import java.util.List;

public class LoxFunction implements LoxCallable {

  private final String name;
  private final Stmt.Function declaration;
  private final Environment closure;
  private final boolean isInitializer;

  LoxFunction(Stmt.Function declaration, Environment closure, boolean isInitializer) {
    this.name = declaration.name.lexeme;
    this.closure = closure;
    this.declaration = declaration;
    this.isInitializer = isInitializer;
  }

  @Override
  public String toString() {
    if (name == null) return "<fn>";
    return "<fn " + name + ">";
  }

  @Override
  public Object call(Interpreter interpreter, List<Object> arguments) {
    Environment environment = new Environment(closure);
    for (int i = 0; i < declaration.params.size(); i++) {
      environment.define(declaration.params.get(i).lexeme, arguments.get(i));
    }

    try {
      interpreter.executeBlock(declaration.body, environment);
    } catch (Interpreter.Return returnValue) {
      if (isInitializer) {
        return closure.getAt(0, "this");
      }
      return returnValue.value;
    }

    if (isInitializer) {
      return closure.getAt(0, "this");
    }

    return null;
  }

  @Override
  public int arity() {
    return declaration.params.size();
  }

  LoxFunction bind(LoxInstance loxInstance) {
    Environment environment = new Environment(closure);
    environment.define("this", loxInstance);
    return new LoxFunction(declaration, environment, isInitializer);
  }
}
