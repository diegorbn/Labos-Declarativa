% Pues no hay mucho que hacer aqui xd

add(FirstNumber, SecondNumber, Result) :-
    number(FirstNumber),
    number(SecondNumber),
    Result is FirstNumber + SecondNumber.

subtract(FirstNumber, SecondNumber, Result) :-
    number(FirstNumber),
    number(SecondNumber),
    Result is FirstNumber - SecondNumber.
