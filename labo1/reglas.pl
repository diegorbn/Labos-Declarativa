esta_en_peligro(Personaje) :-
    se_encuentra(Lugar, Personaje),
    zona(Lugar, Enemigo),
    infectado(Enemigo, plaga),
    !.

esta_armado(Personaje) :-
    armado_con(Personaje, pistola);
    armado_con(Personaje, cuchillo),
    !.

lugar_peligroso(Lugar) :-
    zona(Lugar, Enemigo),
    infectado(Enemigo, plaga),
    !.

puede_defenderse(Personaje) :-
    esta_armado(Personaje),
    esta_en_peligro(Personaje),
    !.

