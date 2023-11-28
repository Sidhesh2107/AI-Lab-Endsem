% Facts
male(namdeo).
male(kundlik).
male(datta).
male(siddhant).
male(shivam).
male(dhondiram).
male(vilas).
male(mayur).
male(akshay).

female(valubai).
female(nilam).
female(sunita).
female(kavya).
female(radha).
female(rukmini).
female(muktabai).
female(pranjali).
female(pranali).

% Relationships
parent(namdeo, kundlik).
parent(namdeo, datta).
parent(namdeo, rukmini).
parent(namdeo, muktabai).
parent(dhondiram, mayur).
parent(dhondiram, pranali).
parent(vilas, akshay).
parent(vilas, pranjali).
parent(kundlik, siddhant).
parent(datta, radha).
parent(datta, kavya).
parent(datta, shivam).
parent(valubai, kundlik).
parent(valubai, datta).
parent(valubai, rukmini).
parent(valubai, muktabai).
parent(nilam, siddhant).
parent(sunita, radha).
parent(sunita, kavya).
parent(sunita, shivam).
parent(rukmini, akshay).
parent(rukmini, pranjali).
parent(muktabai, mayur).
parent(muktabai, pranali).

married(namdeo, valubai).
married(kundlik, nilam).
married(datta, sunita).
married(vilas, rukmini).
married(dhondiram, muktabai).

% Rules
parent(X, Y) :- parent(X, Y).
grandparent(X, Y) :- parent(X, Z), parent(Z, Y).
grandson(Name, Person) :- grandparent(Name, Person), male(Person).
sibling(X, Y) :- parent(Z, X), parent(Z, Y), X \== Y.
cousin(X, Y) :- parent(Z, X), parent(W, Y), sibling(Z, W).
aunt_or_uncle(X, Y) :- parent(Z, Y), sibling(X, Z).
husband(X, Y) :- married(X, Y).
parent(Person, Parent) :- parent(Parent, Person).
son(Person, Son) :- parent(Person, Son), male(Son).
daughter(Person, Daughter) :- parent(Person, Daughter), female(Daughter).
nephew(X, Y) :- sibling(X, A), parent(A, Y).
parent_in_law(X, Y) :- parent(X, A), female(X), female(A), married(Y, A).