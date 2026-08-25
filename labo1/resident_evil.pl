profesion(leon, agente).
profesion(luis, investigador).
profesion(ada, espia).
profesion(ashley, estudiante).

edad(leon, 27).
edad(ashley, 20).
edad(ada, 26).
edad(luis, 32).

arma(pistola).
arma(cuchillo).

armado_con(leon, pistola).
armado_con(leon, cuchillo).
armado_con(ada, pistola).
armado_con(luis, cuchillo).

dificultad(castillo, alta).
dificultad(isla, muy_alta).
dificultad(pueblo, alta).

infectado(ganado, plaga).
infectado(regeneraciones, plaga).

lugar(castillo).
lugar(pueblo).
lugar(isla).

zona(pueblo, ganado).
zona(castillo, ganado).
zona(isla, regeneradores).

se_encuentra(pueblo, leon).
se_encuentra(pueblo, luis).
se_encuentra(pueblo, ada).
se_encuentra(castillo, ada).

