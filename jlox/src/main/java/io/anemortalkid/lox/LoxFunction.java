package io.anemortalkid.lox;

import java.util.List;

public class LoxFunction implements LoxCallable {

  private final String name;
  private final Expr.Function declaration;
  private final Environment closure;

  LoxFunction(String name, Expr.Function declaration, Environment closure) {
    this.name = name;
    this.closure = closure;
    this.declaration = declaration;
  }

  @Override
  public String toString() {
    if (name == null) return "<fn>";
    return "<fn " + name + ">";
  }

  @Override
  public Object call(Interpreter interpreter, List<Object> arguments) {
    Environment environment = new Environment(closure);
    for (int i = 0; i < declaration.parameters.size(); i++) {
      environment.define(declaration.parameters.get(i).lexeme, arguments.get(i));
    }

    try {
      interpreter.executeBlock(declaration.body, environment);
    } catch (Interpreter.Return returnValue) {
      return returnValue.value;
    }

    return null;
  }

  @Override
  public int arity() {
    return declaration.parameters.size();
  }
}
