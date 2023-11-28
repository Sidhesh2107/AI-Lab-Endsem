male(shree).
male(jeet).
male(tukaram).
male(prabhas).
male(vicky).
male(ankur).
male(jitendra).
male(rajendra).
male(sudhakar).
male(aditya).
male(ashish).
male(om).
male(sonu).
male(kapil).
male(amit).
male(john).
male(devid).
female(priya).
female(suman).
female(jyoti).
female(anjali).
female(prajakta).
female(lata).
female(swati).
female(hemlata).
female(mansi).
female(tanu).
female(sanu).
female(prabha).


parent(shree, jeet).
parent(shree, tukaram).
parent(priya, jeet).
parent(priya, tukaram).
parent(jeet, ankur).
parent(jeet, jitendra).
parent(jeet, rajendra).

parent(suman,ankur).
parent(suman,jitendra).
parent(suman,rajendra).

parent(jitendra,om).
parent(jitendra,tanu).
parent(prajakta,om).
parent(prajakta,tanu).
parent(sudhakar,sonu).
parent(sudhakar,sanu).
parent(swati,sonu).
parent(swati,sanu).
parent(rajendra,amit).
parent(rajendra,kapil).
parent(lata,amit).
parent(lata,kapil).
parent(ankur,aditya).
parent(ankur,ashish).
parent(jyoti,aditya).
parent(jyoti,ashish).
parent(tukaram,prabhas).
parent(tukaram, prabha).
parent(hemlata,prabhas).
parent(hemlata, prabha).

parent(prabha, john).
parent(devid, john).
parent(prabhas,vicky).
parent(mansi,vicky).
parent(aditya, soham).
parent(shreya,soham).
parent(vicky,sudhakar).
parent(anjali, sudhakar).

father(X, Y) :- male(X), parent(X, Y).

mother(X, Y) :- female(X), parent(X, Y).

wife(X,Y) :- mother(X,Z),father(Y,Z).

husband(X,Y):- father(X,Z),mother(Y,Z).

father_in_law(X, Y) :- male(X),female(Y),parent(Y,T),grandfather(X,T).

mother_in_law(X, Y) :- female(X),female(Y),parent(Y,T),grandmother(X,T).

son(X, Y) :- male(X), parent(Y, X).

daughter(X, Y) :- female(X), parent(Y, X).

grandfather(X, Z) :- father(X, Y), parent(Y, Z).

grandmother(X, Z) :- mother(X, Y), parent(Y, Z).

deer(X,Y) :- male(W),male(X),sibling(X,W),husband(W,Y).


deerani(X,Y) :- mother(X,W),cousin(W,T),mother(Y,T).


neice(X,Y):- cousin(X,Z), parent(Y,Z).
sibling(X, Y) :- parent(Z, X), parent(Z, Y), X \= Y.

cousin(X, Y) :- parent(Z, X), parent(W, Y), sibling(Z, W).

uncle(X, Y) :- parent(Z, Y), sibling(X, Z), male(X).

aunt(X, Y) :- cousin(Z, Y), mother(X, Z), female(X).

second_uncle(X, Y) :-male(X),grandfather(W, Y), sibling(W,Z),son(X,Z).

second_aunt(X, Y) :-female(X),grandfather(W, Y), sibling(W,Z),grandfather(Z,D),mother(X,D).

second_cousin(X, Y) :- grandfather(W, Y), sibling(W,Z),grandfather(Z,X).

second_grandfather(X,Y) :- grandfather(W,Y),sibling(W,X).

second_grandmother(X,Y) :- second_uncle(W,Y),mother(X,W).

great_grandfather(X, Z) :- father(X, Y), grandfather(Y, Z).

great_grandmother(X, Z) :- mother(X, Y), grandfather(Y, Z).

daughter_in_law(X,Z):- husband(X,Y), parent(Z,Y).
son_in_law(X,Z) :- husband(X,Y), parent(Z,Y).

grandson(X, Y) :- male(X), parent(Y, Z), parent(Z, X).
granddaughter(X, Y) :- female(X), parent(Y, Z), parent(Z, X).

great_grandson(X, Y) :- male(X), parent(Y, Z), parent(Z, W), parent(W, X).
great_granddaughter(X, Y) :- female(X), parent(Y, Z), parent(Z, W), parent(W, X).




% Your family relationships and rules here
find_relationship(X, Y) :-
    father(X, Y), write(X), write(' is the father of '), write(Y), nl.

find_relationship(X, Y) :-
    wife(X, Y), write(X), write(' is the wife of '), write(Y), nl.

find_relationship(X, Y) :-
    husband(X, Y), write(X), write(' is the husband of '), write(Y), nl.

find_relationship(X, Y) :-
    mother(X, Y), write(X), write(' is the mother of '), write(Y), nl.

find_relationship(X, Y) :-
    son(X, Y), write(X), write(' is the son of '), write(Y), nl.

find_relationship(X, Y) :-
    daughter(X, Y), write(X), write(' is the daughter of '), write(Y), nl.

find_relationship(X, Y) :-
    grandfather(X, Y), write(X), write(' is the grandfather of '), write(Y), nl.

find_relationship(X, Y) :-
    sibling(X, Y), write(X), write(' is the sibling of '), write(Y), nl.

find_relationship(X, Y) :-
    father(Z, Y), mother(X, Z), write(X), write(' is the grandmother of '), write(Y), nl.

find_relationship(X, Y) :-
    second_grandmother(X,Y), write(X), write(' is the second grandmother of '), write(Y), nl.

find_relationship(X, Y) :-
    second_grandfather(X,Y), write(X), write(' is the second grandfather of '), write(Y), nl.

find_relationship(X, Y) :-
    second_uncle(X, Y), write(X), write(' is the second-uncle of '), write(Y), nl.

find_relationship(X, Y) :-
    second_aunt(X, Y), write(X), write(' is the second-aunt of '), write(Y), nl.

find_relationship(X, Y) :-
    uncle(X, Y), write(X), write(' is the uncle of '), write(Y), nl.

find_relationship(X, Y) :-
    aunt(X, Y), write(X), write(' is the aunt of '), write(Y), nl.

find_relationship(X, Y) :-
    cousin(X, Y), write(X), write(' is the cousin of '), write(Y), nl.

find_relationship(X, Y) :-
    deerani(X, Y), write(X), write(' is the deerani of '), write(Y), nl.

find_relationship(X, Y) :-
    deer(X, Y), write(X), write(' is the deer of '), write(Y), nl.

find_relationship(X, Y) :-
    second_cousin(X, Y), write(X), write(' is the second cousin of '), write(Y), nl.

find_relationship(X, Y) :-
    father_in_law(X, Y), write(X), write(' is the father-in-law of '), write(Y), nl.

find_relationship(X, Y) :-
    mother_in_law(X, Y), write(X), write(' is the mother-in-law of '), write(Y), nl.

find_relationship(X, Y) :-
    great_grandfather(X, Y), write(X), write(' is the great-grandfather of '), write(Y), nl.

find_relationship(X, Y) :-
    great_grandmother(X, Y), write(X), write(' is the great-grandmother of '), write(Y), nl.




find_relationship(X, Y) :-
    daughter_in_law(X,Y), write(X), write(' is the daughter in law  of '), write(Y), nl.

find_relationship(X, Y) :-
    son_in_law(X,Y), write(X), write(' is the son in law  of '), write(Y), nl.

    find_relationship(X, Y) :-
    grandson(X, Y), write(X), write(' is the grandson of '), write(Y), nl.


find_relationship(X, Y) :-
    granddaughter(X, Y), write(X), write(' is the granddaughter of '), write(Y), nl.


find_relationship(X, Y) :-
    great_grandson(X, Y), write(X), write(' is the great-grandson of '), write(Y), nl.

find_relationship(X, Y) :-
    great_granddaughter(X, Y), write(X), write(' is the great-granddaughter of '), write(Y), nl.


find_relationship(X, Y) :- write('Relationship not defined between '), write(X), write(' and '), write(Y), nl.


