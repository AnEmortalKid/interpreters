```textmate
program   → declaration* EOF ;

declaration → classDecl
             | funDecl
             | varDecl
             | statement;
            
block     → "{" declaration* "}" ;

classDecl -> "class" IDENTIFIER "{" function* "}";

varDecl → "var" IDENTIFIER ( "=" expression )? ";" ;

funDecl -> "fun" function ;
function → IDENTIFIER "(" parameters? ")" block ;
parameters → IDENTIFIER ( "," IDENTIFIER )* ;

statement → exprStmt
          | forStmt
          | ifStmt
          | printStmt
          | returnStmt
          | whileStmt
          | block;

returnStmt -> "return" expression? ";" ;

forStmt -> "for" "(" (varDecl | exprStatement | ";" )
                    expression? ";"
                    expression? ")" statement ;

whileStmt -> "while" "(" expression ")" statement ;

exprStmt  → expression ";" ;

printStmt → "print" expression ";" ;

ifStmt -> "if" "(" expression ")" statement ("else" statement)? ;

expression → assignment ;

assignment → (call ".")? IDENTIFIER "=" assignment
            | logic_or ;

logic_or -> logic_and ( "or" logic_and )* ;

logic_and -> equality ( "and" equality )* ;

equality       → comparison ( ( "!=" | "==" ) comparison )* ;

comparison     → addition ( ( ">" | ">=" | "<" | "<=" ) addition )* ;

addition       → multiplication ( ( "-" | "+" ) multiplication )* ;

multiplication → unary ( ( "/" | "*" ) unary )* ;

unary           → ( "!" | "-" ) unary 
                | call ;
call            → primary ( "(" arguments? ")" | "." IDENTIFIER )* ;

arguments → expression ( "," expression )* ;

primary        → NUMBER | STRING | "false" | "true" | "nil"
                 | "(" expression ")"
                 | IDENTIFIER ;
```