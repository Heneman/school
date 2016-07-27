/**
 * @author Kyle Heneman
 * @since 04/20/2016
 */

 /**
  * Import knowledgebase of drung interactions
  */
:- [meds].


/**
 * Tests if two drugs interact
 * 
 * @param D1 First drug to compare
 * @param D2 Second drug to compare
 *
 * @return true if drugs share an interaction
 */
do_interact(D1, D2) :-
  % Tests whether drugs interact with
  % each other in either order
	interacts(D1, D2, _) ; interacts(D2, D1, _).


/**
 * Test if drug interacts with others
 *
 * @param D1 Drug to test
 *
 * @return true if drug interacts with another
 */
does_interact(D) :-
	% Tests whether a drug interacts with
	% another in either order
	interacts(D, _, _) ; interacts(_, D, _).


/**
 * Gets the effects of two drugs when
 * they interact and prints them
 *
 * @param D1 First drug to test
 * @param D2 Second drug to test
 */
get_effects(D1, D2) :-
	% If two drugs have an interaction
	do_interact(D1, D2) ->
		% Get all effects of those interactions
		findall(E, (interacts(D1, D2, E) ; interacts(D2, D1, E)), AE1),
		% Sort the results and remove duplicates
		sort(AE1, AE2),
		% Write each effect that was found
		write_each(AE2)
		;
		% Tell user the two drugs don't interact
		write(D1), write(' does not interact with: '), write(D2).


/**
 * Gets drug interactions that may be causing given effect
 *
 * @param E Possible effect of a drug interaction
 */
get_causes(E) :-
	% If the effect given is the
	% result of two drugs interacting
	interacts(_, _, E) ->
		% Find all drug interactions that have given effect
		findall([D1, D2], (interacts(D1, D2, E) ; interacts(D2, D1, E)), AD1),
		% Sort the results and remove duplicates
		sort(AD1, AD2),
		% Write each drug interaction that was found
		write_each(AD2)
		;
		% Tell user the effect isn't caused by drug interactions
		write('No drug interactions found for effect: '), write(E).


/**
 * Gets other drugs that the given drug interacts with
 *
 * @param D Drug to test interactions for
 */
reacts_with(D) :-
	% If given drug interacts with any other
	does_interact(D) ->
	  % Find all interactions that given drug is a part of
		findall(D2, (interacts(D, D2, I) ; interacts(D2, D, I)), AD1),
		% Sort the results and remove duplicates
		sort(AD1, AD2),
		% Write each drug that was found
		write_each(AD2)
		;
		% Tell user the drug doesn't have any interactions
		write('No drugs found to interact with: '), write(D).


/**
 * Gets other drugs that the given drug does NOT interact with
 *
 * @param D Drug to test interactions for
 */
does_not_react_with(D) :-
	% Get all drugs given drug interacts with
	findall(D2, (interacts(D, D2, I) ; interacts(D2, D, I)), AD1),
	% Sort the results and remove duplicates
	sort(AD1, AD2),
	% Get all drugs that have an interaction but
	% aren't in the list of drugs that given drug interacts with
	findall(D2, ((interacts(D2, _, _) ; interacts(_, D2, _)), not(member(D2, AD2))), AD3),
	% Sort the results and remove duplicates
	sort(AD3, AD4),
	% Write each drug that given drug doesn't interact with
	write_each(AD4).


/**
 * Writes each item in a list
 *
 * @param List of items to write to terminal
 */
write_each([X | List]) :-
	% Write the head of the list
	write('* '), write(X), nl,
	% Call again with rest of list
	write_each(List).

	