; Kyle Heneman
; ICS 365-01
; Spring 2016

#lang racket

; Constant for maximum number of wrong guesses
(define MAX_WRONG 10)

; Constants for each row of gallows
(define CROSS_BEAM "   ________  \n")
(define ROPE "   |      l  \n")
(define HEAD "   |      O  \n")
(define TORSO "   |      |  \n")
(define LEFT_ARM "   |     /|  \n")
(define LEFT_HAND "   |    ,/|\\ \n")
(define RIGHT_ARM "   |     /|\\ \n")
(define RIGHT_HAND "   |    ,/|\\,\n")
(define LEFT_LEG "   |     /   \n")
(define LEFT_FOOT "   |    _/ \\ \n")
(define RIGHT_LEG "   |     / \\ \n")
(define RIGHT_FOOT "   |    _/ \\_\n")
(define POST "   |         \n")
(define BASE "___|___      \n\n")


; Lists of each state of gallows (no wrong guesses, one wrong guess, etc)
; An empty gallows for no wrong guesses
(define NONE_WRONG (list CROSS_BEAM ROPE ROPE POST POST POST POST BASE))
; A gallows with a head for one wrong guess
(define ONE_WRONG (list CROSS_BEAM ROPE ROPE HEAD POST POST POST BASE))
; A gallows with a head and torso for two wrong guesses
(define TWO_WRONG (list CROSS_BEAM ROPE ROPE HEAD TORSO POST POST BASE))
; A gallows with a head, torso, and left arm for three wrong guesses
(define THREE_WRONG (list CROSS_BEAM ROPE ROPE HEAD LEFT_ARM POST POST BASE))
; A gallows with a head, torso, left arm, and right arm for four wrong guesses
(define FOUR_WRONG (list CROSS_BEAM ROPE ROPE HEAD RIGHT_ARM POST POST BASE))
; A gallows with a head, torso, left arm, right arm, and left leg for five wrong guesses
(define FIVE_WRONG (list CROSS_BEAM ROPE ROPE HEAD RIGHT_ARM LEFT_LEG POST BASE))
; A gallows with a head, torso, left arm, right arm, left leg, and right leg for six wrong guesses
(define SIX_WRONG (list CROSS_BEAM ROPE ROPE HEAD RIGHT_ARM RIGHT_LEG POST BASE))
; A gallows with a head, torso, left hand, right arm, left leg, and right leg for seven wrong guesses
(define SEVEN_WRONG (list CROSS_BEAM ROPE ROPE HEAD LEFT_HAND RIGHT_LEG POST BASE))
; A gallows for eight wrong guesses
(define EIGHT_WRONG (list CROSS_BEAM ROPE ROPE HEAD RIGHT_HAND RIGHT_LEG POST BASE))
; A gallows for nine wrong guesses
(define NINE_WRONG (list CROSS_BEAM ROPE ROPE HEAD RIGHT_HAND LEFT_FOOT POST BASE))
; A gallows for ten wrong guesses
(define TEN_WRONG (list CROSS_BEAM ROPE ROPE HEAD RIGHT_HAND RIGHT_FOOT POST BASE))


; Lists of characters for words to guess
(define WORD_LIST '((#\r #\a #\d #\i #\a #\t #\e) (#\d #\i #\s #\p #\e #\n #\s #\a #\b #\l #\e)
                    (#\r #\a #\m #\p #\a #\n #\t) (#\c #\o #\a #\l) (#\m #\a #\r #\v #\e #\l #\o #\u #\s)
                    (#\s #\e #\l #\f) (#\t #\o #\r #\p #\i #\d) (#\w #\i #\l #\d) (#\a #\w #\a #\k #\e)
                    (#\c #\o #\i #\l) (#\p #\i #\n #\k) (#\i #\n #\v #\e #\n #\t) (#\d #\o #\l #\l #\s)
                    (#\z #\o #\o) (#\w #\h #\i #\t #\e) (#\w #\a #\r #\l #\i #\k #\e) (#\h #\a #\m #\m #\e #\r)
                    (#\w #\o #\r #\d) (#\u #\n #\i #\q #\u #\e) (#\g #\u #\i #\t #\a #\r) (#\c #\a #\v #\e)
                    (#\k #\i #\n #\d #\h #\e #\a #\r #\t #\e #\d) (#\b #\r #\a #\n #\c #\h)
                    (#\e #\x #\c #\h #\a #\n #\g #\e) (#\t #\r #\a #\i #\l) (#\c #\e #\r #\t #\a #\i #\n)
                    (#\s #\o #\n #\g) (#\p #\u #\n #\i #\s #\h) (#\t #\r #\e #\e) (#\y #\e #\l #\l)
                    (#\a #\d #\h #\e #\s #\i #\v #\e) (#\s #\t #\a #\t #\e #\m #\e #\n #\t)
                    (#\c #\o #\n #\d #\i #\t #\i #\o #\n) (#\s #\e #\c #\o #\n #\d)
                    (#\d #\e #\s #\c #\r #\i #\p #\t #\i #\v #\e) (#\t #\r #\a #\n #\q #\u #\i #\l)
                    (#\c #\l #\o #\t #\h) (#\k #\n #\o #\w #\l #\e #\d #\g #\e #\a #\b #\l #\e)
                    (#\l #\i #\m #\i #\t) (#\h #\a #\n #\g) (#\b #\o #\u #\n #\c #\e) (#\d #\o #\c #\t #\o #\r)
                    (#\g #\a #\i #\n #\f #\u #\l) (#\e #\a #\r #\n) (#\v #\o #\l #\c #\a #\n #\o)
                    (#\h #\a #\l #\f) (#\f #\r #\a #\i #\l) (#\c #\o #\m #\p #\a #\r #\i #\s #\o #\n)
                    (#\t #\r #\i #\p) (#\d #\r #\a #\c #\o #\n #\i #\a #\n) (#\s #\p #\u #\r #\i #\o #\u #\s)
                    (#\h #\a #\l #\t #\i #\n #\g) (#\r #\a #\i #\l #\w #\a #\y) (#\r #\i #\g #\h #\t #\f #\u #\l)
                    (#\d #\a #\n #\g #\e #\r #\o #\u #\s) (#\o #\b #\s #\c #\e #\n #\e) (#\m #\u #\t #\e)
                    (#\s #\e #\l #\e #\c #\t #\i #\v #\e) (#\b #\l #\u #\s #\h #\i #\n #\g)
                    (#\g #\r #\u #\e #\s #\o #\m #\e) (#\b #\u #\t #\t #\h #\o #\l #\e) (#\b #\i #\k #\e)
                    (#\c #\o #\l #\o #\s #\s #\a #\l) (#\m #\a #\r #\r #\y) (#\n #\u #\t) (#\z #\o #\o #\m)
                    (#\c #\o #\m #\p #\l #\e #\t #\e) (#\b #\l #\e #\a #\c #\h) (#\m #\e #\n)
                    (#\h #\a #\r #\m #\o #\n #\y) (#\s #\q #\u #\e #\a #\k) (#\s #\w #\i #\t #\c #\h)
                    (#\e #\l #\a #\t #\e #\d) (#\s #\h #\o #\c #\k #\i #\n #\g) (#\b #\a #\s #\e)
                    (#\t #\r #\a #\s #\h #\y) (#\r #\e #\g #\r #\e #\t) (#\r #\e #\j #\o #\i #\c #\e)
                    (#\d #\a #\m #\a #\g #\e #\d) (#\o #\s #\s #\i #\f #\i #\e #\d)
                    (#\g #\i #\r #\a #\f #\f #\e) (#\f #\l #\o #\o #\d) (#\s #\e #\a #\s #\h #\o #\r #\e)
                    (#\w #\r #\e #\s #\t #\l #\e) (#\k #\n #\o #\t) (#\f #\a #\i #\l)
                    (#\n #\o #\i #\s #\e #\l #\e #\s #\s) (#\t #\r #\i #\c #\k) (#\w #\a #\r #\y)
                    (#\s #\w #\i #\f #\t) (#\w #\a #\t #\e #\r #\y) (#\c #\a #\r #\v #\e)
                    (#\d #\i #\z #\z #\y) (#\c #\u #\b) (#\e #\s #\c #\a #\p #\e) (#\n #\e #\c #\k)
                    (#\t #\a #\b #\l #\e) (#\f #\i #\l #\l) (#\e #\m #\p #\l #\o #\y) (#\s #\c #\r #\a #\p #\e)
                   )
)


; Returns an element from WORD_LIST at an index
; chosen randomly from the range of 0 to (list length - 1)
;
(define (get-random-word)
  ; list-ref returns the element from 'ls' at the index
  ; returned from choosing a random number
  ; from 0 to (list length - 1)
  (list-ref WORD_LIST (random (length WORD_LIST)))
)


; Accepts a list of characters and returns a list containing an underscore
; for each character in the original list, separated by spaces
;
; word - A list of characters with which to make
;        a list of underscores of the same length
;
(define (get-blanks word)
  ; Accepts a list of characters and an empty list to build on, adding
  ; an underscore to the empty list for each character in the list
  ; of characters passed in
  ;
  ; word - A list of characters with which to make a
  ;        list of underscores of the same length
  ; blanks - An initially empty list with which to add
  ;          underscores to for each character in original list
  ;
  (define (blank-out word blanks)
    (cond
      ; If the character list is empty,
      ; return list of underscores
      [(null? word) blanks]
      ; Otherwise if last character in list has been reached,
      ; append a single underscore to list of underscores
      [(equal? (length word) 1) (append blanks '(#\_))]
      ; Other-otherwise append an underscore and a space
      ; to list of underscores
      [else (blank-out (cdr word) (append blanks '(#\_ #\ )))]
    )
  )
  ; Call `blank-out` with a character list
  ; and an empty list to build on
  (blank-out word '())
)


; Helper method to display current gallows state
; according to the number of incorrect guesses
;
; wrong-count - Number of incorrect guesses
;               made so far
;
(define (show-gallows wrong-count)
  (case wrong-count
    ; Show gallows for no wrong guesses
    [(0) (display-gallows NONE_WRONG)]
    ; Show gallows for one wrong guess
    [(1) (display-gallows ONE_WRONG)]
    ; Show gallows for two wrong guesses
    [(2) (display-gallows TWO_WRONG)]
    ; Show gallows for three wrong guesses
    [(3) (display-gallows THREE_WRONG)]
    ; Show gallows for four wrong guesses
    [(4) (display-gallows FOUR_WRONG)]
    ; Show gallows for five wrong guesses
    [(5) (display-gallows FIVE_WRONG)]
    ; Show gallows for six wrong guesses
    [(6) (display-gallows SIX_WRONG)]
    ; Show gallows for seven wrong guesses
    [(7) (display-gallows SEVEN_WRONG)]
    ; Show gallows for eight wrong guesses
    [(8) (display-gallows EIGHT_WRONG)]
    ; Show gallows for nine wrong guesses
    [(9) (display-gallows NINE_WRONG)]
    ; Show gallows for ten wrong guesses
    [(10) (display-gallows TEN_WRONG)]
  )
)


; Accepts and displays a list of gallows
; pieces to represent a gallows' state
;
; gallows - A list of gallows pieces that
;           represents gallows' current state
;
(define (display-gallows gallows)
  (cond
    ; If the list of pieces is empty display nothing
    [(null? gallows) (display "")]
    ; Otherwise display the first piece of the gallows
    [else (display (car gallows))
          ; And recurse with the rest of the pieces in gallows' state
          (display-gallows (cdr gallows))
    ]
  )
)


; Displays letters incorrectly guessed
;
; guessed - List of strings of incorrectly guessed letters
;
(define (display-guessed guessed)
  (display (string-append "\n\nGuessed: " (string-join guessed ", ")))
)


; Accepts number of incorrect guesses and
; and blanked out word
;
; wrongs - Count of incorrect guesses made by user
; blanks - List of underscores and possible correct guesses
;
(define (display-state wrongs blanks guessed)
  ; Show the current gallows' state according
  ; to number of incorrect guesses
  (show-gallows wrongs)
  ; Show the current state of correct guesses
  (display (list->string blanks))
  ; Display list of incorrect guesses
  (display-guessed guessed)
)


; Reads line from user, converst string to list,
; and returns first character of what was entered
(define (get-user-char)
  ; Prompt for input
  (display "\n\nEnter a letter to guess: \n")
  ; Get first character of input
  (let ((input (string->list (read-line))))
    (cond
      ; If character is not a lowercase letter
      [(not (char-lower-case? (car input)))
        ; Give error and prompt again
        (display "\nInvalid input!")
        (get-user-char)
      ]
      ; Otherwise return first character of input
      [else (car input)]
    )
  )
)


; Finds index or indices of a character in a given list
; Taken from http://stackoverflow.com/a/5365321/1183256
;
; char - The character to search the string list for
; word - The string list to search for `char` in
;
(define (get-indices char word)
  ; Returns a list of the index or indices
  ; of a character in a list
  ;
  ; rest-of-word - List of remaining characters to inspect
  ; index - List of index or indices character was found at
  ;
  (define (find-indices rest-of-word index)
    ; If rest of list is empty, return an empty list
    (if (null? rest-of-word) '()
      ; Otherwise reach last character of list
      (let ((rest-of-indices (find-indices (cdr rest-of-word) (+ index 1))))
        ; And if it is equal to the character being searched for
        (if (equal? (car rest-of-word) char)
          ; Add the index to a list
          (cons index rest-of-indices)
          ; Otherwise return the list of collected indices
          rest-of-indices)
      )
    )
  )
  ; Find indices starting at index 0
  (find-indices word 0)
)


; Accepts a list of underscores, a character to
; insert into the list of underscores, and
; a list of an index or indices at which to
; insert the character into
;
; blanks - List of underscores
; letter - Character to insert into list
;          of the underscores
; target - List of an index or indices at
;          which to insert character into
;
(define (fill-letter blanks letter target)
  ; Accepts a list of underscores, a character to
  ; insert into the list of underscores, an index
  ; for the current iteration, a list of target indices,
  ; and an empty list on which to build upon
  ;
  ; blanks - List of underscores
  ; letter - Character to insert into list
  ;          of the underscores
  ; current - Current index of the iteration
  ; target - List of an index or indices at
  ;          which to insert character into
  ; build - An empty list with which to build upon
  ; 
  (define (fill-char blanks letter current target build)
    (cond
      ; If list of underscores is now empty,
      ; return the built list
      [(null? blanks) build]
      ; Otherwise if the list of target indices is not empty,
      ; and the current index is equal to (the next index * 2)
      ; (taking separating spaces into account) 
      [(and (not (null? target)) (eqv? current (* (car target) 2)))
        ; Call `fill-char` with the second half of the `blanks` pair,
        ; the character to be inserted, the next index of the list of underscores,
        ; the list of indices at which `letter` is to be inserted, and the new list
        ; of underscores with `letter` inserted at the current index
        (fill-char (cdr blanks) letter (+ 1 current) (cdr target) (append build (list letter)))]
      ; Otherwise call `fill-char` with the second half of the `blanks` pair,
      ; the character to be inserted, the next index of the list of underscores,
      ; the list of indices at which `letter` is to be inserted, and the new list
      ; of underscores with the first half of the `blanks` pair appended
      [else (fill-char (cdr blanks) letter (+ 1 current) target (append build (list (car blanks))))]
    )
  )
  ; Call `fill-char` with the list of underscores,
  ; the letter to insert, a starting index of 0,
  ; the list of indices to insert `letter` into,
  ; and an empty list on which to build
  (fill-char blanks letter 0 target '())
)


; Main function to begin execution
(define (main)
  ; Random word selected from WORD_LIST
  (define word (get-random-word))
  ; Greet with title of game
  (display "\n\nLet's play Hangman!\n\n")

  ; Main loop for I/O
  ;
  ; wrongs - Integer representing number of incorrect guesses
  ; blanked - List containing an underscore and space for each letter in `word`
  ; guessed - List of strings containing letters of incorrect guesses
  ;
  (define (main-loop wrongs blanked guessed)
    (cond
      ; If number of wrong guesses is equal to the maximum
      ; incorrect guesses allowed
      [(eqv? wrongs MAX_WRONG)
        ; Show current state of gallows, revealed letters, and incorrect guesses
        (display-state wrongs blanked guessed)
        ; Tell them they are bad at this
        (display "\n\nYou lose!\n")
        ; Show what the word to be guessed actually was
        (display (string-append "The word was: " (list->string word)))
        ; Make space for more output
        (display "\n\n")]
      ; Otherwise if all letters have been correctly guessed
      [(not (string-contains? (list->string blanked) "_"))
        ; Show current state of gallows, revealed letters, and incorrect guesses
        (display-state wrongs blanked guessed)
        ; Tell them they are good at this
        (display "\n\nYou win!\n\n")]
      ; Otherwise if user needs to input more guesses
      [else
        ; Show current state of gallows, revealed letters, and incorrect guesses
        (display-state wrongs blanked guessed)
        ; Get character from user
        (define guess (get-user-char))
        (cond
          ; If `word` contains the character that was guessed
          [(string-contains? (list->string word) (string guess))
            ; Give congratulations
            (display "\nWoot!\n\n")
            ; Call main-loop with current incorrect guess count,
            ; list of blanks with correct guesses inserted,
            ; and the list of incorrect guesses
            (main-loop wrongs (fill-letter blanked guess (get-indices guess word)) guessed)]
          ; Otherwise make space for more output
          [else (display #\newline)
                ; Show incorrect guess message
                (display (string-append (string guess) " is not in the word!\n\n"))
                ; Call main-loop with incremented incorrect guess count,
                ; list of blanks with correct guesses inserted,
                ; and the list of incorrect guesses
                (main-loop (+ 1 wrongs) blanked (append guessed (list (string guess))))]
        )]
    )
  )

  ; Call main-loop with a starting incorrect guess count of 0, a list of underscores and spaces
  ; for each character in `word`, and an empty list to hold incorrect guesses
  (main-loop 0 (get-blanks word) '())

  ; Prompt user to play again or exit
  (display "Enter '(main)' to play again, or '(exit)' to quit!")
)

; Start
(main)