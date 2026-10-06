almacenar(N, [N]) :-
    N >= 0,
    N < 10.

almacenar(N, [Digito|Rest]):-
    N >= 10,
    Digito is N mod 10,
    NuevoN is N // 10,
    almacenar(NuevoN, Rest).

