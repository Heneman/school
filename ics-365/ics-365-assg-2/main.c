/**
 * Grammar from example 3.4
 *
 * <assign> -> <id> = <expr>
 *   <id>   -> A | B | C
 *   <expr> -> <expr> + <term>
 *           | <term>
 *   <term> -> <term> * <factor>
 *           | <factor>
 *   <factor> -> ( <expr> )
 *             | <id>
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// `int` type and values for `true` and `false`
typedef int boolean;
#define true 1
#define false 0

// Constant for input line size.  Value taken from `c_sample.c` memory allocation
enum {MEM_SIZE = 200};

// Global input holder and value for index
char *input;
int i = 0;


/**
 * Processes non-terminal to ensure that it complies with the grammar for <expr>
 */
boolean expr() ;

/*
 * Gets user input from command line and returns char * containing
 */
char * getInput() {
  // Allocate a 200 bit memory block to store user input
  input = malloc(MEM_SIZE);
  // Get user input from stdin, unsure it's no longer than MEM_SIZE chars in length
  fgets(input, MEM_SIZE, stdin);
  // Return user input
  return input;
}


/*
 * Taken from http://stackoverflow.com/questions/1726302/removing-spaces-from-a-string-in-c
 *
 * Copies `source` values into two char pointers, and loops through one, assigning the next value of the first string
 * to the current value of the second if the char is not equal to ' ', '\n', or '/r', removing any whitespace chars.
 * How this works is something that I'm still trying to wrap my head around
 */
void stripWhiteSpace(char *source) {
  char *i = source;
  char *j = source;

  while(*j != 0) {
    *i = *j++;
    if((*i != ' ') && (*i != '\n') && (*i != '\r')) {
      i++;
    }
  }

  *i = 0;
}


/*
 * Returns the char at index `i` of `input`
 */
char peek(int i) {
  return input[i];
}


/*
 * Tests whether a given char is an Identifier of the grammar from 3.2
 */
boolean isId(int i) {
  switch(peek(i)) {
    case 'A':
    case 'B':
    case 'C':
      return true;
    default:
      return false;
  }
}


/*
 * Tests whether the char at index `i` of `input` is an equals sign ('=')
 */
boolean isEquals(int i) {
  return peek(i) == '=';
}


/*
 * Tests whether the char at index `i` of `input` is a plus sign ('+')
 */
boolean isPlus(int i) {
  return peek(i) == '+';
}


/*
 * Tests whether the char at index `i` of `input` is a multiplication sign ('*')
 */
boolean isMultiply(int i) {
  return peek(i) == '*';
}


/*
 * Tests whether the char at index `i` of `input` is a left parenthesis ('(')
 */
boolean isLeftParen(int i) {
  return peek(i) == '(';
}


/*
 * Tests whether the char at index `i` of `input` is a right parenthesis (')')
 */
boolean isRightParen(int i) {
  return peek(i) == ')';
}


/*
 * Increments index `i` to progress through the expression lexemes
 */
void consume() {
  printf("Consuming lexeme: %c\n", input[i++]);
}


/**
 * Processes non-terminal to ensure that it complies with the grammar for <factor>
 */
boolean factor() {
  // If lexeme conforms to <id> grammar
  if(isId(i)) {
    // Accept it
    consume();
    return true;
  }
  // If lexeme is an opening parenthesis ('(')
  else if(isLeftParen(i)) {
    // Accept it
    consume();
    // If parenthesis contains a valid <expr>
    if(expr()) {
      // If the valid <expr> is followed by a closing parenthesis (')')
      if(isRightParen(i)) {
        // Accept it
        consume();
        return true;
      }
    }
  }

  // Otherwise a lexeme that doesn't conform to <factor> was found
  return false;
}

/**
 * Processes non-terminal to ensure that it complies with the grammar for <term>
 */
boolean term() {
  // If lexeme conforms to <factor> grammar
  if(factor()) {
    // And if lexeme is a multiplication symbol ('*')
    if(isMultiply(i)) {
      // Accept it
      consume();
      // Return whether following lexeme conforms to <term> grammar
      return term();
    }
    // Otherwise lexeme is a valid <term>
    else {
      return true;
    }
  }

  // Otherwise a lexeme that doesn't conform to <term> was found
  return false;
}

/**
 * Processes non-terminal to ensure that it complies with the grammar for <expr>
 */
boolean expr() {
  // If lexeme conforms to <term> grammar
  if(term()) {
    // And if lexeme is an addition symbol ('+')
    if(isPlus(i)) {
      // Accept it
      consume();
      // Return whether following lexeme conforms to <expr> grammar
      return expr();
    }
    // Otherwise lexeme is a valid <expr>
    else {
      return true;
    }
  }

  // Otherwise a lexeme that doesn't conform to <expr> was found
  return false;
}


/*
 * Processes expression to ensure that it starts with an <id>, followed by an '=', then an <expr>
 */
boolean assign() {
  // Ensure that an <id> is the the first token of <assign>
  if(!isId(i)) {
    // If not, print error message and exit
    printf("First lexeme is not an <id>.  Exiting...\n");
    return false;
  }

  // Consume assignment <id>
  consume();

  // Ensure second token of <assign> is an assignment operator
  if(!isEquals(i)) {
    // If not, print error message and exit
    printf("Next lexeme is not an '='.  Exiting...\n");
    return false;
  }

  // Consume assignment operator
  consume();

  // Ensure that next token complies with <expr> grammar
  if(!expr()) {
    // Expression didn't conform to grammar
    return false;
  }

  // Ensure that whole string was parsed, and exit if not
  if(i != strlen(input)) {
    printf("Didn't consume all tokens, ¯\\_(ツ)_/¯\n");
    return false;
  }

  // Expression matches grammar of <assign>
  return true;
}


/*
 * Parses lexemes of `input`
 */
void parse() {
  // Ensure that expression conforms to grammar of <assign>
  if(assign()) {
    // Show success message and exit
    printf("Statement was accepted by the grammar!\n");
    exit(0);
  }
  else {
    // Show failure message and exit
    printf("Statement didn't conform to the grammar!  Failed to parse.\n");
    exit(1);
  }
}


/*
 * Main function
 */
int main(int argc, const char * argv[]) {
  // Request user input
  printf("Enter statement to parse:\n");

  // Puts user input into char array
  char *string = getInput();

  // Removes white spaces from inputted string
  stripWhiteSpace(string);

  // Parse input
  parse();
}