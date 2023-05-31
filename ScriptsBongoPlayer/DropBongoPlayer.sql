-- Drop relationships section -------------------------------------------------

ALTER TABLE "FAVORITO" DROP CONSTRAINT "Relationship5"
;
ALTER TABLE "FAVORITO" DROP CONSTRAINT "Relationship4"
;
ALTER TABLE "LISTA_CANCION" DROP CONSTRAINT "Relationship3"
;
ALTER TABLE "LISTA_CANCION" DROP CONSTRAINT "Relationship2"
;
ALTER TABLE "LISTA" DROP CONSTRAINT "Relationship1"
;




-- Drop keys for tables section -------------------------------------------------

ALTER TABLE "FAVORITO" DROP CONSTRAINT "Key5"
;
ALTER TABLE "LISTA_CANCION" DROP CONSTRAINT "Key4"
;
ALTER TABLE "CANCION" DROP CONSTRAINT "Key3"
;
ALTER TABLE "LISTA" DROP CONSTRAINT "Key2"
;
ALTER TABLE "LISTA" DROP CONSTRAINT "AK_NOMBRE_USUARIO"
;
ALTER TABLE "USUARIO" DROP CONSTRAINT "Key1"
;
ALTER TABLE "USUARIO" DROP CONSTRAINT "CORREO"
;
ALTER TABLE "USUARIO" DROP CONSTRAINT "ALIAS"
;


-- Drop indexes section -------------------------------------------------

DROP INDEX "IX_Relationship1"
;


-- Drop tables section ---------------------------------------------------

DROP TABLE "FAVORITO"
;
DROP TABLE "LISTA_CANCION"
;
DROP TABLE "CANCION"
;
DROP TABLE "LISTA"
;
DROP TABLE "USUARIO"
;

-- Drop Sequences ------------------------------
drop sequence S_USUARIO;
drop sequence S_CANCION;
drop sequence S_LISTA;
