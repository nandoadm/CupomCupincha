CREATE TABLE public.afiliado
(
    id         UUID         NOT NULL,
    nome       VARCHAR(100) NOT NULL,
    cnpj       VARCHAR(20),
    created_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_afiliado PRIMARY KEY (id)
);

CREATE TABLE public.loja_afiliado
(
    loja_id     UUID NOT NULL,
    afiliado_id UUID NOT NULL,
    CONSTRAINT pk_loja_afiliado PRIMARY KEY (loja_id, afiliado_id)
);

ALTER TABLE public.loja_afiliado
    ADD CONSTRAINT FK_LOJA_AFILIADO_ON_AFILIADO FOREIGN KEY (afiliado_id) REFERENCES public.afiliado (id);

ALTER TABLE public.loja_afiliado
    ADD CONSTRAINT FK_LOJA_AFILIADO_ON_LOJA FOREIGN KEY (loja_id) REFERENCES public.loja (id);

CREATE TABLE users
(
    id       UUID         NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);