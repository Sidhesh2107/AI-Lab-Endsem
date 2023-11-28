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
male(ashok).
male(yatarth).
male(rahul).
male(sharvil).
male(rama).
male(bhiva).
male(raj).
male(sudarshan).
male(sriyansh).
male(advait).

female(valubai).
female(nilam).
female(sunita).
female(kavya).
female(radha).
female(rukmini).
female(muktabai).
female(pranjali).
female(pranali).
female(shanti).
female(poonam).
female(varsha).
female(ruchira).

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
parent(pranjali, yatarth).
parent(ashok, yatarth).
parent(rahul, sharvil).
parent(pranali, sharvil).
parent(rama, nilam).
parent(rama, poonam).
parent(rama, varsha).
parent(rama, sudarshan).
parent(shanti, nilam).
parent(shanti, poonam).
parent(shanti, varsha).
parent(shanti, sudarshan).
parent(varsha, advait).
parent(raj, advait).
parent(poonam, ruchira).
parent(poonam, sriyansh).
parent(bhiva, ruchira).
parent(bhiva, sriyansh).


married(namdeo, valubai).
married(kundlik, nilam).
married(datta, sunita).
married(vilas, rukmini).
married(dhondiram, muktabai).
married(ashok, pranjali).
married(rahul, pranali).
married(rama, shanti).
married(bhiva,poonam).
married(raj, varsha).


% Rules
grandparent(X, Y) :- parent(X, Z), parent(Z, Y).
sibling(X, Y) :- parent(Z, X), parent(Z, Y), X \== Y.
nephew(X, Y) :- child(X, A), sibling(A, Y), male(X).
niece(X, Y) :- child(X, A), sibling(A, Y), female(X).
aunt_or_uncle(X, Y) :- parent(Z, Y), sibling(X, Z).
husband(X, Y) :- married(X, Y).
child(X, Y) :- parent(Y, X).
son(X, Y) :- male(X), child(X, Y).
daughter(X, Y) :- female(X), child(X, Y).
mother_in_law(X, Y) :- parent(X, A), married(A,Y), female(X).
father_in_law(X, Y) :- parent(X, A), married(A,Y), male(X).
son_in_law(X,Y):-parent(Y,A),married(X,A),male(X).
daughter_in_law(X,Y):-parent(Y,A),married(A,X),female(X).
cousin(X,Y):-parent(A,X),sibling(A,B),parent(B,Y).
cousin2(mayur,advait):-parent(muktabai,mayur),sibling(muktabai,kundlik),married(kundlik,nilam),sibling(nilam,varsha),parent(varsha,advait).


relationship(X, Y, Relation) :-
    parent(X, Y),
    Relation = 'parent'.

relationship(X, Y, Relation) :-
    cousin2(X, Y),
    Relation = 'cousins'.

relationship(X, Y, Relation) :-
    cousin(X, Y),
    Relation = 'cousin'.

relationship(X, Y, Relation) :-
    son(X, Y),
    Relation = 'son'.

relationship(X, Y, Relation) :-
    daughter(X, Y),
    Relation = 'daughter'.
    
relationship(X, Y, Relation) :-
    child(Y, X),
    Relation = 'child'.

relationship(X, Y, Relation) :-
    grandparent(X, Y),
    Relation = 'grandparent'.
    
relationship(X, Y, Relation) :-
    grandparent(Y, X),
    Relation = 'grandchild'.
    
relationship(X, Y, Relation) :-
    sibling(X, Y),
    Relation = 'sibling'.
    

    
relationship(X, Y, Relation) :-
    aunt_or_uncle(X, Y),
    Relation = 'aunt/uncle'.
    
relationship(X, Y, Relation) :-
    nephew(X, Y),
    Relation = 'nephew'.

relationship(X, Y, Relation) :-
    niece(X, Y),
    Relation = 'niece'.
    
relationship(X, Y, Relation) :-
    mother_in_law(X, Y),
    Relation = 'mother-in-law'.
    
relationship(X, Y, Relation) :-
    father_in_law(X, Y),
    Relation = 'father-in-law'.
    
relationship(X, Y, Relation) :-
    son_in_law(X, Y),
    Relation = 'son-in-law'.
    
relationship(X, Y, Relation) :-
    daughter_in_law(X, Y),
    Relation = 'daughter-in-law'.
