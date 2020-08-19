program   → declaration* EOF ;

declaration → varDecl
            | statement;
            
block     → "{" declaration* "}" ;

varDecl → "var" IDENTIFIER ( "=" expression )? ";" ;

statement → exprStmt
          | forStmt
          | ifStmt
          | printStmt
          | whileStmt
          | block;

forStmt -> "for" "(" (varDecl | exprStatement | ";" )
                    expression? ";"
                    expression? ")" statement ;

whileStmt -> "while" "(" expression ")" statement ;

exprStmt  → expression ";" ;

printStmt → "print" expression ";" ;

ifStmt -> "if" "(" expression ")" statement ("else" statement)? ;

expression → assignment ;

assignment → IDENTIFIER "=" assignment
            | logic_or ;

logic_or -> logic_and ( "or" logic_and )* ;

logic_and -> equality ( "and" equality )* ;

equality       → comparison ( ( "!=" | "==" ) comparison )* ;

comparison     → addition ( ( ">" | ">=" | "<" | "<=" ) addition )* ;

addition       → multiplication ( ( "-" | "+" ) multiplication )* ;

multiplication → unary ( ( "/" | "*" ) unary )* ;

unary          → ( "!" | "-" ) unary
                 | primary ;

primary        → NUMBER | STRING | "false" | "true" | "nil"
                 | "(" expression ")"
                 | IDENTIFIER ;